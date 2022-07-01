package baekjoon.탐욕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1781_2 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    PriorityQueue<Problem> pq = new PriorityQueue<>();
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int deadline = Integer.parseInt(st.nextToken());
      int noodle = Integer.parseInt(st.nextToken());
      pq.offer(new Problem(deadline, noodle));
    }
    br.close();

    PriorityQueue<Integer> solved = new PriorityQueue<>(Integer::compare);
    while (!pq.isEmpty()) {
      Problem problem = pq.poll();
      int size = solved.size();
      if (size == problem.deadline && problem.noodle > solved.peek()) {
        solved.poll();
        solved.offer(problem.noodle);
      } else if (size < problem.deadline) {
        solved.offer(problem.noodle);
      }
    }
    System.out.println(solved.stream().mapToInt(Integer::valueOf).sum());
  }

  private record Problem(int deadline, int noodle) implements Comparable<Problem> {
    @Override
      public int compareTo(Problem o) {
        if (this.deadline == o.deadline) return Integer.compare(o.noodle, this.noodle);
        return Integer.compare(this.deadline, o.deadline);
      }
    }
}
