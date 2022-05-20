package flab;

// [70]6] https://leetcode.com/problems/design-hashmap/
public class D20220508_1 {
  public static void main(String[] args) {
    MyHashMap myHashMap = new MyHashMap();
    myHashMap.put(1, 1); // The map is now [[1,1]]
    myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
    System.out.println(myHashMap.get(1));
    System.out.println(myHashMap.get(3));
    myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
    System.out.println(myHashMap.get(2));
    myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
    System.out.println(myHashMap.get(2));
  }

  static class MyHashMap {
    private static class Node {
      int k;
      int v;
      Node next;
      Node pre;

      public Node(int k, int v) {
        this.k = k;
        this.v = v;
        this.next = null;
        this.pre = null;
      }
    }

    private int capacity;
    private final float LOAD_FACTOR;
    private Node[] tab;
    private int size;
    private int threshold;

    public MyHashMap() {
      this.size = 0;
      this.capacity = 15;
      this.LOAD_FACTOR = 0.75f;
      this.threshold = (int) (this.capacity * this.LOAD_FACTOR);
      this.tab = new Node[this.capacity];
    }

    public void put(int key, int value) {
      Node node = this.tab[this.hash(key)];

      if (node == null) {
        this.size += 1;
        if (this.size > this.threshold) this.resize();
        this.tab[this.hash(key)] = new Node(key, value);
      } else {
        while (true) {
          if (node.k == key) {
            node.v = value;
            return;
          } else if (node.next == null) break;
          node = node.next;
        }
        Node newNode = new Node(key, value);
        node.next = newNode;
        newNode.pre = node;
      }
    }

    public int get(int key) {
      Node node = this.tab[this.hash(key)];

      if (node == null) return -1;
      else if (node.k == key) return node.v;

      while ((node = node.next) != null) {
        if (node.k == key) return node.v;
      }
      return -1;
    }

    public void remove(int key) {
      Node node = this.tab[this.hash(key)];

      if (node == null) return;
      else if (node.k == key) {
        this.tab[this.hash(key)] = null;
        this.size -= 1;
      } else {
        while ((node = node.next) != null) {
          if (node.k == key) break;
        }
        if (node == null) return;
        Node preNode = node.pre;
        Node nextNode = node.next;

        node.pre = null;
        node.next = null;
        if (preNode != null) preNode.next = nextNode;
        if (nextNode != null) nextNode.pre = preNode;
      }
    }

    private int hash(int key) {
      return key % this.capacity;
    }

    public void resize() {
      final int PRE_CAPACITY = this.capacity;
      this.capacity *= 2;
      this.threshold = (int) (this.capacity * this.LOAD_FACTOR);

      Node[] newTab = new Node[this.capacity];
      for (int i = 0; i < PRE_CAPACITY; i++) {
        Node node = this.tab[i];
        if (node == null) continue;
        newTab[this.hash(node.k)] = node;
      }
      this.tab = newTab;
    }
  }
}
