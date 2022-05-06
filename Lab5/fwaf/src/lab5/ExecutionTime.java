package lab5;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.LinkedList;

public class ExecutionTime {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Random rand = new Random();
		LinkedList<Integer> nums = new LinkedList<Integer>();
		File input = new File(System.getProperty("user.dir") +  "\\" + args[3]);
		File output = new File(System.getProperty("user.dir") + "\\" + args[4]);
		BufferedWriter inputWriter = new BufferedWriter(new FileWriter(input));
		inputWriter.write("");
		for(int i = 0; i < 1000; i++) {
			inputWriter.append(String.valueOf(rand.nextInt(1000)) + "\n");
		}
		inputWriter.close();
		try {
			BufferedReader inputReader = new BufferedReader(new FileReader(input));
			for (String line = inputReader.readLine(); line != null; line = inputReader.readLine()) {
				nums.add(Integer.valueOf(line));
			}
			inputReader.close();
		} catch (FileNotFoundException e) {
			System.out.println(System.getProperty("user.dir") +  "\\" + args[3]);
			System.out.println("filen hittades inte lmao");
		}
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter(output));
		outputWriter.write("numberOfIterations,javaSortingAlgorithm,mySortingAlgorithm\n");
		for (int i = 1; i <= Integer.valueOf(args[5]); i ++){
			LinkedList<Integer> numsToSort = nums;
			long myStartTime = System.nanoTime();
			ListSorter mySorter = new ListSorter(numsToSort);
			mySorter.sort();
			long myDif = System.nanoTime() - myStartTime;
			long standardStartTime = System.nanoTime();
			numsToSort.sort((n1, n2) -> n1.compareTo(n2));
			long standardDif = System.nanoTime() - standardStartTime;
			outputWriter.append(i + "," + standardDif + "," + myDif + "\n");
		}
		outputWriter.close();
	}
}
