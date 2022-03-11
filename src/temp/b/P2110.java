package temp.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2110 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int C = Integer.parseInt(st.nextToken());
    int[] routerList = new int[N];
    int left = 1;
    int right = 0;
    int result = 0;

    for (int i = 0; i < N; i++) {
      routerList[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(routerList);
    right = routerList[N - 1] - routerList[0];

    while (left <= right) {
      int gap = (left + right) / 2;
      int temp = 1;
      int i = 0;
      for (int j = i + 1; j < N; j++) {
        if (routerList[j] - routerList[i] < gap) {
          continue;
        }
        i = j;
        temp += 1;
      }
      if (temp >= C) {
        left = gap + 1;
        result = gap;
      } else {
        right = gap - 1;
      }
    }

    bw.write(result + "\n");
    bw.flush();
    bw.close();
    br.close();
  }
}
