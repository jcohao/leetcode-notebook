class BestTimeToBuyAndSellStockIII {
    // 把 prices 分成左右两边数组，分别计算两边数组买卖的最大差值
    // 然后记下最大和即可
    // 时间复杂度为 O(n*n)，空间复杂度为 O(n)
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int max = 0;

        for (int i = 0; i < prices.length; i++) {
            int left = helper(prices, 0, i);
            int right = helper(prices, i+1, prices.length-1);
            max = Math.max(max, left + right);
        }

        return max;
    }

    private int helper(int[] prices, int start, int end) {
        if (start == end) return 0;

        int min = Integer.MAX_VALUE, max = 0;

        for (int i = start; i <= end; i++) {
            min = Math.min(min, prices[i]);
            max = Math.max(max, prices[i] - min);
        }

        return max;
    }

    /**
     * 创建两个数组，一个记录 i 天以前（包括 i）买卖的最大差价
     * 另外一个记录 i 天以后（不包括 i） 买卖的最大差价
     * 
     * 时间和空间复杂度都是 O(n)
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
    
        int[] pre = new int[prices.length];
        int[] post = new int[prices.length];

        int min = Integer.MAX_VALUE, partMax = 0;

        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            // 记录局部的最大值，后面所得的差值要跟这个值相比较才是 i 天前买卖的最大差值
            partMax = Math.max(partMax, prices[i] - min);
            pre[i] = partMax;
        }

        int max = prices[prices.length-1];
        partMax = 0;

        for (int i = prices.length-2; i >= 0; i--) {
            max = Math.max(max, prices[i+1]);
            partMax = Math.max(partMax, max - prices[i+1]);
            post[i] = partMax;
        }

        int maxResult = 0;

        for (int i = 0; i < prices.length; i++) {
            maxResult = Math.max(maxResult, pre[i] + post[i]);
        }

        return maxResult;
    }

    /**
     * dp[k, i] = max(dp[k, i-1], prices[i] - prices[j] + dp[k-1])
     * 对于第 k 次交易，在第 i 天
     * 如果不交易，则是 dp[k, i-1]
     * 如果交易，则为 prices[i] - prices[j] + dp[k-1] j = [0...i)
     * 
     * 时间复杂度为 O(kn^2) 空间复杂度为 O(kn)
     */
    public int maxProfitDP(int[] prices) {
        if (prices == null || prices.length < 1) return 0;

        int[][] dp = new int[3][prices.length];

        for (int k = 1; k <= 2; k++) {
            for (int i = 1; i < prices.length; i++) {
                // 第 i 天的交易得找 0 ~ i-1 之前股价最小的一天
                int min = prices[0];
                for (int j = 1; j < i; j++) {
                    // 股价最小的一天还跟在这一天之前的一趟交易有关，如果这一天的金额减去这一天之前交易的金额
                    // 为最小的话，那么在这一天买入股票时最佳的
                    min = Math.min(min, prices[j] - dp[k-1][j-1]);
                }
                // 第 i 天要不不做交易，要不卖出股票
                dp[k][i] = Math.max(dp[k][i-1], prices[i] - min);
            }
        }

        return dp[2][prices.length-1];
    }

    /**
     * 以上 DP 解法 min 的计算是重复了的，经过简化之后时间复杂度变为 O(kn)
     */
    public int maxProfitDP2(int[] prices) {
        if (prices == null || prices.length < 1) return 0;

        for (int k = 1; k <= 2; k++) {
            int min = prices[0];
            for (int i = 1; i < prices.length; i++) {
                min = Math.min(min, prices[i] - dp[k-1][i-1]);
                dp[k][i] = Math.max(dp[k][i-1], prices[i] - min);
            }
        }

        return dp[2][prices.length-1];
    }

    public int maxProfit2(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0, buy2 = Integer.MIN_VALUE, sell2 = 0;

        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, price + buy1);
            // 计算第二次交易时的最佳买入时间，要把第一次的交易金额给算进来
            buy2 = Math.max(buy2, sell1-price);
            sell2 = Math.max(sell2, price+buy2);
        }

        return sell2;
    }
    
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIII solution = new BestTimeToBuyAndSellStockIII();

        System.out.println(solution.maxProfitDP(new int[]{3,2,6,5,0,3}));
    }
}