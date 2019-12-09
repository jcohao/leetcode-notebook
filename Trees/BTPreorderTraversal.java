import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class BTPreorderTraversal {
    private List<Integer> result = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return result;
        result.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return result;
    }


    // 非递归版本
    public List<Integer> preorderTraversal2(TreeNode root) {
        if (root == null) return result;

        Stack<TreeNode> stack = new Stack<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);

            if (node.right != null) stack.add(node.right);
            if (node.left != null) stack.add(node.left);
        }

        return result;
    }
}