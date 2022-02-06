package baekjoon.기본자료구조;

import java.util.Arrays;
import java.util.Scanner;

// * 배열, 완탐
public class P2798 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int[] a = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
    int[] numList = Arrays.stream(sc.nextLine().split(" ")).mapToInt(x -> Integer.valueOf(x)).toArray();
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < numList.length - 2; i++) {
      for (int j = i + 1; j < numList.length - 1; j++) {
        for (int k = j + 1; k < numList.length; k++) {
          if (numList[i] + numList[j] + numList[k] <= a[1]) {
            max = Math.max(max, numList[i] + numList[j] + numList[k]);
          }
        }
      }
    }
    System.out.println(max);
    sc.close();
  }
}
