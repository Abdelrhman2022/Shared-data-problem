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
public class GroupMultiplierTask extends MultiplierTask {

    protected final int row;

    public GroupMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int row) {
        super(result, matrix1, matrix2);
        this.row = row;
    }

    @Override
    public void run() {
        for (int x = row; x < row + 3; x++) {
            if (x > matrix1.length) {
                break;
            }
            for (int i = 0; i < matrix2[0].length; i++) {
                result[row][i] = 0;
                for (int j = 0; j < matrix1[row].length; j++) {
                    result[row][i] += matrix1[row][j] * matrix2[j][i];

                }

            }
        }
    }
}
