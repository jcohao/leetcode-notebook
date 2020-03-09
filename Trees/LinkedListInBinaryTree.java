class LinkedListInBinaryTree {
    /**
     * 这种解法通过不了的原因在于当遍历的节点超过了正确答案的节点才发现匹配错误时无法回头从正确的节点开始遍历
     * 考虑例子：
     * [1,2,1,2,3]
     * [1,null,2,null,1,2,null,1,null,null,2,null,3]
     */
    public boolean isSubPath1(ListNode head, TreeNode root) {
        if (head == null || root == null) return false;
        
        return helper(head, root, head, true);
    }
    
    private boolean helper(ListNode head, TreeNode node, ListNode cur, boolean isHead) {
        if (cur == null) return true;
        else if (node == null) return false;
        
        if (node.val == cur.val) {
            return helper(head, node.left, cur.next, false) || helper(head, node.right, cur.next, false);
        } else {
            if (!isHead) {
                return helper(head, node, head, true);
            } else {
                return helper(head, node.left, head, true) || helper(head, node.right, head, true);
            }
        }
    }


    /**
     * 所以应该递归从每一个节点开始进行 dfs 的匹配
     * 时间复杂度为 O(N * min(L, H)) N 树节点 H 树高 L 链长
     * 空间复杂度 O(H)
     */
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null || root == null) return false;

        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode head, TreeNode node) {
        if (head == null) return true;
        else if (node == null) return false;

        return head.val == node.val && (dfs(head.next, node.left) || dfs(head.next, node.right));

    }


    /**
     * dp 解法
     */
    
}