class SearchA2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        return helper(matrix, target, 0, matrix.length, 0, matrix[0].length);   
    }

    private boolean helper(int[][] matrix, int target, int mStart, int mEnd, int nStart, int nEnd) {
        if (mStart >= mEnd || nStart >= nEnd || matrix[mEnd-1][nEnd-1] < target) return false;

        int newMEnd = mEnd, newNEnd = nEnd;

        for (int i = nStart; i < nEnd; i++) {
            if (matrix[mStart][i] == target) {
                return true;
            } else if (matrix[mStart][i] > target) {
                newNEnd = i;
                break;
            }
        }

        for (int i = mStart; i < mEnd; i++) {
            if (matrix[i][nStart] == target) {
                return true;
            } else if (matrix[i][nStart] > target) {
                newMEnd = i;
                break;
            }
        }

        
        return helper(matrix, target, mStart+1, newMEnd, nStart+1, newNEnd);
    }


    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[matrix.length-1][matrix[0].length-1] < target
            || matrix[0][0] > target) return false;

        int m = 0, n = matrix[0].length-1;

        while (m < matrix.length && n >= 0) {
            if (matrix[m][n] == target) return true;
            else if (matrix[m][n] > target) n--;
            else m++;
        }

        return false;
    }


    public static void main(String[] args) {
        SearchA2DMatrixII solution = new SearchA2DMatrixII();

        int[][] matrix = {
            {1},
            {3},
            {5}
        };

        solution.searchMatrix(matrix, 4);
    }
}