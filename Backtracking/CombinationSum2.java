public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtracking(result, new ArrayList<Integer>(), 0, candidates, target);  
        return result;
    }

    private void backtracking(List<List<Integer>> result, List<Integer> subArray, int index,
        int[] candidates, int target) {
            if (target == 0) {
                result.add(new ArrayList<>(subArray));
            } else {
                for (int i = index; i < candidates.length; i++) {
                    if (i > index && candidates[i-1] == candidates[i]) continue;
                    else if (candidates[i] > target) return;
                    subArray.add(candidates[i]);
                    backtracking(result, subArray, i+1, candidates, target-candidates[i]);                         subArray.remove(subArray.size() - 1);
                }
            }
    }
}