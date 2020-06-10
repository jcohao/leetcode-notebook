class MaxDotProductOfTwoSubsequences {
    /**
     * 递推公式 F(x, y) = max{nums1[x]*nums2[y], F(x-1, y), F(x, y-1), F(x-1, y-1) + nums1[x]*nums2[y]}
     */
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = nums1[i] * nums2[j];
                if (i > 0 && j > 0) dp[i][j] += Math.max(dp[i-1][j-1], 0);
                if (i > 0) dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
                if (j > 0) dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
            }
        }

        return dp[n-1][m-1];
    }


    public int maxDotProduct2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;

        int[][] dp = new int[m+1][n+1];

        for (int i = 0; i < m+1; i++) dp[i][0] = -10000000;

        for (int i = 1; i < n+1; i++) dp[0][i] = -10000000;

        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                dp[i][j] = Math.max(nums1[i-1]*nums2[j-1], 
                           Math.max(nums1[i-1]*nums2[j-1] + dp[i-1][j-1], 
                           Math.max(dp[i][j-1], dp[i-1][j])));
            }
        }

        return dp[m][n];
        
        
    }
}