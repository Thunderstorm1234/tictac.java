import java.util.*;

class game {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        boolean gameRunning = true;

        while (gameRunning) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] and column [1-3]): ");
            int row, col;

            // Input handling
            Scanner scanner = new Scanner(System.in);
            while (true) {
                try {
                    row = scanner.nextInt() - 1;
                    col = scanner.nextInt() - 1;

                    if (isValidMove(row, col)) {
                        break;
                    } else {
                        System.out.println("Invalid move. Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Enter row and column (e.g., '1 2').");
                    scanner.nextLine(); // Consume invalid input
                }
            }

            makeMove(row, col);

            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameRunning = false;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                gameRunning = false;
            }

            // Switch player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    private static boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDiagonals() {
        return (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}