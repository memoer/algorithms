package temp.kakao;

import java.util.*;

public class P42890 {
  private String[][] relation;
  private List<String> keyList;
  private List<String> ans;

  public static void main(String[] args) {
    String[][] relation = {
            {"100", "ryan", "music", "2"},
            {"200", "apeach", "math", "2"},
            {"300", "tube", "computer", "3"},
            {"400", "con", "computer", "4"},
            {"500", "muzi", "music", "3"},
            {"600", "apeach", "music", "2"}
    };
    System.out.println(new P42890().solution(relation));
  }

  // 후보키 + 최소성을 만족하는 종류의 개수를 리턴하라.
  public int solution(String[][] relation) {
    this.relation = relation;
    this.keyList = new ArrayList<>();
    this.ans = new ArrayList<>();

    int columnLength = relation[0].length;
    for (int r = 1; r <= columnLength; r++) combination(columnLength, r, 0, new StringBuilder());
    for (String key : keyList) if (isCandidateKey(key) && isMinimalKey(key)) ans.add(key);

    return ans.size();
  }

  private void combination(int n, int r, int start, StringBuilder sb) {
    int len = sb.length();
    if (len == r) {
      keyList.add(sb.toString());
      return;
    }
    for (int i = start; i < n; i++) {
      sb.append(i);
      combination(n, r, i + 1, sb);
      sb.deleteCharAt(len);
    }
  }

  private boolean isCandidateKey(String key) {
    int[] idxList = Arrays.stream(key.split("")).mapToInt(Integer::parseInt).toArray();
    Set<String> set = new HashSet<>();
    for (String[] record : relation) {
      StringBuilder sb = new StringBuilder();
      for (int idx : idxList) sb.append(record[idx]);
      if (!set.add(sb.toString())) return false;
    }
    return true;
  }

  private boolean isMinimalKey(String key) {
    // key -> 034, v -> 03
    // key -> 012, v -> 02
    char[] chars = key.toCharArray();
    int keyLen = key.length();
    for (String v : ans) {
      int vLen = v.length();
      int i = 0;
      int j = 0;
      while (i < keyLen && j < vLen) if (chars[i++] == v.charAt(j)) j++;
      if (j == vLen) return false;
    }
    return true;
  }
}
