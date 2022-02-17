package baekjoon.그래프;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 다음에 dfs 재귀로 한 번 풀어볼 것!
public class P1012 {
  private static int[][] getAdjLocation(int[] location, int N, int M) {
    List<int[]> list = new ArrayList<>();
    final int[][] temp = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    for (int i = 0; i < 4; i++) {
      int y = location[0] + temp[i][0];
      int x = location[1] + temp[i][1];
      if (y >= N || y < 0 || x >= M || x < 0) {
        continue;
      }
      list.add(new int[] { y, x });
    }
    return list.toArray(new int[0][0]);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int T = Integer.parseInt(st.nextToken());
    Queue<int[]> needVisit = new LinkedList<>();

    for (int i = 0; i < T; i++) {
      st = new StringTokenizer(br.readLine());
      int result = 0;
      final int M = Integer.parseInt(st.nextToken());
      final int N = Integer.parseInt(st.nextToken());
      final int K = Integer.parseInt(st.nextToken());
      boolean[][] grid = new boolean[N][M];

      for (int j = 0; j < K; j++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        grid[y][x] = true;
      }

      for (int n = 0; n < N; n++) {
        for (int m = 0; m < M; m++) {
          if (!grid[n][m]) {
            continue;
          }
          grid[n][m] = false;
          needVisit.offer(new int[] { n, m });
          while (!needVisit.isEmpty()) {
            int[] location = needVisit.poll();
            for (int[] adj : getAdjLocation(location, N, M)) {
              int y = adj[0];
              int x = adj[1];
              if (grid[y][x]) {
                grid[y][x] = false;
                needVisit.offer(new int[] { y, x });
              }
            }
          }
          result += 1;
        }
      }
      bw.write(result + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
