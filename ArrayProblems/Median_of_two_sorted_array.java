class Solution {
    /**
     * 如果将两个有序数组先合并到一起再去找中位数的话
     * 虽然简单，但是不满足题目所要求的时间复杂度为O(log(m + n))
     * 而且空间复杂度也比较大
     */
    // public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    //     int[] result = new int[nums1.length + nums2.length];
    //     if (nums1.length == 0) {
    //         result = nums2;
    //     } else if (nums2.length == 0) {
    //         result = nums1;
    //     } else {
    //         int cur1 = 0;
    //         int cur2 = 0;
    //         int count = 0;

    //         while (cur1 < nums1.length && cur2 < nums2.length) {
    //             if (nums1[cur1] <= nums2[cur2]) {
    //                 result[count++] = nums1[cur1++];
    //             } else {
    //                 result[count++] = nums2[cur2++];
    //             }
    //         }

    //         while (cur1 < nums1.length) {
    //             result[count++] = nums1[cur1++];
    //         }

    //         while (cur2 < nums2.length) {
    //             result[count++] = nums2[cur2++];
    //         }
    //     }
    

    //     if (result.length % 2 == 0) {
    //         return (result[result.length/2] + result[result.length/2 - 1])/2;
    //     } else {
    //         return result[result.length/2];
    //     }
    // }

    /**
     * 若时间复杂度要求为 O(log(m+n)) 则应该想到二分查找，那么题目就相当于在两个有序数组中进行二分查找了
     * 
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int left = (m + n + 1)/2, right = (m + n + 2)/2;
        // 这样每次都找中间的那两个数就不用区分奇偶数情况了，奇数情况下left和right是同一个数
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
        
    }

    // 在两个有序数组中找第 K 个元素,i, j 是记录两个数组的起始位置的
    // 这里的二分是对 k 进行二分，分别在两个数组中找 k/2 个元素
    private int findKth(int[] nums1, int i, int[] nums2, int j, int k) {

        if (i >= nums1.length) return nums2[j + k - 1];
        // 当 i 超过数组1长度时，第k个肯定就在第2个数组里面
        if (j >= nums2.length) return nums1[i + k - 1];

        // 当k等于1的时候，直接返回两个数组第一个间较小的一个
        if (k == 1) return Math.min(nums1[i], nums2[j]);
        
        // 可能有数组不存在第 k/2 个元素，不存在则给它赋一个最大值
        int midVal1 = (i + k/2 - 1 < nums1.length) ? nums1[i + k/2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k/2 - 1 < nums2.length) ? nums2[j + k/2 - 1] : Integer.MAX_VALUE;

        if (midVal1 < midVal2) {
            // 若第一个数组的 k/2 较小，则需要找的数肯定不在 nums1 的前 k/2 个元素中
            // 所以越过 nums1 的前 k/2 个元素
            return findKth(nums1, i + k/2, nums2, j, k - k/2);
        } else {
            return findKth(nums1, i, nums2, j + k/2, k - k/2);
        }
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};

        System.out.println(solution.findMedianSortedArrays(nums1, nums2));
    }

}

