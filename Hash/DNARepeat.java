import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class DNARepeat {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 10) return result;
        
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i <= s.length() - 10; i++) {
            String subStr = s.substring(i, i + 10);
            if (map.getOrDefault(subStr, 0) == 1) {
                result.add(subStr);
            }
            map.put(subStr, map.getOrDefault(subStr, 0) + 1);
        }
        
        return result;
    }

    public List<String> findRepeatedDnaSequences2(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 10) return result;

        Set<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - 10; i++) {
            String subStr = s.substring(i, i+10);
            if (!set.contains(subStr)) {
                set.add(subStr);
            } else {
                if (!result.contains(subStr)) {
                    result.add(subStr);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        DNARepeat solution = new DNARepeat();
        System.out.println(solution.findRepeatedDnaSequences2("AAAAAAAAAAAA").toString());
    }
}