class LongestCommonSubsequence {
    /**
     * 使用动态规划求解 dp[i][j] 代表 text1[0 ... i] 和 text2[0 ... j] 的最长公共子字符串
     * 当 text1[i] == text2[j] 时，dp[i][j] = dp[i-1][j-1] + 1
     * 否则 dp[i][j] = max(dp[i-1][j], dp[i][j-1])
     * 
     * 例子：abcde ace
     * 两个字符串最后一个都为 e 则，maxCommon = 1
     * abcd 和 ac 的最后一个字符不等，则 maxCommon += max(f(abcd, a), f(abc, ac))，如此类推
     * 
     * 时间复杂度为 O(mn) 空间复杂度为 O(mn)
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;

        int len1 = text1.length(), len2 = text2.length();

        int[][] dp = new int[len1+1][len2+1];

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (text1.charAt(i) == text2.charAt(j)) dp[i+1][j+1] = dp[i][j] + 1;
                else dp[i+1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
            }
        }

        return dp[len1][len2];
    }

    /**
     * 优化时间复杂度为 O(n)
     */
    public int longestCommonSubsequence1(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;

        int len1 = text1.length(), len2 = text2.length(), pre = 0, temp = 0;

        int[] dp = new int[len2+1];

        for (int i = 0; i < len1; i++) {
            pre = dp[0];
            for (int j = 0; j < len2; j++) {
                temp = dp[j+1];
                if (text1.charAt(i) == text2.charAt(j)) dp[j+1] = dp[j] + 1;
                else dp[j+1] = Math.max(dp[j], dp[j+1]);
                pre = temp;
            }
        }

        return dp[len2];
    }


}