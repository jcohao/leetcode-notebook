
/** 
Given a number s in their binary representation. Return the number of steps to reduce it to 1 under the following rules:

If the current number is even, you have to divide it by 2.

If the current number is odd, you have to add 1 to it.
*/

class NumberOfStepsInBinary {
    public int numSteps(String s) {
        int result = 0, carry = 0;
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) - '0' + carry == 1) {
                // 0 + 1 = 1
                // 1 + 0 = 1，此时为奇数，需要通过加 1 后除 2 才能跳过当前位，此时 carry 变为 1
                carry = 1;
                result += 2;
            } else {
                // 0 + 0 = 0
                // 1 + 1 = 0，此时为偶数，只需要除 2 即可跳过当前位，carry 保持不变
                result += 1;
            }
        }

        return result + carry;
    }

    public static void main(String[] args) {
        NumberOfStepsInBinary solution = new NumberOfStepsInBinary();

        System.out.println(solution.numSteps("1111"));
    }
}