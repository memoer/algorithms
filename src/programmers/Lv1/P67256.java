package programmers.Lv1;

public class P67256 {

    static class Solution {

        public String solution(int[] numbers, String hand) {
            final char LEFT = 'L';
            final char RIGHT = 'R';
            int[] left = {3, 0};
            int[] right = {3, 2};
            StringBuilder sb = new StringBuilder();

            for (int n : numbers) {
                if (n == 1 || n == 4 || n == 7) {
                    sb.append(LEFT);
                    left[0] = row(n);
                    left[1] = 0;
                } else if (n == 3 || n == 6 || n == 9) {
                    sb.append(RIGHT);
                    right[0] = row(n);
                    right[1] = 2;
                } else {
                    int row = row(n);
                    int col = col(n);
                    int v1 = Math.abs(row - left[0]) + Math.abs(col - left[1]);
                    int v2 = Math.abs(row - right[0]) + Math.abs(col - right[1]);
                    if (v1 < v2) {
                        sb.append(LEFT);
                        left[0] = row;
                        left[1] = col;
                    } else if (v1 > v2) {
                        sb.append(RIGHT);
                        right[0] = row;
                        right[1] = col;
                    } else if (hand.equals("right")) {
                        sb.append(RIGHT);
                        right[0] = row;
                        right[1] = col;
                    } else {
                        sb.append(LEFT);
                        left[0] = row;
                        left[1] = col;
                    }
                }
            }

            return sb.toString();
        }

        private int row(int n) {
            return switch (n) {
                case 1, 2, 3 -> 0;
                case 4, 5, 6 -> 1;
                case 7, 8, 9 -> 2;
                case 0 -> 3;
                default -> throw new IllegalStateException("Unexpected value: " + n);
            };
        }

        private int col(int n) {
            return switch (n) {
                case 1, 4, 7 -> 0;
                case 2, 5, 8, 0 -> 1;
                case 3, 6, 9 -> 2;
                default -> throw new IllegalStateException("Unexpected value: " + n);
            };
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        System.out.println(new Solution().solution(numbers, hand));
    }
}

