import java.util.List;
import java.util.Stack;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }

class BTInorderTraversal {
    private List<Integer> result = new ArrayList<>();

    /**
     * 中序遍历（左根右），递归版本
     * 时间复杂度 T(n) = 2 * T(n/2) + 1 所以为 T(n)
     * 空间复杂度最差为 O(n)，平均为 O(logn)
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return result;
        inorderTraversal(root.left);
        result.add(root.val);
        inorderTraversal(root.right);
        return result;
    }

    private Stack<TreeNode> stack = new Stack<>();
    // 非递归
    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) return result;
        TreeNode cur = root;

        while (!stack.empty() || cur != null) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            result.add(cur.val);
            cur = cur.right;
            
        }

        return result;
    }
}