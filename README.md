### [Notion 기록 Link](https://jnam.notion.site/3a57997df12848f093fb434e7fef4c4c)

- 취약한 알고리즘
  1. 동적 프로그래밍
  2. 이분 탐색
  3. 탐욕

# 못 풀었던 문제들
### 백준
1. 탐색
   - [1766]문제집 -->> 1[Fail] / 2[Fail]
     - 위상정렬을 사용할 것
     - 문제를 연달아 풀 필요 없음 "2-4,3-1,5-6" 일 경우, "2-3-1-4-5-6"이 정답임
   - [2250]트리의 높이와 너비 -->> 1[Fail] / 2[OK]
     - 노드가 1개일 경우를 생각해야 한다
2. 그래프
   - [5719]거의 최단 경로 -->> 1[OK] / 2[Fail]
      - BFS를 통해 경로 역 추적할 시, 메모리 초과 뜬다.
      - 백트래킹으로 역추적해야 메모리 초과 안뜬다.
      - **다익스트라 탐색시, 경로를 미리 저장해놓고 탐색**
3. 탐욕
4. DP
    - [11053]가장 긴 증가하는 부분 수열 -->> 1[OK] /
    - [9251]LCS -->> 1[OK] /
    - [1495]기타리스트 -->> 1[OK] /
    - [2655]가장높은탑쌓기 -->> 1[OK] /

### 프로그래머스
1. Lv2
   - [62048] 멀쩡한 사각형
   - [72412] 순위검색
   - [60057] 문자열 압축 -->> 1[OK] /
   - [72411] 메뉴 리뉴얼 -->>
   - [42586] 기능개발 -->>
   - [81032] 거리 두기 확인하기 -->>

### LeetCode

1. [410__D20220501_3] Split Array Largest Sum -->>
2. [278__D20220501_1] First Bad Version -->>

# 복기한 문제들

### 백준
1. 탐색
   - ~~[1715]카드 정렬하기 -->> 1[OK]~~
   - ~~[1939]중량제한 -->> 1[OK] / 2[OK]~~
     - bfs를 활용하여 시작 - 끝 공장이 연결되었는 지 확인한다.
     - 프림으로도 충분히 통과시킬 수 있다.
   - ~~[2110]공유기 설치 -->> 1[OK] / 1[OK]~~
     - 문제 설명이 너무 이상해...
   - ~~[1991]트리순회 -->> 1[OK] / 2[OK]~~
2. 그래프
    - ~~[1774]우주신과의 교감 -->> 1[OK] / 1[OK]~~
      - 크루스칼 알고리즘 사용
3. 탐욕
    - ~~[1781]컵라면 -->> 1[OK] / 2[OK]~~
    - ~~[1461]도서관 -->> 1[OK] / 2[OK]~~
4. DP
    - ~~[1904]01타일 -->> 1[OK]~~
    - ~~[12865]평범한 배낭 -->> 1[Fail] / 2[OK]~~
### 프로그래머스
1. Lv1
   - ~~[86491]최소직사각형 ->> 1[OK]~~
