import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 使用 hashmap 保存值与值在数组中的位置
 * 当删除的时候将删除的值在数组中的位置换成数组中最后一个元素
 * 更新该元素在 map 中的值
 * list 删除最后一个元素的时间复杂度为 O(1)
 */
class RandomizedSet {

    private Map<Integer, Integer> map;
    private List<Integer> list;
    private int size = 0;
    private Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            map.put(val, size);
            list.add(val);
            size++;
        }

        return true;
        
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        } else {
            int pos = map.get(val);

            int lastVal = list.get(size-1);
            
            map.put(lastVal, pos);
            map.remove(val);
            list.set(pos, lastVal);
            list.remove(--size);
        }

        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int pos = random.nextInt(size);
        return list.get(pos);
    }
}