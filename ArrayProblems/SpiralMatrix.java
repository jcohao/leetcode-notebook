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
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
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


    public static void main(String[] args) {
        SpiralMatrix solution = new SpiralMatrix();

        int[][] matrix = {
            {1,2,3},
            {5,6,7},
            {9,10,11}
        };

        System.out.println(solution.spiralOrder(matrix).toString());
    }
}