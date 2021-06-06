package com.ogabek.algorithms.arrays2D;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 286. Walls and Gates
 *
 * You are given an m x n grid rooms initialized with these three possible values.
 *
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 * Input: rooms = [[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 */
public class WallsAndGates {
    private static final int EMPTY = 2147483647;
    private static final short[][] directions = new short[][] {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    /**
     * time: O(2m*n) => O(m*n)
     * space: O(n)
     */
    public void wallsAndGatesBFS(int[][] rooms) {
        // 1. sequential iteration: start a DFS if it's a gate
        // 2. BFS: start the search from all the gates simultaneously:
        // BFS guarantees we always process the nearest level first, so the all the INF cells will
        // be filled with the smallest distance automatically

        if(rooms.length == 0 || rooms[0].length == 0) return;

        Queue<int[]> queue = new LinkedList<>();

        for(int row = 0; row < rooms.length; row++) {
            for(int col = 0; col < rooms[0].length; col++) {
                if(rooms[row][col] == 0) {
                    queue.add(new int[] {row, col});
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] currDir = queue.poll();
            int currValue = rooms[currDir[0]][currDir[1]];

            for(short[] dir : directions) {
                int newRow = currDir[0] + dir[0];
                int newCol = currDir[1] + dir[1];

                if(newRow < 0 || newRow >= rooms.length || newCol < 0 || newCol >= rooms[0].length) continue;
                if(rooms[newRow][newCol] == EMPTY) {
                    queue.add(new int[] {newRow, newCol});
                    rooms[newRow][newCol] = currValue + 1;
                }
            }
        }

    }

    /**
     * WARNING: "TIME LIMIT EXCEEDED" on LeetCode
     *
     * time: O(n)
     * space: O(n)
     */
    public void wallsAndGatesDFS(int[][] rooms) {
        // 1. sequential iteration: start a DFS if it's a gate
        // 2. DFS: replace all adjacent INFs with the minimum distance to from the gate

        for(int row = 0; row < rooms.length; row++) {
            for(int col = 0; col < rooms[0].length; col++) {
                if(rooms[row][col] == 0) {
                    dfs(rooms, row, col, 0);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int row, int col, int dist) {
        // 1. base case: return if the move is out of bounds
        if(row < 0 || row >= rooms.length || col < 0 || col >= rooms[0].length) return;
        if(dist > rooms[row][col]) return; // or return if the distance is greater

        // 2. replace if the current distance is smaller
        rooms[row][col] = dist;

        // 3. add the adjacent moves
        for(short[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            dfs(rooms, newRow, newCol, dist + 1);
        }
    }
}
