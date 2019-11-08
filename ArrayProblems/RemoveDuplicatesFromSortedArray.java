class RemoveDuplicatesFromSortedArray {
    /**
     * 左指针记录新数组位置，右指针遍历原数组，
     * 当右指针所指的值大于左指针的值时，插入到左指针值的后面，左指针加一
     * 最后返回新数组的长度
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int cur = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[cur]) {
                nums[cur+1] = nums[i];
                cur++;
            }
        }

        return cur + 1;
    }


    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray solution = new RemoveDuplicatesFromSortedArray();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(solution.removeDuplicates(nums));
        for (int i : nums) System.out.print(i);
    }
}