class InterleavingString {
    // TLE
    public boolean isInterleave1(String s1, String s2, String s3) {
        if (s3 == null) return false;
        else if (s3.length() == 0) return s1.length() == 0 && s2.length() == 0;
        else if (s1 == null || s1.length() == 0) return s2.equals(s3);
        else if (s2 == null || s2.length() == 0) return s1.equals(s3);
        else if (s1.length() + s2.length() != s3.length()) return false;

        int s1_p = 0, s2_p = 0, s3_p = 0;

        while (s3_p < s3.length()) {
            if (s1_p == s1.length()) {
                return s3.endsWith(s2.substring(s2_p));
            } else if (s2_p == s2.length()) {
                return s3.endsWith(s1.substring(s1_p));
            } else if (s1.charAt(s1_p) == s3.charAt(s3_p) && s2.charAt(s2_p) != s3.charAt(s3_p)) {
                s1_p++;
                s3_p++;
            } else if (s1.charAt(s1_p) != s3.charAt(s3_p) && s2.charAt(s2_p) == s3.charAt(s3_p)) {
                s2_p++;
                s3_p++;
            } else if (s1.charAt(s1_p) == s3.charAt(s3_p) && s2.charAt(s2_p) == s3.charAt(s3_p)) {
                return isInterleave(s1.substring(s1_p+1), s2.substring(s2_p), s3.substring(s3_p+1)) ||
                    isInterleave(s1.substring(s1_p), s2.substring(s2_p+1), s3.substring(s3_p+1));
            } else {
                return false;
            }
        }

        return true;
    }

    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s3 == null) return false;
        else if (s3.length() == 0) return s1.length() == 0 && s2.length() == 0;
        else if (s1 == null || s1.length() == 0) return s2.equals(s3);
        else if (s2 == null || s2.length() == 0) return s1.equals(s3);
        else if (s1.length() + s2.length() != s3.length()) return false;

        return helper(s1.toCharArray(), s2.toCharArray(), s3.toCharArray(), 0, 0, 0);
    }

    // 递归太深
    private boolean helper(char[] chs1, char[] chs2, char[] chs3, int p1, int p2, int p3) {
        if (p1 == chs1.length && p2 == chs2.length && p3 == chs3.length) return true;

        while (p3 < chs3.length) {
            if (p1 == chs1.length) {
                while (p2 < chs2.length && p3 < chs3.length) {
                    if (chs2[p2++] != chs3[p3++]) return false;
                }
                return p2 == chs2.length && p3 == chs3.length;
            } else if (p2 == chs2.length) {
                while (p1 < chs1.length && p3 < chs3.length) {
                    if (chs1[p1++] != chs3[p3++]) return false;
                }
                return p1 == chs1.length && p3 == chs3.length;
            } else if (chs1[p1] == chs3[p3] && chs2[p2] != chs3[p3]) {
                p1++;
                p3++;
            } else if (chs1[p1] != chs3[p3] && chs2[p2] == chs3[p3]) {
                p2++;
                p3++;
            } else if (chs1[p1] == chs3[p3] && chs2[p2] == chs3[p3]) {
                return helper(chs1, chs2, chs3, p1++, p2, p3++) ||
                    helper(chs1, chs2, chs3, p1, p2++, p3++);
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 强拆，时间复杂度 O(2^(m+n)) 空间复杂度 O(m+n) 更加容易 TLE
     */
    public boolean isInterleave3(String s1, String s2, String s3) {
        return is_Interleave(s1, 0, s2, 0, "", s3);
    }

    private boolean is_Interleave(String s1, int i, String s2, int j, String res, String s3) {
        if (res.equals(s3) && i == s1.length() && j == s2.length()) return true;

        boolean ans = false;

        if (i < s1.length()) {
            ans |= is_Interleave(s1, i+1, s2, j, res + s1.charAt(i), s3);
        }
        if (j < s2.length()) {
            ans |= is_Interleave(s1, i, s2, j+1, res + s2.charAt(j), s3);
        }

        return ans;
    }

    /**
     * 带着 memo 的递归，memo 可以减少重复的计算
     */
    public boolean isInterleave4(String s1, String s2, String s3) {
        // memo[i][j] 代表 s1 匹配到 i，s2 匹配到 j，-1 为初始化，0 为不匹配，1 为匹配
        int[][] memo = new int[s1.length()][s2.length()];
        // 先初始化
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }

        return is_Interleave(s1, 0, s2, 0, s3, 0, memo);
    }

    private boolean is_Interleave(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
        if (i == s1.length()) return s2.substring(j).equals(s3.substring(k));

        if (j == s2.length()) return s1.substring(i).equals(s3.substring(k));

        if (memo[i][j] >= 0) return memo[i][j] == 1 ? true : false;

        boolean ans = false;

        if (s3.charAt(k) == s1.charAt(i) && is_Interleave(s1, i+1, s2, j, s3, k+1, memo) ||
            s3.charAt(k) == s2.charAt(j) && is_Interleave(s1, i, s2, j+1, s3, k+1, memo)) {
            ans = true;
        }

        memo[i][j] = ans ? 1 : 0;

        return ans;
    }

    /**
     * dp 解法，dp[i][j] 表示 s1[0...i] 前缀和 s2[0...j] 前缀能够组成 s3[0...i+j+2] 前缀
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null && s2 == null) return s3 == null;
        else if (s3.length() != s1.length() + s2.length()) return false;

        int len1 = s1.length(), len2 = s2.length();

        boolean[][] dp = new boolean[len1+1][len2+1];

        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1);
                } else if (j == 0) {
                    dp[i][j] = dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i-1+j);
                } else {
                    dp[i][j] = (dp[i][j-1] && s2.charAt(j-1) == s3.charAt(i+j-1)) || 
                        (dp[i-1][j] && s1.charAt(i-1) == s3.charAt(i-1+j));
                }
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String s1 = "bb";
        String s2 = "cc";
        String s3 = "bcbc";
        InterleavingString solution = new InterleavingString();
        System.out.println(solution.isInterleave(s1, s2, s3));
    }
}