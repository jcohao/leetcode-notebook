class WordSearch {

    /**
     * 我的想法是位置记录加回溯去解题
     * 首先找到第一个匹配的字符位置，开始回溯
     * 从该位置往四个方向去走，注意不能超出边界条件，而且所走的路不能是访问过的
     * 如果该方向走不通则返回到原来位置继续往剩下的方向去走
     */
    public boolean exist(char[][] board, String word) {
        int R = board.length, C = board[0].length;
        // 用于记录哪些位置是访问过的
        boolean memo[][] = new boolean[R][C];
        boolean flag = false;

        // 先找出第一个匹配的字母
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (word.charAt(0) == board[i][j]) {
                    memo[i][j] = true;
                    flag = backtrack(board, board[i][j] + "", memo, word, i, j);
                    memo[i][j] = false;
                }

                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, String subStr, boolean[][] memo, String target, int r, int c) {
        // 只要有一个字符不匹配则返回 false
        if (subStr.length() > target.length() || 
             !subStr.equals(target.substring(0, subStr.length()))) {
            return false;
        } else if (subStr.equals(target)) {
            return true;
        } 

        // 方向向量
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};


        // 往上下左右四个方向遍历，遍历到一个位置则记录下该位置已遍历
        // 如果回溯返回为 false 说明该方向走不通，刚刚那个位置返回到未访问状态
        // 坐标回到访问前的状态
        for (int i = 0; i < 4; i++) {
            r += dr[i];
            c += dc[i];
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && !memo[r][c]) {
                memo[r][c] = true;
                boolean flag = backtrack(board, subStr + board[r][c], memo, target, r, c);
                if (flag) {
                    return true;
                } else {
                    memo[r][c] = false;
                }
            }
            r -= dr[i];
            c -= dc[i];
        }
        
        return false;
    }


    public static void main(String[] args) {
        WordSearch solution = new WordSearch();
        char[][] board = {
            {'A','B','C','E'},
            {'A','F','C','S'},
            {'A','D','E','E'}
        };

        System.out.println(solution.exist(board, "ABCD"));
    }
}