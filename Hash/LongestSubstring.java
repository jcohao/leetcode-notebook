import java.util.Arrays;

class LongestSubstring {
    /**
     * 左右指针遍历字符串 s，用一个 int[256] (足够记录字符数) 来记录遍历过的字符
     * 右指针移动，当移动到一个字符时，查看其在记录数组中的数是不是 0
     * 是 0 表示之前没出现过，然后该数字 ++ 就变为 1 了
     * 如果是出现过的话，说明 left 和 right 之间是没有重复字符的子字符串
     * 这时候更新最长子字符串的长度，right 移回到 left 的位置，left++，这里 left 不能先 ++ 再移 right
     * 因为 right 在循环中也是要 ++ 的，否则的话会跳过 left 指向的数字
     * 然后重置记录数组
     * 特殊情况是 right 移动到字符串最后一个字符时，且这个字符之前没出现过的话，也要更新 max 的值
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        int[] memo = new int[256];
        Arrays.fill(memo, 0);

        int max = 0;

        for (int left = 0, right = 0; right < s.length(); right++) {
            char curChar = s.charAt(right);
            if (right == s.length() - 1 && memo[curChar] == 0) {
                max = Math.max(max, right - left + 1);
            } else if (memo[curChar] == 0) {
                memo[curChar]++;
            } else {
                max = Math.max(max, right - left);
                // 后面 right 还得 ++，所以得等于 ++ 前的 left
                right = left;
                left++;
                Arrays.fill(memo, 0);
            }
        }

        return max;
    }

    /**
     * 也可以用记录数组来记录字符出现的位置，这样可以达到 O(n) 的时间复杂度
     * 如此记录字符出现过的位置，则下一次左指针移动就不是移动到自己的下一位了
     * 而是移动到出现过的字符的下一位了，省去了很多不必要的操作
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) return 0;

        int[] memo = new int[256];
        // 键盘只能打出 128 个字符，所以用 128 就够了
        Arrays.fill(memo, -1);

        int i, j, max;
        i = j = max = 0;

        while (j < s.length()) {
            char curChar = s.charAt(j);
            // 这时说明 curChar 是在之前出现过的
            if (memo[curChar] >= i) {
                // i 指向重复出现的字符的下一位
                i = memo[curChar] + 1;
            }
            // 更新 curChar 出现的位置
            memo[curChar] = j;
            j++;
            max = Math.max(max, j - i);
        }

        return max;
    }


    public static void main(String[] args) {
        LongestSubstring solution = new LongestSubstring();
        String s = "abcabczxv";
        System.out.println(solution.lengthOfLongestSubstring2(s));
    }
}