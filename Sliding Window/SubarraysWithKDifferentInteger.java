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

    public int subarraysWithKDistinct3(int[] A, int K) {
        // 要求只有 K 个不同数字组成的子数组的个数
        // 需要知道：最多 K - 1 个不同数字的子数组组合数 + 只有 K 个不同数字组成的子数组的个数
        //                  = 最多 K 个不同数字的子数组组合数
        return atMostK(A, K) - atMostK(A, K - 1);
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
            // 长度相当于当前 j 指向的元素与之前的数字的组合数
            res += j - i + 1;
        }

        return res;
    }


    public int subarraysWithKDistinct4(int[] A, int K) {
        int result = 0;
        if (A == null || A.length == 0) return result;

        // 这里如果有一个数字是比 A.length 还要大的话数组就越界了
        // 还是改用 HashMap 吧
        int[] counts = new int[A.length + 1];
        int left = 0, countDist = 0, combNum = 1;

        for (int right = 0; right < A.length; right++) {
            if (counts[A[right]]++ == 0) countDist++;

            while (countDist > K) {
                if (--counts[A[left]] == 0) countDist--;
                left++;
                // 循环退出的条件时 countDist == K，此时子数组不同数字的个数刚好为 K
                // 这时候的子数组算一个组合数
                combNum = 1;
            }

            if (countDist == K) {
                // 当 left 所指的数字数目大于 1
                // 即时将该数字排除掉，也是满足题意的一个子数组
                // 所以组合数加 1
                while (counts[A[left]] > 1) {
                    combNum++;
                    counts[A[left]]--;
                    left++;
                }

                result += combNum;
            }
        }

        return result;
    }



    public static void main(String[] args) {
        SubarraysWithKDifferentInteger solution = new SubarraysWithKDifferentInteger();

        int[] A = {27,27,43,28,11,20,1,4,49,18,37,31,31,7,3,31,50,6,50,46,4,13,31,49,15,52,25,31,35,4,11,50,40,1,49,14,46,16,11,16,39,26,13,4,37,39,46,27,49,39,49,50,37,9,30,45,51,47,18,49,24,24,46,47,18,46,52,47,50,4,39,22,50,40,3,52,24,50,38,30,14,12,1,5,52,44,3,49,45,37,40,35,50,50,23,32,1,2};
        

        System.out.println(solution.subarraysWithKDistinct4(A, 20));
    }
}