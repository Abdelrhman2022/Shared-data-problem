package Barrier;



import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AbdallaEssam
 */
public class Barrier_sem extends Barrier implements Runnable {

    private final Semaphore mutex;
    private final Semaphore blockerSemaphore;

    public Barrier_sem(int waitingCount) {
        super(waitingCount);
        mutex = new Semaphore(1);
        blockerSemaphore = new Semaphore(0);
    }

    @Override
    public void barrierPoint() {
        try {
            if (noOfWatingThreads == maxWaiting)
            {
                blockerSemaphore.release();
                System.out.println("All " + noOfWatingThreads + " threads have reached the barrier. The barrier is now open" );
                noOfWatingThreads = 0;
            } // unblock one thread
            
            blockerSemaphore.acquire();
            blockerSemaphore.release();  
            System.out.println(Thread.currentThread().getName() + " has passed the barrier");
        } catch (InterruptedException ex) {
            Logger.getLogger(Barrier_sem.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public void run() {
        try {
            mutex.acquire();
            noOfWatingThreads = noOfWatingThreads + 1;
            System.out.println(Thread.currentThread().getName() + " is working");
            mutex.release();
            
            barrierPoint();
        } catch (InterruptedException ex) {
            Logger.getLogger(Barrier_sem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
