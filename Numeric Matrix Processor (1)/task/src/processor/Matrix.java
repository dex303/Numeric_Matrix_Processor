package processor;

import java.util.Scanner;

public class Matrix {
    double[][] matrix;

    public Matrix(Scanner scanner, int aRow, int aCol) {
        matrix = new double[aRow][aCol];
        for (int i = 0; i < aRow; i++) {
            for (int j = 0; j < aCol; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
    }

    public Matrix(int aRow, int aCol) {
        matrix = new double[aRow][aCol];
    }

    public double determinant() {
        if (this.matrix.length == 1) {
            return this.matrix[0][0];
        } else if (this.matrix.length == 2) {
            return this.matrix[0][0] * this.matrix[1][1] - this.matrix[0][1] * this.matrix[1][0];
        } else {
            double determinant = 0;
            for (int r = 0; r < this.matrix[0].length; r++) {
                Matrix newMatrix = new Matrix(this.matrix.length - 1, this.matrix.length - 1);
                for (int i = 0; i < this.matrix.length; i++) {
                    for (int j = 0; j < this.matrix[0].length; j++) {
                        if (i == 0 || j == r) {
                            continue;
                        } else {
                            if (j < r) {
                                newMatrix.matrix[i - 1][j] = this.matrix[i][j];
                            } else {
                                newMatrix.matrix[i - 1][j - 1] = this.matrix[i][j];
                            }
                        }
                    }
                }
                if (r % 2 == 0) {
                    determinant += this.matrix[0][r] * newMatrix.determinant();
                } else {
                    determinant += -this.matrix[0][r] * newMatrix.determinant();
                }
            }
            return determinant;
        }
    }

    public void inverse() {
        double det = this.determinant();
        if (det == 0) {
            System.out.println("There is no inverse matrix");
            return;
        }

        double[][] cofactor = new double[this.matrix.length][this.matrix[0].length];

        for (int a = 0; a < cofactor.length; a++) {
            for (int b = 0; b < cofactor[0].length; b++) {
                Matrix zMatrix = new Matrix(cofactor.length - 1, cofactor[0].length - 1);
                for (int i = 0; i < cofactor.length; i++) {
                    for (int j = 0; j < cofactor[0].length; j++) {
                        if (i == a || j == b) {
                            continue;
                        } else if (i < a) {
                            if (j < b) {
                                zMatrix.matrix[i][j] = this.matrix[i][j];
                            } else {
                                zMatrix.matrix[i][j - 1] = this.matrix[i][j];
                            }
                        } else if (i > a) {
                            if (j < b) {
                                zMatrix.matrix[i - 1][j] = this.matrix[i][j];
                            } else {
                                zMatrix.matrix[i - 1][j - 1] = this.matrix[i][j];
                            }
                        }
                    }
                }

                if (((a + 1) + (b + 1)) % 2 != 0) {
                    cofactor[a][b] = -1 * zMatrix.determinant();
                } else {
                    cofactor[a][b] = zMatrix.determinant();
                }
            }
        }


        double[][] tMatrix = new double[cofactor.length][cofactor.length];
        for (int row = 0; row < tMatrix.length; row++) {
            for (int col = 0; col < tMatrix.length; col++) {
                tMatrix[row][col] = cofactor[col][row];
            }
        }

        System.out.println("\nThe inverse matrix is:");
        int result;
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                result = (int) (tMatrix[i][j] / det * 100);
                System.out.print((double) result / 100 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
