import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");

        // Display menu and get user's choice
        System.out.println("Menu:");
        System.out.println("1. 1vs1");
        System.out.println("2. 1vsComputer");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            play1vs1();
        } else if (choice == 2) {
            play1vsComputer();
        } else {
            System.out.println("Invalid choice!");
        }
    }

    // Play 1 vs 1 game
    public static void play1vs1() {
        char[][] board = new char[3][3];
        initializeBoard(board);

        int player = 1;
        char mark;
        boolean gameFinished = false;
        int row, col;

        while (!gameFinished) {
            // Display the board
            displayBoard(board);

            // Get the player's mark
            mark = getPlayerMark(player);

            // Get the player's move
            System.out.println("Player " + player + "'s turn.");
            row = getMove("Enter row number (1-3): ");
            col = getMove("Enter column number (1-3): ");

            // Check if the move is valid
            while (!isValidMove(board, row, col)) {
                System.out.println("Invalid move! Try again.");
                row = getMove("Enter row number (1-3): ");
                col = getMove("Enter column number (1-3): ");
            }

            // Make the move
            makeMove(board, row, col, mark);

            // Check if the game is finished
            if (isGameOver(board)) {
                displayBoard(board);
                System.out.println("Game over! Player " + player + " wins!");
                gameFinished = true;
            } else if (isBoardFull(board)) {
                displayBoard(board);
                System.out.println("Game over! It's a tie!");
                gameFinished = true;
            }

            // Switch to the other player
            player = (player == 1) ? 2 : 1;
        }
    }

    // Play 1 vs computer game
    public static void play1vsComputer() {
        char[][] board = new char[3][3];
        initializeBoard(board);

        int player = 1;
        char playerMark = 'X';
        char computerMark = 'O';
        boolean gameFinished = false;
        int row, col;

        while (!gameFinished) {
            // Display the board
            displayBoard(board);

            // Get the player's move
            if (player == 1) {
                System.out.println("Your turn.");
                row = getMove("Enter row number (1-3): ");
                col = getMove("Enter column number (1-3): ");

                // Check if the move is valid
                while (!isValidMove(board, row, col)) {
                    System.out.println("Invalid move! Try again.");
                    row = getMove("Enter row number (1-3): ");
                    col = getMove("Enter column number (1-3): ");
                }

                // Make the move
                makeMove(board, row, col, playerMark);
            } else {
                System.out.println("Computer's turn.");

                    // Get the computer's move
    int[] computerMove = getComputerMove(board, computerMark);
    row = computerMove[0];
    col = computerMove[1];

    // Make the move
    System.out.println("Computer chooses row " + (row+1) + " and column " + (col+1) + ".");
    makeMove(board, row, col, computerMark);
}

            // Check if the game is finished
            if (isGameOver(board)) {
                displayBoard(board);
                if (player == 1) {
                    System.out.println("Game over! You win!");
                } else {
                    System.out.println("Game over! Computer wins!");
                }
                gameFinished = true;
            } else if (isBoardFull(board)) {
                displayBoard(board);
                System.out.println("Game over! It's a tie!");
                gameFinished = true;
            }

            // Switch to the other player
            player = (player == 1) ? 2 : 1;
        }
    }

    // Initialize the board with empty cells
    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    // Display the board
    public static void displayBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Get the player's mark (either 'X' or 'O')
    public static char getPlayerMark(int player) {
        return (player == 1) ? 'X' : 'O';
    }

    // Get a move from the player
    public static int getMove(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextInt() - 1;
    }

    // Check if a move is valid
    public static boolean isValidMove(char[][] board, int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        return board[row][col] == '-';
    }

    // Make a move
    public static void makeMove(char[][] board, int row, int col, char mark) {
        board[row][col] = mark;
    }

    // Check if the game is over
    public static boolean isGameOver(char[][] board) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }

                // Check diagonals
                if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                    return true;
                }
                if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                    return true;
                }
        
                return false;
            }
        
            // Check if the board is full
            public static boolean isBoardFull(char[][] board) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == '-') {
                            return false;
                        }
                    }
                }
                return true;
            }
        
            // Get a move from the computer
            public static int[] getComputerMove(char[][] board, char computerMark) {
                // Check for a winning move
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == '-') {
                            board[i][j] = computerMark;
                            if (isGameOver(board)) {
                                int[] move = {i, j};
                                board[i][j] = '-';
                                return move;
                            }
                            board[i][j] = '-';
                        }
                    }
                }
        
                // Check for a blocking move
                char playerMark = (computerMark == 'X') ? 'O' : 'X';
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == '-') {
                            board[i][j] = playerMark;
                            if (isGameOver(board)) {
                                int[] move = {i, j};
                                board[i][j] = '-';
                                return move;
                            }
                            board[i][j] = '-';
                        }
                    }
                }
        
                // Generate a random move
                Random random = new Random();
                int row = random.nextInt(3);
                int col = random.nextInt(3);
                while (!isValidMove(board, row, col)) {
                    row = random.nextInt(3);
                    col = random.nextInt(3);
                }
                int[] move = {row, col};
                return move;
            }
        }
        

