class Solution(object):
    def uniquePaths(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: int
        机器人只能往左或往下走，则达到终点的路径数等于到达终点上一格的路径数加上左一格的路径数，
        可以依此进行动态规划， 利用一个 dp 数组来存储到达每一格的路径数
        """
        dp = [[0] * m for _ in range(n)]

        for i in range(n):
            for j in range(m):
                if i == 0 or j == 0:
                    dp[i][j] = 1
                else:
                    dp[i][j] = dp[i-1][j] + dp[i][j-1]


        return dp[-1][-1]

s = Solution()
print(s.uniquePaths(7, 3))