package main.java.graph;

// Needs Comments for Disjoint Set

import java.util.LinkedList;
import java.util.Queue;

//    Friend Circle
//        - https://leetcode.com/problems/friend-circles/
//
//    There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in
//    nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of
//    C. And we defined a friend circle is a group of students who are direct or indirect friends.  Given a N*N matrix M
//    representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students
//    are direct friends with each other, otherwise not. And you have to output the total number of friend circles among
//    all the students.
//
//    Example 1:
//    Input:
//       [[1,1,0],
//        [1,1,0],
//        [0,0,1]]
//    Output: 2
//    Explanation: The 0th and 1st students are direct friends, so they are in a friend circle.  The 2nd student himself
//                 is in a friend circle. So return 2.
//
//    Example 2:
//    Input:
//       [[1,1,0],
//        [1,1,1],
//        [0,1,1]]
//    Output: 1
//    Explanation:  The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, so the 0th
//                  and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.

public class FriendCircles {

    // Breadth First Search Approach
    // - loop through each person and increase the number of friend circles if not visited
    // - then visit each of his/her friends and friends' friends and mark as visited
    // O(n) Time Complexity
    // O(1) Space Complexity
    public int BFSCount(int[][] M) {


        Queue<Integer> queue = new LinkedList<>();
        int circles = 0;

        // loop through each person
        for (int i = 0; i < M.length; i++) {

            // 1 represents not visited, 2 represents visited
            // increase circles count if person has not been visited
            if (M[i][i] == 1) {

                circles++;
                queue.add(i);

                // breadth first search for friends within his/her circle
                while (!queue.isEmpty()) {
                    int k = queue.poll();
                    for (int j = 0; j < M[i].length; j++) {
                        // 1 represents part of the circle in M[k][j]
                        // 1 represents not visited in M[j][j]
                        if (M[k][j] == 1 && M[j][j] == 1) {
                            queue.add(j);
                            M[j][j] = 2;
                        }
                    }
                }
            }
        }

        return circles;
    }

    // Depth First Search Approach
    // - loop through each person and increase the number of friend circles if not visited
    // - then visit each of his/her friends and friends' friends and mark as visited
    // O(n) Time Complexity
    // O(1) Space Complexity
    public int DFSCount(int[][] M) {

        int circles = 0;

        // loop through each person
        for (int i = 0; i < M.length; i++) {

            // 1 represents not visited, 2 represents visited
            // increase circles count if person has not been visited
            // depth first search of each friend and friends' friends
            if (M[i][i] == 1) {
                circles++;
                DFS(M, i);
            }
        }

        return circles;
    }

    private void DFS(int[][] M, int f) {

        // loop through each person's friends
        for (int j = 0; j < M[f].length; j++) {

            // 1 represents part of the circle in M[k][j]
            // 1 represents not visited in M[j][j]
            if (M[f][j] == 1 && M[j][j] == 1) {
                M[j][j] = 2;
                DFS(M,j);
            }
        }

    }

    // Need to study more of!
    private class UnionFind {
        private int count = 0;
        private int[] parent, rank;

        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;
            }
            else {
                parent[rootQ] = rootP;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }
            count--;
        }

        public int count() {
            return count;
        }
    }

    public int unionFind(int[][] M) {
        int n = M.length;
        UnionFind circles = new UnionFind(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) circles.union(i, j);
            }
        }
        return circles.count();
    }

}
