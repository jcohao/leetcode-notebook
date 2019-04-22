class Solution(object):
    def maxSubArray(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        设置一个 start 和 end 双层遍历数组， 记录下最大和
        当数据量比较大的时候会 time limit exceeded
        """
        # max_sum = -float('inf')

        # for start in range(len(nums)):
        #     for end in range(start+1, len(nums)+1):
        #         if sum(nums[start:end]) > max_sum:
        #             max_sum = sum(nums[start:end])


        # return max_sum

        """
        设立一个 before 变量记录之前的和，
        遍历数组，
        如果 before 大于等于 0，则 before 加上遍历的数字，
            如果此时的和大于之前的最大和，则更新最大和
        如果 before 小于 0，则把 before 设置为当前遍历的数字
        """
        res = before = nums[0]

        for num in nums[1:]:
            if before >= 0:
                before += num
            else:
                before = num

            if before > res:
                res = before
        
        return res

s = Solution()
print(s.maxSubArray([-2,1,-3,4,-1,2,1,-5,4]))

