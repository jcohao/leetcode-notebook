import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

class BTPostorderTraversal {
    private List<Integer> result = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return result;

        postorderTraversal(root.left);
        postorderTraversal(root.right);
        result.add(root.val);

        return result;
    }

    // 非递归版本
    public List<Integer> postorderTraversal2(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        if (root == null) return result;

        Deque<TreeNode> deque = new ArrayDeque<>();

        TreeNode p = root;

        while (!deque.isEmpty() || p != null) {
            if (p != null) {
                deque.push(p);
                result.addFirst(p.val);
                p = p.right;
            } else {
                TreeNode node = deque.pop();
                p = node.left;
            }
        }

        return result;
        
    }
    
}