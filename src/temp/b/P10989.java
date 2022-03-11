package temp.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class P10989 {
  public static void main(String[] args) throws IOException {
    final int RANGE = 10_001;
    int[] temp = new int[RANGE];
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    for (int i = 0; i < N; i++) {
      int num = Integer.parseInt(br.readLine());
      temp[num] += 1;
    }
    for (int i = 0; i < RANGE; i++) {
      for (int j = 0; j < temp[i]; j++) {
        bw.write(i + "\n");
      }
    }
    bw.flush();
    bw.close();
    br.close();
  }
}
