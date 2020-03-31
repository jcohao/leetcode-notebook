class WildcardMatching {
    // TLE
    public boolean isMatch1(String s, String p) {
        if (s == null || p == null) return s == p;
        else if (p.isEmpty()) return s.isEmpty();
        
        
        boolean first_match = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?');
        
        if (p.charAt(0) == '*') {
            return isMatch(s, p.substring(1)) || isMatch(s.substring(1), p);
        } else {
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
        
    }

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return s == p;

        // 使用 i, j 分别表示 s 和 p 遍历到的地方
        // 使用 jStar 表示 p 中星号的位置
        // 使用 iStar 表示星号匹配到 s 中的位置
        int jStar = -1, iStar = -1, i = 0, j = 0, m = s.length(), n = p.length();

        while (i < m) {
            // s[i] == p[j] 或 p[j] == '?' 时，i j 自增
            if (j < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < n && p.charAt(j) == '*') {
                // 当 p[j] == '*' 时，jStar 记录此时的位置，iStar 记录此时星号匹配 s 的位置
                // 这里 i 没有自增是因为星号可以匹配 0 个字符
                jStar = j++;
                iStar = i;
            } else if (iStar >= 0) {
                // 当不匹配单个字符，且前面星号出现过的情况下（要看 iStar 是不是 -1）
                // i 回到 iStar 后一位，iStar 自增，j 也回到 jStar 的后一位
                i = ++iStar;
                j = jStar + 1;
            } else {
                // 都不是以上情况，则返回 false
                return false;
            }
        }

        // 当 s 为空时，要看 p 中剩下的是不是全部为星号
        while (j < n && p.charAt(j) == '*') j++;
        // 最后返回是否遍历完 p
        return j == n; 
    }


    /**
     * dp 解法，dp[i][j] 表示 s 中的前 i 个字符子串和 p 中的前 j 个字符子串能够匹配
     * dp[0][0] 表示 s 和 p 都为空的时候，此时返回 true
     * 要预先处理 s 为空时，p 为连续星号的情况
     * 若 p 的第 j 个字符为星号，因为星号可以匹配 0 个或多个字符，所以如果 p 中前 j-1 个字符能跟 s 中前 i 个字符匹配，则 dp[i][j-1] 为 true
     * 或者 p 中的前 j-1 个字符跟 s 中前 i 个字符匹配成功，dp[i][j-1] == true，则 dp[i][j] 也为 true（* 可以匹配多个字符）
     * 若 p 的第 j 个字符不为星号，则要看 dp[i-1][j-1] 是否为 true，然后判断 p[j-1] 是否等于 s[i-1] 或者 p[j-1] 为 ？
     */
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        // 处理 s 为空，p 为连续星号的情况
        for (int i = 1; i <= n; i++) {
            if (p.charAt(i-1) == '*') dp[0][i] = dp[0][i-1];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                } else {
                    dp[i][j] = (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') && dp[i-1][j-1];
                }
            }
        }

        return dp[m][n];

    }

    /**
     * 递归方法
     */
    public boolean isMatch3(String s, String p) {
        return helper(s, p, 0, 0) > 1;
    }

    private int helper(String s, String p, int i, int j) {
        // 返回 2 则说明匹配成功
        if (i == s.length() && j == p.length()) return 2;
        // s 匹配完，p 当前字符不是 * 则返回 0
        if (i == s.length() && p.charAt(j) != '*') return 0;
        // p 匹配完，则返回 1
        if (j == p.length()) return 1;

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            return helper(s, p, i+1, j+1);
        }

        if (p.charAt(j) == '*') {
            if (j + 1 < p.length() && p.charAt(j+1) == '*') return helper(s, p, i, j+1);

            for (int k = 0; k <= s.length() - i; k++) {
                int res = helper(s, p, i + k, j + 1);
                if (res == 0 || res == 2) return res;
            }
        }

        return 1;
    }

    public static void main(String[] args) {
        WildcardMatching solution = new WildcardMatching();

        solution.isMatch("aa", "*");
    }
}