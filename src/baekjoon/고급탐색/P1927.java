package baekjoon.고급탐색;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class P1927 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    final int N = Integer.parseInt(br.readLine());

    for (int i = 0; i < N; i++) {
      int x = Integer.parseInt(br.readLine());
      if (x == 0) {
        bw.write((pq.isEmpty() ? 0 : pq.poll()) + "\n");
      } else {
        pq.add(x);
      }
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
