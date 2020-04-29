public class LongestHappyString {
    public String longestDiverseString1(int a, int b, int c) {
        return generate(a, b, c, "a", "b", "c");
    }

    // 需要保证每一次调用的顺序都是 a >= b >= c
    private String generate(int a, int b, int c, String aa, String bb, String cc) {
        if (a < b) return generate(b, a, c, bb, aa, cc);    // 保证 a >= b
        if (b < c) return generate(a, c, b, aa, cc, bb);    // 保证 b >= c
        // 当 b == 0 的时候，此时 c 肯定也为 0
        if (b == 0) return (a >= 2 ? aa + aa : (a == 0 ? "" : aa));

        int use_a = Math.min(2, a), use_b = a - use_a >= b ? 1 : 0;

        return (use_a == 2 ? aa + aa : (use_a == 1 ? aa : "")) +
            (use_b == 1 ? bb : "") + generate(a - use_a, b - use_b, c, aa, bb, cc);
    }

    public String longestDiverseString(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        int size = a + b + c;
        int A = 0, B = 0, C = 0;
        for (int i = 0; i < size; i++) {
            if ((a >= b && a >= c && A != 2) || (B == 2 && a > 0) || (C == 2 && a > 0)) {
                sb.append("a");
                a--;
                A++;
                B = 0;
                C = 0;
            } else if ((b >= a && b >= c && B != 2) || (A == 2 && b > 0) || (C == 2 && b > 0)) {
                sb.append("b");
                b--;
                B++;
                A = 0;
                C = 0;
            } else if ((c >= a && c >= b && C != 2) || (B == 2 && c > 0) || (A == 2 && c > 0)) {
                sb.append("c");
                c--;
                C++;
                A = 0;
                B = 0;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LongestHappyString solution = new LongestHappyString();

        System.out.println(solution.longestDiverseString(1, 1, 7));
    }
}