package programmers.highscore.dp;

public class P43105 {

    public static void main(String[] args) {
        int[][] triangle = {
            {7},
            {3, 8},
            {8, 1, 0},
            {2, 7, 4, 4},
            {4, 5, 2, 6, 5},
        };
        int solution = new Solution().solution(triangle);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int[][] triangle) {
            int answer = -1;
            int height = triangle.length;

            for (int i = 1; i < height; i++) {
                int width = triangle[i].length;
                for (int j = 0; j < width; j++) {
                    int target = triangle[i][j];
                    int topLeft = topLeft(triangle, i, j);
                    int topRight = topRight(triangle, i, j);
                    if (topLeft != -1 && topRight != -1) {
                        triangle[i][j] = Math.max(target + topLeft, target + topRight);
                    } else if (topLeft == -1) {
                        triangle[i][j] = target + topRight;
                    } else {
                        triangle[i][j] = target + topLeft;
                    }
                    answer = Math.max(answer, triangle[i][j]);
                }
            }

            return answer;
        }

        private int topLeft(int[][] triangle, int i, int j) {
            int top = i - 1;
            int left = j - 1;
            return left < 0 ? -1 : triangle[top][left];
        }

        private int topRight(int[][] triangle, int i, int j) {
            int top = i - 1;
            return j >= triangle[top].length ? -1 : triangle[top][j];
        }

    }
}
