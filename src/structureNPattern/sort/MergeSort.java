package structureNPattern.sort;

import java.util.Arrays;

public class MergeSort extends Sort {
  private SortType sortType;
  private int[] sorted;
  private boolean onProgress;

  private int[] merge(int[] l, int[] r) {
    int[] sorted = new int[l.length + r.length];
    int idx = 0;
    int lp = 0;
    int rp = 0;
    while (lp < l.length && rp < r.length) {
      boolean condition = this.sortType == SortType.DESCENDING ? l[lp] > r[rp] : l[lp] < r[rp];
      if (condition) {
        sorted[idx++] = l[lp++];
      } else {
        sorted[idx++] = r[rp++];
      }
    }
    if (lp < l.length) {
      for (int i = lp; i < l.length; i++) {
        sorted[idx++] = l[i];
      }
    } else if (rp < r.length) {
      for (int i = rp; i < r.length; i++) {
        sorted[idx++] = r[i];
      }
    }
    return sorted;
  }

  private int[] mergeSplit(int[] list) {
    if (list.length <= 1) {
      return list;
    }
    int medium = list.length / 2;
    int[] l = mergeSplit(Arrays.copyOfRange(list, 0, medium));
    int[] r = mergeSplit(Arrays.copyOfRange(list, medium, list.length));
    if (this.onProgress && l.length > 1 && r.length > 1) {
      this.print(l);
      this.print(r);
    }
    return merge(l, r);
  }

  @Override
  protected void impl(SortType sortType, int[] list, boolean onProgress) {
    this.sortType = sortType;
    this.onProgress = onProgress;
    this.sorted = this.mergeSplit(list);
  }

  public int[] getSorted() {
    return sorted;
  }
}
