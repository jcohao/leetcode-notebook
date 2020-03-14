class RemoveNthNodeFromEnd {
    /**
     * 注意头结点的处理
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) return null;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        
        while (n != 0) {
            fast = fast.next;
            n--;
        }
        
        if (fast == null) return head;
        
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        slow.next = slow.next.next;
        
        return dummy.next;
    }
}