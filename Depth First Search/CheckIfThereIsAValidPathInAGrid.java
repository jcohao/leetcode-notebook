import java.util.Queue;

class CheckIfThereIsAValidPathInAGrid {
    int m, n;
    boolean[][] visited;
    // 每条路能通往哪个方向
    int[][][] dirs = {
        {{0, -1}, {0, 1}},
        {{-1, 0}, {1, 0}},
        {{0, -1}, {1, 0}},
        {{1, 0}, {0, 1}},
        {{0, -1}, {-1, 0}},
        {{-1, 0}, {0, 1}}
    };
    
    public boolean hasValidPath(int[][] grid) {
        if (grid == null || grid.length == 0) return true;

        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        return dfs(grid, 0, 0);
    }

    private boolean dfs(int[][] grid, int row, int col) {
        if (row == m-1 && col == n-1) return true;
        else if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col]) return false;

        visited[row][col] = true;

        for (int[] dir : dirs[grid[row][col]-1]) {
            int nextRow = row + dir[0], nextCol = col + dir[1];
            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n 
                && !visited[nextRow][nextCol]) {
                // 判断能不能往回走
                for (int[] path : dirs[grid[nextRow][nextCol]-1]) {
                    if (nextRow + path[0] == row && nextCol + path[1] == col)
                        return dfs(grid, nextRow, nextCol);
                }
            }

        }

        return false;
    }


    public boolean hasValidPathBFS(int[][] grid) {
        if (grid == null || grid.length == 0) return true;

        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int count = 1;

        while (!queue.isEmpty()) {
            while (count != 0) {
                int point = queue.poll();
                int row = point / n, col = point % n;
                visited[row][col] = true;
                count--;

                for (int[] dir : dirs[grid[row][col]-1]) {
                    int nextRow = row + dir[0], nextCol = col + dir[1];
                    if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n &&
                        !visited[nextRow][nextCol]) {
                        // 判断能不能往回走
                        for (int[] path : dirs[grid[nextRow][nextCol]-1]) {
                            if (nextRow + path[0] == row && nextCol + path[1] == col) 
                                queue.add(nextRow * n + nextCol);
                        }
                    }
                }

            }

            count = queue.size();

        }

        return visited[m-1][n-1];
    }
}