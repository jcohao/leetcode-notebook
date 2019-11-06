import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class FourSum2 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int result = 0;
        if (A == null || A.length == 0) return result;

        int len = A.length;

        int[] AB = new int[len*len];
        int[] CD = new int[len*len];

        // 先计算出 AB 和的组合 CD 和的组合
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                AB[len*i+j] = A[i] + B[j];
                CD[len*i+j] = C[i] + D[j];
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        int matchNum = -1;
        // 遍历 AB 中的数字，用二分法从 CD 中找出相反数，这里二分法不仅要找出 CD 中是否有 AB 的相反数
        // 还要找出相应的个数，若 AB 中有相同的数字，则沿用上一个计算出来的个数即可，不需要重新计算
        for (int i = 0; i < len * len; i++) {

            if (i > 0 && AB[i] == AB[i-1]) {
                if (matchNum == -1) continue;
                else result += matchNum;
            } else {
                // 不仅要看有没有，还得看有多少个
                matchNum = binarySearch(CD, -AB[i]);
                if (matchNum != -1) result += matchNum;
            }
            
        }

        return result;

    }

    private int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                int result = 1, temp = mid;
                while (temp > 0 && nums[temp] == nums[--temp]) {
                    result++;
                }
                temp = mid;
                while (temp < nums.length - 1 && nums[temp] == nums[++temp]) {
                    result++;
                }
                return result;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }


    /**
     * 可以用 map 记下 A + B 的和，然后在 C + D 中寻找相反数
     */
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        int result = 0;
        if (A == null || A.length == 0) return result;

        int len = A.length;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                map.put(A[i] + B[j], map.getOrDefault(A[i] + B[j], 0) + 1);
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (map.containsKey(-(C[i] + D[j]))) {
                    result += map.get(-(C[i] + D[j]));
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FourSum2 solution = new FourSum2();
        int[] A = {-1,-1};
        int[] B = {-1,1};
        int[] C = {-1,1};
        int[] D = {1,-1}; 
        System.out.println(solution.fourSumCount2(A,B,C,D));
    }
}