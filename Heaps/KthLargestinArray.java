class KthLargestinArray {
    /**
     * 这道题可以用排序或者堆来做，但都不是这道题想要考察的
     * 这道题应该用快排的思想去做
     * 快排的思想是选择一个中间数，然后比中间数大的放一边，比中间数小的放另一边
     * 一旦这个中间数的位置为 k-1 时，则该数就是第 K 大的数，数组从大到小排序
     * 如果中间数位置比 k-1 要大，则所要返回的数在中间数位置的左边
     * 反之，在右边
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) return Integer.MAX_VALUE;

        return helper(nums, 0, nums.length-1, k);
    }

    private int helper(int[] nums, int start, int end, int k) {
        int pivot = nums[start], left = start + 1, right = end;

        while (left <= right) {
            if (nums[left] < pivot && nums[right] > pivot) {
                swap(nums, left++, right--);
            }
            if (nums[left] >= pivot) left++;
            if (nums[right] <= pivot) right--;
        }

        swap(nums, start, right);

        if (right == k-1) return nums[right];
        else if (right < k-1) return helper(nums, right+1, end, k);
        else return helper(nums, start, right-1, k);
        
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }


    public static void main(String[] args) {
        KthLargestinArray solution = new KthLargestinArray();
        int[] nums = {3,2,1,5,6,4};
        System.out.println(solution.findKthLargest(nums, 2));
    }
}