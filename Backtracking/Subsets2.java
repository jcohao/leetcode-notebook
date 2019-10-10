import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Subsets2 {
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;    
    }

    private void backtrack(List<List<Integer>> result, List<Integer> subArray, int[] nums, int start) {
        result.add(new ArrayList<>(subArray));
        for (int i = start; i < nums.length; i++) {
            // 这里去重的方法跟 Combination2 那里的一模一样
            if (i > start && nums[i-1] == nums[i]) continue;
            subArray.add(nums[i]);
            backtrack(result, subArray, nums, i + 1);
            subArray.remove(subArray.size() - 1);
        }
    }


    public static void main(String[] args) {
        Subsets2 solution = new Subsets2();
        int[] nums = {2,2,2};
        System.out.println(solution.subsetsWithDup(nums).toString());
    }
}