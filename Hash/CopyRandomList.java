import java.util.HashMap;
import java.util.Map;

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}

/**
 * 用 map 去记录创建过的节点
 * 然后递归调用创建节点的函数，
 * 首先是传入的节点是 null 的话则返回 null
 * 否则，先判断是不是已经创建过的节点，如果是则直接返回在 map 中的该节点
 * 如果没有创建过的，则创建节点，然后把节点记录到 map 中，设置 val 值
 * next 值和 random 值则调用创建节点函数，最后返回
 */
class CopyRandomList {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Map<Integer, Node> map = new HashMap<>();
        return helper(head, map);
    }


    private Node helper(Node head, Map<Integer, Node> map) {
        if (head == null) return null;

        if (map.containsKey(head.val)) {
            return map.get(head.val);
        } else {
            Node node = new Node();
            map.put(head.val, node);
            node.val = head.val;
            node.next = helper(head.next, map);
            node.random = helper(head.random, map);
            return node;
        }
    }


    /**
     * 以上方法的非递归版本，第一遍循环把所有的节点都复制一份，第二遍循环的时候才去设置next 和 random
     */
    public Node copyRandomList2(Node head) {
        if (head == null) return null;

        Map<Integer, Node> map = new HashMap<>();

        Node cur = head;
        Node result = new Node(cur.val, null, null);

        cur = cur.next;
        map.put(result.val, result);

        while (cur != null) {
            Node node = new Node(cur.val, null, null);
            map.put(node.val, node);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            Node node = map.get(cur.val);
            if (cur.next != null) node.next = map.get(cur.next.val);
            if (cur.random != null) node.random = map.get(cur.random.val);
            cur = cur.next;
        }

        return result;
    }

    /**
     * 第三种方法不需要使用使用 map 来记录已创建的节点，
     * 首先创建复制的节点，把它链在源节点的后面， 1(2)->2(2)->null   复制后  1(2)->1'()->2(2)->2'()->null
     * 然后遍历复制复制节点的random，断链
     */
    public Node copyRandomList3(Node head) {
        if (head == null) return null;

        Node cur = head;

        // 复制节点，并把复制节点链在原节点的后面
        while (cur != null) {
            Node node = new Node(cur.val, cur.next, null);
            cur.next = node;
            cur = cur.next.next;
        }

        // 设置复制节点的 random 值
        cur = head;
        while (cur != null) {
            // cur.next 为复制节点,将它的random节点设为，cur的random节点的下一个节点，则cur.random.next 
            // 则是 random 的复制节点
            // cur.random 有可能是 null
            if (cur.random != null) cur.next.random = cur.random.next;
            cur = cur.next.next;
        }

        Node resultCur = new Node(0, null, null);
        Node result = resultCur;
        // 断链
        cur = head;
        while (cur != null) {
            resultCur.next = cur.next;
            resultCur = resultCur.next;
            cur.next = resultCur.next;
            cur = cur.next;
        }

        return result.next;
    }


    public static void main(String[] args) {
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(2, null, null);

        // node1.next = node2;
        // node1.random = node2;
        // node2.random = node2;

        CopyRandomList solution = new CopyRandomList();
        System.out.println(solution.copyRandomList3(node1) == node1);
    }
}