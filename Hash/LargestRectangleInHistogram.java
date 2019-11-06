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
     * 比如 2 4 6 递增序列，就没必要从 2 或 4 的时候开始算，因为 2 或 4 开始算会得到 2 4 4 三种可能面积，而从 6 开始算则是 6 8 6 肯定比 2 4 4 要大
     * 而递减序列为什么不要算，比如 1 6 5 1 2 3, 其实遇到下一个是大于现在这个值的都要执行以上的操作，
     * 跟递增递减好像没什么关系
     */
    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int maxArea = 0;

        for (int cur = 0; cur < heights.length; cur++) {
            if (cur == heights.length - 1 || heights[cur] > heights[cur+1]) {
                int minHeight = heights[cur];

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

        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            // 栈为空或者当前遍历的值大于栈顶所指元素的值则将当前遍历的索引入栈
            if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
                stack.add(i);
            } else {
                // 当前遍历值是大于栈顶所指元素的值，则找到右边界，右边界则是当前遍历的值
                int rightBorder = i;
                // 弹出来的是要处理位置所对应的高度
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
        System.out.println(solution.largestRectangleArea2(heights));
    }
}