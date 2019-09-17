package main.java.lists;

import java.util.HashMap;
import java.util.Map;

//    Copy List with Random Pointers
//    - https://leetcode.com/problems/copy-list-with-random-pointer/
//
//    A linked list is given such that each node contains an additional random pointer which could point to any node in
//    the list or null.  Return a deep copy of the list.
//
//    Example:
//    Input:
//        {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
//    Explanation:
//        Node 1's value is 1, both of its next and random pointer points to Node 2.
//        Node 2's value is 2, its next pointer points to null and its random pointer points to itself.

public class CopyListWithRandomPointers {

    // Map Method
    // - originalMap: hash reference to the original node as key with its index as value
    // - copyMap: hash the index as key and reference to the copy node as the value
    // - match the next and random references of the copy linked list to the original
    // O(n) Time Complexity
    // O(n) Space Complexity
    public Node copyRandomList(Node head) {

        if (head == null) return null;

        // Maps to track location of Node
        // - original maps node to an index location
        // - copy maps index to a copied node
        Map<Node, Integer> originalMap = new HashMap<>();
        Map<Integer, Node> copyMap = new HashMap<>();

        Node current = head;
        Node copy = null;
        int i = 1;

        while (current != null) {

            // Create new Node copy
            copy = new Node(current.val, null, null);

            // Map original Node and Copy
            originalMap.put(current, i);
            copyMap.put(i++, copy);

            current = current.next;
        }

        // Connect next and random by using originalMap to find the index position and copyMap to find the copy Node
        current = head;
        copy = copyMap.get(1);
        while (current != null) {
            copy.next = copyMap.get(originalMap.get(current.next));
            copy.random = copyMap.get(originalMap.get(current.random));
            copy = copy.next;
            current = current.next;
        }

        // Return copy head is stored in position 1 of hash map
        return copyMap.get(1);
    }

    // Node Class
    // - contains value, next pointer and random pointer to another node (possibly itself)
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
}
