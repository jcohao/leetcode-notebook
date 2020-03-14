class Add2NumbersII {
    /**
     * 这道题跟上一道题的不同在于链表头部表示高位，尾部表示低位
     * 这里用了将链表的值复制到数组当中然后最后进行头插
     * 时间和空间复杂度都为 O(l1 + l2)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode cur1 = l1, cur2 = l2;
        
        int len1 = 0, len2 = 0;
        
        while (cur1 != null) {
            len1++;
            cur1 = cur1.next;
        }
        
        while (cur2 != null) {
            len2++;
            cur2 = cur2.next;
        }
        
        ListNode longList = null;
        ListNode shortList = null;
        
        if (len1 > len2) {
            longList = l1;
            shortList = l2;
        } else {
            longList = l2;
            shortList = l1;
        }
        
        int longLen = Math.max(len1, len2);
        int shortLen = Math.min(len1, len2);
        
        int[] longs = new int[longLen];
        int[] shorts = new int[longLen];
        
        for (int i = 0; i < longLen; i++) {
            longs[i] = longList.val;
            longList = longList.next;
        }
        
        for (int i = longLen - shortLen; i < longLen; i++) {
            shorts[i] = shortList.val;
            shortList = shortList.next;
        }
        
        ListNode head = new ListNode(0), cur = head;
        int carry = 0, sum = 0;
        
        for (int i = longLen - 1; i >= 0; i--) {
            sum = longs[i] + shorts[i] + carry;
            carry = sum / 10;
            ListNode temp = new ListNode(sum % 10);
            temp.next = head.next;
            head.next = temp;
        }
        
        if (carry != 0) {
            ListNode temp = new ListNode(carry);
            temp.next = head.next;
            head.next = temp;
        }
        
        return head.next;
    }
}