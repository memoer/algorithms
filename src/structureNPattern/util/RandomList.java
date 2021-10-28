package structureNPattern.util;

public class RandomList {
  private static boolean isDuplicated(int[] list, int number) {
    for (int i = 0; i < list.length; i++) {
      if (list[i] == 0) {
        break;
      } else if (list[i] == number) {
        return true;
      }
    }
    return false;
  }

  public static int[] get(int length) {
    int[] list = new int[length];
    int idx = 0;
    while (idx < length) {
      int number = (int) (Math.random() * 100);
      if (!isDuplicated(list, number)) {
        list[idx] = number;
        idx += 1;
      }
    }
    return list;
  }
}
