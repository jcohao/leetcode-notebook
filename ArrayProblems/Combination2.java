import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 这道题跟上一道题都是用到了回溯法
 * 但是这道题的区别是每一个元素不能用重复次
 * 而且 candidates 里面是允许有重复元素的，这样将会导致最终的结果可能有重复
 * 我这里的解决方案是先把找出来的集合存到 set 中，让 set 来帮我们去重
 * 最后再转为 list 去返回最终结果
 */
class Combination2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        // Set<List<Integer>> result = new HashSet<>();
        backtracking(result, new ArrayList<Integer>(), 0, candidates, target);
        return result;
        // return new ArrayList<List<Integer>>(result);
    }

    private void backtracking(List<List<Integer>> result, List<Integer> subArray, int index,
        int[] candidates, int target) {
            // 应该先判断target是否等于 0，不然刚好所有值加起来等于target则会加不进result中
            if (target == 0) {
                result.add(subArray);
            } else {
                for (int i = index; i < candidates.length; i++) {
                    // 当 i 不为 index 时，且前后两个遍历的数相等，则跳过，可以去掉重复的项
                    if (i > index && candidates[i - 1] == candidates[i]) continue;
                    List<Integer> newSubArray = new ArrayList<>(subArray);
                    if (candidates[i] <= target) {
                        newSubArray.add(candidates[i]);
                        // 自身不能用重复次，所以这里传入的索引次数应该为 i+1，从自己的下一位开始遍历
                        backtracking(result, newSubArray, i+1, candidates, target-candidates[i]);
                    } else {
                        // 大于的话就直接返回了，之后的值也用不了了
                        return;
                    }
                    
                    
                }
            }
    }


    public static void main(String[] args) {
        Combination2 solution = new Combination2();
        int[] nums = {10,1,2,7,6,1,5};
        System.out.println(solution.combinationSum2(nums, 8).toString());
    }
}