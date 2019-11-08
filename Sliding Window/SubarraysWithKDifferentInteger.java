import java.util.HashMap;
import java.util.Map;

class SubarraysWithKDifferentInteger {

    /**
     * Time Limit Exceeded
     */
    public int subarraysWithKDistinct(int[] A, int K) {
        if (A == null || A.length == 0 || K <= 0) return 0;

        int result = 0;

        // 记录滑动窗口内数字的个数
        Map<Integer, Integer> map;

        for (int left = 0; left < A.length; left++) {
            map = new HashMap<>();

            for (int right = left; right < A.length; right++) {
                // 右指针每次移动都将数字记录到 map 中
                map.put(A[right], map.getOrDefault(A[right], 0) + 1);

                if (map.size() == K) {      // map 的大小等于 K 时，则结果加 1
                    result++;
                } else if (map.size() > K) {    // map 的大小大于 K 时，break 出去，左指针加 1 重复上述步骤
                    break;
                }
            }
        }

        return result;
    }


    public int subarraysWithKDistinct2(int[] A, int K) {
        if (A == null || A.length == 0 || K <= 0) return 0;

        int result = 0;

        Map<Integer, Integer> map = new HashMap<>();

        int left = 0, right = 0;

        while (right < A.length || map.size() > K) {
            if (right < A.length && map.size() <= K) {
                if (map.size() == K) result++;
                map.put(A[right], map.getOrDefault(A[right], 0) + 1);
                right++;
            } else if (map.size() > K || right > A.length - 1) {
                while (map.size() == K + 1) {
                    map.put(A[left], map.get(A[left]) - 1);
                    if (map.get(A[left]) == 0) {
                        map.remove(A[left]);
                    }
                    left++;
                    result++;
                }
            }
        }

        return result;
    }


    public int atMostK(int[] A, int K) {
        int i = 0, res = 0;

        Map<Integer, Integer> count = new HashMap<>();

        for (int j = 0; j < A.length; j++) {
            if (count.getOrDefault(A[j], 0) == 0) K--;
            count.put(A[j], count.getOrDefault(A[j], 0) + 1);
            while (K < 0) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0) K++;
                i++;
            }
            res += j - i + 1;
        }

        return res;
    }

    public static void main(String[] args) {
        SubarraysWithKDifferentInteger solution = new SubarraysWithKDifferentInteger();

        int[] A = {1,2,1,2,3};

        System.out.println(solution.atMostK(A, 1));
    }
}