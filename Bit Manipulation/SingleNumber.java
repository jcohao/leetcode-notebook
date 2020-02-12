class SingleNumber {
    /**
     * 异或运算有结合率，自己与自己异或结果为 0
     */
    public int singleNumber(int[] nums) {
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            result = result ^ nums[i];
        }

        return result;
    }
}