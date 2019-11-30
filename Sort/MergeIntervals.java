import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return intervals;

        List<int[]> result = new ArrayList<>();

        // 首先按每个区间的第一个元素进行排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] < o2[0]) {
                    return -1;
                } else if (o1[0] > o2[0]) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        result.add(intervals[0]);

        // 排序后的区间只要检查它和上一个区间是否有重叠，
        // 如果重叠了就求出合并后的区间，加到结果集中
        // 如果没有则直接加到结果集中
        for (int i = 1; i < intervals.length; i++) {
            if (result.get(result.size() - 1)[1] >= intervals[i][0]) {
                // 有可能第一个区间包含了第二个区间
                int[] temp = {result.get(result.size()-1)[0], Math.max(result.get(result.size() - 1)[1], intervals[i][1])};
                result.remove(result.size() - 1);
                result.add(temp);
            } else {
                result.add(intervals[i]);
            }
        }

        int[][] result2Int = new int[result.size()][];

        for (int i = 0; i < result.size(); i++) {
            result2Int[i] = result.get(i);
        }

        return result2Int;



    }

    public static void main(String[] args) {
        int[][] nums = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
        MergeIntervals solution = new MergeIntervals();
        int[][] result = solution.merge(nums);
        for (int[] num : result) {
            System.out.println(Arrays.toString(num));
        }
    }
}