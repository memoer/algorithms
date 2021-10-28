package baekjoon.advancedStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// * 해시, 집합, 그래프
public class P4195 {
  static class Solution {
    private Map<String, String> parent;
    private Map<String, Integer> rank;
    private Map<String, Integer> network;

    private void union(String rootA, String rootB) {
      int rankA = rank.get(rootA);
      int rankB = rank.get(rootB);
      int sumNetwork = network.get(rootA) + network.get(rootB);
      if (rankA > rankB) {
        parent.put(rootB, rootA);
      } else {
        parent.put(rootA, rootB);
        if (rankA == rankB) {
          rank.put(rootB, rankB + 1);
        }
      }
      network.put(rootA, sumNetwork);
      network.put(rootB, sumNetwork);
    }

    private String find(String people) {
      if (people != parent.get(people)) {
        parent.put(people, find(parent.get(people)));
      }
      return parent.get(people);
    }

    private void init(String[] peopleList) {
      String a = peopleList[0];
      String b = peopleList[1];
      if (!parent.containsKey(a)) {
        parent.put(a, a);
      }
      if (!parent.containsKey(b)) {
        parent.put(b, b);
      }
      if (!rank.containsKey(a)) {
        rank.put(a, 1);
      }
      if (!rank.containsKey(b)) {
        rank.put(b, 1);
      }
      if (!network.containsKey(a)) {
        network.put(a, 1);
      }
      if (!network.containsKey(b)) {
        network.put(b, 1);
      }
    }

    public void solution() throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      List<Integer> result = new ArrayList<>();
      int N = Integer.valueOf(br.readLine());
      for (int i = 0; i < N; i++) {
        int F = Integer.valueOf(br.readLine());
        parent = new HashMap<>();
        rank = new HashMap<>();
        network = new HashMap<>();
        for (int j = 0; j < F; j++) {
          String[] peopleList = br.readLine().split(" ");
          init(peopleList);
          String rootA = find(peopleList[0]);
          String rootB = find(peopleList[1]);
          if (rootA != rootB) {
            union(rootA, rootB);
          }
          result.add(network.get(find(peopleList[1])));
        }
      }
      for (int r : result) {
        bw.write(r + "\n");
      }
      br.close();
      bw.flush();
      bw.close();
    }
  }

  public static void main(String[] args) throws Exception {
    new Solution().solution();
  }
}
