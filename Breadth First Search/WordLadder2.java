import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class WordLadder2 {
    int minLevel = Integer.MAX_VALUE;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        if (beginWord == null || endWord == null || !wordList.contains(endWord)) return result;

        Set<String> wordSet = new HashSet<>(wordList);

        List<String> subList = new ArrayList<>();
        subList.add(beginWord);
        wordSet.remove(beginWord);

        backtracking(wordSet, result, subList, beginWord, endWord, -1);

        return result;
    }

    private void backtracking(Set<String> wordSet, List<List<String>> result, List<String> subList, 
        String beginWord, String endWord, int index) {
        if (wordSet.isEmpty() || wordSet.size() > minLevel) return;

        for (int i = 0; i < beginWord.length(); i++) {
            if (i == index) continue;
            char[] words = beginWord.toCharArray();
            for (char ch = 'a'; ch <= 'z'; ch++) {
                words[i] = ch;
                String word = String.valueOf(words);
                if (word.equals(endWord)) {
                    List<String> temp = new ArrayList<>(subList);
                    temp.add(word);
                    result.add(temp);
                    minLevel = Math.min(minLevel, temp.size());
                    return;
                }

                if (wordSet.contains(word)) {
                    subList.add(word);
                    wordSet.remove(word);
                    backtracking(wordSet, result, subList, word, endWord, i);
                    wordSet.add(word);
                    subList.remove(subList.size()-1);
                }


            }
        }
    }


    /**
     * 先用 BFS 找到最短的路径，再用 DFS 将这些路径遍历出来
     * 在 BFS 的过程中，相当于建立一张图，记录每一个节点在哪一层，每一个顶点的子节点有哪些
     * 而且还要记录最短的路径，如果超过最短路径，则跳出循环
     */
    public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        if (beginWord == null || endWord == null || !wordList.contains(endWord)) return result;

        // 记录每个节点的子节点
        Map<String, List<String>> parents = new HashMap<>();

        // 记录每一个节点所在的层数
        Map<String, Integer> level = new HashMap<>();
        for (String s : wordList) {
            level.put(s, Integer.MAX_VALUE);
        }

        // 最短路径的层数
        int minLevel = Integer.MAX_VALUE;

        // 用于 BFS 遍历
        Queue<String> queue = new LinkedList<>();

        // 处理 beginWord
        level.put(beginWord, 0);
        queue.offer(beginWord);


        while (!queue.isEmpty()) {
            String word = queue.poll();

            int steps = level.get(word) + 1;

            if (steps > minLevel) break;

            for (int i = 0; i < word.length(); i++) {
                char[] words = word.toCharArray();
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (ch == words[i]) continue;
                    words[i] = ch;
                    String changedWord = String.valueOf(words);

                    if (level.containsKey(changedWord)) {
                        if (steps > level.get(changedWord)) continue;
                        else if (steps < level.get(changedWord)) {
                            queue.offer(changedWord);
                            level.put(changedWord, steps);
                        }

                        // 当 steps == level.get(changedWord) 时，有可能是不同的父节点到达该节点，此时不能把节点多次加入 queue 中

                        if (parents.containsKey(changedWord)) {
                            parents.get(changedWord).add(word);
                        } else {
                            parents.put(changedWord, new ArrayList<>());
                            parents.get(changedWord).add(word);
                        }

                        if (changedWord.equals(endWord)) minLevel = steps;
                    }
                }
            }
        }

        // 然后用 DFS 去构造路径
        dfs(endWord, beginWord, new LinkedList<>(), parents, result);

        return result;
    }

    private void dfs(String word, String beginWord, List<String> subList, Map<String, List<String>> parents, List<List<String>> result) {
        if (word.equals(beginWord)) {
            subList.add(0, word);
            result.add(new ArrayList<>(subList));
            subList.remove(0);
            return;
        }

        subList.add(0, word);
        if (parents.get(word) != null) {
            for (String s : parents.get(word)) {
                dfs(s, beginWord, subList, parents, result);
            }
        }
        subList.remove(0);
    }


    public static void main(String[] args) {
        WordLadder2 solution = new WordLadder2();
        // String[] words = {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
        String[] words = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = Arrays.asList(words);
        List<List<String>> result = solution.findLadders2("hit", "cog", wordList);
        result.forEach(list -> System.out.println(list.toString()));
    }
}