package bally.ca.number.random;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


/**
 * 
 * @author rvieira
 * 
 * I made a decision to use Mergesort algorithm, the time complexity is O(nLogn) time complexity
 * In case of big arrays I searched that I would need to implement a solution using chunkArrays
 *
 * kind of other sorting algorihtms : Buble sort / Selecting sort / Insertion Sort/ Merge Sort/ Quick Sort/ Heap Sort. Counting Sort. Radix Sort
 * parameters to classify  : time complexity (Some algorithms going to be faster)/
 *                 space complexity or memory usage (some algorithms use constant amount of extra memory to rearrange the elements in the list,while some
 *                 others like merge sort use extra memory to temporarily store data and  the memory usage growns with input size
 *                 and stability 
 *                 and recursive( quick sort/ merge sort) or non-recursive (insertion sort/ selecting sort)
 *                 
 *                 merge we first divide until get 1 element and then starts to conquer to start merge comparing the first element of each array and sorting 
 */


public class MergeSort {
	private static final int MAX_RANDOM_NUMBERS =  1000000; 

	public static void main(String[] args) throws IOException {

		Random rand = new Random();
		
		//if we try one billion - 1000000000 we can  see OutofMemory ERror exception in our machine
		int[] numbers = new int[MAX_RANDOM_NUMBERS];

		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = rand.nextInt(MAX_RANDOM_NUMBERS);
		}

		System.out.println("Creating randomNumbers.txt"); 
		saveSortedRandomNumberInTextFile(numbers,"randomNumbers.txt");

		//here we sort the numbers 
		mergeSort(numbers);     

		System.out.println("\nCreating sortedNumbers.txt");
		saveSortedRandomNumberInTextFile(numbers,"sortedNumbers.txt");
	}

	private static void saveSortedRandomNumberInTextFile ( int[] numbers, String fileName) throws IOException {

		//Java BufferedWriter class is used to provide buffering for Writer instances. It makes the performance fast. It inherits Writer class
		
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		for (int i = 0 ;i < MAX_RANDOM_NUMBERS; i++ ) {			 
			writer.write(numbers[i]+"\n");
		}
		
		
		
		
		writer.close();
	}

	//Here a recursive call is made, new storage locations for variables are allocated on the stack. 
	//As, each recursive call returns, the old variables and parameters 
	private static void mergeSort(int[] inputArray) {
		int inputLength = inputArray.length;

		//here we check if the is only 1 element on the array, if so this is already sorted before merging
		if (inputLength < 2) {
			return;
		}

		//here we will split the array in half to start order before merging
		int midIndex = inputLength / 2;

		//add one half on the left array
		int[] leftHalf = new int[midIndex];
		//add the other or rest of the array which we split in 2 
		int[] rightHalf = new int[inputLength - midIndex];

		for (int i = 0; i < midIndex; i++) {
			//copy the original array into the leftarray
			leftHalf[i] = inputArray[i];
		}

		for (int i = midIndex; i < inputLength; i++) {
			//copy the rest of the array 
			rightHalf[i - midIndex] = inputArray[i];
		}

		//we sort the half arrays. using the recursion which is the method calls itself	
		//we will call recursively until we get one element into the arrays, to then start conquer to  merge
		//if we dont have limit to stop the call itself we would see stackoverflow exception ,because would call the method forever 
		mergeSort(leftHalf);
		mergeSort(rightHalf);

		//we call the merge methodo which has the the two sorted arrays in one large sorted array    
		merge(inputArray, leftHalf, rightHalf);
	}


	private static void merge (int[] inputArray, int[] leftHalf, int[] rightHalf) {
		int leftSize = leftHalf.length;
		int rightSize = rightHalf.length;

		//we need three variable one for left array, other for right array which are sorted and other one for the merged array
		//we do te same line separating with coma just for clear code and because they are the same type we only need to declare on the first one
		int i = 0, j = 0, k = 0;

		//we loop until we run all elements ,
		while (i < leftSize && j < rightSize) {

			//here we garantee we are adding the lowest element on the largest/marged array
			if (leftHalf[i] <= rightHalf[j]) {
				inputArray[k] = leftHalf[i];
				i++;
			}
			//if not we know the right array has the lowest element than  the one from left array
			else {
				inputArray[k] = rightHalf[j];
				j++;
			}
			//we need to increment the merged array
			k++;
		}
		
		//we still need to clean up and add all the elements on the largest array,
		//if there is no elements to add it will pass by for the next piece of code
		//this way we garantee all elements are added to the inputArray - merged/largest array
		while (i < leftSize) {
			inputArray[k] = leftHalf[i];
			i++;
			k++;
		}
		while (j < rightSize) {
			inputArray[k] = rightHalf[j];
			j++;
			k++;
		}

	}
}
