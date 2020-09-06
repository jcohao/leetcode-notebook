

public class BinarySearch {

    /**
     * 没有重复元素的二分法
     */
    public int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int low = 0, high = nums.length-1, mid = 0;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return -1;
    }


    // 有重复元素的情况下

    /**
     * 查找第一个值等于给定值的元素
     */
    public int binarySearch1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int low = 0, high = nums.length-1, mid = 0;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                if (mid == 0 || nums[mid-1] != target) return mid;
                else high = mid - 1;
            }
        }

        return -1;
    }


    /**
     * 查找最后一个值等于给定值的元素
     */
    public int binarySearch2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int low = 0, high = nums.length-1, mid = 0;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                if (mid == nums.length-1 || nums[mid+1] != target) return mid;
                else low = mid + 1;
            }
        }

        return -1;
    }


    /**
     * 查找第一个大于等于给定值的元素
     */
    public int binarySearch3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int low = 0, high = nums.length-1, mid = 0;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                if (mid == 0 || nums[mid-1] < target) return mid;
                else high = mid - 1;
            }
        }

        return -1;
    }

    /**
     * 查找第一个小于等于给定值的元素
     */
    public int binarySearch4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int low = 0, high = nums.length-1, mid = 0;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (nums[mid] > target) {
                high = mid - 1;
            } else {
                if (mid == nums.length-1 || nums[mid+1] > target) return mid;
                else low = mid + 1;
            }
        }

        return -1;
    }
}