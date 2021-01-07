package MatrixMultiplication;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Genius
 */
public class IndividualMultiplerTask extends MultiplierTask {

    protected final int row;
    protected final int column;

    public IndividualMultiplerTask(double[][] result, double[][] matrix1, double[][] matrix2, int row, int column) {
        super(result, matrix1, matrix2);
        this.row = row;
        this.column = column;
    }

    public void run() {
        result[row][column] = 0;
        for (int i = 0 , j = 0 ; (i < matrix2[0].length) && (j < matrix1[row].length); i++,j++){
            
                result[row][column] += matrix1[row][i] * matrix2[j][column];
            
        }
    }
}
