package baekjoon.twopointer;

import java.util.Arrays;
import java.util.Scanner;

public class P2003 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int N = scanner.nextInt();
    long M = scanner.nextLong();
    int[] arr = new int[N];
    for (int i = 0; i < N; i++) arr[i] = scanner.nextInt();
    scanner.close();

    int answer = 0;
    int start = 0;
    int end = 0;
    long sum = 0;

    while (start < N) {
      if (sum == M) {
        answer += 1;
        sum -= arr[start++];
      } else if (sum > M) {
        sum -= arr[start++];
      } else {
        if (end != N) sum += arr[end++];
        else break;
      }
    }

    System.out.println(answer);
  }

  private static class Solution {
    private static void iterate() {
      Scanner scanner = new Scanner(System.in);
      int[] condition = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
      int[] arr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
      int answer = 0;
      for (int i = 0; i < condition[0]; i++) {
        int sum = 0;
        for (int j = i; j < condition[0]; j++) {
          sum += arr[j];
          if (sum == condition[1]) {
            answer += 1;
            break;
          }
        }
      }
      scanner.close();
      System.out.println(answer);
    }

    public void solution(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int[] condition = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::valueOf).toArray();
      int[] arr = new int[condition[0] + 1];
      for (int i = 0; i < condition[0]; i++) arr[i] = scanner.nextInt();

      int answer = 0;
      int start = 0;
      int end = 0;
      int sum = 0;
      //    7 13
      //    2 3 5 7 9 11 13
      while (end <= condition[0]) {
        if (sum < condition[1]) {
          sum += arr[end++];
        } else {
          sum -= arr[start++];
        }
        if (sum == condition[1]) {
          answer += 1;
        }
      }

      scanner.close();
      System.out.println(answer);
    }
  }

  private static class Solution2 {

    public void solution(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int N = scanner.nextInt();
      long M = scanner.nextLong();
      int[] arr = new int[N];
      for (int i = 0; i < N; i++) arr[i] = scanner.nextInt();
      scanner.close();

      int answer = 0;
      int start = 0;
      int end = 0;
      long sum = 0;

      while (start < N) {
        if (sum == M) {
          answer += 1;
          sum -= arr[start++];
        } else if (sum > M) {
          sum -= arr[start++];
        } else {
          if (end == N) sum -= arr[start++];
          else sum += arr[end++];
        }
      }

      System.out.println(answer);
    }
  }
}
