public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
    

class BTMaxPathSum {
    int maxValue;
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        // 递归得到左子树最大路径和，因为有可能是负值，所以跟 0 之间作比较取一个最大值
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));

        maxValue = Math.max(maxValue, left + right + node.val);
        // 对于当前节点来说，该方法是用于递归计算最大左或右子树加上当前节点的值的和，所以只返回一边
        return Math.max(left, right) + node.val;
    }
}