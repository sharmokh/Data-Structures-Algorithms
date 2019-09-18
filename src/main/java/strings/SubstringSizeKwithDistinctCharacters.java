package main.java.strings;

import java.util.*;

//    Substrings of size K with K distinct chars
//
//    Given a string s and an int k, return all unique substrings of s of size k with k distinct characters.
//
//    Example 1:
//    Input: s = "abcabc", k = 3
//    Output: ["abc", "bca", "cab"]
//
//    Example 2:
//    Input: s = "abacab", k = 3
//    Output: ["bac", "cab"]
//
//    Example 3:
//    Input: s = "awaglknagawunagwkwagl", k = 4
//    Output: ["wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag", "nagw", "agwk", "kwag"]
//    Explanation:  Substrings in order are: "wagl", "aglk", "glkn", "lkna", "knag", "gawu", "awun", "wuna", "unag",
//                  "nagw", "agwk", "kwag", "wagl", "wagl" is repeated twice, but is included in the output once.

public class SubstringSizeKwithDistinctCharacters {
    public static void main(String[] args) {
        System.out.println(hashMethod("abcabc", 3).toString());
        System.out.println(hashMethod("abacab", 3).toString());
        System.out.println(hashMethod("awaglknagawunagwkwagl", 4).toString());
    }

    public static List<String> hashMethod(String s, int K) {
        Map<Character, Integer> map = new HashMap<>();
        Set<String> distinctSubstrings = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            if (i+1 >= K) {
                if (map.size() == K) {
                    distinctSubstrings.add(s.substring(i-K+1, i+1));
                }
                char c = s.charAt(i-K+1);
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    map.remove(c);
                }
            }
        }
        return new ArrayList<>(distinctSubstrings);
    }
}
