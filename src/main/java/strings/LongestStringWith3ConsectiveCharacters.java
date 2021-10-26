package main.java.strings;

//    Longest String with 3 Consective Characters
//
//    Given A, B, C, find any string of maximum length that can be created such that no 3 consecutive characters are
//    same. There can be at max A 'a', B 'b' and C 'c'.
//
//    Example 1:
//    Input: A = 1, B = 1, C = 6
//    Output: "ccbccacc"
//
//    Example 2:
//    Input: A = 1, B = 2, C = 3
//    Output: "acbcbc"

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LongestStringWith3ConsectiveCharacters {

    public static void main (String[] args) {
        int[] chars = new int[26];
        chars[0] = 1; chars[1] = 1; chars[2] = 6;
        System.out.println(priorityQueueMethod(chars));
    }

    public static String priorityQueueMethod(int[] chars){

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] A, int[] B) {
                return B[1] - A[1];
            }
        });

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 0) {
                pq.add(new int[]{i, chars[i]});
            }
        }

        StringBuilder sb = new StringBuilder();
        int[] hold = {pq.peek()[0], pq.peek()[1], 0};
        while (!pq.isEmpty()) {
            int[] letter = pq.remove();
            if (hold[0] != letter[0]) {
                if (hold[1] > 0) {
                    pq.add(new int[] {hold[0], hold[1]});
                }
                hold = new int[] {letter[0], letter[1], 0};
            } else if (hold[2] >= 2) {
                continue;
            }
            sb.append((char)(letter[0]+'a'));
            letter[1]--; hold[1]--; hold[2]++;
            if (letter[1] > 0) {
                pq.add(letter);
            }
        }
        return sb.toString();
    }
}
