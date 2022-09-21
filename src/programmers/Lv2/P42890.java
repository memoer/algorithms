package programmers.Lv2;

import java.util.*;

public class P42890 {
  public static void main(String[] args) {
    String[][] relation = {
            {"100", "ryan", "music", "2"},
            {"200", "apeach", "math", "2"},
            {"300", "tube", "computer", "3"},
            {"400", "con", "computer", "4"},
            {"500", "muzi", "music", "3"},
            {"600", "apeach", "music", "2"},
    };
    System.out.println(new Solution().solution(relation));
  }

  static class Solution {
    private int innerLength;
    private List<String>[] list;
    private String[][] relation;

    public int solution(String[][] relation) {
      this.relation = relation;
      this.innerLength = relation[0].length;
      this.list = new ArrayList[innerLength];
      int outterLength = relation.length;
      int answer = 0;

      for (int i = 0; i < this.innerLength; i++) {
        this.list[i] = new ArrayList<>();
        combination(0, 0, i + 1, new StringBuilder());
      }

      Set<String> uniqueSet = new HashSet<>();
      Set<String> check = new HashSet<>();
      for (List<String> l : list) {
        for (String candidate : l) {
          boolean isDuplicated = false;
          for (int i = 0; i < outterLength; i++) {
            String key = getKey(candidate, i);
            if (check.contains(key)) {
              isDuplicated = true;
              break;
            }
            check.add(key);
          }
          if (!isDuplicated) {
            uniqueSet.add(candidate);
          }
          check.clear();
        }
      }
      for (String unique : uniqueSet) {
        int length = unique.length();
        if (length == 1) {
          answer += 1;
          continue;
        }
        boolean plusAnswer = true;
        for (int i = length/2; i >= 0; i--) {
          StringBuilder sb = new StringBuilder();
          for (int j = 0; j < length; j++) {
            if (j == (i * 2) || j == (i * 2) + 1) {
              continue;
            }
            sb.append(unique.charAt(j));
          }
          if (uniqueSet.contains(sb.toString().trim())) {
            plusAnswer= false;
            break;
          }
        }
        if (plusAnswer) {
          answer += 1;
        }
      }
      return answer;
    }


    private void combination(int idx, int count, int end, StringBuilder key) {
      if (count == end) {
        this.list[end - 1].add(key.toString().trim());
        return;
      }
      for (; idx < this.innerLength; idx++) {
        key.append(idx).append(" ");
        combination(idx + 1, count + 1, end, key);
        int kLength = key.length();
        key.delete(kLength - 2, kLength);
      }
    }

    private String getKey(String candidateStr, int idx) {
      StringBuilder key = new StringBuilder();
      int[] candidate = Arrays.stream(candidateStr.split(" ")).mapToInt(Integer::parseInt).toArray();
      for (int c : candidate) {
        key.append(this.relation[idx][c]);
      }
      return key.toString();
    }
  }
}
