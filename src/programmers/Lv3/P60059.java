package programmers.Lv3;

import java.util.*;

public class P60059 {
  //  public static void main(String[] args) {
  //    int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
  //    int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
  //    new P60059().solution(key, lock);
  //  }
  //
  //  private void print() {
  //    for (int[] ints : lock) {
  //      for (int anInt : ints) System.out.print(anInt + ", ");
  //      System.out.println();
  //    }
  //    System.out.println("--");
  //  }
  /**
   * key -> M x M [3 <= M <= 20]
   * - M은 항상 N이하입니다. 즉, key의 이차원 배열은 lock의 이차원 배열보다 항상 같거나 작다.
   * lock -> N x N [3 <= N <= 20]
   * key, lock의 원소는 0(or)1 로 이루어져 있음. [0->홈 부분, 1->돌기 부분]
   */
  // 열쇠로 자물쇠를 열 수 있으면 true, 없으면 false 반환
  /**
   * 자물쇠에는 홈이 파여 있고, 열쇠 또한 홈과 돌기 부분이 있습니다.
   * 열쇠는 회전과 이동이 가능하며, 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조
   * 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있습니다.
   */
  int[][] key;
  int[][] lock;
  int keyLen;
  int lockLen;
  List<Location> lockLocList;
  int num;
  boolean ans;

  public boolean solution(int[][] key, int[][] lock) {
    this.key = key;
    this.lock = lock;
    this.keyLen = key.length;
    this.lockLen = lock.length;
    this.lockLocList = new ArrayList<>();
    this.num = 0;
    this.ans = false;

    if (sync()) return ans;
    for (int i = 0; i < 4; i++) {
      Set<Location> set = rotationKey();
    }
    return ans;
  }

  private boolean sync() {
    int minY = Integer.MAX_VALUE;
    int maxY = Integer.MIN_VALUE;
    int minX = Integer.MAX_VALUE;
    int maxX = Integer.MIN_VALUE;

    for (int i = 0; i < lockLen; i++) {
      for (int j = 0; j < lockLen; j++) {
        int v = lock[i][j];
        if (v == 1) continue;
        num += 1;
        minY = Math.min(minY, i);
        maxY = Math.max(maxY, i);
        minX = Math.min(minX, i);
        maxX = Math.max(maxX, i);
        if (keyLen == lockLen) lockLocList.add(new Location(i, j));
      }
    }
    if (isImpossible(minY, maxY, minX, maxX)) return true;
    replaceLock(minY, maxY, minX, maxX);
    return false;
  }

  private boolean isImpossible(int minY, int maxY, int minX, int maxX) {
    int diffY = maxY - minY;
    int diffX = maxX - minX;
    return Math.max(diffX, diffY) > keyLen;
  }

  private void replaceLock(int minY, int maxY, int minX, int maxX) {
    int start = Math.min(minY, minX);
    int end = Math.max(maxY, maxX);
    lockLen = Math.max(keyLen, end - start + 1);
    int[][] tmp = new int[lockLen][lockLen];
    for (int i = 0; i < lockLen; i++) for (int j = 0; j < lockLen; j++) tmp[i][j] = 1;
    for (int i = start, y = 0; i <= end; i++, y++) {
      for (int j = start, x = 0; j <= end; j++, x++) {
        if (lock[i][j] == 0) lockLocList.add(new Location(y, x));
        tmp[y][x] = lock[i][j];
      }
    }
    lock = tmp;
  }

  private Set<Location> rotationKey() {
    Set<Location> list = new HashSet<>();
    int[][] tmp = new int[keyLen][keyLen];
    for (int i = 0; i < keyLen; i++)
      for (int j = 0; j < keyLen; j++)
        tmp[j][keyLen - i - 1] = key[i][j];
    for (int i = 0; i < keyLen; i++)
      for (int j = 0; j < keyLen; j++)
        if (tmp[i][j] == 1) list.add(new Location(i, j));
    return list;
  }

  private void move(List<int[]> list, int direct) {
    int size = list.size();
    switch (direct) {
      case 0: // up
        break;
      case 1: // down
        break;
      case 2: // left
        break;
      case 3: // right
        break;
    }
  }

  private boolean check(Set<Location> set, Set<Set<Location>> visited) {
    if (isUnlock(set)) {
      ans = true;
      return true;
    }
    for (int i = 0; i < 4; i++) {
      visited.add(set);
      move();
      visited.remove(set);
    }
  }

  private boolean isUnlock(Set<Location> set) {
    int acc = num;
    for (Location location : lockLocList) {
      if (set.contains(location)) acc -= 1;
      if (acc == 0) return true;
    }
    return false;
  }

  private static class Location {
    int y;
    int x;

    public Location(int y, int x) {
      this.y = y;
      this.x = x;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Location location = (Location) o;
      return y == location.y && x == location.x;
    }

    @Override
    public int hashCode() {
      return Objects.hash(y, x);
    }
  }
}
