package main.java.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//    Critical Connections in a Network
//        - https://leetcode.com/problems/critical-connections-in-a-network/
//
//    There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network
//    where connections[i] = [a, b] represents a connection between servers a and b.  Any server can reach any other
//    server directly or indirectly through the network.  A critical connection is a connection that, if removed, will
//    make some server unable to reach some other server.  Return all critical connections in the network in any order.
//
//    Example 1:
//    Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
//    Output: [[1,3]]
//    Explanation: [[3,1]] is also accepted.

public class CriticalConnections {

    public static void main(String[] args) {

        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(0,1));
        connections.add(Arrays.asList(1,2));
        connections.add(Arrays.asList(2,0));
        connections.add(Arrays.asList(1,3));

        CriticalConnections cc = new CriticalConnections();
        List<List<Integer>> bridges = cc.tarjanAlgorithm(4,connections);

        for (List<Integer> bridge : bridges) {
            System.out.println(bridge.toString());
        }
    }

    List<Integer>[] graph;
    int id = 0; // labels each vertex id
    int[] ids;  // stores id of each vertex
    int[] low;  // stores lowest reachable vertex

    // Tarjan Bridge Algorithm
    // - Utilizing DFS (producing DFS Tree/Forest) to find strongly connected components and labeling the head of each
    //   subtree
    // O(|v| + |e|) Time Complexity
    // O(v) Space Complexity
    public List<List<Integer>> tarjanAlgorithm(int n, List<List<Integer>> connections) {

        graph = new ArrayList[n];
        buildGraph(connections);
        ids = new int[n];
        low = new int[n];

        // Bridges stores all critical connections in the graph
        List<List<Integer>> bridges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (ids[i] == 0) {
                dfs(i, -1, bridges);
            }
        }

        // Uncomment to view ids of each vertex and lowest reachable vertex
        // System.out.println(Arrays.toString(ids));
        // System.out.println(Arrays.toString(low));

        return bridges;
    }

    // Builds graph from list of connections
    // - Loops through all connections and builds a graph by storing u in v's array list and vise versa.
    private void buildGraph(List<List<Integer>> connections) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> connection : connections) {
            graph[connection.get(0)].add(connection.get(1));
            graph[connection.get(1)].add(connection.get(0));
        }
    }

    // DFS search using Tarjan's Algorithm to label vertex ids and lowest reachable vertex
    private void dfs(int at, int parent, List<List<Integer>> bridges) {

        // Initial labelling of vertex and lowest reachable vertex
        ids[at] = low[at] = ++id;

        for (int to : graph[at]) {
            // Checks to see if ID has not been visited, else if visited not itself
            if (ids[to] == 0) {

                dfs(to, at, bridges);
                // lowest reachable vertex is either current (to) lowest reachable vertex
                // OR connections (at) lowest reachable vertex
                low[at] = Math.min(low[at], low[to]);

                // If current (to) lowest reachable vertex is larger than connections (at) vertex id, the connection is
                // a bridge.  For example, there is a connection between V2 and V4 but the lowest reachable vertex for
                // V4 is itself (V4 > V2), then the connection between V2 and V4 is a bridge (or critical connection).
                if (low[to] > ids[at]) {
                    bridges.add(Arrays.asList(at, to));
                }

            } else if (to != parent) {

                // if visited already, lowest reachable is either current (to) vertex id
                // OR connections (at) lowest reachable vertex
                low[at] = Math.min(low[at], ids[to]);

            }
        }
    }
}
