import java.util.Map;

class SubarraySumEqualsK {
    /**
     * 以下解法的 sum 相当于是序列 (0, i) 的和，如果序列 (0, j) 的和 sum - k 在 map 中出现过
     * 这说明序列 (0, j) - (0, i) = (i, j) 直接的序列的和为 k
     * 初始化 put 的 (0, 1) 则是序列 (0, i) 的和就等于 k 的情况
     * 
     * 时间和空间复杂度都为 O(n)
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) count += map.get(sum - k);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}