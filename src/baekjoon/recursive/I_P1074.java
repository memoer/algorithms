package baekjoon.recursive;

import java.util.Scanner;

// 재귀 풀 때, 모든 경우의 수를 재귀로 돌리려 하지 말아라! 이것이 함정이다.
// 최대한 수를 줄일 것!!!
public class I_P1074 {
  public static int answer = -1;

  public static int getAnswer(int N, int r, int c) {
    if (N == 1) {
      if (r == 0) {
        if (c == 0) {
          return answer += 1;
        }
        return answer += 2;
      }
      if (c == 0) {
        return answer += 3;
      }
      return answer += 4;
    }
    int size = (int) Math.pow(2, N);
    int chunk = size / 2;
    if (r >= chunk && c >= chunk) {
      answer += chunk * chunk * 3;
      return getAnswer(N - 1, r - chunk, c - chunk);
    } else if (r >= chunk) {
      answer += chunk * chunk * 2;
      return getAnswer(N - 1, r - chunk, c);
    } else if (c >= chunk) {
      answer += chunk * chunk;
      return getAnswer(N - 1, r, c - chunk);
    } else {
      return getAnswer(N - 1, r, c);
    }
  }

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    String[] input = sc.nextLine().split(" ");
    int N = Integer.valueOf(input[0]);
    int r = Integer.valueOf(input[1]);
    int c = Integer.valueOf(input[2]);
    System.out.println(getAnswer(N, r, c));
    sc.close();
  }
}
