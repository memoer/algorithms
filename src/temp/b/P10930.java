package temp.b;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class P10930 {
  private static MessageDigest getMd(String str) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(str.getBytes());
    return md;
  }

  private static String byteToHex(byte[] byteList) {
    StringBuilder sb = new StringBuilder();
    for (byte b : byteList) {
      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }

  public static void main(String[] args) throws NoSuchAlgorithmException {
    Scanner sc = new Scanner(System.in);
    MessageDigest md = getMd(sc.nextLine());
    String hexStr = byteToHex(md.digest());
    System.out.println(hexStr);
    sc.close();
  }
}
