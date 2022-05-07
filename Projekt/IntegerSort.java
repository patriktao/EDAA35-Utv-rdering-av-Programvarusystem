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
			outputWriter.write("numberOfIterations,mergeSort,heapSort\n");

			// Input Reader
			int[] array = readIntegers(new Scanner(new FileReader(inputFile)));

			for (int i = 1; i < iterations; i++) {
				// HeapSort
				HeapSortClass heapSort = new HeapSortClass();
				long heapStartTime = System.nanoTime();
				heapSort.run(array);
				long heapSortTime = System.nanoTime() - heapStartTime;

				// MergeSort
				MergeSortClass mergeSort = new MergeSortClass();
				long mergeStartTime = System.nanoTime();
				mergeSort.run(array, 0, array.length - 1);
				long mergeSortTime = System.nanoTime() - mergeStartTime;

				// Write to file
				outputWriter.append(i + "," + mergeSortTime + "," + heapSortTime + "\n");
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

class HeapSortClass {

	public void run(int[] array) {
		int[] arr = array;
		int n = arr.length;

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, n, i);

		// One by one extract an element from heap
		for (int i = n - 1; i > 0; i--) {
			// Move current root to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			// call max heapify on the reduced heap
			heapify(arr, i, 0);
		}
	}

	// To heapify a subtree rooted with node i which is
	// an index in arr[]. n is size of heap
	void heapify(int arr[], int n, int i) {
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && arr[l] > arr[largest])
			largest = l;

		// If right child is larger than largest so far
		if (r < n && arr[r] > arr[largest])
			largest = r;

		// If largest is not root
		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(arr, n, largest);
		}
	}
}

class MergeSortClass {

	public int[] run(int[] arr, int l, int r) {
		int[] array = arr;
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
