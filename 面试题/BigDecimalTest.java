import java.math.BigDecimal;

class BigDecimalTest {
    /**
     * 前者还是存在精度问题
     * 如果非要用浮点数来构造 BigDecimal，则应该先把浮点数转化为 String
     * Double.toString() or String.valueOf()
     */
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(0.1);
        System.out.println(a);
        BigDecimal b = new BigDecimal("0.1");
        System.out.println(b);


        BigDecimal c = new BigDecimal(Double.toString(0.1));
        System.out.println(c);
        BigDecimal d = new BigDecimal(String.valueOf(0.1));
        System.out.println(d);
    }
}