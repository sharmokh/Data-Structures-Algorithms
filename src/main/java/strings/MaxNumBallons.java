package main.java.strings;

import java.util.Arrays;

public class MaxNumBallons {
    public static void main(String[] args) {
        MaxNumBallons mnb = new MaxNumBallons();
        System.out.println(mnb.maxNumberOfBalloons("leetcode"));
    }

    public int maxNumberOfBalloons(String text) {
        int[] letters = new int[26];
        for (char c : text.toCharArray()) {
            letters[c-'a']++;
        }
        System.out.println(Arrays.toString(letters));
        String target = "balloon";
        int max = 0;
        while (true) {
            for (char c : target.toCharArray()) {
                if (letters[c-'a'] == 0) {
                    return max;
                }
                letters[c-'a']--;
            }
            System.out.println(Arrays.toString(letters));
            max++;
        }
    }
}
