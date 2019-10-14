import java.util.ArrayList;
import java.util.List;

class GenerateParentheses {
    /**
     * 像这种排列组合的题一般都是用回溯来做，方法都类似于 Combination
     * 这一题的 left right 变量分别记录加进去的左括号和右括号的数量
     * 数量限制则为左括号数不能超过 n，右括号数不能大于左括号数
     * 每次回溯回来都要去掉刚加进去的元素
     * 当字符串的长度等于 2n 的时候就把字符串加入到结果集中
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        backtract(result, new StringBuffer(), 0, 0, n);

        return result;
    }

    private void backtract(List<String> result, StringBuffer sb, int left, int right, int n) {
        if (sb.length() == 2*n) {
            result.add(String.valueOf(sb));
            return;
        }

        if (left - right > 0) {
            sb.append(")");
            backtract(result, sb, left, right + 1, n);
            sb.deleteCharAt(sb.length()-1);
        } 

        if (left < n) {
            sb.append("(");
            backtract(result, sb, left + 1, right, n);
            sb.deleteCharAt(sb.length()-1);
        }
    }


    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        char[] str = new char[2*n];
        backtracking(result, str, 0, 0, n);

        return result;
    }

    private void backtracking(List<String> result, char[] str, int left, int right, int n) {
        // System.out.println(str.length);
        // 这里不能用 str 数组的长度来判断，因为数组的长度为定长
        int len = left + right;
        if (len == 2*n) {
            result.add(String.valueOf(str));
            return;
        } 

        if (left - right > 0) {
            str[len] = ')';
            backtracking(result, str, left, right + 1, n);
        } 

        if (left < n) {
            str[len] = '(';
            backtracking(result, str, left + 1, right, n);

        }
    }


    public static void main(String[] args) {
        GenerateParentheses solution = new GenerateParentheses();
        System.out.println(solution.generateParenthesis2(3).toString());
    }
}