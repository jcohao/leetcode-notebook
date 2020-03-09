import java.util.List;
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


class PopulatingNextRightPointers2 {
    // 这一题与版本一的区别在于不仅限于完全二叉树了
    public Node connect1(Node root) {
        if (root == null) return null;

        Node start = root, cur = null;

        List<Node> list = new ArrayList<>();

        while (start != null) {
            cur = start;
            while (cur != null) {
                if (cur.left != null) list.add(cur.left);
                if (cur.right != null) list.add(cur.right);

                cur = cur.next;
            }

            for (int i = 0; i < list.size()-1; i++) {
                list.get(i).next = list.get(i+1);
            }

            start = (list.size() == 0) ? null : list.get(0);
            list.clear();

        }


        return root;
    }


    /**
     * 递归
     */
    public Node connect(Node root) {
        if (root == null) return null;

        // 这一层是连的下一层节点，这一层的节点已经连好了
        Node p = root.next;

        while (p != null) {
            if (p.left != null) {
                p = p.left;
                break;
            }

            if (p.right != null) {
                p = p.right;
                break;
            }

            p = p.next;
        }

        if (root.right != null) root.right.next = p;
        if (root.left != null) root.left.next = (root.right == null) ? p : root.right;

        // 必须先迭代右子树，不然左子树想通过 next 去遍历下一个节点却发现 next 还没有连上
        connect(root.right);
        connect(root.left);
        

        return root;
    }


    public Node connect2(Node root) {
        Node dummy = new Node(0), cur = dummy, head = root;

        while (root != null) {
            // 当前层连的是下一层的节点

            if (root.left != null) {
                cur.next = root.left;
                cur = cur.next;
            }

            if (root.right != null) {
                cur.next = root.right;
                cur = cur.next;
            }

            root = root.next;

            if (root == null) {
                cur = dummy;
                root = dummy.next;
                dummy.next = null;
            }
        }

        return head;
    } 

    public static void main(String[] args) {
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node7 = new Node(7);

        Node node2 = new Node(2, node4, node5, null);
        Node node3 = new Node(3, null, node7, null);
        Node node1 = new Node(1, node2, node3, null);

        PopulatingNextRightPointers2 solution = new PopulatingNextRightPointers2();
        solution.connect2(node1);
    }
}