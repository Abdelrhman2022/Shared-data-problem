package MatrixMultiplication;

import java.util.List;

public class Multiplier {
    
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result){
        throw new UnsupportedOperationException("No implementation for multiply method, write your own implementation");
    };
    
    protected static void waitForThreads(List<Thread> threads) {
        
        for( Object t : threads) {
            Thread thread = (Thread)t;
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }
}
