import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N 皇后问题，任意两个皇后横竖斜线都不能相碰
 * 这里的解法是以每一行为单位去回溯
 * 遍历一行中的每一个位置，如果它的竖方向，斜左上方向和斜右上方向都没有棋子，则这个地方是可以放棋子的
 * 进入当下一行去，如果这一行的所有位置都不满足，则说明之前的棋子放错位置了
 * 回溯到上一行去，把之前放的棋子拿走，再尝试下一个位置,
 * 只需要考虑以前放入的棋子的情况，即只需要考虑上方，无需考虑下方，因为下方都还没放
 * 一直这样放直到递归到行数为 n，则说明这是一种正确解法，放入到结果集中
 * 现在以 n = 4 为例子分析，# 表示这些地方都是不能放的
 * 放入第一个棋子：
 *      # Q # #
 *      # # # .
 *      . # . #
 *      . # . .
 * 放入第二个棋子：
 *      # Q # #
 *      # # # Q
 *      . # # #
 *      . # . #
 * 放入第三个棋子：
 *      # Q # #
 *      # # # Q
 *      Q # # #
 *      # # . #
 *  放入最后一个棋子：
 *      # Q # #
 *      # # # Q
 *      Q # # #
 *      # # Q #      
 */
class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] puzzle = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(puzzle[i], '.');
        }
        backtrack(result, puzzle, 0);
        return result;
    }

    private void backtrack(List<List<String>> result, char[][] puzzle, int row) {
        int n = puzzle.length;
        // 正解，加入到结果中
        if (row == n) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                temp.add(new String(puzzle[i]));
            }
            result.add(temp);
            return;
        }

        // 找棋子位置
        for (int col = 0; col < n; col++) {
            // 如果竖、左上斜、右上斜位置都没有棋子，则在该位置放一枚棋子
            // 只需要管上面，不需要管下面

            // 以上方向都没有棋子的标记
            boolean flag = true;

            // 看竖方向有没有棋子
            for (int i = row; i >= 0; i--) {
                if (puzzle[i][col] == 'Q') {
                    flag = false;
                    break;
                }
            }

            // 斜左上方向，如果此时 flag 为 false 的话就可以跳过
            for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
                if (puzzle[i][j] == 'Q') {
                    flag = false;
                    break;
                }
            }

            // 斜右上方，如果此时 flag 为 false 的话就可以跳过
            for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
                if (puzzle[i][j] == 'Q') {
                    flag = false;
                    break;
                }
            }


            if (flag) {
                // 则表示以上方向都没有棋子，放入棋子并向下回溯
                puzzle[row][col] = 'Q';
                backtrack(result, puzzle, row+1);
                // 回来了则说明这个位置不行，得改回来
                puzzle[row][col] = '.';
            } else {
                // 不满足则 continue
                continue;
            }
        }
    }


    /**
     * 另一种做法是用三个布尔数组去记录上述讲过的三个方向是否已经放置过棋子，
     * 这里面左上斜方向是满足方程 row - col = c 右上斜方向满足 row + col = c ，处于同一条斜线上的 c 是一样的
     * 所以打算将这个值存放在布尔数组当中去记录这个方向是否能够放置棋子
     * 因为 row col 的取值范围都是 0 - n-1   所以 row - col 的范围是 -(n-1) - n-1    row + col 的范围是 0 - 2n-2
     * 所以用 N-1+(row-col) 来记录左上斜方向的位置，用 row+col 来记录 右上斜方向的位置 
     */
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] puzzle = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(puzzle[i], '.');
        }
        boolean[] cols = new boolean[n];
        boolean[] index_45 = new boolean[2*n-1];
        boolean[] index_135 = new boolean[2*n-1];
        backtrack(result, puzzle, 0, cols, index_45, index_135);
        return result;
    }


    private void backtrack(List<List<String>> result, char[][] puzzle, int row, boolean[] cols, boolean[] index_45, boolean[] index_135) {
        int N = puzzle.length;
        
        if (row == N) {
            List<String> temp = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                temp.add(new String(puzzle[i]));
            }
            result.add(temp);
            return;
        }

        for (int col = 0; col < N; col++) {
            int pos_45 = row + col;
            int pos_135 = N - 1 + (row - col);

            if (cols[col] || index_45[pos_45] || index_135[pos_135]) {
                continue;
            } else {
                puzzle[row][col] = 'Q';
                cols[col] = index_45[pos_45] = index_135[pos_135] = true;
                backtrack(result, puzzle, row+1, cols, index_45, index_135);
                cols[col] = index_45[pos_45] = index_135[pos_135] = false;
                puzzle[row][col] = '.';
            }
        }

    }


    public static void main(String[] args) {
        NQueens solution = new NQueens();
        List<List<String>> result = solution.solveNQueens2(20);
        System.out.println(result.toString());

    }
}