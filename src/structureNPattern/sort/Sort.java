package structureNPattern.sort;

public abstract class Sort {

  public void print(int[] list) {
    for (int i : list) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  private void line() {
    System.out.println("-- -- -- -- -- -- -- -- -- --");
  }

  public void run(SortType sortType, int[] list, boolean onProgress) {
    if (onProgress) {
      this.line();
    }
    this.impl(sortType, list, onProgress);
    if (onProgress) {
      this.line();
    }
  };

  protected abstract void impl(SortType sortType, int[] list, boolean onProgress);
}
