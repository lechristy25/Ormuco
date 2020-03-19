import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * 
 * @author Lenoy Christy
 * This program takes in two version numbers as inputs and 
 * evaluates whether one is greater than, lesser than, or equal to the other
 *
 */
public class VersionComparison {
	
	/**
	 * main: Prompts user input and passes it to a parsing method to obtain an integer array representation. 
	 * 		 Makes method calls to evaluate the relation between the two versions.
	 *       Prints out this relation. 
	 * 
	 */
	public static void main(String[] args) {
		
		//prompt for user input of first version
		Scanner version1Input = new Scanner(System.in);
		System.out.print("Enter the first version: "); 
		String version1 = version1Input.nextLine(); 
		int[] v1 = parseVersion(version1); //method call to parse user input into int array representation
		

		//prompt for user input of second version
		Scanner version2Input = new Scanner(System.in);
		System.out.print("Enter the second version: "); 
		String version2 = version2Input.nextLine(); 
		int[] v2 = parseVersion(version2); //method call to parse user input into int array representation
		
		
		//Makes method call to compare the two versions, and prints out statement depending on the returned value of the called method
		switch(compareVersions(v1,v2)) {
		case 0:
			System.out.println("version "+version1 +" and version "+ version2 + " are equal.");
			break;
		case 1:
			System.out.println("version " +version1 + " is greater than version " + version2 + ".");
			break;
		case 2: 
			System.out.println("version "+version1 + " is less than version " + version2 + ".");
			break;
		}
		
		}
	
	
	/**
	 * parseVersion: Parses  the user's string input into an int array representation
	 * @param version - String representation of the version number
	 * @return int array representation of the version number
	 */
	public static int[] parseVersion(String version) {
		StringTokenizer st = new StringTokenizer(version, "."); //separate user inputs into tokens
		int[] intVersion = new int[st.countTokens()];
		int index = 0;
		while(st.hasMoreTokens()) {
			intVersion[index] = Integer.parseInt(st.nextToken());
			index++;
		}
		return intVersion;
	}
	
	/**
	 * compareVersions: compares two int array representations of version numbers to determine the relation between them
	 * @param v1 - the first version
	 * @param v2 - the second version
	 * @return a single integer representation of the relation between the two versions. A result of 0 implies that the two versions are equal. 
	 * 		   1 implies that the first version is greater than the second. 2 implies that the first version is lesser than the second. 
	 */
	
	public static int compareVersions (int[] v1, int[] v2) {
		int result = 0;
		if (v1.length == v2.length) { // case where both versions are equal in length 
			for (int i = 0; i < v2.length; i++) 
				if(v1[i] > v2[i]) {
					result = 1;
					return result;
				}
				else if (v1[i] < v2 [i]) {
					result = 2;
					return result;
				}
			
		}
		else if (v1.length < v2.length) { // case where version 1 is shorter in length than version 2
			for (int i = 0; i < v1.length; i++) // compare the two versions up to the last number in version 1
				if(v1[i] > v2[i]) {
					result = 1;
					return result;
					
				}
				else if (v1[i] < v2 [i]) {
					result = 2;
					return result;	
				}
			result = 2; // if both versions are equal up to the last number in version 1, then version 2 is assumed to be greater
			
		}
		else { // case where version 2 is shorter in length than version 1
			for (int i = 0; i < v2.length; i++) // compare the two versions up to the last number in version 2
				if(v1[i] > v2[i]) {
					result = 1;
					return result; 
					
				}
				else if (v1[i] < v2 [i]) {
					result = 2;
					return result;	
				}
			result = 1; // if both versions are equal up to the last number in version 2, then version 1 is assumed to be greater 
		}
		return result;
	}
	
}

// ~~~~~ TEST CASES ~~~~~~~~//
// First Version: 1.2. Second Version: 1.1. Output: "version 1.2 is greater than version 1.1"
// First Version: 1.1. Second Version: 1.12. Output: "version 1.1 is less than version 1.12"
// First Version: 1.1. Second Version: 1.1. Output: "version 1.1 and version 1.1 are equal"
// First version: 1.1.1. Second Version: 1.1.1.1. Output: "version 1.1.1 is less than version 1.1.1.1"
// First version: 1.1.1.1. Second Version: 1.1.1. Output: "version 1.1.1.1 is greater than version 1.1.1"
// First version: 10.1. Second Version: 9.2.1.1. Output: "version 10.1 is greater than version 9.2.1.1" 
// First version: 9.2.1.1. Second Version: 10.1. Output: "version 9.2.1.1 is less than version 10.1"
