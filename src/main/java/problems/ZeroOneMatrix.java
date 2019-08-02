package main.java.problems;

import java.util.LinkedList;
import java.util.Queue;

//  https://leetcode.com/problems/01-matrix/
//
//  Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
//  The distance between two adjacent cells is 1.
//
//
//        Example 1:
//
//        Input:
//        [[0,0,0],
//        [0,1,0],
//        [0,0,0]]
//
//        Output:
//        [[0,0,0],
//        [0,1,0],
//        [0,0,0]]
//
//
//        Example 2:
//
//        Input:
//        [[0,0,0],
//        [0,1,0],
//        [1,1,1]]
//
//        Output:
//        [[0,0,0],
//        [0,1,0],
//        [1,2,1]]

public class ZeroOneMatrix {

    // Brute Force - Naive Approach
    // - for each value of 1, check distance of all 0s from that cell and take the minimum
    // O((mn)2) Time Complexity
    // O(1) Space Complexity
    public int[][] bruteForce(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    matrix[i][j] = Integer.MAX_VALUE;
                    for (int r = 0; r < matrix.length; r++) {
                        for (int c = 0; c < matrix[0].length; c++) {
                            if (matrix[r][c] == 0) {
                                int distance = Math.abs(r-i) + Math.abs(c-j);
                                matrix[i][j] = Math.min(matrix[i][j], distance);
                            }
                        }
                    }
                }
            }
        }

        return matrix;
    }

    // Breadth First Search
    // - adding all 0s into queue and setting 1s to integer max value
    // - dequeue 0s and adjust neighbors that are greater by a distance of 1
    // O(mn) Time Complexity
    // O(mn) Space Complexity
    public int[][] breadthFirstSearch(int[][] matrix) {

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : directions) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r < 0 || r >= m || c < 0 || c >= n ||
                        matrix[r][c] <= matrix[cell[0]][cell[1]] + 1) {
                    continue;
                }
                queue.add(new int[]{r, c});
                matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
            }
        }

        return matrix;
    }

    // Dynmaic Programming
    // - going from top left to bottom right
    // - reverse direction in case matrix[0][0] = 1
    // O(mn) Time Complexity
    // O(1) Space Complexity
    public int[][] dynamicProgramming(int[][] matrix) {

        int max = matrix.length * matrix[0].length;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    int upCell = i > 0 ? matrix[i - 1][j] + 1 : max;
                    int leftCell = j > 0 ? matrix[i][j - 1] + 1 : max;
                    matrix[i][j] = Math.min(upCell, leftCell);
                }
            }
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                if (matrix[i][j] != 0) {
                    int downCell = i < matrix.length - 1 ? matrix[i + 1][j] + 1 : max;
                    int rightCell = j < matrix[0].length - 1 ? matrix[i][j + 1] + 1 : max;
                    matrix[i][j] = Math.min(matrix[i][j], Math.min(downCell, rightCell));
                }
            }
        }

        return matrix;
    }


}
