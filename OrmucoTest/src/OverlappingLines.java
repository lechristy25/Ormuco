import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Lenoy Christy
 * This program takes in two 1D lines as inputs and evaluates whether
 * or not they overlap
 *
 */

public class OverlappingLines {
	
	/**
	 * main: Prompts user input and parses through it to separate the coordinates. 
	 * 		 Makes method calls to format the lines and evaluate if they overlap.
	 *       Prints out whether the two lines overlap or not. 
	 * 
	 */
	
	public static void main(String[] args) {
		Scanner lineInputs = new Scanner(System.in);
		System.out.print("Enter two lines with format (x1,x2),(x3,x4): "); //prompt for user input
		String lines = lineInputs.nextLine(); 
		StringTokenizer st = new StringTokenizer(lines, "(,)"); //separate user inputs into tokens
		int[] line1 = new int[2];
		int[] line2 = new int[2];
		
		//parse the tokens into ints, and store them as coordinates in their respective arrays.
        while (st.hasMoreTokens()) {
            for(int i = 0; i<2; i++) line1[i] = Integer.parseInt(st.nextToken()); 
            for(int i = 0; i<2; i++) line2[i] = Integer.parseInt(st.nextToken()); 
        }
        
        //format the arrays so that the smaller coordinate appears first, check for negative inputs.
        minMaxFormatting(line1);
        minMaxFormatting(line2);
        
        //call to overlap method, print out result of evaluation
        if(overlap(line1, line2)) System.out.println("The two lines overlap!");
        else System.out.println("The two lines don't overlap!");
      
    }
        
	/**
	 * minMaxFormatting: Formats the array so that the smaller coordinate appears first. 
	 * 					 Checks for negative inputs 
	 * @param coords - array of coordinates
	 * @return formatted array of coordinates
	 */
	  public static int[] minMaxFormatting(int[] coords) {
		  	if (coords[0] < 0 || coords[1] < 0) 
		  		throw new IllegalArgumentException("Coordinates cannot be negative");
		  	else if (coords[0] > coords[1]) {
	    		int temp = coords[1];
	    		coords[1] = coords[0]; 
	    		coords[0] = temp;
	    		return coords;
	    	}
	    	else return coords;
    
	}
	  
	/**
	 * overlap: evaluate whether or not two lines overlap
	 * @param l1 - array representing the first line
	 * @param l2 - array representing the second line
	 * @return boolean, true if lines overlap, false if they don't
	 */
	  
	  public static boolean overlap(int[] l1, int[] l2) {
		  if(l1[0] < l2[0]) {
			  if(l1[1] >= l2[0]) return true;
			  else return false;
		  }
		  else { 
			  if(l2[1] >= l1[0]) return true;
			  else return false;
		  }
	  }
}


