import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class TestMaker {
    public static void createTest(int n) {
        try {
            PrintWriter writer = new PrintWriter("program3data.txt", StandardCharsets.UTF_8);
            writer.println(n);
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                writer.println("n" + (i + 1));
            }
            HashMap<Integer, HashSet<Integer>> done = new HashMap<Integer, HashSet<Integer>>();
            for (int i = 0; i < n / 2; i++) {
                done.put(i + 1, new HashSet<Integer>());
            }
            //int m = rand.nextInt(n * n) + 1;
            writer.println(500);
            int from = rand.nextInt(n / 2) + 1;
            int to = rand.nextInt(n / 2) + 1 + (n / 2);
            for (int i = 0; i < 500; i++) {
                while (done.get(from).contains(to)) {
                    from = rand.nextInt(n / 2) + 1;
                    to = rand.nextInt(n / 2) + 1 + (n / 2);
                }
                done.get(from).add(to);
                writer.println(from + " " + to);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
