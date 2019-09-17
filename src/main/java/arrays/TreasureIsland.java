package main.java.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//    Treasure Island I
//
//    You have a map that marks the location of a treasure island. Some of the map area has jagged rocks and dangerous
//    reefs. Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure
//    out a shortest route to the treasure island.
//
//    Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from the
//    top-left corner of the map and can move one block up, down, left or right at a time. The treasure island is marked
//    as X in a block of the matrix. X will not be at the top-left corner. Any block with dangerous rocks or reefs will
//    be marked as D. You must not enter dangerous blocks. You cannot leave the map area. Other areas O are safe to sail
//    in. The top-left corner is always safe. Output the minimum number of steps to get to the treasure.
//
//    Example:
//    Input:
//        [['O', 'O', 'O', 'O'],
//        ['D', 'O', 'D', 'O'],
//        ['O', 'O', 'O', 'O'],
//        ['X', 'D', 'D', 'O']]
//    Output: 5
//    Explanation: Route is (0, 0), (0, 1), (1, 1), (2, 1), (2, 0), (3, 0) The minimum route takes 5 steps.
//
//
//    Treasure Island II
//
//    You have a map that marks the locations of treasure islands. Some of the map area has jagged rocks and dangerous
//    reefs. Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure
//    out a shortest route to one of the treasure islands.
//
//    Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from one of
//    the starting point (marked as S) of the map and can move one block up, down, left or right at a time. The treasure
//    island is marked as X. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous
//    blocks. You cannot leave the map area. Other areas O are safe to sail in. Output the minimum number of steps to
//    get to any of the treasure islands.
//
//    Example:
//    Input:
//        [['S', 'O', 'O', 'S', 'S'],
//         ['D', 'O', 'D', 'O', 'D'],
//         ['O', 'O', 'O', 'O', 'X'],
//         ['X', 'D', 'D', 'O', 'O'],
//         ['X', 'D', 'D', 'D', 'O']]
//    Output: 3
//    Explanation: You can start from (0,0), (0, 3) or (0, 4). The treasure locations are (2, 4) (3, 0) and (4, 0).
//                 Here the shortest route is (0, 3), (1, 3), (2, 3), (2, 4).

public class TreasureIsland {

    public static void main(String[] args) {
        TreasureIsland ti = new TreasureIsland();

        char[][] singleStartMap = {{'O', 'O', 'O', 'O'},
                                   {'D', 'O', 'D', 'O'},
                                   {'O', 'O', 'O', 'O'},
                                   {'X', 'D', 'D', 'O'}};
        System.out.println(ti.oneStart(singleStartMap));

        char[][] multipleStartMap = {{'S', 'O', 'O', 'S', 'S'},
                                     {'D', 'O', 'D', 'O', 'D'},
                                     {'O', 'O', 'O', 'O', 'X'},
                                     {'X', 'D', 'D', 'O', 'O'},
                                     {'X', 'D', 'D', 'D', 'O'}};
        System.out.println(ti.multipleStarts(multipleStartMap));
    }

    // Multiple Start Locations
    // - loop through each element and add each start location to list of possible starts
    // - do breadth first search to determine distance of closest treasure to start position
    // O(r*c) Time Complexity
    // O(r*c) Space Complexity depending on number of starts
    public int multipleStarts(char[][] map){
        List<int[]> starts = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'S') {
                    starts.add(new int[] {i, j});
                }
            }
        }
        return bfsSearch(map, starts);
    }

    // One Start Location
    // - create a list with start position at (0, 0)
    // O(r*c) Time Complexity
    // O(r*c) Space Complexity
    public int oneStart(char[][] map) {
        List<int[]> starts = new ArrayList<>();
        starts.add(new int[] {0,0});
        return bfsSearch(map, starts);
    }

    // Utility BFS Function
    // - add starts to queue and loop while queue is not empty
    // - loop through every element currently in queue and determine if it one of three cases
    // - 'X' treasure found and return current level
    // - 'D' is a dead end
    // - 'O' mark position with 'D' and queue in all four directions (if possible)
    private int bfsSearch(char[][] map, List<int[]> starts) {

        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        Queue<int[]> q = new LinkedList<>(starts);
        int level = -1;

        while (!q.isEmpty()) {

            // increase depth level to reach treasure
            // the size of the queue determines all elements within that level
            level++;
            int size = q.size();

            // loop through and remove each element part of the same level
            while (size-- > 0) {
                int[] c = q.poll();

                // switch statement checking if position at map is 'X', 'D', or 'O'
                switch (map[c[0]][c[1]]) {
                    case 'X': return level;
                    case 'D': break;
                    default:
                        map[c[0]][c[1]] = 'D';

                        // add to queue each value up, down, left and/or right if within map and not 'D'
                        for (int[] d : directions) {
                            int i = c[0] + d[0];
                            int j = c[1] + d[1];
                            if (i >= 0 && i < map.length && j >= 0 && j < map[0].length && map[i][j] != 'D') {
                                q.offer(new int[] {i, j});
                            }
                        }
                }
            }
        }

        // if no treasure is not found, return -1
        return -1;
    }


}
