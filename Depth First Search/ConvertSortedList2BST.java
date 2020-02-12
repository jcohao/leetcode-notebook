class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class ConvertSortedList2BST {
    public TreeNode sortedListToBSTWrong(ListNode head) {
        if (head == null) return null;

        TreeNode root = new TreeNode(head.val);
        head = head.next;

        int count = 0;
        while (head != null) {
            TreeNode node = new TreeNode(head.val);
            head = head.next;
            if (count != 1) {
                TreeNode cur = root;
                while (cur.right != null) {
                    cur = cur.right;
                }
                cur.right = node;
                count++;
            } else {
                TreeNode newRoot = root.right;
                root.right = null;
                newRoot.left = root;
                node.left = newRoot.right;
                newRoot.right = node;
                root = newRoot;
                count = 0;
            }
        }

        return root;

    }


    /**
     * 每次找出链表的中间节点，以中间节点作为树根
     * 然后在递归中间节点两边的链表，继续找中间节点
     * 返回后则为上层中间节点的左子树或右子树节点
     * 寻找中间节点可以使用快慢指针遍历链表
     * 
     * 时间复杂度为 O(NlogN) 找中间节点的时间复杂度为 O(N) 构建树的时间复杂度为 O(logN)
     * 空间复杂度为 O(logN) 栈的空间占用
     */
    private ListNode findMiddleNode(ListNode head) {
        ListNode pre = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (pre != null) pre.next = null;

        return slow;
    }


    public TreeNode sortedListToBST1(ListNode head) {
        if (head == null) return null;

        ListNode mid = findMiddleNode(head);
        TreeNode node = new TreeNode(mid.val);

        if (mid == head) return node;

        node.left = sortedListToBST(head);
        node.right = sortedListToBST(mid.next);

        return node;
    }



    /**
     * 可以先把链表转换为数组，以减少寻找中间节点的时间
     * 时间复杂度为 O(n) 空间复杂度为 O(n)
     */
    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) return null;

        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val);
            head = head.next;
        }

        TreeNode root = constructTreeNode(list, 0, list.size()-1);

        return root;
    }

    private TreeNode constructTreeNode(List<Integer> list, int left, int right) {
        if (left > right) return null;

        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(list.get(mid));

        if (left == right) return node;

        node.left = constructTreeNode(list, left, mid-1);
        node.right = constructTreeNode(list, mid+1, right);

        return node;
    }



    /**
     * 利用中序遍历的方法去构建 BST
     * 时间复杂度为 O(n) 遍历链表  空间复杂度为 O(logn) 栈的调用
     */
    private ListNode head;


    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;

        this.head = head;

        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            size++;
        }

        return convertList2BST(0, size-1);
    }


    private TreeNode convertList2BST(int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
 
        TreeNode left = convertList2BST(start, mid-1);

        TreeNode node = new TreeNode(head.val);
        
        node.left = left;
        head = head.next;

        node.right = convertList2BST(mid+1, end);

        return node;

    }




    public static void main(String[] args) {
        ListNode n1 = new ListNode(-10);
        ListNode n2 = new ListNode(-3);
        ListNode n3 = new ListNode(0);
        ListNode n4 = new ListNode(5);
        ListNode n5 = new ListNode(9);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ConvertSortedList2BST solution = new ConvertSortedList2BST();
        solution.sortedListToBST(n1);
    }
} 