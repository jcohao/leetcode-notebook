import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SubstringwithConcatenation {
    /**
     * words 中的字符串作全排列，得到的连接字符串在 s 中匹配位置
     * Time limit exceeded
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return result; 
        // 获取全排列
        Arrays.sort(words);
        List<String> permutation = permutate(words);
        // 获取起始坐标，如过不存在则返回 -1
        for (String str : permutation) {
            List<Integer> indexs = substringIndex(s, str);
            if (indexs != null) {
                result.addAll(indexs);
            }
        }
        return result;
    }

    private List<Integer> substringIndex(String s, String substring) {
        if (s.length() < substring.length() || !s.contains(substring)) return null;
        List<Integer> indexs = new ArrayList<>();
        for (int i = 0; i <= s.length() - substring.length(); i++) {
            if (s.charAt(i) == substring.charAt(0)) {
                if (s.substring(i, i+substring.length()).equals(substring)) {
                    indexs.add(i);
                }
            }
        }
        return indexs; 
    }


    private List<String> permutate(String[] words) {
        List<String> permutation = new ArrayList<>();
        backtrack(permutation, new ArrayList<>(), new boolean[words.length], words);
        return permutation;
    }

    private void backtrack(List<String> result, List<String> subArray, boolean[] memo, String[] words) {
        if (subArray.size() == words.length) {
            String temp = "";
            for (String str : subArray) {
                temp += str;
            }
            result.add(temp);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!memo[i]) {
                if (i > 0 && words[i].equals(words[i-1]) && !memo[i-1]) continue;
                subArray.add(words[i]);
                memo[i] = true;
                backtrack(result, subArray, memo, words);
                subArray.remove(subArray.size()-1);
                memo[i] = false;
            }
            
         
        }
    } 

    /**
     * 用两个指针 start end，end 离 start 的距离为所匹配子字符串的长度，当 end 大于所匹配字符串的时候退出循环
     * 然后每个 start 和 end 的位置去匹配 words 字符串数组中的字符串
     * 用一个记录数组去记录，当这个词是能够匹配上的，且记录数组中没有记录过的，则记录
     * 最后如果记录数组中全部都为 true， 则这个位置有有效位置，加入到结果集当中
     * 但这个方法很慢，看怎么提升下吧
     */

    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) {
            return result;
        }

        int wordLength = words[0].length();
        int substrLength = words.length * wordLength;

        for (int start = 0, end = substrLength; end <= s.length(); start++, end++) {
            if (testSubStr(start, end, s, words)) {
                result.add(start);
            }
        }

        return result;
    
    }


    private boolean testSubStr(int start, int end, String s, String[] words) {
        boolean[] memo = new boolean[words.length];
        List<String> wordsList = Arrays.asList(words);

        for (int i = start; i < end; i += words[0].length()) {
            String word = s.substring(i, i + words[0].length());
            // 如果都不在这个数组里面的，则直接返回 false ps 还是很慢
            if (!wordsList.contains(word)) return false;
            for (int j = 0; j < words.length; j++) {
                if (words[j].equals(word) && !memo[j]) {
                    memo[j] = true;
                    break;
                }
            }
        }

        boolean result = true;
        for (boolean i : memo) {
            result &= i;
        }
        return result;
    }


    /**
     * 用两个 Map 去记录出现的单词数
     * 第一个 Map 用于预处理，将出现的单词以及其次数存储到 Map 中
     * 然后对 s 进行遍历，还是遍历到长度小于所要匹配的子字符串长度时就退出循环
     * 第二个 map 为第一个的复制，每次判断单词的时候，如果单词不在 map 里面或者单词出现的次数不匹配的话
     * 这该子字符串不符合
     */

    public List<Integer> findSubstring3(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return result;

        Map<String, Integer> memo = new HashMap<>();

        int wordNum = words.length;
        int wordLength = words[0].length();
        int substrLength = wordNum * wordLength;

        for (String word : words) {
            memo.put(word, memo.getOrDefault(word, 0) + 1);
        }

        for (int start = 0; start <= s.length() - substrLength; start++) {
            Map<String, Integer> temp = new HashMap<>(memo);

            int num = wordNum;
            int cur = start;
            while (num > 0) {
                String word = s.substring(cur, cur + wordLength);
                if (!temp.containsKey(word) || temp.get(word) < 1) break;
                temp.put(word, temp.get(word)-1);
                num--;
                cur += wordLength;
            }

            if (num == 0) result.add(start);
        }

        return result;
    }


    public static void main(String[] args) {
        SubstringwithConcatenation solution = new SubstringwithConcatenation();
        String[] words = {"foo","bar"};
        
        String str = "barfoothefoobarman";
        
        
        
        System.out.println(solution.findSubstring3(str, words));
    }
}