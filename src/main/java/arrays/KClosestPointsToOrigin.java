package main.java.arrays;

// Needs Comments

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//    K Closest Points to Origin
//        - https://leetcode.com/problems/k-closest-points-to-origin/
//
//    We have a list of points on the plane.  Find the K closest points to the origin (0, 0).  Here, the distance
//    between two points on a plane is the Euclidean distance.  You may return the answer in any order.  The answer is
//    guaranteed to be unique (except for the order that it is in.)
//
//    Example 1:
//    Input: points = [[1,3],[-2,2]], K = 1
//    Output: [[-2,2]]
//    Explanation:
//        The distance between (1, 3) and the origin is sqrt(10). The distance between (-2, 2) and the origin is
//        sqrt(8). Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin. We only want the closest K = 1 points from
//        the origin, so the answer is just [[-2,2]].
//
//    Example 2:
//    Input: points = [[3,3],[5,-1],[-2,4]], K = 2
//    Output: [[3,3],[-2,4]]
//            (The answer [[-2,4],[3,3]] would also be accepted.)

public class KClosestPointsToOrigin {

    public static void main(String[] args) {
        int[][] points = {{3,3}, {5,-1}, {-2,4}};
        int[][] results = streamMethod(points, 2);
        for (int[] result : results) {
            System.out.println(Arrays.toString(result));
        }
    }

    public static int[][] streamMethod(int[][] points, int K) {
        Arrays.sort(points, Comparator.comparing(p -> p[0] * p[0] + p[1] * p[1]));
        return Arrays.copyOfRange(points, 0, K);
    }

    public static int[][] distancesArrayMethod(int[][] points, int K) {
        int[] distances = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            distances[i] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
        }

        Arrays.sort(distances);
        int maxKDistance = distances[K - 1];
        int[][] results = new int[K][2];
        int i = 0;

        for (int[] point : points) {
            if (point[0] * point[0] + point[1] * point[1] <= maxKDistance) {
                results[i++] = point;
            }
        }

        return results;
    }

    public static int[][] priorityQueueMethod(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] A, int[] B) {
                return (A[0] * A[0] + A[1] * A[1]) - (B[0] * B[0] + B[1] * B[1]);
            }
        });
        for (int[] point : points) {
            pq.add(point);
        }
        int[][] results = new int[K][];
        while (K-- > 0) {
            results[K] = pq.remove();
        }
        return results;
    }
}
