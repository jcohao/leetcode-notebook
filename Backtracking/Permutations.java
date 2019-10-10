import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, nums, new ArrayList<>(), 0);
        return result;
    }


    private void backtrack(List<List<Integer>> result, int[] nums, List<Integer> subArray, int count) {
        // count 是计数用的，当 count 等于数组长度时，则子数组为一个排列，计入到结果当中
        // 这里用子数组的长度去计数就不需要额外的变量去计数了
        // subArray.size() == nums.length
        if (count == nums.length) {
            result.add(new ArrayList<>(subArray));
            return;
        } 
        for (int i = 0; i < nums.length; i++) {
            // 如果元素不在的话则加入到子数组中
            // 然后进行回溯
            // 每次回溯回来都删掉子数组中最后的一个元素
            if (!subArray.contains(nums[i])) {
                subArray.add(nums[i]);
                backtrack(result, nums, subArray, count+1);
                subArray.remove(subArray.size() - 1);
            } 
            
        }
    }

    

    public static void main(String[] args) {
        Permutations solution = new Permutations();
        int[] nums = {1, 2, 3};
        System.out.println(solution.permute2(nums).toString());

    }
}