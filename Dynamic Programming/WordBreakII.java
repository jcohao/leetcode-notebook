import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class WordBreakII {
    // 常规的回溯会导致 TLE
    // private List<String> result = new ArrayList<>();
    public List<String> wordBreak1(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return result;

        Set<String> wordSet = new HashSet<>(wordDict);

        collectWord(new ArrayList<>(), s, 0, wordSet);

        return result;
    }

    private void collectWord(List<String> strs, String s, int start, Set<String> wordSet) {
        if (start == s.length()) {
            String sentence = strs.stream().reduce((a, b) -> (a + " " + b)).get();
            result.add(sentence);
            return;
        }

        for (int i = start+1; i <= s.length(); i++) {
            String word = s.substring(start, i);
            if (wordSet.contains(word)) {
                strs.add(word);
                collectWord(strs, s, i, wordSet);
                strs.remove(strs.size()-1);
            }
        }
    }

    private List<String> result = new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return result;

        int maxLen = 0;

        for (int i = 0; i < wordDict.size(); i++) {
            maxLen = Math.max(maxLen, wordDict.get(i).length());
        }

        Set<String> wordSet = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length()+1];

        collectWord(new ArrayList<>(), s, 0, wordSet, maxLen, dp);

        return result;
    }

    /**
     * dp[i] 表示 i 以后的字符串所构成的词都是不能在词典中找到的
     */
    private void collectWord(List<String> strs, String s, int start, Set<String> wordSet, int maxLen, boolean[] dp) {
        if (start == s.length()) {
            String sentence = strs.stream().reduce((a, b) -> (a + " " + b)).get();
            result.add(sentence);
            return;
        } else if (dp[start]) {
            return;
        }

        // boolean invalid = true;

        for (int i = start+1; i <= s.length(); i++) {
            if (i - start > maxLen) break;
            String word = s.substring(start, i);
            if (wordSet.contains(word)) {
                // 能进这里只能说明 i 以后的有可能成句，但用 result 的数量变化来作为标记
                // 则可以判断 i 以后的词能不能成句
                // invalid = false;
                int rstBeforeDFS = result.size();
                strs.add(word);
                collectWord(strs, s, i, wordSet, maxLen, dp);
                if (result.size() == rstBeforeDFS) dp[i] = true;
                strs.remove(strs.size()-1);
            }
        }

        // 不能在跳出循环以后才更新 dp
        // if (invalid) dp[start] = true;
    }


    /**
     * 使用 hashmap 记录所有从索引 i 开始的有效字符串
     */
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Map<Integer, List<String>> memo = new HashMap<>();

        return dfs(s, wordDict, 0, memo);
    }

    private List<String> dfs(String s, List<String> wordDict, int start, Map<Integer, List<String>> memo) {
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> rst = new ArrayList<>();

        if (start == s.length()) {
            rst.add("");
            return rst;
        }

        String curStr = s.substring(start);

        for (String word : wordDict) {
            if (curStr.startsWith(word)) {
                List<String> sub = dfs(s, wordDict, start + word.length(), memo);
                for (String t : sub) {
                    rst.add(word + (t.equals("") ? "" : " ") + t);
                }
            }
        }

        memo.put(start, rst);

        return rst;
    }

    public static void main(String[] args) {
        WordBreakII solution = new WordBreakII();

        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");

        List<String> result = solution.wordBreak2("catsanddog", wordDict);

        result.forEach(System.out::println);

    }
}