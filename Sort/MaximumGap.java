class MaximumGap {
    public int maximumGap(int[] nums) {
        int gap = 0;
        if (nums == null || nums.length < 2) return gap;

        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            // if (nums[i] - nums[i-1] > gap) gap = nums[i] - nums[i-1];
            // gap = Math.max(gap, nums[i] - nums[i-1]);
            gap = (nums[i] - nums[i-1] > gap) ? nums[i] - nums[i-1] : gap;
        }

        return gap;
        
    }


    /**
     * 在排序的过程中找 gap 以提高性能
     * 这里先用选排试下水，感觉没那么简单，用选排是因为每次选出来的可以跟上一位去比较，没错，就是因为简单
     * 应该是更慢的，因为上面最多是 O(nlogn) 而下面则是 O(n*n)
     */
    public int maximumGap2(int[] nums) {
        int gap = 0;
        if (nums == null || nums.length < 2) return gap;

        int min, temp;

        for (int i = 0; i < nums.length; i++) {
            min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) min = j;
            }

            temp = nums[i];
            nums[i] = nums[min];
            nums[min] = temp;

            if (i > 0 && nums[i] - nums[i-1] > gap) gap = nums[i] - nums[i-1];

        }

        return gap;
    }


    /**
     * MSD：先用高优先级的关键字进行分组
     * LSD：先用低优先级的关键字进行分组
     * 基数排序，从低位到高位 LSD，先按照个位的数字进行排序，如 44, 5, 32, 6
     * 按个位进行排序为：32, 44, 5, 6，再按十位进行排序 5, 6, 32, 44，如此类推
     */
    public int maximumGap3(int[] nums) {
        int gap = 0;
        if (nums == null || nums.length < 2) return gap;

        int maxVal = nums[0];

        for (int i = 1; i < nums.length; i++) {
            maxVal = (nums[i] > maxVal) ? nums[i] : maxVal;
        }

        int[] aux = new int[nums.length];

        int exp = 1;
        int radix = 10;

        while (maxVal / exp > 0) {
            int[] count = new int[radix];

            for (int i = 0; i < nums.length; i++)
                count[(nums[i] / exp) % 10]++;

            for (int i = 1; i < radix; i++) 
                count[i] += count[i-1];

            for (int i = nums.length - 1; i >= 0; i--)      // 按照对应位数去排序
                aux[--count[(nums[i] / exp) % 10]] = nums[i];

            for (int i = 0; i < nums.length; i++)
                nums[i] = aux[i];

            exp *= radix;
        }

        for (int i = 1; i < nums.length; i++) {
            gap = (nums[i] - nums[i-1] > gap) ? nums[i] - nums[i-1] : gap;
        }

        return gap;

    }
}