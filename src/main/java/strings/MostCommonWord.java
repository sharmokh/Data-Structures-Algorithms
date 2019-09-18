package main.java.strings;

import java.util.*;

//    Most Common Word
//        - https://leetcode.com/problems/most-common-word/
//
//    Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned
//    words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.  Words in
//    the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case
//    sensitive.  The answer is in lowercase.
//
//    Example:
//
//    Input:
//        paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
//        banned = ["hit"]
//    Output: "ball"
//    Explanation:
//        "hit" occurs 3 times, but it is a banned word.  "ball" occurs twice (and no other word does), so it is the
//        most frequent non-banned word in the paragraph.  Note that words in the paragraph are not case sensitive, that
//        punctuation is ignored (even if adjacent to words, such as "ball,"), and that "hit" isn't the answer even
//        though it occurs more because it is banned.

public class MostCommonWord {

    public static void main(String[] args) {
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        System.out.println(mapMethod(paragraph, banned));
        System.out.println(trieMethod(paragraph,banned));
    }

    //  Map Method
    //  - enter banned words in hashset, than iterate through each word in paragraph
    //  - if the word is not banned, enter and/or update the frequency of th word in the hashmap
    //  - if the count is greater than current max, set the current word as max
    //  O(m + n) Time Complexity
    //  O(m + n) Space Complexity
    public static String mapMethod(String paragraph, String[] banned){
        String[] words = paragraph.toLowerCase().split("\\W+");
        Map<String, Integer> map = new HashMap<>();
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        int maxFrequency = 0; String mostCommonWord = "";
        for (String word : words) {
            if (ban.contains(word)) continue;
            map.put(word, map.getOrDefault(word, 0) + 1);
            if (map.get(word) > maxFrequency) {
                mostCommonWord = word;
                maxFrequency = map.get(word);
            }
        }
        return mostCommonWord;
    }

    //  Trie Method
    //  - enter banned words into a trie with boolean label of false
    //  - for each word in paragraph, walk the trie, update the frequency and check if most frequent word
    //  O(m + n) Time Complexity
    //  O(m + n) Space Complexity
    public static String trieMethod(String paragraph, String[] banned) {

        Trie root = new Trie();
        Trie current = root;

        // insert banned words into Trie
        for (String ban : banned) {
            for (char c : ban.toCharArray()) {
                if (current.next[c-'a'] == null) {
                    current.next[c-'a'] = new Trie();
                }
                current = current.next[c-'a'];
            }
            current.ban = true;
            current = root;
        }

        String[] words = paragraph.toLowerCase().split("\\W+");
        String mostCommonWord = ""; int maxFrequency = 0;

        for (String word : words) {

            // Insert words into Trie
            for (char c : word.toCharArray()) {
                if (current.next[c-'a'] == null) {
                    current.next[c-'a'] = new Trie();
                }
                current = current.next[c-'a'];
            }

            // Update frequency of the word
            if (!current.ban) {
                current.count++;
                if (current.count > maxFrequency) {
                    mostCommonWord = word;
                    maxFrequency = current.count;
                }
            }

            current = root;
        }

        return mostCommonWord;
    }

    private static class Trie {
        private Trie[] next = new Trie[26];    // sub nodes
        private int count;                     // word frequency
        private boolean ban;                   // banned?
    }
}
