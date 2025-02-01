package programmers.Lv2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class P64065_2 {

    public static void main(String[] args) {
        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        int[] solution = new Solution().solution(s);

        for (int i : solution) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    private static class Solution {

        public int[] solution(String s) {
            Set<String> set = new HashSet<>();
            return Arrays.stream(s.substring(1, s.length() - 1).split("},"))
                .sorted(Comparator.comparingInt(String::length))
                .map(v1 -> {
                    String[] split = v1.substring(1, v1.length() - (v1.contains("}") ? 1 : 0)).split(",");
                    String target = null;
                    for (String v2 : split) {
                        if (set.contains(v2)) {
                            continue;
                        }
                        set.add(v2);
                        target = v2;
                    }
                    return target;
                })
                .filter(Objects::nonNull)
                .mapToInt(Integer::parseInt)
                .toArray();
        }
    }
}
