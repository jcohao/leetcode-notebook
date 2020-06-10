class PalindromicSubstrins {
    public int countSubstrings1(String s) {
        if (s == null || s.length() == 0) return 1;

        int result = 0, len = s.length();

        // dp 解法，依靠判断 s[i] == s[j] 以及 dp[i+1][j-1] 来判断 s[i...j] 子序列是否为回文序列
        boolean[][] dp = new boolean[len][len]; 
        
        for (int i = len-1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    result++;
                }
            }
        }

        return result;
    }


    // 时间复杂度为 O(n*n) 空间复杂度为 O(1) 
    public int countSubstrings2(String s) {
        if (s == null || s.length() == 0) return 1;

        int result = 0, len = s.length();

        // 遍历回文字符串的每一个中心，该中心可能为一个字符串，也可能为两个字符串之间
        for (int center = 0; center <= 2*len-1; center++) {
            int left = center / 2;
            int right = left + center % 2;
            // 以 left right 为基点向两边扩，寻找回文子串
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                result++;
                left--;
                right++;
            }
        }

        return result;
    }

    /**
     * 马拉车算法，找出字符串中最长的回文子串的长度
     * 时间和空间复杂度都为 O(n)
     */
    public int manacher(String s) {
        char[] A = new char[2 * s.length() + 3];
        A[0] = '@';
        A[1] = '#';
        A[A.length-1] = '$';
        // 在原字符串的所有字符的两边都添加 # 将字符串的长度变为奇数，就能统一处理奇偶数情况了
        int t = 2;
        for (char c : s.toCharArray()) {
            A[t++] = c;
            A[t++] = '#';
        }

        // P 是用来记录以 i 为中心的子串，其最长回文子串的半径，其半径减一则是其回文子串的长度
        int[] P = new int[A.length];
        // 最开始的时候一头一尾添加 @ 和 $ 是为了计算回文子串的开始位置 = (中间位置 - 半径) / 2

        // center 能延伸到最右端的回文串的中心位置
        // right 能延伸到最右端的回文串的右端点位置
        int center = 0, right = 0;

        for (int i = 1; i < P.length-1; i++) {
            // 当 i < right 此时 i 在最大回文串的范围里面，j = 2*center-i 为 i 以 center 为中心的对称点
            // 如果 P[j] < right - i，说明以 j 为中心的回文串没有超出最大回文串的范围
            // 根据回文字符串的对称性可知 P[i] == P[j]
            // 当 P[j] >= right - i 时，以 j 为中心的回文串的左端超过了最大回文串的左端 [left...center...right]
            // 此时 P[j] == P[i] 不总是成立，此时 [left...j] 之间的字符肯定能在 [j...center] 中找到
            // 也就是根据对称性 [i...right] 与 [center...i] 之间的字符串肯定也是相同的
            // 所以此时 P[i] 至少为 right - i
            // 至于超出 right 的那部分是否相等，就得在下面去遍历了
            // 当 right > i 的时候只能老实地一步步去匹配
            P[i] = right > i ? Math.min(right - i, P[2*center - i]) : 1;

            // 从 right + 1 开始，匹配其对称的字符，然后更新 P[i]
            while (A[i + P[i]] == A[i - P[i]]) P[i]++;

            // 如果 i + P[i] > right 的话，则更新最大回文子串的中心位置以及能到达的最右端
            if (i + P[i] > right) {
                center = i;
                right = i + P[i];
            }
        }

        int max = 0;
        for (int num : P) max = Math.max(max, num);

        return max-1;
    }




    public static void main(String[] args) {
        PalindromicSubstrins solution = new PalindromicSubstrins();

        solution.manacher("abaaba");
    }
}