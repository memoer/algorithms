package programmers.DFSnBFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
// ! 오답! 테스트 케이스 1개가 틀린다.

// ? 모든 문자열의 수는 3으로 고정
// ? tickets의 문자열 길이는 3 ~ 10,000
// ? tickets[0] -> tickets[1] 로 이동
// ? tickets에 존재하는 모든 ticket들을 다 써야함
// ! ticket을 다 쓰면서, 거쳐간 항공들의 이름 배열을 return [ 경로가 여러개일 경우, 동일한 항공은 제외+틀린 항공들을 알파벳순으로 정렬 ]
// ! -> 동일한 항공을 제외하고, 다른 항공 A,B 가 있을 때, A.compareTo(B) 가 양수가 나올 경우 -> B를 채택 / 음수가 나올 경우 -> A를 채택
public class D43164 {
  // ** [ATL, ICN], [ATL, SFO], [ICN, SFO], [ICN, ATL], [SFO, ATL]
  static class Solution {
    private String START_AIRPLANE = "ICN";
    private int START_MIN_IDX = 0;
    private int START_MAX_IDX = 0;
    private List<String[]> candidate = new ArrayList<>();
    private String[][] ticketList;

    private void process(String curTicketB, int startIdx, int endIdx, List<Integer> usedTicketList, List<String> path) {
      for (int i = startIdx; i < endIdx; i++) {
        String[] nextTicket = ticketList[i];
        if (isCandidate(usedTicketList, i, curTicketB, nextTicket[0])) {
          usedTicketList.add(i);
          path.add(nextTicket[1]);
          dfs(nextTicket, usedTicketList, path);
          usedTicketList.add(usedTicketList.size() - 1);
          path.remove(path.size() - 1);
        }
      }
    }

    private boolean isCandidate(List<Integer> list, int i, String airplaneA, String airplaneB) {
      return !list.contains(i) && airplaneA.equals(airplaneB);
    }

    private void dfs(String[] ticket, List<Integer> usedTicketList, List<String> path) {
      if (ticketList.length + 1 == path.size()) {
        candidate.add(path.toArray(new String[0]));
        return;
      }
      String curTicketB = ticket[1];
      if (curTicketB.charAt(0) < 'I') {
        // 왼쪽으로 탐색 A ~ I
        process(curTicketB, 0, START_MIN_IDX, usedTicketList, path);
      } else if (curTicketB.charAt(0) > 'I') {
        // 오른쪽으로 탐색 I ~ Z
        process(curTicketB, START_MAX_IDX + 1, this.ticketList.length, usedTicketList, path);
      } else {
        process(curTicketB, START_MIN_IDX, START_MAX_IDX + 1, usedTicketList, path);
      }
    }

    private void init(String[][] tickets) {
      Arrays.sort(tickets, (pre, cur) -> pre[0].charAt(0) - cur[0].charAt(0));
      this.ticketList = tickets;
    }

    private List<Integer> getStartIdxList(String[][] tickets) {
      List<Integer> startIdxList = new ArrayList<>();
      for (int i = 0; i < this.ticketList.length; i++) {
        String[] ticket = this.ticketList[i];
        if (ticket[0].equals(this.START_AIRPLANE)) {
          startIdxList.add(i);
        }
      }
      START_MIN_IDX = startIdxList.get(0);
      START_MAX_IDX = startIdxList.get(startIdxList.size() - 1);
      return startIdxList;
    }

    public String[] solution(String[][] tickets) {
      init(tickets);
      List<Integer> startIdxList = getStartIdxList(tickets);
      for (int startIdx : startIdxList) {
        List<Integer> usedTicketList = new ArrayList<>();
        List<String> result = new ArrayList<>();
        String[] ticket = this.ticketList[startIdx];
        usedTicketList.add(startIdx);
        result.add(ticket[0]);
        result.add(ticket[1]);
        dfs(ticket, usedTicketList, result);
      }
      String[] answer = candidate.stream().sorted((pre, cur) -> {
        for (int i = 0; i < candidate.get(0).length; i++) {
          int compare = pre[i].compareTo(cur[i]);
          if (compare < 0) {
            return compare;
          }
        }
        return 0;
      }).collect(Collectors.toList()).get(0);
      return answer;
    }
  }

  public static void main(String[] args) {
    String[][][] testCase = {
        { { "ICN", "SFO" }, { "ICN", "ATL" }, { "SFO", "ATL" }, { "ATL", "ICN" }, { "ATL", "SFO" } },
        { { "ICN", "JFK" }, { "HND", "IAD" }, { "JFK", "HND" } },
        { { "ICN", "AAA" }, { "ICN", "AAA" }, { "ICN", "AAA" }, { "AAA", "ICN" }, { "AAA", "ICN" } },
        { { "ICN", "BOO" }, { "ICN", "COO" }, { "COO", "DOO" }, { "DOO", "COO" }, { "BOO", "DOO" }, { "DOO", "BOO" },
            { "BOO", "ICN" }, { "COO", "BOO" }, },
        { { "ICN", "A" }, { "ICN", "A" }, { "A", "ICN" }, }, { { "ICN", "COO" }, { "ICN", "BOO" }, { "COO", "ICN" } },
        { { "ICN", "SFO" }, { "SFO", "ICN" }, { "ICN", "SFO" }, { "SFO", "QRE" }, }, };
    for (String[][] tickets : testCase) {
      for (String s : new Solution().solution(tickets)) {
        System.out.print(s + ", ");
      }
      System.out.println();
    }
  }
}

// *{ { "SFO", "ICN" },{ "ICN", "SFO" }, { "ICN", "SFO" }, { "SFO", "QRE" }, }