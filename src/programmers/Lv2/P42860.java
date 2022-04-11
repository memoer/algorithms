package programmers.Lv2;

import java.util.function.IntUnaryOperator;

class Solution {
  private final byte FIRST_ALPHBET = 65;
  private final byte MIDDLE = 13;
  private final byte DISTANCE_MOVE = 0;
  private final byte DISTANCE_CURSOR = 1;
  private int answer;
  private String name;
  private int nameLength;
  private int cursor;
  private boolean[] skippable;
  private int rest;

  private void initSkippable() {
    this.skippable = new boolean[this.nameLength];
    for (int i = 0; i < this.nameLength; i++) {
      if (name.charAt(i) == FIRST_ALPHBET) {
        skippable[i] = true;
        rest -= 1;
      }
    }
  }

  private void addAnswer(int num, boolean subRest) {
    answer += num;
    if (subRest) {
      this.skippable[this.cursor] = true;
      rest -= 1;
    }
  }

  private void moveUpDown(char target) {
    int move = target - FIRST_ALPHBET;
    if (move >= MIDDLE) {
      move = 26 - move;
    }
    addAnswer(move, true);
  }

  private int[] getDistance(IntUnaryOperator operator) {
    int move = 0;
    int i = cursor;
    while (skippable[i]) {
      move += 1;
      i = operator.applyAsInt(i);
    }
    return new int[] { move, i };
  }

  private boolean isDone() {
    return this.rest == 0;
  }

  private void moveLeftRight() {
    if (isDone()) {
      return;
    }
    int[] right = getDistance(i -> i == nameLength - 1 ? 0 : i + 1);
    int[] left = getDistance(i -> i == 0 ? nameLength - 1 : i - 1);
    int[] shortest = right[DISTANCE_MOVE] > left[DISTANCE_MOVE] ? left : right;
    this.cursor = shortest[DISTANCE_CURSOR];
    addAnswer(shortest[DISTANCE_MOVE], false);
  }

  public int solution(String name) {
    this.answer = 0;
    this.name = name;
    this.nameLength = this.name.length();
    this.rest = nameLength;
    this.cursor = 0;
    initSkippable();

    if (this.skippable[this.cursor]) {
      // 처음[0]이 'A'일 경우, 커서 이동
      moveLeftRight();
    }
    while (!isDone()) {
      moveUpDown(this.name.charAt(this.cursor));
      moveLeftRight();
    }
    return this.answer;
  }
}

public class P42860 {
  public static void main(String[] args) {
    String name1 = "BBBBAAAABA";
    // 그리디라면 오른쪽으로 가는데, 이는 틀린 답임
    // 왼 2 +1 -> 오3 +1 -> 오2 +1 => 10
    String name2 = "ABABAAAAABA";
    // 이거 결국엔 경우의 수 다 구해서 제일 최솟값 구해야 함
    System.out.println(new Solution().solution(name2));
  }
}
