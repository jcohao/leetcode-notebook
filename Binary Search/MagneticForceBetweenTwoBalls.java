import java.util.Arrays;
import java.util.List;

class MagneticForceBetweenTwoBalls {
    /**
     * 这道题用二分查找来解决
     * 最大磁力值从 1 到数组中最大磁力值之间寻找
     * 验证每一次的磁力值是否能在数组中摆 m 个球
     * 不能的话缩小右界
     * 可以的话增加左界，寻找更大的满足条件的磁力值
     */
    public int maxDistance(int[] position, int m) {
        if (position == null || position.length == 0) return 0;

        Arrays.sort(position);

        int len = position.length, result = 0;

        int left = 1, right = position[len-1] - position[0];

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int minForce = midIsMinForce(position, m, mid);

            if (minForce == -1) {
                right = mid - 1;
            } else {
                result = Math.max(result, mid);
                left = mid + 1;
            }
        }

        return result;
    }

    private int midIsMinForce(int[] position, int m, int force) {
        int result = Integer.MAX_VALUE;
        int pre = position[0];
        m--;
        
        for (int i = 1; i < position.length; i++) {
            if (position[i] - pre >= force) {
                m--;
                result = Math.min(result, position[i] - pre);
                pre = position[i];
            }

            if (m == 0) break;
        }

        return m == 0 ? result : -1;

    }

    public static void main(String[] args) {
        MagneticForceBetweenTwoBalls solution = new MagneticForceBetweenTwoBalls();

        System.out.println(solution.maxDistance(new int[]{1,2,3,4,7}, 3));

        

    }
}