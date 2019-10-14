class NQueens2 {
    private int result = 0;
    public int totalNQueens(int n) {
        // Integer 内部存储的是 final 变量，一旦创建则不会变，Integer++ 则会创建副本
        // Integer result = 0;
        backtrack(n, 0, new boolean[n], new boolean[2*n-1], new boolean[2*n-1]);
        return result;
    }
    
    
    private void backtrack(int n, int row, boolean[] cols, boolean[] index_45, boolean[] index_135) {
        if (row == n) {
            result++; 
            return;
        }
        
        for (int col = 0; col < n; col++) {
            int pos_45 = row + col;
            int pos_135 = n - 1 + (row - col);
            
            if (cols[col] || index_45[pos_45] || index_135[pos_135]) {
                continue;
            } else {
                cols[col] = index_45[pos_45] = index_135[pos_135] = true;
                backtrack(n, row+1, cols, index_45, index_135);
                cols[col] = index_45[pos_45] = index_135[pos_135] = false;
            }
            
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.totalNQueens(4));
    }
}