import java.util.LinkedList;
import java.util.Queue;

class CourseSchedule {
    /**
     * 这道题其实就是看这些课程所构造出来的图有没有环
     * 首先先用一个二维数组来构造图
     * 再用一个一位数组来记录节点的入度
     * 将入度为 0 的节点加入到队列中
     * 然后从队列中取节点，每取一个节点代表上一门课
     * 再遍历这门课是哪些课的先行课
     * 先行课节点入度减一，当先行课节点的入度减到 0 的时候，将该节点加入到队列中
     * 直到队列为空，最后就是看还有没有节点的入度不为 0，如果有，则证明存在环
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] graph = new int[numCourses][numCourses];

        int[] in = new int[numCourses];

        for (int[] node : prerequisites) {
            graph[node[1]][node[0]] = 1;
            in[node[0]]++; 
        }

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int n = queue.poll();
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (graph[n][i] != 0) {
                    if (--in[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }

        return count == numCourses;
    }


    /**
     * 版本二就是求拓扑排序了，只需要把队列中 poll 出来的元素加到数组中即可
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        int[][] graph = new int[numCourses][numCourses];

        int[] in = new int[numCourses];

        for (int[] node : prerequisites) {
            graph[node[1]][node[0]] = 1;
            in[node[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (in[i] == 0) queue.offer(i);
        }

        int count = 0;

        while (!queue.isEmpty()) {
            int n = queue.poll();
            result[count++] = n;
            
            for (int i = 0; i < numCourses; i++) {
                if (graph[n][i] != 0) {
                    if (--in[i] == 0) {
                        queue.offer(i);
                    }
                }
            }
        }

        return count == numCourses ? result : new int[0];
    }
}