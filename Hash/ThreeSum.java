import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 去重
            if (i > 0 && nums[i] == nums[i-1]) continue;

            // 特殊情况，由于是求三数之和为 0，数组是排过序的，如果 [i] + [n-1] + [n-2] 还是小于 0，则可以跳过 i
            if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] < 0) continue;

            // 如果 [i] + [i+1] + [i+2] 的还是大于 0 的话，那后面就不可能有等于 0 的组合了，直接 break
            if (nums[i] + nums[i+1] + nums[i+2] > 0) break;


            int left = i + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重
                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left < right && nums[right-1] == nums[right]) right--;
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums = {-2,0,0,2,2};
        List<List<Integer>> result = solution.threeSum(nums);
        result.forEach(e -> System.out.println(e.toString()));
    }
}