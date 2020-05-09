class FindKthSamllestSumOfSortedRows {
    public int kthSmallest(int[][] mat, int k) {
        // 1 <= mat[i][j] <= 5000, m <= 40，可以在 [m, 5000*m] 的区间内做二分查找去找第 k 个最小和
        int m = mat.length, n = mat[0].length;

        int left = m, right = m * 5000, ans = -1;

        while (left <= right) {
            // 这个 mid 就是目标和
            int mid = left + (right - left) / 2;

            // 每次去找序列和小于等于目标和的个数，当个数大于 k 则说明第 k 个最小序列和在目标和的左边
            // 否则在目标和的右边
            int cnt = countArraysHaveSumLessOrEqual(mat, m, n, mid, 0, 0, k);

            if (cnt >= k) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private int countArraysHaveSumLessOrEqual(int[][] mat, int m, int n, int targetSum, int r, int sum, int k) {
        if (sum > targetSum) return 0;

        if (r == m) return 1;

        int ans = 0;

        for (int c = 0; c < n; c++) {
            int cnt = countArraysHaveSumLessOrEqual(mat, m, n, targetSum, r+1, sum + mat[r][c], k - ans);
            // 为 0 则说明所有的和都大于目标和
            if (cnt == 0) break;
            ans += cnt;
            // ans > k 则说明目标和是大于第 k 最小和的
            if (ans > k) break;
        }

        return ans;
    }

    public static void main(String[] args) {
        FindKthSamllestSumOfSortedRows solution = new FindKthSamllestSumOfSortedRows();
        int[][] mat = {
            {1, 3, 11},
            {2, 4, 6}
        };

        System.out.println(solution.kthSmallest(mat, 5));
    }
}