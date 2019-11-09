public class SudokuSolver {
    static char[] possibleNumbers = {'1','2','3','4','5','6','7','8','9'};

    public static boolean solve(char[][] board, int row, int col) {
        /*
        so the base case would be in two
        1) if the column "overflows" then update the column to be zero and increment row
        2) if row is also overflowed then we would return true (indicating that we have solved the entire board)
         */

        if (col >= board.length || row >= board.length) {
            if (row >= board.length) {
                return true;
            }
            return solve(board, row + 1, 0);
        }

        // If the current row is a '.' then you should try to solve it
        if (board[row][col] == '.') {
            // Go through every possible number and see if any of them can work out
            for (int i = 0; i < possibleNumbers.length; i++) {
                // get a possible number
                char c = possibleNumbers[i];
                if (isPossibleChar(board, c, row, col)) {
                    board[row][col] = c;
                    if (solve(board, row, col + 1)) {
                        return true;
                    }
                    board[row][col] = '.';
                }
            }
            return false;
        } else {
            // Otherwise you should be calling the next one
            return solve(board, row, col + 1);
        }
    }

    public static boolean isPossibleChar(char[][] board, char c, int row, int col) {
        /*
        We have three conditions to meet
        1) Whether the number is repeated in the row
        2) Whether the number is repeated in the column
        3) Whether the number is repeated in the mini square
         */

        // First let's check whether the row is good
        for (int j = 0; j < board.length; j++) {
            if (j != col && board[row][j] == c) {
                return false;
            }
        }

        // First let's check whether the column is good
        for (int i = 0; i < board.length; i++) {
            if (i != row && board[i][col] == c) {
                return false;
            }
        }

        // This is what would be hard and it is to find out whether a number is unique in its sub graph
        // First step is to figure out which "row and column is the index a part of"
        // next would be to find out the start index of that sub graph
        // and finally you would have to loop through the sub graph and check whether the value is present there

        // given the row number we would have to find out which mini square row is it in
        /*
            0 -> 0
            1 -> 0
            2 -> 0
            3 -> 1
            4 -> 1
            5 -> 1
            6 -> 2
            7 -> 2
            8 -> 2

            so all we have to do is just divide the number by 3
         */

        int miniSquareRow = row / 3;
        int miniSquareCol = col / 3;

        /*
        Now that we have found the miniSquareRow and col
        let's find the location of it's start so we can loop around that

        so
        (0,0) -> (0,0)
        (0,1) -> (0,3)
        (0,2) -> (0,6)
        (1,0) -> (3,0)
        (1,1) -> (3,3)
        (1,2) -> (3,6)
        (2,0) -> (6,0)
        .
        .
        .

        We can deduce a pattern from this. Which is that
        startRow = 3 * miniSquareRow
        startCol = 3 * miniSquareCol
         */

        int startRow = 3 * miniSquareRow;
        int startCol = 3 * miniSquareCol;

        /*
        Now we know where we will be looping from
         */

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j  = startCol; j < startCol + 3; j++) {
                // We need to make sure that the element we are looking at is not the one passed in
                // so that would mean
                /*
                    0 1 2
                    3 4 5
                    6 7 8

                    lets say row and col are (2,2) -> 8
                    so basically if i == row and j == row, you would have to skip
                 */
                if (!(i == row && j == col) && board[i][j] == c) {
                    return false;
                }
            }
        }

        return true;
    }

    static void print(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        char[][] board = {
                {'8','.','.','.','.','.','.','.','.'},
                {'.','.','3','6','.','.','.','.','.'},
                {'.','7','.','.','9','.','2','.','.'},
                {'.','5','.','.','.','7','.','.','.'},
                {'.','.','.','.','4','5','7','.','.'},
                {'.','.','.','1','.','.','.','3','.'},
                {'.','.','1','.','.','.','.','6','8'},
                {'.','.','8','5','.','.','.','1','.'},
                {'.','9','.','.','.','.','4','.','.'},
        };

        solve(board, 0,0);
        print(board);
    }
}
