class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class ValidateBST {
    /**
     * 验证一棵树是不是 BST 只需要看其中序遍历的序列是不是有序的即可
     */
    private List<Integer> sequence = new ArrayList<>();
    public boolean isValidBST1(TreeNode root) {
        if (root == null) return true;

        inorder(root);

        for (int i = 1; i < sequence.size(); i++) {
            // 二叉搜索树不能有相同的值
            if (sequence.get(i) <= sequence.get(i-1)) return false;
        }

        return true;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);
        sequence.add(node.val);
        inorder(node.right);
    }

    /**
     * 可以使用非递归的方法去遍历树的节点，这样就不用额外数组去保存节点的值了
     * 因为如果该树为二叉搜索树，节点的出栈顺序肯定是有序的
     * 只需要比较出栈的节点值于上一个节点值即可
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;

        Stack<TreeNode> stack = new Stack<>();
        // 有一个 case 直接是 [Integer.MIN_VALUE]
        double preNum = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }

            root = stack.pop();
            if (root.val <= preNum) return false;
            preNum = root.val;
            root = root.right;
        }

        return true;

    }

    /**
     * 不能递归验证左右子树是否二叉搜索树来验证整棵树是否为二叉搜索树
     * 因为子树为二叉搜索树并不代表整棵树也为二叉搜索树
     * 例子：[10,5,15,null,null,6,20]
     */


    /**
     * 递归的做法可以通过比较每个节点的上限和下限来判断是否为二叉搜索树
     */
    public boolean isValidBST(TreeNode root) {
        // 根节点不受限，其必须大于其左子树所有节点，所以是左子树的上限
        // 其必须小于其右子树的所有节点，所以是右子树的下限
        return helper(root, null, null);
    }

    private boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;

        if (lower != null && val <= lower ||
            upper != null && val >= upper) {
                return false;
        }

        return helper(node.left, lower, val) && helper(node.right, val, upper);


    }
    
}