package com.ogabek.algorithms.arrays2D;

public class NearestExitFromEntranceInMaze {
    public static void main(String[] args) {
        char[][] maze1 = new char[][] {
                {'+','+','.','+'},
                {'.','.','.','+'},
                {'+','+','+','.'}};

        char[][] maze2 = new char[][] {{'.', '+', '.'}};

//        System.out.println(nearestExit(maze1, new int[] {1, 2})); // 1
        System.out.println(nearestExit(maze2, new int[] {0, 2})); // -1
    }

    static short[][] DIRS = new short[][] {
            {-1, 0},
            {0,  1},
            {1,  0},
            {0, -1}
    };

    public static int nearestExit(char[][] maze, int[] entrance) {
        int rows = maze.length;
        int cols = maze[0].length;
        int[][] curr = new int[rows][cols];

        for(int r = 0; r < rows; r++) {
            for(int c = 0; c < cols; c++) {
                char val = maze[r][c];
                if(val == '.') {
                    curr[r][c] = 0;
                } else {
                    curr[r][c] = -1;
                }
            }
        }

        while(true) {
            int moves = 0;
            for(int r = 0; r < rows; r++) {
                for(int c = 0; c < cols; c++) {
                    if(curr[r][c] == 0) {// 0, 2
                        if(r == entrance[0] && c == entrance[1]) continue;

                        boolean isExit = false;
                        for(short[] dir : DIRS) {
                            int newRow = r + dir[0]; // 0
                            int newCol = c + dir[1]; // 3

                            if(newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) { // check if out of bounds
                                if(newRow != entrance[0] || newCol != entrance[1]) { // check if not equal to the entrance
                                    isExit = true;
                                }
                                continue;
                            } else if (curr[newRow][newCol] == -1) {
                                continue;
                            }

                            int neighbourVal = curr[newRow][newCol];

                            if(neighbourVal > 0 || (newRow == entrance[0] && newCol == entrance[1])) {
                                curr[r][c] = neighbourVal + 1;
                                moves++;
                            }
                        }

                        if(isExit) return curr[r][c];
                    }
                }
            }

            if(moves == 0) break;
        }

        return -1;
    }
}
