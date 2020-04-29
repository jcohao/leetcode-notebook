import java.util.Stack;

class ValidParenthesisString {
    /**
     * 当 *) 时，* 可充当左括号抵消掉右括号
     * 当 *( 时，* 无论是左括号、右括号还是为空都是无效的
     * 所以 * 在右括号之前是一定能抵消掉右括号的
     * 所以这个题使用两个栈来保存左括号和星号的位置
     * 遍历字符串，当遇到左括号，则左括号索引压入 left 栈
     * 当遇到星号，则星号索引压入 star 栈
     * 当遇到右括号，如果此时左栈和星栈都为空，则不合法，返回 false
     * 左栈不为空，则 pop 一个出来抵消右括号
     * 左栈为空星栈不为空，则 pop 一个出来抵消右括号
     * 遍历完之后左栈多出来的就是多余的左括号，继续判断有没有星号与其对应的左括号匹配
     * 此时星栈的数量肯定是大于等于左栈的，如果星栈栈顶位置 < 左栈栈顶位置，即出现了类似 *( 这种情况，不合法，返回 false
     * 当左栈遍历为空，星栈中的元素就是多余的星号了，可以为空，返回 true
     */
    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) return true;
        
        Stack<Integer> star = new Stack<>();
        Stack<Integer> left = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (ch == '(') {
                left.add(i);
            } else if (ch == '*') {
                star.add(i);
            } else {
                if (left.isEmpty() && star.isEmpty()) return false;
                else if (!left.isEmpty()) left.pop();
                else star.pop();
            }
        }
        
        // if (star.size() < left.size()) return false;
        
        // while (!left.isEmpty()) {
        //     int leftNum = left.pop();
        //     int starNum = star.pop();
            
        //     if (starNum < leftNum) return false;
        // }
        
        // return true;

        while (!star.isEmpty() && !left.isEmpty()) {
            if (star.peek() < left.peek()) return false;
            star.pop();
            left.pop();
        }

        return left.isEmpty();
    }

    /**
     * 这种方法是记录每次需要匹配的最少数量的右括号和最多数量的右括号
     * 当所需最多数量的右括号数为负时，则不合法，返回 false
     * 最终判断最少是否为零，为零则返回 true
     */
    public boolean checkValidString1(String s) {
        if (s == null || s.length() == 0) return true;

        int less = 0, most = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // 遇到左括号，最少和最多都 +1
            if (ch == '(') {
                less++;
                most++;
            } else if (ch == ')') {
                // 遇到右括号，如果此时最少不为零则 -1，为零则保持不变，因为如果此时仍合法的话前面估计是有 * 的
                // 如果不合法的话，最多已经为负的了，比如 )
                less += less == 0 ? 0 : -1;
                most--;
            } else {
                // 遇到星号，最少的情况跟遇到 ) 相同
                // 最多则 +1
                less += less == 0 ? 0 : -1;
                most++;
            }

            if (most < 0) return false;
        }

        return less == 0;
    }

    public boolean checkValidString2(String s) {
        if (s == null || s.length() == 0) return true;

        int left = 0, right = 0, len = s.length();

        // 正向遍历，把所有的 * 号都当成左括号，进行括号匹配
        // 正向遍历是找有没有多出来的右括号
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '*') left++;
            else left--;

            if (left < 0) return false;
        }

        // 如果此时 left == 0，则说明 * 当成左括号可以完全匹配
        if (left == 0) return true;

        // 此时的 left > 0 的话，有可能多出来的左括号是 *，也有可能是本身就是多出来的左括号
        // 本身就多出来的左括号会在反向遍历的时候被遍历出来，如果没有检测出来，则多出来的左括号肯定是 * 

        // 反向遍历，把所有的 * 号都当成右括号，进行括号匹配
        for (int i = len-1; i >= 0; i--) {
            if (s.charAt(i) == ')' || s.charAt(i) == '*') right++;
            else right--;

            if (right < 0) return false;
        }

        // 正向遍历时，* 都变成了右括号，如果导致右括号有剩余，则剩余的肯定是 * 号，所以直接返回 true
        return true;

    }


    public static void main(String[] args) {
        ValidParenthesisString solution = new ValidParenthesisString();

        System.out.println(solution.checkValidString2("(*)"));
    }
    
}