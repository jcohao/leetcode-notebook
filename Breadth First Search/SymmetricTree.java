import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.sun.corba.se.impl.orbutil.graph.Node;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


class SymmetricTree {
    /**
     * 判断一棵树是否是对称的
     * 
     * 递归解法：每次传入需要比较的两个子节点，当两个子节点都为空则返回 true，一个为空或者两个的值不相等则返回 false
     * 如果都不是以上的情况则左节点的左节点要与右节点的右子节点比较，左节点的右子节点要与右节点的左子节点比较，所以是或运算
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return helper(root.left, root.right);
    }


    private boolean helper(TreeNode left, TreeNode right) {
        // if (left == null && right == null) return true;
        // else if (left == null && right != null || left != null && right == null || left.val != right.val) return false;
        if (left == null || right == null) return left == right;
        else if (left.val != right != val) return false;

        return helper(left.left, right.right) && helper(left.right, right.left);
    }

    /**
     * 非递归解法：要用到层次遍历，但要考虑到怎么处理子节点为空的情况
     * 这里当数组为空时，加入一个 -1 做标记
     * 
     * 性能要比递归的差
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 1;
        int noEmpty = 1;

        while (!queue.isEmpty() && noEmpty != 0) {
            int count = 0;
            int empty = 0;
            List<Integer> list = new ArrayList<>();

            while (size != 0) {
                TreeNode node = queue.poll();
                size--;
                if (node == null) {
                    list.add(-1);
                    continue;
                } else {
                    list.add(node.val);
                }
                
                if (node.left != null) count++;
                else empty++;
                queue.offer(node.left);

                if (node.right != null) count++;
                else empty++;
                queue.offer(node.right);       
            }

            noEmpty = count;
            size = count + empty;
            if (!arrayIsSymmetric(list)) return false;

        }

        return true;
    }

    private boolean arrayIsSymmetric(List<Integer> list) {
        if (list == null || list.size() == 0) return true;

        int left = 0, right = list.size()-1;

        while (left < right) {
            if (list.get(left) != list.get(right)) return false;
            left++;
            right--;
        }

        return true;
    }

    /**
     * 其实也可以用栈来进行广度优先搜索，队列也可以，只要每次入队是对称的两个节点，出来也是对应的两个即可
    */
    public boolean isSymmetric3(TreeNode root) {
        if (root == null) return true;

        Stack<TreeNode> stack = new Stack<>();

        if (root.left == null || root.right == null) return root.left == root.right;

        stack.add(root.left);
        stack.add(root.right);
        

        while (!stack.isEmpty()) {
            TreeNode left = stack.pop();
            TreeNode right = stack.pop();

            if (left == null && right == null) continue;

            if (left == null || right == null || left.val != right.val) return false;

            stack.add(left.left);
            stack.add(right.right);
            stack.add(left.right);
            stack.add(right.left);
        }

        return true;
    }



    public static void main(String[] args) {
        SymmetricTree solution = new SymmetricTree();
        TreeNode n1 = new TreeNode(1);
        TreeNode l1 = new TreeNode(2);
        TreeNode r1 = new TreeNode(2);
        n1.left = l1;
        n1.right = r1;
        TreeNode l2 = new TreeNode(2);
        TreeNode l3 = new TreeNode(2);
        l1.left = l2;
        r1.left = l3;
        System.out.println(solution.isSymmetric2(n1));
        
    }
}