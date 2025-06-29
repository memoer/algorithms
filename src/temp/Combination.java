package temp;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        list.add("a");
        list.add("b");
        list.add("c");
        int size = list.size();
        combination(size, 2, 0, new ArrayList<>());
    }

    private static void combination(int n, int r, int start, List<String> temp) {
        if (temp.size() == r) {
            System.out.println(temp);
            return;
        }

        for (int i = start; i < n; i++) {
            temp.add(list.get(i));
            combination(n, r, i + 1, temp);
            temp.removeLast();
        }
    }
}
