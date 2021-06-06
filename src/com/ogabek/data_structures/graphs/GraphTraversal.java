package com.ogabek.data_structures.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphTraversal {
    public static void main(String[] args) {
        int[][] graphAdjacencyList = new int[][] {
                {1,3}, // 0
                {0}, // 1
                {3, 8}, // 2
                {0, 2, 4, 5}, // 3
                {3, 6}, // 4
                {3}, // 5
                {4, 7}, // 6
                {6}, // 7
                {2} // 8
        };

        GraphTraversal graphTraversal = new GraphTraversal();
        System.out.println("BFS: " + Arrays.toString(graphTraversal.bfs(graphAdjacencyList)));
        System.out.println("DFS: " + Arrays.toString(graphTraversal.dfs(graphAdjacencyList)));
    }

    private int writeIndex = 0;
    public int[] bfs(int[][] adjacencyList) {
        int[] values = new int[adjacencyList.length];
        boolean[] visited = new boolean[adjacencyList.length];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        int writeIndex = 0;
        while (!queue.isEmpty()) {
            int value = queue.poll();

            visited[value] = true;
            values[writeIndex++] = value;

            for(int neighbour : adjacencyList[value]) {
                if(!visited[neighbour]) queue.add(neighbour);
            }
        }

        return values;
    }

    public int[] dfs(int[][] adjacencyList) {
        int[] values = new int[adjacencyList.length];
        boolean[] visited = new boolean[adjacencyList.length];
        dfsRecursive(adjacencyList, 0, visited, values);
        return values;
    }

    private void dfsRecursive(int[][] adjacencyList, int vertex, boolean[] visited, int[] values) {
        visited[vertex] = true;
        values[writeIndex++] = vertex;

        for(int neighbourVertex : adjacencyList[vertex]) {
            if(!visited[neighbourVertex]) dfsRecursive(adjacencyList, neighbourVertex, visited, values);
        }
    }
}
