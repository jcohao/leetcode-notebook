public class Numberof1Bits {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) result++;
            n = n >> 1;
        }
        return result;
    }

    // 每一次将最后一位为 1 的位置连同其后面的位数清 0 
    // 直到数字为 0 为止
    // 001100100
    // 001100011 & 001100100 => n - 1 & n = 001100000
    public int hammingWeight1(int n) {
        int result = 0;
        while (n != 0) {
            n = n & (n - 1);
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        Numberof1Bits solution = new Numberof1Bits();
        System.out.println(solution.hammingWeight1(11));
    }
}