package main.java.tree;

//    Given a list of unique integers nums, construct a BST from it (you need to insert nodes one-by-one with the given
//    order to get the BST) and find the distance between two nodes node1 and node2. Distance is the number of edges
//    between two nodes. If any of the given nodes does not appear in the BST, return -1.
//
//    Example:
//    Input: nums = [2, 1, 3], node1 = 1, node2 = 3
//    Output: 2
//    Explanation:
//          2
//         / \
//        1   3

public class DistanceBetweenNodesInBST {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(2);
        head.left = new TreeNode(1);
        head.right = new TreeNode(6);
        TreeNode current = head.right;
        current.left = new TreeNode(4);

        System.out.println(distanceBetweenNodes(head, new TreeNode(1), new TreeNode(4)));
    }

    // Recursive Method
    // - find lowest common ancestor of both nodes
    // - calculate and add the depth from the ancestor to each node
    // O(n) Time Complexity
    // O(n) Space Complexity
    public static int distanceBetweenNodes(TreeNode head, TreeNode p, TreeNode q) {
        TreeNode ancestor = lowestCommonAncestor(head, p, q);
        return nodeDepth(ancestor, p) + nodeDepth(ancestor, q);
    }

    // Calculates depth from ancestor to node, recursively
    private static int nodeDepth(TreeNode current, TreeNode child) {
        if (current.val == child.val) {
            return 0;
        }
        if (child.val > current.val) {
            return nodeDepth(current.right, child) + 1;
        } else {
            return nodeDepth(current.left, child) + 1;
        }
    }

    // Determines the lowest common ancestor of two nodes in a tree
    private static TreeNode lowestCommonAncestor(TreeNode head, TreeNode p, TreeNode q) {
        if (p.val > q.val) {
            TreeNode temp = p;
            p = q;
            q = temp;
        }
        if (p.val < head.val && q.val > head.val) {
            return head;
        }
        if (p.val > head.val) {
            return lowestCommonAncestor(head.right, p, q);
        } else {
            return lowestCommonAncestor(head.right, p, q);
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}
