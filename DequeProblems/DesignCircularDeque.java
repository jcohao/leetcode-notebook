class MyCircularDeque {
    // 双端队列的最大容量
    private int capacity;
    // 实际容量
    private int size;
    // 队头指针
    private Node head;
    // 队尾指针
    private Node tail;

    // 使用双链表去实现
    class Node {
        int val;
        Node next;
        Node pre;

        Node(int val) {
            this.val = val;
        }
    }
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.capacity = k;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        
        Node node = new Node(value);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.pre = node;
            head = node;
        }
        
        size++;
        return true;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) return false;
        Node node = new Node(value);

        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.pre = tail;
            tail.next = node;
            tail = node;
        }
        
        size++;
        return true;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) return false;

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.pre = null;
        }

        size--;
        return true;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) return false;

        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.pre;
            tail.next = null;
        }

        size--;
        return true;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) return -1;
        else return head.val;
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) return -1;
        else return tail.val;
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return capacity == size;
    }
}

public class DesignCircularDeque {
    public static void main(String[] args) {
        
        // Your MyCircularDeque object will be instantiated and called as such:
        MyCircularDeque obj = new MyCircularDeque(3);
        boolean param_1 = obj.insertFront(1);
        boolean param_2 = obj.insertLast(2);
        // boolean param_3 = obj.deleteFront();
        // boolean param_4 = obj.deleteLast();
        int param_5 = obj.getFront();
        int param_6 = obj.getRear();
        boolean param_7 = obj.isEmpty();
        boolean param_8 = obj.isFull();
        System.out.println(param_1);
        System.out.println(param_2);
        // System.out.println(param_3);
        // System.out.println(param_4);
        System.out.println(param_5);
        System.out.println(param_6);
        System.out.println(param_7);
        System.out.println(param_8);
    }
}