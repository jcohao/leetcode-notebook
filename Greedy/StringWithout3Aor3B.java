public class StringWithout3Aor3B {
    public String strWithout3a3b1(int A, int B) {
        StringBuilder sb = new StringBuilder(A + B);
        char a = 'a', b = 'b';
        int i = A, j = B;
        if (B > A) {
            a = 'b';
            b = 'a';
            i = B;
            j = A;
        }
        while (i-- > 0) {
            sb.append(a);
            if (i > j) {
                sb.append(a);
                i--;
            }
            if (j-- > 0) sb.append(b);
        }

        return sb.toString();
    }

    public String strWithout3a3b(int A, int B) {
        StringBuilder sb = new StringBuilder();

        while (A > 0 || B > 0) {
            boolean writeA = false;
            int len = sb.length();
            if (len >= 2 && sb.charAt(len-1) == sb.charAt(len-2)) {
                if (sb.charAt(len-1) == 'b') writeA = true;
            } else {
                if (A >= B) writeA = true;
            }

            if (writeA) {
                A--;
                sb.append("a");
            } else {
                B--;
                sb.append("b");
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        StringWithout3Aor3B solution = new StringWithout3Aor3B();

        System.out.println(solution.strWithout3a3b(4, 1));
    }
}