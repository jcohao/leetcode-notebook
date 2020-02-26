import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SortIntergersByBits {
    public int[] sortByBits(int[] arr) {
        if (arr == null || arr.length < 2) return arr;

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        for (int num : arr) {
            int count = 0;
            list.add(num);
            while (num != 0) {
                if ((num & 1) == 1) count++;
                num = num >> 1;
            }
            map.put(list.get(list.size()-1), count);
        }
        
        // 基本类型不能充当变量参数，所以把它转为 list 再进行排序
        list.sort((a, b) -> (map.get(a) - map.get(b) == 0) ? a - b : map.get(a) - map.get(b));
        // arr = list.toArray();
        arr = list.stream().mapToInt(Integer::intValue).toArray();
        return arr;
    }

    /**
     * 别人的，使用各种库方法的解法
     */
    public int[] sortByBits1(int[] arr) {
        Integer[] input = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(input, (a, b) -> Integer.bitCount(a) == Integer.bitCount(b) ? a - b : Integer.bitCount(a) - Integer.bitCount(b));
        return Arrays.stream(input).mapToInt(Integer::intValue).toArray();
    }


    public static void main(String[] args) {
        int[] arr = {1024,512,256,128,64,32,16,8,4,2,1};

        SortIntergersByBits solution = new SortIntergersByBits();

        System.out.println(Arrays.toString(solution.sortByBits1(arr)));
    }
}