package programmers.Lv2;

public class P12985 {
    // 1. 1번 ↔ 2번, 3번 ↔ 4번, ..., N-1번 ↔ N번의 참가자끼리 게임을 진행
    // 2. 다음 라운드에 진출할 참가자의 번호는 다시 1번부터 N/2번을 차례대로 배정받습니다
    //       만약 1번↔2번 끼리 겨루는 게임에서 2번이 승리했다면 다음 라운드에서 1번을 부여받고, 3번↔4번에서 겨루는 게임에서 3번이 승리했다면 다음 라운드에서 2번을 부여받게 됩니다.
    // 3. 최종 한 명이 남을 때까지 진행됩니다.
    // Q) 게임 참가자 수 N, 참가자 번호 A, 경쟁자 번호 B가 함수 solution의 매개변수로 주어질 때, 처음 라운드에서 A번을 가진 참가자는 경쟁자로 생각하는 B번 참가자와 몇 번째 라운드에서 만나는지 return 하는 solution 함수를 완성해 주세요.
    //       A번 참가자와 B번 참가자는 서로 붙게 되기 전까지 항상 이긴다고 가정합니다.
    static class Solution {
        public int solution(int n, int a, int b) {
            a-=1;
            b-=1;
            int answer = 1;
            while (a / 2 != b / 2) {
                a /= 2;
                b /= 2;
                answer += 1;
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        int n = 8, a = 4, b = 7;
        System.out.println(new Solution().solution(n, a, b));
    }
}
