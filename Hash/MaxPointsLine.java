import java.util.HashMap;
import java.util.Map;

class MaxPointsLine {
    public int maxPoints(int[][] points) {
        if (points == null || points.length == 0) return 0;

        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int numerator = points[j][0] - points[i][0];
                int denominator = points[j][1] - points[i][1];
                int b = 0;
                String key = null;
                if (numerator == 0) {
                    b = points[j][1];
                    key = "numerator_0_" + b;
                } else if (denominator == 0) {
                    b = points[j][0];
                    key = "denominator_0_" + b;
                } else {
                    b = denominator * points[j][1] - numerator * points[j][0];
                    key = numerator + "_" + denominator + "_" + b;
                }

                if (map.containsKey(key)) {
                    map.put(key, map.get(key) + 1);
                } else {
                    map.put(key, 1);
                }

            }
        }

        return map.values().stream().max((a, b) -> a > b ? 1 : -1).get();
    }

    public static void main(String[] args) {
        MaxPointsLine solution = new MaxPointsLine();
        int[][] points = {{1,1},{2,2},{3,3}};

        System.out.println(solution.maxPoints(points));
    }
}