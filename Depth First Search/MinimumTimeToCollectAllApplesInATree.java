class MinimumTimeToCollectAllApplesInATree {
    public int minTime1(int n, int[][] edges, List<Boolean> hasApple) {
        
        boolean[] memo = new boolean[edges.length];
        
        return dfs(edges, 0, hasApple, memo);
    }
    
    private int dfs(int[][] edges, int from, List<Boolean> hasApple, boolean[] memo) {
        int result = 0;
        for (int i = 0; i < edges.length; i++) {
        
            int[] edge = edges[i];
            int temp = 0;
            if (memo[i]) continue;
            
            if (edge[0] == from) {
                if (hasApple.get(edge[1])) {
                    temp += 2 + dfs(edges, edge[1], hasApple, memo);
                } else {
                    temp += dfs(edges, edge[1], hasApple, memo);
                    if (temp != 0) temp += 2;
                }

                memo[i] = true;
            }

            result += temp;
        }
        
        return result;
    }


    // 要先用 Map 建树，这样遍历树的子节点会更快
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> tree = new HashMap<>();

        for (int[] edge : edges) {
            if (!tree.containsKey(edge[0])) {
                tree.put(edge[0], new ArrayList<>());
            }

            tree.get(edge[0]).add(edge[1]);
        }

        int result = dfs(tree, 0, hasApple);
        // 根节点重复计算的话要减回来
        return result > 0 ? result - 2 : 0;
    }

    private int dfs(Map<Integer, List<Integer>> tree, int from, List<Boolean> hasApple) {
        if (!tree.containsKey(from)) {
            if (hasApple.get(from)) return 2;
            else return 0;
        }

        int result = 0;
         
        for (int to : tree.get(from)) {
            result += dfs(tree, to, hasApple);
        }

        if (result > 0 || hasApple.get(from)) result += 2;

        return result;
    }
}