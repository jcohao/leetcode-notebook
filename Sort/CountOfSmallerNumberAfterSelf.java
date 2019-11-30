import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class CountOfSmallerNumberAfterSelf {
    /**
     * 要求一个数右边有多少个较小的数，从这个数组的最后一个元素开始
     * 将它们有序地插入到另外一个数组中，则每个元素插入的位置，则是
     * 它原来右边比它小的元素的个数，排序的过程中它越过了多少个元素，
     * 则说明原来有多少个比它小的元素在它右边
     * 
     * 二分法
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new LinkedList<>();
        if (nums == null || nums.length == 0) return counts;

        List<Integer> sortTemp = new ArrayList<>();

        for (int i = nums.length-1; i >= 0; i--) {
            int low = 0, high = sortTemp.size();

            while (low < high) {
                int mid = low + (high - low) / 2;

                if (sortTemp.get(mid) >= nums[i]) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
            sortTemp.add(high, nums[i]);
            counts.add(0, high);
        }

        return counts;
    }


    public static void main(String[] args) {
        CountOfSmallerNumberAfterSelf solution = new CountOfSmallerNumberAfterSelf();

        int[] nums = {5,2,6,1};
        System.out.println(solution.countSmaller(nums).toString());
    }
}