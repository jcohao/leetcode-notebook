class LongestPalindromicSubstring {
    String maxStr;
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        
        maxStr = s.substring(0, 1);

        findPalindrome(s);

        return maxStr;
    }

    private void findPalindrome(String s) {
        if (s == null || s.length() <= 1) return;

        if (isPalindrome(s)) {
            maxStr = s.length() > maxStr.length() ? s : maxStr;
        } else {
            findPalindrome(s.substring(0, s.length()-1));
            findPalindrome(s.substring(1, s.length()));
        }
    }

    private boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int len = chars.length;

        for (int i = 0; i < len/2; i++) {
            if (chars[i] != chars[len-i-1]) return false;
        }

        return true;
    }
}