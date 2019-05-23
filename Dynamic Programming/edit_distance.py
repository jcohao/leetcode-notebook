class Solution(object):
    def minDistance(self, word1, word2):
        """
        :type word1: str
        :type word2: str
        :rtype: int
        这道题得用 DP 的方法去做，但是如果不知道递推关系是很难开头的，递推关系如下：
        设置一个 dp m*n 的数组做记录，dp[i][j] 表示第一个字符串的前 i 个字符和第二个字符串的前 j 个字符的最少操作数
        然后这个 dp[i][j] 是如何推出来的是关键，首先如果 i，j 中有一个为 0 那 dp[i][j] 肯定就等于 i 或者 j 了
        其次，如果不为 0，那就要看 word1[i-1] 和 word2[j-1] (索引从0开始的) 是否相等，如果相等，则操作数跟 dp[i-1][j-1] 一致
        如果不等，这操作数在 dp[i-1][j] 删除操作， dp[i][j-1] 增加操作， dp[i-1][j-1] 替换操作，这三个操作中的最小者 + 1
        具体这三个数为什么是对应这三个操作可以画图找他们的关系，知道了递推关系之后就可以写代码了 
        核心代码里面为两层循环，所以时间复杂度为 O(mn)， 用到的 dp 也是 m*n 规模的，所以空间复杂度也是这个昂
        """

        len1 = len(word1)
        len2 = len(word2)

        dp = [[0] * (len1 + 1) for _ in range(len2 + 1)]

        # 初始化， i,j 有一个为 0 的情况
        for i in range(1, len1+1): 
            dp[0][i] = i

        for j in range(1, len2+1):
            dp[j][0] = j


        for i in range(1, len2+1):
            for j in range(1, len1+1):
                # 字符串索引从 0 开始
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1

        return dp[-1][-1]



s = Solution()
print(s.minDistance("horse", "ros"))