import java.util.Arrays;

class RemoveDuplicatesFromSortedArray2 {

    /**
     * 遍历数组，每个位置都往前找其相同的数字，这里用到了另外一个指针去找相同的数字
     * 直到找到相同数字的右边界，题目要求是相同的数字不超过两个，所以这里的长度要减去 j - i - 1  
     * 例子中相应的是减一，找右边界的时候记得判断是否越过了数组，即 j+1 < nums.length
     * 然后将左右边界中间的值设置为 Max
     * 后面经过排序 Max 值就会被排到后面，检查的时候因为是用返回的长度去检查数组的，所以就忽略掉 Max 值了
     * i   j
     * 1 1 1 2 2
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        else if (nums.length < 3) return nums.length;

        int devide = 0;

        for (int i = 0; i < nums.length-1; i++) {
            int j = i;
            while (j+1 < nums.length && nums[j+1] == nums[i]) {
                j++;
                // 这里可以从指针移动的同时去设置值
                nums[j] = Integer.MAX_VALUE;
            }



            if (j == i) {
                continue;
            } else {
                // 右边界的值被改了，要设回来
                nums[j] = nums[i];
                devide += j - i - 1;
                // 下一次遍历就是 i 的下一位了
                i = j;
            }

            

            // for (int k = i+1; k < j; k++) {
            //     nums[k] = Integer.MAX_VALUE;
            // }
            
        }

        Arrays.sort(nums);

        return nums.length - devide;
    }


    // 只需要保证重复元素只有两个即可，i 相当于左指针，num 相当于右指针
    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num > nums[i-2]) {
                nums[i++] = num;
            }
        }

        return i;
    }


    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray2 solution = new RemoveDuplicatesFromSortedArray2();
        int[] nums = {1,1,1,2,2,3,3,3};
        int len = solution.removeDuplicates2(nums);
        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }
    }
}