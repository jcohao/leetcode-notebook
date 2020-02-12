import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


class BTZigZagLevelOrderTraversal {
    /**
     * 这个题主要还是用层次遍历法去做，只是要加一个变量标记是奇数层还是偶数层，
     * 如果是奇数层的话，节点的值从尾部加入列表
     * 如果是偶数层，节点的值从头部加入列表
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        int level = 1;

        int count = 1;

        while (!queue.isEmpty()) {
            List<Integer> temp = new LinkedList<>();
            while (count != 0) {
                TreeNode node = queue.poll();

                if (level % 2 != 0) {
                    temp.add(node.val);
                } else {
                    temp.add(0, node.val);
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                count--;
            }

            count = queue.size();
            level++;
            result.add(temp);
        }

        return result;

    }

    /**
     * 递归解法
     */
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        if (root == null) return result;

        levelTraversal(root, 1);

        return result;
    }

    private void levelTraversal(TreeNode node, int level) {
        if (node == null) return;

        if (level > result.size()) {
            result.add(new ArrayList<>());
        }

        if (level % 2 != 0) {
            result.get(level-1).add(node.val);
        } else {
            result.get(level-1).add(0, node.val);
        }

        levelTraversal(node.left, level+1);
        levelTraversal(node.right, level+1);
    }

}