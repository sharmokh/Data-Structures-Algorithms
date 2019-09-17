package main.java.arrays;

//    Max Min Path
//      - https://leetcode.com/problems/path-with-maximum-minimum-value
//
//    Given a matrix with r rows and c columns, find the maximum score of a path starting at [0, 0] and ending at
//    [r - 1, c - 1]. The score of a path is the minimum value in that path. For example, the score of the path
//    8 → 4 → 5 → 9 is 4.  You can only move either down or right at any point in time.
//
//    Example 1:
//
//    Input:
//       [[5, 1],
//        [4, 5]]
//    Output: 4
//    Explanation:
//        Possible paths:
//        5 → 1 → 5 => min value is 1
//        5 → 4 → 5 => min value is 4
//        Return the max value among minimum values => max(4, 1) = 4.

import java.util.PriorityQueue;

public class MaxMinPath {

    public static void main(String[] args) {
        int[][] grid = {{4, 2, 6, 8},
                        {5, 5, 4, 7},
                        {1, 6, 3, 4},
                        {3, 3, 5, 8}};
        MaxMinPath mmp = new MaxMinPath();
        System.out.println(mmp.maxMinPath(grid));
    }

    // DFS Method
    int max = 0;
    int[][] grid;
    // - track every path through the starting position to end position using a priority queue
    // - then check if the first element of the queue is greater than the current max
    // O(2^nlogn) Time Complexity
    // O(2^n) Space Complexity
    public int maxMinPath(int[][] grid) {
        this.grid = grid;
        dfs(new PriorityQueue<>(), 0, 0);
        return max;
    }

    private void dfs(PriorityQueue<Integer> pq, int i, int j) {
        pq.offer(grid[i][j]);
        // if at end of path, compare first element of priority queue to the current max
        if (i+1 == grid.length && j+1 == grid[0].length) {
            int min = pq.peek();
            max = Math.max(min, max);
        }
        // move down
        if (i+1 < grid.length) {
            dfs(pq, i+1, j);
        }
        // move left
        if (j+1 < grid[0].length) {
            dfs(pq, i, j+1);
        }
        pq.remove(grid[i][j]);
    }
}
