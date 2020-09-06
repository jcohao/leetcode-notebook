import java.util.ArrayDeque;
import java.util.Stack;

class LargestRectangleInHistogram {
    /**
     * 爆破会超时
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (heights == null || n == 0) return 0;

        int maxArea = 0;
       
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int area = (j - i + 1) * findMin(heights, i, j);
                if (area > maxArea) maxArea = area;
            }
        }

        return maxArea;
    }

    private int findMin(int[] heights, int left, int right) {
        int min = heights[left];
        while (left <= right) {
            if (heights[left] < min) min = heights[left];
            left++;
        }

        return min; 
    }

    /**
     * 遍历整个数组，当遇到的是递增序列的时候，这个序列的右边界作为起点，往前去计算面积，最后一个数组元素也是如此操作
     * 这里的右边界为一个局部峰值，在局部峰值之前的情况已经被局部峰值的情况所包括，不用重复去计算
     *      2,1,5,6,2,3
     * 
     *      1 5 6 这里已经包含了 1 5 的情况了
     */
    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int maxArea = 0;

        for (int cur = 0; cur < heights.length; cur++) {
            // 找到递增序列的最高点
            if (cur == heights.length - 1 || heights[cur] > heights[cur+1]) {
                int minHeight = heights[cur];
                // 然后往前计算最大的面积
                for (int i = cur; i >= 0; i--) {
                    minHeight = Math.min(minHeight, heights[i]);
                    maxArea = Math.max(maxArea, minHeight * (cur - i + 1));
                }
            }
        }

        return maxArea;
    }

    /**
     * 下一种方法是利用栈，寻找某一个高度比它矮的左右边界，然后计算这个高度的最大面积
     */
    public int largestRectangleArea3(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        // Stack<Integer> stack = new Stack<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            // 栈为空或者当前遍历的值大于栈顶所指元素的值则将当前遍历的索引入栈
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.add(i);
            } else {
                // 当前遍历值是小于栈顶所指元素的值，则找到右边界，右边界则是当前遍历的值
                int rightBorder = i;
                // 弹出来的是要处理位置所对应的高度的索引
                int cur = stack.pop();
                // 弹出所有相等的值，因为同样高度相连的值只需要处理一个即可
                while (!stack.isEmpty() && heights[cur] == heights[stack.peek()]) {
                    stack.pop();
                }
                // 栈若为空的话则将左边界设为 -1，否则是栈顶元素
                int leftBorder = (stack.isEmpty()) ? -1 : stack.peek();
                // 此时可以得到 cur 所代表的高度在左右边界之间的最大面积，因为是不包含左右边界的，所以要额外减 1
                maxArea = Math.max(maxArea, heights[cur] * (rightBorder - leftBorder - 1));
                // 当前这个 i 值还没有处理，所以要退回去
                i--;
            }
        }
        // 栈里面剩下的元素也得处理，此时右边界就固定了
        int rightBorder = stack.peek()+1;
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int leftBorder = (stack.isEmpty()) ? -1 : stack.peek();
            maxArea = Math.max(maxArea, heights[cur] * (rightBorder - leftBorder - 1));
        }

        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleInHistogram solution = new LargestRectangleInHistogram();
        int[] heights = {2, 1, 1, 1, 1, 1, 5};
        System.out.println(solution.largestRectangleArea3(heights));
    }
}