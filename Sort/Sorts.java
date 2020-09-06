import java.util.Arrays;

public class Sorts {


    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;

        return true;
    }

    /**
     * 选择排序：找到数组中的最小的元素，然后往与数组的第一个元素交换，
     *          然后找剩下元素中最小的元素，与第二个元素交换，以此类推
     * 时间复杂度为 O(n*n) 空间复杂度为 O(1)
     * 没有利用到输入的初始状态，即顺序的数组和乱序的数组所花的时间是差不多的
     * 选择排序是不稳定的，例子 {5, 8, 5, 2, 9}
     */
    public static void selectionSort(Comparable[] a) {
        if (a == null || a.length == 0) return;
        int min;
        for (int i = 0; i < a.length - 1; i++) {
            min = i;
            for (int j = i+1; j < a.length; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
    }


    /**
     * 插入排序：索引之前的数组都是有序的，在数组中找到相应的位置往里面放元素，
     *          后面的元素往后挪动
     * 对应部分有序的数组很有效
     * 时间复杂度为 O(n*n) 空间复杂度为 O(1)
     * 插入排序涉及两两相邻的元素相交，是稳定的
     */
    public static void insertionSort(Comparable[] a) {
        if (a == null || a.length == 0) return;

        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j - 1);
            }
        }
    } 


    /**
     * 冒泡排序：双层循环遍历数组，外层循环遍历每一个元素，内层循环作比较，
     * 如果相邻两个元素后一个比前一个要小则交换位置，直到不能交换位置位置，
     * 则每一次将把该轮最大的元素往数组尾部推到正确的位置
     * 冒泡排序跟插入排序很相似，但是区别在于，插入排序在排序过程中形成的
     * 局部有序序列不一定与最后排序结果相同，而冒泡排序每次冒出的都是数组
     * 中当前轮的最大或最小元素
     * 时间复杂度 O(n*n) 空间复杂度 O(1)
     * 冒泡排序也是稳定的
     */
    public static void bubbleSort(Comparable[] a) {
        if (a == null || a.length == 0) return;

        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (less(a[j+1], a[j])) exch(a, j, j+1);
            }
        }
    }

    /**
     * 希尔排序：基于插入排序，每次将间隔为 h 的元素进行局部排序，最终用插入排序将局部有序的数组进行排序
     * 希尔排序更高效的原因是它衡量了子数组的规模和有序性
     * 时间复杂度为 O(n^(1.3~2)) 空间复杂度为 O(1) 而且希尔排序是不稳定的
     */
    public static void shellSort(Comparable[] a) {
        if (a == null || a.length == 0) return;

        int h = 1;
        while (h < a.length/3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            h /= 3;
        }
    }

    

    /**
     * 归并排序：递归地将数组分成两半然后进行排序，再归并到一起
     * 时间复杂度为 O(NlogN) 空间复杂度为 O(N) 是稳定的
     */
    private static Comparable[] aux;

    private static void merge(Comparable[] a, int low, int mid, int high) {
        int i = low, j = mid + 1;

        // 将要排序的区间复制到临时数组中
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        for (int k = low; k <= high; k++) {
            if (i > mid) a[k] = aux[j++];     // 左边排完
            else if (j > high) a[k] = aux[i++]; // 右边排完
            else if (less(aux[j], aux[i])) a[k] = aux[j++];   // 右边比左边要小
            else a[k] = aux[i++];         // 左边小于或等于右边
        }
    }

    // 自顶向下的归并排序
    public static void mergeSort(Comparable[] a) {
        aux = new Comparable[a.length];
        up2down(a, 0, a.length - 1);
    }

    // 当处理小规模数组时（数组长度小于 15）可以用插排和选排来提高效率
    private static void up2down(Comparable[] a, int low, int high) {
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        up2down(a, low, mid);
        up2down(a, mid + 1, high);
        // 这里 up2down 方法的作用其实在于安排多次 merge() 方法调用的正确顺序
        merge(a, low, mid, high);
    }


    // 自底向上的归并，适合用链表组织的数据
    public static void mergeBU(Comparable[] a) {
        int N = a.length;
        aux = new Comparable[N];
        // 按照子数组大小进行归并，子数组从 1 开始，每次加倍
        for (int sz = 1; sz < N; sz *= 2) {
            for (int low = 0; low < N-sz; low += 2*sz) {
                merge(a, low, low+sz-1, Math.min(low+2*sz-1, N-1));
            }
        }
    }


    /**
     * 快速排序：每次把小于等于切分元素的排到切分元素前面，把大于它的排在它后面
     * 时间复杂度为 O(NlogN) 空间复杂度为 O(1)
     * 快速排序是不稳定的
     */
    public static void quickSort(Comparable[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static void quickSort(Comparable[] a, int low, int high) {
        if (high <= low) return;
        // if (high <= low + M) {insertionSort(a, low, high); return;}
        // 切分，low ~ j-1 的元素都小于 a[j]，j+1 ~ high 的元素都大于 a[j]
        int j = partition(a, low, high);
        // 左半边排序
        quickSort(a, low, j - 1);
        // 右半边排序
        quickSort(a, j+1, high);
    }


    private static int partition(Comparable[] a, int low, int high) {
        int i = low, j = high + 1;
        Comparable v = a[low];

        while (true) {
            while (less(a[++i], v)) if (i == high) break;
            while (less(v, a[--j])) if (j == low) break;

            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, low, j);
        return j;
    }


    /**
     * 堆排序：一棵完全二叉树，具体分为小顶堆和大顶堆，这里用小顶堆为例，父节点的值不大于左右子树的值
     * 简单的堆实现用数组来实现，k 位置的元素不大于 2k + 1 和 2k + 2 位置的值
     * 每次将堆顶元素 poll 出，然后调整堆的顺序
     * 堆排序是不稳定的
     */
    public static void heapSort(Comparable[] a) {
        if (a == null || a.length == 0) return;

        // 堆的创建
        Comparable[] heap = createHeap(a);


        // 循环 poll 出堆中元素，然后调整堆内元素的顺序
        int index = 0;

        while (index < a.length) {
            a[index] = heap[0];
            sink(heap, index);
            index++;
        }
    }

    private static Comparable[] createHeap(Comparable[] a) {
        // 堆的创建要用上浮操作
        Comparable[] heap = new Comparable[a.length];

        int index = 0;

        for (Comparable item : a) {
            heap[index] = item;
            rise(heap, index);
            index++;
        }

        return heap;
    }

    private static void rise(Comparable[] heap, int index) {
        int pIndex = index % 2 == 0 ? index/2 - 1 : index/2;

        while (pIndex >= 0 && !less(heap[pIndex], heap[index])) {
            exch(heap, pIndex, index);
            index = pIndex;
            pIndex = index % 2 == 0 ? index/2 - 1 : index/2;
        }
    }

    private static void sink(Comparable[] heap, int index) {
        if (index == heap.length - 1) return;

        int bound = heap.length - index - 1;
        int cur = 0;
        heap[0] = heap[bound];

        while (cur < bound) {
            if (2*cur + 1 < bound && 2*cur + 2 < bound) {
                if (less(heap[2*cur+2], heap[2*cur+1])) {
                    exch(heap, 2*cur+1, 2*cur+2);
                }
            }

            if (2*cur + 1 < bound && !less(heap[cur], heap[2*cur+1])) {
                exch(heap, cur, 2*cur+1);
                cur = 2*cur + 1;
            } else {
                break;
            }
        }
    }

    /**
     * 基数排序：分桶进行排序，在数值上有 LSD 和 MSD 
     * LSD 是从最低位开始，而 MSD 则是从最高位开始
     * 以下用 LSD 做例子对数值数组排序
     */
    public static void lsdSort(int[] arr) {
        if (arr == null || arr.length == 0) return;

        int max = Integer.MIN_VALUE;

        for (int a : arr) {
            max = Math.max(max, a);
        }

        // 找出最大的数是多少位的
        int count = 0;
        while (max != 0) {
            max /= 10;
            count++;
        }

        // 每次排序所用的桶
        int[][] bin = new int[10][arr.length];
        // 记录桶中装了多少元素
        int[] memo = new int[10];

        for (int i = 0; i < count; i++) {
            int lsd = (int) Math.pow(10, i);

            for (int a : arr) {
                int pos = (a / lsd) % 10;
                bin[pos][memo[pos]++] = a;  
            }

            int index = 0;
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < memo[j]; k++) {
                    arr[index++] = bin[j][k];
                }

                memo[j] = 0;
            }
        }


    }

    public static void msdSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        int max = Integer.MIN_VALUE;

        for (int a : arr) {
            max = Math.max(max, a);
        }

        int count = String.valueOf(max).length();

        int[][] bin = new int[10][arr.length];

        int[] memo = new int[10];

        for (int a : arr) {
            int index = (int) (a / Math.pow(10, count-1));
            bin[index][memo[index]++] = a;
        }

        int k = 0;
        for (int i = 0; i < 10; i++) {
            if (memo[i] == 0) continue;
            int[] tmp = new int[memo[i]];
            for (int j = 0; j < memo[i]; j++) {
                tmp[j] = bin[i][j];
            }

            msdSort(tmp);
            for (int j = 0; j < tmp.length; j++) {
                arr[k++] = tmp[j];
            }
        }

        
    }



    public static void main(String[] args) {
        int[] nums = {432, 2, 10, 8};
        Sorts.msdSort(nums);
        for (int a : nums) {
            System.out.print(a + " ");
        }
    }

}