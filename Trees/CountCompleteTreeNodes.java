class CountCompleteTreeNodes {
    // 层序遍历，记录每一个节点
    public int countNodes1(TreeNode root) {
        int result = 0;
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.add(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            result += size;
            
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        
        return result;
    }


    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        return 1 + countNodes(root.left) + countNodes(root.right);

        // return root == null ? 0 : 1 + countNodes(root.left) + countNodes(root.right);
    }
}