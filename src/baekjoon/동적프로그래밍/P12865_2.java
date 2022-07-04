package baekjoon.동적프로그래밍;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P12865_2 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken()) + 1;
    final int K = Integer.parseInt(st.nextToken()) + 1;
    int[][] dp = new int[N][K];
    // N번만큼 반복 [최대 100]
    for (int i = 1; i < N; i++) {
      // 1. dp에서 i가 물건의 개수를 뜻한다.
      // 2. i가 2일 경우, 물건 2개를 가지고 가방을 채워넣어본다.
      st = new StringTokenizer(br.readLine());
      int w = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      // K번만큼 반복 [최대 100,000]
      for (int j = 1; j < K; j++) {
        // 1. dp에서 j는 무게를 뜻한다.
        // 2. 최대 7까지 담을 수 있으므로, j는 7까지만 반복되어야 한다.
        int top = dp[i - 1][j];
        int candidate = j - w < 0 ? 0 : dp[i - 1][j - w] + v;
        dp[i][j] = Math.max(top, candidate);
      }
    }
    br.close();
    // 1. 총 시간 복잡도 -> O(NK)
    // 2. 100 * 100,000 -> 10,000,000 -> 1초 충분
    System.out.println(dp[N - 1][K - 1]);
  }
}
