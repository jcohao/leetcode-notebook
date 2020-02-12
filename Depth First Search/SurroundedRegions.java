import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Coordinate {
    int r;
    int c;

    public Coordinate(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class SurroundedRegions {
    public void solve(char[][] board) {
        if (board == null) return;

        int n = board.length, m = board[0].length;

        boolean[][] visited = new boolean[n][m];

        Queue<Coordinate> queue = new LinkedList<>();

        for (int i = 1; i < m-1; i++) {
            if (board[0][i] == 'O') queue.add(new Coordinate(0, i));
            if (board[n-1][i] == 'O') queue.add(new Coordinate(n-1, i));
        }

        for (int i = 1; i < n-1; i++) {
            if (board[i][0] == 'O') queue.add(new Coordinate(i, 0));
            if (board[i][m-1] == 'O') queue.add(new Coordinate(i, m-1));
        }

        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            Coordinate coor = queue.poll();
            int row = coor.r;
            int col = coor.c;

            for (int[] dir : dirs) {
                int r = row + dir[0];
                int c = col + dir[1];

                if (r > 0 && r < n-1 && c > 0 && c < m-1 && board[r][c] == 'O' && !visited[r][c]) {
                    queue.add(new Coordinate(r, c));
                    visited[r][c] = true;
                }
            }
        }

        for (int r = 1; r < n-1; r++) {
            for (int c = 1; c < m-1; c++) {
                if (!visited[r][c] && board[r][c] == 'O') {
                    board[r][c] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegions solution = new SurroundedRegions();
        char[][] board = {
            {'X', 'O', 'X', 'O', 'X', 'O'},
            {'O', 'X', 'O', 'X', 'O', 'X'},
            {'X', 'O', 'X', 'O', 'X', 'O'},
            {'O', 'X', 'O', 'X', 'O', 'X'}
        };
        solution.solve(board);

        for (char[] b : board) {
            System.out.println(Arrays.toString(b));
        }
    }
}