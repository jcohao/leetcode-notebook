import java.util.Queue;

public class NumbersOfIslands {
    boolean[][] visited;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int m, n;
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int result = 0;
        
        m = grid.length;
        n = grid[0].length;
        visited = new boolean[m][n];
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '1' && !visited[r][c]) {
                    union(grid, r, c);
                    result++;
                }
            }
        }
        
        return result;
    }

    // DFS
    private void union(char[][] grid, int r, int c) {
        if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c] || grid[r][c] == '0') return;
        visited[r][c] = true;
        for (int[] dir : dirs) {
            union(grid, r + dir[0], c + dir[1]);
        }
    }
    
    // TLE
    private void union1(char[][] grid, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        int count = 1;
        
        while (!queue.isEmpty()) {
            while (count != 0) {
                int[] point = queue.poll();
                visited[point[0]][point[1]] = true;
            
                for (int[] dir : dirs) {
                    int x = dir[0] + point[0];
                    int y = dir[1] + point[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y] && grid[x][y] == '1')
                        queue.add(new int[]{x, y});
                }
                
                count--;
            }
            
            count = queue.size();
        } 
    }

    // BFS
    private void union(char[][] grid, int point) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(point);

        while (!queue.isEmpty()) {       
            int pos = queue.poll();

            for (int[] dir : dirs) {
                int x = pos / n + dir[0];
                int y = pos % n + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || grid[x][y] == '0') continue;
                queue.add(x * n + y);
                // 在这里设置 true 比在 poll 出来再 true 效率要高
                visited[x][y] = true;
            }
        }

    }
}