class JumpGameII {
    /**
     * 保存一个 pre_max 和 max 变量
     * pre_max 表示这一步能走的范围，max 表示在 pre_max 范围内下一步能跳的最大范围
     * 逐个遍历数组中的元素，当超过 pre_max 范围是跳步加一，更新 pre_max 为 max
     * 当 max 大于等于 last index 时返回跳步加一
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int step = 0, len = nums.length;

        int pre_max = nums[0], max = nums[0];

        for (int i = 0; i < len; i++) {
            if (pre_max >= len - 1) return step + 1;

            int scale = i + nums[i];

            if (i > pre_max) {
                pre_max = max;
                step++;
            }
            
            max = Math.max(max, scale);
            
        }

        return step+1;
    }

    public static void main(String[] args) {
        JumpGameII solution = new JumpGameII();

        int[] nums = {1,2,3};

        System.out.println(solution.jump(nums));
    }
}