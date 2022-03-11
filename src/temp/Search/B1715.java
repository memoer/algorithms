package temp.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B1715 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int n = Integer.parseInt(br.readLine());
    int result = 0;
    while (n-- > 0) {
      pq.offer(Integer.parseInt(br.readLine()));
    }
    br.close();
    while (pq.size() > 1) {
      int sum = pq.poll() + pq.poll();
      result += sum;
      pq.offer(sum);
    }

    System.out.println(result);
  }
}
