package structureNPattern.sort;

public class InsertSort extends Sort {
  private void insert(int[] list, int i, int j) {
    int temp = list[i];
    for (int k = i; k > j; k--) {
      list[k] = list[k - 1];
    }
    list[j] = temp;
  }

  @Override
  protected void impl(SortType sortType, int[] list, boolean onProgress) {
    int length = list.length;
    for (int i = 1; i < length; i++) {
      for (int j = 0; j < i; j++) {
        boolean condition = sortType == SortType.DESCENDING ? list[i] > list[j] : list[i] < list[j];
        if (condition) {
          this.insert(list, i, j);
        }
      }
      if (onProgress) {
        this.print(list);
      }
    }
  }
}
