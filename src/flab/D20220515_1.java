package flab;

import java.util.Arrays;

// 범위 내에 1의 개수
// https://docs.google.com/document/d/119jeuxmDocUknXIrJoT4fmUBrfzv_8BbfFGTjP7-x3w/edit
public class D20220515_1 {
  public static void main(String[] args) {
    //    int s = 2;
    //    int e = 6;
    //    System.out.println(new OnesInTheRange().numOfOnes(s, e));

    System.out.println(new Answer().numOfOnes(2, 6));
  }

  static public class OnesInTheRange {
    static final int[] arr = new int[]{0, 1, 1, 0, 0, 1, 1, 1}; // ex 1

    public int numOfOnes(int s, int e) {
      final int LENGTH = e - s + 1;
      int[] ints = Arrays.copyOfRange(arr, s, e + 1);
      Arrays.sort(ints);
      if (ints[LENGTH - 1] == 0) {
        return 0;
      }
      for (int i = 0; i < LENGTH; i++) {
        if (ints[i] == 1) {
          return LENGTH - i;
        }
      }
      return 0;
    }
  }

  static class Answer {
    static final int[] arr = new int[]{0, 1, 1, 0, 0, 1, 1, 1}; // ex 1
    private final int[] ones;

    public Answer() {
      ones = new int[arr.length];
      ones[0] = arr[0];
      for (int i = 1; i < arr.length; i++) {
        ones[i] = ones[i - 1] + arr[i];
      }
    }

    public int numOfOnes(int s, int e) {
      return s == 0 ? ones[e] : ones[e] - ones[s - 1];
    }
  }
}
