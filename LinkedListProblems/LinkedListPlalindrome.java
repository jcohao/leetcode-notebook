class LinkNode {
    int val;
    LinkNode next;

    public LinkNode(int val, LinkNode next) {
        this.val = val;
        this.next = next;
    }

    public LinkNode(int val) {
        this.val = val;
    }
}


class LinkedListPlalindrome {
    /**
     * 要判断链表是否是回文的其实可以把整个链表的值复制到数组里面，然后判断是否为回文
     * 这样时间复杂度就为 O(n) 空间复杂度也为 O(n)
     * 如果要原地判断空间复杂度为 O(1) 的话，就用双循环遍历链表，这样时间复杂度就是 O(n*n)
     */
    public boolean plalindrome(LinkNode node) {
        if (node == null) return false;

        int len = 0;

        LinkNode cur = node;

        while (cur != null) {
            len++;
            cur = cur.next;
        }

        if (len == 1) return true;

        cur = node;
        for (int i = 0; i < len/2; i++) {
            LinkNode comp = node;
            for (int j = 0; j < len - i; j++) {
                comp = comp.next;
            }

            if (cur.val != comp.val) return false;

            cur = cur.next;
        }

        return true;
    }
}