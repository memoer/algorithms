package programmers.Lv1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class P64061 {
  static class Solution {
    public int solution(int[][] board, int[] moves) {
      int answer = 0;
      int length = board.length;
      Queue<Integer>[] qArr = new LinkedList[length];
      Stack<Integer> pre = new Stack<>();
      for (int i = 0; i < length; i++) {
        qArr[i] = new LinkedList<>();
      }
      for (int i = 0; i < length; i++) {
        for (int j = 0; j < length; j++) {
          if (board[i][j] == 0) {
            continue;
          }
          qArr[j].offer(board[i][j]);
        }
      }
      for (int move : moves) {
        if (qArr[move - 1].isEmpty()) {
          continue;
        }
        int target = qArr[move - 1].poll();
        if (!pre.isEmpty() && target == pre.peek()) {
          pre.pop();
          answer += 1;
        } else {
          pre.push(target);
        }
      }
      return answer * 2;
    }
  }

  public static void main(String[] args) {
    int[][] board = {
        { 0, 0, 0, 0, 0 },
        { 0, 0, 1, 0, 3 },
        { 0, 2, 5, 0, 1 },
        { 4, 2, 4, 4, 2 },
        { 3, 5, 1, 3, 1 },
    };
    int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };
    System.out.println(new Solution().solution(board, moves));
  }
}
