import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow;
import java.util.stream.Stream;

public class App {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
//    long start = System.nanoTime();
//    List<Boolean> list = IntStream.range(0, 11).parallel().mapToObj(v -> test()).toList();
//    long duration = (System.nanoTime() - start) / 1_000_000;
//    System.out.println(duration);

//    IntStream.range(0, 10).
//        map(v -> {
//          System.out.printf("map) %d\n", v);
//          return v;
//        })
//        .filter(v -> {
//          System.out.printf("filter) %d\n", v);
//          return true;
//        })
//        .toArray();

    test2();
  }

  private static boolean test() {
    try {
      Thread.sleep(1_000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println("hello");
    return true;
  }

  private static void test2() {
    System.out.println("----------------DECLARED----------------");
    CompletableFuture[] array = Stream.of("nike", "adidas", "newbalance")
        .map((shop) -> {
          System.out.println("map 1");
          return CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync, " + shop);
            return requestToAServer(shop);
          });
        })
        .map(future -> {
          System.out.println("map 2");
          return future.thenApply(result -> {
            System.out.println("thenApply, " + result);
            return "checked, " + result;
          });
        })
        .map(future -> {
              System.out.println("map 3");
              return future.thenCompose(result -> {
                System.out.println("thenCompose, " + result);
                return CompletableFuture.supplyAsync(() -> requestToBServer(result));
              });
            }
        ).toArray(CompletableFuture[]::new);
    try {
      Thread.sleep(10_000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
//    System.out.println("-----------------BLOCKED-----------------");
//    List<String> result = list.stream().map(CompletableFuture::join).toList();
//    System.out.println("-------------------END-------------------");
//    for (String s : result) {
//      System.out.println(s);
//    }
  }

  private static String requestToAServer(String shop) {
    try {
      System.out.println("request To A/" + shop);
      Thread.sleep(4_000);
      System.out.println("response From A/" + shop);
      return "A Success(" + shop + ")";
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private static String requestToBServer(String shop) {
    try {
      System.out.println("request To B/" + shop);
      Thread.sleep(3_000);
      System.out.println("response From B/" + shop);
      return "B Success(" + shop + ")";
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }


}
