package main.java.strings;

//    Substring with K Distinct Characters
//        Releated - https://leetcode.com/problems/subarrays-with-k-different-integers
//    Given a string s and an int k, return an int representing the number of substrings (not unique) of s with exactly
//    k distinct characters. If the given string doesn't have k distinct characters, return 0.
//
//    Example 1:
//    Input: s = "pqpqs", k = 2
//    Output: 7
//    Explanation: ["pq", "pqp", "pqpq", "qp", "qpq", "pq", "qs"]
//
//    Example 2:
//    Input: s = "aabab", k = 3
//    Output: 0

import java.util.HashMap;
import java.util.Map;

public class SubstringWithKDistinctChars {

    public static void main(String[] args) {
        System.out.println(slidingWindowMethod("pqpqs", 2));
        System.out.println(slidingWindowMethod("aabab", 2));
        System.out.println(slidingWindowMethod("aabab", 3));
    }

    // Sliding Window Method
    // - calculate all permutations with a maximum of K distinct characters subtracted by all permutations with a
    //   maximum of K - 1 distinct characters
    // O(n) Time Complexity
    // O(n) Space Complexity
    public static int slidingWindowMethod(String s, int K) {
        char[] A = s.toCharArray();
        return atMostK(A, K) - atMostK(A, K - 1);
    }

    // Determines at most K different distinct letters
    private static int atMostK(char[] A, int K) {

        int i = 0, count = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int j = 0; j < A.length; ++j) {

            // increase the value of the letter in the map by 1
            map.put(A[j], map.getOrDefault(A[j], 0) + 1);

            // if there are more than K letters in the map, shift i (window start)
            // remove letter from map if there aren't any left until the size equals K
            while (map.size() > K) {
                map.put(A[i], map.get(A[i]) - 1);
                if (map.get(A[i]) == 0) {
                    map.remove(A[i]);
                }
                i++;
            }

            // add number of permutations
            count += j - i + 1;
        }
        return count;
    }
}
