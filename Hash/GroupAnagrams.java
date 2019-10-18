import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GroupAnagrams {
    /**
     * 按照其字符串排列分类，同一类的放到 map 的 list 中
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // List<List<String>> result = new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] temp = str.toCharArray();
            Arrays.sort(temp);
            String key = new String(temp);
            // String key = String.valueOf(temp);
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            // List<String> list = map.get(key);
            // list.add(str);
            map.get(key).add(str);
        }

        // for (List<String> list : map.values()) {
        //     result.add(list);
        // }

        // return result;
        // 这里可以直接 new ArrayList<>(map.values())
        return new ArrayList<>(map.values());
        
    }


    /**
     * 也可以用一个数组去记录字母出现的次数，将整个数组变为字符串作为键
     * 时间复杂度 O(NK) N 输入数组长度， K 最长字符串长度
     * 空间复杂度 O(NK)
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        int[] charCount = new int[26];

        for (String str : strs) {
            Arrays.fill(charCount, 0);
            for (char c : str.toCharArray()) {
                charCount[c - 'a']++;
            }

            StringBuffer sb = new StringBuffer();
            for (int i : charCount) {
                sb.append(i);
            }
            String key = sb.toString();
            // String key = "";
            // for (int i : charCount) {
            //     key += i;
            // }
            if (!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(str);
            // 没有放进去
            // map.getOrDefault(key, new ArrayList<>()).add(str);
        }

        return new ArrayList<>(map.values());

    }

    public static void main(String[] args) {
        // String s1 = "eas";
        // String s2 = "sae";
        // char[] temp = s1.toCharArray();
        // Arrays.sort(temp);
        GroupAnagrams solution = new GroupAnagrams();
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution.groupAnagrams2(input));
    }
}