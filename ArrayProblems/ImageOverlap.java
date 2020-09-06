class ImageOverlap {
    /**
     * 统计图像 A 中为 1 的每个点的坐标到图像 B 中为 1 的每个点的坐标的距离
     * 移动之后重叠的点所统计到的距离肯定是一样的，最后找出这个被记录得最多的
     * 距离
     */
    public int largestOverlap(int[][] A, int[][] B) {
        int n = A.length;
        
        List<int[]> listA = new ArrayList<>(), listB = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) listA.add(new int[]{i, j});
                
                if (B[i][j] == 1) listB.add(new int[]{i, j});
            }
        }
        
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < listA.size(); i++) {
            int[] posA = listA.get(i);
            for (int j = 0; j < listB.size(); j++) {
                int[] posB = listB.get(j);
                
                int x = posA[0] - posB[0], y = posA[1] - posB[1];
                
                String key = x + "-" + y;
                
                map.put(key, map.getOrDefault(key, 0)+1);
            }
        }
        
        int result = 0;
        
        for (Integer num : map.values()) result = Math.max(result, num);
        
        return result;
    }

    // 空间优化
    public int largestOverlap1(int[][] A, int[][] B) {
        int n = A.length;
        
        List<Integer> listA = new ArrayList<>(), listB = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 这里乘以 n 的话会有干扰，由于 n 不超过 30，这里选用乘以 100 以排除干扰
                if (A[i][j] == 1) listA.add(i * 100 + j);
                if (B[i][j] == 1) listB.add(i * 100 + j);
            }
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        
        for (int i = 0; i < listA.size(); i++) {
            for (int j = 0; j < listB.size(); j++) {
                int key = listA.get(i) - listB.get(j);
                
                map.put(key, map.getOrDefault(key, 0)+1);
                
                result = Math.max(result, map.get(key));
            } 
        }
        
        return result;
    }
}