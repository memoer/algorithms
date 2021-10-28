package structureNPattern.sort;

public class SelectSort extends Sort {

  public void print(int[] list) {
    for (int i : list) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  @Override
  public void impl(SortType sortType, int[] list, boolean onProgress) {
    int length = list.length;
    for (int i = 0; i < length - 1; i++) {
      int conditionIdx = i;
      for (int j = i + 1; j < length; j++) {
        boolean condition = sortType == SortType.DESCENDING ? list[conditionIdx] < list[j]
            : list[conditionIdx] > list[j];
        if (condition) {
          conditionIdx = j;
        }
      }
      if (conditionIdx != -1) {
        int temp = list[i];
        list[i] = list[conditionIdx];
        list[conditionIdx] = temp;
      }
      if (onProgress) {
        print(list);
      }
    }

  }
}
