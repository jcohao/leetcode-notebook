public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
    

class SumRoot2LeafNum {
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;

        return helper(0, root);
    }

    private int helper(int sum, TreeNode node) {
        if (node == null) {
            return 0;
        } else if (node.left == null && node.right == null) {
            return sum * 10 + node.val;
        } else {
            return helper(sum*10 + node.val, node.left) + helper(sum*10 + node.val, node.right);
        } 
    }
}