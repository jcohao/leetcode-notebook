class LargestDivisibleSubset {
    /**
     * 大前提：如果 (a, b, c, d) 为 DS a < b < c < d
     * 则 x % d == 0 && x > d，那么 (a, b, c, d, x) 也为 DS
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        Arrays.sort(nums);

        int len = nums.length;
        // dp[i] 为以 nums[i] 为结尾的最长 DS 的长度
        int[] dp = new int[len];
        // pre[i] 为 nums[i] 能整除的最大 nums[k] 的索引 k，0 <= k < i
        int[] pre = new int[len];
        // max 为最长 DS 的长度，last 记录最长 DS 的最后一个索引
        int max = 0, last = -1;

        for (int i = 0; i < len; i++) {
            dp[i] = 1;
            pre[i] = -1;

            for (int j = i-1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + dp[j] > dp[i]) {
                        dp[i] = dp[j] + 1;
                        pre[i] = j;
                    }
                }

                if (dp[i] > max) {
                    max = dp[i];
                    last = i;
                }
            }
        }

        List<Integer> result = new ArrayList<>();

        while (last != -1) {
            result.add(nums[last]);
            last = pre[last];
        }

        return result;
    }
}