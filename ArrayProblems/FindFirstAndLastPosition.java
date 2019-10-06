import java.util.Arrays;

public class FindFirstAndLastPosition {

    /**
     * 二分找出目标元素，然后前后找一头一尾，时间复杂度则为 nlogn
     * 
     */
    // public int[] searchRange(int[] nums, int target) {
    //     int[] result = {-1, -1};
    //     if (nums == null || nums.length == 0) {
    //         return result;
    //     }
    //     int low = 0, high = nums.length - 1;
    //     int pos = -1;

    //     while (low <= high) {
    //         int mid = low + (high - low) / 2;
    //         if (target == nums[mid]) {
    //             pos = mid;
    //             break;
    //         } else if (target < nums[mid]) {
    //             high = mid - 1;
    //         } else {
    //             low = mid + 1;
    //         }
    //     }

    //     if (pos != -1) {
    //         int first = pos, last = pos;
    //         // 判断成功了才能改变值
    //         while (first - 1 >= 0 && nums[first-1] == target) {
    //             first--;
    //         }

    //         while (last + 1 < nums.length && nums[last+1] == target) {
    //             last++;
    //         }
    //         result[0] = first;
    //         result[1] = last;

    //         return result;
    //     } else {
    //         return result;
    //     }
        
    // }

    /**
     * 可以对二分法进行修改，查找左边界时返回的条件为 mid 的值等于 target 且 mid-1 的值小于 target
     * 查找右边界时返回的条件为 mid 的值等于target 且 mid + 1 的值大于 target
     * 
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }

        int left = -1, right = -1;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target && (mid - 1 < 0 || nums[mid-1] < target)) {
                left = mid;
                break;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // 如果左边界找不到就意味着右边界也找不到
        if (left == -1)
            return result;

        low = 0;
        high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2; 
            if (nums[mid] == target && (mid + 1 >= nums.length || nums[mid+1] > target)) {
                right = mid;
                break;
            } else if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        result[0] = left;
        result[1] = right;
        return result;
    }

    public static void main(String[] args) {
        FindFirstAndLastPosition solution = new FindFirstAndLastPosition();
        int[] nums = { 1 };
        System.out.println(Arrays.toString(solution.searchRange(nums, 1)));
    }
}