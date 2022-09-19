package temp.kakao;

public class P17679 {
  int m;
  int n;
  char[][] board;
  int[][] square = new int[][]{{0, 0}, {1, 0}, {0, 1}, {1, 1}};

  public static void main(String[] args) {
    int m = 4, n = 5;
    String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
    new P17679().solution(m, n, board);
  }

  // 2x2 형태로 4개가 붙어있을 경우 사라지면서 점수를 얻는 게임
  // 입력으로 주어진 판 정보를 가지고 몇 개의 블록이 지워질지 출력하라.
  public int solution(int m, int n, String[] board) {
    this.m = m;
    this.n = n;
    this.board = new char[m][];
    for (int i = 0; i < m; i++) this.board[i] = board[i].toCharArray();

    int ans = 0;
    while (true) {
      boolean[][] check = getCheck();
      int count = getCount(check);
      if (count == 0) break;
      ans += count;
      disappear(check);
      down();
    }

    return ans;
  }

  private boolean[][] getCheck() {
    boolean[][] check = new boolean[m][n];
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        if (this.board[i][j] != '\0' && isAvailable(i, j)) fill(i, j, check);
    return check;
  }

  private void fill(int i, int j, boolean[][] check) {
    for (int[] loc : square) check[i + loc[0]][j + loc[1]] = true;
  }

  private int getCount(boolean[][] check) {
    int count = 0;
    for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) if (check[i][j]) count += 1;
    return count;
  }

  private boolean isAvailable(int i, int j) {
    boolean isAvailable = true;
    char target = '\0';
    for (int[] loc : square) {
      int y = i + loc[0];
      int x = j + loc[1];
      if (y < 0 || x < 0 || y >= m || x >= n) return false;
      if (target == '\0') target = this.board[y][x];
      else if (target != this.board[y][x]) return false;
    }
    return isAvailable;
  }

  private void disappear(boolean[][] check) {
    for (int i = 0; i < m; i++)
      for (int j = 0; j < n; j++)
        if (check[i][j]) this.board[i][j] = '\0';
  }

  private void down() {
    for (int i = m - 1; i > 0; i--) {
      for (int j = 0; j < n; j++) {
        if (this.board[i][j] != '\0') continue;
        int idx = i - 1;
        while (idx > 0 && this.board[idx][j] == '\0') idx -= 1;
        this.board[i][j] = this.board[idx][j];
        this.board[idx][j] = '\0';
      }
    }
  }
}
