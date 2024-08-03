package temp;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

  private static List<String> list = new ArrayList<>();

  public static void main(String[] args) {
    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    list.add("e");
    int n = list.size();
    permutation(n, 2, 0);
  }

  private static void permutation(int n, int r, int depth) {
    if (depth == r) {
      System.out.println(list.subList(0, r));
      return;
    }
    for (int i = depth; i < n; i++) {
      swap(i, depth);
      permutation(n, r, depth + 1);
      swap(i, depth);
    }
  }

  private static void swap(int i, int j) {
    String a = list.get(i);
    String b = list.get(j);
    list.set(i, b);
    list.set(j, a);
  }
}