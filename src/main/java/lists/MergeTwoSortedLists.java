package main.java.lists;

//    Merge Two Sorted Lists
//    - https://leetcode.com/problems/merge-two-sorted-lists/
//
//    Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the
//    nodes of the first two lists.
//
//    Example:
//    Input: 1->2->4, 1->3->4
//    Output: 1->1->2->3->4->4

public class MergeTwoSortedLists {

    // Recursive Method
    // - recursive call terminates if either l1, l2 or both are null
    // - set current node to lesser of two nodes and its next to the recursive function
    // O(m+n) Time Complexity
    // O(m+n) Space Complexity - no extra space used
    public ListNode recursiveMethod(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // determine the lesser of the two nodes
        ListNode current = null;
        if (l1.val < l2.val) {
            current = l1;
            l1 = l1.next;
        } else {
            current = l2;
            l2 = l2.next;
        }

        // next node will be determine by recursive call
        current.next = recursiveMethod(l1, l2);
        return current;
    }

    // Loop Method
    // - create new head and set the current to head
    // - loop while either l1 or l2 has more nodes, if either is null set current.next to the other and break
    // - if both are not null, compare the values of both and add the smaller of the two to current
    // O(m+n) Time Complexity
    // O(m+n) Space Complexity
    public ListNode loopMethod(ListNode l1, ListNode l2) {

        // create head and current nodes
        ListNode head = new ListNode(-1);
        ListNode current = head;

        // loop and add the list of one if the other is empty or the smaller of the two nodes
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                current.next = l2;
                break;
            } else if (l2 == null) {
                current.next = l1;
                break;
            } else if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            // move to added node
            current = current.next;
        }

        // return head.next because head node is not part of either list
        return head.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
}
