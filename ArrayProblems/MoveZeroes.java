class MoveZeroes {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        int count = 0;
        for (int i = 0; i < len - count; i++) {
            if (nums[i] == 0) {
                for (int j = i + 1; j < len - count; j++) {
                    nums[j-1] = nums[j];
                }
                nums[len - 1 - count] = 0;
                count++;
            }
            if (nums[i] == 0) {
                i--;
            }
        }
    }

    /**
     * 设置快慢指针，遇到 0 的时候慢指针就不动，这样每次拷贝就能把 0
     * 的位置覆盖过去
     */
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }

        for (; j < nums.length; j++) {
            nums[j] = 0;
        }
    }

    public void moveZeroes3(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int j = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                nums[i] = 0;
                j++;
            }
        }
    }


    /**
     * 美女 olsh 的滚雪球方法，此方法最快，交换次数最少
     */
    public void moveZeroes4(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int snowball = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                snowball++;
            } else if (snowball > 0) {
                int temp = nums[i];
                nums[i] = nums[i - snowball];
                nums[i - snowball] = temp;
            }
        }
    }

    public static void main(String[] args) {
        MoveZeroes solution = new MoveZeroes();
        int[] nums = {0, 1, 0, 3, 12};
        solution.moveZeroes4(nums);
        for (int i : nums) {
            System.out.println(i);
        }
    }
}