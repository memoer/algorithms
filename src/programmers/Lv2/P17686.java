package programmers.Lv2;

import java.util.Arrays;

public class P17686 {
  public static void main(String[] args) {
    //    String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
    String[] files = {"F-14"};
    for (String s : new Solution().solution(files)) System.out.print(s + ", ");
    System.out.println();
  }

  private static class Solution {
    /*
    1. 1 <= files[0].length <= 100
    2. 1 <= files.length <= 1,000
    3. files[0] -> 영문 대소문자, 숫자, 공백(" "), 마침표("."), 빼기 부호("-") 로 이루어져 있다.
    4. files[0] -> 영문자로 시작하며, 숫자를 하나 이상 포함하고 있다.
     */
    /*
    1. head -> 사전 순 정렬, 문자열 비교시 대소문자 구분 X
    2. number -> head 대소문자 차이 외에 같을 경우, number 숫자 순으로 정렬 "9 < 10 < 0011 < 012 < 13 < 14" 순으로 정렬된다.
    3. tail -> "head, number" 같을 경우, 원래 입력에 주어진 순서를 유지한다.
     */
    public String[] solution(String[] files) {
      return Arrays
              .stream(files)
              .map(File::of)
              .sorted()
              .map(File::merge)
              .toArray(String[]::new);
    }

    private static class File implements Comparable<File> {
      private final String head;
      private final String number;
      private final String tail;

      public File(String head, String number, String tail) {
        this.head = head;
        this.number = number;
        this.tail = tail;
      }

      private static File of(String file) {
        int length = file.length();
        int nIdx = 0;
        int tIdx = 0;
        for (int i = 0; i < length; i++) {
          char c = file.charAt(i);
          if (c >= 48 && c <= 57) {
            if (nIdx == 0) nIdx = i;
          } else if (nIdx != 0) {
            tIdx = i;
            break;
          }
        }
        String head = file.substring(0, nIdx);
        String number = tIdx == 0 ? file.substring(nIdx) : file.substring(nIdx, tIdx);
        String tail = tIdx == 0 ? null : file.substring(tIdx);
        return new File(head, number, tail);
      }

      public String merge() {
        String s = head + number;
        return tail == null ? s : s + tail;
      }

      @Override
      public int compareTo(File o) {
        int cHead = this.head.toLowerCase().compareTo(o.head.toLowerCase());
        return cHead != 0 ? cHead : Integer.compare(Integer.parseInt(this.number), Integer.parseInt(o.number));
      }
    }
  }
}
