package temp.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P11650 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    Coordinate[] list = new Coordinate[N];
    for (int i = 0; i < N; i++) {
      String[] input = br.readLine().split(" ");
      list[i] = new Coordinate(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
    }
    Arrays.sort(list, (pre, cur) -> pre.x != cur.x ? pre.x - cur.x : pre.y - cur.y);
    for (Coordinate c : list) {
      bw.write(c + "\n");
    }
    bw.flush();
    bw.close();
    br.close();
  }

  static class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() {
      return x + " " + y;
    }
  }
}
