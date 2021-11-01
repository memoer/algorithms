package baekjoon.basicSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P10989 {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.valueOf(br.readLine());
    int[] answer = new int[10001];
    for (int i = 0; i < N; i++) {
      int num = Integer.valueOf(br.readLine());
      answer[num]++;
    }
    for (int i = 1; i < answer.length; i++) {
      for (int j = 0; j < answer[i]; j++) {
        bw.write(i + "\n");
      }
    }
    bw.flush();
    bw.close();
    br.close();
  }
}
