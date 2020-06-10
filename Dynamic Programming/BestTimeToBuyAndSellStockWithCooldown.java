class BestTimeToBuyAndSellStockWithCooldown {
    /**
     * buy[i] 表示第 i 天之前最后一个操作是买，此时的最大收益
     * sell[i] 表示第 i 天之前最后一个操作是卖，此时的最大收益
     * rest[i] 表示第 i 天之前最后一个操作是冷冻期，此时的最大利益
     * 
     * 状态方程：
     * buy[i] = max(rest[i-1] - price, buy[i-1])
     * sell[i] = max(buy[i-1] + price, sell[i-1])
     * rest[i] = max(sell[i-1], buy[i-1], rest[i-1])
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        
        int len = prices.length;

        int[] buy = new int[len+1];
        int[] sell = new int[len+1];
        int[] rest = new int[len+1];

        buy[0] = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            buy[i+1] = Math.max(rest[i] - prices[i], buy[i]);
            sell[i+1] = Math.max(buy[i] + prices[i], sell[i]);
            rest[i+1] = Math.max(sell[i], Math.max(buy[i], rest[i]));
        }

        return sell[len];
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithCooldown solution = new BestTimeToBuyAndSellStockWithCooldown();
        System.out.println(solution.maxProfit(new int[]{1,2,3,0,2}));
    }
}