package structureNPattern.sort;

import structureNPattern.util.RandomList;

enum SortType {
  // 내림차순, 오름차순
  DESCENDING, ASCENDING
}

public class Main {
  static int LENGTH = 10;
  static int[] list;

  public static void init() {
    list = RandomList.get(LENGTH);
  }

  public static void main(String args[]) throws Exception {
    // SelectSort ss = new SelectSort();
    // BubbleSort bs = new BubbleSort();
    // InsertSort is = new InsertSort();
    MergeSort ms = new MergeSort();
    init();
    ms.print(list);
    ms.run(SortType.DESCENDING, list, true);
    list = ms.getSorted();
    ms.print(list);
  }
}
