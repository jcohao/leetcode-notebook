import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class UniqueBST2 {
    /**
     * 由于给出的序列是有序的 1...n
     * 所以当 i 作为根节点的时候，1...i-1 肯定是存在于其左子树中，无论什么形式
     * 而 i+1...n 肯定是存在于其右子树中
     * 所以可以用递归的形式，先找出左右子树的集合，然后再进行组合
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();

        return helper(1, n);
    }

    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> result = new ArrayList<>();
        if (start > end) {
            result.add(null);
            return result;
        } else if (start == end) {
            result.add(new TreeNode(start));
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i - 1);
            List<TreeNode> right = helper(i + 1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) { 
                    TreeNode root = new TreeNode(i);  
                    root.left = l;
                    root.right = r;
                    result.add(root);
                }
            }
            
        }

        return result;
    }

    public static void main(String[] args) {
        UniqueBST2 solution = new UniqueBST2();

        solution.generateTrees(3);
    }
}