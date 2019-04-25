class Solution(object):
    def climbStairs(self, n):
        """
        :type n: int
        :rtype: int
        DP 问题， 第 n 步的走法等于第 n - 1 步和第 n - 2 步的和
        """
        if n < 2:
            return n

        dp = [0] * n
        dp[0], dp[1] = 1, 2

        for i in range(2, n):
            dp[i] = dp[i-1] + dp[i-2]

        return dp[-1]


s = Solution()
print(s.climbStairs(3))