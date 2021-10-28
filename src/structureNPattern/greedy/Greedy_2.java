package structureNPattern.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Greedy_2 {

  public static void main(String[] args) throws Exception {
    int capacity = 30;
    int[][] dataList = { { 10, 10 }, { 15, 12 }, { 20, 10 }, { 25, 8 }, { 30, 5 } };
    List<String> answer = new ArrayList<>();
    List<float[]> result = new ArrayList<>();
    float totalValue = 0;
    Arrays.sort(dataList, (pre, cur) -> {
      float preValuePerWeight = (float) pre[0] / pre[1];
      float curValuePerWeight = (float) cur[0] / cur[1];
      return (int) (preValuePerWeight - curValuePerWeight);
    });
    for (int[] data : dataList) {
      if (capacity <= 0) {
        break;
      }
      int weight = data[0];
      int value = data[1];
      if (weight <= capacity) {
        result.add(new float[] { weight, value, weight, value });
        capacity -= weight;
        totalValue += value;
      } else {
        float valuePerWeight = (float) value / weight;
        float fractionalValue = capacity * valuePerWeight;
        result.add(new float[] { weight, value, capacity, fractionalValue });
        capacity = 0;
        totalValue += fractionalValue;
      }
    }
    answer.add(Float.toString(totalValue));
    for (float[] object : result) {
      int weight = (int) object[0];
      int value = (int) object[1];
      int filledCapacity = (int) object[2];
      float fracionalValue = object[3];
      answer.add("[" + weight + ", " + value + ", " + filledCapacity + ", " + fracionalValue + "]");
    }
    System.out.println(String.join(", ", answer));
  }
}
