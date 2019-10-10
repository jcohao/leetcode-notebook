import java.util.ArrayList;
import java.util.List;

class Subsets {
    /**
     * 给定一组含有不同整数的数组，返回其所有的子集，包括空集
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        // result.add(new ArrayList());
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> subArray, int[] nums, int start) {
        // 先加的话就不用再把空集额外添加进去了
        result.add(new ArrayList<>(subArray));
        for (int i = start; i < nums.length; i++) {
            subArray.add(nums[i]);
            // result.add(new ArrayList<>(subArray));
            backtrack(result, subArray, nums, i + 1);
            // 每次回溯返回都会去掉末尾那个元素
            subArray.remove(subArray.size() - 1);
        }
    }

    public static void main(String[] args) {
        Subsets solution = new Subsets();
        int[] nums = {1,2,3};
        System.out.println(solution.subsets(nums).toString());
    }
}