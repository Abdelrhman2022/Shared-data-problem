package MatrixMultiplication;

import java.util.Random;

/**
 * generate random matrices
 */
public class MatrixGenerator {
    /**
     * Generate matrices of size rows, columns with double elements
     * @param rows number of rows
     * @param columns number of columns 
     * @return 
     */
    public static double[][] generate(int rows, int columns) {
        double[][] ret = new double[rows][columns];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ret[i][j] = random.nextDouble() * 10;
            }
        }
        return ret;
    }
}
