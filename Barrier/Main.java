package Barrier;


public class Main {

    public static void main(String[] args) {
        Barrier_sem barrier = new Barrier_sem(10);

    Thread bs1 = new Thread(barrier);
    Thread bs2 = new Thread(barrier);
    Thread bs3 = new Thread(barrier);
    Thread bs4 = new Thread(barrier);
    Thread bs5 = new Thread(barrier);
    Thread bs6 = new Thread(barrier);
    Thread bs7 = new Thread(barrier);
    Thread bs8 = new Thread(barrier);
    Thread bs9 = new Thread(barrier);
    Thread bs10 = new Thread(barrier);

    bs1.start();
    bs2.start();        
    bs3.start();        
    bs4.start();
    bs5.start();
    bs6.start();
    bs7.start();        
    bs8.start();        
    bs9.start();
    bs10.start();
    }
}
