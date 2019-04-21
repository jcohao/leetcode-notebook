class Solution(object):
    def longestPalindrome(self, s):
        """
        :type s: str
        :rtype: str
        外层循环遍历字符串，内层循环按最长步长去遍历, 判断有没有回文字符串，
        一旦发现回文字符串，更新最长回文字符串和最长长度
        """
        # if not s:
        #     return ""

        # maxlength = 1
        # longest = s[0]

        # for i in range(len(s)):
        #     if i + maxlength >= len(s):
        #         break
        #     for j in range(i+maxlength, len(s)):
        #         temp = s[i:j+1]
        #         if s[i:j+1] == s[i:j+1][::-1]:
        #             longest = s[i:j+1]
        #             maxlength = j - i + 1 

        # return longest


        """
        将回文字符串分为两种情况处理，
        增加一个字符范围，或增加两个字符范围，看扩展的字符串还是不是回文字符串，
        如果是的话，则更新回文字符串的起始位置和长度
        """ 
        # len_s = len(s)
        # if len_s <= 1 or not s:
        #     return s

        # start, maxlength = 0, 1

        # for i in range(1, len_s):
        #     test_s1 = s[i-maxlength-1:i+1]
        #     test_s2 = s[i-maxlength:i+1]

        #     if i-maxlength-1 >= 0 and test_s1 == test_s1[::-1]:
        #         start, maxlength = i-maxlength-1, maxlength+2
        #     elif i-maxlength >= 0 and test_s2 == test_s2[::-1]:
        #         start, maxlength = i-maxlength, maxlength+1

        # return s[start:start+maxlength]


        """
        DP 解法
        """
        len_s = len(s)
        if len_s <= 1 or not s:
            return s

        start, maxlength = 0, 1

        dp = [[0] * len_s for i in range(len_s)]
        
        for i in range(len_s):
            dp[i][i] = 1
            if i < len_s-1 and s[i] == s[i+1]:
                dp[i][i+1] = 1
                start = i
                maxlength = 2

        # 这里的 i 相当于最长长度
        for i in range(3, len_s+1):
            for j in range(0, len_s-i+1):
                r = j + i - 1
                if s[r] == s[j] and dp[j+1][r-1] == 1:
                    dp[j][r] = 1
                    start = j
                    maxlength = i

        return s[start:start+maxlength]



s = Solution()
print(s.longestPalindrome("abaccdcca"))