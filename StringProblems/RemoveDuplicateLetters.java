import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return s;

        // 记录左边最小字符的位置
        int pos = 0;  

        // 记录所有字符出现的次数
        int[] memo = new int[26];
        for (int i = 0; i < s.length(); i++) memo[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            if (--memo[s.charAt(i) - 'a'] == 0) break;
        }

        return s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters solution = new RemoveDuplicateLetters();

        System.out.println(solution.removeDuplicateLetters("cbacdcbc"));
    }
}