class NumberOfWaysToPaintN3Grid {
    // 用回溯的方法，当 n 一大则调用栈过深，TLE
    private int mod = 1_000_000_007;
    private int result = 0;
    public int numOfWays1(int n) {
        if (n <= 0) return result;

        int[][] grid = new int[n][3];
        

        helper(grid, 0, 0);

        return result;
    }

    private void helper(int[][] grid, int row, int col) {
        if (row == grid.length) {
            result++;
            result %= mod;
            return;
        } else if (col == 3) {
            helper(grid, row + 1, 0);
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (row == 0 && col != 0) {
                if (grid[row][col-1] == i) continue;
            } else if (row != 0 && col == 0) {
                if (grid[row-1][col] == i) continue;
            } else if (row != 0 && col != 0) {
                if (grid[row-1][col] == i || grid[row][col-1] == i) continue;
            }
            grid[row][col] = i;
            helper(grid, row, col+1);
        }

    }

    /**
     * 涂颜色总共有两种形态
     * 121 型：121, 212, 313, 232, 131, 323     6 种
     * 123 型: 123, 321, 132, 312, 213, 231     6 种
     * 
     * 所有的可能方案等于 121 型的数量 + 123 型的数量
     * 如果第一层是 121 型，则下一层可涂的方案为：212, 313, 232, 312, 213
     * 即等于上一层的 121 型 * 3 + 上一层的 123 型 * 2
     * 如果第一层是 123 型，则下一层可涂的方案为：212, 232, 321, 312
     * 即等于上一层的 121 型 * 2 + 上一层的 123 层 * 2
     * 
     * 则这一层的方案也是 121 型的数量 + 123 型的数量
     */
    public int numOfWays(int n) {
        if (n <= 0) return 0;

        long[][] dp = new long[n][2];

        dp[0][0] = 6;
        dp[0][1] = 6;

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i-1][0] * 3 % mod + dp[i-1][1] * 2 % mod;
            dp[i][1] = dp[i-1][0] * 2 % mod + dp[i-1][1] * 2 % mod;
        }

        return (int) ((dp[n-1][0] + dp[n-1][1]) % mod);
    }

    public static void main(String[] args) {
        NumberOfWaysToPaintN3Grid solution = new NumberOfWaysToPaintN3Grid();

        System.out.println(solution.numOfWays(5000));
    }
}