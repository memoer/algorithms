package programmers.Lv2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P17684 {
  public static void main(String[] args) {
    String msg = "TOBEORNOTTOBEORTOBEORNOT";
    for (int n : new Solution().solution(msg)) System.out.print(n + ", ");
    System.out.println();
  }

  private static class Solution {
    private String msg;
    private int length;
    private Map<String, Integer> book;

    /*
        1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화
        ---
        2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 `w` 를 찾는다.
        3. `w` 에 해당하는 사전의 색인 번호를 출력하고, 입력에서 `w`를 제거한다.
        4. 입력에서 처리되지 않은 다음 글자가 남아있다면[`c`], `w+c`에 해당하는 단어를 사전에 등록한다.
        5. 단계 2로 넘어간다.
        ---
         */
    public int[] solution(String msg) {
      this.msg = msg;
      this.length = msg.length();
      this.book = getBook();
      List<Integer> answer = new ArrayList<>();

      for (int idx = 0; idx < length; ) {
        String[] arr = getWC(idx);
        idx += arr[0].length();
        answer.add(book.get(arr[0]));
        book.put(arr[1], book.size() + 1);
      }

      return answer.stream().mapToInt(v -> v).toArray();
    }

    private String[] getWC(int idx) {
      StringBuilder w = new StringBuilder();
      StringBuilder c = new StringBuilder();
      for (; idx < length; idx++) {
        c.append(msg.charAt(idx));
        if (!book.containsKey(c.toString())) break;
        else w.append(msg.charAt(idx));
      }
      return new String[]{w.toString(), c.toString()};
    }


    private Map<String, Integer> getBook() {
      Map<String, Integer> book = new HashMap<>();
      for (char i = 65; i < 91; i++) book.put(String.valueOf(i), i - 64);
      return book;
    }
  }
}
