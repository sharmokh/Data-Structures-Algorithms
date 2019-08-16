package main.java.tree;

import java.util.LinkedList;
import java.util.Queue;

//    https://leetcode.com/problems/check-completeness-of-a-binary-tree/
//
//    Given a binary tree, determine if it is a complete binary tree.
//
//        Definition of a complete binary tree from Wikipedia:
//        In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the
//        last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
//
//
//        Example 1:
//
//        Input: [1,2,3,4,5,6]
//        Output: true
//        Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
//
//
//        Example 2:
//
//        Input: [1,2,3,4,5,null,7]
//        Output: false
//        Explanation: The node with value 7 isn't as far left as possible.

public class CheckCompletenessOfBinaryTree {

    // Level Order Traversal
    // - Using a queue and loop, dequeue current node and enqueue its children until reaching a null Node
    // - Then dequeue remaining items in the queue and if any are not null return false, else return true
    // O(n) Time Complexity
    // O(log n) Space Complexity
    public boolean levelOrderTraversal(TreeNode root) {

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        // loop through and dequeue current node and enqueue its children until reaching a null value
        while (q.peek() != null) {
            TreeNode current = q.poll();
            q.offer(current.left);
            q.offer(current.right);
        }

        // loop until queue is empty, checking remainder of nodes are null
        while (!q.isEmpty()) {
            TreeNode current = q.poll();
            if (current != null) {
                return false;
            }
        }

        return true;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
