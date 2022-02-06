package baekjoon.재귀;

import java.util.Scanner;

public class P1074 {
  public static int getBoxLength(int n) {
    return (int) Math.pow(2, n) / 2;
  }

  public static int getBoxSize(int boxLength) {
    return boxLength * boxLength;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] input = sc.nextLine().split(" ");
    int result = 0;
    int n = Integer.parseInt(input[0]);
    int r = Integer.parseInt(input[1]);
    int c = Integer.parseInt(input[2]);
    for (; n > 0; n--) {
      int boxLength = getBoxLength(n);
      int numberOfBoxes = 0;
      if (r >= boxLength && c >= boxLength) {
        numberOfBoxes = 3;
        r -= boxLength;
        c -= boxLength;
      } else if (r >= boxLength) {
        numberOfBoxes = 2;
        r -= boxLength;
      } else if (c >= boxLength) {
        numberOfBoxes = 1;
        c -= boxLength;
      }
      result += getBoxSize(boxLength) * numberOfBoxes;
    }
    System.out.println(result);
    sc.close();
  }
}
