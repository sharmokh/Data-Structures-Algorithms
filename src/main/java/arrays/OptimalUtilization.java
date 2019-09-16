package main.java.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    Optimal Utilization
//
//    Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the
//    second integer represents a value. Your task is to find an element from a and an element form b such that the sum
//    of their values is less or equal to target and as close to target as possible. Return a list of ids of selected
//    elements. If no pair is possible, return an empty list.
//
//    Example 1:
//    Input:
//        a = [[1, 2], [2, 4], [3, 6]]
//        b = [[1, 2]]
//        target = 7
//    Output: [[2, 1]]
//    Explanation:
//        There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8,
//        respectively.  Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
//
//    Example 2:
//    Input:
//        a = [[1, 3], [2, 5], [3, 7], [4, 10]]
//        b = [[1, 2], [2, 3], [3, 4], [4, 5]]
//        target = 10
//    Output: [[2, 4], [3, 2]]
//    Explanation:
//        There are two pairs possible. Element with id = 2 from the list `a` has a value 5, and element with id = 4
//        from the list `b` also has a value 5.  Combined, they add up to 10. Similarily, element with id = 3 from `a`
//        has a value 7, and element with id = 2 from `b` has a value 3.  These also add up to 10. Therefore, the
//        optimal pairs are [2, 4] and [3, 2].
//
//    Example 3:
//    Input:
//        a = [[1, 8], [2, 7], [3, 14]]
//        b = [[1, 5], [2, 10], [3, 14]]
//        target = 20
//    Output: [[3, 1]]
//
//    Example 4:
//    Input:
//        a = [[1, 8], [2, 15], [3, 9]]
//        b = [[1, 8], [2, 11], [3, 12]]
//        target = 20
//    Output: [[1, 3], [3, 2]]

public class OptimalUtilization {

    public static void main(String[] args) {

        OptimalUtilization optimalUtilization = new OptimalUtilization();
        int[][] A = {{1,3}, {2,5}, {3,7}, {4,10}};
        int[][] B = {{1,2}, {2,3}, {3,4}, {4,5}, {5,5}};
        List<int[]> results = optimalUtilization.sortMethod(A, B, 10);
        for (int[] result : results) {
            System.out.print(Arrays.toString(result) + " ");
        }
    }

    // Sort Method
    // - Sort both arrays
    // - Start with first element of A and last element of B to determine closest sum less than or equal to target
    // - Find addition elements of equal value
    // O(nlogn) Time Complexity due to Sort
    // O(A*B) Space Complexity if each element of A by each element of B is part of solution
    public List<int[]> sortMethod(int[][] A, int[][] B, int target) {

        List<int[]> list = new ArrayList<>();

        // Sort A and B by values
        Arrays.sort(A, (i, j) -> i[1] - j[1]);
        Arrays.sort(B, (i, j) -> i[1] - j[1]);

        // Loop backwards through B first to find first sum of A & B below or equal to target
        // Loop forward through A in case previous sum is less than target until sum of A & B are equal or greater
        // Repeat if the next A's and previous B's sum is closer to target
        int i = 0, j = B.length - 1;
        do {
            while (j >= 0 && A[i][1] + B[j][1] > target) {
                j--;
            }
            while (i < A.length && A[i][1] + B[j][1] <= target) {
                i++;
            }
        } while (--i < A.length && j >= 0
                    && A[i][1] + B[j][1] < A[i+1][1] + B[j-1][1]
                    && A[i+1][1] + B[j-1][1] <= target);

        // Redefine target and loop through remaining array to find addition solutions to add to list
        target = A[i][1] + B[j][1];
        while (i < A.length && j >= 0) {
            int sum = A[i][1] + B[j][1];
            if (sum == target) {
                list.add(new int[] {A[i][0], B[j][0]});
            }
            if (sum < target) {
                i++;
            } else {
                j--;
            }
        }

        return list;
    }


    // Brute Force Method
    // - loop through both A and B (nested), sum up the values and compare to target
    // O(A*B) Time Complexity
    // O(A*B) Space Complexity
    public List<int[]> bruteForce(int[][] A, int[][] B, int target) {
        List<int[]> list = new ArrayList<>();
        int maxSum = 0;
        for (int[] a : A) {
            for (int[] b : B) {
                int sum = a[1] + b[1];
                if (sum <= target && sum > maxSum) {
                    list.clear();
                    list.add(new int[] {a[0], b[0]});
                    maxSum = sum;
                } else if (sum == maxSum) {
                    list.add(new int[] {a[0], b[0]});
                }
            }
        }
        return list;
    }

}