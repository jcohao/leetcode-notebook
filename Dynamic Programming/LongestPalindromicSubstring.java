class LongestPalindromicSubstring {
    String maxStr;
    /**
     * TLE
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() <= 1) return s;
        
        maxStr = s.substring(0, 1);

        findPalindrome(s);

        return maxStr;
    }

    private void findPalindrome(String s) {
        if (s == null || s.length() <= maxStr.length()) return;

        if (isPalindrome(s)) {
            maxStr = s.length() > maxStr.length() ? s : maxStr;
        } else {
            findPalindrome(s.substring(0, s.length()-1));
            findPalindrome(s.substring(1, s.length()));
        }
    }

    private boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;

        for (int i = 0; i < len/2; i++) {
            if (chars[i] != chars[len-i-1]) return false;
        }

        return true;
    }


    /**
     * 从中心进行展开，看该子字符串是否为回文字符串
     * 要分奇偶情况
     * 时间复杂度为 O(n^2) 空间复杂度为 O(1)
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) return s;

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);

            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }

        return R - L - 1;
    }


    /**
     * 动态规划解法：
     * 维护一个二维数组 dp = new boolean[n][n]，dp[i][j] 代表区间 [i, j] 是否为回文字符串
     * 当 i == j 时为回文字符串，当 j = i+1 时为相邻字符，判断 s[i] == s[j]
     * 当 j - i > 1 则需要判断 s[i] == s[j] && dp[i-1][j-1] 的值，如果为 true 则 [i, j] 区间
     * 的字符串为回文字符串，此时需要判断 j - i + 1 是否大于最长回文字符串，然后更新最长字符串的值 
     * 时间复杂度和空间复杂度都为 O(n^2)
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() < 2) return s;

        String result = s.substring(0, 1);
        int len = s.length();

        boolean[][] dp = new boolean[len][len];

        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i + 1; j < len; j++) {
                if (j == i + 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i+1][j-1];
                }

                if (dp[i][j] == true && j - i + 1 > result.length()) {
                    result = s.substring(i, j+1);
                }
            }
        }

        return result;
    }
}