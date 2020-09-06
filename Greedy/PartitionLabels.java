class PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        if (S == null || S.length() == 0) return new ArrayList<>();

        // 记录字符最后出现的位置
        int[] last = new int[26];

        for (int i = 0; i < S.length(); i++) last[S.charAt(i)-'a'] = i;

        int j = 0, pre = -1;

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < S.length(); i++) {
            j = Math.max(j, last[S.charAt(i)-'a']);
            // 当 i 等于 (pre, j] 之间字符最后位置的最大值，则表明 (pre, j] 区间中的字符在后面不会再出现
            // 此时 (pre, j] 为一个分区
            if (i == j) {
                result.add(i-pre);
                pre = i;
            }
        }

        return result;
    }


    public List<Integer> partitionLabels2(String S) {
        if (S == null || S.length() == 0) return new ArrayList<>();
        
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        
        int pre = -1;
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            set.add(ch);
            
            map.put(ch, map.get(ch)-1);
            
            if (map.get(ch) == 0) set.remove(ch);
            
            if (set.isEmpty()) {
                result.add(i - pre);
                pre = i;
            }
        }
        
        return result;       
    }
}