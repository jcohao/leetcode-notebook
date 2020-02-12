class BitwiseANDofNumbersRange {
    public int rangeBitwiseAnd1(int m, int n) {
        int result = m;
        while (m < n) {
            result = result & (m + 1);
            m++;
            if (result == 0) return 0;
        }
        return result;
    }

    /**
     * 寻找两个数左边公共的部分
     */
    public int rangeBitwiseAnd(int m, int n) {
        int i = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            i++;
        }

        return m << i;
    }
    
}