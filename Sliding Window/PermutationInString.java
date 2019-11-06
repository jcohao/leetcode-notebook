import java.util.Arrays;

class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s1 == null || s1Len == 0) return true;
        else if (s2 == null || s2Len == 0) return false;

        // 记录 s1 中出现的字母次数
        int[] memo = new int[128];

        for (int i = 0; i < s1Len; i++) {
            memo[s1.charAt(i)]++;
        }

        for (int i = 0; i <= s2Len - s1Len; i++) {
            if (memo[s2.charAt(i)] != 0) {
                int left = i;
                // int right = i + s1Len;
                int[] temp = Arrays.copyOf(memo, 128);
                boolean flag = true;
                while (left < i + s1Len) {
                    int num = s2.charAt(left);
                    if (temp[num] > 0) {
                        temp[num]--;
                    } else {
                        flag = false;
                        break;
                    }
                    left++;
                }

                if (flag) return true;
            }
        }

        return false;

    }


    /**
     * 第二种解法是利用哈希表和双指针
     */
    public boolean checkInclusion2(String s1, String s2) {
        int s1Len = s1.length(), s2Len = s2.length(), left = 0;

        // 先用哈希表记录 s1 中字符出现的情况
        int[] count = new int[26];
        for (int i = 0; i < s1Len; i++) {
            count[s1.charAt(i) - 'a']++;
        }


        // 然后遍历 s2 如果遍历到 s1 中没有的字符，或者超出 s1 中字符的记录数，则左指针移动
        for (int i = 0; i < s2Len; i++) {
            if (--count[s2.charAt(i) - 'a'] < 0) {
                while (++count[s2.charAt(left++) - 'a'] != 0);
            } else if (i - left + 1 == s1Len) {
                return true;
            }
        }

        return s1Len == 0;
    }

    public static void main(String[] args) {
        PermutationInString solution = new PermutationInString();
        String s1 = "ab", s2 = "eidbaooo";
        System.out.println(solution.checkInclusion2(s1, s2));
    }
}