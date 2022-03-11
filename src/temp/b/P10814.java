package temp.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P10814 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    final int N = Integer.parseInt(br.readLine());
    Person[] list = new Person[N];
    for (int i = 0; i < N; i++) {
      String[] s = br.readLine().split(" ");
      list[i] = new Person(Integer.parseInt(s[0]), s[1]);
    }
    Arrays.sort(list, (pre, cur) -> pre.age - cur.age);
    for (Person p : list) {
      bw.write(p + "\n");
    }
    bw.flush();
    bw.close();
    br.close();
  }

  static class Person {
    public int age;
    public String name;

    public Person(int age, String name) {
      this.age = age;
      this.name = name;
    }

    @Override
    public String toString() {
      return age + " " + name;
    }
  }
}
