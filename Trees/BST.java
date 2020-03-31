import java.util.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    // 基于二叉查找树的符号表
    private Node root;      // 二叉查找树根结点

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;      // 以该结点为根的子树中的结点总数

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    public void put(Key key, Value val) {
        // 查找 key，找到则更新值，否则为它创建新结点
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        
        if (x == null) {
            return Node(key, val, 1);
        } else {
            int cmp = key.compareTo(x.key);

            if (cmp < 0) {
                x.left = put(x.left, key, val);
            } else if (cmp > 0) {
                x.right = put(x.right, key, val);
            } else {
                x.val = val;
            }

            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    // 找小于等于 key 的最大 key
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        // 如果是等于，则就是所要找的节点
        if (cmp == 0) return x;
        // 如果是小于，则需要找的节点在左子树上
        if (cmp < 0) return floor(x.left, key);
        // 如果是大于，则需要找的节点可能在右子树上，如果找不到则为 x
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    // 找大于等于 key 的最小 key
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        else return x;
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    // 返回排名为 k 的节点，排名从 0 开始
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        // t > k 则需要找的节点在左子树中
        if (t > k) return select(x.left, k);
        // t < k 则需要找的节点在右子树中，找右子树中排名为 k-t-1 的节点
        // k-t-1  t (左子树节点数)  1 (根结点)
        else if (t < k) return select(x.right, k-t-1);
        // t == k 则返回当前节点
        else return x;
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    // 返回以 x 为根结点的子树小于 x.key 的键的数量
    // 这里第一个节点或者为空节点都返回顺序 0
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
    }


    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    // 使用被删除节点的右子树中的最小节点来替换
    // 这种方法的删除不能保证树的对称性
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.add(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

}