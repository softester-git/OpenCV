import java.io.IOException;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        int[] array = new int[3];
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            array[i] = (int) (Math.random() * 101);
            System.out.println("Number: " + array[i]);
            System.out.println(i);
        }
        sum = Arrays.stream(array).sum();

        System.out.println("Average: " + sum);
    }
}
