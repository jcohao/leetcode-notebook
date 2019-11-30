import java.util.Arrays;

class WiggleSort2 {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2) return;

        Arrays.sort(nums);
        int n = nums.length, j = (n + 1) / 2, k = n;

        int[] temp = Arrays.copyOf(nums, n);

        for (int i = 0; i < n; i++) {
            nums[i] = i % 2 == 0 ? temp[--j] : temp[--k];
        }
    }


    public static void main(String[] args) {
        WiggleSort2 solution = new WiggleSort2();

        int[] nums = {1,2,2,1,2,1,1,1,1,2,2,2};
        solution.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}