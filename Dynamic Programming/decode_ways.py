class Solution(object):
    def numDecodings(self, s):
        """
        :type s: str
        :rtype: int
        可以用 DP 去解决， 如果是长度为 n 的字串，
        先判断 n 是不是为 0，如果是且 n-1 为 1 或 2 则为 n-2 的情况
        如果 n-1 不是 1 或 2 则返回 0
        如果 n 不是 0 ，则分以下步骤解题
            若 n：n-1 是 11 到 26 之间， 则长 n 的解码方式为 n-1, n-2 的和
            否则为 n-1 的解码方式
        """
        if not s or s[0] == '0':
            return 0

        len_s = len(s)
        dp = [1] * (len_s+1)

        for i in range(1, len_s):
            if s[i] == '0':
                if s[i-1] in {'1', '2'}:
                    dp[i+1] = dp[i-1]
                else:
                    return 0
            else:
                if 10 < int(s[i-1:i+1]) <= 26:
                    dp[i+1] = dp[i] + dp[i-1]
                else:
                    dp[i+1] = dp[i]

        return dp[-1]

s = Solution()
print(s.numDecodings("101"))
        