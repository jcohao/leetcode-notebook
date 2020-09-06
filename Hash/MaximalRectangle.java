import java.util.Arrays;

class MaximalRectangle {
    /**
     * 第一种方法为 DP 解法，也不晓得为啥属于 DP
     * 每遍历一行都维护 3 个数组：
     * heights: 想象下直方图，当前行做成直方图的高度，遇到 0 则为 0
     * lefts: 连续为 1 的左边界，遇到 0 肯定也为 0，反正高为 0 不管咋搞这一格最后的结果都是 0
     * rights: 连续为 1 的右边界+1，方便 right - left 即为长，所以+1，但rights[c] 的值还受上一行的影响，
     *         如果上一行的值比现在算的要小的话，则使用上一行的值，猜测可能是为了计算这一列的面积
     *         结合左右一起看的话好像是这么回事
     *         遇上 0 的话则为 n，也是为啥都行，反正高为 0
     * 
     * 每遍历完一行都计算 res = (rights[c] - lefts[c]) * heights[c]
     * 
     * 
     * 
     * heights:
     * [1, 0, 1, 0, 0]
     * [2, 0, 2, 1, 1]
     * [3, 1, 3, 2, 2]
     * [4, 0, 0, 3, 0]
     * 
     * lefts:
     * [0, 0, 2, 0, 0]
     * [0, 0, 2, 2, 2]
     * [0, 0, 2, 2, 2]
     * [0, 0, 0, 3, 0]
     * 
     * rights:
     * [1, 5, 3, 5, 5]
     * [1, 5, 3, 5, 5]
     * [1, 5, 3, 5, 5]
     * [1, 5, 5, 4, 5]
     */
    public int maximalRectangle(char[][] matrix) {
        // 行数
        int m = matrix.length;
        if (matrix == null || m == 0) return 0;
        // 列数
        int n = matrix[0].length;

        int[] heights = new int[n];
        int[] lefts = new int[n];
        int[] rights = new int[n];
        // rights 用 n 来填充
        Arrays.fill(rights, n);

        int result = 0;

        for (int r = 0; r < m; r++) {
            // 构建直方图的高度
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == '0') heights[c] = 0;
                else heights[c] += 1;
            }
            System.out.println("heights: " + Arrays.toString(heights));

            // 左边界
            int leftCur = 0;
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] == '1') {
                    // 上一层的值会对这一层的值有限制
                    lefts[c] = Math.max(lefts[c], leftCur);
                } else {
                    lefts[c] = 0;
                    leftCur = c + 1;
                }
            }
            System.out.println("lefts: " + Arrays.toString(lefts));

            // 右边界
            int rightCur = n;
            for (int c = n - 1; c >= 0; c--) {
                if (matrix[r][c] == '1') {
                    rights[c] = Math.min(rights[c], rightCur);
                } else {
                    rights[c] = n;
                    rightCur = c;
                }
                result = Math.max(result, (rights[c] - lefts[c]) * heights[c]);
            }

            System.out.println("rights: " + Arrays.toString(rights));
            
        }

        return result;
        
    }

    public static void main(String[] args) {
        MaximalRectangle solution = new MaximalRectangle();
        char[][] matrix = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        System.out.println(solution.maximalRectangle(matrix)); 
    }
}