class InorderAndPreorderConstructBT {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length-1, preorder, inorder);    
    }

    /**
     * 依照前序的顺序，以第一个节点为根节点，则以该根节点在中序的位置为基准，左边的值构成该根结点的左子树，
     * 右边的值构成该根结点的右子树，以此进行递归
     */
    private TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length-1 || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);

        int inIndex = 0;

        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == preorder[preStart]) {
                inIndex = i;
                break;
            }
        }

        root.left = helper(preStart+1, inStart, inIndex-1, preorder, inorder);
        // 跳过根节点，以及构成左子树的节点，则是右子树根节点的开始
        root.right = helper(preStart + inIndex - inStart + 1, inIndex+1, inEnd, preorder, inorder);

        return root;
    }


    private int in = 0;
    private int pre = 0;

    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        return build(preorder, inorder, Integer.MIN_VALUE);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop) {
        if (pre >= preorder.length) return null;

        if (inorder[in] == stop) {
            in++;
            return null;
        }

        TreeNode root = new TreeNode(preorder[pre++]);
        root.left = build(preorder, inorder, root.val);
        root.right = build(preorder, inorder, stop);

        return root;
    }
}