import java.util.ArrayList;
import java.util.List;

class PermutationSequence {
    public String getPermutation(int n, int k) {
        // List<Integer> result = new ArrayList<>();

        List<Integer> nums = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        
        while (sb.length() < n) {
            int size = factorial(n - 1 - sb.length());
            int num = (k - 1) / size;
            // result.add(nums.get(num));
            sb.append(nums.get(num));
            nums.remove(num);
            k = k - num * size;
        }


        // StringBuffer sb = new StringBuffer();
        // result.stream().forEach(e -> sb.append(e));
        // return sb.toString();

        return String.valueOf(sb);
    }


    private int factorial(int n) {
        if (n == 1 || n == 0) return 1;
        else return n * factorial(n - 1);
    }


    public static void main(String[] args) {
        PermutationSequence solution = new PermutationSequence();
        System.out.println(solution.getPermutation(3, 3));
    }
}