package programmers.Lv2;

import java.util.HashSet;
import java.util.Set;

public class P12981 {
  public static void main(String[] args) {
    int n = 3;
    String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
    for (int num : new Solution().solution(n, words)) System.out.print(num + ", ");
    System.out.println();
  }

  private static class Solution {
    private final Set<String> set = new HashSet<>();
    private char last = '\0';

    /*
    **알고리즘 조건**
    1. 1 ~ n 까지 n명의 사람
    2. 1번부터 번호 순서대로 한 사람씩 진행
    3. 마지막 사람이 단어를 말한 다음에는 다시 1번부터 시작 [rotation]
    4. 앞사람이 말한 단어의 마지막 문자로 시작하는 단어를 말해야 함
    5. 이전에 등장했던 단어는 사용 불가 [중복 불가]
    6. 한 글자는 인정 안 됨
     */
    /*
    **구해야 하는 것**
    1. 사람의 수 n과 순서대로 말한 단어 words가 주어진다.
    2. 가장 먼저 탈락하는 사람의 번호, 그 사람이 자신의 "몇 번째 차례"에 탈락하는 지 구할 것 -> 정답은 [사람의 번호, 차례] 로 반환
    3. 주어진 단어들로 탈락자가 없다면, [0,0] 반환
     */
    /*
    **범위**
    1. 2 <= n <= 10
    2. n <= words.length <= 100
    3. 2 <= words[i].length <= 50
     */
    public int[] solution(int n, String[] words) {
      final int LENGTH = words.length;
      for (int i = 0; i < LENGTH; i++) {
        if (isAvailable(words[i])) {
          set.add(words[i]);
          last = words[i].charAt(words[i].length() - 1);
        } else {
          return new int[]{i % n + 1, i / n + 1};
        }
      }
      return new int[]{0, 0};
    }

    private boolean isAvailable(String word) {
      if (last == '\0' || word.charAt(0) == last) return !set.contains(word);
      return false;
    }
  }
}
