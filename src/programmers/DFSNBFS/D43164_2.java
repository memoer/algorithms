package programmers.DFSnBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 정답! 풀었따
// ? 앞에 문자열을 기반으로 알파벳 순서 sorting
// ? 앞에 문자열이 같다면, 뒤의 문자열 기반으로 알파벳 순서 sorting
public class D43164_2 {
  static class Solution {
    private final String START_AIRPLANE = "ICN";
    private boolean isSearched = false;
    private int minStartIdx = 0;
    private int maxStartIdx = 0;
    private String[] answer;
    private String[][] ticketList;

    private void init() {
      Arrays.sort(this.ticketList,
          (pre, cur) -> pre[0].compareTo(cur[0]) == 0 ? pre[1].compareTo(cur[1]) : pre[0].compareTo(cur[0]));
      List<Integer> idxList = new ArrayList<>();
      for (int i = 0; i < this.ticketList.length; i++) {
        String airplain = this.ticketList[i][0];
        if (airplain.equals(START_AIRPLANE)) {
          idxList.add(i);
        }
      }
      minStartIdx = idxList.get(0);
      maxStartIdx = idxList.get(idxList.size() - 1);
    }

    private void move(int startIdx, int endIdx, String curAirplaine, List<String> candidate,
        List<Integer> passedByIdx) {
      for (int i = startIdx; i < endIdx; i++) {
        String nextAirplaineA = ticketList[i][0];
        if (isSearched) {
          // dfs 시간 복잡도 최소화
          return;
        } else if (curAirplaine.equals(nextAirplaineA) && !passedByIdx.contains(i)) {
          String nextAirplaineB = ticketList[i][1];
          candidate.add(nextAirplaineB);
          passedByIdx.add(i);
          dfs(nextAirplaineB, candidate, passedByIdx);
          candidate.remove(candidate.size() - 1);
          passedByIdx.remove(passedByIdx.size() - 1);
        }
      }
    }

    private void dfs(String curAirplaine, List<String> candidate, List<Integer> passedByIdx) {
      if (candidate.size() == this.ticketList.length + 1) {
        isSearched = true;
        answer = candidate.toArray(new String[0]);
        return;
      }
      final char CUR_PREFIX_AIRPLAIN = curAirplaine.charAt(0);
      final char START_PREFIX_AIRPLAIN = START_AIRPLANE.charAt(0);
      // Search 시간복잡도 최소화
      if (CUR_PREFIX_AIRPLAIN < START_PREFIX_AIRPLAIN) {
        // A ~ I 왼쪽
        move(0, maxStartIdx + 1, curAirplaine, candidate, passedByIdx);
      } else if (CUR_PREFIX_AIRPLAIN > START_PREFIX_AIRPLAIN) {
        // I ~ Z 오른쪽
        move(minStartIdx, this.ticketList.length, curAirplaine, candidate, passedByIdx);
      } else {
        // I
        move(minStartIdx, maxStartIdx + 1, curAirplaine, candidate, passedByIdx);
      }
    }

    public String[] solution(String[][] ticketList) {
      this.ticketList = ticketList;
      init();
      for (int i = minStartIdx; i < maxStartIdx + 1; i++) {
        String[] ticket = this.ticketList[i];
        List<String> candidate = new ArrayList<>(this.ticketList.length + 1);
        List<Integer> passedByIdx = new ArrayList<>(this.ticketList.length);
        candidate.add(ticket[0]);
        candidate.add(ticket[1]);
        passedByIdx.add(i);
        dfs(ticket[1], candidate, passedByIdx);
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    String[][][] testCase = {
        // { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" }, {
        // "ATL", "SFO" } },
        // { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } },
        // { { "ICN", "AAA" }, { "ICN", "AAA" }, { "ICN", "AAA" }, { "AAA", "ICN" }, {
        // "AAA", "ICN" } },
        // { { "ICN", "BOO" }, { "ICN", "COO" }, { "COO", "DOO" }, { "DOO", "COO" }, {
        // "BOO", "DOO" }, { "DOO", "BOO" },
        // { "BOO", "ICN" }, { "COO", "BOO" }, },
        // { { "ICN", "A" }, { "ICN", "A" }, { "A", "ICN" }, }, { { "ICN", "COO" }, {
        // "ICN", "BOO" }, { "COO", "ICN" } },
        { { "ICN", "SFO" }, { "SFO", "ICN" }, { "ICN", "SFO" }, { "SFO", "QRE" }, }, };
    for (String[][] ticketList : testCase) {
      for (String s : new Solution().solution(ticketList)) {
        System.out.print(s + ", ");
      }
      System.out.println();
    }
  }
}
// { "ICN", "SFO" }, { "ICN", "SFO" }, { "SFO", "ICN" }, { "SFO", "QRE" },