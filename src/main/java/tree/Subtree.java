package main.java.tree;

import java.util.LinkedList;
import java.util.Queue;

//    Subtree of Another Tree
//        - https://leetcode.com/problems/subtree-of-another-tree/
//
//    Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with
//    a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s
//    could also be considered as a subtree of itself.
//
//    Example 1:
//    Given tree s:
//            3
//           / \
//          4   5
//         / \
//        1   2
//    Given tree t:
//          4
//         / \
//        1   2
//    Return true, because t has the same structure and node values with a subtree of s.
//
//    Example 2:
//    Given tree s:
//
//              3
//             / \
//            4   5
//           / \
//          1   2
//         /
//        0
//    Given tree t:
//          4
//         / \
//        1   2
//    Return false.

public class Subtree {

    // Recursive Method
    // - true: if s and t values equal (or both are null)
    //         and using checkSub, both left and right subtrees
    // - false: if values are not equals (or only one is null)
    //          recursively call on left and right subtree to check if match
    // O(m*n) Time Complexity
    // O(m) Space Complexity
    public boolean recursiveMethod(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val == t.val) {
            return (checkSub(s.left, t.left) && checkSub(s.right, t.right)) || recursiveMethod(s.left, t) || recursiveMethod(s.right, t);
        }
        return recursiveMethod(s.left, t) || recursiveMethod(s.right, t);
    }

    // Utility Function for Recursive Method
    // - true: if s and t values equal (or both are null)
    //         and if both left subtree are equal and if both right subtree are equal
    // - false: if s and t values are not equal (or only one is null)
    private boolean checkSub(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        return s.val == t.val && checkSub(s.left, t.left) && checkSub(s.right, t.right);
    }

    // Queue Method
    // - queue left and right nodes and compare values as they come out of the queue
    // O(m*n) Time Complexity
    // O(m+n) Space Complexity
    public boolean queueMethod(TreeNode s, TreeNode t) {

        Queue<TreeNode> q = new LinkedList<>();
        q.add(s);

        // level order traversal using queue
        // if current value equals t value, use utility to check remainder of subtree
        while (!q.isEmpty()) {
            TreeNode current = q.remove();
            if (current.val == t.val && checkQueue(current, t)) {
                return true;
            }
            if (current.left != null) q.add(current.left);
            if (current.right != null) q.add(current.right);
        }

        return false;
    }

    // Utility Function for Queue Method
    // - add and remove two values at a time and check if both values equal, then add their children
    // - false: if values don't equal or only one tree has either a left or right child
    // - true: if false is not triggered, all node values have been checked
    private boolean checkQueue(TreeNode c, TreeNode t) {

        Queue<TreeNode> q = new LinkedList<>();
        q.add(c); q.add(t);

        while (!q.isEmpty()) {

            c = q.remove();
            t = q.remove();
            if (c.val != t.val) return false;

            // if both c and t have a left node, add to queue else if only one has a value return false
            if (c.left != null && t.left != null) {
                q.add(c.left);
                q.add(t.left);
            } else if (c.left != null ^ t.left != null) {
                return false;
            }

            // if both c and t have a right node, add to queue else if only one has a value return false
            if (c.right != null && t.right != null) {
                q.add(c.right);
                q.add(t.right);
            } else if (c.right != null ^ t.right != null) {
                return false;
            }
        }

        return true;
    }

    // String Method
    // - convert each tree to string and check if t is an index of s
    // O(m*n) Time Complexity
    // O(m) Space Complexity
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String sTree = convertToString(s);
        String tTree = convertToString(t);
        return sTree.indexOf(tTree) != -1;
    }

    // Utility Function for String Method
    // - recursive function to concatenate a string using preorder traversal
    // - append current value with symbol, then append left subtree or LN for null and right subtree or RN for null
    private String convertToString(TreeNode current) {

        // append symbol and value to string
        StringBuilder sb = new StringBuilder();
        sb.append("#"+current.val);

        // if left or right is not null append recursive call of the subtree, otherwise LN or RN if null
        if (current.left != null) {
            sb.append(convertToString(current.left));
        } else {
            sb.append("LN");
        }
        if (current.right != null) {
            sb.append(convertToString(current.right));
        } else {
            sb.append("RN");
        }

        return sb.toString();
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
