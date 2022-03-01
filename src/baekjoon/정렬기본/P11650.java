package baekjoon.정렬기본;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P11650 {
  static class Coordinate {
    int x;
    int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return this.x + " " + this.y;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.valueOf(br.readLine());
    Coordinate[] cArr = new Coordinate[N];
    for (int i = 0; i < N; i++) {
      String[] input = br.readLine().split(" ");
      cArr[i] = new Coordinate(Integer.valueOf(input[0]), Integer.valueOf(input[1]));
    }
    Arrays.sort(cArr, (pre, cur) -> pre.x != cur.x ? pre.x - cur.x : pre.y - cur.y);
    for (Coordinate c : cArr) {
      bw.write(c + "\n");
    }
    bw.flush();
    bw.close();
    br.close();
  }
}
