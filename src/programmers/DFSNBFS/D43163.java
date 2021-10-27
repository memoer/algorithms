package programmers.DFSNBFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 맨 처음 beginWord와 배열내부 단어에서 한 단어만 다른 단어들 추출 -> 이를 dfs 실행
// 1. 현재 단어가 target과 같은가?
//  - 맞으면 break;
// 2. 현재 단어 <-> 배열에 있는 단어 비교 -> 하나의 알파벳만 다른 단어로
//  - 전에 변경했던 워드는 제외
public class D43163 {
  static class Solution {
    private Map<String, List<Integer>> availableMap = new HashMap<>();
    private String[] words;
    private String target;
    private int answer = Integer.MAX_VALUE;

    private void init(String begin, String[] words) {
      // cache -> 배열안에 있는 각각의 문자열이 다른 문자열로 갈 수 있는 idx list를 만들어준다.
      // [ 알파벳 하나만 변경된 index 들]
      // 추후 탐색을 진행할 때, 해당 index 로만 이동한다.
      availableMap.put(begin, new ArrayList<>());
      for (int i = 0; i < words.length; i++) {
        String b = words[i];
        int diffCount = 0;
        for (int j = 0; j < b.length(); j++) {
          if (begin.charAt(j) != b.charAt(j)) {
            diffCount++;
          }
        }
        if (diffCount == 1) {
          availableMap.get(begin).add(i);
        }
      }
      for (int i = 0; i < words.length; i++) {
        List<Integer> temp = new ArrayList<>();
        String a = words[i];
        for (int j = 0; j < words.length; j++) {
          int diffCount = 0;
          String b = words[j];
          if (i == j) {
            continue;
          }
          for (int k = 0; k < b.length(); k++) {
            if (a.charAt(k) != b.charAt(k)) {
              diffCount++;
            }
          }
          if (diffCount == 1) {
            temp.add(j);
          }
        }
        availableMap.put(a, temp);
      }

    }

    private boolean isInclude(String target, String[] words) {
      // 만약, 주어진 배열에 target이 없으면 false를 리턴, 있으면 true를 리턴한다.
      boolean isInclude = false;
      for (String word : words) {
        if (word.equals(target)) {
          isInclude = true;
          break;
        }
      }
      return isInclude;
    }

    private void dfs(String word, int acc, List<String> passedByWordList) {
      // 탐색
      if (word.equals(target)) {
        this.answer = Math.min(this.answer, acc);
        return;
      }
      for (int idx : availableMap.get(word)) {
        String w = words[idx];
        if (!passedByWordList.contains(w)) {
          passedByWordList.add(w);
          dfs(w, acc + 1, passedByWordList);
          passedByWordList.remove(w);
        }
      }
    }

    public int solution(String begin, String target, String[] words) {
      if (!isInclude(target, words)) {
        // 주어진 배열에 target이 없으면 바로 return 0
        return 0;
      }
      List<String> passedByWordList = new ArrayList<>();
      passedByWordList.add(begin);
      this.target = target;
      this.words = words;
      init(begin, words);
      dfs(begin, 0, passedByWordList);
      return this.answer;
    }
  }

  public static void main(String[] args) {
    String begin = "hit";
    String target = "cog";
    String[] words = { "hot", "dot", "dog", "lot", "log", "cog" };
    System.out.println(new Solution().solution(begin, target, words));
  }
}
