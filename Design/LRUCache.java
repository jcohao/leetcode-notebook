import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

class Node {
    int key;
    int value;

    Node next;
    Node pre;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

/**
 * 用双链表去记录节点的顺序
 * 用 hashmap 去记录键值
 * 一旦有节点过期，则 remove 掉该有的键
 */


class LRUCache {

    private Map<Integer, Node> map;

    private Node head;
    private Node tail;

    private int size;
    private int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tai.pre = head;

        size = 0;
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);

            removeNode(node);
            addNode(node);
            return node.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        Node node;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            removeNode(node);
            addNode(node);
        } else {
            node = new Node(key, value);
            map.put(key, node);
            addNode(node);
            size++;
        }


        if (size > capacity) {
            Node temp = tail.pre;
            removeNode(temp);
            map.remove(temp.key);
            temp = null;   
            size--;
        }
    }

    private void addNode(Node node) {
        node.next = head.next;
        node.pre = head;

        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }


    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(1, 2);  
        cache.put(2, 3);   
        cache.put(4, 1); 
        System.out.println(cache.get(1));      
        System.out.println(cache.get(2));
    }
}



// class LRUCache {
//     private LinkedHashMap<Integer, Integer> map;

//     LRUCache(int capacity) {
//         map = new LinkedHashMap<>(capacity, 0.75f, true) {
//             protected boolean removeEldestEntry(Map.Entry eldest) {
//                 return size() > capacity;
//             }
//         };
//     }

//     public int get(int key) {
//         return map.getOrDefault(key, -1);
//     }

//     public void put(int key, int value) {
//         map.put(key, value);
//     }
// }