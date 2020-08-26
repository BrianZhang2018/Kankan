package systemdesign;

import java.util.Arrays;
import java.util.Scanner;
/**
 * coding1: connect 4那个游戏，实现两个部分. 第一部分是改成只输入列. 第二部分是加一个机器人对战
 *
 * https://cs.nyu.edu/courses/fall16/CSCI-UA.0101-009/notes/Lecture12.pdf
 * Created by brianzhang on 8/23/20.
 */
public class Connect4 {
    static char[][] board = new char[6][7];  // game board
    static char player = 'X'; // start with player X

    public static void main(String[] args) {
        fillBoard(board, 'o');
        Scanner input = new Scanner(System.in);
        while (true) {
            // Show the state of the board
            showGameBoard(board);
            // Ask player for move
            System.out.print("Player " + player + ", please enter the column where you'd like to drop your piece: ");
            int col = input.nextInt();
            if (tryDropPiece(board, col, player)) {
                if (checkForWin()) { // Check for winner
                    System.out.println("Player " + player + " wins!");
                    showGameBoard(board);
                    return; // End game
                }
                player = switchPlayer(player); // Switch players
            }
        }
    }

    public static boolean tryDropPiece(char[][] board, int col, char player) {
        boolean result = false;
        // Check if the column is full
        if (board[0][col] != 'o') {
            System.out.println("That column is already full.");
            return false;
        }
        // Drop the piece as far as it will go. Find the first empty space, starting from the bottom
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][col] == 'o') {
                board[row][col] = player;
                return true;
            }
        }
        return result;
    }

    public static boolean checkForWin() {
        // Check for win horizontally
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length - 3; col++) {
                if (board[row][col] != 'o' && board[row][col] == board[row][col + 1] &&
                        board[row][col] == board[row][col + 2] && board[row][col] == board[row][col + 3]) {
                    return true;
                }
            }
        }

        // Check for win vertically
        for (int col = 0; col < board[0].length; col++) {
            for (int row = 0; row < board.length - 3; row++) {
                if (board[row][col] != 'o' && board[row][col] == board[row + 1][col] &&
                        board[row][col] == board[row + 2][col] && board[row][col] == board[row + 3][col]) {
                    return true;
                }
            }
        }

        // Check for win diagonally, from top left
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 0; col < board[row].length - 3; col++) {
                if (board[row][col] != 'o' && board[row][col] == board[row + 1][col + 1]
                        && board[row][col] == board[row + 2][col + 2] && board[row][col] == board[row + 3][col + 3]) {
                    return true;
                }
            }
        }

        // Check for win diagonally, from top right
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 3; col < board[row].length; col++) {
                if (board[row][col] != 'o' && board[row][col] == board[row + 1][col - 1]
                        && board[row][col] == board[row + 2][col - 2] && board[row][col] == board[row + 3][col - 3]) {
                    return true;
                }
            }
        }

        // Check for win diagonally, from top right
        for (int row = 0; row < board.length - 3; row++) {
            for (int col = 3; col < board[row].length; col++) {
                if (board[row][col] != 'o' && board[row][col] == board[row + 1][col - 1]
                        && board[row][col] == board[row + 2][col - 2] && board[row][col] == board[row + 3][col - 3]) {
                    return true;
                }
            }
        }

        return false;
    }

    public static char[][] fillBoard(char[][] board, char myChar) {
        for (int row = 0; row < board.length; row++) {
            Arrays.fill(board[row], 0, board[row].length, myChar);
        }
        return board;
    }

    public static void showGameBoard(char[][] board) {
        System.out.println();
        for (int row = 0; row < board.length; row++) {
            System.out.print("|");
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(" " + board[row][col] + " |");
            }
            System.out.println();
        }
    }

    public static char switchPlayer(char currentPlayer) {
        if (currentPlayer == 'X') {
            return 'Y';
        } else {
            return 'X';
        }
    }
}
