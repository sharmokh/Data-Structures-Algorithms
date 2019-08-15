package main.java.lists;

//    https://leetcode.com/problems/intersection-of-two-linked-lists/
//
//    Write a program to find the node at which the intersection of two singly linked lists begins.
//
//        Example 1:
//
//        Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
//        Output: Reference of the node with value = 8
//        Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists
//        intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There
//        are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
//
//
//        Example 2:
//
//        Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
//        Output: Reference of the node with value = 2
//        Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists
//        intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are
//        3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
//
//
//        Example 3:
//
//        Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
//        Output: null
//        Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the
//        two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
//        Explanation: The two lists do not intersect, so return null.


import java.util.HashSet;
import java.util.Set;

public class IntersectionOfTwoLinkedLists {

    class Node {
        int val;
        Node next;
        Node(int x) {
            this.val = x;
            this.next = null;
        }
    }

    // Brute Force Naive Method
    // - nested while loop and checking one node at a time of List A with all of the nodes of List B
    // O(mn) Time Complexity
    // O(1) Space Complexity
    public Node bruteForce(Node headA, Node headB) {

        // List A is the outer loop
        while (headA != null) {
            Node current = headB;

            // List B is the inner loop
            while (current != null) {

                // Check if Node references equal
                if (headA == current) {
                    return current;
                }

                current = current.next;
            }

            headA = headA.next;
        }

        // return null if lists do not intersect
        return null;
    }


    // HashSet Method
    // - hash all Nodes in List A into a HashSet
    // - check and return the first Node that matches in List B
    // O(m + n) Time Complexity
    // O(m) Space Complexity
    public Node hashSetMethod(Node headA, Node headB) {

        // Hash each Node in List A
        Set<Node> nextA = new HashSet<>();
        Node current = headA;
        while (current != null) {
            nextA.add(current);
            current = current.next;
        }

        // Loop through each Node in List B
        current = headB;
        while (current != null) {

            // Check if Node is in HashSet
            if (nextA.contains(current)) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    // Two Pointer Method
    // - Find the length of each list
    // - Even out the length by traversing the longer list because the list cannot intersect without matching lengths
    // - Traverse both lists simultaneously and stop when Nodes equal
    // O(m + n) Time Complexity
    // O(1) Space Complexity
    public Node twoPointerMethod(Node headA, Node headB) {

        int lengthA = listLength(headA), lengthB = listLength(headB);

        while (lengthA > lengthB) {
            headA = headA.next;
            lengthA--;
        }

        while (lengthB > lengthA) {
            headB = headB.next;
            lengthB--;
        }

        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    private int listLength(Node current) {
        int count = 0;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
}
