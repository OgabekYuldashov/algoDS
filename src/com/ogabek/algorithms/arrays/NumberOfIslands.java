package com.ogabek.algorithms.arrays;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public static void main(String[] args) {
        NumberOfIslands numberOfIslands = new NumberOfIslands();

        int count = numberOfIslands.numIslands(new char[][] {
                {'1','0','1','1','1'},
                {'1','0','1','0','1'},
                {'1','1','1','0','1'}
        });
        System.out.println(count);
        System.out.println(Short.MAX_VALUE);
    }

    short[][] directions = new short[][] {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    /**
     * time: O(2m*n) => O(m*n)
     * space: O(n)
     */
    public int numIslands(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;

        int count = 0;

        for(int row = 0; row < grid.length; row++) {
            for(int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] == '1') {
                    bfs(grid, row, col);
                    // dfs(grid, row, col);
                    count++;
                }
            }
        }

        return count;
    }

    private void bfs(char[][] grid, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {row, col});

        while(!queue.isEmpty()) {
            int[] dir = queue.poll();
            grid[dir[0]][dir[1]] = '0';

            for(short[] newDir : directions) {
                row = dir[0] + newDir[0]; // new row
                col = dir[1] + newDir[1]; // new col

                if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length)
                    continue;

                if(grid[row][col] == '1') {
                    queue.add(new int[] {row, col}); // add the valid coordinate
                    grid[row][col] = '0';
                }
            }
        }
    }

    private void dfs(char[][] grid, int row, int col) {
        grid[row][col] = '0';

        for(short[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if(newRow < 0 || newRow >= grid.length || newCol < 0 || newCol >= grid[0].length || grid[newRow][newCol] == '0')
                continue;

            dfs(grid, newRow, newCol);
        }
    }
}
