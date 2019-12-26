import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}


class MergeKSortedLists {

    // 存到数组里面，进行排序，再接上链，时间复杂度 O(NlogN)



    // 每次找出 k 个链表中最小的元素，接到结果链表之后，然后该最小元素的链表指针往前一步
    // 时间复杂度 O(KN)
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(-1);

        if (lists == null || lists.length == 0) return result.next;

        ListNode cur = result;

        int pos = countMin(lists);

        while (pos != -1) {
            cur.next = lists[pos];
            lists[pos] = lists[pos].next;
            cur = cur.next;
            cur.next = null;
            pos = countMin(lists);
        }

        return result.next;
    }

    private int countMin(ListNode[] lists) {
        int min = Integer.MAX_VALUE, pos = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) continue;

            if (lists[i].val < min) {
                min = lists[i].val;
                pos = i;
            }
        }

        return pos;
    } 

    // 维护一个堆，每次加入节点都进行上浮
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ArrayList<ListNode> heap = new ArrayList<>();
        heap.add(new ListNode(-1));


        for (int i = 0; i < lists.length; i++) {
            ListNode cur = lists[i];


            while (cur != null) {
                heap.add(cur);
                cur = cur.next;
                swim(heap);
            }
        }

        for (int i = 1; i < heap.size()-1; i++) {
            heap.get(i).next = heap.get(i+1);
        }
        heap.get(heap.size()-1).next = null;

        return heap.get(1);
    }

    private void swim(ArrayList<ListNode> heap) {
        int k = heap.size()-1;
        while (k > 1 && heap.get(k/2).val > heap.get(k).val) {
            // 只更改里面的数，等最后再重新接上链
            int t = heap.get(k/2);
            heap.get(k/2).val = heap.get(k).val;
            heap.get(k).val = t;

            k = k/2; 
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(5);
        l1.next = l2;
        l2.next = l3;

        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(3);
        ListNode r3 = new ListNode(4);
        r1.next = r2;
        r2.next = r3;

        ListNode k1 = new ListNode(2);
        ListNode k2 = new ListNode(6);
        k1.next = k2;

        ListNode[] lists = {l1, r1, k1};

        MergeKSortedLists solution = new MergeKSortedLists();
        solution.mergeKLists2(lists);

    }
}