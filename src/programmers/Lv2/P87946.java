package programmers.Lv2;

import java.util.Arrays;
import java.util.Comparator;

public class P87946 {
  public static void main(String[] args) {
    int k = 80;
    int[][] dungeons = {{80, 20}, {50, 40}, {30, 10}};
    System.out.println(new Solution().solution(k, dungeons));
  }

  private static class Solution {
    private int answer;
    private int length;
    private int[][] dungeons;
    private boolean[] blocked;

    /*
    1. 탐험 시작 전 "최소 필요 피로도", 탐험 마친 후 "소모 피로도"
      - "최소 필요 피로도 -> 80, 소모 피로도 -> 20" 일 경우, 현재 남은 피로도는 80이상이어야 하고, 던전 탐험 후에는 피로도 20이 소모된다.
      - k -> 현재 피로도, dungeons -> 각 행마다 "최소 필요 피로도, 소모 피로도"
    2. 유저는 최대한 많은 던전을 탐험해야 한다.
      - 탐험할 수 있는 최대 던전 수 -> answer
     */
    /*
    1. 1 <= k[현재 피로도] <= 5,000
    2. 1 <= dungeons.length <= 8
    3. 1 <= 소모 피로도 <= 최소 필요 피로도 <= 1,000
     */
    public int solution(int k, int[][] dungeons) {
      this.answer = 0;
      this.length = dungeons.length;
      this.dungeons = dungeons;
      blocked = new boolean[this.length];
      permutation(0, k);
      return answer;
    }

    private void permutation(int acc, int rest) {
      this.answer = Math.max(this.answer, acc);
      for (int i = 0; i < this.length; i++) {
        if (!isAvailable(rest, dungeons[i], blocked[i])) continue;
        blocked[i] = true;
        permutation(acc + 1, rest - dungeons[i][1]);
        blocked[i] = false;
      }
    }

    private boolean isAvailable(int k, int[] d, boolean blocked) {
      return !blocked && k >= d[0];
    }
  }
}
