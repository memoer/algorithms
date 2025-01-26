package programmers.Lv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P17686_2 {

    public static void main(String[] args) {
        String[] files = {
            "F-15"
        };
        String[] solution = new Solution().solution(files);
        for (String s : solution) {
            System.out.println(s);
        }
    }

    private static class Solution {

        public String[] solution(String[] files) {
            List<Data> list = new ArrayList<>();
            int len = files.length;

            for (int i = 0; i < len; i++) {
                String file = files[i];
                int[] idx = findNumber(file.toCharArray());
                String head = file.substring(0, idx[0]).toLowerCase();
                String number = file.substring(idx[0], idx[1]);
                Data data = new Data(file, head, Integer.parseInt(number), i);
                list.add(data);
            }

            return list.stream().sorted((o1, o2) -> {
                int compare1 = o1.head.compareTo(o2.head);
                if (compare1 != 0) {
                    return compare1;
                }

                int compare2 = Integer.compare(o1.number, o2.number);
                if (compare2 != 0) {
                    return compare2;
                }

                return Integer.compare(o1.idx, o2.idx);
            }).map(v -> v.origin).toArray(String[]::new);
        }

        private int[] findNumber(char[] arr) {
            int[] nIdx = new int[2];

            int len = arr.length;
            for (int i = 0; i < len; i++) {
                if (Character.isDigit(arr[i])) {
                    if (nIdx[0] == 0) {
                        nIdx[0] = i;
                    }
                } else if (nIdx[0] != 0) {
                    nIdx[1] = i;
                    break;
                }
            }

            if (nIdx[1] == 0) {
                nIdx[1] = len;
            }
            return nIdx;
        }

        private static class Data {

            public String origin;
            public String head;
            public int number;
            public int idx;

            public Data(String origin, String head, int number, int idx) {
                this.origin = origin;
                this.head = head;
                this.number = number;
                this.idx = idx;
            }
        }
    }


}
