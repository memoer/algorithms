package baekjoon.기본탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1236 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());
    char[][] grid = new char[N][M];
    int rowEmpty = 0;
    int colEmpty = 0;

    for (int i = 0; i < N; i++) {
      String s = new StringTokenizer(br.readLine()).nextToken();
      for (int j = 0; j < M; j++) {
        grid[i][j] = s.charAt(j);
      }
      if (!s.contains("X")) {
        rowEmpty += 1;
      }
    }

    for (int col = 0; col < M; col++) {
      StringBuilder sb = new StringBuilder();
      for (int row = 0; row < N; row++) {
        sb.append(grid[row][col]);
      }
      if (!sb.toString().contains("X")) {
        colEmpty += 1;
      }
    }
    System.out.println(Math.max(rowEmpty, colEmpty));

    br.close();
  }
}
