class MaximumProductSubarray {
    /**
     * 求连续数组的最大乘积，使用双循环的方法时间复杂度为 O(n^2)
     */
    public int maxProduct1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int result = nums[0], len = nums.length;
        
        for (int i = 0; i < len; i++) {
            int product = nums[i];
            result = Math.max(result, nums[i]);
            for (int j = i+1; j < len; j++) {
                product *= nums[j];
                result = Math.max(result, product);
            }
        }
        
        return result;
        
    }


    /**
     * 使用两个数组记录包含 nums[i] 的最大连续子数组积
     * 以及最小连续子数组积
     */
    public int maxProduct2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int result = nums[0], len = nums.length;
        
        int[] maxs = new int[len];
        int[] mins = new int[len];
        
        maxs[0] = nums[0];
        mins[0] = nums[0];
        
        for (int i = 1; i < len; i++) {
            maxs[i] = Math.max(Math.max(maxs[i-1] * nums[i], mins[i-1] * nums[i]), nums[i]);
            mins[i] = Math.min(Math.min(maxs[i-1] * nums[i], mins[i-1] * nums[i]), nums[i]);
            
            // 因为有 0 的存在，不能用 maxs[len-1] 来表示最终结果
            result = Math.max(result, maxs[i]);
        }
        
        return result;
        
    }


    /**
     * 优化
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int result = nums[0], imax = nums[0], imin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = imax;
                imax = imin;
                imin = temp;
            }

            imax = Math.max(nums[i], imax * nums[i]);
            imin = Math.min(nums[i], imin * nums[i]);


            result = Math.max(result, imax);
        }

        return result;
    }

    public static void main(String[] args) {
        MaximumProductSubarray solution = new MaximumProductSubarray();

        solution.maxProduct(new int[]{-2,0,-1,3,-1});

    }
}