package com.ogabek.algorithms.graphs;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class NetworkDelayTime {
    public static void main(String[] args) {

    }

    /**
     * APPROACH 1: Dijkstra's algorithm -> fastest but cannot work with negative weights
     *
     * @param times edges with weights
     * @param n number of total nodes
     * @param k starting node
     * @return minimum time to reach all the nodes
     *
     * time: O(N + ElogE) => slightly slower than Dijkstra's shortest path, but works with negative weights
     * space: O(N)
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 1. create adj list with weights 0: [[1,1], [2,1]]
        LinkedList<Edge>[] adjList = new LinkedList[n+1];
        for(int i = 1; i <= n; i++) {
            adjList[i] = new LinkedList<>();
        }
        // fill the adj list
        for(int[] time : times) {
            adjList[time[0]].add(new Edge(time[1], time[2]));
        }

        // 2. create dist array with int max value to represent infinity
        int[] distances = new int[n+1];
        for(int i = 1; i <= n; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[k] = 0; // distance to starting node is 0

        // 3. init minHeap of Integer
        Queue<Integer> heap = new PriorityQueue<>((a, b) -> Integer.compare(distances[a], distances[b]));
        heap.add(k);

        // 4. BFS over nodes, minHeap root is pushed into queue
        while(!heap.isEmpty()) {
            int from = heap.poll();
            LinkedList<Edge> neighbors = adjList[from];

            for(Edge neighbor : neighbors) {
                int newDistance = distances[from] + neighbor.weight;
                if(newDistance < distances[neighbor.dest]) {
                    distances[neighbor.dest] = newDistance;
                    heap.add(neighbor.dest);
                }
            }

        }

        // 5. find max distance, early return if max value (infinity) is detected
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            int dist = distances[i];
            if(dist == Integer.MAX_VALUE) return -1;
            if(dist > max) max = dist;
        }

        return max;
    }

    public class Edge {
        Integer dest;
        Integer weight;

        public Edge(int dest, int weight) {
            this.dest =  dest;
            this.weight = weight;
        }
    }



    /**
     * APPROACH 2: Bellman-Ford algorithm -> can work with negative weights
     *
     * @param times edges with weights
     * @param n number of total nodes
     * @param k starting node
     * @return minimum time to reach all the nodes
     *
     * time: O(N*E) => slightly slower than Dijkstra's shortest path, but works with negative weights
     * space: O(N)
     */
    public int networkDelayTimeBellmanFord(int[][] times, int n, int k) {
        // 1. iterate over the edges n-1 times
        // 2. in each sub iteration, store shortest time to get to each node
        // 3. find the max time to reach a node
        // 4. if max is infinity, return -1 (impossible to reach all), else return max

        Integer[] distance = new Integer[n+1]; // +1 size to offset the 0 based index
        // for(int i = 1; i <= n; i++) distance[i] = Integer.MAX_VALUE;
        distance[k] = 0;

        for(int i = 1; i < n; i++) { // time: O(n)
            boolean changeDetected = false;
            for(int[] time : times) { // time: O(E) -> edges
                int from = time[0];
                int to = time[1];
                int weight = time[2];

                if(distance[from] != null && (distance[to] == null || distance[from] + weight < distance[to])) {
                    distance[to] = distance[from] + weight;
                    changeDetected = true;
                }
            }

            if(!changeDetected) break; // early break if no change detected
        }

        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++) {
            if(distance[i] == null) return -1;
            if( distance[i] > max) max = distance[i];
        }

        return max;
    }
}
