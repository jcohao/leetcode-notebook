class JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        if (arr == null || arr.length == 0) return false;
        else if (arr[start] == 0) return true;

        boolean[] visited = new boolean[arr.length];

        return jump(arr, start, visited);
    }

    private boolean jump(int[] arr, int start, boolean[] visited) {
        if (visited[start]) return false;
        else if (arr[start] == 0) return true;

        boolean left = false, right = false;

        visited[start] = true;

        if (start - arr[start] >= 0) {
            left = jump(arr, start - arr[start], visited);
        }

        if (start + arr[start] < arr.length) {
            right = jump(arr, start + arr[start], visited);
        }

        return left || right;
    }
}