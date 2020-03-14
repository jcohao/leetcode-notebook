class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}


class ReverseNodesInKGroup {
    /**
     * 时间复杂度为 O(kN)， 空间复杂度为 O(1)
     */
    public ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) return head;

        ListNode dummy = new ListNode(0), cur = dummy;
        ListNode start = head, nextStart;

        while (start != null) {
            ListNode p = start;

            // 数一下，看剩余的链表长度是否大于或等于 k 
            for (int i = 1; i < k; i++) {
                if (p != null) p = p.next;
            }

            // 如果是小于 k，此时剩下的链表直接连到 cur 后面即可
            if (p == null) {
                cur.next = start;
                break;
            }

            
            // 如果不小于，用 nextStart 记录下次需要遍历的位置
            nextStart = p.next;
            // 断开需要反转的链表
            p.next = null;
            p = start;

            // 遍历需要反转的链表，使用头插法插入到 cur 后面
            while (p != null) {
                ListNode temp = p.next;

                p.next = cur.next;
                cur.next = p;

                p = temp;
            }

            // 此时的 start 为反转后链表的最后一个节点，赋值给 cur
            cur = start;

            start = nextStart;
            
        }

        return dummy.next;
    }


    /**
     * 递归版本
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;
        while (cur != null && count != k) {
            cur = cur.next;
            count++;
        }

        if (count == k) {
            cur = reverseKGroup(cur, k);

            while (count > 0) {
                ListNode temp = head.next;
                head.next = cur;
                cur = head;
                head = temp;
            }
            head = cur;
        }

        return head;
    }

    private ListNode createList(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        ListNode head = new ListNode(0), cur = head;

        for (int num : nums) {
            ListNode temp = new ListNode(num);
            cur.next = temp;
            cur = cur.next;
        }

        return head.next;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        ReverseNodesInKGroup solution = new ReverseNodesInKGroup();

        ListNode head = solution.createList(nums);

        solution.reverseKGroup(head, 2);
    }
}