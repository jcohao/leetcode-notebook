class JumpGame {
    /**
     * 这里设置一个变量 start 用来标记起始跳跃的位置
     * 然后遍历 start + nums[start] 范围内的数组元素
     * 如果能跳到最后一格，则直接返回 true
     * 没有则当 index + nums[index] > start + nums[start] 的变量，说明在这个地方起跳能跳到更远的地方
     * 所以 start 变更为 index
     * 否则 index++
     * 当遍历完了 start + nums[start] 范围内的元素都不能跳到最后一格，则跳不到最后一格，返回 false
     * 
     * 时间复杂度为 O(n) 空间复杂度为 O(1)
     */
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        int len = nums.length;

        int start = 0, i = 0;

        // 这里等于的情况是 [0] 得返回 true
        while (i <= start + nums[start]) {
            if (start + nums[start] >= len - 1) return true;

            if (i + nums[i] > start + nums[start]) {
                start = i;
            } else {
                i++;
            }
        }

        return false;
    }

    /**
     * 回溯，这种方法当数据量大的时候会 TLE 
     * 时间复杂度 O(2^n) 每一个位置都要试一遍，是或否
     * 空间复杂度 O(n) 递归调用方法需要栈空间
     */
    public boolean canJump2(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        return backtracking(0, nums);
    }

    private boolean backtracking(int start, int[] nums) {
        if (start + nums[start] >= nums.length - 1) return true;

        for (int i = Math.min(nums.length-1, start + nums[start]); i > start; i--) {
            if (backtracking(i, nums)) return true;
        }

        return false;
    }

    /**
     * 自顶向下的动态规划
     * 用一个数组来记录每个位置能不能到达最后一格，1 表示能，0 未知，-1 不能
     * 然后进行以上回溯
     * 这个方法很慢很慢  时间复杂度 O(n^2) 空间复杂度 O(n)
     */
    int[] memo;
    public boolean canJump3(int[] nums) {
        if (nums == null || nums.length == 0) return false;

        memo = new int[nums.length];
        memo[nums.length-1] = 1;

        return top2bottom(0, nums);
    }

    private boolean top2bottom(int start, int[] nums) {
        if (memo[start] != 0) return memo[start] == 1 ? true : false;

        int further = Math.min(start + nums[start], nums.length - 1);
        for (int i = further; i > start; i--) {
            if (top2bottom(i, nums)) {
                memo[start] = 1;
                return true;
            }
        }

        memo[start] = -1;
        return false;
    }



    public static void main(String[] args) {
        JumpGame solution = new JumpGame();

        int[] nums = {2,3,1,1,4};
        
        System.out.println(solution.canJump3(nums));
    }
}