package test.java.problems;

import main.java.problems.*;

import java.util.Random;

class ZeroOneMatrixTest {

    public static void main(String [ ] args) {

        testSize(10);
        testSize(50);
        testSize(100);
        testSize(500);

    }

    private static void testSize(int size) {

        System.out.println(String.format("%s x %s Matrix", size, size));

        ZeroOneMatrix zeroOneMatrix = new ZeroOneMatrix();

        int[][] matrix = createMatrix(size);
        long startTime = System.currentTimeMillis();
        zeroOneMatrix.bruteForce(matrix);
        long endTime = System.currentTimeMillis();
        System.out.println("Brute Force Time: " + (endTime - startTime));

        matrix = createMatrix(size);
        startTime = System.currentTimeMillis();
        zeroOneMatrix.breadthFirstSearch(matrix);
        endTime = System.currentTimeMillis();
        System.out.println("Breadth First Search Time: " + (endTime - startTime));

        matrix = createMatrix(size);
        startTime = System.currentTimeMillis();
        zeroOneMatrix.dynamicProgramming(matrix);
        endTime = System.currentTimeMillis();
        System.out.println("Dynamic Programming Time: " + (endTime - startTime));

        System.out.println();
    }

    private static int[][] createMatrix(int size) {

        Random random = new Random();
        int[][] matrix = new int[size][size];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = random.nextInt(2);
            }
        }

        return matrix;
    }
}