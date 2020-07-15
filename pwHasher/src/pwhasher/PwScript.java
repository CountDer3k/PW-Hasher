package pwhasher;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
 
public class PwScript {
	
	static Scanner myObj = new Scanner(System.in);

	public static void main(String[] args) {
		while(true) {
		//	loopedProgram();
			println("Times to loop: ");
			String line = myObj.nextLine();
			int n = Integer.parseInt(line);
			loopedProgramX(n);
		}
	}
	
	public static void loopedProgramX(int n) {
		println("Input your string");
		// Take user string
		String input = myObj.nextLine();
		String output = "";
		
		int option = -1;
		// ask if hash/unhash
		while (option == -1) {
			println("What would you like to do with that string? \n0= Hash string \n1= Unhash string");
			option = takeInputLine();
		}
		
		
		if(option == 0) {
			// Hash the string
			println("Hashing: " + input);
			printTime("Hashing");
			output = hashThis(input);
			for(int i = 0; i < n ; i++) {
				output = hashThis(output);
			}
			println("Hashing Complete. \nKey is: "+ n + output);
			
		}
		else if(option == 1) {
			// Unhash the string
			println("Unhashing: " + input);
			printTime("UnHashing");
			input = input.substring(1,input.length());
			output = unhashThis(input);
			for(int i =0; i < n; i++) {
				output = unhashThis(output);
			}
			println("Unhashing Complete. \nKey is: " + output);
		}
		println("------------------------");
		println("");
	}

	//--------------------------
	// Hashing Functions START
	//--------------------------
	public static String hashThis(String s) {
		String hashArr[] = new String[s.length()];
		String tempString = "";
		String newString = "";
		
		// Stores string into an array as ASCII number of each char
		for(int i = 0; i < s.length(); i++) {
			// Convert to ascii number
			String tempS = letterToASCIINum(s,i);
			// Store in array
			hashArr[i]= tempS;
		}			
		

		// Switches array Positions
		for(int k = 0; k < hashArr.length/2; k++) {
			String temp = hashArr[k];
			hashArr[k] = hashArr[(hashArr.length-1) - k];
			hashArr[(hashArr.length-1) - k] = temp;
			
		}
		
		// Revert every 3rd item to ascii
		for(int i =0; i < hashArr.length; i++) {
			if(i%3 == 0) {
				int asciiAsInt = Integer.parseInt(hashArr[i]);
				try {
					asciiAsInt = asciiAsInt + 3;
				}
				catch(Exception e){
					printTime("Went too high. Fixing.");
				}
				hashArr[i] = "" + (char)asciiAsInt;
			}
		}
		
		
		// convert array back to string
		for(int l = 0; l < hashArr.length; l++) {
			tempString += hashArr[l];
		}
		

		newString = tempString;
		return newString;
	}
	
	public static String unhashThis(String s) {
		ArrayList<String> hashArrL = new ArrayList<String>();
		String tempString = "";
		String newString = "";

		
		for(int i = 0; i < s.length(); i++) {
			if(i%7==0) {
				int c = (int)s.charAt(i);
				c = c - 3;
				hashArrL.add((char)c +"");
			}
			else {
				char a1 = s.charAt(i);
				char a2 = s.charAt(i+1);
				char a3 = s.charAt(i+2);
				String temp = ""+ a1+a2+a3;
				int tempS = Integer.parseInt(temp);
				i = i+2;
				hashArrL.add((char)tempS+"");
			}
		}
		String hashArr[] = new String[hashArrL.size()];
		
		for(int i = 0; i< hashArrL.size(); i++) {
			hashArr[i] = hashArrL.get(i);
		}

		
		// Switches array Positions
		for(int k = 0; k < hashArr.length/2; k++) {
			String temp = hashArr[k];
			hashArr[k] = hashArr[(hashArr.length-1) - k];
			hashArr[(hashArr.length-1) - k] = temp;
		}
		
		
		// convert array back to string
		for(int l = 0; l < hashArr.length; l++) {
			tempString += hashArr[l];
		}
				

		newString = tempString;
		return newString;
	}
	//--------------------------
	// Hashing Functions END
	//--------------------------	
	
	
	
	//--------------------------
	// Printing Functions START
	//--------------------------
	public static void printf(String s) {
		System.out.print(s);
	}
	
	public static void println(String s) {
		System.out.println(s);
	}
	
	public static void printTime(String s) {

		printf(s + "..");
		Sleep(1);
		printf(".");
		Sleep(1);
		printf(".");
		Sleep(1);
		printf(".");
		Sleep(1);
		println(".");
	}
	//--------------------------
	// Printing Functions END
	//--------------------------	
	
	//----------------------
	// Misc. Functions START
	//----------------------
	
	public static void Sleep(int n) {
		try {
			TimeUnit.SECONDS.sleep(n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int takeInputLine() {
		// Read user input (or file input if redirected)
		String input = myObj.nextLine();
		try {
			int inputInt = Integer.parseInt(input);
			if(inputInt == 0 || inputInt == 1) {
				return inputInt;
			}
			else {
				return -1;
			}	
		}
		// Happens if the input is a string/non-integer
		catch (Exception e){
			return -1;
		}
	}
	
	public static String letterToASCIINum(String s, int i) {
		
		int c = (int)s.charAt(i);
		return addPadding(c);
	}
	
	public static String addPadding(int s) {
		int count = String.valueOf(s).length();
		
		// Adds 0's to make length 3 for each tempS
		if(count == 2) {
			return "0"+s;
		}
		else if (count == 1) {
			return "00"+s;
		}
		else {
			return String.valueOf(s);
		}
	}
	
	//----------------------
	// Misc. Functions END
	//----------------------
}
