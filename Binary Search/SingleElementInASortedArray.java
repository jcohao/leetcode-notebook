class SingleElementInASortedArray {
    public int singleNonDuplicate1(int[] nums) {
        int low = 0, high = nums.length-1, mid = 0;
        
        while (low < high) {
            mid = low + (high - low) / 2;
            
            if (nums[mid] != nums[mid-1] && nums[mid] != nums[mid+1]) return nums[mid];
            else if (nums[mid] == nums[mid-1] && mid % 2 == 0) high = mid;
            else if (nums[mid] == nums[mid-1] && mid % 2 != 0) low = mid + 1;
            else if (nums[mid] != nums[mid-1] && mid % 2 == 0) low = mid;
            else if (nums[mid] != nums[mid-1] && mid % 2 != 0) high = mid - 1;
        }
        
        return nums[low];
    }


    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length-1, mid = 0;

        while (low < high) {
            mid = low + (high - low) / 2;

            // 保持 mid 到 high 的元素个数为奇数个
            if (mid % 2 == 1) mid--;

            // 此时如果不等于的话则在左边
            if (nums[mid] != nums[mid+1]) high = mid;
            // 等于的话在右边
            else low = mid + 2;
        }

        return nums[low];
    }
}