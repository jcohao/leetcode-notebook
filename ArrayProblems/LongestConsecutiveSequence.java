class LongestConsecutiveSequence {
    /**
     * 比较直接的方法是直接排序然后寻找最长连续子序列
     * 时间复杂度为 O(nlogn) 空间复杂度为 O(1)
     * 达不到要求的 O(n) 
     */

    /**
     * 用 HashSet 来去重和查找值
     * 每次从子序列开始的值开始找最长连续子串
     * 最终时间复杂度和空间复杂度都为 O(n)
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        Set<Integer> set = new HashSet<>();
        
        for (int num : nums) set.add(num);
        
        int result = 1;
        
        for (int num : set) {
            if (!set.contains(num-1)) {
                int curNum = num;
                int count = 1;
                
                while (set.contains(curNum+1)) {
                    curNum++;
                    count++;
                }
                
                result = Math.max(result, count);
            }
        }
        
        return result;
    }
}