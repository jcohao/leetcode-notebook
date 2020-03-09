import java.util.Stack;

class TreeNode {
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


    /**
     * 这种方法破坏了原本的树结构
     */
    public int sumNumbers2(TreeNode root) {
        if (root == null) return 0;

        int result = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            if (node.left == null && node.right == null) {
                result += node.val;
            }

            if (node.left != null) {
                node.left.val += node.val * 10;
                stack.push(node.left);
            }

            if (node.right != null) {
                node.right.val += node.val * 10;
                stack.push(node.right);
            }
        }

        return result;
    }
}