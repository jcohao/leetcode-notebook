class ContainerWithMostWater {
    /**
     * 左右指针移动，最大面积为 max((right - left) * min(height[left], height[right]), maxArea)
     * 两个指针的值谁小谁就移动，反证：如果是大的移动，本来盛水最多就是由小的决定，移动之后就算高不变，
     * 底边也减小了，所以面积肯定是小了的，所以移动小的
     */
    public int maxArea(int[] height) {
        int result = 0;
        if (height == null || height.length == 0) return result;

        int left = 0, right = height.length - 1;

        while (left < right) {
            // if (height[left] < height[right]) {
            //     result = Math.max(result, (right - left) * height[left]);
            //     left++;
            // } else {
            //     result = Math.max(result, (right - left) * height[right]);
            //     right--;
            // }

            int minHeight = height[left] < height[right] ? height[left++] : height[right--];
            // 这里还没算就移动指针了，所以边长要加 1
            result = Math.max(result, (right - left + 1) * minHeight);
        }

        return result;
    }
}