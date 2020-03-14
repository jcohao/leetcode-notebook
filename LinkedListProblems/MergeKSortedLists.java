import java.util.List;
import java.util.PriorityQueue;

class MergeKSortedLists {
    /**
     * 使用优先队列来保存节点的值，每次 poll 出最小的节点值
     * 时间复杂度为 O(NlogN) 空间复杂度为 O(N)
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        else if (lists.length == 1) return lists[0];

        Queue<Integer> queue = new PriorityQueue<>();

        for (ListNode list : lists) {
            while (list != null) {
                queue.offer(list.val);
                list = list.next;
            }
        }

        ListNode dummy = new ListNode(0), cur = dummy;

        while (!queue.isEmpty()) {
            int num = queue.poll();

            cur.next = new ListNode(num);
            cur = cur.next;
        }

        return dummy.next;

    }

    /**
     * 用数组存所有节点的值，然后排序，最后再链成链表
     * 时间复杂度为 O(NlogN) (排序的时间复杂度为 O(NlogN))
     * 空间复杂度为 O(N) 
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        else if (lists.length == 1) return lists[0];

        List<Integer> nums = new ArrayList<>();

        for (ListNode list : lists) {
            while (list != null) {
                nums.add(list.val);
                list = list.next;
            }
        }

        nums.sort((a, b) -> (a - b));

        ListNode dummy = new ListNode(0), cur = dummy;

        for (int num : nums) {
            cur.next = new ListNode(num);
            cur = cur.next;
        }

        return dummy.next;
    }

    /**
     * 用多个指针去遍历对应的链表，每次决出最小的那个链到最终链表上
     * 时间复杂度为 O(l * N) l 为 lists 的长度，空间复杂度为 O(1)
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        else if (lists.length == 1) return lists[0];

        ListNode dummy = new ListNode(0), cur = dummy;

        int count = 0;

        while (count < lists.length) {
            int min = findmin(lists);

            cur.next = lists[min];
            cur = cur.next;
            lists[min] = lists[min].next;

            if (lists[min] == null) count++;
        }

        return dummy.next;
    }

    private int findmin(ListNode[] lists) {
        ListNode min = new ListNode(Integer.MAX_VALUE);
        int index = -1;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) continue;

            if (lists[i].val < min.val) {
                min = lists[i];
                index = i;
            } 
        }

        return index;
    }
}