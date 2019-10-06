import java.util.Arrays;


class RotateImage {
    /**
     * 这种方法借助了临时数组的帮助，而题目是不允许这样的
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = matrix[n-j-1][i];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }


    /**
     * [1,2,3]              [7,4,1]
     * [4,5,6]   转为       [8,5,2]
     * [7,8,9]              [9,6,3]
     * 
     * 以 5 为中心，外圈为一次转换的基本单位，可以看到 1,3,9,7 按顺时针的方向依次换位
     * 而 2,6,8,4 也是一样
     * 所以外循环是 0 到 length/2 从外圈向内圈遍历
     * 内循环则是对应的四个变量依次换位，找到这四个变量的索引变化则是问题的关键
     * 
     * (i, j)          (j, n-i-1)
     * 
     * (n-j-1, i)      (n-i-1, n-j-1)
     * 
     */
    public void rotate2(int[][] matrix) {
        if (matrix == null || matrix.length < 2) {
            return;
        }

        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            // 一行里面，前 n-1 个元素都需要旋转
            for (int j = i; j < n-i-1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-i-1][n-1-j];
                matrix[n-i-1][n-1-j] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }
    }


    public static void main(String[] args) {
        RotateImage solution = new RotateImage();
        int[][] matrix = {
            {5, 1, 9,11},
            {2, 4, 8,10},
            {13, 3, 6, 7},
            {15,14,12,16}
        };

        solution.rotate2(matrix);
        for (int[] i : matrix) {
            System.out.println(Arrays.toString(i));
        }
        
    }
}