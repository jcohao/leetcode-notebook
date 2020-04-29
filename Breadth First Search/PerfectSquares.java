import java.util.Arrays;

class PerfectSquares {
    // TLE
    int min = Integer.MAX_VALUE;
    public int numSquares1(int n) {
        findSquare(n, 0);
        return min;
    }
    
    private void findSquare(int n, int level) {
        if (n == 0) {
            min = Math.min(min, level);
            return;
        } else if (n < 0 || level > min) {
            return;
        }
        
        for (int j = (int) Math.sqrt(n); j >= 1; j--) {
            findSquare(n-j*j, level+1);
        }
    }

    public int numSquares(int n) {
        if (n <= 0) return 0;

        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        PerfectSquares solution = new PerfectSquares();
        System.out.println(solution.numSquares(7654));
    }
}