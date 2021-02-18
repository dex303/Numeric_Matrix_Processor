package processor;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix by a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    byConst();
                    break;
                case 3:
                    byMatrix();
                    break;
                case 4:
                    transpose();
                    break;
                case 5:
                    System.out.print("Enter matrix size: ");
                    int aRow = scanner.nextInt();
                    int aCol = scanner.nextInt();
                    System.out.println("Enter matrix: ");
                    Matrix aMatrix = new Matrix(scanner, aRow, aCol);
                    System.out.println(aMatrix.determinant());
                    break;
                case 6:
                    System.out.print("Enter matrix size: ");
                    int aRo = scanner.nextInt();
                    int aCo = scanner.nextInt();
                    System.out.println("Enter matrix: ");
                    new Matrix(scanner, aRo, aCo).inverse();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    private static void transpose() {
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        System.out.print("Your choice: ");
        int secondChoice = scanner.nextInt();

        System.out.print("Enter matrix size: ");
        int aRow = scanner.nextInt();
        int aCol = scanner.nextInt();
        System.out.println("Enter matrix: ");
        Matrix aMatrix = new Matrix(scanner, aRow, aCol);
        double[][] bMatrix;

        switch (secondChoice) {
            case 1:
                bMatrix = new double[aCol][aRow];
                for (int row = 0; row < aCol; row++) {
                    for (int col = 0; col < aRow; col++) {
                        bMatrix[row][col] = aMatrix.matrix[col][row];
                        System.out.print(bMatrix[row][col] + " ");
                    }
                    System.out.println();
                }
                break;
            case 2:
                bMatrix = new double[aCol][aRow];
                for (int row = 0; row < aCol; row++) {
                    for (int col = 0; col < aRow; col++) {
                        bMatrix[row][col] = aMatrix.matrix[aMatrix.matrix[0].length - 1 - col][aMatrix.matrix.length - 1 - row];
                        System.out.print(bMatrix[row][col] + " ");
                    }
                    System.out.println();
                }
                break;
            case 3:
                bMatrix = new double[aRow][aCol];
                for (int row = 0; row < aCol; row++) {
                    for (int col = 0; col < aRow; col++) {
                        bMatrix[row][col] = aMatrix.matrix[row][aMatrix.matrix[0].length - 1 - col];
                        System.out.print(bMatrix[row][col] + " ");
                    }
                    System.out.println();
                }
                break;
            case 4:
                bMatrix = new double[aRow][aCol];
                for (int row = 0; row < aCol; row++) {
                    for (int col = 0; col < aRow; col++) {
                        bMatrix[row][col] = aMatrix.matrix[aMatrix.matrix.length - 1 - row][col];
                        System.out.print(bMatrix[row][col] + " ");
                    }
                    System.out.println();
                }
                break;
        }
    }

    private static void byMatrix() {
        System.out.print("Enter size of first matrix: ");
        int aRow = scanner.nextInt();
        int aCol = scanner.nextInt();
        System.out.println("Enter first matrix: ");
        Matrix aMatrix = new Matrix(scanner, aRow, aCol);

        System.out.print("Enter size of second matrix: ");
        int bRow = scanner.nextInt();
        int bCol = scanner.nextInt();
        System.out.println("Enter second matrix: ");
        Matrix bMatrix = new Matrix(scanner, bRow, bCol);

        if (aCol != bRow) {
            System.out.println("ERROR");
        } else {
            System.out.println("The multiplication result is:");
            double dot;
            for (int i = 0; i < aRow; i++) {
                for (int j = 0; j < bCol; j++) {
                    dot = 0;
                    for (int a = 0; a < aCol; a++) {
                        dot += aMatrix.matrix[i][a] * bMatrix.matrix[a][j];
                    }
                    System.out.print(dot + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private static void byConst() {
        System.out.print("Enter size of matrix: ");
        int aRow = scanner.nextInt();
        int aCol = scanner.nextInt();
        System.out.println("Enter matrix: ");
        Matrix cMatrix = new Matrix(scanner, aRow, aCol);

        System.out.print("Enter constant: ");
        double CONSTANT = scanner.nextDouble();

        System.out.println("The multiplication result is:");
        for (int i = 0; i < aRow; i++) {
            for (int j = 0; j < aCol; j++) {
                System.out.print(cMatrix.matrix[i][j] * CONSTANT + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void add() {
        System.out.print("Enter size of first matrix: ");
        int aRow = scanner.nextInt();
        int aCol = scanner.nextInt();
        System.out.println("Enter first matrix: ");
        Matrix aMatrix = new Matrix(scanner, aRow, aCol);

        System.out.print("Enter size of second matrix: ");
        int bRow = scanner.nextInt();
        int bCol = scanner.nextInt();
        System.out.println("Enter second matrix: ");
        Matrix bMatrix = new Matrix(scanner, bRow, bCol);

        if (aRow != bRow || aCol != bCol) {
            System.out.println("ERROR");
        } else {
            System.out.println("The addition result is:");
            for (int i = 0; i < aRow; i++) {
                for (int j = 0; j < aCol; j++) {
                    System.out.print(aMatrix.matrix[i][j] + bMatrix.matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
