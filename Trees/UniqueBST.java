class UniqueBST {
    /**
     * 计算树的可能性个数符合递推公式 f(n) = 2*f(n-1) + 2*f(n-2)f(1) + 2*f(n-3)f(2) + ... (分奇偶)
     * 奇数则再 + f(n/2)*f(n/2) 
     * 时间复杂度 O(n*n)
     * 空间复杂度 O(n)
     */
    public int numTrees(int n) {
        if (n < 1) return 0;

        int[] dp = new int[n+1];

        // 0 个节点的情况有一种
        dp[0] = 1;

        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < i/2; j++) {
                dp[i] += 2 * dp[j] * dp[i-j-1];
            }

            if ((i+1) % 2 == 0) dp[i] += dp[i/2] * dp[i/2];
        }

        return dp[n];
    }


    public static void main(String[] args) {
        UniqueBST solution = new UniqueBST();
        System.out.println(solution.numTrees(4));
    }
}