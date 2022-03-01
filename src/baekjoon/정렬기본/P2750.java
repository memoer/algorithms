package baekjoon.정렬기본;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P2750 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.valueOf(br.readLine());
    int[] answer = new int[N];
    for (int i = 0; i < N; i++) {
      answer[i] = Integer.valueOf(br.readLine());
    }
    Arrays.sort(answer);
    for (int num : answer) {
      bw.write(num + "\n");
    }
    bw.flush();
    bw.close();
    br.close();
  }
}
