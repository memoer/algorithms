package baekjoon.탐색기본;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://st-lab.tistory.com/277 <- 부가설명
public class P2110 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int C = Integer.parseInt(st.nextToken());
    int[] arr = new int[N];
    int result = 0;

    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(arr);
    int left = 1;
    int right = arr[N - 1] - arr[0];
    while (left <= right) {
      int gap = (left + right) / 2;
      int count = 1;
      int i = 0;
      for (int j = i + 1; j < N; j++) {
        if (count > C) {
          break;
        }
        if (arr[j] - arr[i] >= gap) {
          count += 1;
          i = j;
        }
      }
      if (count >= C) {
        left = gap + 1;
        result = gap;
      } else {
        right = gap - 1;
      }
    }
    System.out.println(result);

    br.close();
  }
}
