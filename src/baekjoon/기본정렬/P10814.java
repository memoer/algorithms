package baekjoon.기본정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P10814 {
  static class Person {
    public int age;
    public String name;

    public Person(int age, String name) {
      this.age = age;
      this.name = name;
    }

    @Override
    public String toString() {
      return this.age + " " + this.name;
    }
  }

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.valueOf(br.readLine());
    Person[] personArr = new Person[N];
    for (int i = 0; i < N; i++) {
      String[] input = br.readLine().split(" ");
      personArr[i] = new Person(Integer.valueOf(input[0]), input[1]);
    }
    // 이미 가입 순서대로 되어 있음
    Arrays.sort(personArr, (pre, cur) -> pre.age - cur.age);
    for (Person p : personArr) {
      bw.write(p + "\n");
    }
    br.close();
    bw.flush();
    bw.close();
  }
}
