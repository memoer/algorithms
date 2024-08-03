package programmers.Lv3;

/**
 * 기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
 * 보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
 */

import java.util.*;

/**
 * n -> 벽면의 크기 [5 <= n <= 100]
 * build_frame -> 기둥과 보를 설치,삭제하는 작업이 순서대로 담긴 2차원 배열 [1 <= build_frame.length <= 1,000]
 * [x, y, a, b]
 * - x,y -> 기둥,보를 설치 또는 삭제할 교차점의 좌표 [가로, 세로 좌표]
 * - a -> 설치,삭제할 구조물의 종류. [0->기둥, 1->보]
 * - b -> 0->삭제, 1->설치
 * 구조물은 교차점 좌표를 기준으로 오른쪽, 기둥은 위쪽 방향으로 설치 또는 삭제
 */
// 모든 명령어를 수행한 후, 구조물의 상태를 반환
// [x,y,a] -> 가로,세로,기둥(or)보 -> 기둥:0, 보:1
// x좌표 기준으로 오름차순, y좌표 기준으로 오름차순, 모두 같다면 a 기준으로 오름차순
public class P60061 {
  private final int G = 0;
  private final int B = 1;
  int xLen;
  int yLen;
  Map<Coordinate, boolean[]> map;

  public int[][] solution(int n, int[][] build_frame) {
    xLen = build_frame[0].length;
    yLen = build_frame.length;
    map = new HashMap<>();
    for (int[] row : build_frame) {
      if (row[3] == 1) if (!addIsAvailable(row)) continue;
      else if (!deleteIsAvailable(row)) continue;
      write(row);
    }
    return getAns();
  }

  private boolean addIsAvailable(int[] row) {
    int x = row[0];
    int y = row[1];
    if (row[2] == G) { // 기둥
      Coordinate k = new Coordinate();
      if (y == 0) return true;
      if (y - 1 >= 0) {
        k.x = x;
        k.y = y - 1;
        boolean[] v = map.get(k);
        if (v[G]) return true;
      }
      if (x - 1 >= 0) {
        k.x = x - 1;
        k.y = y;
        boolean[] v = map.get(k);
        if (v[B]) return true;
      }
    } else { // 보
      Coordinate k = new Coordinate();
      if (y - 1 >= 0) {
        k.x = x;
        k.y = y - 1;
        boolean[] v = map.get(k);
        if (v[G]) return true;
      }
      if (y - 1 >= 0 && x + 1 < xLen) {
        k.x = x + 1;
        k.y = y - 1;
        boolean[] v = map.get(k);
        if (v[G]) return true;
      }
      if (x - 1 >= 0) {
        k.x = x - 1;
        k.y = y;
        boolean[] v1 = map.get(k);
        k.x = x + 1;
        boolean[] v2 = map.get(k);
        if (v1[B] && v2[B]) return true;
      }
    }
    return false;
  }

  private boolean deleteIsAvailable(int[] row) {
    int x = row[0];
    int y = row[1];
    if (row[2] == G) { // 기둥
      Coordinate k = new Coordinate();
      if (y + 1 < yLen) {
        k.x = x;
        k.y = y + 1;
        boolean[] v = map.get(k);
        if (v[G]) return false;
        if (x - 1 >= 0) {
          k.x = x - 1;
          boolean[] v1 = map.get(k);
          if (v1[B]) return false;
        }
      }
    } else {

    }
    return true;
  }

  private void write(int[] row) {
    Coordinate k = new Coordinate(row[0], row[1]);
    int type = row[2];
    if (row[3] == 1) {
      boolean[] v = new boolean[2];
      v[type] = true;
      map.put(k, v);
    } else if (map.containsKey(k)) {
      boolean[] v = map.get(k);
      v[type] = false;
    }
  }

  private int[][] getAns() {
    List<int[]> ans = new ArrayList<>();
    for (Map.Entry<Coordinate, boolean[]> e : map.entrySet()) {
      boolean[] v = e.getValue();
      if (!v[0] && !v[1]) continue;
      Coordinate k = e.getKey();
      if (v[0]) ans.add(new int[]{k.x, k.y, 0});
      if (v[1]) ans.add(new int[]{k.x, k.y, 1});
    }
    return (int[][]) ans.stream().sorted((p, c) -> {
      int a = Integer.compare(p[0], c[0]);
      if (a != 0) return a;
      int b = Integer.compare(p[1], c[1]);
      if (b != 0) return b;
      return Integer.compare(p[2], c[2]);
    }).toArray();
  }

  private static class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public Coordinate() {
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Coordinate that = (Coordinate) o;
      return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
}
