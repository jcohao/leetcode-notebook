class ImplementstrStr {
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        else if (haystack == null || haystack.length() == 0 || haystack.length() < needle.length()) return -1;

        char[] haystacks = haystack.toCharArray();
        char[] needles = needle.toCharArray();

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystacks[i] == needles[0]) {
                for (int j = 0; j < needle.length(); j++) {
                    if (haystacks[i+j] != needles[j]) break;

                    if (j == needle.length() - 1) return i;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        ImplementstrStr solution = new ImplementstrStr();
        System.out.println(solution.strStr("aaaaa", "aab"));
    }
}