package programmers.Lv2;

import java.util.Stack;

public class P60057 {
  static class Solution {
    public int solution(String s) {
      int length = s.length();
      int answer = Integer.MAX_VALUE;
      for (int size = 1; size <= length / 2; size++) {
        Stack<Slice> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i += size) {
          // String preSlice = i == 0 ? "" : s.substring(i - size, i);A
          String data = s.substring(i, i + size > length ? length : i + size);
          if (!stack.isEmpty() && stack.peek().data.equals(data)) {
            stack.peek().addAcc();
          } else {
            stack.push(new Slice(data));
          }
        }
        while (!stack.isEmpty()) {
          Slice slice = stack.pop();
          sb.append((slice.acc == 1 ? "" : slice.acc) + slice.data);
        }
        answer = Math.min(answer, sb.length());
      }
      return answer;
    }

    private class Slice {
      public int acc = 1;
      public String data;

      public Slice(String data) {
        this.data = data;
      }

      public void addAcc() {
        this.acc += 1;
      }
    }
  }

  public static void main(String[] args) {
    String[] sArr = { "aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd",
            "aaabbbaaa" };
    for (String s : sArr) {
      System.out.println(new Solution().solution(s));
    }
  }
}
