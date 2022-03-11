package temp.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2110 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int C = Integer.parseInt(st.nextToken());
    int left = 1;
    int right = 0;
    int result = 0;
    int[] arr = new int[N];

    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    br.close();
    Arrays.sort(arr);
    right = arr[N - 1];

    while (left <= right) {
      int temp = 1;
      int pre = arr[0];
      int mid = (left + right) / 2;
      for (int i = 1; i < N; i++) {
        if (arr[i] - pre >= mid) {
          pre = arr[i];
          temp += 1;
        }
      }
      if (temp >= C) {
        left = mid + 1;
        result = mid;
      } else {
        right = mid - 1;
      }
    }

    System.out.println(result);
  }
}
