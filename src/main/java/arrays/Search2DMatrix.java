package main.java.arrays;

//    Search a 2D Matrix
//        - https://leetcode.com/problems/search-a-2d-matrix-ii/
//
//    Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following
//    properties:
//        - Integers in each row are sorted in ascending from left to right.
//        - Integers in each column are sorted in ascending from top to bottom.
//
//    Example:
//    Consider the following matrix:
//        [[1,   4,  7, 11, 15],
//         [2,   5,  8, 12, 19],
//         [3,   6,  9, 16, 22],
//         [10, 13, 14, 17, 24],
//         [18, 21, 23, 26, 30]]
//    Given target = 5, return true.
//    Given target = 20, return false.

public class Search2DMatrix {
    public static void main(String[] args) {
        Search2DMatrix sdm = new Search2DMatrix();
        int[][] matrix = {{1, 4, 7, 11, 15},
                          {2, 5, 8, 12, 19},
                          {3, 6, 9, 16, 22},
                          {10, 13, 14, 17, 24},
                          {18, 21, 23, 26, 30}};
        System.out.println(sdm.searchMatrix(matrix, 5));
        System.out.println(sdm.searchMatrix(matrix, 20));
    }

    // Two Pointer Method
    // - starting from top left hand corner, check and return true if value equals target
    // - if value is larger than target, move right (smaller values) until reach the right most element
    // - if value is smaller than target, move down (larger values) until reach the bottom
    // O(m+n) Time Complexity
    // O(1) Space Complexity
    public boolean searchMatrix(int[][] matrix, int target) {

        int i = matrix.length - 1, j = 0;

        while (i >= 0 && j < matrix[i].length) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                i--;
            } else {
                j++;
            }
        }

        // return false (not found) if i = -1 or j = matrix[i].length
        return false;
    }
}
