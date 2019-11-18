class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}


class Merge2SortedLists {
    /**
     * 由于两个链表是有序的，只需每次比较 l1 和 l2 值的大小，较小的就接到结果链上
     * 然后该链的指针指到下一个结点处，直到有一个指针指向为空循环结束，
     * 此时，肯定会有一条链是还没有遍历完的，将还没遍历完的链接到结果链尾即可
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        else if (l2 == null) return l1;

        ListNode head = new ListNode(-1);

        ListNode cur = head;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;

                l1 = l1.next;
                cur = cur.next;
            } else {
                cur.next = l2;

                l2 = l2.next;
                cur = cur.next;
            }
        }

        if (l1 != null) {
            cur.next = l1;
        } else {
            cur.next = l2;
        }

        return head.next;

    }

    /**
     * 递归版本
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        else if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists2(l1.next, l2);
            // 返回当前节点，让上一层的 next 指针指向当前节点
            return l1;
        } else {
            l2.next = mergeTwoLists2(l1, l2.next);
            return l2;
        }
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        // ListNode l3 = new ListNode(4);
        l1.next = l2;
        // l2.next = l3;

        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(3);
        // ListNode r3 = new ListNode(4);
        r1.next = r2;
        // r2.next = r3;

        Merge2SortedLists solution = new Merge2SortedLists();
        ListNode head = solution.mergeTwoLists2(l1, r1);
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
    }
}