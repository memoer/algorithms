package temp;

import java.util.ArrayList;
import java.util.List;

public class Permutation {

    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        list.add("a");
        list.add("b");
        list.add("c");
        int size = list.size();
        permutation(size, 2, 0);
    }

    private static void permutation(int n, int r, int depth) {
        if (r == depth) {
            System.out.println(list);
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(i, depth);
            permutation(n, r, depth + 1);
            swap(i, depth);
        }
    }

    private static void swap(int i, int j) {
        String temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
