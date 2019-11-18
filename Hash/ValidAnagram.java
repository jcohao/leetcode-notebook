import java.util.Arrays;

class ValidAnagram {
    /**
     * 使用两个数组分别记录两个字符串中所包含字符的个数
     * 然后遍历两个数组，看所记录的字符数是否一致
     * 一致则返回为 true，不一致则为 false
     */
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;

        int[] countS = new int[256];
        int[] countT = new int[256];

        for (int i = 0; i < s.length(); i++) {
            countS[s.charAt(i)]++;
            countT[t.charAt(i)]++;
        }

        for (int i = 0; i < countS.length; i++) {
            if (countS[i] != countT[i]) return false;
        }

        return true;
    }

    /**
     * 或者只用一个数组记录即可，最后检查数组的元素是否全部为 0
     */
    public boolean isAnagram2(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;

        int[] count = new int[256];

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
            count[t.charAt(i)]--;
        }

        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) return false;
        }

        return true;
    }

    /**
     * 将两个字符串转换为字符数组，然后数组进行排序比较
     */
    public boolean isAnagram3(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) return false;

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        Arrays.sort(sArr);
        Arrays.sort(tArr);

        return Arrays.equals(sArr, tArr);
    }



    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();
        System.out.println(solution.isAnagram3("rat", "tar"));
    }
}