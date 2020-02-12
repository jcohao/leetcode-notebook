// Given an array arr, replace every element in that array with the greatest element among the elements to its right, 
// and replace the last element with -1.

// After doing so, return the array.

// Input: arr = [17,18,5,4,6,1]
// Output: [18,6,6,6,1,-1]


class ReplaceElementswithGreatestonRightSide {
    /**
     * 这道题一开始只想到了从左到右两层循环去解，没有想到从由到左只需要一层循环
     * 不过在解题中还是要养成先完成后优化的习惯，做出来了总比没做出来要好
     */
    public int[] replaceElements(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        
        for (int i = 0; i < arr.length; i++) {
            int max;
            if (i == arr.length - 1) {
                max = -1;
            } else {
                max = arr[i+1];
            }
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] > max) max = arr[j];
            }
            arr[i] = max;
        }

        return arr;
    }

    public int[] replaceElements1(int[] arr) {
        if (arr == null || arr.length == 0) return arr;

        int rightMax = -1;
        int temp;
        for (int i = arr.length-1; i >= 0; i--) {
            temp = arr[i];
            arr[i] = rightMax;
            rightMax = Math.max(temp, rightMax);
            // if (temp > rightMax) rightMax = temp;
        }

        return arr;
    }
}