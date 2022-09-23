package programmers.Lv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 트리를 구성하는 모든 노드의 x, y 좌표 값은 정수
 * 모든 노드는 서로 다른 x값
 * 같은 레벨(level)에 있는 노드는 같은 y 좌표
 * 자식 노드의 y 값은 항상 부모 노드보다 작다.
 * 임의의 노드 V의 왼쪽 서브 트리(left subtree)에 있는 모든 노드의 x값 -> V의 x값보다 작다.
 * 임의의 노드 V의 오른쪽 서브 트리(right subtree)에 있는 모든 노드의 x값 -> V의 x값보다 크다.
 */

/**
 * nodeinfo -> 이진트리를 구성하는 각 노드의 좌표가 1번 노드부터 순서대로 들어있는 2차원 배열
 * - 1 <= nodeinfo.length <= 10,000
 * - nodeinfo[i] 는 i + 1번 노드의 좌표이며, [x축 좌표, y축 좌표] 순
 */
// 전위/후위 순회한 결과를 2차원 배열에 순서대로 담아 반환
public class P42892 {
  public static void main(String[] args) {
    int[][] nodeinfo = {
            {5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}
    };
    new P42892().solution(nodeinfo);
  }

  public int[][] solution(int[][] nodeinfo) {
    int len = nodeinfo.length;
    for (int i = 0; i < len; i++) nodeinfo[i] = new int[]{nodeinfo[i][0], nodeinfo[i][1], i + 1};
    Arrays.sort(nodeinfo, (p, c) -> Integer.compare(c[1], p[1]));

    Tree root = null;
    for (int[] node : nodeinfo) {
      Tree newTree = new Tree(node);
      if (root == null) {
        root = newTree;
        continue;
      }
      Tree cur = root;
      while (true) {
        if (cur.node[0] < newTree.node[0]) { // 오른쪽
          if (cur.right != null) cur = cur.right;
          else {
            cur.right = newTree;
            break;
          }
        } else { // 왼쪽
          if (cur.left != null) cur = cur.left;
          else {
            cur.left = newTree;
            break;
          }
        }
      }
    }
    return new int[][]{
            preOrder(new ArrayList<>(), root).stream().mapToInt(v -> v).toArray(),
            postOrder(new ArrayList<>(), root).stream().mapToInt(v -> v).toArray()
    };
  }

  private List<Integer> preOrder(List<Integer> list, Tree cur) {
    list.add(cur.node[2]);
    if (cur.left != null) preOrder(list, cur.left);
    if (cur.right != null) preOrder(list, cur.right);
    return list;
  }

  private List<Integer> postOrder(List<Integer> list, Tree cur) {
    if (cur.left != null) postOrder(list, cur.left);
    if (cur.right != null) postOrder(list, cur.right);
    list.add(cur.node[2]);
    return list;
  }

  private static class Tree {
    private Tree left;
    private Tree right;
    private int[] node;

    public Tree(int[] node) {
      this.node = node;
    }
  }

}
