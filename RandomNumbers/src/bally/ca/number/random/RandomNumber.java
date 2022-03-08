package bally.ca.number.random;

import java.util.Random; // gererate random numbers with this library
import java.io.*;

public class RandomNumber {

	private static final int MAX_RANDOM_NUMBERS = 1000000; 

	public static void main(String[] args) throws IOException {
		//Challenge 1
		saveRandomNumberInTextFile ();

	}

   //Java BufferedWriter class is used to provide buffering for Writer instances. It makes the performance fast. It inherits Writer class
	
	private static void saveRandomNumberInTextFile () throws IOException {
		Random rnd = new Random();
		BufferedWriter writer = new BufferedWriter(new FileWriter("randomNumbersC1.txt"));
		for (int i = 0 ;i < MAX_RANDOM_NUMBERS; i++ ) {
			int rand = rnd.nextInt(MAX_RANDOM_NUMBERS);
			writer.write(rand+"\n");
		}
		writer.close();
	}
}
