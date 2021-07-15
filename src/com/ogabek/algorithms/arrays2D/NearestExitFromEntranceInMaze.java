package com.ogabek.algorithms.arrays2D;

import java.util.LinkedList;
import java.util.Queue;

public class NearestExitFromEntranceInMaze {
    public static void main(String[] args) {
        char[][] maze1 = new char[][] {
                {'+','+','.','+'},
                {'.','.','.','+'},
                {'+','+','+','.'}};

        char[][] maze2 = new char[][] {{'.', '+', '.'}};

        System.out.println(nearestExit(maze1, new int[] {1, 2})); // 1
        System.out.println(nearestExit(maze2, new int[] {0, 2})); // -1
    }

    public static int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        maze[entrance[0]][entrance[1]] = 'v'; // mark as visited

        short[][] DIRS = new short[][] {
                {-1, 0},
                {0,  1},
                {1,  0},
                {0, -1}
        };

        int steps = 1;
        int size = queue.size();
        while(!queue.isEmpty()) {
            if(size == 0) {
                steps++;
                size = queue.size();
            }

            int[] cell = queue.poll();
            size--;

            for(short[] dir : DIRS) {
                int nRow = cell[0] + dir[0];
                int nCol = cell[1] + dir[1];

                if(nRow >= 0 && nRow < rows && nCol >= 0 && nCol < cols && maze[nRow][nCol] == '.') { // within bounds and valid cell
                    maze[nRow][nCol] = 'v'; // mark as visited

                    if(nRow == 0 || nRow == rows - 1 || nCol == 0 || nCol == cols - 1) { // if the cell is an exit
                        return steps;
                    }

                    queue.offer(new int[] {nRow, nCol});
                }
            }
        }

        return -1;
    }
}
