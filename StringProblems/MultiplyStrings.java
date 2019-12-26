import java.math.BigInteger;

class MultiplyStrings {
    public String multiply1(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || 
            num2 == null || num2.length() == 0)
            return null;

        return ((new BigInteger(num1)).multiply(new BigInteger(num2))).toString();
    }


    public String multiply(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || 
            num2 == null || num2.length() == 0)
            return null;
        else if (num1.equals("0") || num2.equals("0"))
            return "0";

        int[] res = new int[num1.length()+num2.length()];

        for (int i = num1.length()-1; i >= 0; i--) {
            for (int j = num2.length()-1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int posLow = i+j+1;     // 低位
                int posHigh = i+j;      // 高位
                mul += res[posLow];
                res[posLow] = mul % 10;
                res[posHigh] += mul / 10;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < res.length; i++) {
            if (sb.length() != 0 || res[i] != 0) sb.append(res[i]);
        }

        return sb.toString();
    }
    


    public static void main(String[] args) {
        MultiplyStrings solution = new MultiplyStrings();

        System.out.println(solution.multiply("99", "99"));
    }
}