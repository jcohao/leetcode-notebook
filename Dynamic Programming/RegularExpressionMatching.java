class RegularExpressionMatching {
    public boolean isMatch1(String s, String p) {
        if (s == null || p == null) return s == p;

        int pPointer = 0, sPointer = 0;

        while (pPointer < p.length() && sPointer < s.length()) {
            char pChar = p.charAt(pPointer);

            if (pChar == '.') {
                // 匹配 . ，分带 * 和不带 *
                if (pPointer < p.length()-1 && p.charAt(pPointer+1) == '*') {
                    PPointer += 2;
                    while (sPointer < s.length()) {
                        if (isMatch(s.substring(sPointer), p.substring(pPointer))) {
                            return true;
                        } else {
                            sPointer++;
                        }
                    }
                    
                } else {
                    pPointer++;
                    sPointer++;
                }

            } else {
                // 匹配普通字符，也是分带 * 和不带 *
                if (pPointer < p.length()-1 && p.charAt(pPointer+1) == '*') {
                    pPointer += 2;
                    while (sPointer < s.length()) {
                        if (isMatch(s.substring(sPointer), p.substring(pPointer))) {
                            return true;
                        } else {
                            sPointer++;
                        }
                    }
                } else {
                    if (s.charAt(sPointer) != pChar) return false;

                    pPointer++;
                    sPointer++;
                }

            }
        }


        if (pPointer == p.length() && sPointer == s.length()
            || pPointer == p.length()-2 && p.charAt(pPointer+1) == '*') return true;
        else return false;
    }


    /**
     * 递归解法
     */
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        boolean first_match = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        if (p.length() >= 2 && p.charAt(1) == '*') {
            // 前面为 x* 什么都不匹配，后面则为匹配第一个字符，然后 x* 继续匹配剩下的字符串
            return isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p));
        } else {
            // 仅匹配第一个字符
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }

    /**
     * 带 memory 的递归，对应以上递归的解法，将中间结果存储在了 dp 数组中
     * 使用 dp[i, j] 记录 s[:i] 是否与 p[:j] 匹配
     */
    boolean memo[][];
    public boolean isMatch2(String s, String p) {
        // memo[s.length() + 1][p.length() + 1] 为二者都遍历完的情况
        memo = new boolean[s.length() + 1][p.length() + 1];
        return dp(0, 0, s, p);
    }

    private boolean dp(int i, int j, String s, String p) {
        if (memo[i][j]) return true;
        boolean ans;

        if (j == p.length()) {
            ans = i == s.length();
        } else {
            boolean first_match = i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');

            if (j + 1 < p.length() && p.charAt(j+1) == '*') {
                ans = dp(i, j+2, s, p) || first_match && dp(i+1, j, s, p);
            } else {
                ans = first_match && dp(i+1, j+1, s, p); 
            }
        }

        memo[i][j] = ans;
        return ans;
    }

    // 这个才是 dp 解法
    // dp[i][j] 代表的是 s[i:] 与 p[j:] 是否匹配，需要由后往前遍历
    // 因为前面状态的计算依赖后面的结果
    public boolean isMatch3(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[s.length()][p.length()] = true;

        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean first_match = i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');

                if (j + 1 < p.length() && p.charAt(j+1) == '*') {
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    // 这里必须要先校验 first_match 因为 i 是有可能是非法的
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        RegularExpressionMatching solution = new RegularExpressionMatching();

        System.out.println(solution.isMatch("mississippi", "mis*is*p*."));
    }
}