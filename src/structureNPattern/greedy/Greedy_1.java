package structureNPattern.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Greedy_1 {
  public static void print(Map<Integer, Integer> map) {
    for (int key : map.keySet()) {
      System.out.println(key + ": " + map.get(key));
    }
  }

  public static void main(String[] args) throws Exception {
    int price = 4720;
    Integer[] coinList = { 1, 100, 50, 500 };
    Map<Integer, Integer> answer = new HashMap<>();
    Arrays.sort(coinList, Comparator.reverseOrder());
    for (int coin : coinList) {
      int number = price / coin;
      price -= coin * number;
      answer.put(coin, number);
    }
    print(answer);
  }
}
