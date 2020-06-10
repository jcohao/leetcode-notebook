class BestTimeToBuyAndSellStockWithTransactionFee {
    // wrong answer
    public int maxProfit1(int[] prices, int fee) {
        if (prices == null || prices.length < 2) return 0;
        
        int min = Integer.MAX_VALUE, partMin = Integer.MAX_VALUE, result = 0;
        
        for (int i = 0; i < prices.length; i++) {
            partMin = Math.min(partMin, prices[i]);
            min = Math.min(min, prices[i]);

            if (i < prices.length-1 && prices[i] <= prices[i+1]) continue;
            
            if (prices[i] > partMin + fee) {
                result += prices[i] - partMin - fee;
                partMin = Integer.MAX_VALUE;
            }
            
            if (prices[i] - min - fee > result) {
                result = prices[i] - min - fee;
                partMin = Integer.MAX_VALUE;
            }
        }
        
        return result;
    }

    public int maxProfit(int[] prices, int fee) {
        if (prices == null) return 0;
        
        int cash = 0, hold = -prices[0];
        
        for (int i = 1; i < prices.length; i++) {
            cash = Math.max(cash, hold + prices[i] - fee);
            hold = Math.max(hold, cash - prices[i]);
        }
        
        return cash;
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStockWithTransactionFee solution = new BestTimeToBuyAndSellStockWithTransactionFee();

        System.out.println(solution.maxProfit(new int[]{1,4,6,2,8,3,10,14}, 3));
    }
}