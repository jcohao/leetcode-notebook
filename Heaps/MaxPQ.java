// 当一棵二叉树的每个结点都大于等于它的子结点时，这棵树是堆有序的
// 二叉堆是一组能够用堆有序的完全二叉树排序的元素，并在数组中按照层级存储
// （不用数组的第一个元素）
// 在一个堆中，位置 k 的结点的父结点的位置为 k/2 向下取整，而它的两个子结点的位置分别为 2k 和 2k+1
public class MaxPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN+1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);

        return max;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]);
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }


    // 上浮
    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    // 下沉
    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            // 上位小于下位的元素就要换，小于最大那个就要换
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
}