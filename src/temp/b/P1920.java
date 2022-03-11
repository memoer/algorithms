package temp.b;

import java.util.Arrays;
import java.util.Scanner;

public class P1920 {
  private static int[] getList(Scanner sc) {
    int N = Integer.valueOf(sc.nextLine());
    int[] list = new int[N];
    String[] a = sc.nextLine().split(" ");
    for (int i = 0; i < N; i++) {
      list[i] = Integer.valueOf(a[i]);
    }
    return list;
  }

  private static String search(int[] src, int num) {
    int left = 0;
    int right = src.length - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (src[mid] > num) {
        right = mid - 1;
      } else if (src[mid] < num) {
        left = mid + 1;
      } else {
        return "1\n";
      }
    }
    return "0\n";
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    int[] a = getList(sc);
    int[] b = getList(sc);
    Arrays.sort(a);
    for (int num : b) {
      sb.append(search(a, num));
    }
    System.out.println(sb.toString());
    sc.close();
  }
}
