import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BipartiteMatching {

    public void initializeData(String fileName) {
        File file = new File(fileName);
        try {
            Scanner in = new Scanner(file);
            int n = in.nextInt();

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }
}
