package programmers.Lv2;

import java.util.*;

public class P72412_2 {
  public static void main(String[] args) {
    String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior " +
            "chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior " +
            "chicken 50"};
    String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200",
            "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken " +
            "100", "- and - and - and - 150"};
    for (int num : new Solution().solution(info, query)) System.out.print(num + ", ");
    System.out.println();
  }

  private static class Solution {
    /*
    1. 1 <= info <= 50,000 [배열 크기]
    2. info 문자열 조건
      - 개발언어 -> "cpp, java, python"
      - 직군 -> "backend, frontend"
      - 경력 -> "junior, senior"
      - 소울푸드 -> "chicken, pizza"
      - 1 <= 점수 <= 100,000
    3. 1 <= query <= 100,000 [배열 크기]
    4. query 문자열 조건
      - "-" 표시는 조건 고려 X
     */
    private Map<String, List<Integer>> table;
    private Map<String, Boolean> sorted;
    public final static String DELIMITER = " ";

    public int[] solution(String[] info, String[] query) {
      final int QUERY_LENGTH = query.length;

      // 1. info(N) 길이 -> 최대 50,000
      // 2. O(16N)
      table = new HashMap<>(16 * info.length);
      for (String i : info) {
        Summary summary = Summary.of(i);
        // 시간복잡도 -> O(16) 상수
        permutation(summary.key.split(Solution.DELIMITER), summary.score);
      }

      sorted = new HashMap<>(table.size());
      for (String key : table.keySet()) sorted.put(key, false);

      // query(M) 길이 -> 최대 100,000
      int[] answer = new int[QUERY_LENGTH];
      for (int i = 0; i < QUERY_LENGTH; i++) {
        Summary summary = Summary.of(query[i]);
        List<Integer> list = table.get(summary.key);
        if (list == null) continue;
        if (!sorted.get(summary.key)) {
          // 1. 최악의 경우, info가 모두 동일한 문자열이 건네질 때 -> list의 길이가 info의 길이가 된다.
          // 2. 이를 정렬할 경우 시간 복잡도 -> O(logN) 1 <= M <= 50,000
          list.sort(Comparator.comparing(score -> score));
          sorted.put(summary.key, true);
        }
        // 1. 이진 탐색의 시간 복잡도는 O(logN)
        // 2. list의 길이가 N이 되기 떄문에, O(logN)
        answer[i] = list.size() - bisearch(list, summary.score);
      }
      // * info -> N, query -> M
      // 1. O(16N + M(logN + logN)) -> O(16N + 2MlogN)
      // 3. 상수 제거 -> O(N + MlogN) [1<=N<=50,000 / 1<=M<=100,000]
      // 206,094
      return answer;
    }

    // 해당 알고리즘은 아래에 있는 이진탐색 알고리즘이 매우 중요!
    private int bisearch(List<Integer> list, int target) {
      int left = 0;
      int right = list.size() - 1;
      while (left <= right) {
        int mid = (left + right) / 2;
        // 1. <=으로 조건할 경우, target 값과 같은 mid 인덱스에서 left 인덱스가 앞으로 더 나아간다.
        // 2. 이렇게 되면, target 값의 인덱스보다 left 인덱스가 앞서 나가기 때문에, 조건에 부합하는 점수가 하나 빼진다.
        // 3. < 으로 조건하여 무조건 target 값에 대해서 작을 경우에만 left 인덱스를 변경하고, target 값과 같을 경우에는 right를 계속 내리는 방식으로 진행해야 한다.
        if (list.get(mid) < target) left = mid + 1;
        else right = mid - 1;
      }
      return left;
    }

    private void permutation(String[] s, int score) {
      String ALL = "-";
      String[] a = new String[]{s[0], ALL};
      String[] b = new String[]{s[1], ALL};
      String[] c = new String[]{s[2], ALL};
      String[] d = new String[]{s[3], ALL};
      // 2 * 2 * 2 * 2  -> 16번의 반복문
      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < 2; j++) {
          for (int k = 0; k < 2; k++) {
            for (int l = 0; l < 2; l++) {
              String key = a[i] + b[j] + c[k] + d[l];
              if (table.get(key) == null) table.put(key, new ArrayList<>());
              table.get(key).add(score);
            }
          }
        }
      }
    }

    private static class Summary {
      private final String key;
      private final int score;

      public Summary(String key, int score) {
        this.key = key;
        this.score = score;
      }

      public static Summary of(String s) {
        int lastIdx = s.lastIndexOf(Solution.DELIMITER);
        int score = Integer.parseInt(s.substring(lastIdx + 1));
        return new Summary(s.substring(0, lastIdx).replaceAll(" and ", ""), score);
      }
    }
  }
}
