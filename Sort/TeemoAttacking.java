class TeemoAttacking {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int result = 0;
        if (timeSeries == null || timeSeries.length == 0 || duration < 1) return result;

        for (int i = 0; i < timeSeries.length; i++) {
            if (i == timeSeries.length - 1 || timeSeries[i] + duration <= timeSeries[i+1]) {
                result += duration;
            } else {
                result += timeSeries[i+1] - timeSeries[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TeemoAttacking solution = new TeemoAttacking();

        int[] nums = {1, 4};
        System.out.println(solution.findPoisonedDuration(nums, 2));
    }
}