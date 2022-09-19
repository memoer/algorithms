package temp.kakao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class P42889 {
  public static void main(String[] args) {
    int N = 5;
    int[] stages = {3, 3, 3, 3};
    for (int n : new P42889().solution(N, stages)) {
      System.out.print(n + ", ");
    }
    System.out.println();
  }
  // 실패율은 다음과 같이 정의한다. -> 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수

  public int[] solution(int N, int[] stages) {
    int len = Integer.max(Arrays.stream(stages).max().getAsInt(), N);
    int[] notClearedCountArr = getNotClearedArr(len, stages);
    int[] totalArr = getTotalArr(len, notClearedCountArr);
    return getFailRateMap(len, totalArr, notClearedCountArr)
            .entrySet()
            .stream()
            .filter(e -> e.getKey() <= N)
            .sorted((pre, cur) -> {
              int compare = Double.compare(cur.getValue(), pre.getValue());
              return compare != 0 ? compare : Integer.compare(pre.getKey(), cur.getKey());
            })
            .mapToInt(Map.Entry::getKey)
            .toArray();
  }

  private int[] getNotClearedArr(int len, int[] stages) {
    int[] ret = new int[len + 1];
    for (int stage : stages) ret[stage] += 1;
    return ret;
  }

  private int[] getTotalArr(int len, int[] notClearedArr) {
    int[] ret = new int[len + 1];
    ret[0] = Arrays.stream(notClearedArr).sum();
    for (int i = 1; i <= len; i++) ret[i] = ret[i - 1] - notClearedArr[i - 1];
    return ret;
  }

  private Map<Integer, Double> getFailRateMap(int len, int[] totalArr, int[] notClearedCountArr) {
    Map<Integer, Double> map = new HashMap<>();
    // 스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.
    for (int i = 1; i <= len; i++) map.put(i, totalArr[i] == 0 ? 0 : (double) notClearedCountArr[i] / totalArr[i]);
    return map;
  }
}
