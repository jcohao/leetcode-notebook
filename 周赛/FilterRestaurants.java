import java.util.List;
import java.util.stream.Collectors;

class FilterRestaurants {
    /**
     * 根据条件过滤给定的一堆酒店信息，restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei]
     * veganFriendlyi 为 1 则只要 veganFriendlyi 为 1 的酒店，veganFriendlyi 为 0 则不过滤此条件
     * 然后根据 maxPrice 和 maxDistance 来过滤酒店信息
     * 返回一个数组，里面包含了过滤出来的酒店 id，id 值根据每个酒店的 ratingi 值从高到低排序
     * 
     * 这里要注意，rating 相同的 id 较大的在前
     */
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<int[]> result = new ArrayList<>();
        if (restaurants == null || restaurants.length == 0) return new ArrayList<>();

        for (int[] restaurant : restaurants) {
            if ((veganFriendly == 1 ? restaurant[2] == 1 : true) && (restaurant[3] <= maxPrice) &&
                (restaurant[4] <= maxDistance)) {
                int[] filtered = {restaurant[0], restaurant[1]};
                result.add(filtered);
            }
        }

        Collections.sort(result, (a, b) -> ((b[1]-a[1]) == 0) ? (b[0]-a[0]) : (b[1]-a[1]));

        return result.stream().map(a -> a[0]).collect(Collectors.toList());
       
    }
}