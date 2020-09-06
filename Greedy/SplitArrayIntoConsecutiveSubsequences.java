class SplitArrayIntoConsecutiveSubsequences {
    /**
     * 创建两个哈希表，一个记录数字出现的次数，一个记录某个序列下一位所需要连接的数字的次数
     * 例如 1 2 3 所需要连接的下一位就是 4
     * 如果需要连接的数字大于 0 则该需要连接的数字在哈希表中的数量减一，其下一位就加一
     * 如果需要连接的数字为 0，说明要利用该数字重新开一个新的序列
     * 此时检查该数字加一和加二的次数是否大于 0，都大于 0 则说明能够开一个新的序列
     * 然后也是更新记录次数的哈希表与记录下一位的哈希表即可
     * 其他情况则返回 false
     * 
     * 
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> need = new HashMap<>();
        
        for (int num : nums) freq.put(num, freq.getOrDefault(num, 0) + 1);
                
        for (int num : nums) {
            if (freq.getOrDefault(num, 0) == 0) continue;
            
            if (need.getOrDefault(num, 0) > 0) {
                need.put(num, need.get(num) - 1);
                need.put(num+1, need.getOrDefault(num+1, 0) + 1);
            } else if (freq.getOrDefault(num+1, 0) > 0 && freq.getOrDefault(num+2, 0) > 0) {
                freq.put(num+1, freq.get(num+1)-1);
                freq.put(num+2, freq.get(num+2)-1);
                need.put(num+3, need.getOrDefault(num+3, 0) + 1);
            } else {
                return false;
            }
            
            freq.put(num, freq.get(num)-1);
        }
        
        return true;
    }

    public static void main(String[] args) {
        SplitArrayIntoConsecutiveSubsequences solution = new SplitArrayIntoConsecutiveSubsequences();
        solution.isPossible(new int[]{1,2,3,4,5,6});
    }
    
}