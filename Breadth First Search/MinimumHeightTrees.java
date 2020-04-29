import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    int val;
    List<Node> link;

    Node(int val) {
        this.val = val;
        this.link = new ArrayList<>();
    }
}

class MinimumHeightTrees {
    private List<Integer> result = new ArrayList<>();
    // TLE
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 0) return result;

        int min = Integer.MAX_VALUE;
        Node[] nodes = new Node[n];

        for (int[] edge : edges) {
            if (nodes[edge[0]] == null) nodes[edge[0]] = new Node(edge[0]);
            if (nodes[edge[1]] == null) nodes[edge[1]] = new Node(edge[1]);

            nodes[edge[0]].link.add(nodes[edge[1]]);
            nodes[edge[1]].link.add(nodes[edge[0]]);
        }

        for (Node node : nodes) {
            int mht = findMin(node, n);
            if (mht < min) {
                min = mht;
                result.clear();
                result.add(node.val);
            } else if (mht == min) {
                result.add(node.val);
            }
        }

        return result;
    }


    private int findMin(Node node, int n) {
        if (node == null) return 0;

        boolean[] visited = new boolean[n];

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        int height = 0, count = 1;

        while (!queue.isEmpty()) {
            while (count > 0) {
                Node t = queue.poll();
                visited[t.val] = true;
                count--;
                for (Node l : t.link) {
                    if (!visited[l.val]) queue.add(l);
                }
            }
           
            count = queue.size();
            if (count == 0) break;
            height++;
        }

        return height;

    }

    /**
     * 这个方法用了剪枝的方法，每次把树的叶子节点给去掉，当结点的数目少于 2 时，则剩下的节点就是 MHT 的根结点
     */
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);

        boolean[][] graph = new boolean[n][n];

        int[] count = new int[n];

        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = true;
            graph[edge[1]][edge[0]] = true;

            count[edge[0]]++;
            count[edge[1]]++;
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (count[i] == 1) list.add(i);
        }

        while (n > 2) {
            n -= list.size();

            List<Integer> newList = new ArrayList<>();

            for (int num : list) {
                for (int link = 0; link < graph[num].length; link++) {
                    if (graph[num][link]) {
                        count[link]--;
                        graph[link][num] = false;
                        if (count[link] == 1) newList.add(link);
                        break;
                    }
                } 
            }
            
            list = newList;
        }

        return list;
    }

    public static void main(String[] args) {
        MinimumHeightTrees solution = new MinimumHeightTrees();

        System.out.println(solution.findMinHeightTrees1(2, new int[][]{{0, 1}}).toString());
    }
}