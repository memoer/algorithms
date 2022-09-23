package programmers.Lv3;

// https://code-lab1.tistory.com/152
public class P60059 {
  /**
   * key -> M x M [3 <= M <= 20]
   * - M은 항상 N이하입니다. 즉, key의 이차원 배열은 lock의 이차원 배열보다 항상 같거나 작다.
   * lock -> N x N [3 <= N <= 20]
   * key, lock의 원소는 0(or)1 로 이루어져 있음. [0->홈 부분, 1->돌기 부분]
   */
  // 열쇠로 자물쇠를 열 수 있으면 true, 없으면 false 반환
  private int[][] key;
  private int[][] lock;
  private int[][] extendedLock;
  private int keyLen;
  private int lockLen;

  public static void main(String[] args) {
    int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
    int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
    new P60059().solution(key, lock);
  }

  public boolean solution(int[][] key, int[][] lock) {
    this.key = key;
    this.lock = lock;
    this.keyLen = key.length;
    this.lockLen = lock.length;
    this.extendedLock = getExtendedLock();

    for (int i = 0; i < 4; i++) {
      if (check()) return true;
      if (i != 3) rotate();
    }
    return false;
  }

  private int[][] getExtendedLock() {
    /**
     * 확장 길이
     * 1. lock 길이에서 위 아래로 keyLen을 2번씩 더한다.
     * 2. 확장된 배열에서 (0,0)부터 검사를 해야하므로, key와 겹쳐야 한다. -> 그러므로 위 아래 1개씩 빼준다. [-2]
     */
    int extendedLockLen = lockLen + keyLen * 2 - 2;
    int[][] extendedLock = new int[extendedLockLen][extendedLockLen]; // 확장시킨 배열

    // (key의 길이-1)만큼 확장시켜주었으니, 확장된 배열에서 (key 길이-1)부터 시작한다.
    int start = keyLen - 1;
    // 시작지점부터 lock의 길이만큼 반복문을 돈다.
    int end = start + lockLen;
    for (int i = start; i < end; i++) {
      for (int j = start; j < end; j++) {
        int y = i - (keyLen - 1);
        int x = j - (keyLen - 1);
        // 확장된 배열에 lock을 표시한다.
        extendedLock[i][j] = lock[y][x];
      }
    }
    return extendedLock;
  }

  private void rotate() {
    int len = key.length;
    int[][] tmp = new int[len][len];
    for (int i = 0; i < len; i++) for (int j = 0; j < len; j++) tmp[i][j] = key[len - j - 1][i];
    this.key = tmp;
  }

  private boolean check() {
    // 확장된 배열 끝까지 모두 돌면 안된다. 끝에서 key 검사를 할 수 없다. [배열을 넘어섬]
    int len = extendedLock.length - keyLen + 1;
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < len; j++) {
        combine(i, j);
        if (isUnlock()) return true;
        restore(i, j);
      }
    }
    return false;
  }

  private void combine(int i, int j) {
    for (int k = 0; k < keyLen; k++) for (int l = 0; l < keyLen; l++) extendedLock[k + i][l + j] += key[k][l];
  }

  private boolean isUnlock() {
    int start = keyLen - 1;
    int end = start + lockLen;
    for (int i = start; i < end; i++) for (int j = start; j < end; j++) if (extendedLock[i][j] != 1) return false;
    return true;
  }

  private void restore(int i, int j) {
    for (int k = 0; k < keyLen; k++) for (int l = 0; l < keyLen; l++) extendedLock[i + k][j + l] -= key[k][l];
  }
}
