package main.java.arrays;

//    Reorder Data in Log Files
//      - https://leetcode.com/problems/reorder-data-in-log-files/
//
//    You have an array of logs.  Each log is a space delimited string of words.  For each log, the first word in each
//    log is an alphanumeric identifier.  Then, either:
//        - Each word after the identifier will consist only of lowercase letters, or;
//        - Each word after the identifier will consist only of digits.
//
//    We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least
//    one word after its identifier. Reorder the logs so that all of the letter-logs come before any digit-log.  The
//    letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The
//    digit-logs should be put in their original order. Return the final order of the logs.
//
//    Example 1:
//    Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]
//    Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReorderData {

    public static void main(String[] args) {

        ReorderData rd = new ReorderData();
        String[] logs = {"dig2 8 1 5 1","let4 art can","let3 art can","dig1 3 6","let2 own kit dig","let1 art zero"};

        System.out.println(Arrays.toString(rd.reorderLogFilesLoop(logs)));
        System.out.println(Arrays.toString(rd.reorderLogFilesStream(logs)));

    }

    // Loop Method
    // - Looping through each string, check if it is letter- or digit-log
    // - Digit: simply add to end of list
    // - Letter: locate its proper position and insert it
    // O(n2) Time Complexity
    // O(n) Space Complexity
    public String[] reorderLogFilesLoop(String[] logs) {

        List<String> list = new ArrayList<>();

        for (String log : logs) {

            // Check last character if it is a digit or letter
            if (Character.isDigit(log.charAt(log.length()-1))) {
                list.add(log);
            } else {

                // Determine location by comparing current log to each log in list
                // Break first if log in list is a digit-log, then by comparing lexicographical order using portion
                // after identifier, but if both substrings are equal, by identifier
                int i = -1;
                while (++i < list.size()) {
                    String[] current = log.split(" ", 2);
                    String[] compare = list.get(i).split(" ", 2);
                    if (Character.isDigit(compare[1].charAt(0))) {
                        break;
                    } else if (current[1].compareTo(compare[1]) < 0) {
                        break;
                    } else if (current[1].equals(compare[1]) && current[0].compareTo(compare[0]) < 0) {
                        break;
                    }
                }

                // insert log in proper location
                list.add(i, log);
            }
        }

        return list.toArray(new String[logs.length]);
    }

    // Arrays Sort Algorithm and Comparator Stream
    public String[] reorderLogFilesStream(String[] logs) {

        Arrays.sort(logs, (log1, log2) -> {

            // split log into two parts
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);

            // determine if digit or alpha log
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

            // if both are letter-logs, compare and order lexicographically portion after identifier
            // if both portions are equal, compare and order by identifiers
            if (!isDigit1 && !isDigit2) {
                int compare = split1[1].compareTo(split2[1]);
                return compare != 0 ? compare : split1[0].compareTo(split2[0]);
            }

            // if one of the two are letter-log, place in front otherwise leave in current location
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });

        return logs;
    }
}
