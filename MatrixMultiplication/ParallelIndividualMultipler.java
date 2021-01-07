package MatrixMultiplication;


import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Genius
 */
public class ParallelIndividualMultipler extends Multiplier {

    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        List threads = new ArrayList<>();
        int row = result.length;
        int column = result[0].length;
        for (int i = 0; i < row; i++) {
            for (int x = 0; x < column; x++) {

                IndividualMultiplerTask task = new IndividualMultiplerTask(result, matrix1, matrix2, i, x);
                Thread thread = new Thread(task);
                thread.start();
                threads.add(thread);
                if (threads.size() % 10 == 0) {
                    waitForThreads(threads);
                }

            }
        }
    }
}
