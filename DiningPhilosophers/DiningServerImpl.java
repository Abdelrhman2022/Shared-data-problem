package DiningPhilosophers;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiningServerImpl implements DiningServer {
    
    private Semaphore mutex = new Semaphore(1);
    private Semaphore[] chopstick = new Semaphore[5];

    // different philosopher states
    enum State {
        THINKING, HUNGRY, EATING
    };

    // number of philosophers
    public static final int NUM_OF_PHILS = 5;

    // array to record each philosopher's state
    private State[] state=new State[NUM_OF_PHILS];
    
    public DiningServerImpl() {
        
        for (int i = 0; i < 5; i++) {
            chopstick[i] = new Semaphore(0);
        }
        
        for (int i = 0; i < NUM_OF_PHILS; i++) {
            state[i] = State.THINKING;
        }
    }

    // called by a philosopher when they wish to eat 
    @Override
    public void takeForks(int pnum) {
        try {
            mutex.acquire();
            // state that hungry 
            state[pnum] = State.HUNGRY;
            // eat if neighbours are not eating 
            test(pnum);
            
            mutex.release();

            // if unable to eat wait to be signalled 
            chopstick[pnum].acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(DiningServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // called by a philosopher when they are finished eating 
    @Override
    public void returnForks(int pnum) {
        try {
            mutex.acquire();
            // state that thinking 
            state[pnum] = State.THINKING;
          
            test(leftNeighboar(pnum));            
            test(rightNeighboar(pnum));            
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(DiningServerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void test(int pnum) {
        if (state[pnum] == State.HUNGRY && state[leftNeighboar(pnum)] != State.HUNGRY && state[rightNeighboar(pnum)] != State.HUNGRY) {
            state[pnum] = State.EATING;
         // sem_post(&S[phnum]) has no effect during takefork used to wake up hungry philosophers during putfork 
        chopstick[pnum].release();
        }
    }
    
    private int leftNeighboar(int pnum) {
        return (pnum + 4) % NUM_OF_PHILS;
    }
    
    private int rightNeighboar(int pnum) {
        return (pnum + 1) % NUM_OF_PHILS;
    }
}
