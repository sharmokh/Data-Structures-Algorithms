package main.java.arrays;

// Needs Comments

import java.util.Arrays;

//    Pair with Max Appeal Sum
//
//    Find pair with maximum Appeal value.
//        Input: Array
//        Output: index {i, j} ( i = j allowed) with maximum Appeal
//        Appeal = A[i] +A[j] + abs(i-j)
//
//    Example 1:
//    Input: [1, 3, -1]
//    Output: [1, 1]
//    Explanation: Appeal = A[1] + A[1] + abs(0) = 3 + 3 + 0 = 6
//
//    Example 2:
//    Input: [1, 6, 1, 1, 1, 1, 7]
//    Output: [1, 6]
//    Explanation: 6 + 7 + abs(1 - 6) = 18
//
//    Example 3:
//    Input: [6, 2, 7, 4, 4, 1, 6]
//    Output: [0, 6]
//    Explanation: 6 + 6 + abs(0 - 6) = 18

public class PairWithMaxAppealSum {

    public static void main(String[] args) {
        int[] A = {1, 6, 1, 1, 1, 1, 7};
        System.out.println(Arrays.toString(maxAppealSum(A)));
    }

    public static int[] maxAppealSum(int[] A) {

        int i = 0, j = 0, m = Integer.MIN_VALUE, n = Integer.MIN_VALUE;

        for (int k = 0; k < A.length; k++) {
            int sum = A[k] + k;
            if (sum > m) {
                m = sum;
                i = k;
            }
            sum = A[k] - k;
            if (sum > n) {
                n = sum;
                j = k;
            }
        }

        if (i < j) {
            return new int[] {i, j};
        } else {
            return new int[] {j, i};
        }
    }

}
