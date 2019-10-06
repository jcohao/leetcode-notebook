public class SearchinRotatedSortedArray {

    /**
     * 但这种方法好像也没有达到题目的要求，题目要求的是时间复杂度为log(n)
     * 我这里查找旋转的地方就n了，虽然二分法是log(n),但总的时间复杂度还是 n
     * 
     */
    // public int search(int[] nums, int target) {
    //     if(nums.length == 0 || (nums.length == 1 && nums[0] != target)) {
    //         return -1;
    //     } else if (nums.length == 1 && nums[0] == target) {
    //         return 0;
    //     }

    //     int cur = 0;
    //     for (int i = 0; i < nums.length - 1; i++) {
    //         if (nums[i] > nums[i+1]) {
    //             cur = i;
    //             break;
    //         }
    //     }

    //     if (cur == 0) {
    //         return binarySearch(nums, 0, nums.length-1, target);
    //     } else {
    //         return Math.max(binarySearch(nums, 0, cur, target), binarySearch(nums, cur+1, nums.length-1, target));
    //     }
        
    // }

    // private int binarySearch(int[] nums, int start, int end, int target) {
    //     int middle = (start + end) / 2;
    //     if (start > end) {
    //         return -1;
    //     } else if (nums[start] == target) {
    //         return start;
    //     } else if (nums[middle] == target) {
    //         return middle;
    //     } else if (nums[end] == target) {
    //         return end;
    //     } else if (nums[middle] > target) {
    //         return binarySearch(nums, start, middle - 1, target);
    //     } else {
    //         return binarySearch(nums, middle + 1, end, target);
    //     }
    // }

    /**
     * 以 [0,1,2,4,5,6,7] 为例，这个数组不管怎么旋转，它有一半始终是有序的
     * 在这一半有序的情况下，如果target是落到了这个区间，则正常使用二分查找即可
     * 如果不是落入这个区间，则另外一半也按照二分查找处理，只是这一半就相当于原来旋转后的数组的情况了
     * 如何判断左边还是右边是正常的递增序列呢？
     * 用中间值与最右边的值比较即可，如果中间值大于最右边的值，则左半边是递增的
     * 如果中间值小于最右边的值，则右半边是递增的
     * 
     * 而二分法则是判断选择左半边还是右半边的情况
     * 当哪一边是递增的且target落入到那一边时，则选择该一半数组
     * 如果不是以上情况的话则选择另外一半
     * 
     * 那为什么是选择中间值与最右边值比较而不是选择中间值与最左边的值比较呢？
     * 当出现两个数字的情况下如 [3,1]，1 mid 和 left会重合，此时target又不等于mid上的值
     * 这时候就会落在右边，但是此时会不满足target落在右边的情况
     * 
     * 但是如果修改循环判断条件 start + 1 < end ，让start和end无法重叠，就是到了只剩两个数字的情况，最后判断是不是在边值上
     * 就可以用判断左边的方法了
     * 
     */

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int res = -1;
        int start = 0, end = nums.length - 1;

        while (start + 1 < end) {
            int middle = start + (end - start) / 2; // 这种写法可以避免 overflow 的情况 (start + end) 有可能overflow
            if (nums[middle] == target) {
                res = middle;
                break;
            }

            // if (nums[middle] > nums[end]) {
            //     if (nums[middle] > target && nums[start] <= target) {
            //         end = middle - 1;
            //     } else {
            //         start = middle + 1;
            //     }
            // } else {
            //     if (nums[middle] < target && nums[end] >= target) {
            //         start = middle + 1;
            //     } else {
            //         end = middle - 1;
            //     }
            // }

            if (nums[middle] > nums[start]) {
                if (nums[middle] > target && nums[start] <= target) {
                    end = middle - 1;
                } else {
                    start = middle + 1;
                } 
            } else {
                if (nums[middle] < target && nums[end] >= target) {
                    start = middle + 1;
                } else {
                    end = middle - 1;
                }
            }
        }
        System.out.print("start: " + start);
        System.out.println(" end: " + end);
        if (target == nums[start]) return start;
        else if (target == nums[end]) return end;
        else return res;

        // return res;
    }



    public static void main(String[] args) {
        int[] nums = {3,1};
        SearchinRotatedSortedArray s = new SearchinRotatedSortedArray();
        System.out.println(s.search(nums, 1));
    }


}