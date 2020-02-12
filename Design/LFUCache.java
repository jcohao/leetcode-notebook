import java.util.HashMap;
import java.util.Map;

class Node {
    int key;
    int value;
    int frequency;

    Node next;
    Node pre;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;

        this.frequency = 1;
    }
}


class LFUCache {
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;


    private int capacity;
    private int size = 0;

    public LFUCache(int capacity) {
        this.capacity = capacity;

        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.pre = head;
        head.frequency = 0;
        tail.frequency = 0;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.frequency++;
            updatePos(node);
            return node.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            // 如果包含则只需要更新节点的 frequency 值，以及它的位置
            Node node = map.get(key);
            node.value = value;
            node.frequency++;
            updatePos(node);
        } else {
            // 如果不包含则要判断容量是否已满，满了的话还得先去掉最后一个
            Node node = new Node(key, value);

            if (size == capacity && capacity != 0) {
                removeTail();
                addTail(node);
                updatePos(node);
            } else if (size < capacity) {
                size++;
                addTail(node);
                updatePos(node);
            } 
        }
    }

    private void updatePos(Node node) {
        while (node.frequency >= node.pre.frequency && node.pre.frequency != 0) {
            Node pre = node.pre;
            node.next.pre = pre;
            pre.next = node.next;
            pre.pre.next = node;
            node.pre = pre.pre;
            node.next = pre;
            pre.pre = node;
        }
    }

    private void addTail(Node node) {
        node.pre = tail.pre;
        tail.pre.next = node;
        node.next = tail;
        tail.pre = node;
        map.put(node.key, node);
    }

    private void removeTail() {
        Node node = tail.pre;
        tail.pre = node.pre;
        node.pre.next = tail;
        map.remove(node.key);
        node = null;
    }


    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));

        // cache.put(1, 1);
        // cache.put(2, 2);
        // System.out.println(cache.get(1));
        // cache.put(3, 3);
        // System.out.println(cache.get(2));
        // System.out.println(cache.get(3));
        // cache.put(4, 4);
        // System.out.println(cache.get(1));
        // System.out.println(cache.get(3));
        // System.out.println(cache.get(4));
    }


}