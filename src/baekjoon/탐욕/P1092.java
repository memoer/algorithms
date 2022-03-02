package baekjoon.탐욕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1092 {
  public static void main(String[] args) throws IOException {
    int result = 0;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int N = Integer.parseInt(br.readLine());
    int[] trainArr = new int[N];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      trainArr[i] = Integer.parseInt(st.nextToken());
    }
    final int M = Integer.parseInt(br.readLine());
    int[] boxArr = new int[M];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < M; i++) {
      boxArr[i] = Integer.parseInt(st.nextToken());
    }
    br.close();

    Arrays.sort(trainArr);
    Arrays.sort(boxArr);
    if (trainArr[N - 1] < boxArr[M - 1]) {
      System.out.println(-1);
      System.exit(0);
    }
    int boxSize = M;
    while (boxSize > 0) {
      int i = N - 1;
      int j = M - 1;
      while (i >= 0 && j >= 0) {
        if (boxArr[j] == -1) {
          j -= 1;
        } else if (trainArr[i] >= boxArr[j]) {
          i -= 1;
          boxSize -= 1;
          boxArr[j--] = -1;
        } else {
          j -= 1;
        }
      }
      result += 1;
    }
    System.out.println(result);
  }
}
