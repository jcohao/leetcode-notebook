import java.util.Arrays;

class WiggleSort {

// Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
// For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].

    // 现将数组排序，然后索引 1 和索引 2 的元素进行交换，索引 4 和索引 5 的元素进行交换，以此类推
    // 时间复杂度为 O(nlogn)
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) return;

        Arrays.sort(nums);

        for (int i = 1; i < nums.length - 1; i += 3) {
            int temp = nums[i];
            nums[i] = nums[i+1];
            nums[i+1] = temp;
        }
    }

    // 当遍历到奇数位时， nums[i] >= nums[i-1]
    // 当遍历到偶数位时， nums[i] <= nums[i-1]
    // 当不满足上述条件时，跟前一位数字交换即可
    // 时间复杂度为 O(n)
    public void wiggleSort2(int[] nums) {
        if (nums == null || nums.length < 2) return;

        for (int i = 1; i< nums.length; i++) {
            if (i % 2 == 0 && nums[i] > nums[i-1] || i % 2 == 1 && nums[i] < nums[i-1]) {
                int temp = nums[i];
                nums[i] = nums[i-1];
                nums[i-1] = temp;
            }
        }
    }


    public static void main(String[] args) {
        WiggleSort solution = new WiggleSort();

        int[] nums = {3, 5, 4, 1, 6, 4};

        solution.wiggleSort2(nums);
        System.out.println(Arrays.toString(nums));
    }
} 