class MaximalSquare {
    /**
     * 用 dp 解法来解这道题
     * dp[i][j] 代表以 matrix[i][j] 为右下角顶点的正方形的最大边长
     * 当 matrix[i][j] == 1 时，更新 dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])
     * 
     * 将 dp 设为 [m+1][n+1] 的容量就不用考虑初始化了
     * 
     * 时间复杂度和空间复杂度均为 O(mn)
     */
    public int maximalSquare1(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int m = matrix.length, n = matrix[0].length, maxArea = 0;

        int[][] dp = new dp[m+1][n+1];

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
                    maxArea = Math.max(maxArea, dp[i][j] * dp[i][j]);
                }
            }
        }

        return maxArea;
    }

    /**
     * 优化的 dp 解法
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int m = matrix.length, n = matrix[0].length, maxArea = 0, pre = 0, temp = 0;

        int[] dp = new int[n+1];

        for (int i = 0; i < m; i++) {
            pre = dp[0];
            for (int j = 1; j < n+1; j++) {
                temp = dp[j];
                if (matrix[i][j-1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j-1], dp[j]), pre);
                    maxArea = Math.max(maxArea, dp[j]*dp[j]);
                } else {
                    dp[j] = 0;
                }

                pre = temp;
            }
        }

        return maxArea;
    }
}