package baekjoon.기본그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 유기농 배추 (2606) - DFS 재귀로 풀어보기
public class P1012Dfs {
  private static final int[][] AROUND = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
  private static boolean[][] grid;
  private static int result = 0;
  private static int n; // Y
  private static int m; // X

  private static boolean isAvailable(int x, int y, int[] next) {
    return x + next[0] >= 0 && x + next[0] < m && y + next[1] >= 0 && y + next[1] < n && grid[y + next[1]][x + next[0]];
  }

  private static void dfs(int x, int y) {
    grid[y][x] = false;
    for (int i = 0; i < 4; i++) {
      if (isAvailable(x, y, AROUND[i])) {
        dfs(x + AROUND[i][0], y + AROUND[i][1]);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    final int T = Integer.parseInt(br.readLine());

    for (int i = 0; i < T; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      m = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
      final int K = Integer.parseInt(st.nextToken());
      grid = new boolean[n][m];
      for (int j = 0; j < K; j++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        grid[y][x] = true;
      }
      for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
          if (!grid[y][x]) {
            continue;
          }
          dfs(x, y);
          result += 1;
        }
      }
      bw.write(result + "\n");
      result = 0;
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
