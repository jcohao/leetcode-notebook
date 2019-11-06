import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



class MinimumWindowsSubstring {
    /**
     * 这种做法是下用 map 记录 t 中每个字符出现的次数
     * 然后遍历 s，遍历到一个是在 t 中出现的字符后，记这个位置为 left
     * 从 left 这个位置去遍历 s，每遍历到一个 t 中的字符，map 中对应的字符次数就减 1
     * 我们用 t 字符的长度去计数 count，当记录的字符数达到 count，则记下此时的子字符串
     * 直到整个数组遍历完，
     * 但很可惜，这种方法 Time Limit exceeded
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        // else if (s.equals(t)) return t;

        Map<String, Integer> map = new HashMap<>();

        for (char ch : t.toCharArray()) {
            String key = ch + "";
            map.put(key, map.getOrDefault(key, 0)+1);
        }

        String result = null;

        for (int i = 0; i <= s.length()-t.length() ; i++) {
            char ch = s.charAt(i);
            String chKey = ch + "";
            if (map.containsKey(chKey)) {
                int left = i, right = -1;
                int count = t.length();
                Map<String, Integer> temp = new HashMap<>(map);
                for (int j = i; j < s.length(); j++) {
                    String otherKey = s.charAt(j) + "";
                    if (temp.containsKey(otherKey) && temp.get(otherKey) > 0) {
                        temp.put(otherKey, temp.get(otherKey)-1);
                        count--;
                    }

                    if (count == 0) {
                        right = j + 1;
                        break;
                    }
                }

                if (right == -1) {
                    break;
                } else {
                    if (result == null || result.length() > right - left) {
                        result = s.substring(left, right);
                    }
                }

            }
        }

        return (result == null) ? "" : result;

    }

    // 还是超出时间了
    public String minWindow2(String s, String t) { 
        if (s == null || t == null || s.length() < t.length()) return "";
        else if (s.equals(t)) return t;

        Map<String, Integer> map = new HashMap<>();
        
        for (char ch : t.toCharArray()) {
            String key = ch + "";
            map.put(key, map.getOrDefault(key, 0)+1);
        }

        List<Integer> position = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            String key = s.charAt(i) + "";
            if (map.containsKey(key)) position.add(i);
        } 

        String result = null;

        for (int i = 0; i <= position.size() - t.length(); i++) {
            int left = position.get(i), right = -1;
            int count = t.length();
            Map<String, Integer> temp = new HashMap<>(map);
            for (int j = i; j < position.size(); j++) {
                int z = position.get(j);
                String otherKey = s.charAt(z) + "";
                if (temp.containsKey(otherKey) && temp.get(otherKey) > 0) {
                    temp.put(otherKey, temp.get(otherKey)-1);
                    count--;
                }

                if (count == 0) {
                    right = z + 1;
                    break;
                }
            }

            if (right == -1) {
                break;
            } else {
                if (result == null || result.length() > right - left) {
                    result = s.substring(left, right);
                }
            }
        }

        return (result == null) ? "" : result;
    }


    /**
     * 用一个数组记录所有t中字母在s中出现的位置，用map记录字母出现的次数
     * 然后从左往右扫记录位置的数组，每过一个位置该字母在map中的次数就减1，直到有一个字母次数为1的时候，左边界就定了
     * 然后从右往左扫，也是每过一个位置就在map中减1，直到有一个字母次数为1时，右边界确定
     * 这种方法是错的，找到那两个边界也不代表那一段字符串就是最短的
     */
    public String minWindow3(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        
        Map<String, Integer> mapT = new HashMap<>();
        Map<String, Integer> mapS = new HashMap<>();
        List<Integer> poss = new ArrayList<>();

        for (char ch : t.toCharArray()) {
            String key = ch + "";
            mapT.put(key, mapT.getOrDefault(key, 0)+1);
        }

        for (int i = 0; i < s.length(); i++) {
            String key = s.charAt(i) + "";
            if (mapT.containsKey(key)) {
                poss.add(i);
                mapS.put(key, mapS.getOrDefault(key, 0)+1);
            } 
        }

        int left = -1, right = -1;
        for (int pos : poss) {
            String key = s.charAt(pos) + "";
            if (mapS.get(key) > mapT.get(key)) {
                mapS.put(key, mapS.get(key)-1);
            } else if (mapS.get(key) == mapT.get(key)) {
                left = pos;
                break;
            } else {
                break;
            }
        }


        for (int i = poss.size()-1; i >= 0; i--) {
            int pos = poss.get(i);
            String key = s.charAt(pos) + "";
            if (mapS.get(key) > mapT.get(key)) {
                mapS.put(key, mapS.get(key)-1);
            } else if (mapS.get(key) == mapT.get(key)) {
                right = pos;
                break;
            } else {
                break;
            }
        }

        return (left == -1 || right == -1) ? "" : s.substring(left, right+1);

    }



