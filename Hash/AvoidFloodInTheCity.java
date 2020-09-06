import java.util.TreeSet;

class AvoidFloodInTheCity {
    public int[] avoidFlood(int[] rains) {
        if (rains == null || rains.length == 0) return new int[0];

        Map<Integer, Integer> map = new HashMap<>();

        TreeSet<Integer> set = new TreeSet<>();

        int[] result = new int[rains.length];


        for (int i = 0; i < rains.length; i++) {
            if (rains[i] == 0) {
                set.add(i);
                result[i] = 1;
            } else {
                if (map.containsKey(rains[i])) {
                    Integer index = set.ceiling(map.get(rains[i]));
                    if (index == null) return new int[0];

                    set.remove(index);
                    result[index] = rains[i];
                }
                result[i] = -1;
                map.put(rains[i], i);
            }
        }

        return result;
    }
}