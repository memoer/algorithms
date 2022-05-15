package flab;

import java.util.HashMap;
import java.util.Map;

public class D20220424_2 {
  public static void main(String[] args) {
    SnapshotArray snapshotArray = new SnapshotArray(3);
    snapshotArray.set(0, 5);
    System.out.println(snapshotArray.snap());
    snapshotArray.set(0, 6);
    System.out.println(snapshotArray.get(0, 0));
  }
  static class SnapshotArray {
    private final int[] arr;
    private final Map<Integer, int[]> snap;
    private int snapId;

    public SnapshotArray(int length) {
      this.arr = new int[length];
      this.snap = new HashMap<>();
      this.snapId = 0;
    }

    public void set(int index, int val) {
      this.arr[index] = val;
    }

    public int snap() {
      this.snap.put(this.snapId, this.arr.clone());
      return this.snapId++;
    }

    public int get(int index, int snap_id) {
      return this.snap.get(snap_id)[index];
    }
  }
}
