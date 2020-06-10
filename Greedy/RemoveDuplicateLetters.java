import java.util.Stack;

class RemoveDuplicateLetters {
    /**
     * 利用栈解决这个问题
     * 首先先记录下字符串中各字母的个数
     * 创建一个数组记录某个字符是否已经加入到栈中了
     * 遍历字符串
     * 当字母是遍历过的话，则元素数量减一且跳过
     * 当栈不为空，栈顶元素比当前遍历元素要大，且该栈顶元素在后面还会出现，此时弹出栈顶元素，设置该元素为未遍历
     * 直到栈为空或者栈顶元素比当前遍历元素小为止
     * 然后将当前遍历元素入栈，元素数量减一，设置为已遍历
     * 
     * 最终得到栈中的元素即为结果
     * 
     * 时间复杂度为 O(n*n) 空间复杂度为 O(n)
     */
    public String removeDuplicateLetters1(String s) {
        if (s == null || s.length() == 0) return s;

        int[] memo = new int[26];
        boolean[] has = new boolean[26];

        for (char c : s.toCharArray()) {
            memo[c-'a']++;
        }

        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {

            if (has[c-'a']) {
                memo[c-'a']--;
                continue;
            }

            while (!stack.isEmpty() && c < stack.peek() && memo[stack.peek()-'a'] > 0) {
                has[stack.peek()-'a'] = false;
                stack.pop();
            }

            stack.push(c);
            memo[c-'a']--;
            has[c-'a'] = true;
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }


    /**
     * 递归解法
     */
    public String removeDuplicateLetters(String s) {
        int[] memo = new int[26];

        int pos = 0;

        for (int i = 0; i < s.length(); i++) memo[s.charAt(i)-'a']++;

        for (int i = 0; i < s.length(); i++) {
            // 找当前字符串中字符顺序最小的或数量只有一个的字符
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--memo[s.charAt(i)-'a'] == 0) break;
        }

        return s.length() == 0 ? "" : s.charAt(i) + s.substring(pos+1).replaceAll("" + s.charAt(pos), "");
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters solution = new RemoveDuplicateLetters();

        System.out.println(solution.removeDuplicateLetters("cbacdcbc"));
    }
}