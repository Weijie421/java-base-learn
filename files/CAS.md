#CAS  
比较和交换  compareAndSwap,
它是一条CPU并发原语  
它的功能实判断内存某个位置的值是否为预期值，如果是则更新为新的值，这个过程是原子的。  
CAS并发原语体现在JAVA中就是sun.misc.Unsafe类中的各个方法，调用Unsafe类中的CAS方法，JVM会帮我们实现CAS汇编指令。这是一种完全依赖于硬件的功能。
CAS是系统原语，原语属于操作系统用语范畴，是由若干条指令组成的，用于完成某个功能的一个过程，并且原语的执行必须是连续的，在执行过程中不允许被中断，因此
不会造成数据不一致问题。  
```
    public final int getAndIncrement() {
        return unsafe.getAndAddInt(this, valueOffset, 1);
    }
```
> this : 当前对象   
> valueOffset : 内存偏移量
## 源码
```
public class AtomicInteger extends Number implements java.io.Serializable {
    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                (AtomicInteger.class.getDeclaredField("value"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    private volatile int value;
```
## 自旋锁
```
    public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }
```
## unsafe
CAS的核心类，由于Java方法无法直接访问底层系统，需要通过本地native方法来访问，unsafe相当于一个后门，基于该类可以直接操作特定内存数据。
**Unsafe类存在于sun.misc**包中，其内部方法操作可以向C的指针一样直接操作内存，因此JAVA中CAS操作的执行依赖于Unsafe类的方法。
注：Unsafe类中所有的方法都是native修饰的，Unsafe类中的方法都直接调用操作系统底层资源执行相应的任务。  

## 缺点
1. **循环时间长，开销大**。如果CAS失败，会一直进行尝试，如果CAS长时间一直不成功，会给CPU带来很大的开销。  
2. **只能保证一个共享变量的原子操作**  
3. **ABA问题**

# ABA问题  
CAS首尾比较值一致，但是中间过程中其他线程可能多次修改过主内存。
尽管当前线程的CAS操作成功，但是不代表这个过程是没问题的。  

### ABA方法解决办法
加时间戳（流水号）
atomicStampedReference