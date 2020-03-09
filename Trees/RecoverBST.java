import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class RecoverBST {
    /**
     * 这种解法是首先对其进行中序遍历，将节点存在数组当中
     * 然后再将数组中结点的值进行排序
     * 时间复杂度由排序方式决定，这里用的冒泡，所以为 O(n*n)
     * 空间复杂度为 O(n)
     */
    public void recoverTree(TreeNode root) {
        if (root == null) return;

        List<TreeNode> nodes = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.add(root);
                root = root.left;
            }

            root = stack.pop();
            nodes.add(root);
            root = root.right;
        }

        for (int i = 0; i < nodes.size()-1; i++) {
            for (int j = 0; j < nodes.size()-1-i; j++) {
                if (nodes.get(j).val > nodes.get(j+1).val) {
                    int temp = nodes.get(j).val;
                    nodes.get(j).val = nodes.get(j+1).val;
                    nodes.get(j+1).val = temp;
                }
            }
        }
    }

    /**
     * 例子 1 5 3 4 2 6 
     * 假设前一个遍历的节点为 preNode 现在遍历的节点为 curNode 
     * 当第一次出现 preNode.val > curNode.val 时，有问题的节点为 preNode
     * 当第二次出现 preNode.val > curNode.val 时，有问题的节点为 curNode
     * 然后直接交换这两个节点的值即可
     * 
     * 这里用到 Morris Traversal 来保证空间复杂度为 O(1)
     */
    public void morrisTraversal(TreeNode root) {
        TreeNode temp = null;
        while (root != null) {
            if (root.left != null) {
                temp = root.left;
                // 找到最右节点，且这个节点不能是 root 节点
                while (temp.right != null && temp.right != root) temp = temp.right;
                // 最右节点不为 null，肯定指向 root，为第二次访问该节点，进行拆桥
                if (temp.right != null) {
                    temp.right = null;
                    // visited node
                    System.out.println(root.val);
                    root = root.right;
                } else {
                    // 为 null 则为第一次访问，建桥
                    temp.right = root;
                    root = root.left;
                }
            } else {
                // visited node
                System.out.println(root.val);
                root = root.right;
            }
        }
    }


    public void recoverTree1(TreeNode root) {
        if (root == null) return;

        TreeNode preNode = new TreeNode(Integer.MIN_VALUE);
        TreeNode firstNode = null;  // 第一个要修正的节点
        TreeNode secondNode = null; // 第二个要修正的节点

        // morris traversal
        TreeNode temp = null;
        while (root != null) {
            if (root.left != null) {
                temp = root.left;
                while (temp.right != null && temp.right != root) temp = temp.right;

                if (temp.right == null) {
                    temp.right = root;
                    root = root.left;
                } else {
                    temp.right = null;
                    // visited
                    System.out.println(root.val);
                    if (preNode.val > root.val) {
                        if (firstNode == null) {
                            firstNode = preNode;
                            // 并列两个节点交换的情况
                            secondNode = root;
                        } else {
                            secondNode = root;
                        }
                    }
                    preNode = root;
                    root = root.right;
                }
            } else {
                // visited
                System.out.println(root.val);
                if (preNode.val > root.val) {
                    if (firstNode == null) {
                        firstNode = preNode;
                        secondNode = root;
                    } else {
                        secondNode = root;
                    }
                }
                preNode = root;
                root = root.right;
            }
        }

        // swap 
        int tempVal = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tempVal;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode n2 = new TreeNode(1);
        TreeNode n5 = new TreeNode(4);
        root.left = n2;
        root.right = n5;
        TreeNode n1 = new TreeNode(2);
        
        n5.left = n1;
        

        RecoverBST solution = new RecoverBST();
        solution.recoverTree1(root);
    }
}