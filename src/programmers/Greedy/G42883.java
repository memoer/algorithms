package programmers.Greedy;

public class G42883 {
  static class Solution {
    public String solution(String number, int k) {
      StringBuilder sb = new StringBuilder();
      int idx = 0;
      // 만들어야할 문자의 길이수 만큼 반복
      for (int i = 0; i < number.length() - k; i++) {
        // number.length() - k -> 만들어질 문자의 길이 [ 9,4 일 경우, 문자열의 길이는 5 ]
        // 그러면 총 반복문은 만들어질 문자의 길이만큼만 돈다 -> 5번만 도는 것.
        int max = 0;
        for (int j = idx; j < i + k + 1; j++) {
          System.out.print(number.charAt(j) + ", ");
          int num = number.charAt(j) - '0';
          if (max < num) {
            max = num;
            idx = j;
          }
        }
        System.out.println();
        // 총 5번 돌아서 5의 길이를 가진 문자열이 완성된다.
        sb.append(number.charAt(idx++));
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    String number = "4177252841";
    int k = 4;
    System.out.println(new Solution().solution(number, k));
  }
}

// 4177252841

// 4177 252841
// 4 1772 52841
// 41 7725 2841
// 417 7252 841
// 4177 2528 41
// 41772 5284 1
// 417725 2841