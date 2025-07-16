package programmers.review;

public class P43105 {

    public static void main(String[] args) {
        int[][] triangle = {
            {7},
            {3, 8},
            {8, 1, 0},
            {2, 7, 4, 4},
            {4, 5, 2, 6, 5}
        };
        int solution = new Solution().solution(triangle);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int[][] triangle) {
            int height = triangle.length;
            int[][] temp = create(height, triangle[0][0]);

            for (int i = 1; i < height; i++) {
                int[] width = triangle[i];
                temp[i] = new int[width.length];
                for (int j = 0; j < width.length; j++) {
                    int[] ints = get(j, temp, i - 1, width.length);
                    for (int v : ints) {
                        temp[i][j] = Math.max(temp[i][j], triangle[i][j] + v);
                    }
                }
            }

            return getAnswer(temp, height);
        }

        private int[][] create(int height, int first) {
            int[][] temp = new int[height][];
            temp[0] = new int[1];
            temp[0][0] = first;
            return temp;
        }

        private int[] get(int j, int[][] temp, int parent, int len) {
            if (j == 0) {
                return new int[]{0, temp[parent][j]};
            } else if (j == len - 1) {
                return new int[]{temp[parent][j - 1], 0};
            } else {
                return new int[]{temp[parent][j - 1], temp[parent][j]};
            }
        }

        private int getAnswer(int[][] temp, int height) {
            int answer = Integer.MIN_VALUE;
            int[] ints = temp[height - 1];
            for (int v : ints) {
                answer = Math.max(answer, v);
            }
            return answer;
        }
    }
}
