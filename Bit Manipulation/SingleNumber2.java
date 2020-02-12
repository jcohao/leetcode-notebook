class SingleNumber2 {

    /**
     * 每一位的二进制数相加，然后对 3 取余
     */
    public int singleNumber(int[] nums) {
        int result = 0;

        for (int i = 0; i < 32; i++) {
            int sum = 0;

            for (int num : nums) {
                sum += (num >> i) & 1 ;
            }

            result |= (sum % 3) << i;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2,2,3,2};
        SingleNumber2 solution = new SingleNumber2();
        System.out.println(solution.singleNumber(nums));
    }
}