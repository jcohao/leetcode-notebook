import java.util.Stack;

class LongestValidParentheses {
    /**
     * 每一次遇到 ( 则将其 index 入栈，遇到 ) 则出栈，如果此时栈非空的话，则栈顶元素肯定是出栈 ) 所对应的有效 (
     * 时间和空间复杂度都为 O(n)
     */
    public int longestValidParentheses(String s) {
        int max = 0;

        Stack<Integer> stack = new Stack<>();
        // 一开头就是 ) 的情况
        stack.push(-1);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }

        return max;
    }


    /**
     * dp 解法：时间和空间复杂度都为 O(n)
     */
    public int longestValidParentheses2(String s) {
        int max = 0;
        int[] dp = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            // 有效的字符串肯定是以 ) 结尾的
            if (s.charAt(')')) {
                if (s.charAt(i-1) == '(') {
                    // 因为有一开头就是 () 的情况，所以要判断 i >= 2 ? 
                    // 如果 i - 2 处也是有效字串则 dp[i] = dp[i-2] + 2
                    dp[i] = (i >= 2 ? dp[i-2] : 0) + 2;
                } else if (i - dp[i-1] > 0 && s.charAt(i - dp[i-1] - 1) == '(') {
                    // 判断 i - dp[i-1] > 0 是为了排除 ()) 开头的情况
                    // i-dp[i-1]-1 是为了解决多个括号嵌套的情况，dp[i-1] 则为中间的有效长度，i-dp[i-1]-1 则是对称位
                    // 判断对称位是否为 (，是则加 2
                    dp[i] = dp[i-1] + ((i - dp[i-1]) >= 2 ? dp[i-dp[i-1]-2] : 0) + 2;
                    // 判断 i-dp[i-1] >= 2，因为有 (()) 开头的情况
                }
                max = Math.max(dp[i], max);
            }
        }

        return max;
    }

    public int longestValidParentheses3(String s) {
        int left = 0, right = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                max = Math.max(max, right*2);
            } else if (right > left) {
                left = right = 0;
            }
        }

        left = right = 0;

        for (int i = s.length()-1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }

            if (left == right) {
                max = Math.max(max, left*2);
            } else if (left > right) {
                left = right = 0;
            }
        }

        return max;

    }

    public static void main(String[] args) {
        LongestValidParentheses solution = new LongestValidParentheses();

        System.out.println(solution.longestValidParentheses3("())("));
    }
}