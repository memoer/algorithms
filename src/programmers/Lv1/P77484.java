package programmers.Lv1;

public class P77484 {
  static class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
      int[] answer = new int[2];
      int zero = 0;
      int temp = 7;
      for (int lotto : lottos) {
        if (lotto == 0) {
          zero += 1;
        }
      }
      if (zero == 6) {
        return new int[] { 1, 6 };
      }
      for (int lotto : lottos) {
        for (int num : win_nums) {
          if (lotto == num) {
            temp -= 1;
          }
        }
      }
      if (temp == 7) {
        return new int[] { 6, 6 };
      }
      answer[0] = temp - zero;
      answer[1] = temp;
      return answer;
    }
  }

  public static void main(String[] args) {
    int[] lottos = { 44, 1, 0, 0, 31, 25 };
    int[] win_nums = { 31, 10, 45, 1, 6, 19 };
    for (int num : new Solution().solution(lottos, win_nums)) {
      System.out.print(num + ", ");
    }
    System.out.println();
  }
}
