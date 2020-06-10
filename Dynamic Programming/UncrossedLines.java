class UncrossedLines {
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length;
        
        int[][] dp = new int[m+1][n+1];
        
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                if (A[i] == B[j]) dp[i][j] = 1 + dp[i+1][j+1];
                else dp[i][j] = Math.max(dp[i+1][j+1], Math.max(dp[i+1][j], dp[i][j+1]));
            }
        }
        
        return dp[0][0];
    }

    public static void main(String[] args) {
        UncrossedLines solution = new UncrossedLines();

        System.out.println(solution.maxUncrossedLines(new int[]{1, 2, 4}, new int[]{1, 2, 4}));
        
    }
}