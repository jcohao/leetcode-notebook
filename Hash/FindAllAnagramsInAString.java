import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FindAllAnagramsInAString {
    /**
     * 解法参考 Permutation in String
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || s.length() == 0) return new ArrayList<>();
        
        List<Integer> result = new ArrayList<>();
        
        int pLen = p.length();
        
        int[] memo = new int[26];
        
        
        for (int i = 0; i < pLen; i++) memo[p.charAt(i)-'a']++;
        
        int left = 0, count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (--memo[s.charAt(i)-'a'] >= 0) count++; 

            if (count == pLen) result.add(left);

            if (i - left == pLen - 1 && ++memo[s.charAt(left++)-'a'] > 0) count--;
        }
        
        return result;
        
    }

    
    public static void main(String[] args) {
        FindAllAnagramsInAString solution = new FindAllAnagramsInAString();

        System.out.println(solution.findAnagrams("cbaebabacd", "abc").toString());
        
    }
}