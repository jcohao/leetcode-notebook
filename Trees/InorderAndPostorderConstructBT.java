class InorderAndPostorderConstructBT {
    /**
     * 这道题的解法跟 inorder preorder 的解法差不多
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper(postorder.length-1, 0, inorder.length-1, inorder, postorder);
    }

    private TreeNode helper(int postIndex, int inStart, int inEnd, int[] inorder, int[] postorder) {
        if (postIndex < 0 || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode(postorder[postIndex]);

        int inIndex = 0;

        // 这里可以用 hashmap 把 inorder 的值和键建立映射
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == postorder[postIndex]) {
                inIndex = i;
                break;
            }
        }

        root.right = helper(postIndex-1, inIndex+1, inEnd, inorder, postorder);
        root.left = helper(postIndex - (inEnd - inIndex) - 1, inStart, inIndex-1, inorder, postorder);

        return root;
    }
}