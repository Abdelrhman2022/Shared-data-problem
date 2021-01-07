/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MatrixMultiplication;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author AbdallaEssam
 */
/* test */
public class MainExample {

    public static void main(String[] args) {
        double matrix1[][] = MatrixGenerator.generate(1000, 1000);
        double matrix2[][] = MatrixGenerator.generate(1000, 1000);
        double result[][] = new double[matrix1.length][matrix2[0].length];
       
        long startTime = System.nanoTime();
        ParallelRowMultipler.multiply(matrix1, matrix2, result);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("Execution time ParallelRowMultipler in milliseconds : " + timeElapsed / 1000000);
        
         startTime = System.nanoTime();
        ParallelGroupMultipler.multiply(matrix1, matrix2, result);
         endTime = System.nanoTime();
         timeElapsed = endTime - startTime;
        System.out.println("Execution time ParallelGroupMultipler in milliseconds : " + timeElapsed / 1000000);


    }
}
