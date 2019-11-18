class RotateArray {
    /**
     * 这道题最直接的做法估计就是把原数组元素 nums[i] 复制到临时数组 temp[i+k]
     * 最后再把临时数组的元素复制回原数组中，但时间和空间复杂度都很大
     * 
     * 为了减少空间复杂度，只能是进行原地复制了，挪动 k 个位置不好转移，但每次挪动 1 个位置就好操作了
     * 只需把最后一个元素保存起来，然后从后往前遍历，将每一个元素往后挪一个位置，最后再把最后一个元素
     * 放回到第一个位置即可，然后整个过程挪动 k 次结束，最终时间复杂度为 O(kn)
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k == nums.length) return;

        int len = nums.length;
        // 如果 k 大于数组长度要先做取余操作
        if (k > len) k %= len;

        while (k > 0) {
            int temp = nums[len-1];

            for (int i = len - 2; i >= 0; i--) {
                nums[i+1] = nums[i];
            }

            nums[0] = temp;
            k--;
        }
    }

    /**
     * 然后想一下能不能不挪动 k 次，然后依照上一个方法每个元素挪 k 个位置，没想通
     * 参考别人的解题方法为，现将数组逆过来，然后分别将前 k 个元素和后 n - k 个元素逆回来即可
     */
    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k == nums.length) return;
        int len = nums.length;
        // 如果 k 大于数组长度要先做取余操作
        if (k > len) k %= len;

        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        if (start >= end) return;

        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        RotateArray solution = new RotateArray();
        int[] nums = {1, 2, 3, 4, 5, 6};
        solution.rotate(nums, 2);
    }
}