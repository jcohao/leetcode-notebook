class InversePairs {
    /**
     * 题目中运用到了归并排序的思想
     * 在把数组从小到大进行排序的归并排序过程中，如 {3,4} {1,2} 的合并过程
     * 在 3 > 1 的情况下，这两个数字可以组成一对 IP，因为数组是部分有序的，所以子序列中排在 3 后面的数字也可以跟
     * 1 组成 IP
     * 
     * 这道题的时间复杂度是 O(NlogN) 空间复杂度是 O(N)
     */
    int mod = 1_000_000_007;
    int result = 0;
    int[] temp;
    public int inversePairs(int[] array) {
        if (array == null || array.length == 0) return 0;
        
        temp = new int[array.length];

        mergeSort(array, 0, array.length-1);
        
        return result;
    }

    private void mergeSort(int[] array, int start, int end) {
        if (start >= end) return;

        int mid = start + (end - start) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid+1, end);
        merge(array, start, mid, end);
    }

    private void merge(int[] array, int start, int mid, int end) {
        int i = start, j = mid + 1, k = 0;

        while (i <= mid && j <= end) {
            if (array[i] > array[j]) {
                temp[k++] = array[j++];
                result = (result + (mid - i + 1)) % mod;
            } else {
                temp[k++] = array[i++];
            }
        }

        while (i <= mid) temp[k++] = array[i++];

        while (j <= end) temp[k++] = array[j++];

        for (k = 0, i = start; i <= end; i++, k++) array[i] = temp[k];
    }
}