class Solution(object):
    def uniquePathsWithObstacles(self, obstacleGrid):
        """
        :type obstacleGrid: List[List[int]]
        :rtype: int
        对比上一个版本， 这道题在格子中添加了障碍，
        解法跟上一道题类似， 同样是维护一个 dp 矩阵来记录每一格的步数，
        如果遇到障碍，则该格的步数为 0
        """
        m = len(obstacleGrid[0])
        n = len(obstacleGrid)

        dp = [[0] * m for _ in range(n)]

        for i in range(n):
            for j in range(m):
                if not obstacleGrid[i][j]:
                    if not i and not j:
                        dp[i][j] == 1
                    elif not i and j:
                        dp[0][j] = dp[0][j-1]
                    elif i and not j:
                        dp[i][0] = dp[i-1][0]
                    else:
                        dp[i][j] = dp[i-1][j] + dp[i][j-1]


        return dp[-1][-1]

s = Solution()
data = [
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
print(s.uniquePathsWithObstacles(data))
