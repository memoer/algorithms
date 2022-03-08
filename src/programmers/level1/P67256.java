package programmers.level1;

public class P67256 {
  static class Solution {
    private final int[] LEFT = { 1, 4, 7, -1 };
    private final int[] RIGHT = { 3, 6, 9, -1 };
    StringBuilder sb = new StringBuilder();
    private int left = -1;
    private int right = -1;

    private int getY(int number) {
      switch (number) {
        case 1:
        case 2:
        case 3:
          return 1;
        case 4:
        case 5:
        case 6:
          return 2;
        case 7:
        case 8:
        case 9:
          return 3;
        case 0:
        default:
          return 4;
      }
    }

    private void useHand(char whichHand, int number) {
      sb.append(whichHand);
      if (whichHand == 'L') {
        left = number;
      } else {
        right = number;
      }
    }

    private boolean isLeft(int number) {
      for (int n : LEFT) {
        if (number == n) {
          return true;
        }
      }
      return false;
    }

    private boolean isRight(int number) {
      for (int n : RIGHT) {
        if (number == n) {
          return true;
        }
      }
      return false;
    }

    private int[] getDiff(int number) {
      int numberY = getY(number);
      int leftY = getY(left);
      int rightY = getY(right);
      return new int[] { Math.abs(numberY - leftY) + (isLeft(left) ? 1 : 0),
          Math.abs(numberY - rightY) + (isRight(right) ? 1 : 0) };
    }

    public String solution(int[] numbers, String hand) {
      for (int number : numbers) {
        if (isLeft(number)) {
          useHand('L', number);
          continue;
        } else if (isRight(number)) {
          useHand('R', number);
          continue;
        }
        int[] diff = getDiff(number);
        if (diff[0] < diff[1]) {
          useHand('L', number);
        } else if (diff[0] > diff[1]) {
          useHand('R', number);
        } else if (hand.equals("left")) {
          useHand('L', number);
        } else {
          useHand('R', number);
        }
      }
      return sb.toString();
    }
  }

  public static void main(String[] args) {
    int[] numbers = { 2, 5, 8, 0 };
    String hand = "right";
    System.out.println(new Solution().solution(numbers, hand));
  }
}
