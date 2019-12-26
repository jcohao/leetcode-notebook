class FloatPrimitiveTest {
    /**
     * float 和 double 类型都是由浮点数表示的，都存在精度问题
     */
    public static void main(String[] args) {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;

        if (a == b) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}