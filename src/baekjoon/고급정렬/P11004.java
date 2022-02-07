package baekjoon.고급정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11004 {
  private static int[] merge(int[] l, int[] r) {
    final int L_LENGTH = l.length, R_LENGTH = r.length;
    int lp = 0, rp = 0, idx = 0;
    int[] temp = new int[L_LENGTH + R_LENGTH];
    while (lp < L_LENGTH && rp < R_LENGTH) {
      temp[idx++] = l[lp] > r[rp] ? r[rp++] : l[lp++];
    }
    if (lp < L_LENGTH) {
      for (; lp < L_LENGTH; lp++) {
        temp[idx++] = l[lp];
      }
    }
    if (rp < R_LENGTH) {
      for (; rp < R_LENGTH; rp++) {
        temp[idx++] = r[rp];
      }
    }
    return temp;
  }

  private static int[] splitNMerge(int[] arr) {
    int length = arr.length;
    if (length < 2) {
      return arr;
    }
    int[] l = splitNMerge(Arrays.copyOfRange(arr, 0, length / 2));
    int[] r = splitNMerge(Arrays.copyOfRange(arr, length / 2, length));
    return merge(l, r);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // StringTokenizer 을 쓰면, 메모리를 적게 사용한다.
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken()) - 1;
    int[] arr = new int[N];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    bw.write(splitNMerge(arr)[K] + "\n");

    bw.flush();
    bw.close();
    br.close();
  }
}
