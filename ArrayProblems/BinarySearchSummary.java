import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BinarySearchSummary {
    /**
     * 普通的二分法
     */
    public int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        int mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) high = mid - 1;
            else low = mid + 1;
        }

        return -1;
    }

    /**
     * 查找一个不小于目标值的数，可以转换为查找其最后一个小于它的数，最后返回 high - 1 即可
     */
    public int binarySearch2(int[] nums, int target) {
        int low = 0, high = nums.length;
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (nums[mid] < target) low = mid + 1;
            else high = mid;
        }

        return high;

    }


    /**
     * 查找第一个大于目标值的数，可以转换为查找第一个不大于目标值的数
     */
    public int binarySearch3(int[] nums, int target) {
        int low = 0, high = nums.length;
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (nums[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return high;
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 5, 6, 9};
        BinarySearchSummary solution = new BinarySearchSummary();
        System.out.println(solution.binarySearch2(nums, 16));
        // List<Integer> list = new ArrayList<>();
        // list.add(1);
        // list.add(2);
        // System.out.println(Arrays.toString(list.toArray()));
    }
}