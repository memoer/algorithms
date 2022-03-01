package baekjoon.탐욕;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P1092 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 크레인 수
    final int N = Integer.parseInt(br.readLine());
    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] train = new int[N];
    int result = 0;
    for (int i = 0; i < N; i++) {
      train[i] = Integer.parseInt(st.nextToken());
    }
    final int M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    PriorityQueue<Integer> box = new PriorityQueue<>(Collections.reverseOrder());
    for (int i = 0; i < M; i++) {
      box.offer(Integer.parseInt(st.nextToken()));
    }
    br.close();

    Arrays.sort(train);
    if (train[N - 1] < box.peek()) {
      result = -1;
    } else {
      while (!box.isEmpty()) {
        for (int i = N - 1; i >= 0; i--) {
          if (box.isEmpty()) {
            break;
          } else if (train[i] >= box.peek()) {
            box.poll();
          }
        }
        result += 1;
      }
    }
    System.out.println(result);
  }
}
