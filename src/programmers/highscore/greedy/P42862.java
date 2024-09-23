package programmers.highscore.greedy;

import java.util.Arrays;

public class P42862 {

  public static void main(String[] args) {
    int n = 5;
    int[] lost = new int[]{2, 4};
    int[] reserve = new int[]{3};
    int solution = new Solution().solution(n, lost, reserve);
    System.out.println(solution);
  }

  private static class Solution {

    // "바로 앞 번호 (OR) 뒷번호"에게만 빌려줄 수 있다.
// 체육복이 없으면, 수업을 들을 수 없다.
    public int solution(int n, int[] lost, int[] reserve) {
      int[] clothes = initialize(n, lost, reserve);
      for (int i = 0; i < n; i++) {
        if (clothes[i] < 2) {
          continue;
        }
        if (i - 1 >= 0 && clothes[i - 1] == 0) {
          clothes[i] -= 1;
          clothes[i - 1] += 1;
        } else if (i + 1 < n && clothes[i + 1] == 0) {
          clothes[i] -= 1;
          clothes[i + 1] += 1;
        }
      }
      return getResult(clothes);
    }

    private int[] initialize(int n, int[] lost, int[] reserve) {
      int[] result = new int[n];
      Arrays.fill(result, 1);
      for (int i : reserve) {
        result[i - 1] += 1;
      }
      for (int i : lost) {
        result[i - 1] -= 1;
      }
      return result;
    }

    private int getResult(int[] clothes) {
      int result = 0;
      for (int cloth : clothes) {
        if (cloth != 0) {
          result += 1;
        }
      }
      return result;
    }
  }
}
