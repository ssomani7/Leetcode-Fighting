package cc.ArraysAndStrings;

import java.util.*;

//127. Word Ladder
public class WordLadder {

    //Time Limit Exceed
    private Map<String, Set<String>> map = new HashMap<>();

    private void addEle(List<String> wordList, String begin){
        if(!map.containsKey(begin)){
            map.put(begin, new HashSet<>());
        }
        for(String word: wordList){
            int diff = 0;
            for(int i = 0; i < word.length(); i++){
                if(word.charAt(i) != begin.charAt(i)){
                    diff ++;
                }
            }
            if(diff == 1){
                map.get(begin).add(word);
            }
        }

        return;
    }

    private int recursion(Set<String> setOld, String begin, String end, int dist){
        Set<String> set = new HashSet<>();
        set.addAll(setOld);
        Set<String> set1 = map.get(begin);
        int rst = Integer.MAX_VALUE;
        if(set1.contains(end)){
            return dist + 1;
        }
        if(set1.isEmpty()){
            return rst;
        }

        for(String s: set1){
            if(!set.contains(s)){
                set.add(s);
                int tmp = recursion(set, s, end, dist + 1);
                if(tmp != Integer.MAX_VALUE && rst > tmp){
                    rst = tmp;
                }
            }
        }
        return rst;
    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord)){
            return 0;
        }

        addEle(wordList, beginWord);
        addEle(wordList, endWord);
        for(String word: wordList){
            addEle( wordList, word);
        }

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int rst = recursion(visited, beginWord, endWord, 1);
        return rst == Integer.MAX_VALUE? 0: rst;
    }

    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        String beginWord = "hot";
        String endWord = "dog";
        String[] wordArr = {"hot","dog","cog","pot","dot"};
        List<String> wordList = new ArrayList<>();
        for(String word: wordArr){
            wordList.add(word);
        }
        int rst = wordLadder.ladderLength(beginWord, endWord, wordList);
        System.out.println(rst);
    }
}
