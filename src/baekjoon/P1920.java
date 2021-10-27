package baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class InputItem {
  int number;
  int[] list;

  public InputItem(int number, int[] list) {
    this.number = number;
    this.list = list;
  }
}

public class P1920 {
  public static InputItem input(Scanner sc) {
    int N = sc.nextInt();
    int[] a = new int[N];
    for (int i = 0; i < N; i++) {
      a[i] = sc.nextInt();
    }
    return new InputItem(N, a);
  }

  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    List<Character> result = new ArrayList<Character>();
    InputItem a = input(sc);
    InputItem b = input(sc);

    for (int i = 0; i < b.number; i++) {
      boolean isContains = false;
      for (int j = 0; j < a.number; j++) {
        if (b.list[i] == a.list[j]) {
          isContains = true;
          break;
        }
      }
      result.add(isContains ? '1' : '0');
    }
    for (char c : result) {
      System.out.println(c);
    }
    sc.close();
  }
}
