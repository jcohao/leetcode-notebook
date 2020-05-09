class NumberOfWaysToWearDifferentHatsToEachOther {
    int mod = 1_000_000_007, result = 0;
    // 普通的回溯，TLE
    public int numberWays1(List<List<Integer>> hats) {
        if (hats == null || hats.size() == 0) return 0;

        boolean[] memo = new boolean[41];

        int m = hats.size();

        backtracking(hats, m, memo, 0);

        return result;
    }

    private void backtracking(List<List<Integer>> hats, int m, boolean[] memo, int row) {
        if (row == m) {
            result++;
            result %= mod;
            return;
        }

        List<Integer> curList = hats.get(row);

        for (int i = 0; i < curList.size(); i++) {
            if (memo[curList.get(i)]) continue;
            memo[curList.get(i)] = true;
            backtracking(hats, m, memo, row+1);
            memo[curList.get(i)] = false;
        }
    }

    long[] record = new long[41];
    // 还是 TLE
    public int numberWays2(List<List<Integer>> hats) {
        if (hats == null || hats.size() == 0) return 0;

        for (int i = 1; i <= 40; i++) {
            record[i] = 1 << i-1;
        }
        
        int m = hats.size();

        backtrack(hats, m, 0, 0);
        
        return result;
    }

    private void backtrack(List<List<Integer>> hats, int m, int row, long memo) {
        if (row == m) {
            result++;
            result %= mod;
            return;
        }

        List<Integer> curList = hats.get(row);

        for (int i = 0; i < curList.size(); i++) {
            int hat = curList.get(i);
            if ((record[hat] & memo) != 0) continue;
            memo |= record[hat];
            backtrack(hats, m, row+1, memo);
            memo ^= record[hat];
        }
    }


    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size();
        // 第 i 号帽应该分配给哪些人
        List<Integer>[] h2p = new List[41];
        for (int i = 1; i <= 40; i++) h2p[i] = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int hat : hats.get(i)) {
                h2p[hat].add(i);
            }
        }

        Integer[][] dp = new Integer[41][1024];

        return dfs(h2p, (1 << n) - 1, 1, 0, dp);
    }

    // allMask 全为 1 则说明所有人都没被分配到帽子
    // hat 代表分配到第 i 顶帽子
    private int dfs(List<Integer>[] h2p, int allMask, int hat, int chosen, Integer[][] dp) {
        if (chosen == allMask) return 1;
        if (hat > 40) return 0;
        if (dp[hat][chosen] != null) return dp[hat][chosen];
        int ans = dfs(h2p, allMask, hat + 1, chosen, dp);
        for (int p : h2p[hat]) {
            if (((chosen >> p) & 1) == 1) continue;
            ans += dfs(h2p, allMask, hat + 1, chosen | (1 << p), dp);
            ans %= mod;
        }

        return dp[hat][chosen] = ans;
    }

}