package baekjoon.탐색기본;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
1. 집 N개가 "수직선" 위에 있음
2. "집"에 공유기 1개를 설치한다.
  - 한 집에는 공유기를 하나만 설치할 수 있다.
3. 가장 인접한 "두 공유기 사이의 거리"를 가능한 크게 하여 설치한다.
4. C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램 작성
 */
/*
1. 집의 개수 N -> 2 <= N <= 200,000
2. 공유기의 개수 C -> 2 <= C <= N
3. 집의 좌표 x -> 0 <= x <= 1,000,000,000
 */
/*
1. 총 시간 복잡도 -> O(NlogX) -> O(200,000 x log(1,000,000,000))
2. 6,000,000 -> 시간 제한 2초 만족
 */
/*
1. 최소 거리가 1이라면, 1이상인 거리들은 모두 설치 가능
2. 최소 거리가 2이라면, 2이상인 거리들은 모두 설치 가능
3. 이렇게 점점 키워가면서 최댓값을 구한다.
4. 만약 값이 너무 크면, 공유기를 다 사용하지 못하고 남는다.
5. 해당 문제의 핵심은, "공유기는 무조건 다 써야한다" 임
 */
public class P2110_2 {
  private static int[] line;
  private static int c;
  private static int n;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    line = new int[n];
    int left = 1;
    int right = Integer.MIN_VALUE;
    int mid = 0;
    int answer = 0;
    for (int i = 0; i < n; i++) {
      int x = Integer.parseInt(br.readLine());
      line[i] = x;
      right = Integer.max(right, x);
    }
    br.close();

    Arrays.sort(line);
    // 이진 탐색 -> O(logX)
    while (left <= right) {
      // 무조건 이 이상으로 공유기를 설치해야 한다.
      mid = (left + right) / 2;
      // isAvailable -> O(N)
      if (isAvailable(mid)) {
        left = mid + 1;
        answer = mid;
      } else {
        right = mid - 1;
      }
    }
    System.out.println(answer);
  }

  private static boolean isAvailable(int mid) {
    int rest = c - 1;
    int preX = line[0];
    // 최악의 경우, 집의 개수만큼 반복문이 돈다 -> O(N)
    for (int i = 1; i < n; i++) {
      int curX = line[i];
      int gapX = curX - preX;
      // 공유기를 설치한 집과의 거리 차이가 무조건 mid 보다 커야 설치할 수 있다.
      if (gapX >= mid) {
        rest -= 1;
        preX = curX;
      }
      if (rest <= 0) return true;
    }
    return false;
  }
}
