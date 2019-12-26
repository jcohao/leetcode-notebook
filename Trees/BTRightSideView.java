import java.util.Queue;

import javax.swing.tree.TreeNode;

class BTRightSideView {

    /**
     * 层序遍历，然后把每一层最后的节点都记录下来
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) return result;

        Queue<TreeNode> queue = new ArrayList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            int iter = 1;
            int size = queue.size();
            while (iter <= size) {
                TreeNode node = queue.poll();
                if (iter == size) result.add(node.val);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                iter++;
            }
        }

        return result;

    }


    
}