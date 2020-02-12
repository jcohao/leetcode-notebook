import java.util.List;
import java.util.ArrayList;

class Subsets {

    /**
     * 找子集，最好理解的方法就是回溯了
     * 时间复杂度 O(N * 2 ^ N)    空间复杂度 O(2 ^ N)
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        backtracking(result, new ArrayList<>(), nums, 0);

        return result;
    }


    private void backtracking(List<List<Integer>> result, List<Integer> subsets, 
        int[] nums, int start) {
            result.add(new ArrayList<>(subsets));

            for (int i = start; i < nums.length; i++) {
                subsets.add(nums[i]);
                backtracking(result, subsets, nums, i+1);
                subsets.remove(subsets.size()-1);
            }
    }



    /**
     * 位图解法
     * 用 0 和 1 来表示数组中每一个位置的数字在或者不在
     * 如 nums = {1, 2, 3}  010 就代表了 subset [2]
     * 时间复杂度 O(N * 2 ^ N)    空间复杂度 O(2 ^ N)
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        int n = nums.length;
        int nthBit = 1 << n;

        for (int i = 0; i < Math.pow(2, n); i++) {
            // i 与 nthBit (其实就是开头为1其他为0的二进制数) 相与，得到我们所需要的排列 (去掉最前面那位)
            String bitmask = Integer.toBinaryString(i | nthBit).substring(1);
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < bitmask.length(); j++) {
                if (bitmask.charAt(j) == '1') {
                    subset.add(nums[j]);
                }
            }
            result.add(subset);
        }

        return result;
    }

    public static void main(String[] args) {
        Subsets solution = new Subsets();
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = solution.subsets(nums);
        for (List<Integer> list : result) {
            System.out.println(list.toString());
        }
    }
}