import java.util.List;
import java.util.Queue;
import java.util.Set;

class RemoveInvalidParentheses {
    /**
     * dfs 解法
     * @param s
     * @return
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        Set<String> set = new HashSet<>();

        // 先计算需要删除多少个左括号和右括号
        int left = 0, right = 0;
        
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                left++;
            } else if (ch == ')') {
                if (left == 0) right++;
                else left--;
            }
        }

        dfs(s, 0, left, right, set);

        result.addAll(set);

        return result;
    }


    /**
     * 判断该字符串是否有效，用以下规则去判断
     * 当 i <= n - 1 时， nums(')') <= nums('(')
     * 当 i == n - 1 时， nums(')') == nums('(')
     * @param s
     * @return
     */
    private boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;

        int count = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') count++;
            else if (ch == ')') count--;

            // count < 0 说明此时左边的右括号大于左括号，肯定是不合法的
            if (count < 0) return false;
        }

        return count == 0;
    }

    /**
     * 从 start 位置开始删除，先删右括号，再删左括号
     * @param s
     * @param start
     * @param left
     * @param right
     * @param set
     */
    private void dfs(String s, int start, int left, int right, Set<String> set) {
        if (left == 0 && right == 0 && isValid(s)) {
            set.add(s);
            return;
        }

        for (int i = start; i < s.length(); i++) {

            if (i != start && s.charAt(i) == s.charAt(i-1)) continue;

            String afterDelete = s.substring(0, i) + s.substring(i+1, s.length());
            if (s.charAt(i) == ')' && right > 0) {               
                dfs(afterDelete, i, left, right-1, set);
            } else if (s.charAt(i) == '(' && left > 0) {
                dfs(afterDelete, i, left-1, right, set);
            }
        }
    }


    public List<String> removeInvalidParentheses1(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    /**
     * 
     * @param s
     * @param ans
     * @param last_i 表示当前遍历到的地方
     * @param last_j 表示上一个删除的地方
     * @param par 表示是正序括号匹配还是倒序括号匹配
     */
    private void remove(String s, List<String> ans, int last_i, int last_j, char[] par) {
        for (int stack = 0, i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            // 当左括号数大于右括号数时直接 continue，否则说明前缀不合法，删除多余的右括号
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; j++) {
                // 如果当前括号时右括号，且为第一个右括号，则删除该右括号
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j-1) != par[1]))
                    remove(s.substring(0, j) + s.substring(j+1, s.length()), ans, i, j, par);
            }
            // 此循环的目的是删除多余的右括号，删到最好右括号数和左括号一样多，所以之间返回
            return;
        }

        // 翻转则是为了删除多余的左括号
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') remove(reversed, ans, 0, 0, new char[]{')', '('});
        else ans.add(reversed);
    }


    /**
     * BFS 解法
     */
    public List<String> removeInvalidParentheses2(String s) {
        List<String> res = new ArrayList<>();

        if (s == null) return res;

        Set<String> visited = new HashSet<>();

        Queue<String> queue = new LinkedList<>();

        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                res.add(s);
                found = true;
            }

            if (found) continue;

            // 生成所有可能的结果
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                String t = s.substring(0, i) + s.substring(i+1, s.length());

                if (!visited.contains(t)) {
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return res;
    }
}