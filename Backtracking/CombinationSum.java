class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<Integer>(), candidates, 0, target);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> subArray, int[] candidates, int start, int target) {
        if (target == 0) {
            result.add(new ArrayList(subArray));
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (candidates[i] > target) return;
                subArray.add(candidates[i]);
                backtrack(result, subArray, candidates, i, target - candidates[i]);
                subArray.remove(subArray.size() - 1);
            }
        }
    }
}