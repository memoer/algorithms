package baekjoon.advancedSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class P2751 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.valueOf(br.readLine());
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < N; i++) {
      int num = Integer.valueOf(br.readLine());
      pq.add(num);
    }
    while (!pq.isEmpty()) {
      bw.write(pq.poll() + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
