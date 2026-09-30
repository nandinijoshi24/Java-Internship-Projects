import java.util.Scanner;

public class ConnectFour
{
    static final int ROWS = 6;
    static final int COLS = 7;
    static final char EMPTY = ' ';
    static final char PLAYER1 = 'X';
    static final char PLAYER2 = 'O';

    static char[][] board = new char[ROWS][COLS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean player1Turn = true;

        initializeBoard();
        System.out.println("🎮 Welcome to Connect Four!\n");

        while (true) {
            printBoard();
            char currentPlayer = player1Turn ? PLAYER1 : PLAYER2;
            System.out.print("Player " + currentPlayer + ", enter your move (1-7): ");
            int col;

            try {
                col = scanner.nextInt() - 1;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                scanner.next(); 
                continue;
            }

            if (isValidMove(col)) {
                makeMove(col, currentPlayer);

                if (checkWin(currentPlayer)) {
                    printBoard();
                    System.out.println("🏆 Player " + currentPlayer + " wins!");
                    break;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("It's a tie!");
                    break;
                }

                player1Turn = !player1Turn;
            } else {
                System.out.println("Invalid move. Column is full or out of range.");
            }
        }

        scanner.close();
    }

    static void initializeBoard() {
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j < COLS; j++)
                board[i][j] = EMPTY;
    }

    static void printBoard() {
        System.out.println();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-----------------------------");
        System.out.println("| 1 | 2 | 3 | 4 | 5 | 6 | 7 |");
        System.out.println("-----------------------------\n");
    }

    static boolean isValidMove(int col) {
        return col >= 0 && col < COLS && board[0][col] == EMPTY;
    }

    static void makeMove(int col, char player) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == EMPTY) {
                board[i][col] = player;
                break;
            }
        }
    }

    static boolean isBoardFull() {
        for (int i = 0; i < COLS; i++) {
            if (board[0][i] == EMPTY)
                return false;
        }
        return true;
    }

    static boolean checkWin(char player) {
        
        for (int i = 0; i < ROWS; i++)
            for (int j = 0; j <= COLS - 4; j++)
                if (board[i][j] == player && board[i][j + 1] == player &&
                    board[i][j + 2] == player && board[i][j + 3] == player)
                    return true;

        for (int i = 0; i <= ROWS - 4; i++)
            for (int j = 0; j < COLS; j++)
                if (board[i][j] == player && board[i + 1][j] == player &&
                    board[i + 2][j] == player && board[i + 3][j] == player)
                    return true;

        for (int i = 3; i < ROWS; i++)
            for (int j = 0; j <= COLS - 4; j++)
                if (board[i][j] == player && board[i - 1][j + 1] == player &&
                    board[i - 2][j + 2] == player && board[i - 3][j + 3] == player)
                    return true;

        for (int i = 0; i <= ROWS - 4; i++)
            for (int j = 0; j <= COLS - 4; j++)
                if (board[i][j] == player && board[i + 1][j + 1] == player &&
                    board[i + 2][j + 2] == player && board[i + 3][j + 3] == player)
                    return true;

        return false;
    }
}

