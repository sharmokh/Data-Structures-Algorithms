package main.java.strings;

// Needs Comments

//    Longest Palindromic Substring
//        - https://leetcode.com/problems/longest-palindromic-substring/
//
//    Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
//
//    Example 1:
//    Input: "babad"
//    Output: "bab"
//    Note: "aba" is also a valid answer.
//
//    Example 2:
//    Input: "cbbd"
//    Output: "bb"

public class LongestPalindromicSubstring {

    int start;
    int length;

    public String longestPalindrome(String s) {

        start = 0;
        length = 0;

        for (int i = 0; i < s.length() - length/2; i++) {
            checkPali(s, i, i);
            checkPali(s, i, i+1);
        }

        return s.substring(start, start + length);
    }

    private void checkPali(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--; r++;
        }
        if (r-(++l) > length) {
            start = l;
            length = r-l;
        }
    }
}
