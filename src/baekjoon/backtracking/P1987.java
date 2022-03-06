package baekjoon.backtracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class P1987 {
  private static int result = 0;
  private static final int CONVERT = 65;
  private static boolean[] visited = new boolean[26];
  private static char[][] arr;
  private static int r;
  private static int c;

  private static int getIdx(int row, int col) {
    return arr[row][col] - CONVERT;
  }

  private static void dfs(int row, int col, int path) {
    result = Math.max(result, path);
    if (col > 0 && !visited[getIdx(row, col - 1)]) {
      visited[getIdx(row, col - 1)] = true;
      dfs(row, col - 1, path + 1);
      visited[getIdx(row, col - 1)] = false;
    }
    if (col < c - 1 && !visited[getIdx(row, col + 1)]) {
      visited[getIdx(row, col + 1)] = true;
      dfs(row, col + 1, path + 1);
      visited[getIdx(row, col + 1)] = false;
    }
    if (row < r - 1 && !visited[getIdx(row + 1, col)]) {
      visited[getIdx(row + 1, col)] = true;
      dfs(row + 1, col, path + 1);
      visited[getIdx(row + 1, col)] = false;
    }
    if (row > 0 && !visited[getIdx(row - 1, col)]) {
      visited[getIdx(row - 1, col)] = true;
      dfs(row - 1, col, path + 1);
      visited[getIdx(row - 1, col)] = false;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    arr = new char[r][c];
    for (int i = 0; i < r; i++) {
      String s = br.readLine();
      arr[i] = s.toCharArray();
    }
    br.close();
    visited[arr[0][0] - CONVERT] = true;
    dfs(0, 0, 1);

    bw.write(result + "\n");
    bw.flush();
    bw.close();
  }
}
