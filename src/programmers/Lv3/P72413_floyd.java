package programmers.Lv3;

import java.util.Arrays;

public class P72413_floyd {
  public static void main(String[] args) {
    int n = 6, s = 4, a = 6, b = 2;
    int[][] fares = {
            {4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}
    };
    new P72413_floyd().solution(n, s, a, b, fares);
  }

  public int solution(int n, int s, int a, int b, int[][] fares) {
    int[][] map = new int[n + 1][n + 1];
    for (int i = 0; i <= n; i++) {
      Arrays.fill(map[i], 100_000_000);
      map[i][i] = 0;
    }
    //    print(n, map);

    for (int[] fare : fares) map[fare[0]][fare[1]] = map[fare[1]][fare[0]] = fare[2];
    //    print(n, map);

    for (int k = 1; k <= n; k++) {
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          if (map[i][j] > map[i][k] + map[k][j]) map[i][j] = map[i][k] + map[k][j];
        }
      }
    }
    //    print(n, map);

    int answer = map[s][a] + map[s][b];
    for (int i = 1; i <= n; i++) answer = Math.min(answer, map[s][i] + map[i][a] + map[i][b]);

    return answer;
  }

  private void print(int n, int[][] arr) {
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) System.out.print(arr[i][j] + ", ");
      System.out.println();
    }
    System.out.println("----------------");
  }
}
