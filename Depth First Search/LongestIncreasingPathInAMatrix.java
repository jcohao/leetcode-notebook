class LongestIncreasingPathInAMatrix {
    int m, n;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int max = 0;

    // 记录已经遍历过的格子，以免重复计算
    int[][] dp;
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        
        m = matrix.length;
        n = matrix[0].length;

        dp = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(matrix, i, j, Integer.MIN_VALUE));
            }
        }
        
        return max;
    }


    private int dfs(int[][] matrix, int row, int col, int pre) {
        if (row < 0 || row >= m || col < 0 || col >= n || matrix[row][col] <= pre) {
            return 0;
        } else {
            if (dp[row][col] == 0) {
                int partMax = 0;

                for (int[] dir : dirs) {
                    partMax = Math.max(partMax, dfs(matrix, row + dir[0], col + dir[1], matrix[row][col]));
                }

                dp[row][col] = partMax;   
            }

            return 1 + dp[row][col];
        }
    }
    
    /**
     * 没有任何剪枝的话会出现 TLE
     */
    private void dfs(int[][] matrix, int row, int col, int pre, boolean[][] visited, int distance) {
        if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col] ||
            matrix[row][col] <= pre) return;
        
        visited[row][col] = true;
        distance++;
        max = Math.max(max, distance);
        
        for (int[] dir : dirs) {
            dfs(matrix, row + dir[0], col + dir[1], matrix[row][col], visited, distance);
        }

        visited[row][col] = false;
    }

    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix solution = new LongestIncreasingPathInAMatrix();

        int[][] matrix = {
            {9, 9, 4},
            {6, 6, 8},
            {2, 1, 1}
        };

        System.out.println(solution.longestIncreasingPath(matrix));
    }
}