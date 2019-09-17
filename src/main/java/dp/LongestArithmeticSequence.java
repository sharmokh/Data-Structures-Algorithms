package main.java.dp;

import java.util.HashMap;
import java.util.Map;

//    Longest Arithmetic Sequence
//        - https://leetcode.com/problems/longest-arithmetic-sequence/
//
//    Given an array A of integers, return the length of the longest arithmetic subsequence in A. Recall that a
//    subsequence of A is a list A[i_1], A[i_2], ..., A[i_k] with 0 <= i_1 < i_2 < ... < i_k <= A.length - 1, and that a
//    sequence B is arithmetic if B[i+1] - B[i] are all the same value (for 0 <= i < B.length - 1).
//
//    Example 1:
//    Input: [3,6,9,12]
//    Output: 4
//    Explanation:  The whole array is an arithmetic sequence with steps of length = 3.
//
//    Example 2:
//    Input: [9,4,7,2,10]
//    Output: 3
//    Explanation:  The longest arithmetic subsequence is [4,7,10].
//
//    Example 3:
//    Input: [20,1,15,3,10,5,8]
//    Output: 4
//    Explanation:  The longest arithmetic subsequence is [20,15,10,5].

public class LongestArithmeticSequence {

    // Brute Force Method
    // - loop through each value and find the difference with every other value
    // - then check the rest of the array for other values with the same difference
    // O(n3) Time Complexity
    // O(1) Space Complexity
    public int bruteForce(int[] A) {

        int longest = 2;

        // nested loop to find values between each element in the array
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = i + 1; j < A.length; j++) {

                int d = A[j] - A[i];

                // while loop to check if there are additional elements part of the sequence
                int count = 2, l = j, r = j + 1;
                while (r < A.length) {
                    if (A[r] - A[l] == d) {
                        count++;
                        longest = Math.max(longest, count);
                        l = r;
                    }
                    r++;
                }
            }
        }

        return longest;
    }


    // Dynamic Programming with HashMap
    // - for each value in the array, set up a HashMap of the difference as the key and sequence count as value
    // - to determine sequence count, refer to previous row and add 1 to existing sequence or default to 2 (1 + 1)
    // O(n2) Time Complexity
    // O(n2) Space Complexity
    public int dpHashMap(int[] A) {

        int longest = 2;
        Map<Integer, Integer>[] map = new HashMap[A.length];

        // nested loop to find values between each element in the array
        for( int i = 0; i < A.length; i++) {
            map[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                int d = A[i] - A[j];

                // check to see if difference appeared before and add 1 to its count or default to 2 (1 + 1)
                map[i].put(d, map[j].getOrDefault(d, 1) + 1);
                longest = Math.max(longest, map[i].get(d));
            }
        }

        return longest;
    }

    // Dynamic Programming with Array
    // - find the max difference by traversing array to find the min and max and subtracting them
    // - set up 2D array using position in array as the row and the difference as the column
    // - to determine sequence count, refer to previous row and add 1 to existing sequence or default to 2 (1 + 1)
    // O(n2) Time Complexity
    // O(nm) Space Complexity (m = 2 * maxDifference + 1)
    public int dpArray(int[] A) {

        // find the max difference but finding the min and max values in the array
        int min = A[0], max = A[0];
        for (int a : A) {
            min = Math.min(a, min);
            max = Math.max(a, max);
        }
        int maxDifference = max - min, longest = 2;

        // create a matrix to store count based on location and possible difference
        int[][] dp = new int[A.length][2 * maxDifference + 1];

        // nested loop to find values between each element in the array
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < i; j++) {

                // maxDifference is added to difference to keep difference positive to serve as index
                int d = A[i] - A[j] + maxDifference;

                // check to see if difference appeared before and add 1 to its count or default to 2 (1 + 1)
                dp[i][d] = Math.max(2, dp[j][d] + 1);
                longest = Math.max(longest, dp[i][d]);
            }
        }

        return longest;
    }
}
