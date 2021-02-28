package com.rwj.Thread;

class Ticket{
    private int number = 30;
    public synchronized void saleTicket(){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"\t 买到票了 "+ (number--)+" 还剩 "+number);
        }
    }
}

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                for (int j = 0; j < 30; j++) {
                    ticket.saleTicket();
                }
            },String.valueOf(i)).start();
        }

    }
}
