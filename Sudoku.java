import java.util.Scanner;

class Sudoku {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//      Take inputs from user
//      Provide a 9x9 sudoku

        int[][] board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        int length = board.length;

        if (SudokuSolver(board, length)) {
            printBoard(board, length);
        } else {
            System.out.println("No Solution Exists");
        }
    }

    public static boolean SudokuSolver(int[][] grid, int n) {
        int row = -1;
        int col = -1;
        boolean isSolved = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    isSolved = false;   //for empty spaces
                    break;
                }
            }
            if (!isSolved) {
                break;
            }
        }

        if (isSolved) {
            return true;
        }

        // calling recursively for checking
        //other position if the current postion is invalid
        //by backtracking
        for (int k = 1; k <= n; k++) {
            if (checkSafe(grid, row, col, k)) {
                grid[row][col] = k;
                if (SudokuSolver(grid, n)) {
                    return true;
                } else {
                    grid[row][col] = 0;
                }
            }
        }
        return false; //if there is no solution for the position hence ending the call
    }

    public static boolean checkSafe(int[][] grid, int row, int col, int num) {
        // row check - returns false if existing number is present
        for (int i = 0; i < grid.length; i++) {
            if (grid[row][i] == num) return false;
        }

        // column check - returns false if existing number is present
        for (int[] ints : grid) {
            if (ints[col] == num) return false;
        }

        //3x3 grid check
        int root = (int) Math.sqrt(grid.length);
        int gridRow = row - row % root;
        int gridCol = col - col % root;

        for (int i = gridRow; i < gridRow + root; i++) {
            for (int j = gridCol; j < gridCol + root; j++) {
                if (grid[i][j] == num) {
                    return false;
                }
            }
        }

        // Returning safe if no numbers are repeated
        return true;
    }

    public static void printBoard(int[][] grid, int N) {

        for (int[] row : grid) {
            for (int i = 0; i < N; i++) {
                System.out.print(row[i] + " ");
            }
            System.out.println();
        }
    }

}