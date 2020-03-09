class BTLevelTraversal {
    /**
     * 非递归层序遍历
     */
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.add(root);
        int count = 1;
        
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            while (count != 0) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
                
                count--;
            }
            
            result.add(level);
            count = queue.size();
        }
        
        return result;
    }


    /**
     * 递归的层序遍历
     */
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return result;
        helper(root, 1);
        return result;
    }

    private void helper(TreeNode node, int level) {
        if (node == null) return;

        if (level > result.size()) result.add(new ArrayList<>());

        result.get(level-1).add(node.val);

        helper(node.left, level+1);
        helper(node.right, level+1);
    }

    /**
     * 以上两个版本的时间复杂度和空间复杂度都是 O(n)
     */
}