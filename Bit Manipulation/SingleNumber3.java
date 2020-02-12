import java.util.Arrays;

class SingleNumber3 {
    /**
     * 大体的方法是将数组中所有数字进行异或操作
     * 得到的结果取其中为 1 的一位，这一位里，答案中的两个数字肯定是不同取值的
     * 这一位取刚刚得到的结果的最右边为 1 的一位，通过 a & -a 得到
     * 负数为补码，即反码加一
     * 然后根据每一位数跟这一位相与，根据得到的结果不同将数字分到两个数组中
     * 这两个数将分别分到不同的数组中
     * 然后每个数组中的数字进行异或操作，最后得出的结果则是 single number
     */
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) return new int[2];
        int len = nums.length;

        int result = findSingleNumber(nums);

        int a = result & -result;

        int[] list1 = new int[len];
        int[] list2 = new int[len];
        for (int i = 0; i < len; i++) {
            if ((nums[i] & a) == 0) {
                list1[i] = nums[i];
            } else {
                list2[i] = nums[i];
            }
        }

        int[] results = {findSingleNumber(list1), findSingleNumber(list2)};
        return results;
    }

    private int findSingleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    public static void main(String[] args) {
        SingleNumber3 solution = new SingleNumber3();

        int[] nums = {1, 2, 1, 3, 2, 5};

        System.out.println(Arrays.toString(solution.singleNumber(nums)));
    }
}