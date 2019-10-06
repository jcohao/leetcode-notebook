import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


class FirstMissingPositiveInteger {
    /**
     * 这里用的方法是先把数组装到 Set 里面，然后再从 1 遍历到数组长度
     * 在遍历过程中若数字存在于 Set 中则 continue，如果不存在则立刻返回该数字
     * 该数字则为第一个缺失的正整数
     * 如果全部都遍历完都还没返回，则说明缺失的数字为数组长度+1
     * 这种方法的时间复杂度为 O(n) 
     * 
     * 
     * 另外一种方法则是创建一个临时数组，里面填充了 -1
     * 遍历输入的数组，若数字为正数，则将临时数组的 num-1 位置赋为 1
     * 最后遍历临时数组，当发现有值为负，则返回该索引+1
     * 若全部遍历完成，则返回数组长度+1
     * 但这种方法的时间复杂度也是 O(n)
     */
    public int firstMissingPositive(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            // 可能时间的差距在这吧，Set 里面加元素还得判重
            numsSet.add(num);
        }

        int i;
        for (i = 1; i <= nums.length; i++) {
            if (!numsSet.contains(i)) {
                return i;
            }
        }

        return i;
    }

    // 方法2
    public int firstMissingPositive2(int[] nums) {
        int[] temp = new int[nums.length];
        Arrays.fill(temp, -1);
        for (int num : nums) {
            if (num > 0 && num <= nums.length) {
                temp[num-1] = 1;
            }
        }

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] < 0) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

// 第三种方法是不需要用到临时数组的方法
// 在遍历数组的时候，查看数字是否为正数且小于等于数组长度的数
// 如果是则将它安放在数组中该数字减1的位置
// 最后遍历部分排过序的数组，若该索引位置中的数字不等于索引+1，则返回索引+1
// 否则返回数组长度+1
public int firstMissingPositive3(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        // 这里用的是 while 循环而不是 if 则会将所有改变过的数字都放到正确的位置
        while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i]-1]) {
            int temp = nums[i];
            // 这里nums[i]就变了
            nums[i] = nums[nums[i]-1];
            nums[temp-1] = temp;
        }
    }

   
    System.out.println(Arrays.toString(nums));
    for (int i = 0; i < nums.length; i++) {
        if (i+1 != nums[i]) {
            return i + 1;
        }
    }

    return nums.length + 1;
}


    public static void main(String[] args) {
        int[] nums = {3,4,-1,1};
        FirstMissingPositiveInteger solution = new FirstMissingPositiveInteger();
        System.out.println(solution.firstMissingPositive3(nums));
    }
}