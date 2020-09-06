public class DivideTwoIntegers {
    /**
     * 当被除数和除数都为正数的时候，除法的商等于被除数减去除数而不变负数的次数
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return -1;
        
        // 异或，相同为 0 不同为 1
        int sign = dividend > 0 ^ divisor > 0 ? -1 : 1;
        
        int result = 0;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        
        while (dividend >= divisor) {
            result++;
            dividend -= divisor;
        }
        
        return sign * result;
    }


    public int divide1(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;

        int sign = dividend > 0 ^ divisor > 0 ? -1 : 1;

        long dvd = Math.abs(dividend), dvs = Math.abs(divisor);

        int result = 0;

        // 当除数小于被除数时，除数乘以两倍，直到大于被除数为止，记录乘2的次数到结果中
        // 然后被除数减去除数最大时小于等于被除数时的值，继续重复上述操作，直到除数大于被除数
        while (dvd >= dvs) {
            long temp = dvs, m = 1;
            while (temp << 1 <= dvd) {
                temp <<= 1;
                m++;
            }

            dvd -= temp;
            result += m;
        }

        return result * sign;
    }

    public static void main(String[] args) {
        int num = 12 << 1;
        System.out.println(num <= 15);
    }
}