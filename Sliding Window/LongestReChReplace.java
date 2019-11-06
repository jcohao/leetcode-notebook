class LongestReChReplace {
    /**
     * 右指针往右遍历，如果是相同的字符则继续往右走，如果是不同的字符则 count + 1，直到 count 加到大于 k，记录此时
     * right - left 的长度，然后左指针移动一格，左指针得移动到底
     * 右指针移动到字符串末还得计算一次左右指针间距
     */
    public int characterReplacement(String s, int k) {
        int max = 0;

        if (s == null || s.length() == 0) return max;

        for (int left = 0; left < s.length(); left++) {
            int count = 0;
            for (int right = left + 1; right < s.length(); right++) {
                // 这里边界条件要判断太多了
                if (right == s.length() - 1) {
                    if (count < k) {
                        if (s.charAt(left) == s.charAt(right)) {
                            max = Math.min(s.length(), Math.max(max, right - left + k - count + 1));
                        } else {
                            max = Math.min(s.length(), Math.max(max, right - left + k - count));
                        }   
                    } else {
                        if (s.charAt(left) == s.charAt(right)) {
                            max = Math.max(max, right - left + 1);
                        } else {
                            max = Math.max(max, right - left);
                        }
                    }
                } else if (s.charAt(left) == s.charAt(right)) {
                    continue;
                } else {
                    count++;

                    if (count > k) {
                        max = Math.max(max, right - left);
                        break;
                    }
                }

                
            }
        }

        return max;
    }



    public int characterReplacement2(String s, int k) {
        int max = 0;
        if (s == null || s.length() == 0) return max;
        int len = s.length();
        // 记录英文字母出现的次数
        int[] count = new int[26];
        int left = 0, maxCount = 0;

        for (int right = 0; right < len; right++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(right) - 'A']);
            // 到了该字母的最长字符串修改长度
            while (right - left + 1 - maxCount > k) {
                // 这里是左边界动，左边界扫过的字母就出了窗口范围，需要减回来
                count[s.charAt(left) - 'A']--;
                left++;
            }
            max = Math.max(max, right - left + 1);
        }

        return max;
    }


   

    public static void main(String[] args) {
        LongestReChReplace solution = new LongestReChReplace();
        String s = "BAAA";

        System.out.println(solution.characterReplacement2(s, 0)); 
        
    }
}