import java.util.ArrayList;
import java.util.List;

class DiagonalTraverseII {
    /**
     * 利用同一对角线坐标之和相等的特性去遍历，但以下方法 TLE 了
     */
    public int[] findDiagonalOrder1(List<List<Integer>> nums) {
        if (nums == null || nums.size() == 0) return new int[0];

        int maxCol = 0;

        for (List<Integer> list : nums) {
            maxCol = Math.max(maxCol, list.size());
        }
        
        int row = nums.size(), col = 0, count = row + maxCol - 1;
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i <= count; i++) {
            
            for (int x = i, y = 0; x >= 0 && y <= i; x--, y++) {
                if (x >= row) continue;
                col = nums.get(x).size();
                if (y >= col) continue;
                
                result.add(nums.get(x).get(y));
            }
        }
        
        int[] arr = new int[result.size()];
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = result.get(i);
        }
        
        return arr;
    }


    /**
     * 遍历每个元素，将遍历到的元素按照坐标和加入到 map 中，遍历完之后再将 map 中的数据拿出来，
     * 每个 key 对应的值倒序加入到结果集中
     * 
     * 如果是竖着遍历的话，拿结果的时候就不用倒序了
     * 
     * 时间空间复杂度均为 O(mn)
     */
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        if (nums == null || nums.length == 0) return new int[0];

        Map<Integer, List<Integer>> map = new HashMap<>();

        int count = 0;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                if (!map.containsKey(i+j)) {
                    map.put(i+j, new ArrayList<>());
                }
                map.get(i+j).add(nums.get(i).get(j));
                count++;
            }
        }

        int[] result = new int[count];

        count = 0;
        int sum = 0;

        while (map.containsKey(sum)) {
            List<Integer> list = map.get(sum);

            for (int i = list.size()-1; i >= 0; i--) {
                result[count++] = list.get(i);
            }

            sum++;
        }

        return result;
    }
}