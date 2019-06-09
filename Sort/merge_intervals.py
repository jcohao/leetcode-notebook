class Solution(object):
    def merge(self, intervals):
        """
        :type intervals: List[List[int]]
        :rtype: List[List[int]]
        给定一个包含一些代表区间的数组，要求合并有重叠的数组项，边界相等也算重叠
        这里的重叠存在两种情况：1.相交 2.包含
        首先，先将区间按照右边界点排序，为什么是右边界点，为了处理有某个大区间包含了前面很多个小区间的情况
        然后从后面往前面遍历，按照上面的两种情况看有没有重叠，
        如果有则合并，[min(left), max(right)]
        """
        if len(intervals) < 2:
            return intervals

        # sorted 返回的是副本，不改变原来的元素
        intervals = sorted(intervals, key = lambda x: x[1])

        for i in range(len(intervals)-1, 0, -1):
            if intervals[i][0] <= intervals[i-1][1]:
                intervals[i-1][0] = min(intervals[i-1][0], intervals[i][0])
                intervals[i-1][1] = intervals[i][1]     # 右边界是排过序的

            intervals.pop(i)

        return intervals
