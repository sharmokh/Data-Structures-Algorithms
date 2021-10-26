package main.java.tree;

import java.util.ArrayList;
import java.util.List;

//    Subtree with Max Average
//
//    Given an N-ary tree, find the subtree with the maximum average. Return the root of the subtree.  A subtree of a
//    tree is the node which have at least 1 child plus all its descendants. The average value of a subtree is the sum
//    of its values, divided by the number of nodes.
//
//    Example 1:
//
//    Input:
//               20
//             /     \
//            12      18
//         /  |  \    / \
//        11  2   3  15  8
//
//    Output: 18
//    Explanation:
//        There are 3 nodes which have children in this tree:
//        12 => (11 + 2 + 3 + 12) / 4 = 7
//        18 => (18 + 15 + 8) / 3 = 13.67
//        20 => (12 + 11 + 2 + 3 + 18 + 15 + 8 + 20) / 8 = 11.125
//        18 has the maximum average so output 18.

public class SubtreeWithMaxAverage {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(20);
        TreeNode left = new TreeNode(12);
        left.nodes.add(new TreeNode(11));
        left.nodes.add(new TreeNode(2));
        left.nodes.add(new TreeNode(3));
        TreeNode right = new TreeNode(18);
        right.nodes.add(new TreeNode(15));
        right.nodes.add(new TreeNode(8));
        head.nodes.add(left);
        head.nodes.add(right);

        SubtreeWithMaxAverage maxAverage = new SubtreeWithMaxAverage();
        maxAverage.maxAverageSubtree(head);
        System.out.println(maxAverage.max.val);
    }

    public TreeNode max = new TreeNode(-1);

    public void maxAverageSubtree(TreeNode current) {
        for (TreeNode child : current.nodes) {
            maxAverageSubtree(child);
            current.sum += child.val;
            current.count += child.count;
        }
        if (current.count > 1 &&  (double) current.sum / current.count > (double) max.sum / max.count) {
            max = current;
        }
    }

    public static class TreeNode {
        int val;
        int sum;
        int count;
        List<TreeNode> nodes;
        TreeNode(int val) {
            this.val = val;
            count = 1;
            sum = val;
            nodes = new ArrayList<>();
        }
    }
}
