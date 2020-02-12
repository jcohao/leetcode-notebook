class RemovePalindromicSubsequences {
    /**
     * 输入一个只包含小写 a b 的字符串，每次删除一个回文序列，正着倒着都一样的，
     * 返回多少次能把字符串删到空
     * 
     * 注意：！！！这个子序列不一定要连续的
     * 所以，只需要分三种情况，字符串为空 0 次，本来就是回文字符串 1 次，其他情况则为 2 次
     * 先删除含有 a 的序列，然后删除 含有 b 的序列即可
     */
    public int removePalindromeSub(String s) {
        return s.isEmpty() ? 0 : (s.equals((new StringBuilder(s).reverse().toString())) ? 1 : 2);
    }
}