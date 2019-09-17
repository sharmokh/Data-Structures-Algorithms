package main.java.dp;

//    Maximum of Absolute Value Expression
//        - https://leetcode.com/problems/maximum-of-absolute-value-expression/
//
//    Given two arrays of integers with equal lengths, return the maximum value of:
//        |A[i] - A[j]| + |B[i] - B[j]| + |i - j|
//    where the maximum is taken over all 0 <= i, j < arr1.length.
//
//    Example 1:
//    Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
//    Output: 13
//
//    Example 2:
//    Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
//    Output: 20

public class MaxAbsValExp {

    // Brute Force Naive Method
    // - two loops to manage i and j to plug in every combination
    // - compare the sum with the max
    // O(n2) time complexity
    // O(1) space complexity
    public int bruteForce(int[] A, int[] B) {

        int max = 0, n = A.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = Math.abs(A[i] - A[j]) + Math.abs(B[i] - B[j]) + Math.abs(i - j);
                max = Math.max(sum, max);
            }
        }

        return max;
    }

    // Memorization
    // - loop once through the array
    // - memorize the max value of |A[i]| + |B[i]| + |i|
    // - subtract |A[j]| + |B[j]| + |j| and compare to max
    // O(n) time complexity
    // O(1) space complexity
    public int memorization(int[] A, int[] B) {

        int max = Integer.MIN_VALUE;
        int maxpp = Integer.MIN_VALUE;
        int maxpn = Integer.MIN_VALUE;
        int maxnp = Integer.MIN_VALUE;
        int maxnn = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {
            maxpp = Math.max(maxpp, A[i] + B[i] - i);
            maxpn = Math.max(maxpn, A[i] - B[i] - i);
            maxnp = Math.max(maxnp, -A[i] + B[i] - i);
            maxnn = Math.max(maxnn, -A[i] - B[i] - i);
            max = Math.max(max, maxpp - A[i] - B[i] + i);
            max = Math.max(max, maxpn - A[i] + B[i] + i);
            max = Math.max(max, maxnp + A[i] - B[i] + i);
            max = Math.max(max, maxnn + A[i] + B[i] + i);
        }

        return max;
    }

}
