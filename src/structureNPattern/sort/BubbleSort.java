package structureNPattern.sort;

public class BubbleSort extends Sort {
  private void swap(int[] list, int i, int j) {
    int temp = list[i];
    list[i] = list[j];
    list[j] = temp;
  }

  @Override
  protected void impl(SortType sortType, int[] list, boolean onProgress) {
    int length = list.length;
    for (int i = 0; i < length - 1; i++) {
      for (int j = i; j < length - 1; j++) {
        boolean condition = sortType == SortType.DESCENDING ? list[i] < list[j] : list[i] > list[j];
        if (condition) {
          this.swap(list, j, j + 1);
        }
        if (onProgress) {
          this.print(list);
        }
      }
    }
  }

}
