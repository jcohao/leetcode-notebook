class FloatWrapperTest {
    /**
     * Float 的装箱和拆箱实际操作的还是底层的 float 类型
     * 所以还是会存在精度丢失的问题
     */
    public static void main(String[] args) {
        Float a = Float.valueOf(1.0f - 0.9f);
        Float b = Float.valueOf(0.9f - 0.8f);

        System.out.println(a);
        System.out.println(b);

        if (a.equals(b)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}