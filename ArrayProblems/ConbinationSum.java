import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * 本题采用回溯法去解决，数组需先升序排序，首先拿例子 [1,2,3] t=8 来看
 * 如果第一个元素为 1 的话，那么第二个元素可以是 (1,2,3) 此时 target 变为 7
 * 每一次都这样深入，到最后 target 变为 0 的时候，则沿着之前一直过来的序列则是满足要求的序列
 * 将此序列加入到 result 中
 * 当深入到 target 小于当前元素时，则此序列不满足要求，不加入到 result 中，且返回
 * 每一次遍历的元素都必须不小于上一次加入序列的元素，不然会出现重复
 * 且每一次序列加入新元素都必须复制成另外一个序列，不然里面会有很多重复的元素
 */
class ConbinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        for (int candidate : candidates) {
            List<Integer> subArray = new ArrayList<>();
            if (candidate <= target) {
                subArray.add(new Integer(candidate));
                backtracking(result, candidates, subArray, target - candidate);
            }
        }

        return result;
    }

    private void backtracking(List<List<Integer>> result, int[] candidates, List<Integer> subArray, int target) {
        if (target == 0) {
            result.add(subArray);
        } else {
            for (int candidate : candidates) {
                if (target < candidate) {
                    return;
                } else if (candidate < subArray.get(subArray.size()-1)) {
                    // 后来的元素不能小于之前加进去的元素，不然会有重复
                    continue;
                } else {
                    // 不能将原列表一直传递，不然会出现很多重复的元素
                    // 将列表作为构造方法的参数即可复制列表
                    List<Integer> newSubArray = new ArrayList<>(subArray);
                    newSubArray.add(new Integer(candidate));
                    backtracking(result, candidates, newSubArray, target - candidate);
                }
            }
        }
    }

    public static void main(String[] args) {
        ConbinationSum solution = new ConbinationSum();
        int[] nums = {1,2,3};
        System.out.println(solution.combinationSum(nums, 8).toString());
    }
}