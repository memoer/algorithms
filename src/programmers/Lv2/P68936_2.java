package programmers.Lv2;

public class P68936_2 {

    public static void main(String[] args) {
        int[][] arr = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {0, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 1, 1, 1, 1},
            {0, 1, 0, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 1},
            {0, 0, 0, 0, 1, 1, 1, 1}
        };
        int[] solution = new Solution().solution(arr);
        for (int i : solution) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static class Solution {

        public int[] solution(int[][] arr) {
            return compression(arr);
        }

        public int[] compression(int[][] arr) {
            int len = arr.length;
            if (len == 1) {
                int a = arr[0][0] == 0 ? 1 : 0;
                int b = arr[0][0] == 1 ? 1 : 0;
                return new int[]{a, b};
            }
            int half = len / 2;

            int[][] lt = create(arr, half, 0, 0);
            int[] v1 = compression(lt);

            int[][] rt = create(arr, half, 0, half);
            int[] v2 = compression(rt);

            int[][] ld = create(arr, half, half, 0);
            int[] v3 = compression(ld);

            int[][] rd = create(arr, half, half, half);
            int[] v4 = compression(rd);

            int zero = (v1[0] + v2[0] + v3[0] + v4[0]);
            int one = (v1[1] + v2[1] + v3[1] + v4[1]);
            if (zero == 4 && one == 0) {
                return new int[]{1, 0};
            } else if (zero == 0 && one == 4) {
                return new int[]{0, 1};
            } else {
                return new int[]{zero, one};
            }
        }

        public int[][] create(int[][] arr, int half, int si, int sj) {
            int[][] newArr = new int[half][half];
            for (int k = 0, i = si; k < half; k++, i++) {
                for (int l = 0, j = sj; l < half; l++, j++) {
                    newArr[k][l] = arr[i][j];
                }
            }
            return newArr;
        }
    }
}
