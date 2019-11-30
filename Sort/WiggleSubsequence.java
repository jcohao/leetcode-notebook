class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        int result = 0;
        if (nums == null || nums.length == 0) return result;

        int pre = 0, temp = 1;

        for (int i = 1; i < nums.length; i++) {
            if ((nums[i] - nums[i-1]) * pre >= 0 || i == nums.length - 1) {
                result = (temp > result) ? temp : result;
                temp = 1;
            } else {
                temp++;
            }

            pre = nums[i] - nums[i-1];
        }

        return result + 1;
    }

    public static void main(String[] args) {
        WiggleSubsequence solution = new WiggleSubsequence();

        int[] nums = {1,17,5,10,13,15,10,5,16,8};

        System.out.println(solution.wiggleMaxLength(nums));
    }
}