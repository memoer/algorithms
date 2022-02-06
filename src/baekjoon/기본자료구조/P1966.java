package baekjoon.기본자료구조;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// * 큐, 구현, 그리디
class Node {
  int priority;
  int index;

  public Node(int priority, int index) {
    this.priority = priority;
    this.index = index;
  }
}

public class P1966 {
  public static void main(String[] args) throws Exception {
    Scanner sc = new Scanner(System.in);
    int testCase = Integer.parseInt(sc.nextLine());
    List<Integer> result = new ArrayList<Integer>();
    for (int i = 0; i < testCase; i++) {
      int order = 0;
      Queue<Node> q = new LinkedList<Node>();
      List<Integer> priorityList = new ArrayList<Integer>();
      int N = sc.nextInt();
      int M = sc.nextInt();
      for (int index = 0; index < N; index++) {
        int priority = sc.nextInt();
        Node node = new Node(priority, index);
        q.add(node);
        priorityList.add(priority);
      }
      Collections.sort(priorityList, Comparator.reverseOrder());
      while (true) {
        Node node = q.poll();
        if (node.priority < priorityList.get(0)) {
          q.add(node);
        } else {
          order += 1;
          if (node.index == M) {
            result.add(order);
            break;
          }
          priorityList.remove(0);
        }
      }
    }
    for (int number : result) {
      System.out.println(number);
    }
    sc.close();
  }
}
