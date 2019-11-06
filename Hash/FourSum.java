import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class FourSum {
    /**
     * 回溯   超时 :)
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums == null || nums.length < 4) return result;
        Arrays.sort(nums);
        
        backtracking(result, new ArrayList<>(), nums, target, 0);
        
        return result;
    }
    
    private void backtracking(List<List<Integer>> result, List<Integer> subArray, int[] nums, int target, int start) {
        if (target == 0 && subArray.size() == 4) {
            result.add(new ArrayList<>(subArray));
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) continue;
            
            subArray.add(nums[i]);
            backtracking(result, subArray, nums, target-nums[i], i+1);
            subArray.remove(subArray.size()-1);
        }
    }


    /**
     * 这种方法的时间复杂度为 O(n^3)
     */
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) return result;
        // 首先将数组排序
        Arrays.sort(nums);

        // 外层双层循环遍历两个数
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;        // 去重

            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i+1 && nums[j] == nums[j-1]) continue;
                
                // 定义左右指针去遍历剩下的数字
                int left = j + 1, right = nums.length - 1;

                while (left < right) {
                    int res = nums[i] + nums[j] + nums[left] + nums[right];

                    if (res == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left+1]) left++;  // 左指针指向的数字重复则去重
                        while (left < right && nums[right] == nums[right-1]) right--;       // 右指针也一样
                        // 若不用去重的话，左右指针都得移动
                        left++;
                        right--;
                    } else if (res < target) {
                        // 结果比 target 要小，因为数组是排过序的，所以左指针移动
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }


    public List<List<Integer>> fourSum3(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums, target, 3, 0);
    }

    /**
     * 递归版的 NSum N > 1
     */
    private List<List<Integer>> nSum(int[] nums, int target, int n, int start) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < n) return result;

        // 2 sum 的情况，也是递归结束的边界
        if (n == 2) {
            int left = start, right = len - 1;
            while (left < right) {
                int res = nums[left] + nums[right];

                if (res == target) {
                    // result.add(Arrays.asList(nums[left], nums[right]));
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[left]);
                    temp.add(nums[right]);
                    result.add(temp);

                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left < right && nums[right] == nums[right-1]) right--;

                    left++;
                    right--;
                } else if (res < target) {
                    left++;
                } else {
                    right--;
                }
            }
        } else {
            for (int i = start; i < len - n + 1; i++) {
                // 如果要用这个条件去重，得先判断
                // if (i > start && nums[i] == nums[i-1]) continue;
                // 这里不能为 start + 1 应该是 i + 1 才对
                List<List<Integer>> temp = nSum(nums, target-nums[i], n-1, i+1);
                if (temp.size() != 0) {
                    for (List<Integer> list : temp) {
                        list.add(nums[i]);
                    }
                    result.addAll(temp);
                } 

                while (i < len - 1 && nums[i] == nums[i+1]) {
                    i++;
                }
                
            }
        }


        return result;
    }


    

    public static void main(String[] args) {
        FourSum solution = new FourSum();
        int[] nums = {-1, 0, 1, 0};
        List<List<Integer>> result = solution.fourSum3(nums, 0);
        result.forEach(array -> System.out.println(array.toString()));
    }
}