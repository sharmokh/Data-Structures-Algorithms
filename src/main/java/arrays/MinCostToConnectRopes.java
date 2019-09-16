package main.java.arrays;

import java.util.PriorityQueue;

//    Min Cost to Connect Ropes
//    - https://leetcode.com/problems/minimum-cost-to-connect-sticks (premium)
//
//    Given n ropes of different lengths, we need to connect these ropes into one rope. We can connect only 2 ropes at
//    a time. The cost required to connect 2 ropes is equal to sum of their lengths. The length of this connected rope
//    is also equal to the sum of their lengths. This process is repeated until n ropes are connected into a single
//    rope. Find the min possible cost required to connect all ropes.
//
//    Example 1:
//    Input: ropes = [8, 4, 6, 12]
//    Output: 58
//    Explanation: The optimal way to connect ropes is as follows
//        1. Connect the ropes of length 4 and 6 (cost is 10). Ropes after connecting: [8, 10, 12]
//        2. Connect the ropes of length 8 and 10 (cost is 18). Ropes after connecting: [18, 12]
//        3. Connect the ropes of length 18 and 12 (cost is 30).
//    Total cost to connect the ropes is 10 + 18 + 30 = 58
//
//    Example 2:
//    Input: ropes = [20, 4, 8, 2]
//    Output: 54
//
//    Example 3:
//    Input: ropes = [1, 2, 5, 10, 35, 89]
//    Output: 224
//
//    Example 4:
//    Input: ropes = [2, 2, 3, 3]
//    Output: 20

public class MinCostToConnectRopes {

    public static void main(String[] args) {
        MinCostToConnectRopes minCost = new MinCostToConnectRopes();
        int[] ropes = {1, 2, 5, 10, 35, 89};
        System.out.println(minCost.priorityQueueMethod(ropes));
    }

    // Priority Queue Method
    // - enter all rope sizes into priority queue
    // - remove two elements at a time and add cost to the total
    // - insert new rope size into priority queue
    // O(nlogn) Time Complexity
    // O(n) Space Complexity
    public int priorityQueueMethod(int[] ropes) {

        // Enter all rope sizes into priority queue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int rope : ropes) {
            pq.offer(rope);
        }

        // Loop until priority queue has one remaining element (final rope size)
        // Remove smallest two rope, connect them and add cost to total cost, insert new rope back into priority queue
        int totalCost = 0;
        while (pq.size() > 1) {
            int cost = pq.poll() + pq.poll();
            totalCost += cost;
            pq.offer(cost);
        }

        return totalCost;
    }
}