    public String minWindow4(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";
        
        Map<String, Integer> map = new HashMap<>();

        // 记录 t 中出现的字符的次数
        for (char ch : t.toCharArray()) {
            String key = ch + "";
            map.put(key, map.getOrDefault(key, 0)+1);
        }
        String result = "";
        int count = 0, left = 0, minLen = s.length()+1;
        // 遍历 s
        for (int i = 0; i < s.length(); i++) {
            String key = s.charAt(i) + "";
            if (map.containsKey(key)) {
                map.put(key, map.get(key)-1);
                if (map.get(key) >= 0) {
                    count++;
                } 
            }

            while (count == t.length()) {
                if (minLen > i - left + 1) {
                    minLen = i - left + 1;
                    result = s.substring(left, left + minLen);
                }
                String leftkey = s.charAt(left) + "";
                if (map.containsKey(leftkey)) {
                    map.put(leftkey, map.get(leftkey)+1);
                    if (map.get(leftkey) > 0) {
                        count--;
                    }
                }
                left++;
            }
        }

        return result;
    }


    /**
     * 一开始记录那些都是一样的，就不说了，
     * 这里主要是 map 中使用了 char 的包装类 Character，就不用转来转去那么麻烦了
     * 然后重头戏是滑动窗口的部分
     * 这里面先将窗口的左边界固定，然后移动又边界
     * 每当右边界移动到一个 t 中存在的字符时，map 中对应字符的数量 -1，计数器加 1
     * 这里 map 中的减 1 是先减再去判断其在 s 子字符串中出现的次数有没有超过 t 中出现的次数
     * 如果没有，则计数器加 1，如果有，当然还是减了 1，计数器不动，到后面就能明白这一步为什么这样做了
     * 当计数器达到 t 字符串的长度时，滑动窗口右边界固定，此时左右边界所包含的所有字符里面已经包括了 t 中出现的所有字符，有过之而无不及，因为有可能存在
     * 某些字符达到了 t 中应该出现的次数，但至少会有一个是刚好等于 t 中出现的次数的，由于计数器的缘由
     * 然后就是左边界开始滑动啦，
     * 这里先是判断子字符串是不是小于之前记录的最小子字符串啊，是的话就更新
     * 然后，如果左边界路过的字符时 t 中的字符，则其在 map 中的计数加 1，当其加到大于 0 的时候，这说明左边界已经滑出了题目所要求的子字符串了
     * 因为有一个字符已经滑出去了，这时候就又轮到右边界滑了
     * 这题。。。真他妈难理解
     */
    public String minWindow5(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        // 用 char 的包装类 Character 就不用将 char 转换为 String 类型了
        // 记录 t 中字符出现的次数
        Map<Character, Integer> map = new HashMap<>();
        
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // 记录子字符串的长度
        int minLength = s.length() + 1;
        // 当计数器 count 的长度为 t.length() 时，说明所有 t 中的字符已都包含在子字符串中了
        int count = 0;
        // 滑动窗口左边界
        int left = 0;

        String result = "";

        for (int i = 0; i < s.length(); i++) {
            char ichar = s.charAt(i);
            if (map.containsKey(ichar)) {
                // 不能放在下面的 if 语句里面，因为有可能会出现重复的字符，如果放在里面，重复字符出现则无法计入其中
                map.put(ichar, map.get(ichar)-1);
                if (map.get(ichar) >= 0) {
                    // 是 t 中的字符，计数器加 1
                    count++;
                }

                // 当计数器等于 t 的长度的时候，滑动窗口左边界向右滑动
                while (count == t.length()) {
                    if (minLength > i - left + 1) {
                        minLength = i - left + 1;
                        result = s.substring(left, left + minLength);
                    }
                    char leftchar = s.charAt(left);
                    if (map.containsKey(leftchar)) {
                        // 当左边界滑过 t 中存在的字符时，map 中对应的数量得加回去
                        map.put(leftchar, map.get(leftchar)+1);
                        // 当 map 中有一个字符的数量又重新大于 0 的时候，说明左边界已经滑出包含所有 t 中字符的范围了
                        if (map.get(leftchar) > 0) {
                            count--;
                        }
                    }
                    left++;
                }
            }
        }
        return result;
    }

    /**
     * 最后一个是用数组来代替 hashmap，至于解法还是跟上面一样的
     */
    public String minWindow6(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        int[] charMEMO = new int[256];

        for (char ch : t.toCharArray()) {
            charMEMO[ch]++;
        }

        int count = 0, minLeft = -1, minLength = s.length() + 1, left = 0;

        for (int i = 0; i < s.length(); i++) {
            char ichar = s.charAt(i);
            if (t.indexOf(ichar) != -1) {
                charMEMO[ichar]--;
                if (charMEMO[ichar] >= 0) {
                    count++;
                }
            }

            while (count == t.length()) {
                if (minLength > i - left + 1) {
                    minLength = i - left + 1;
                    minLeft = left;
                }
                char leftchar = s.charAt(left);
                if (t.indexOf(leftchar) != -1) {
                    charMEMO[leftchar]++;
                    if (charMEMO[leftchar] > 0) {
                        count--;
                    }
                }
                left++;
            }

        }

        return (minLeft == -1) ? "" : s.substring(minLeft, minLeft + minLength);
    }


    public static void main(String[] args) {
        MinimumWindowsSubstring solution = new MinimumWindowsSubstring();
        String s = "ADBANC", t = "ABC";
        // String s = "aa", t = "aa";
        System.out.println(solution.minWindow6(s, t));
    }
}