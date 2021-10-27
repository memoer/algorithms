package programmers.sort;

import java.util.Arrays;

public class S42746 {
  static class Solution {
    public String solution(int[] numbers) {
      String answer = "";
      String arrNum[] = new String[numbers.length];
      for (int i = 0; i < arrNum.length; i++) {
        arrNum[i] = String.valueOf(numbers[i]);
      }
      Arrays.sort(arrNum, (s1, s2) -> {
        System.out.println("s1: " + s1 + ", s2: " + s2);
        System.out.println("s1+s2: " + (s1 + s2) + ", s2+s1: " + (s2 + s1));
        System.out.println("result: " + (s2 + s1).compareTo(s1 + s2));
        System.out.println((s2).compareTo(s1));
        // 앞에께 뒤에꺼보다 크면 1(바꿈), 작으면 -1(안바꿈)
        return (s2 + s1).compareTo(s1 + s2);
      });
      for (String n : arrNum) {
        System.out.println(n);
      }
      if (arrNum[0].equals("0")) {
        return "0";
      } else {
        for (String num : arrNum) {
          answer += num;
        }
      }
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] numbers = { 34, 30, 3, 9, 5 };
    System.out.println(new Solution().solution(numbers));
  }
}
