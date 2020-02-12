import java.util.Arrays;

class SumofMutatedArrayClosest2Target {
    public int findBestValue(int[] arr, int target) {
        if (arr == null || arr.length == 0) return 0;

        Arrays.sort(arr);
        
        int sum = 0;
        int len = arr.length;

        for (int num : arr) {
            sum += num;
        }

        int result = arr[len-1];
        int t = target / len;
        int leftSum = 0;

        int cand = 0;

        for (int i = 0; i < len; i++) {
            // 一个一个去试，直到这个值等于当前遍历的数
            while (t <= arr[i]) {
                cand = leftSum + (len - i) * t;
                if (Math.abs(cand - target) < Math.abs(sum - target)) {
                    sum = cand;
                    result = t;
                }
                t += 1;
            }
            
            // 这个数加到左边的总和里面
            leftSum += arr[i];
        }

        return result;
    }


    public static void main(String[] args) {
        SumofMutatedArrayClosest2Target solution = new SumofMutatedArrayClosest2Target();
        int[] arr = {4, 9, 3};
        System.out.println(solution.findBestValue(arr, 10));
    }
}