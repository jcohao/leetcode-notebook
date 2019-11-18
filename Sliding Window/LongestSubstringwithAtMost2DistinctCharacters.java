import java.util.HashMap;
import java.util.Map;

class LongestSubstringwithAtMost2DistinctCharacters {
    /**
     * Given a string s , find the length of the longest substring t that contains
     * at most 2 distinct characters.
     * 
     * Example 1:
     * 
     * Input: "eceba" Output: 3 Explanation: tis "ece" which its length is 3.
     * Example 2:
     * 
     * Input: "ccaabbb" Output: 5 Explanation: tis "aabbb" which its length is 5.
     */

     /**
      * 用 map 记录字符的个数，一旦超过两个字符，左指针开始动，没动一格，对应的字符数量减一
      * 直到字符数降到 2 为止，最后才更新最长子字符串数
      */
    public int solution(String s) {
        if (s == null || s.length() < 2) return 0;

        int left = 0,  max = 0, count = 0;

        Map<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);

            if (!map.containsKey(ch) || map.get(ch) == 0) {
                map.put(ch, 1);
                count++;
            } else {
                map.put(ch, map.get(ch) + 1);
            }

            // if (count == 2) max = Math.max(max, right - left + 1);

            while (count > 2) {
                char leftChar = s.charAt(left);

                map.put(leftChar, map.get(leftChar) - 1);

                if (map.get(leftChar) == 0) count--;

                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;

    }


    public static void main(String[] args) {
        LongestSubstringwithAtMost2DistinctCharacters test = new LongestSubstringwithAtMost2DistinctCharacters();

        System.out.println(test.solution("eceba"));
    }
}