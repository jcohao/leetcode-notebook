import java.util.Arrays;



class SudokuSolver {
    
    /**
     * 没有考虑 3 * 3 的九宫格
     */
    // public void solveSudoku(char[][] board) {
    //     backtrack(board, 0, 0);
    // }

    // private void backtrack(char[][] board, int row, int col) {
    //     if (row == 9) {
    //         return;
    //     } else if (col == 9) {
    //         backtrack(board, row + 1, 0);
    //     } else if (board[row][col] != '.') {
    //         backtrack(board, row, col+1);
    //     } else {
    //         for (char num : chs) {
            
    //             boolean flag = false;
    
    //             for (int r = row; r >= 0; r--) {
    //                 if (board[r][col] == num) {
    //                     flag = true;
    //                     break;
    //                 }
    //             }
    
    //             if (!flag)
    //                 for (int c = col; c >= 0; c--) {
    //                     if (board[row][c] == num) {
    //                         flag = true;
    //                         break;
    //                     }
    //                 }
    
    //             if (flag) {
    //                 continue;
    //             } else {
    //                 board[row][col] = num;
    //                 backtrack(board, row, col+1);
    //                 board[row][col] = '.';
    //             }
    //         }    

    //     }

        
    // }

    /**
     * 这道题的解法也是用了典型的递归回溯
     * 其中用了三个数组来记录行、列以及所处九宫格的数字使用情况
     * 下面这个 GAP 是用来计算数组下标的，比如字符 '9' - GAP = 8
     * 加入第一行已经存了字符 '9' ，则 ROWMEMO[0][8] 就会被设置为 true
     * 而九个 3*3 的九宫格被我顺序分成了 0-8 个位置，遍历到某个坐标
     * 假如是 (4, 8) 则其所处的九宫格为 (4/3)*3+(8/3)=5 不信可以数一下
     * 之后就是开始解题，一开始先遍历整个数组，将已经存在的数字记录到三个记录数组里面
     * 然后进行递归，从 0,0 第一个位置开始
     * 遇到不是 . 的就递归到下一个格子去，是一点的就往里面塞数字
     * 首先要判断这个数字是不是在记录数组里面了，如果是的话就塞下一个数字，
     * 没有在记录数组里面就塞数字，并且往记录数组中记录
     * 当列增加到 9 的时候说明得递归到下一行去了，调用该函数，行别为下一行，列重置
     * 如果是行增加到 9 的时候，则说明数组已经填满了，整个过程都结束了
     * 这里纠结了很久的是返回状况，不能直接 return 直接返回的话会把状态重置，就是说填进去的东西都会被重置
     * 所以要返回一个状态说明整个过程都结束了
     * 这里我们选择返回 true，返回为 true 说明整个过程结束
     * 返回为 false 说明之前填入的数字有误，得重置
     * 这里不用担心的是之前填入的正确的数字就肯定不会改的
     */
    private char[] chs = {
        '1', '2', '3',
        '4', '5', '6',
        '7', '8', '9'
    };
    private int GAP = 49;
    boolean[][] ROWMEMO = new boolean[9][9];
    boolean[][] COLMEMO = new boolean[9][9];
    boolean[][] NINEMEMO = new boolean[9][9];

    public void solveSudoku(char[][] board) {
        // 预处理
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                char number = board[row][col];
                if (number != '.') {
                    ROWMEMO[row][number-GAP] = true;
                    COLMEMO[col][number-GAP] = true;
                    NINEMEMO[(row/3)*3+(col/3)][number-GAP] = true;
                }
            }
        }
        backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int row, int col) {
        if (row == 9) {
            return true;
        } else if (col == 9) {
            return backtrack(board, row+1, 0);
        } else {
            if (board[row][col] != '.') {
                return backtrack(board, row, col+1);
            } else {
                for (char number : chs) {
                    if (ROWMEMO[row][number-GAP] || COLMEMO[col][number-GAP] || NINEMEMO[(row/3)*3+(col/3)][number-GAP]) {
                        continue;
                    } else {
                        ROWMEMO[row][number-GAP] = true;
                        COLMEMO[col][number-GAP] = true;
                        NINEMEMO[(row/3)*3+(col/3)][number-GAP] = true;
                        board[row][col] = number;
                        if (!backtrack(board, row, col+1)) {
                            board[row][col] = '.';
                            ROWMEMO[row][number-GAP] = false;
                            COLMEMO[col][number-GAP] = false;
                            NINEMEMO[(row/3)*3+(col/3)][number-GAP] = false;
                        } else {
                            return true;
                        }
                    }
                }

                return false;
            }
        }

        
    }



    public static void main(String[] args) {
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        SudokuSolver solution = new SudokuSolver();
        solution.solveSudoku(board);
        for (char[] ch : board) {
            System.out.println(Arrays.toString(ch));
        }
        // System.out.println('1' - 49);
    }
}
        
    
