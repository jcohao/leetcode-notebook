import java.util.Map;

/**
 * 一棵树中，由任意节点往下遍历，其遍历到的路径和等于目标值，求一共有多少个这样的路径
 * 
 * 这道题用到了前缀和方法，从上往下遍历用 map 记录每一个节点所经过的路径和，key 为路径和，val 为多少条
 * 然后往左右进行 dfs，每到一个节点检查 路径和 - sum 这个key值在不在 map 中，如果在则说明其中存在
 * 所求的路径和，而数量则是该 key 对应的 val，由于是按顺序先遍历左子树再遍历右子树，遍历完左右子树
 * 需要在 map 中对应 key 值的 val 减去 1
 */
class PathSumIII {
    private int result = 0;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        Map<Integer, Integer> map = new HashMao<>();
        dfs(root, 0, sum, map);

        return result;
    }


    private void dfs(TreeNode node, int count, int sum, Map<Integer, Integer> map) {
        if (node == null) return;

        count += node.val;

        if (map.containsKey(count-sum)) result += map.get(count-sum);

        map.put(count, map.getOrDefault(count, 0)+1);

        dfs(node.left, count, sum, map);
        dfs(node.right, count, sum, map);
        map.put(count, map.get(count)-1);
    }
}