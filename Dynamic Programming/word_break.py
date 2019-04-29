class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        采用 DP 解决此问题
        dp[i] 表示 s[:i] 能拆成 wordDict 中的字符串组合
        首先遍历字符串 s 
        然后再遍历 wordDict ，如果 s[j:i] 在 wordDict 中，且 dp[j] == 1 则 dp[i] 设置为 1
        """
        len_s = len(s)
        dp = [0] * len_s

        for i in range(len_s):
            for word in wordDict:
                len_word = len(word)
                if i + 1 >= len_word and s[i-len_word+1:i+1] == word and (i+1-len_word == 0 or dp[i-len_word] == 1):
                    dp[i] = 1
                    break

        return dp[len_s-1] == 1


s = Solution()
string = "bccdbacdbdacddabbaaaadababadad"
test = ["cbc","bcda","adb","ddca","bad","bbb","dad","dac","ba","aa","bd","abab","bb","dbda","cb","caccc","d","dd","aadb","cc","b","bcc","bcd","cd","cbca","bbd","ddd","dabb","ab","acd","a","bbcc","cdcbd","cada","dbca","ac","abacd","cba","cdb","dbac","aada","cdcda","cdc","dbc","dbcb","bdb","ddbdd","cadaa","ddbc","babb"]
print(s.wordBreak(string, test))