import java.util.LinkedList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class FlattenBT2LinkedList {

    private List<TreeNode> queue = new LinkedList<>();

    /**
     * 该题为层遍历的变形
     * 按层遍历的顺序去遍历树节点，左子树为空则返回，左子树不为空，
     * 则寻找左子树中的最大节点（就是左子树最右下角那个），
     * 找到之后，该节点的右节点指向当前遍历节点的右节点，
     * 当前遍历的右节点指向其左节点，
     * 然后其左节点置空，完了
     */
    public void flatten2(TreeNode root) {
        if (root == null) return;

        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove(0);
            helper(node);
        }
    }


    private void helper(TreeNode node) {
        if (node.left != null) {
            queue.add(node.left);

            if (node.right != null) {
                queue.add(node.right);
            }

            TreeNode rightMost = node.left;
            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }

            rightMost.right = node.right;
            node.right = node.left;
            node.left = null;
        } else {
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    /**
     * 弄平了之后为先序遍历顺序，使用先序遍历的方法来解决
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left != null) flatten(root.left);
        if (root.right != null) flatten(root.right);

        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;

        while (root.right != null) root = root.right;
        root.right = temp;
    }

    /**
     * 先序的非递归版本
     */
    public void flatten1(TreeNode root) {
        if (root == null) return;

        while (root != null) {
            if (root.left != null) {
                TreeNode p = root.left;
                while (p.right != null) {
                    p = p.right;
                }

                p.right = root.right;
                root.right = root.left;
                root.left = null;
            }

            root = root.right;
        }
    }


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
        n1.right = n5;

        n2.left = n3;
        n2.right = n4;

        n5.right = n6;

        FlattenBT2LinkedList solution = new FlattenBT2LinkedList();
        solution.flatten(n1);
    }
}