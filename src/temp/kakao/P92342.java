package temp.kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P92342 {
  /**
   * 1. 라이언이 "가장 큰 점수 차이"로 우승하기 위해 n발의 화살을 "어떤 과녁 점수에 맞혀야 하는지"를 "10점부터 0점까지 순서대로" 정수 배열 반환
   * - 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 return
   * 2. 라이언이 우승할 수 없는 경우 [-1] 반환
   */
  /**
   * 1. 어피치가 화살 n발 쏘고 -> 라이언이 화살 n발 쏜다.
   * 2. k점에 대해 더 많은 화살을 맞힌 선수가 k점을 가져간다, but 동일할 경우 어피치가 가져간다. [k점을 여러 발 맞혀도 k점보다 많은 점수가 아닌, k점만 가져간다.]
   *    - 두 선수 모두 못 맞힐 경우, 어느 누구도 k점을 가져가지 않는다.
   * 3. 최종 점수가 높은 선수가 우승자. but, 같을 경우 어피치가 우승자
   */
  /**
   * n -> 화살의 개수
   * info -> 어피치가 맞힌 과녁의 점수의 개수 10점부터 0점까지
   */
  final int[] SCORE_ARR = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
  final int LENGTH = 11;
  int[] aInfo;
  List<Simulation> simulationList;
  int max;

  public static void main(String[] args) {
    int n = 10;
    int[] info = {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};
    for (int v : new P92342().solution(n, info)) System.out.print(v + ", ");
    System.out.println();
  }

  public int[] solution(int n, int[] info) {
    this.aInfo = info;
    this.max = Integer.MIN_VALUE;
    simulationList = new ArrayList<>();

    combination(n, 0, new int[LENGTH]);
    return getAns();
  }

  private void combination(int rest, int start, int[] bInfo) {
    if (rest == 0) {
      int diff = getDiff(bInfo);
      if (diff > 0) {
        simulationList.add(new Simulation(diff, Arrays.copyOf(bInfo, LENGTH)));
        max = Integer.max(max, diff);
      }
      return;
    }
    for (int i = start; i < LENGTH; i++) {
      int n = aInfo[i] >= rest ? rest : aInfo[i] + 1;
      bInfo[i] = n;
      combination(rest - n, i + 1, bInfo);
      bInfo[i] = 0;
    }
  }

  private int getDiff(int[] bInfo) {
    int a = 0;
    int b = 0;
    for (int i = 0; i < LENGTH; i++) {
      if (aInfo[i] == 0 && bInfo[i] == 0) continue;
      if (aInfo[i] >= bInfo[i]) a += SCORE_ARR[i];
      else b += SCORE_ARR[i];
    }
    return b - a;
  }

  private int[] getAns() {
    if (simulationList.isEmpty()) return new int[]{-1};
    List<Simulation> ans = new ArrayList<>();
    for (Simulation simulation : simulationList) if (simulation.diff == max) ans.add(simulation);
    int size = ans.size();
    if (size == 1) return ans.get(0).arr;
    int idx = -1;
    for (int i = LENGTH - 1; i >= 0; i--) {
      int max = Integer.MIN_VALUE;
      for (int j = 0; j < size; j++) max = Integer.max(max, ans.get(j).arr[i]);
      if (max == 0) continue;
      int cnt = 0;
      for (int j = 0; j < size; j++) {
        if (ans.get(j).arr[i] != max) continue;
        cnt += 1;
        idx = j;
      }
      if (cnt == 1) break;
    }
    return ans.get(idx).arr;
  }

  private static class Simulation {
    private final int diff;
    private final int[] arr;

    public Simulation(int diff, int[] arr) {
      this.diff = diff;
      this.arr = arr;
    }
  }

}

