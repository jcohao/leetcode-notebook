class PalindromePartitioningII {
    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();

        // isPalindrome[i][j] 字符串 [i, j] 区间是否为回文序列
        // 状态方程 isPalindrome[i][j] = (s[i] == s[j]) && isPalindrome[i+1][j-1]
        boolean[][] isPalindrome = new boolean[n][n];

        // for (int i = n - 1; i >= 0; i--) {
        //     for (int j = i; j < n; j++) {
        //         isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && 
        //             (j - i <= 1 || isPalindrome[i+1][j-1]);
        //     }
        // }

        // dp[i] 表示子串 [0...i] 范围内的最小分割数
        // 当 j < i 的情况下，dp[i] = [0...j-1] 的最小分割数 + [j, i] 是否为回文字串 ? 1 : 0
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            // dp[i] 最多分割为 i
            dp[i] = i;
            for (int j = 0; j <= i; j++) {
                // 只有 [j...i] 字串为回文字串才能进条件
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || isPalindrome[j+1][i-1])) {
                    isPalindrome[j][i] = true;
                    // 所有这里 j == 0 的时候肯定是回文
                    dp[i] = (j == 0) ? 0 : Math.min(dp[i], dp[j-1] + 1);
                }
            }
        }

        return dp[n-1];
    }


    public int minCut1(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();

        // isPalindrome[i][j] 字符串 [i, j] 区间是否为回文序列
        // 状态方程 isPalindrome[i][j] = (s[i] == s[j]) && isPalindrome[i+1][j-1]
        boolean[][] isPalindrome = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && 
                    (j - i <= 1 || isPalindrome[i+1][j-1]);
            }
        }

        // dp[i] 表示子串 [0...i] 范围内的最小分割数
        // 当 j < i 的情况下，dp[i] = [0...j-1] 的最小分割数 + [j, i] 是否为回文字串 ? 1 : 0
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            // dp[i] 最多分割为 i
            dp[i] = i;
            if (isPalindrome[0][i]) {
                dp[i] = 0;
                continue;
            }
            for (int j = 0; j <= i; j++) {
                if (isPalindrome[j][i]) dp[i] = Math.min(dp[i], dp[j-1] + 1);
                
            }
        }

        return dp[n-1];
    }

    public static void main(String[] args) {
        PalindromePartitioningII solution = new PalindromePartitioningII();

        System.out.println(solution.minCut1("abacb"));     
    }
}