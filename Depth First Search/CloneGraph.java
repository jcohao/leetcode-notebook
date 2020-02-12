import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};

class CloneGraph {
    /**
     * 要复制整个图，只需要一个一个节点进行复制即可
     * 但每一个节点里面保存了其邻接节点的列表
     * 在复制节点的同时要一同复制该列表
     * 为了记录要复制的节点，将需要复制的节点放到队列中
     * 一个一个 poll 出来进行赋值，并且用列表记录已经处理过的节点
     * 防止重复复制
     * 为了更容易得获取节点，创建一个 map 去记录每一个已创建的节点
     * 
     * 这种方法相当于 BFS 解法了
     */
    public Node cloneGraph1(Node node) {
        if (node == null) return null;
        
        Map<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        boolean[] visited = new boolean[101];

        queue.add(node);

        int headNum = node.val;
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            visited[cur.val] = true;
            Node n;
            if (!map.containsKey(cur.val)) {
                n = new Node(node.val, new ArrayList<>());
                map.put(n.val, n);
            } else {
                n = map.get(cur.val);
            }
            

            for (Node neighbor : cur.neighbors) {
                Node temp;
                if (map.containsKey(neighbor.val)) {
                    temp = map.get(neighbor.val);
                } else {
                    temp = new Node(neighbor.val, new ArrayList<>());
                    map.put(temp.val, temp);
                }
                // 入队操作不仅要判断是否还没遍历过，还得看这个节点在不在队列里面
                // 如果在的话就不加了，不然就重复复制了
                if (!visited[neighbor.val] && !queue.contains(neighbor)) queue.add(neighbor);
                n.neighbors.add(temp);
            }
        }

        return map.get(headNum);
    }


    /**
     * DFS 解法
     */
    private Map<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        return cloneNode(node);
    }

    private Node cloneNode(Node node) {
        if (node == null) return null;

        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }

        Node clone = new Node(node.val, new ArrayList<>());

        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(cloneNode(neighbor));
        }

        return clone;
    }
    

    public static void main(String[] args) {
        CloneGraph solution = new CloneGraph();

        Node n1 = new Node(1, new ArrayList<>());
        Node n2 = new Node(2, new ArrayList<>());
        Node n3 = new Node(3, new ArrayList<>());
        Node n4 = new Node(4, new ArrayList<>());

        n1.neighbors.add(n2);
        n1.neighbors.add(n4);
        n2.neighbors.add(n1);
        n2.neighbors.add(n3);
        n3.neighbors.add(n2);
        n3.neighbors.add(n4);
        n4.neighbors.add(n1);
        n4.neighbors.add(n3);

        solution.cloneGraph(n1);
    }
}

