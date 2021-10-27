package baekjoon;

import java.util.Scanner;

public class a {
  public static void main(String[] args) {
    int i, temp;
    while (true) {
      // 입력한 값의 나머지를 temp에 넣어 나머지를 구한다.
      temp = 25 % 10;
      // 입력한 값의 몫을 i에 넣어준다.
      i = 25 / 10;
      System.out.print("몫 : " + i + "\t");
      System.out.println("나머지 : " + temp);
      // 몫 i의 값이 0이 될때 까지 반복한다.
      if (i == 0) {
        break;
      }
    }
  }
}