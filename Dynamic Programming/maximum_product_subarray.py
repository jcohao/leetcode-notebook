class Solution:
    """
    求子序列中乘积最大的那个子序列，使用map(i, j)记录 i,j 子序列的乘积，最后返回最大的乘积
    时间复杂度和空间复杂度都是 O(n*n)
    方法是OK的，但数据一多就 Time limit 了
    """
    # def maxProduct(self, nums):
        # length = len(nums)
        # result = []

        # for i in range(length):
        #     last = nums[i]
        #     result.append(last)
        #     for j in range(i+1, length):
        #         last *= nums[j]
        #         result.append(last)


        # return max(result)

    """
    另一种方法是记下当前最大和最小，如果遇到负数的话，大的变小，小的变大，
    之后再与每次遍历的值作比较，记下当前最小和最大，这样可以同时找序列
    乘积最小和最大的
    时间复杂度为 O(n) 空间复杂度为 O(1)
    """
    def maxProduct(self, nums):
        r = nums[0]
        imax = r
        imin = r

        for num in nums[1:]:
            if num < 0:
                imax, imin = imin, imax

            imax = max(num, imax*num)
            imin = min(num, imin*num)

            r = max(r, imax)

        return r


s = Solution()

print(s.maxProduct([-2,6,4,-1,0,4]))