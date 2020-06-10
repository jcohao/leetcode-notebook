import java.util.ArrayDeque;
import java.util.Deque;

class MaximumSumCircularSubarray {
    /**
     * Kadane's algorithm，用来计算一个数组中最大连续子数组和的算法
     * sum(j+1) = A[j+1] + max(sum(j), 0)
     */
    public int kadane(int[] A) {
        // 设为 0 则忽略了数组中全部为负值的情况
        int result = 0, sum = 0;

        for (int num : A) {
            sum = num + Math.max(sum, 0);
            result = Math.max(result, sum);
        }

        return result;
    }


    /**
     * 将结果分成两个间隔去计算
     * 时间和空间复杂度都为 O(n)
     */
    public int maxSubarraySumCircular(int[] A) {
        if (A == null || A.length == 0) return 0;

        int len = A.length;

        int ans = A[0], sum = A[0];

        // 第一段间隔的最大和，为了解决所有数字都为正数的情况
        for (int i = 1; i < len; i++) {
            sum = A[i] + Math.max(sum, 0);
            ans = Math.max(sum, ans);
        }

        // 计算第二段间隔的和, 从位置 i 开始向右计算数组的和，rightsums = A[i] + A[i+1] + ... + A[N-1]
        int[] rightsums = new int[len];
        rightsums[len-1] = A[len-1];
        for (int i = len-2; i >= 0; i--) rightsums[i] = rightsums[i+1] + A[i];

        // maxright[i] 为 i 右边的最大子数组和
        int[] maxright = new int[len];
        maxright[len-1] = A[len-1];
        for (int i = len-2; i >= 0; i--) maxright[i] = Math.max(maxright[i+1], rightsums[i]);

        int leftsum = 0;
        for (int i = 0; i < len-2; i++) {
            leftsum += A[i];
            ans = Math.max(ans, leftsum + maxright[i+2]);
        }

        return ans;
    }


    // 把数组扩成两倍做，时间复杂度和空间复杂度都为 O(n)
    public int maxSubarraySumCircular2(int[] A) {
        if (A == null || A.length == 0) return 0;
    
        int N = A.length;

        // P[i] = B[0] + B[1] + ... + B[i-1]
        // B = A+A
        int[] P = new int[2*N+1];
        for (int i = 0; i < 2*N; i++) P[i+1] = P[i] + A[i%N];

        // 计算最大的 p[j] - P[i] 1 <= j-i <= N
        // 对于每一个 j，有 P[i] i >= j-N
        int ans = A[0];
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);

        for (int j = 1; j <= 2*N; j++) {
            // deque.peekFirst() < j-N 说明 P[i] 和用重新加上数组的 A[deque.peekFirst()]
            // 和的计算重复了，需要 poll 出来
            if (deque.peekFirst() < j-N) deque.pollFirst();

            // 用 P[j]-P[deque.peekFirst()] 来计算最大差值
            ans = Math.max(ans, P[j] - P[deque.peekFirst()]);

            // 当 P[j] <= P[deque.peekLast()] 说明下一个 P[i] - P[j] 的值将比 P[i] - P[deque.peekLast()] 值要大
            // 所以把队尾元素弹出
            while (!deque.isEmpty() && P[j] <= P[deque.peekLast()]) deque.pollLast();

            deque.offerLast(j);
        }

        return ans;
    }

    /**
     * 在数组中间产生最大和则使用 kadane 算法计算即可
     * 如果是一头一尾相连产生最大和的话，则为 (A0 + A1 + ... + Ai) + (Aj + Aj+1 + ... + AN-1)
     * 
     * 相当于 sum(A) + kadane(B) 这里 kadane(B) 为最小和
     * 如果以上和为 0 则结果肯定产生在数组中间，否则取两者中的最大值
     */
    public int maxSubarraySumCircular3(int[] A) {
        if (A == null || A.length == 0) return 0;

        int sum = 0;

        for (int num : A) sum += num;

        int kadaneA = kadane(A, 1);
        int kadaneB = kadane(A, -1);

        return sum + kadaneB == 0 ? kadaneA : Math.max(kadaneA, sum + kadaneB);
    }

    private int kadane(int[] A, int cardinal) {
        int result = Integer.MIN_VALUE, cur = 0;

        for (int num : A) {
            cur = cardinal * num + Math.max(cur, 0);
            result = Math.max(cur, result);
        }

        return result;
    }

    public static void main(String[] args) {
        MaximumSumCircularSubarray solution = new MaximumSumCircularSubarray();
        System.out.println(solution.maxSubarraySumCircular3(new int[]{1,-2,3,-2}));
    }
} 