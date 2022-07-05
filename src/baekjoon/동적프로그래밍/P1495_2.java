package baekjoon.동적프로그래밍;

import java.util.*;

public class P1495_2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringTokenizer st = new StringTokenizer(sc.nextLine());
    // 곡의 개수
    final int N = Integer.parseInt(st.nextToken());
    // 시작 볼륨
    final int S = Integer.parseInt(st.nextToken());
    // 볼륨의 최대값
    final int M = Integer.parseInt(st.nextToken());
    int[] volumeArr = Arrays.stream(sc.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    sc.close();

    System.out.println(getAnswer(N, S, M, volumeArr));
  }
  // 시간복잡도 -> O(NM) -> 50,000
  private static int getAnswer(int N, int S, int M, int[] volumeArr) {
    boolean[][] candidate = new boolean[N + 1][M + 1];
    candidate[0][S] = true;
    // 1. N <= 50
    for (int i = 1; i <= N; i++) {
      boolean cantPlay = true;
      int volume = volumeArr[i - 1];
      // 1. M <= 1,000
      // 2. 최악) 모든 볼륨의 크기로 연주할 수 있음 -> candidate[i-1][j] 가 계속 true
      for (int j = 0; j <= M; j++) {
        if (!candidate[i - 1][j]) continue;
        cantPlay = false;
        int min = j - volume;
        int max = j + volume;
        if (min >= 0) candidate[i][min] = true;
        if (max <= M) candidate[i][max] = true;
      }
      if(cantPlay) return -1;
    }
    // 1. 상수 값이므로, 무시해도 된다.
    for (int i = M; i >= 0; i--) if (candidate[N][i]) return i;
    return -1;
  }
}
