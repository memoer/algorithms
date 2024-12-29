package programmers.Lv1;

public class P250137 {

    public static void main(String[] args) {
        int[] bandage = {5, 1, 5};
        int health = 30;
        int[][] attacks = {
            {2, 10}, {9, 15}, {10, 5}, {11, 5}
        };
        int solution = new Solution().solution(bandage, health, attacks);
        System.out.println(solution);
    }

    private static class Solution {

        public int solution(int[] bandage, int health, int[][] attacks) {
            int hp = health;
            int time = 0;
            for (int[] attack : attacks) {
                int seconds = attack[0];
                int damage = attack[1];

                int diff = seconds - time - 1;
                int total = (diff * bandage[1]) + (diff / bandage[0] * bandage[2]);
                hp = Math.min(hp + total, health) - damage;
                if (hp <= 0) {
                    return -1;
                }
                time = seconds;
            }
            return hp;
        }
    }
}

//20

// 6 + 5 = 11