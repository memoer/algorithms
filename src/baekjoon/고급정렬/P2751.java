package baekjoon.고급정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P2751 {
  private static int[] merge(int[] l, int[] r) {
    final int L_LENGTH = l.length;
    final int R_LENGTH = r.length;
    int lp = 0, rp = 0, idx = 0;
    int[] temp = new int[L_LENGTH + R_LENGTH];
    while (lp < L_LENGTH && rp < R_LENGTH) {
      temp[idx++] = l[lp] > r[rp] ? r[rp++] : l[lp++];
    }
    if (lp < L_LENGTH) {
      for (; lp < L_LENGTH; lp++) {
        temp[idx++] = l[lp];
      }
    } else if (rp < R_LENGTH) {
      for (; rp < R_LENGTH; rp++) {
        temp[idx++] = r[rp];
      }
    }
    return temp;
  }

  private static int[] splitNMerge(int[] list) {
    int length = list.length;
    if (length < 2) {
      return list;
    }
    int[] l = splitNMerge(Arrays.copyOfRange(list, 0, length / 2));
    int[] r = splitNMerge(Arrays.copyOfRange(list, length / 2, length));
    return merge(l, r);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N = Integer.parseInt(br.readLine());
    int[] list = new int[N];
    for (int i = 0; i < N; i++) {
      list[i] = Integer.parseInt(br.readLine());
    }
    for (int num : splitNMerge(list)) {
      bw.write(num + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
