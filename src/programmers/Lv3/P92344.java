package programmers.Lv3;

/*
 * 1. 2차원 배열에 각각 정수[내구도]가 존재
 * 2. 건물은 적의 공격을 받으면 내구도가 감소하고 내구도가 0이하가 되면 파괴된다.
 * 3. 반대로, 아군은 회복 스킬을 사용하여 건물들의 내구도를 높이려고 합니다.
 * [적의 공격과 아군의 회복 스킬은 항상 직사각형]
 */
/*
 * board -> 건물의 내구도를 나타내는 2차원 배열
 * - 1 <= board.length <= 1,000, 1 <= board[i].length <= 1,000, 1 <= board[i][j] <= 1,000
 * skill -> 적의 공격 (or) 아군의 회복 스킬을 나타내는 2차원 배열
 * - 1 <= skill.length <= 250,000, skill[i].length = 6
 * - [type, r1, c1, r2, c2, degree]
 * --- type=1 -> 공격, type=2 -> 회복
 * --- (r1, c1) ~ (r2, c2) 모양의 범위 안에 있는 건물의 내구도를 degree 만큼 낮추거나 높인다.
 */
/*
 * 적의 공격 (or) 아군의 회복 스킬이 모두 끝난 뒤, 파괴되지 않은 건물의 개수 반환
 * 최종적으로 건물의 내구도가 1이상이면, 파괴되지 않은 건물
 */
public class P92344 {
  public static void main(String[] args) {
    int[][] board = {
            {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5},
    };
    int[][] skill = {
            {1, 0, 0, 3, 4, 4},
            {1, 2, 0, 2, 3, 2},
            {2, 1, 0, 3, 1, 2},
            {1, 0, 1, 3, 3, 1}
    };
    System.out.println(new P92344().solution(board, skill));
  }

  // 시간 복잡도 -> O(K+MN)
  public int solution(int[][] board, int[][] skill) {
    // board.length -> N, board[i].length -> M skill.length -> K
    int tmpYLen = board.length + 1;
    int tmpXLen = board[0].length + 1;

    int[][] tmp = getSum(tmpYLen, tmpXLen, skill);
    calculate(tmpYLen, tmpXLen, tmp);
    return getAns(board, tmp);
  }

  // O(K) -> 250,000
  private int[][] getSum(int yLen, int xLen, int[][] skill) {
    int[][] tmp = new int[yLen][xLen];
    for (int[] row : skill) {
      int yFirst = row[1];
      int xFirst = row[2];
      int yLast = row[3] + 1;
      int xLast = row[4] + 1;
      int degree = row[5] * (row[0] == 1 ? -1 : 1);
      tmp[yFirst][xFirst] += degree;
      tmp[yFirst][xLast] -= degree;
      tmp[yLast][xFirst] -= degree;
      tmp[yLast][xLast] += degree;
    }
    return tmp;
  }

  // O(MN) -> 1_000 * 1_000 -> 1,000,000
  private void calculate(int yLen, int xLen, int[][] arr) {
    for (int i = 0; i < yLen; i++) for (int j = 1; j < xLen; j++) arr[i][j] += arr[i][j - 1];
    for (int i = 0; i < xLen; i++) for (int j = 1; j < yLen; j++) arr[j][i] += arr[j - 1][i];
  }

  // O(MN) -> 1_000 * 1_000 -> 1,000,000
  private int getAns(int[][] board, int[][] arr) {
    int ans = 0;
    int yLen = board.length;
    int xLen = board[0].length;
    for (int i = 0; i < yLen; i++) for (int j = 0; j < xLen; j++) if (board[i][j] + arr[i][j] > 0) ans += 1;
    return ans;
  }
}

