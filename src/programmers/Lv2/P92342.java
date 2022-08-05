package programmers.Lv2;

import java.util.*;

public class P92342 {
  public static void main(String[] args) {
    int[] n = {
            5,
            1,
            9,
            10
    };
    int[][] info = {
            {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3}
    };
    for (int i = 0; i < 4; i++) {
      for (int j : new Solution().solution(n[i], info[i])) System.out.print(j + ", ");
      System.out.println();
    }
  }

  private static class Solution {
    /*
    1. 어피치가 화살 n발을 다 쏜 후에, 라이언이 n발을 쏜다.
    2. 점수 계산
      1. 과녁 점수는 가장 작은 원이 10점, 가장 큰 원의 바깥쪽이 0점
      2. 만약 k[1~10]점을 어피치가 a발을 맞췄고 라이언이 b발을 맞췄을 경우, 더 많은 화살을 k점에 맞힌 선수가 k점을 가져간다.
        * Ex) 5점을 각각 "2번, 3번" 맞춘다면, 3번 맞춘 애가 5점을 가져간다.
        * Ex) 5점을 3발 맞춰도, 5점만 가져간다.
        * 단, "a=b" 일 경우는 어피치가 k점을 가져갑니다.
        * "a=b=0"인 경우, 라이언과 어피치 모두 k점을 가져가지 않습니다.
      3. 모든 과녁 점수에 대하여 각 선수의 최종 점수를 계산합니다.
    3. 최종 점수가 더 높은 선수를 우승자로 결정한다.
      * 단, 최종 점수가 같을 경우 어피치를 우승자로 결정한다.
     */
    /*
    n -> 화살의 개수
    info -> 어피치가 맞힌 과녁 점수의 개수를 10점부터 0점까지 순서대로 담은 정수 배열
    return -> 라이언이 가장 큰 점수 차이로 우승하기 위해 n발의 화살을 어떤 과녁 점수에 맞혀야 하는지를 10점부터 0점까지 순서대로 정수 배열에 담은 값
      - 라이언이 우승할 수 없는 경우 즉, 무조건 지거나 비기는 경우는 [-1] 을 return
     */
    private static final int INFO_LENGTH = 11;
    private static final int MAX_SCORE = 10;
    private Set<Candidate> answer;
    private int[] aInfo;
    private List<Integer> aInfoOnlyZero;
    private List<Integer> aInfoFilteredZero;
    private List<int[]> aCombinationList;
    private int maxDiff;

    public int[] solution(int n, int[] info) {
      aInfo = info;
      answer = new HashSet<>();
      aInfoOnlyZero = new ArrayList<>();
      aInfoFilteredZero = new ArrayList<>();
      aCombinationList = new ArrayList<>();
      // 1. 어피치가 1개 이상 맞힌 점수에 대해서 뽑고, 이 점수 집합들을 사용하여 조합
      fillInDataToNumberOfCases();
      // 2. 만들어진 조합을 사용하여, 라이언이 이길 수 있는 경우의 수들을 구한다.
      fillInDataToAnswer(n);
      // 3-1. 경우의 수가 없다면 [-1] 반환
      if (answer.isEmpty()) return new int[]{-1};
      // 3-2. 경우의 수가 있다면, 가장 높은 점수차의 경우를 선택한다.
      answer.removeIf(next -> next.diff != maxDiff);
      // 4-1. 가장 높은 점수차의 경우가 1개만 있다면, 해당 배열을 반환
      if (answer.size() == 1) return answer.iterator().next().info;
      // 5-2. 가장 높은 점수차의 경우가 여러개 있다면, 해당 경우들 중 가장 낮은 점수를 더 많이 맞힌 경우를 반환
      return getResultThatMeetsTheCondition();
    }


    private void fillInDataToNumberOfCases() {
      for (int i = 0; i < INFO_LENGTH; i++) {
        if (aInfo[i] == 0) aInfoOnlyZero.add(i);
        else aInfoFilteredZero.add(i);
      }

      int size = aInfoFilteredZero.size();
      for (int length = size; length > 0; length--) {
        for (int i = 0; i < size; i++) {
          int[] numberOfCase = new int[length];
          numberOfCase[0] = aInfoFilteredZero.get(i);
          combination(1, length, numberOfCase, i + 1, size, aInfoFilteredZero);
        }
      }
    }

    private void combination(int idx, int length, int[] numberOfCase, int start, int end,
                             List<Integer> infoFilteredZero) {
      if (idx == length) {
        aCombinationList.add(numberOfCase.clone());
        return;
      }
      for (int i = start; i < end; i++) {
        numberOfCase[idx] = infoFilteredZero.get(i);
        combination(idx + 1, length, numberOfCase, i + 1, end, infoFilteredZero);
      }
    }

    private void fillInDataToAnswer(int n) {
      int rest;
      int[] bInfo;
      for (int[] aCombination : aCombinationList) {
        rest = n;
        bInfo = new int[INFO_LENGTH];
        for (int idx : aCombination) {
          int count = this.aInfo[idx] + 1;
          if (rest < count) break;
          bInfo[idx] = count;
          rest -= count;
        }
        if (rest > 0) checkAInfoZero(rest, bInfo);
        addBInfoOnlyWin(bInfo);
      }
      bInfo = new int[INFO_LENGTH];
      rest = checkAInfoZero(n, bInfo);
      for (int idx : aInfoFilteredZero) {
        bInfo[idx] = rest;
        addBInfoOnlyWin(bInfo.clone());
        bInfo[idx] = 0;
      }
    }

    private int checkAInfoZero(int rest, int[] bInfo) {
      for (int idx : aInfoOnlyZero) {
        if (rest == 0) break;
        bInfo[idx] = 1;
        rest -= 1;
      }
      return rest;
    }

    private void addBInfoOnlyWin(int[] bInfo) {
      int diff;
      if ((diff = getDiff(aInfo, bInfo)) > 0) {
        maxDiff = Math.max(maxDiff, diff);
        answer.add(new Candidate(bInfo, diff));
      }
    }

    private int getDiff(int[] aInfo, int[] bInfo) {
      int aScore = 0;
      int bScore = 0;
      for (int i = 0; i < INFO_LENGTH; i++) {
        if (aInfo[i] == 0 && bInfo[i] == 0) continue;
        if (aInfo[i] < bInfo[i]) bScore += (MAX_SCORE - i);
        else aScore += (MAX_SCORE - i);
      }
      return bScore - aScore;
    }

    private int[] getResultThatMeetsTheCondition() {
      Iterator<Candidate> iterator = answer.iterator();
      int[] info = iterator.next().info;
      int start = INFO_LENGTH - 1;
      while (iterator.hasNext()) {
        int[] nextInfo = iterator.next().info;
        for (int i = start; i >= 0; i--) {
          if (info[i] != 0 || nextInfo[i] != 0) {
            if (info[i] < nextInfo[i]) info = nextInfo;
            break;
          }
        }
      }

      return info;
    }

    private static class Candidate {
      private final int[] info;
      private final int diff;

      public Candidate(int[] info, int diff) {
        this.info = info;
        this.diff = diff;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Arrays.equals(info, candidate.info);
      }

      @Override
      public int hashCode() {
        return Arrays.hashCode(info);
      }
    }
  }
}
