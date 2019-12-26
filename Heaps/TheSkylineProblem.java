import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class TheSkylineProblem {

    /**
     * 利用扫线算法记录每个建筑的左边界点和右边界点
     * 起始点则找最高点，终止点则找第二高的点
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if (buildings == null || buildings.length == 0) return result;

        List<int[]> nodes = new ArrayList<>();

        for (int[] item : buildings) {
            // 每个建筑的起始边高度设为负以表示其为起始边
            nodes.add(new int[]{item[0], -item[2]});
            // 终止边的高度为正
            nodes.add(new int[]{item[1], item[2]});
        }
        

        // 扫边，相当于从左到右扫描，把每一栋房子的起始结点和终止结点都加到数组中
        // 按边从小到大排序，若有两栋房子是相邻的 [2 8 5] [8 9 5]，[8 5] 在前 [8 -5] 在后
        // 负的为从小到大，正的为从大到小
        Collections.sort(nodes, (a, b) -> (a[0] == b[0]) ? a[1]-b[1] : a[0]-b[0]);

        // 存入的高度，由高到低
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> (b-a));
        heap.offer(0);
        int prev = 0;
        for (int[] n : nodes) {
            if (n[1] < 0) {
                // 进入一栋建筑的左边界点则把高度加入到优先队列中
                heap.offer(n[1]);
            } else {
                // 离开一栋建筑的右边界点则把高度移出
                heap.remove(n[1]);
            }
            int cur = heap.peek();
            // cur == prev 说明两个建筑并列或者建筑高度比之前的矮
            if (cur != prev) {
                List<Integer> t = new ArrayList<>();
                t.add(n[0]);
                t.add(cur);
                result.add(t);
                prev = cur;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        
    }
}