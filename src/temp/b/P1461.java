package temp.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class P1461 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());
    List<Integer>[] list = new ArrayList[2];
    int max = Integer.MIN_VALUE;
    st = new StringTokenizer(br.readLine());
    br.close();
    for (int i = 0; i < 2; i++) {
      list[i] = new ArrayList<>();
    }
    for (int i = 0; i < N; i++) {
      int data = Integer.parseInt(st.nextToken());
      int abs = Math.abs(data);
      max = Math.max(max, abs);
      list[data >= 0 ? 0 : 1].add(abs);
    }
    Collections.sort(list[0], Collections.reverseOrder());
    Collections.sort(list[1], Collections.reverseOrder());

    int result = 0;
    for (List<Integer> l : list) {
      int size = l.size();
      for (int i = 0; i < size; i += M) {
        result += (l.get(i) * (i == 0 && l.get(i) == max ? 1 : 2));
      }
    }
    System.out.println(result);
  }
}
