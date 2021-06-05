package com.ogabek.data_structures.arrays;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class MatrixTraversal {
    public static void main(String[] args) {
        int[][] testMatrix = new int[][]{
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9,  10, 11, 12},
                {13, 14, 15, 16}};

        System.out.println("DFS: " + Arrays.toString(traverseMatrixDFS(testMatrix)));
        System.out.println("BFS: " + Arrays.toString(traverseMatrixBFS(testMatrix)));
    }
    static short[][] directions = new short[][]{
            {-1, 0}, // up
            {0, 1}, // right
            {1, 0}, // down
            {0, -1}}; // left
    static int writeIdx = 0;

    /**
     * time: O(n)
     * space: O(n)
     */
    public static int[] traverseMatrixDFS(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return new int[]{};

        int[] values = new int[matrix.length * matrix[0].length];
        writeIdx = 0;

        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        dfs(matrix, 0, 0, visited, values);

        return values;
    }

    private static void dfs(int[][] matrix, int row, int col, boolean[][] visited, int[] values) {
        if(row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length) return; // return if out of bounds
        if(visited[row][col]) return; // return if already visited

        values[writeIdx++] = matrix[row][col];
        visited[row][col] = true;

        for(short[] dir : directions) {
            dfs(matrix, row + dir[0], col + dir[1], visited, values);
        }
    }

    /**
     * time: O(n)
     * space: O(n)
     */
    public static int[] traverseMatrixBFS(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return new int[]{};

        int[] values = new int[matrix.length * matrix[0].length];
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        Queue<int[]> coordinatesQueue = new LinkedList<>();
        coordinatesQueue.add(new int[] {0,0});
        int writePos = 0;

        while (!coordinatesQueue.isEmpty()) {
            int[] curPos = coordinatesQueue.poll();
            int row = curPos[0];
            int col = curPos[1];

            // skip iteration if invalid move
            if(row < 0 || row >= matrix.length || // if row is out of bounds
               col < 0 || col >= matrix[0].length || // if column is out of bounds
               visited[row][col]) continue; // if already visited

            visited[row][col] = true;
            values[writePos++] = matrix[row][col];

            for(short[] dir : directions) {
                coordinatesQueue.add(new int[]{row + dir[0], col + dir[1]});
            }
        }

        return values;
    }

}
