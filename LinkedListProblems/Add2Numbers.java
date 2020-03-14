class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}

class Add2Number {
    /**
     * 先计算出总和的话很容易会溢出
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;
        
        int sum = 0;
        
        while (l1 != null) {
            sum = sum * 10;
            sum += l1.val + l2.val;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        ListNode head = new ListNode(0);
        
        while (sum != 0) {
            ListNode temp = new ListNode(sum % 10);
            temp.next = head.next;
            head.next = temp;
            sum /= 10;
        }
        
        return head.next;
        
    }

    /**
     * 这种做法会修改到原来链表的结构
     * 时间复杂度为 O(l1 + l2) 空间复杂度为 O(min(l1, l2))
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) return null;
        
        int sum = 0, flag = 0;
        ListNode head = new ListNode(0), cur = head;
        
        
        while (l1 != null && l2 != null) {
            sum = flag + l1.val + l2.val;
            flag = sum  / 10;
            ListNode temp = new ListNode(sum % 10);
            cur.next = temp;
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        
        if (l1 != null) {
            cur.next = l1;
        }
        
        if (l2 != null) {
            cur.next = l2;
        }
        
        
        while (flag != 0) {
            if (cur.next != null) {
                sum = cur.next.val + flag;
                cur.next.val = sum % 10;
                flag = sum / 10;
                cur = cur.next;
            } else {
                ListNode temp = new ListNode(1);
                cur.next = temp;
                flag = 0;
            }
            
        }
        
        return head.next;
    }

    /**
     * 题目中的链表头部是低位，尾部是高位，所以尾插即可
     * 这里的时间复杂度和空间复杂度都是 O(max(l1, l2))
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        else if (l2 == null) return l1;

        ListNode head = new ListNode(0), cur = head;
        // 进位
        int carry = 0;

        while (l1 != null || l2 != null) {
            int left = (l1 == null) ? 0 : l1.val;
            int right = (l2 == null) ? 0 : l2.val;

            int sum = carry + left + right;
            cur.next = new ListNode(sum % 10);;

            carry = sum / 10;
            cur = cur.next;
            // 这里要进行判断，l1 l2 有可能为空
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry != 0) {
            cur.next = new ListNode(1);
        }

        return head.next;
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
        // Add2Number solution = new Add2Number();
        // int[] nums1 = {2, 4, 3};
        // int[] nums2 = {5, 6, 4};
        // ListNode l1 = solution.createList(nums1);
        // ListNode l2 = solution.createList(nums2);

        // solution.addTwoNumbers(l1, l2);

        double a = 100;

        System.out.println(a / 10);
    }
}