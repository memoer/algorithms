package programmers.Lv2;

public class P68645 {
  public static void main(String[] args) {
    int n = 1_000;
    for (int num : new Solution().solution(n)) System.out.print(num + ", ");
    System.out.println();
  }

  private static class Solution {
    private enum Direct {
      BOTTOM, RIGHT, TOP,
    }

    private int length;
    private int n;
    private int[][] triangle;
    private Direct curDirect = Direct.BOTTOM;

    // 1. 1 <= n <= 1,000
    // 2. 시간 복잡도 -> O(N^2)
    // 3. 1,000 * 1,000 -> 1,000,000
    public int[] solution(int n) {
      this.n = n;
      this.length = n * (n + 1) / 2;
      triangle = new int[n][];
      for (int i = 0; i < n; i++) triangle[i] = new int[i + 1];

      fillTriangle();
      return getAnswer();
    }


    private void fillTriangle() {
      int cur = 1;
      int count = 0;
      int i = 0;
      int j = 0;
      triangle[i][j] = cur++;
      count += 1;
      // 시간복잡도 -> this.length 만큼 반복한다 -> n*(n+1)/2 -> O(n^2)
      while (count++ != this.length) {
        switch (curDirect = nextDirect(i, j)) {
          case BOTTOM -> i += 1;
          case RIGHT -> j += 1;
          case TOP -> {
            i -= 1;
            j -= 1;
          }
        }
        triangle[i][j] = cur++;
      }
    }

    private Direct nextDirect(int i, int j) {
      int BLANK = 0;
      switch (this.curDirect) {
        case BOTTOM -> {
          if (i + 1 < this.n && triangle[i + 1][j] == BLANK) return Direct.BOTTOM;
          else if (j + 1 < this.triangle[i].length && triangle[i][j + 1] == BLANK) return Direct.RIGHT;
        }
        case RIGHT -> {
          if (j + 1 < this.triangle[i].length && triangle[i][j + 1] == BLANK) return Direct.RIGHT;
          else if (i - 1 >= 0 && triangle[i - 1][j - 1] == BLANK) return Direct.TOP;
        }
        case TOP -> {
          if (i - 1 >= 0 && triangle[i - 1][j - 1] == BLANK) return Direct.TOP;
          else if (i + 1 < this.n && triangle[i + 1][j] == BLANK) return Direct.BOTTOM;
        }
      }
      throw new UnsupportedOperationException();
    }

    private int[] getAnswer() {
      int[] answer = new int[this.length];
      int i = 0;
      // 두 반복문 모두 answer의 길이만틈 반복한다 -> this.length -> n*(n+1)/2 -> O(n^2)
      for (int j = 0; j < n; j++) {
        int length = this.triangle[j].length;
        for (int k = 0; k < length; k++) answer[i++] = this.triangle[j][k];
      }
      return answer;
    }
  }
}
