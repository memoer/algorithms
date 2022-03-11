package temp.Search;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B1991 {
  private static int LEFT = 0;
  private static int RIGHT = 1;
  private static int[][] graph;
  private static final char CONVERT = 'A';
  private static final int NONE = '.' - CONVERT;

  private static void order(int type, StringBuilder sb, int me) {
    if (type == 0) {
      sb.append((char) (me + CONVERT));
    }
    if (graph[me][LEFT] != NONE) {
      order(type, sb, graph[me][LEFT]);
    }
    if (type == 1) {
      sb.append((char) (me + CONVERT));
    }
    if (graph[me][RIGHT] != NONE) {
      order(type, sb, graph[me][RIGHT]);
    }
    if (type == 2) {
      sb.append((char) (me + CONVERT));
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    graph = new int[N][2];
    for (int i = 0; i < N; i++) {
      String[] s = br.readLine().split(" ");
      int me = s[0].charAt(0) - CONVERT;
      int left = s[1].charAt(0) - CONVERT;
      int right = s[2].charAt(0) - CONVERT;
      graph[me][LEFT] = left;
      graph[me][RIGHT] = right;
    }
    br.close();

    for (int i = 0; i < 3; i++) {
      StringBuilder sb = new StringBuilder();
      order(i, sb, 0);
      bw.write(sb.toString() + "\n");
    }
    bw.flush();
    bw.close();
  }
}
