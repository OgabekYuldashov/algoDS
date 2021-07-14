package com.ogabek.algorithms.recursion;

import java.util.HashSet;
import java.util.Set;

public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        SudokuSolver sudokuSolver = new SudokuSolver();

        sudokuSolver.solveSudoku(board);
        sudokuSolver.print();
    }

    // 1. initialize 3 HashSet[] to keep track of existing values in row, col and box
    Set<Character>[] rows = new HashSet[9];
    Set<Character>[] cols = new HashSet[9];
    Set<Character>[] boxes = new HashSet[9];
    char[][] board;

    /**
     * Time complexity: O(1). Because the board size is fixed and there is no N-parameter to measure.
     * Though let's discuss the number of operations needed : (9!)^9.  Let's consider one row, i.e. not more
     * than 99 cells to fill. There are not more than 99 possibilities for the first number to put, not more
     * than 9×8 for the second one, not more than 9×8×7 for the third one etc. In total that results in not
     * more than 9! possibilities for just one row, which means not more than (9!)^9 operations in total.
     *
     * Space complexity: O(1). the board size is fixed, and the space is used to store board, rows, columns
     * and boxes structures, each contains 81 elements.
     */
    public void solveSudoku(char[][] board) {
        this.board = board;

        // initialize the Sets
        for(int i = 0; i < 9; i++) {
            rows[i] = new HashSet<Character>();
            cols[i] = new HashSet<Character>();
            boxes[i] = new HashSet<Character>();
        }

        // 2. go over the board and insert the existing values
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[0].length; c++) {
                char val = board[r][c];
                if(val != '.') {
                    rows[r].add(val);
                    cols[c].add(val);
                    boxes[getBoxId(r, c)].add(val);
                }
            }
        }

        // 3. solveBacktrack()
        solveBacktrack(0, 0);
    }

    private int getBoxId(int row, int col) {
        int rowCount = (int) Math.floor(row / 3) * 3;
        int colCount = (int) Math.floor(col / 3);
        return rowCount + colCount;
    }

    private boolean isValid(int row, int col, char val) {
        // check if number exists horizontally, vertically or it exists in the sub-box
        if(rows[row].contains(val) || cols[col].contains(val) || boxes[getBoxId(row, col)].contains(val)) {
            return false;
        }

        return true;
    }


    public boolean solveBacktrack(int row, int col) {
        if(row == 9 || col == 9) { // check if we have stepped outside the board
            return true; // we were able to solve the board
        }

        char current = board[row][col];
        if (current == '.') {
            for(int i = 1; i <= 9; i++) {
                // add the number to the current cell
                char val = (char) (i + '0');
                board[row][col] = val;

                // decide if it's valid
                if(isValid(row, col, val)) {
                    rows[row].add(val);
                    cols[col].add(val);
                    boxes[getBoxId(row, col)].add(val);

                    if(col == 8) {
                        if(solveBacktrack(row + 1, 0)) {
                            return true;
                        }
                    } else {
                        if(solveBacktrack(row, col + 1)) {
                            return true;
                        }
                    }

                    rows[row].remove(val);
                    cols[col].remove(val);
                    boxes[getBoxId(row, col)].remove(val);
                }

                // remove number if it's not valid
                board[row][col] = '.';
            }
        } else {
            if(col == 8) {
                if(solveBacktrack(row + 1, 0)) {
                    return true;
                }
            } else {
                if(solveBacktrack(row, col + 1)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void print() {
        for(int r = 0; r < 9; r++) {
            System.out.println();
            if(r % 3 == 0 && r != 0) System.out.println("  -------------------------------");
            for(int c = 0; c < 9; c++) {
                String val = "  ";
                if(c % 3 == 0 && c != 0) val = "  |  ";
                System.out.print(val + board[r][c]);
            }
        }
    }
}
