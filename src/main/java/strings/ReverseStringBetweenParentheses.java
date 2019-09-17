package main.java.strings;

import java.util.Stack;

//    Reverse Substrings Between Each Pair of Parentheses
//        - https://leetcode.com/problems/reverse-substrings-between-each-pair-of-parentheses/
//
//    Given a string s that consists of lower case English letters and brackets.  Reverse the strings in each pair of
//    matching parentheses, starting from the innermost one.  Your result should not contain any bracket.
//
//    Example 1:
//    Input: s = "(abcd)"
//    Output: "dcba"
//
//    Example 2:
//    Input: s = "(u(love)i)"
//    Output: "iloveu"
//
//    Example 3:
//    Input: s = "(ed(et(oc))el)"
//    Output: "leetcode"
//
//    Example 4:
//    Input: s = "a(bcdefghijkl(mno)p)q"
//    Output: "apmnolkjihgfedcbq"

public class ReverseStringBetweenParentheses {

    public static void main(String[] args) {

        ReverseStringBetweenParentheses rs = new ReverseStringBetweenParentheses();

        System.out.println(rs.reverseParentheses("(abcd)"));                // dcba
        System.out.println(rs.reverseParentheses("(u(love)i)"));            // iloveu
        System.out.println(rs.reverseParentheses("(ed(et(oc))el)"));        // leetcode
        System.out.println(rs.reverseParentheses("a(bcdefghijkl(mno)p)q")); // apmnolkjihgfedcbq
        System.out.println(rs.reverseParentheses("ta(()us())w((((a))))"));  // tasuwa

    }

    // Recursive Method
    // - Stack opening parentheses and pop closing parentheses until reach a pair
    // - Recursive call the function for the substring between in case other parentheses within
    // - Store and reverse using StringBuilder, add prefix substring, and recursively call on and add suffix substring
    // O(n2) Time Complexity
    // O(n2) Space Complexity
    public String reverseParentheses(String s) {

        // Determine first opening parentheses and if none, return string
        int i = s.indexOf('(');
        if (i == -1) return s;

        // Find correct closing parentheses by pushing on '(' and popping ')' until stack is empty
        Stack<Integer> stack = new Stack<>();
        stack.push(i);
        int j = i;
        while (!stack.isEmpty()) {
            j++;
            if (s.charAt(j) == '(') {
                stack.push(j);
            }
            if (s.charAt(j) == ')') {
                stack.pop();
            }
        }

        // Using StringBuilder reverse the substring between parentheses, recursively calling on function in case other
        // sets of parenthesis within substring and add prefix substring
        StringBuilder sb = new StringBuilder();
        sb.append(reverseParentheses(s.substring(i+1,j)));
        sb = sb.reverse();
        sb.insert(0,s.substring(0,i));

        // check to see if ')' is not the last character in string to add the suffix substring to StringBuilder calling
        // on the function recursively in case suffix has addition sets of parenthesis
        if (j < s.length() - 1) {
            String end = reverseParentheses(s.substring(j+1));
            sb.append(end);
        }

        return sb.toString();
    }
}
