class BestTimeToBugAndSellStockIV {
    /**
     * 方法应该没错，但是 TLE 了
     */
    public int maxProfit1(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0) return 0;

        return helper(prices, 0, 0, k);
    }

    private int helper(int[] prices, int start, int count, int k) {
        if (start >= prices.length-1 || count == k) return 0;

        int min = prices[start], partMax = 0;

        for (int i = start+1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            if (min == prices[i]) continue;
            else if (i == prices.length-1 || prices[i] > prices[i+1]) {
                partMax = Math.max(partMax, prices[i] - min + helper(prices, i+1, count+1, k));
            }
        }

        return partMax;
    }


    /**
     * 加了 DP 做优化，但结果不太对
     */
    public int maxProfit2(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0) return 0;
        
        int[] dp = new int[prices.length];

        return helper(prices, 0, 0, k, dp);
    }

    private int helper(int[] prices, int start, int count, int k, int[] dp) {
        if (start >= prices.length-1 || count == k) return 0;
        else if (dp[start] > 0) return dp[start];

        int min = prices[start], partMax = 0, minIndex = start, max = 0;

        for (int i = start+1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
                minIndex = i;
                continue;
            } else if (i == prices.length-1 || prices[i] > prices[i+1]) {
                if (prices[i] - min < max) continue;
                
                max = prices[i] - min;
                partMax = Math.max(partMax, prices[i] - min + helper(prices, i+1, count+1, k, dp));
                
                
            }
        }
        
        for (int i = start; i <= minIndex; i++) {
            dp[i] = partMax;
        }
        
        return partMax;
    }


    /**
     * dp[i][j] 表示在 j 天之前最多 i 次交易所得到的最大利润
     * 
     * 在第 j 天，有如下状态方程
     * 1. 买或不买不会对当前最大利润产生影响：dp[i][j] = dp[i][j-1]
     * 2. 当卖股票时，这支股票一定是在 t=[0 ... j-1] 中的某天买的，
     *    则此时 dp[i][j] = prices[j] - prices[t] + dp[i-1][t-1]，
     *    dp[i-1][t-1] 表示在 t-1 天之前最多 i-1 次交易所得到的最大利润
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0) return 0;
        int len = prices.length;
        // 当 k >= len/2 时，只要有价格差就获取利润，则会得到最大利润
        if (k >= len/2) return quickSolve(prices);

        int[][] dp = new int[k+1][len];
        for (int i = 1; i <= k; i++) {
            int partMax = -prices[0];
            for (int j = 1; j < len; j++) {
                dp[i][j] = Math.max(dp[i][j-1], prices[j] + partMax);
                partMax = Math.max(partMax, dp[i-1][j-1] - prices[j]);
            }
        }

        return dp[k][len-1];
    }

    private int quickSolve(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) max += prices[i] - prices[i-1];
        }
        return max;
    }

    public static void main(String[] args) {
        BestTimeToBugAndSellStockIV solution = new BestTimeToBugAndSellStockIV();

        System.out.println(solution.maxProfit(2, new int[]{0,3,2,4,0,6}));
    }
}