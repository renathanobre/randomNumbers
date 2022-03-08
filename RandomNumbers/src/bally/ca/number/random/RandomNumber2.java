package bally.ca.number.random;
import java.util.Arrays;
import java.util.Random; // gererate random numbers with this library
import java.io.*;

public class RandomNumber2 {

	private static final int MAX_RANDOM_NUMBERS = 30;// 1000000; 

	public static void main(String[] args) throws IOException {
		//Challenge 1
		saveRandomNumberInTextFile ();
		//Challenge 2
		Merge m1 = new Merge();  
		int[] randonNumberList = GetRandonNumber();
	//	int[] sortedRandonNumber = new int[MAX_RANDOM_NUMBERS];
		int[][] chunkArray= splitArray(randonNumberList, 10);
		for(int i =0; i< chunkArray.length; i++) {			
			m1.mergeSort(chunkArray[i], 0, chunkArray[i].length-1); 
		}	
		for(int i = 1; i< chunkArray.length; i++) {	
		
		}
//		int leftIndex = 0;
//		int rightIndex = 0;
//		int lastSortedValue = 0;
//		int firstItem = 0;
//		for(int i = 1; i< chunkArray.length; i++) {	
//			firstItem= chunkArray[i][rightIndex];
//		
//			for(int j = 0; j< chunkArray[i-1].length; j++) {
//				if(chunkArray[i-1][j] < firstItem) {
//					sortedRandonNumber[leftIndex] = chunkArray[i-1][j];
//					leftIndex++;
//				}else {
//					sortedRandonNumber[leftIndex] = firstItem;
//					
//					rightIndex++;
//					firstItem= chunkArray[i][rightIndex];
//				}
//				lastSortedValue  = sortedRandonNumber[leftIndex];
//			}
//		}
		

		//m1.mergeSort(randonNumberList, 0, MAX_RANDOM_NUMBERS-1); 
		printArray(randonNumberList,randonNumberList.length);

	}

	private static void saveRandomNumberInTextFile () throws IOException {
		Random rnd = new Random();
		BufferedWriter writer = new BufferedWriter(new FileWriter("randomNumbers .txt"));
		for (int i = 0 ;i < MAX_RANDOM_NUMBERS; i++ ) {
			int rand = rnd.nextInt();
			writer.write(rand+"\n");
		}
		writer.close();
	}

	private static int[] GetRandonNumber(){
		Random rnd = new Random();
		int[]  randonNumberList = new int[MAX_RANDOM_NUMBERS];
		for (int i = 0 ;i < MAX_RANDOM_NUMBERS; i++ ) {			
			randonNumberList[i] = rnd.nextInt(MAX_RANDOM_NUMBERS);
		}
		return randonNumberList;
	} 

	/* Function to print the array */  
	private static void  printArray(int a[], int n)  
	{  

		for (int i = 0; i < n; i++)  
			System.out.print(a[i] + " ");  
	}  

	private static int[][] splitArray(int[] arrayToSplit, int chunkSize){
		if(chunkSize<=0){
			return null;  // just in case :)
		}
		// first we have to check if the array can be split in multiple 
		// arrays of equal 'chunk' size
		int rest = arrayToSplit.length % chunkSize;  // if rest>0 then our last array will have less elements than the others 
		// then we check in how many arrays we can split our input array
		int chunks = arrayToSplit.length / chunkSize + (rest > 0 ? 1 : 0); // we may have to add an additional array for the 'rest'
		// now we know how many arrays we need and create our result array
		int[][] arrays = new int[chunks][];
		// we create our resulting arrays by copying the corresponding 
		// part from the input array. If we have a rest (rest>0), then
		// the last array will have less elements than the others. This 
		// needs to be handled separately, so we iterate 1 times less.
		for(int i = 0; i < (rest > 0 ? chunks - 1 : chunks); i++){
			// this copies 'chunk' times 'chunkSize' elements into a new array
			arrays[i] = Arrays.copyOfRange(arrayToSplit, i * chunkSize, i * chunkSize + chunkSize);
		}
		if(rest > 0){ // only when we have a rest
			// we copy the remaining elements into the last chunk
			arrays[chunks - 1] = Arrays.copyOfRange(arrayToSplit, (chunks - 1) * chunkSize, (chunks - 1) * chunkSize + rest);
		}
		return arrays; // that's it
	}
}
