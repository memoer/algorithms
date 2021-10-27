package baekjoon;

import java.security.MessageDigest;
import java.util.Scanner;

public class P10930 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    String input = sc.nextLine();

    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(input.getBytes());

    StringBuilder sb = new StringBuilder();
    for (byte b : md.digest()) {
      sb.append(String.format("%02x", b));
    }
    System.out.println(sb.toString());
    sc.close();
  }
}
