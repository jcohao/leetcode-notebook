import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class BSTIterator {

    private List<Integer> values;

    private int iter = 0;

    /**
     * 对二叉搜索树进行中序遍历得出的序列是递增的
     */
    public BSTIterator(TreeNode root) {
        values = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.add(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            values.add(cur.val);
            cur = cur.right;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        if (iter == values.size()) return null;

        return values.get(iter++);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return iter != values.size();
    }
}


/**
 * 自定义能够随便停止和递归的栈
 */
class BSTIterator2 {
    Stack<TreeNode> stack;

    public BSTIterator2(TreeNode root) {
        stack = new Stack<>();

        // 初始化时，先将根结点沿着左边一直到最小的节点都推入栈，此时栈顶为最小的节点
        leftmostInorder(root);
    }

    private void leftmostInorder(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();

        if (node.right != null) leftmostInorder(node.right);

        return node.val;
    }

    public boolean hasNext() {
        return stack.size() > 0;
    }


}