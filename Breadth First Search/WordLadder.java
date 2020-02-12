import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class WordLadder {
    /**
     * beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     * 这个题可以这么想，假设 beginWord 处于第一层，其改变其中一个字母所变成的字符串且存在于 wordList 中的字符串处于第二层
     * 然后第二层每个字符串又重复刚才的工作，直到出现 endWord 为止
     * 则 hit -> hot -> dot, lot -> dog, log -> cog 则 cog 处在第 5 层，所以返回 5
     * 时间复杂度为 O(M * N) M 为字符串长度，N 为 wordList 的长度
     * 空间复杂度也为 O(M * N)
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0 || !wordList.contains(endWord)) return 0;

        // 去重
        Set<String> set = new HashSet<>(wordList);
        set.remove(beginWord);
        Queue<String> queue = new LinkedList<>();

        int level = 1;
        // 对每一层进行计数
        int count = 1;

        queue.add(beginWord);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            count--;

            for (int i = 0; i < cur.length(); i++) {
                char[] word = cur.toCharArray();
                for (char j = 'a'; j <= 'z'; j++) {
                    word[i] = j;
                    String wordStr = String.valueOf(word);
                    if (set.contains(wordStr)) {                      
                        if (wordStr.equals(endWord)) return level + 1;

                        // 防止循环
                        set.remove(wordStr);
                        queue.add(wordStr);
                    }
                }
            }

            if (count == 0) {
                count = queue.size();
                level++;
            }
        }

        return 0;
    }


    /**
     * 双向广度优先，从 beginWord 和 endWord 两个方向进行广度优先算法，直到两个方向转换到相同的字符串为止
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        
    }

    public static void main(String[] args) {
        WordLadder solution = new WordLadder();
        // String[] words = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = new ArrayList<>();
        wordList.add("htg");
        wordList.add("ctg");
        // wordList.add("bog");
        // wordList.add("dog");
        // wordList.add("lot");
        // wordList.add("log");
        wordList.add("cog");

        System.out.println(solution.ladderLength("htg", "cog", wordList));
    }
}