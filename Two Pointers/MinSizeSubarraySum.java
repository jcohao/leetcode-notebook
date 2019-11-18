class MinSizeSubarraySum {
    /**
     * 题目是求和大于等于 s 的最短子数组，不只是求等于，好好审题鸭。。。亲
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int result = nums.length + 1, left = 0, sum = 0, right = 0;

        while (right != nums.length || sum >= s) {
            if (right < nums.length && sum < s) {
                sum += nums[right++];
                // right++;
            } else {
                // 每次比较的时候 right 已经在上一轮加过 1 了，所以计算间隔的时候不用再加 1 了
                // result = Math.min(result, right - left + 1);
                result = Math.min(result, right - left);
                
                sum -= nums[left++];
                // left++;
            }
        }

        return result == nums.length + 1 ? 0 : result;
    }

    public static void main(String[] args) {
        MinSizeSubarraySum solution = new MinSizeSubarraySum();
        int[] nums = {2,3,1,2,4,3};
        System.out.println(solution.minSubArrayLen(7, nums));
    }
}