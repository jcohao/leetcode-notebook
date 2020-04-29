class ProductOfArrayExceptSelf {
    /**
     * 时间和空间复杂度都为 o(n)
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        // 左数组记录该元素左边的乘积
        int[] left = new int[len];
        // 右数组记录该元素右边的乘积
        int[] right = new int[len];

        left[0] = 1;
        right[len-1] = 1;

        for (int i = 1; i < len; i++) {
            left[i] = left[i-1] * nums[i-1];
            right[len-1-i] = right[len-i] * nums[len-i];
        }

        for (int i = 0; i < len; i++) {
            result[i] = let[i] * right[i];
        }

        return result;
    }

    /**
     * O(1) 解法
     */
    public int[] productExceptSelf1(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        result[0] = 1;

        for (int i = 1; i < len; i++) {
            result[i] = result[i-1] * nums[i-1];
        }

        int R = 1;

        for (int i = len - 1; i >= 0; i--) {
            result[i] = result[i] * R;
            R *= nums[i];
        }

        return result;
    }
}