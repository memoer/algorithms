package programmers.Lv2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P42890_2 {
  public static void main(String[] args) {
    String[][] relation = {
            {"100", "ryan", "music", "2"},
            {"200", "apeach", "math", "2"},
            {"300", "tube", "computer", "3"},
            {"400", "con", "computer", "4"},
            {"500", "muzi", "music", "3"},
            {"600", "apeach", "music", "2"},
    };
    System.out.println(new P42890_2.Solution().solution(relation));
  }
  static class Solution {
    public int solution(String[][] relation) {
      int n = relation.length;        // n개의 데이터
      int m = relation[0].length;     // m개의 컬럼
      ArrayList<Integer> list = new ArrayList<Integer>();

      // 완전 탐색을 돈다.
      for (int i = 1; i < (1 << m); i++) {
        Set<String> s = new HashSet<String>();
        // n개의 데이터
        for (int j = 0; j < n; j++) {
          String now = "";
          // m개의 컬럼
          for (int k = 0; k < m; k++) {
            // 모든 경우의 수 & 해당 라인 데이터 조합.
            if ((i & (1 << k)) > 0) {
              now += relation[j][k];
            }
          }
          s.add(now);
        }
        if (s.size() == n && possi(list, i)) list.add(i);
      }
      return list.size();
    }

    public boolean possi(List<Integer> list, int now) {
      for (int i = 0; i < list.size(); i++) {
        // 같으면 최소성을 만족하지 못함.
        if ((list.get(i) & now) == list.get(i)) {
          return false;
        }
      }
      return true;
    }
  }

}
