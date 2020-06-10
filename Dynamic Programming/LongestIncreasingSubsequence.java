class LongestIncreasingSubsequence {
    /**
     * dp[i] 记录 i 位置的最长增长序列长度
     * 每次遍历，需往前找到比 nums[j] < nums[i] 的且 dp[j] 为最大的 j 位置
     * 然后 dp[i] = dp[j] + 1
     * 时间复杂度为 O(n*n) 空间复杂度为 O(n)
     */
    public int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];

        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) max = Math.max(max, dp[j]);

                if (max == result) break;
            }
            dp[i] = max + 1;
            result = Math.max(result, dp[i]);
        }

        return result;
    }


    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        // 每次记下该最长长度的最小元素
        int[] demo = new int[nums.length];

        int result = 0;

        Arrays.fill(demo, Integer.MAX_VALUE);

        demo[0] = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int j = 0;
            while (nums[i] > demo[j]) j++;

            demo[j] = Math.min(demo[j], nums[i]);

            result = Math.max(result, j);
        }

        return result;
    
    }
}