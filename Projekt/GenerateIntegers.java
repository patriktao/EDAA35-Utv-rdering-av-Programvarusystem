import java.lang.Math;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateIntegers {

    public static void main(String[] args) {
        new GenerateIntegers().run(Integer.parseInt(args[0]));
    }

    public void run(int iterations) {
        File output = new File("integer_data");
        try {
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(output));
            for (int i = 0; i < iterations; i++) {
                int r = getRandomNumber(0, 10000);
                outputWriter.append(r + "\n");
            }
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}