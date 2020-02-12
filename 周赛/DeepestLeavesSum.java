// Given a binary tree, return the sum of values of its deepest leaves.

// Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
// Output: 15

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class DeepestLeavesSum {
    /**
     * 关于树的题还是能用递归尽量用递归
     * 这道题是求树最深的叶子节点的和
     * 这里我用了一个变量去保留最深的深度
     * 当结点深度等于最深深度时，总和叠加
     * 当结点深度大于最深深度时，最深深度更新，总和更新
     */
    private int deepMax = -1;
    private int sum = 0;

    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return sum;

        helper(root, 0);

        return sum;
    }

    private void helper(TreeNode node, int deep) {
        if (deep > deepMax) {
            deepMax = deep;
            sum = node.val;
        } else if (deep == deepMax) {
            sum += node.val;
        }

        if (node.left != null) helper(node.left, deep + 1);
        if (node.right != null) helper(node.right, deep + 1);
    }
}