import java.util.Arrays;

class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;

        int count = 0, interval = n+1, i = 0;
        int[] memo = new int[26];

        for (char ch : tasks) memo[ch - 'A']++;

        Arrays.sort(memo);

        while (i < tasks.length) {
            for (int j = 25; j >= 0; j--) {
                if (memo[j] != 0) {
                    memo[j]--;
                    count++;
                    interval--;
                    i++;
                    if (interval == 0) break;
                } else {
                    break;
                }
            }           
            
            if (i == tasks.length) break;

            if (interval > 0) {
                count += interval;
            }
            interval = n+1;
            Arrays.sort(memo);
        }

        return count;
    }


    public static void main(String[] args) {
        TaskScheduler solution = new TaskScheduler();

        System.out.println(solution.leastInterval(new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}, 2));
    }
}