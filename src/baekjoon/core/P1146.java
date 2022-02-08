package baekjoon.core;

import java.util.Scanner;

// DP
public class P1146 {
  static int answer = 1;

  private static boolean isAvailable(int a, int b, int c) {
    return b > a && b > c || b < a && b < c;
  }

  private static void dfs(int a, int b, int[] students, int rest) {
    if (rest == 0) {
      answer++;
      return;
    }
    for (int i = 0; i < students.length; i++) {
      if (students[i] == -1) {
        continue;
      }
      int c = students[i];
      if (isAvailable(a, b, c)) {
        students[i] = -1;
        dfs(b, c, students, rest - 1);
        students[i] = c;
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = Integer.valueOf(sc.nextLine());
    int[] students = new int[N];
    int[] cached = new int[2];
    if (N > 1) {
      for (int i = 0; i < N; i++) {
        students[i] = i + 1;
      }
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (i == j) {
            continue;
          }
          if (N > 2) {
            int a = students[i];
            int b = students[j];
            students[i] = -1;
            students[j] = -1;
            dfs(a, b, students, N - 2);
            students[i] = a;
            students[j] = b;
          } else {
            answer++;
          }
        }
      }
    }
    sc.close();
    System.out.println(answer);
  }
}
