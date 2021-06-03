package com.ogabek.data_structures.trees;

public class MatrixTraversal {
    static short[][] directions = new short[][]{
            {-1, 0}, // up
            {0, 1}, // right
            {1, 0}, // down
            {0, -1}}; // left
    static int writeIdx = 0;

    // 10:45
    public static int[] traverseMatrixDFS(int[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return new int[]{};

        int[] values = new int[matrix.length * matrix[0].length];

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

}
