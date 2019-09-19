import java.util.Arrays;

/**
 * 解法为从后往前找找到第一对相邻的升序序列
 * 然后以序列第一个为界，再从后面的序列的找到比界要大的数
 * 然后再将这个数与界交换，最终升序排序界后面的序列
 * 如果找不到相邻的升序序列则说明数组是降序的
 * 此时只需把数组升序排序即可
 * 例子 4, 2, 0, 2, 3, 2, 0
 * 从后往前看，320 里面是无法调整出比它还大的数
 * 而2320 可以调整成 3022 即它的下一个序列
 * 只需找到这个从后往前看降序的第一个数，再从后面中找出比它大的与其替换
 * 然后将该位置后面的数进行升序排列即可得到下个序列
 */
class NextPermutation {
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                for (int j = nums.length - 1; j > i; j--) {
                    if (nums[j] > nums[i]) {
                        int temp = nums[j];
                        nums[j] = nums[i];
                        nums[i] = temp;
                        Arrays.sort(nums, i+1, nums.length);
                        return;
                    }
                }
            }
        }
        Arrays.sort(nums);

    }

    public static void main(String[] args) {
        NextPermutation nextPermutation = new NextPermutation();
        int[] nums = {4,2,0,2,3,2,0};
        nextPermutation.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}