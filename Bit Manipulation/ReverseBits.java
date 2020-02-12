class ReverseBits {
    public int reverseBits(int n) {
        int result = 0;
        if (n == 0) return result;

        for (int i = 0; i < 32; i++) {
            result = result << 1;
            if ((n & 1) == 1) {
                result += 1;
            }
            n = n >> 1;
        }

        return result;
    }
}