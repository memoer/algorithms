package programmers.Lv1;

import java.util.ArrayList;
import java.util.List;

public class P42889 {

    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        for (int num : new Solution().solution(N, stages)) {
            System.out.print(num + ", ");
        }
        System.out.println();
    }

    static class Solution {

        public int[] solution(int N, int[] stages) {
            int len = N + 2;
            int[] count = new int[len];
            int[] fail = new int[len];
            for (int stage : stages) {
                for (int i = 1; i <= stage; i++) {
                    count[i] += 1;
                }
                fail[stage] += 1;
            }

            List<Stage> list = new ArrayList<>();
            for (int i = 1; i < N + 1; i++) {
                double rate = count[i] == 0 ? 0 : (double) fail[i] / count[i];
                list.add(new Stage(i, rate));
            }
            return list.stream()
                .sorted(
                    (o1, o2) -> o1.rate != o2.rate ? Double.compare(o2.rate, o1.rate) : Integer.compare(o1.idx, o2.idx))
                .mapToInt(v -> v.idx)
                .toArray();
        }

        private static class Stage {

            public int idx;
            public double rate;

            public Stage(int idx, double rate) {
                this.idx = idx;
                this.rate = rate;
            }
        }
    }

}
