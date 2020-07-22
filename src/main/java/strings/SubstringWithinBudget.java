package main.java.strings;

import main.java.tree.SubtreeWithMaxAverage;

public class SubstringWithinBudget {

    public static void main(String[] args) {
        SubstringWithinBudget swb = new SubstringWithinBudget();
        System.out.println(swb.equalSubstring("abcd", "acde", 0));
    }

    public int equalSubstring(String s, String t, int maxCost) {
        // sliding window
        int total = 0, i = 0, j = 0, max = 0;
        while (i < s.length() && j < s.length()) {
            int cost = Math.abs(s.charAt(j) - t.charAt(j));
            if (total + cost <= maxCost) {
                total += cost;
                j++;
            } else {
                total -= Math.abs(s.charAt(i) - t.charAt(i));
                i++;
            }
            max = Math.max(max, j-i);
        }
        return max;
    }
}
