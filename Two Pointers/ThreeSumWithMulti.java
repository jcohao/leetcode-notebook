class ThreeSumWithMulti {
    public int threeSumMulti(int[] A, int target) {
        int result = 0;
        if (A == null || A.length == 0) return result;
        int n = A.length;
        int MOD = 1000000007;
        // 只要求了 i < j < k，所以是可以排序的
        Arrays.sort(A);

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            while (left < right) {
                int sum = A[i] + A[left] + A[right];

                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else if (A[left] != A[right]) {
                    int leftNum = 1, rightNum = 1;

                    while (left < right && A[left] == A[left+1]) {
                        leftNum++;
                        left++;
                    }

                    while (left < right && A[right] == A[right-1]) {
                        rightNum++;
                        right--;
                    }

                    left++;
                    right--;

                    result += leftNum * rightNum;
                    result %= MOD;
                } else {
                    // 等差数列求和
                    result += (right - left + 1) * (right - left) / 2;
                    result %= MOD;
                    break;
                }
            }
        }

        return result;
    }

    
    public static void main(String[] args) {
        ThreeSumWithMulti solution = new ThreeSumWithMulti();

        int[] A = {1,1,2,2,3,3,4,4,5,5};

        System.out.println(solution.threeSumMulti(A, 8));
    }
}