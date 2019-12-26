import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class FindMedianFromDataStream {
    /**
     * 维护一个最大堆，一个最小堆，每次 add 数据的时候，先看最大堆是否为空
     * 为空则直接加入数据，如果不为空，则看该数是否大于 peek 出来的数
     * 如果大于则加入最小堆，否则加入最大堆
     * 每次加完之后要看两个堆的大小是否相差 2，如果是，则较大的那个 poll 出数据加入较小的那个
     * 最后返回中位数时如果两个堆容量相等，则每人 peek 一个数据出来相加除 2
     * 否则多的那个 peek 出数据来返回
     * 
     * 相当于把数据分为大的一部分和小的一部分
     * 大的由最小堆管理，小的由最大堆管理
     */
    PriorityQueue<Integer> minPQ;       // 空间复杂度为 O(N)
    PriorityQueue<Integer> maxPQ;

    public FindMedianFromDataStream() {
        minPQ = new PriorityQueue<>();
        maxPQ = new PriorityQueue<>((a, b) -> (b - a));
    }
    
    public void addNum(int num) {
        if (maxPQ.isEmpty() || num > maxPQ.peek()) {        // 优先队列的上浮下沉操作时间复杂度为 O(logN)
            minPQ.offer(num);
        } else {
            maxPQ.offer(num);
        }
        
        if (maxPQ.size() > minPQ.size()+1) {
            Integer i = maxPQ.poll();
            minPQ.offer(i);
        } else if (minPQ.size() > maxPQ.size()+1) {
            Integer i = minPQ.poll();
            maxPQ.offer(i);
        }
    }
    
    public double findMedian() {
        if (minPQ.size() == maxPQ.size()) {                // 取中位数的时间复杂度为 O(1)
            return (minPQ.peek()+maxPQ.peek()) / 2.0;
        } else if (maxPQ.size() > minPQ.size()) {
            return maxPQ.peek();
        } else {
            return minPQ.peek();
        }
    }

    public static void main(String[] args) {
        FindMedianFromDataStream solution = new FindMedianFromDataStream();
        solution.addNum(6);
        solution.addNum(10);
        System.out.println(solution.findMedian());
        solution.addNum(2);
        System.out.println(solution.findMedian());
        solution.addNum(-4);
        solution.addNum(-5);
        System.out.println(solution.findMedian());
    }
}


class MedianFinder {
    /**
     * 用数组存储加进来的数字，每次取中位数的时候都将数组排序
     */
    List<Integer> list;     // 空间复杂度为 O(N)

    public MedianFinder() {
       list = new ArrayList<>();
    }
    
    public void addNum(int num) {
        list.add(num);      // 加元素的时间复杂度为 O(1)
    }
    
    public double findMedian() {
        Collections.sort(list);     // 取中位数的时间复杂度为 O(NlogN)

        int size = list.size();

        return (list.get((size-1)/2) + list.get(size/2)) / 2.0;
    }
}

class MedianFinder {
    /**
     * 每次加入元素都进行一次插排
     */
    List<Integer> list;     // 空间复杂度为 O(N)

    public MedianFinder() {
       list = new ArrayList<>();
    }
    
    public void addNum(int num) {
        // 加元素的时间复杂度为 O(N)
        int i = 0;

        // 可以用二分法来找插入点 O(logN) 但是插排挪元素的复杂度为 O(N) 加起来就是 O(N) 了
        while (!list.isEmpty() && i < list.size() && list.get(i) <= num) {
            i++;
        }

        list.add(i, num);
    }
    
    public double findMedian() {
        // 取中位数的时间复杂度为 O(1)

        int size = list.size();

        return (list.get((size-1)/2) + list.get(size/2)) / 2.0;
    }
}
