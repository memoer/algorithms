package programmers.Lv2;

public class P81302 {

    public static void main(String[] args) {
        String[][] places = {
            {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
            {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
            {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
            {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
            {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        int[] solution = new Solution().solution(places);
        for (int i : solution) {
            System.out.print(i + ", ");
        }
    }

    private static class Solution {

        private final int LENGTH = 5;
        private final char WALL = 'X';
        private final char PERSON = 'P';
        private final char EMPTY = 'O';
        private String[][] places;

        public int[] solution(String[][] places) {
            this.places = places;

            int[] answers = new int[this.LENGTH];
            for (int i = 0; i < this.LENGTH; i++) {
                boolean passed = true;

                for (int y = 0; y < this.LENGTH; y++) {
                    String rows = places[i][y];

                    for (int x = 0; x < this.LENGTH; x++) {
                        if (rows.charAt(x) != this.PERSON) {
                            continue;
                        }

                        if (!isAvailable(i, x, y, rows)) {
                            passed = false;
                            break;
                        }
                    }
                    if (!passed) {
                        break;
                    }
                }
                answers[i] = passed ? 1 : 0;
            }
            return answers;
        }

        private boolean isAvailable(int i, int x, int y, String rows) {
            int nextY = y + 1;

            int preX = x - 1;
            if (preX >= 0) {
                if (rows.charAt(preX) == this.PERSON) {
                    return false;
                } else if (rows.charAt(preX) == this.EMPTY) {
                    int ppreX = preX - 1;
                    if (ppreX >= 0 && rows.charAt(ppreX) == this.PERSON) {
                        return false;
                    }
                }
                if (nextY < this.LENGTH && this.places[i][nextY].charAt(preX) == this.PERSON) {
                    return rows.charAt(preX) == this.WALL && this.places[i][nextY].charAt(x) == this.WALL;
                }
            }

            int nextX = x + 1;
            if (nextX < this.LENGTH) {
                if (rows.charAt(nextX) == this.PERSON) {
                    return false;
                } else if (rows.charAt(nextX) == this.EMPTY) {
                    int nnextX = nextX + 1;
                    if (nnextX < this.LENGTH && rows.charAt(nnextX) == this.PERSON) {
                        return false;
                    }
                }
                if (nextY < this.LENGTH && this.places[i][nextY].charAt(nextX) == this.PERSON) {
                    return rows.charAt(nextX) == this.WALL && this.places[i][nextY].charAt(x) == this.WALL;
                }
            }

            if (nextY >= this.LENGTH) {
                return true;
            } else if (this.places[i][nextY].charAt(x) == this.PERSON) {
                return false;
            } else if (this.places[i][nextY].charAt(x) == this.EMPTY) {
                int nnextY = nextY + 1;
                if (nnextY < this.LENGTH && this.places[i][nnextY].charAt(x) == this.PERSON) {
                    return false;
                }
            }
            return true;
        }

    }
}
