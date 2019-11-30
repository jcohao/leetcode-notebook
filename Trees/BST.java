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


}