class EditDistance {
    public int minDistance(String word1, String word2) {
        return helper(word1, word2, 0, 0);
    }

    private int helper(String word1, String word2, int index, int mod) {
        if (word1 == null || index >= word1.length()) 
            return mod + (word1 == null ? word2.length() : word2.length()-word1.length());
        else if (index >= word2.length()) 
            return mod + word1.length() - word2.length();

        if (word1.charAt(index) == word2.charAt(index)) {
            return helper(word1, word2, index+1, mod);
        } else {
            String changeWord = word1.substring(0, index) + word2.charAt(index) + word1.substring(index+1);
            String deleteWord = word1.substring(0, index) + word1.substring(index+1);

            return Math.min(helper(changeWord, word2, index+1, mod+1), helper(deleteWord, word2, index, mod+1));
        }
    }

    /**
     * dp：dp[i][j] 表示长度为 i 的字符串 word1 转换成长度为 j 的字符串 word2 的最小操作数
     * 当 word1[i] == word2[j] 此时 dp[i][j] = dp[i-1][j-1]
     * 当 word1[i] != word2[j]
     *      把 word1[i] 替换成 word2[j] 则 dp[i][j] = dp[i-1][j-1] + 1
     *      在 word1 后面插入一个 word2[j] 则 dp[i][j] = dp[i][j-1] + 1
     *      把 word1[i] 删除 则 dp[i][j] = dp[i-1][j] + 1
     * 
     *      dp[i][j] = min(dp[i-1][j-1], dp[i][j-1], dp[i-1][j])
     */
    public int minDistance1(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        int[][] dp = new int[m+1][n+1];
        
        // 当 word1 的长度为 0 时，则一直做插入操作
        for (int i = 1; i <= n; i++) {
            dp[0][i] = dp[0][i-1] + 1;
        }

        // 当 word2 的长度为 0 时，则一直做删除操作
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i-1][0] + 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i][j-1]), dp[i-1][j]) + 1;
                }
            }
        }

        return dp[m][n];
    }

    public int minDistance2(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        int[] dp = new int[n+1];
        int pre = 0;

        for (int i = 1; i <= n; i++) dp[i] = dp[i-1] + 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (j == 0) {
                    pre = dp[0];
                    dp[0] += 1;
                } else {
                    int temp = pre;
                    pre = dp[j];
                    if (word1.charAt(i-1) == word2.charAt(j-1)) {
                        dp[j] = temp;
                    } else {
                        dp[j] = Math.min(Math.min(temp, dp[j]), dp[j-1]) + 1;
                    }
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        EditDistance solution = new EditDistance();
        int result = solution.minDistance2("horse", "ros");
        System.out.println(result);
    }
}