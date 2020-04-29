import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class ContiguousArray {
    /**
     * 定义一个和记录 0 ~ i 所走过的路径和，遇到 1 则加 1，遇到 0 则减 1
     * 当两个相同的和出现，就意味着这两个相同的和之间的路径和为 0
     * 
     * 时间和空间复杂度都为 O(n)
     */
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int max = 0, sum = 0;

        // hashmap 记录路径和与索引的映射关系，当同样的路径和再次出现，则只需要减去之前的索引则能
        // 得到长度
        Map<Integer, Integer> map = new HashMap<>();

        // 首先加入 (0, -1)，包含从开始就匹配的情况
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            // sum += (nums[i] << 1) - 1;
            if (nums[i] == 1) sum++;
            else sum--;

            if (map.containsKey(sum)) max = Math.max(max, i - map.get(sum));
            else map.put(sum, i);
        }

        return max;
    }


    /**
     * 可以使用数组代替 hashmap
     */
    public int findMaxLength1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] arr = new int[2*len + 1];

        Arrays.fill(arr, -2);
        arr[len] = -1;

        int max = 0, count = 0;

        for (int i = 0; i < len; i++) {
            count += (nums[i] << 1) - 1;

            if (arr[count+len] >= -1) {
                max = Math.max(max, i - arr[count+len]);
            } else {
                arr[count+len] = i;
            }
        }

        return max;
    }


    public static void main(String[] args) {
        ContiguousArray solution = new ContiguousArray();

        System.out.println(solution.findMaxLength1(new int[]{0, 0, 1, 0, 0, 1, 1, 1, 0}));
    }
}