import java.util.Arrays;

class CoinChange {
    public int coinChange1(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0) return 0;

        return helper(coins, amount, new int[amount]);
    }

    // top down 时间复杂度为 O(S*n) 空间复杂度为 O(S) S 为 amount, n 为 coins 的大小
    // 递推公式为 F(S) = F(S - C) + 1，C 为最后一块硬币的值
    private int helper(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem-1] != 0) return count[rem-1];

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int t = helper(coins, rem-coin, count);

            if (t >= 0 && t < min) min = t + 1;
        }

        count[rem-1] = min == Integer.MAX_VALUE ? -1 : min;

        return count[rem-1];
    }


    // bottom up
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i-coins[j]]+1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChange solution = new CoinChange();

        System.out.println(solution.coinChange(new int[]{1, 2}, 3));
    }

    
}