package temp.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class P4195 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    final int F = Integer.parseInt(br.readLine());
    for (int i = 0; i < F; i++) {
      Solution s = new Solution();
      final int N = Integer.parseInt(br.readLine());
      for (int j = 0; j < N; j++) {
        String[] list = br.readLine().split(" ");
        String a = list[0];
        String b = list[1];
        s.initialize(a, b);
        s.union(a, b);
        bw.write(s.getRootNetwork(a) + "\n");
      }
      bw.flush();
    }
    bw.close();
    br.close();
  }

  static class Solution {
    private Map<String, String> parent;
    private Map<String, Integer> rank;
    private Map<String, Integer> network;

    public Solution() {
      this.parent = new HashMap<>();
      this.rank = new HashMap<>();
      this.network = new HashMap<>();
    }

    private int getRootNetwork(String s) {
      return this.network.get(this.find(s));
    }

    private String find(String a) {
      if (a != this.parent.get(a)) {
        this.parent.put(a, this.find(this.parent.get(a)));
      }
      return this.parent.get(a);
    }

    private void union(String a, String b) {
      String rootA = this.find(a);
      String rootB = this.find(b);
      if (rootA.equals(rootB)) {
        return;
      }
      int sumNetwork = this.network.get(rootA) + this.network.get(rootB);
      this.network.put(rootA, sumNetwork);
      this.network.put(rootB, sumNetwork);
      if (this.rank.get(rootA) > this.rank.get(rootB)) {
        this.parent.put(rootB, rootA);
      } else {
        this.parent.put(rootA, rootB);
        if (this.rank.get(rootA) == this.rank.get(rootB)) {
          this.rank.put(rootB, this.rank.get(rootB) + 1);
        }
      }
    }

    private void initialize(String a, String b) {
      if (!this.parent.containsKey(a)) {
        this.parent.put(a, a);
      }
      if (!this.rank.containsKey(a)) {
        this.rank.put(a, 1);
      }
      if (!this.network.containsKey(a)) {
        this.network.put(a, 1);
      }
      if (!this.parent.containsKey(b)) {
        this.parent.put(b, b);
      }
      if (!this.rank.containsKey(b)) {
        this.rank.put(b, 1);
      }
      if (!this.network.containsKey(b)) {
        this.network.put(b, 1);
      }
    }
  }

}
