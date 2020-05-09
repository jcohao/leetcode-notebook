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

    public int maxProfit2(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0, buy2 = Integer.MIN_VALUE, sell2 = 0;

        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, price + buy1);
            buy2 = Math.max(buy2, sell1-price);
            sell2 = Math.max(sell2, price+buy2);
        }

        return sell2;
    }
    public static void main(String[] args) {
        BestTimeToBuyAndSellStockIII solution = new BestTimeToBuyAndSellStockIII();

        System.out.println(solution.maxProfit2(new int[]{3,2,6,5,0,3}));
    }
}