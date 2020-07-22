package main.java.arrays;

import java.util.*;

public class SnakeMoves {

    public static void main(String[] args) {
        SnakeMoves sm = new SnakeMoves();
        int[][] grid = {{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,1,0,1,1,0,0,1,0,0,0,0,1,0,0},
                        {0,1,0,0,0,0,1,0,0,1,0,0,0,0,0},
                        {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,1,1,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,1,0,1,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,1,0,1,0,0,1,0,0,0,1,0,0},
                        {0,0,0,0,1,0,0,0,0,0,0,0,0,1,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
                        {1,0,1,1,0,0,0,0,0,0,0,0,0,0,0},
                        {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
                        {1,0,0,0,0,0,1,0,0,0,1,0,0,0,1},
                        {0,0,1,0,1,0,0,0,0,0,0,0,0,0,0}};
        System.out.println(sm.minimumMoves(grid));
    }

    public int minimumMoves(int[][] grid) {
        int moves = 0, n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        int[] m = new int[] {0, 0, 0, 1};
        queue.offer(m); set.add(Arrays.toString(m));
        while (!queue.isEmpty()) {
            int size = queue.size();
            System.out.println(moves);
            while (size-- > 0) {
                int[] p = queue.poll();
                System.out.println(Arrays.toString(p));
                if (Arrays.equals(p, new int[] {n-1, n-2, n-1, n-1})) {
                    return moves;
                }
                if (p[3] + 1 < n && grid[p[0]][p[1]+1] != 1 && grid[p[2]][p[3]+1] != 1) {
                    m = new int[] {p[0], p[1]+1, p[2], p[3]+1};
                    if (!set.contains(Arrays.toString(m))) {
                        queue.offer(m);
                        set.add(Arrays.toString(m));
                    }
                }
                if (p[2] + 1 < n && grid[p[0]+1][p[1]] != 1 && grid[p[2]+1][p[3]] != 1) {
                    m = new int[] {p[0]+1, p[1], p[2]+1, p[3]};
                    if (!set.contains(Arrays.toString(m))) {
                        queue.offer(m);
                        set.add(Arrays.toString(m));
                    }
                }
                if (p[0] == p[2] && p[0] + 1 < n && grid[p[0]+1][p[1]] != 1) {
                    m = new int[] {p[0], p[1], p[0]+1, p[1]};
                    if (!set.contains(Arrays.toString(m))) {
                        queue.offer(m);
                        set.add(Arrays.toString(m));
                    }
                }
                if (p[1] == p[3] && p[1] + 1 < n && grid[p[0]][p[1]+1] != 1) {
                    m = new int[] {p[0], p[1], p[0], p[1]+1};
                    if (!set.contains(Arrays.toString(m))) {
                        queue.offer(m);
                        set.add(Arrays.toString(m));
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}
