package baekjoon.기본탐색;

import java.util.Scanner;

// ? 탐색
public class P1236 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    String[] input = sc.nextLine().split(" ");
    int row = Integer.valueOf(input[0]);
    int col = Integer.valueOf(input[1]);
    char[][] board = new char[row][col];
    int[] candidate = new int[2];
    for (int i = 0; i < row; i++) {
      String s = sc.nextLine();
      boolean exist = false;
      for (int j = 0; j < s.length(); j++) {
        board[i][j] = s.charAt(j);
        if (s.charAt(j) == 'X') {
          exist = true;
        }
      }
      if (!exist) {
        candidate[0]++;
      }
    }
    for (int i = 0; i < col; i++) {
      boolean exist = false;
      for (int j = 0; j < row; j++) {
        if (board[j][i] == 'X') {
          exist = true;
          break;
        }
      }
      if (!exist) {
        candidate[1]++;
      }
    }
    System.out.println(Math.max(candidate[0], candidate[1]));
    sc.close();
  }
}
