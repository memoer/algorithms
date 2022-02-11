package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12865 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int K = Integer.parseInt(st.nextToken());
    int[][] result = new int[N + 1][K + 1];

    for (int i = 1; i <= N; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());
      for (int j = 1; j <= K; j++) {
        if (j >= weight) {
          result[i][j] = Math.max(result[i - 1][j], value + result[i - 1][j - weight]);
        } else {
          result[i][j] = result[i - 1][j];
        }
      }
    }

    System.out.println(result[N][K]);
    br.close();
  }

}
