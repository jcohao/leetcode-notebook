import java.util.Arrays;
import java.util.Collections;

class MaxProductofWordLengths {
    /**
     * 最直接的方法是将数组按照字符串的长度排序
     * 然后双循环数组，检查每两个字符串直接是否有相同的字符
     * 如果没有则记下长度积
     * 最后返回最长长度积
     * 时间复杂度为 O(n^3) 空间复杂度为 O(1)
     */
    public int maxProduct1(String[] words) {
        if (words == null || words.length == 0) return 0;
        int maxLength = 0;
        Arrays.sort(words, (a, b) -> (b.length() - a.length()));

        for (int i = 0; i < words.length-1; i++) {
            for (int j = i+1; j < words.length; j++) {
                if (!hasCommon(words[i], words[j])) {
                    maxLength = Math.max(maxLength, words[i].length()*words[j].length());
                    break;
                }
                    
            }
        }

        return maxLength;
    }

    private boolean hasCommon(String a, String b) {
        boolean[] visited = new boolean[26];

        for (int i = 0; i < a.length(); i++) {
            visited[a.charAt(i)-'a'] = true;
        }

        for (int i = 0; i < b.length(); i++) {
            if (visited[b.charAt(i)-'a']) return true;
        }

        return false;
    }


    /**
     * 使用一个整型的后 26 位来记录一个字符串中的单词是否出现过
     * 如果出现过，则该位为 1
     * 时间复杂度 O(n^2) 空间复杂度 O(n)
     */
    public int maxProduct(String[] words) {
        int maxLength = 0;
        if (words == null || words.length == 0) return maxLength;

        int[] value = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                value[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }

        for (int i = 0; i < words.length-1; i++) {
            for (int j = i+1; j < words.length; j++) {
                if ((value[i] & value[j]) == 0)
                    maxLength = Math.max(maxLength, words[i].length()*words[j].length());
            }
        }

        return maxLength;
    }


    public static void main(String[] args) {
        MaxProductofWordLengths solution = new MaxProductofWordLengths();
        String[] words = {"a","ab","abc","d","cd","bcd","abcd"};
        System.out.println(solution.maxProduct1(words));  
    }
}