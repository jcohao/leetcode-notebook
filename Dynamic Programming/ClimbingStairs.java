class ClimbingStairs {
    /**
     * 第 n 格要走的步数刚好等于第 n - 1 格（加 1 等于 n）
     * 加上第 n - 2 格（加 2 等于 n）的步数
     */
    public int climbStairs(int n) {
        if (n <= 2) return n;

        int[] nums = new int[n];

        nums[0] = 1;
        nums[1] = 2;

        for (int i = 2; i < n; i++) {
            nums[i] = nums[i-2] + nums[i-1];
        }

        return nums[n-1];
    }

    /**
     * 不需要辅助数组的版本
     */
    public int climbStairs2(int n) {
        if (n <= 2) return n;

        int i = 1, j = 2;
        // int temp;

        for (int k = 2; k < n; k++) {
            // temp = i + j;
            // i = j;
            // j = temp;
            j += i;
            i = j - i;
        }

        return j;
    }


    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();

        System.out.println(solution.climbStairs2(10));
    }
}