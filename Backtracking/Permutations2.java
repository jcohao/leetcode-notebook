import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] memo = new boolean[nums.length];
        backtrack(result, nums, new ArrayList<>(), memo);
        return result;
    }

    private void backtrack(List<List<Integer>> result, int[] nums, 
        List<Integer> subArray, boolean[] memo) {
        if (subArray.size() == nums.length) {
            result.add(new ArrayList<>(subArray));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            
            if (!memo[i]) {
                // 出现重复元素且之前的元素是没被访问过的则跳过
                if (i > 0 && nums[i] == nums[i - 1] && !memo[i - 1]) continue;
                memo[i] = true;
                subArray.add(nums[i]);
                backtrack(result, nums, subArray, memo);
                subArray.remove(subArray.size() - 1);
                memo[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Permutations2 solution = new Permutations2();
        int[] nums = {1,2,3,4};
        System.out.println(solution.permuteUnique(nums).toString());
    }
}