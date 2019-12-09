import java.util.LinkedList;
import java.util.Queue;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

class PopulatingNextRightPointers {
    // 树的层次遍历，左子节点的 next 直接指向同一父亲的右子节点
    // 右子节点的 next 则取决于父节点的 next 是否为空
    // 如果为空则置空，不为空则指向父节点 next 的左子节点
    public Node connect1(Node root) {
        if (root == null) return root;

        Queue<Node> queue = new LinkedList<>();

        queue.add(e);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size > 0) {
                Node cur = queue.poll();
                if (cur.left != null) {
                    cur.left.next = cur.right;
                    cur.right.next = (cur.next == null) ? null : cur.next.left;

                    queue.add(cur.left);
                    queue.add(cur.right);
                    size--;
                } else {
                    queue.clear();
                    break;
                }
            }
        }

        return root;
    }


    /**
     * 递归的做法
     */
    public Node connect(Node root) {
        if (root == null) return null;
        if (root.left != null) root.left.next = root.right;
        if (root.right != null) root.right.next = (root.next == null) ? null : root.next.left;
        connect(root.left);
        connect(root.right);
        return root;
    }

    /**
     * 使用两个指针，start 指向每一层的起始节点，cur 用来遍历该层的节点
     */
    public Node connect2(Node root) {
        if (root == null) return null;

        Node start = root, cur = null;

        while (start.left != null) {
            cur = start;

            while (cur != null) {
                cur.left.next = cur.right;
                if (cur.next != null) cur.right.next = cur.next.left;
                cur = cur.next;
            }
            start = start.left;
        }

        return root;
    }
}