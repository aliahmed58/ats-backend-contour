package com.training.ats.notmine;

public class Test {

    /*
     * Diagonal movement over a 2d array in (/) manner code is from:
     * https://stackoverflow.com/questions/20420065/loop-diagonally-through-two-dimensional-array
     * A bit changed to cater for backward slash movement over a 2d array
     */

    /**
     * return true if there is a win in diagonal backslash
     * @param board 2d array of the board
     * @param piece RED = 1 / BLU = 2 / EMPTY = 0
     * @param length valid win length
     * @return true if won, else false
     */
    public static boolean winInDiagonalBackslash(int[][] board, int piece, int length) {
        // loop over the matrix backwards diagonally (\)
        int height = board.length;
        int width = board[0].length;
        for (int i = 0; i < width + height - 1; i++) {
            int counter = 0;
            for (int col = Math.min(i, width - 1); col >= 0; col--) {
                int k = i - col;
                if (k < height && col < width) {
                    if (board[height - k - 1][col] == piece) {
                        counter ++;
                    }
                    if (counter >= length) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * check for a win in diagonal forward slash (/)
     * @param board 2d array of the board
     * @param piece RED = 1 / BLU = 2 / EMPTY = 0
     * @param length valid win length
     * @return true if won, else false
     */
    public static boolean winInDiagonalForwardSlash(int[][] board, int piece, int length) {
        // get height and width of the board from 2d array
        int height = board.length;
        int width = board[0].length;

        // loop over board diagonally in forward slash manner
        for (int i = 0; i <= width + height - 2; i++) {
            // keeping count of the required pieces found in forward slash
            int count = 0;
            // loop over the diagonal
            for (int col = 0; col <= i; col++) {
                // find the row index
                int row = i - col;
                // check for out of bounds of array
                if (row < height && col < width) {
                    // if the board piece is equal to the piece we are looking a win for
                    if (board[row][col] == piece) {
                        // increment counter
                        count++;
                    }
                    // if the count of required pieces in diagonal is equal to or greater than length for win
                    // return true
                    if (count >= length) {
                        return true;
                    }
                    // else carry on...
                }
            }
        }
        // return false if no win is found
        return false;
    }

    public static void main(String[] args) {
        int[][] board = {
                {1, 1, 0, 1},
                {2, 2, 1, 1},
                {1, 1, 2, 1},
        };

        System.out.println(winInDiagonalBackslash(board, 0, 2)); // false
        System.out.println(winInDiagonalBackslash(board, 1, 3)); // true
        System.out.println(winInDiagonalForwardSlash(board, 2, 2)); // false
        System.out.println(winInDiagonalForwardSlash(board, 1, 2)); // true
    }
}
