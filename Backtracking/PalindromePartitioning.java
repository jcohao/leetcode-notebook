import java.util.ArrayList;
import java.util.List;

class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        
        if (s == null || s.length() == 0) return result;
        
        helper(result, new ArrayList<>(), s, 0);
        
        return result;
    }
    
    
    private void helper(List<List<String>> result, List<String> subSet, String s, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(subSet));
            return;
        }
        
        
        for (int i = start; i < s.length(); i++) {
            int k = start, j = i;
            boolean isPalindrome = true;
            while (k < j) {
                if (s.charAt(k++) != s.charAt(j--)) {
                    isPalindrome = false;
                    break;
                }
            }
            
            if (isPalindrome) {
                subSet.add(s.substring(start, i+1));
                helper(result, subSet, s, i+1);
                subSet.remove(subSet.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        PalindromePartitioning solution = new PalindromePartitioning();

        solution.partition("aab");
    }
}