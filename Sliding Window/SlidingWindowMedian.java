import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class SlidingWindowMedian {

    
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (k > nums.length) return null;

        double[] result = new double[nums.length - k + 1];

        // 把下面列表的实现改为 ArrayList 就不会 Time limit exceeded 了，但也挺慢的
        // List<Integer> list = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            list.add(nums[i]);
        }

        Collections.sort(list);

        // 这里得除以一个 double 的数，不然返回的结果为 int，以下处理会 overflow
        // [2147483647,2147483647] k = 2
        // result[0] = (list.get((k - 1)/2) + list.get(k/2)) / 2.0;
        result[0] = list.get((k - 1)/2) / 2.0 + list.get(k/2) / 2.0;

        for (int i = 0; i < nums.length - k; i++) {
            list.remove(new Integer(nums[i]));

            for (int j = 0; j < list.size(); j++) {
                if (nums[i+k] < list.get(j)) {
                    list.add(j, nums[i+k]);
                    break;
                } 
            }

            if (list.size() != k) list.add(list.size(), nums[i+k]);

            result[i+1] = list.get((k - 1)/2) / 2.0 + list.get(k/2) / 2.0;
        }

        return result;
    }


    // 优先队列，元素按升序排序，凡是比中位数大的元素都塞进 minHeap 中
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    // 元素按降序排序，凡是比中位数小的元素都塞到 maxHeap中
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
        new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i2.compareTo(i1);
            }
        }
    );

    // 一直保持着 maxHeap 的元素个数与 minHeap 的元素个数相等或者比它大一个是为了方便输出中位数
    // 当 k 为奇数时，中位数则是 minHeap 的第一个元素，当 k 为偶数时，中位数则为 minHeap 和 maxHeap 第一个元素的平均值

    public double[] medianSlidingWindow2(int[] nums, int k) {
        if (k > nums.length) return null;

        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i <= nums.length; i++) {
            // 当 i 小于 k 时则往堆里面塞元素
            if (i >= k) {
                result[i - k] = getMedian();
                remove(nums[i - k]);
            }

            if (i < nums.length)
                add(nums[i]);
            
        }

        return result;
    }

    private void add(int num) {
        if (num < getMedian()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        if (maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        }
    }


    private void remove(int num) {
        if (num < getMedian()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }

        if (maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.add(minHeap.poll());
        }
    }

    private double getMedian() {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) return 0;

        if (maxHeap.size() == minHeap.size()) {
            // 这里应该写成这样，不然有可能溢出
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        } else {
            return minHeap.peek();
        }
    }


    // 以上解法的简化版
    public double[] medianSlidingWindow3(int[] nums, int k) {
        if (k > nums.length) return null;

        double[] result = new double[nums.length - k + 1];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < nums.length; i++) {
            
            if (maxHeap.size() <= minHeap.size()) {
                minHeap.add(nums[i]);
                maxHeap.add(minHeap.remove());
            } else {
                maxHeap.add(nums[i]);
                minHeap.add(maxHeap.remove());
            }

            if (minHeap.size() + maxHeap.size() == k) {
                if (minHeap.size() == maxHeap.size()) {
                    result[i - k + 1] = minHeap.peek() / 2.0 + maxHeap.peek() / 2.0;
                } else {
                    result[i - k + 1] = maxHeap.peek();
                }

                if (!minHeap.remove(nums[i - k + 1])) {
                    maxHeap.remove(nums[i - k + 1]);
                }
            }
        }

        return result;
    }
    


    public static void main(String[] args) {
        SlidingWindowMedian solution = new SlidingWindowMedian();

        int[] nums = {1,3,-1,-3,5,3,6,7};

        System.out.println(Arrays.toString(solution.medianSlidingWindow3(nums, 3)));
    }
}