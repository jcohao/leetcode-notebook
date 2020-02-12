import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class NumberofPathswithMaxScore {
    int pathMax = 0;
    int pathNum = 0;

    int mod = 1_000_000_000 + 7;
    /**
     * 每条路都走一遍确实可以，就是时间复杂度太高了，过不了
     */
    public int[] pathsWithMaxScore1(List<String> board) {
        int row = board.size()-1, col = board.get(0).length()-1;
        
        helper(board, row-1, col, 0);
        helper(board, row, col-1, 0);
        helper(board, row-1, col-1, 0);

        int[] results = {pathMax, pathNum};
        return results;

    }

    private void helper(List<String> board, int row, int col, int score) {
        if (row == 0 && col == 0) {
            if (score > pathMax) {
                pathMax = score;
                pathNum = 1;
            } else if (score == pathMax) {
                pathNum++;
            }
        } else {
            if (board.get(row).charAt(col) == 'X') return;
            score += board.get(row).charAt(col) - '0';
            score %= mod;
            if (row == 0) {
                helper(board, row, col-1, score);
            } else if (col == 0) {
                helper(board, row-1, col, score);
            } else {
                if (board.get(row-1).charAt(col) == 'X' && board.get(row).charAt(col-1) == 'X') {
                    helper(board, row-1, col-1, score);
                } else {
                    helper(board, row-1, col, score);
                    helper(board, row, col-1, score);
                }
            }
        }
    }

    public int[] pathsWithMaxScore(List<String> board) {
        int row = board.size(), col = board.get(0).length();

        int[][][] dp = new int[row][col][2];
        int[] start = {0, 1};
        dp[row-1][col-1] = start;

        for (int i = row-2; i >= 0; i--) {
            char ch = board.get(i).charAt(row-1);
            if (ch == 'X') break;
            dp[i][col-1][0] = ch - '0' + dp[i+1][col-1][0];
            dp[i][col-1][1] = 1;
        }

        for (int i = col-2; i >= 0; i--) {
            char ch = board.get(row-1).charAt(i);
            if (ch == 'X') break;
            dp[row-1][i][0] = ch - '0' + dp[row-1][i+1][0];
            dp[row-1][i][1] = 1;
        }

        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};

        for (int i = row-2; i >= 0; i--) {
            for (int j = col-2; j >= 0; j--) {
                char ch = board.get(i).charAt(j);
                if (ch == 'X') continue;
                if (ch == 'E') ch = '0';
                int pathMax = 0;
                int pathSum = 0;

                for (int[] dir : dirs) {
                    int r = i + dir[0];
                    int c = j + dir[1];

                    if (dp[r][c][0] > pathMax) {
                        pathMax = dp[r][c][0];
                        pathSum = dp[r][c][1];
                    } else if (dp[r][c][0] == pathMax) {
                        pathSum += dp[r][c][1];
                    }
                }

                
                if (pathSum != 0)
                    dp[i][j][0] = (pathMax + ch - '0') % mod;
                    dp[i][j][1] = pathSum % mod;
            
            }
        }

        int[] result = {dp[0][0][0], dp[0][0][1]};

        return result;
    }

    public static void main(String[] args) {
        NumberofPathswithMaxScore solution = new NumberofPathswithMaxScore();
        List<String> board = new ArrayList<>();
        // board.add("E11");
        // board.add("XXX");
        // board.add("11S");
        // board.add("EX");
        // board.add("XS");
        board.add("E11345");
        board.add("X452XX");
        board.add("3X43X4");
        board.add("44X312");
        board.add("23452X");
        board.add("1342XS");
        int[] result = solution.pathsWithMaxScore(board);
        System.out.println(Arrays.toString(result));
    }
}