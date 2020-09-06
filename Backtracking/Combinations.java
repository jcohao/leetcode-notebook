import java.util.ArrayList;
import java.util.List;

class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (k > n) return result;
        backtrack(result, new ArrayList<>(), n, k, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> subArray, int n, int k, int index) {
        if (subArray.size() == k) {
            result.add(new ArrayList<>(subArray));
        }

        for (int i = index; i <= n; i++) {
            subArray.add(i);
            backtrack(result, subArray, n, k, i+1);
            subArray.remove(subArray.size() - 1);
        }
    }

    public static void main(String[] args) {
        Combinations solution = new Combinations();
        System.out.println(solution.combine(4, 2).toString());
    }
}