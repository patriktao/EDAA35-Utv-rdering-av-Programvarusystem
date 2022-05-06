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
		LinkedList<Integer> nums = new LinkedList<Integer>();
		File input = new File("/Users/patriktao/EDAA35/"  + args[0]);
		File output = new File("/Users/patriktao/EDAA35/"  + args[1]);
		try {
			//Input Reader
			BufferedReader inputReader = new BufferedReader(new FileReader(input));
			for (String line = inputReader.readLine(); line != null; line = inputReader.readLine()) {
				nums.add(Integer.valueOf(line));
			}
			inputReader.close();
			//Output Writer
			BufferedWriter outputWriter = new BufferedWriter(new FileWriter(output));
			outputWriter.write("numberOfIterations,javaSortingAlgorithm,mySortingAlgorithm\n");
			for (int i = 1; i <= Integer.valueOf(args[2]); i ++){
				LinkedList<Integer> numsToSort = nums;
				//My Sort
				long myStartTime = System.nanoTime();
				ListSorter mySorter = new ListSorter(numsToSort);
				mySorter.sort();
				long myDif = System.nanoTime() - myStartTime;
				//Java sort
				long standardStartTime = System.nanoTime();
				numsToSort.sort((n1, n2) -> n1.compareTo(n2));
				long standardDif = System.nanoTime() - standardStartTime;
				//Output time
				outputWriter.append(i + "," + standardDif + "," + myDif + "\n");
			}
			outputWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println(System.getProperty("user.dir") +  "\\" + args[3]);
			System.out.println("Input filen hittades inte.");
		}
	}
}