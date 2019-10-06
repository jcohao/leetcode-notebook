import java.util.Arrays;

class TrapingRainWater {
    /**
     * 大体的方法是每一次让数组的每个元素-1
     * 然后每两个非负数之间有多少个负数，则能积多少格雨水
     * 这里减了多少次 1 则由数组中最大的元素决定
     * 但是太慢了
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int total = 0;
        int max = Arrays.stream(height).max().getAsInt();
        for (int i = 0; i < max; i++) {
            reduce(height, 1);
            boolean flag = false;
            int temp = 0;
            for (int j = 0; j < height.length; j++) { 
                if (height[j] < 0) {
                    temp++;
                } else {
                    if (flag) {
                        total += temp;
                    }
                    temp = 0;
                    flag = true;
                }
            }
        }

        return total;
    }


    /**
     * 存水的地方肯定是出现在一段降序序列接一段升序序列之间
     * 而这一整段序列存水量的多少为序列中第二大的数减去除最大数之外的其他数的差，然后求和
     * 而这个最大数要不就出现在这段序列的头，要不就出现在尾部
     * 
     * 这种方法不行，如果中间有较小的升降序列就是错的
     * [5,2,1,2,1,5]
     */
    public int trap2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int total = 0;
        boolean desc = false, asc = false;
        int head = -1, tail = -1;
        int pre = height[0];
        for (int i = 1; i < height.length; i++) {
            // 递减序列
            if (height[i] < pre) {
                if (asc) {
                    // 由递增变为递减序列，而且不是开始那段，则完成一整段递减接递增序列的记录
                    if (head != -1) {
                        tail = i - 1;
                        int max = (height[head] > height[tail]) ? height[tail] : height[head];
                        for (int j = head + 1; j < tail; j++) {
                            if (max > height[j])
                                total += (max - height[j]);
                        }
                    }
                }

                if (!desc)
                    head = i - 1;
                desc = true;
                asc = false;
            } else if (height[i] > pre) {
                // 递增序列
                desc = false;
                asc = true;
            } 
            pre = height[i];
            
        }


        // 最后一段为递增序列
        if (asc && head != -1) {
            tail = height.length - 1;
            int max = (height[head] > height[tail]) ? height[tail] : height[head];
            for (int j = head + 1; j < tail; j++) {
                if (max > height[j])
                    total += (max - height[j]);
            }
        }

        return total;

    }

    private void reduce(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] -= k;
        }
    }

    /**
     * 找出每一格左边最大的以及右边最大的，又这二者的较小者决定这一格能容纳多少水量
     * 顺序遍历一次数组，找出每一格左边最大的元素，存到数组里
     * 逆序遍历一次数组，找出每一格右边最大的元素，存到数组里
     * 然后遍历数组，计算存水量
     * 时间复杂度和空间复杂度都为 O(n) 
     */
    public int trap3(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        int total = 0;
        int max = height[0];

        for (int i = 1; i < height.length - 1; i++) {
            max = Math.max(max, height[i]);
            leftMax[i] = max;
        }

        max = height[height.length-1];
        for (int j = height.length - 1; j > 0; j--) {
            max = Math.max(max, height[j]);
            rightMax[j] = max;
        }

        int less;
        for (int k = 1; k < height.length - 1; k++) {
            less = Math.min(leftMax[k], rightMax[k]);
            if (less >= height[k]) {
                total += less - height[k];
            }
        }

        return total;

    }

    /**
     * 另一种减少空间复杂度的方法是：
     * 左右指针一头一尾遍历数组，左右又有一个记录左右边最大值的指针
     * 当左边最大值小于右边最大值时，左指针指向的格子的存水量可以确定
     * 当右边最大值小于左边最大值时，右指针指向的格子的存水量可以确定
     * 当左右指针相遇，遍历结束
     */
    public int trap4(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }

        int total = 0;
        int leftMost = 0, rightMost = 0;
        int left = 0, right = height.length - 1;

        while (left < right) {
            leftMost = Math.max(leftMost, height[left]);
            rightMost = Math.max(rightMost, height[right]);

            if (leftMost <= rightMost) {
                total += leftMost - height[left];
                left++;
            } else {
                total += rightMost - height[right];
                right--;
            }
        }

        return total;
    }

    public static void main(String[] args) {
        int[] nums = {5,2,1,2,1,5};
        TrapingRainWater solution = new TrapingRainWater();
        
        System.out.println(solution.trap4(nums));

    }
}