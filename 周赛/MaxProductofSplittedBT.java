class MaxProductofSplittedBT {
    /**
     * 找出一棵二叉树中，去掉一条边，两个子树和的最大积
     */
    Map<TreeNode, Long> map = new HashMap<>();
    // 这里一定要用 long 来记录最大和，不然会溢出
    long total = 0;
    long maxProduct = 0;
    private static int mod = 1_000_000_000 + 7;
    public int maxProduct(TreeNode root) {
        if (root == null) return 0;

        total = postOrder(root);

        preOrder(root);

        return (int) (maxProduct % mod);
    }


    private long postOrder(TreeNode node) {
        if (node == null) return 0;

        long left = postOrder(node.left);
        long right = postOrder(node.right);
        long sum = node.val + left + right;
        map.put(node, sum);

        return sum;
    }

    private void preOrder(TreeNode node) {
        if (node == null) return;

        if (node.left != null) {
            long leftSum = map.get(node.left);
            maxProduct = Math.max(maxProduct, leftSum*(total - leftSum));
            preOrder(node.left);
        }

        if (node.right != null) {
            long rightSum = map.get(node.right);
            maxProduct = Math.max(maxProduct, rightSum*(total - rightSum));
            preOrder(node.right);
        }
        
    }

}