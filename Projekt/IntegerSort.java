import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class IntegerSort {

	public static void main(String[] args) throws IOException {
		new IntegerSort().run(args);
	}

	public void run(String[] args) throws NumberFormatException, IOException {
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		int iterations = Integer.valueOf(args[2]);
		try {
			// Output Writer
			BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFile));
			outputWriter.write("numberOfIterations,mergeSort,quickSort\n");

			// Input Reader
			int[] array = readIntegers(new Scanner(new FileReader(inputFile)));

			for (int i = 1; i <= iterations; i++) {
				// QuickSort
				QuickSortClass quickSort = new QuickSortClass();
				long quickStartTime = System.nanoTime();
				quickSort.run(array, 0, array.length - 1);
				long quickSortTime = System.nanoTime() - quickStartTime;

				// MergeSort
				MergeSortClass mergeSort = new MergeSortClass();
				long mergeStartTime = System.nanoTime();
				mergeSort.run(array, 0, array.length - 1);
				long mergeSortTime = System.nanoTime() - mergeStartTime;

				// Write to file
				outputWriter.append(i + "," + mergeSortTime + "," + quickSortTime + "\n");
			}
			outputWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
		}
	}

	public int[] readIntegers(Scanner scan) {
		List<Integer> list = new ArrayList<>();
		while (scan.hasNextInt()) {
			list.add(scan.nextInt());
		}
		return list.stream().mapToInt(i -> i).toArray();
	}
}

class QuickSortClass {

	private void swap(int[] array, int i, int j) {
		int[]arr = array.clone();
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private int partition(int[] arr, int low, int high) {

		int pivot = arr[high];
		int i = (low - 1);

		for (int j = low; j <= high - 1; j++) {

			if (arr[j] < pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, high);
		return (i + 1);
	}

	public void run(int[] arr, int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			run(arr, low, pi - 1);
			run(arr, pi + 1, high);
		}
	}
}

class MergeSortClass {

	public int[] run(int[] arr, int l, int r) {
		int[] array = arr.clone();
		mergeSort(array, l, r);
		return array;
	}

	// Main function that sorts arr[l..r] using
	// merge()
	void mergeSort(int[] arr, int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			run(arr, l, m);
			run(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}

	void merge(int arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarray array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}
}
