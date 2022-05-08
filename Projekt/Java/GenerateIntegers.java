import java.lang.Math;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateIntegers {

    public static void main(String[] args) {
        new GenerateIntegers().run(args);
    }

    public void run(String[] args) {
        File output = new File(args[0]);
        int iterations = Integer.parseInt(args[1]);
        try {
            BufferedWriter outputWriter = new BufferedWriter(new FileWriter(output));
            if (args[2].equals("random")) {
	            for (int i = 0; i < iterations; i++) {
	                int r = getRandomNumber(0, 10000);
	                outputWriter.append(r + "\n");
	            }
            }
            else if (args[2].equals("semi-random")) {
            	if (args[3].equals("reverse")) {
            		for (int i = iterations; i > 0; i--) {
            			int r = getSemiRandomNumber(i, iterations);
    	                outputWriter.append(r + "\n");
            		}
            	}
            	else if (args[3].equals("ordered")){
            		for (int i = 0; i < iterations; i++) {
            			int r = getSemiRandomNumber(i, iterations);
    	                outputWriter.append(r + "\n");
            		}
            	}
            }
            else if (args[2].equals("sorted")) {
            	if (args[3].equals("reverse")) {
            		for (int i = iterations; i > 0; i--) {
    	                outputWriter.append(i + "\n");
            		}
            	}
            	else if (args[3].equals("ordered")){
            		for (int i = 0; i < iterations; i++) {
    	                outputWriter.append(i + "\n");
            		}
            	}
            }
            outputWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int getSemiRandomNumber(int n, int iterations) {
    	if (n < 50) {
    		return getRandomNumber(0, n + 50);
    	}
    	else if (n > iterations - 50) {
    		return getRandomNumber(n - 50, iterations);
    	}
    	else {
    		return getRandomNumber(n - 50, n + 50);
    	}
    }
}