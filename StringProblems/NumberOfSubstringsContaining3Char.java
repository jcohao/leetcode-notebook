class NumberOfSubstringsContaining3Char {
    /**
     * 这种解法不能通过大数据量的输入
     */
    public int numberOfSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;

        int count = 0;

        for (int i = 0; i < s.length() - 2; i++) {
            for (int j = i + 3; j <= s.length(); j++) {
                String subString = s.substring(i, j);
                if (subString.contains("a") && subString.contains("b") && subString.contains("c")) {
                    count += s.length() - j + 1;
                    break;
                }
            }
        }

        return count;
    }

    /**
     * 滑动窗口
     */
    public int numberOfSubstrings1(String s) {
        if (s == null || s.length() == 0) return 0;

        int count = 0, j = 0;

        int[] memo = {0, 0, 0};

        for (int i = 0; i < s.length(); i++) {
            memo[s.charAt(i) - 'a']++;
            while (memo[0] > 0 && memo[1] > 0 && memo[2] > 0) {
                memo[s.charAt(j++) - 'a']--; 
            }
            count += j;
        }

        return count;
    }


    public static void main(String[] args) {
        NumberOfSubstringsContaining3Char solution = new NumberOfSubstringsContaining3Char();

        System.out.println(solution.numberOfSubstrings1("aabcaa"));
    }
}