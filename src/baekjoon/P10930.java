package baekjoon;

import java.security.MessageDigest;
import java.util.Scanner;

public class P10930 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();
    StringBuilder sb = new StringBuilder();
    MessageDigest md = MessageDigest.getInstance("SHA-256");

    md.update(input.getBytes());
    for (byte b : md.digest()) {
      // 10진수를 16진수로 표현
      // String.format("%02x", 255); -> ff
      // String.format("%02X", 255); -> FF
      sb.append(String.format("%02x", b));
    }
    System.out.println(sb.toString());
    sc.close();
  }
}
