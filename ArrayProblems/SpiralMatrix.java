import java.util.ArrayList;
import java.util.List;

class SpiralMatrix {
    /**
     * 第一种方法是制定边界条件，顺着边界去遍历数组
     * 记录四个边界条件，首先从左往右遍历，遍历完之后上界加一，
     * 然后从上往下遍历，遍历完后右界减一，
     * 因为上面上界和右界都变化了，接下来的操作涉及下界和左界，需要判断条件，
     * 接着是从右往左遍历，遍历完后下界减一，
     * 最后则是从下往上遍历，遍历完后左界减一，
     * 如果边界还满足条件则继续循环
     * 
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0)
            return result;

        int beginRow = 0, beginCol = 0;
        int endRow = matrix.length - 1, endCol = matrix[0].length - 1;

        while (beginRow <= endRow && beginCol <= endCol) {
            for (int i = beginCol; i <= endCol; i++) {
                result.add(matrix[beginRow][i]);
            }
            beginRow++;

            for (int i = beginRow; i <= endRow; i++) {
                result.add(matrix[i][endCol]);
            }
            endCol--;

            // 上面边界变化了，需要重新判定
            if (beginCol < endCol && beginRow < endRow) {
                for (int i = endCol; i >= beginCol; i--) {
                    result.add(matrix[endRow][i]);
                }
                endRow--;

                for (int i = endRow; i >= beginRow; i--) {
                    result.add(matrix[i][beginCol]);
                }
                beginCol++;
            }
        }

        return result;
    }

    /**
     * 另外一种方法是利用标记的方法，用一个二维数组去记录哪些节点是已经访问过的
     * 用向量去记录行走的方向，(a, b) a 表示上（-1）下（1）b 表示左（-1）右（1）
     * 
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0) 
            return result;

        int Row = matrix.length, Col = matrix[0].length;
        int r = 0, c = 0;
        // 方向向量
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        // di 是决定方向的变量
        int di = 0;

        boolean[][] memo = new boolean[Row][Col];

        for (int i = 0; i < Row * Col; i++) {
            result.add(matrix[r][c]);
            memo[r][c] = true;

            // 下一个位置
            int cr = r + dr[di];
            int cc = c + dc[di];

            if (cr >= 0 && cr < Row && cc >= 0 && cc < Col && !memo[cr][cc]) {
                r = cr;
                c = cc; 
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }

        return result;
    }
    


    public static void main(String[] args) {
        SpiralMatrix solution = new SpiralMatrix();

        int[][] matrix = {
            {1,2,3},
            {5,6,7},
            {9,10,11}
        };

        System.out.println(solution.spiralOrder2(matrix).toString());
    }
}