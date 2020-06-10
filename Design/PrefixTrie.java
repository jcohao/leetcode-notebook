class Trie1 {
    
    private TreeNode root;

    // 利用二叉搜索树实现
    class TreeNode {
        String s;
        TreeNode left;
        TreeNode right;
        
        TreeNode(String s) {
            this.s = s;
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (root == null) root = new TreeNode(word);
        
        TreeNode cur = root;
        
        while (cur != null) {
            if (cur.s.equals(word)) {
                return;
            } else if (cur.s.compareTo(word) > 0) {
                if (cur.left == null) cur.left = new TreeNode(word);
                
                cur = cur.left;
            } else {
                if (cur.right == null) cur.right = new TreeNode(word);
                
                cur = cur.right;
            }
        }
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode cur = root;
        
        while (cur != null) {
            if (cur.s.equals(word)) return true;
            else if (cur.s.compareTo(word) > 0) cur = cur.left;
            else cur = cur.right;
        }
        
        return false;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode cur = root;
        
        while (cur != null) {
            if (cur.s.startsWith(prefix)) return true;
            else if (cur.s.compareTo(prefix) > 0) cur = cur.left;
            else cur = cur.right;
        }
        
        return false;
    }
}

/**
 * prefix tree 每个节点代表当前层有哪些字母，加入第一层有 a 字母，a 字母连着的下一层有 p， p 连着的下一层有 p，最后一个 p 为 end
 * 则这棵树中包含 app 这个字符串
 */
class TrieNode {
    private TrieNode[] links;

    private final int R = 26;

    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    public boolean containsKey(char ch) {
        return links[ch-'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch-'a'];
    }

    public void put(char ch, TrieNode node) {
        links[ch-'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }

}


class Trie {
    
    private TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    // 时间复杂度和空间复杂度都为 O(m)，m 为字符串的长度
    public void insert(String word) {
        if (word == null || word.length() == 0) return;

        TrieNode cur = root;

        for (int i = 0; i < word.length; i++) {
            char ch = word.charAt(i);

            if (!cur.containsKey(ch)) {
                cur.put(ch, new TrieNode());
            }

            cur = cur.get(ch);
        }

        cur.setEnd();
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        return contains(word, false);
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return contains(word, true);
    }

    // 时间复杂度为 O(m)
    private boolean contains(String word, boolean isPrefix) {
        if (word == null || word.length() == 0) return false;

        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (!cur.containsKey(ch)) return false;

            cur = cur.get(ch);
        }

        return isPrefix ? true : cur.isEnd();
    }
}