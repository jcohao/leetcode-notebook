import java.util.PriorityQueue;

class TrappingRainWater2 {
    /**
     * 这道题是二维的升级版，二维的做法是找两边最高的，然后由矮的决定这一格装多少水
     * 则三维的就是找四边最高的，然后也是最矮的决定装多少水
     *  
     * ** 仅靠四个方向最高来决定此格的容水量是不行的，如果有地方低了就会漏 **
     * 边界不装水
     * 
     * 时间复杂度 O(M*N*max(M*N))  空间复杂度 O(1)
     */
    public int trapRainWater1(int[][] heightMap) {
        int result = 0;
        if (heightMap == null || heightMap.length == 0) return result;
        int row = heightMap.length;
        int col = heightMap[0].length;


        // 遍历每一个格子，除了边界部分
        for (int i = 1; i < row-1; i++) {
            for (int j = 1; j < col-1; j++) {

                // 然后用四个循环去找四个方向的最大高度
                int topMost = 0, bottomMost = 0, leftMost = 0, rightMost = 0;

                for (int top = i-1; top >= 0; top--) {
                    if (heightMap[top][j] > topMost) topMost = heightMap[top][j];
                }

                for (int bottom = i+1; bottom < row; bottom++) {
                    if (heightMap[bottom][j] > bottomMost) bottomMost = heightMap[bottom][j];
                }

                for (int left = j-1; left >= 0; left--) {
                    if (heightMap[i][left] > leftMost) leftMost = heightMap[i][left];
                }

                for (int right = j+1; right < col; right++) {
                    if (heightMap[i][right] > rightMost) rightMost = heightMap[i][right];
                }

                int min = findMin(leftMost, rightMost, topMost, bottomMost);

                result += (min > heightMap[i][j]) ? min-heightMap[i][j] : 0;
            }
        }

        return result;
    }

    public int findMin(int ... nums) {
        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n < min) min = n;
        }
        return min;
    }

    /**
     * 使用 BFS 来进行解题
     * 利用优先队列来记录访问过的格子的高度和坐标
     * 由于四周的格子是不能访问的，所以先将四个边界的格子信息加入到优先队列中
     * 然后在从队列中 poll 出最小高度的格子，其决定了其四周的邻居的储水量
     * 还需要一个二维数组来记录访问过的格子
     */
    public int trapRainWater(int[][] heightMap) {
        int result = 0;
        if (heightMap == null || heightMap.length == 0) return result;
        int row = heightMap.length;
        int col = heightMap[0].length;

        int[][] memo = new int[row][col];
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[2]-b[2]));

        // 加入四条边界的数据
        for (int c = 0; c < col; c++) {
            heap.offer(new int[]{0, c, heightMap[0][c]});
            memo[0][c] = 1;
            heap.offer(new int[]{row-1, c, heightMap[row-1][c]});
            memo[row-1][c] = 1;
        }

        for (int r = 1; r < row-1; r++) {
            heap.offer(new int[]{r, 0, heightMap[r][0]});
            memo[r][0] = 1;
            heap.offer(new int[]{r, col-1, heightMap[r][col-1]});
            memo[r][col-1] = 1;
        }

        // 右，左，下，上 四个方向
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        
        while (!heap.isEmpty()) {
            int[] coord = heap.poll();
            int x = coord[0], y = coord[1], z = coord[2];

            // 这里是往四周沿着四条边去延展
            for (int[] d : dirs) {
                int r = d[0] + x;
                int c = d[1] + y;

                if (r > 0 && c > 0 && r < row-1 && c < col-1 && memo[r][c] == 0) {
                    // 问题出在这里，这里加入的应该是两个格子中最高的高度
                    heap.offer(new int[]{r, c, Math.max(z, heightMap[r][c])});
                    result += Math.max(0, z-heightMap[r][c]);
                    memo[r][c] = 1;
                }
            }
            
        }

        return result;

    
    }



    public static void main(String[] args) {
        TrappingRainWater2 solution = new TrappingRainWater2();
        int[][] heightMap = {
            {12,13,1,12},
            {13,4,13,12},
            {13,8,10,12},
            {12,13,12,12},
            {13,13,13,13}
        };
        System.out.println(solution.trapRainWater(heightMap));
    }
}