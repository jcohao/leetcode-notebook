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
     * 时间复杂度为 O(NlogN) 空间复杂度为 O(N)
     */




    public static void main(String[] args) {
        Integer[] nums = {5, 4, 2, 1, 8};
        Sorts.shellSort(nums);
        Sorts.show(nums);
    }


}