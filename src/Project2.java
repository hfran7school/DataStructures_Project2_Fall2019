/**
 * COP 3538: Project 2 -- Stacks and Priority Queues
 * <p>
 * This class creates 5 priority queues using Countries2.csv
 * file and the Priority.java, then removes the countries from
 * their priority queues into a stack, using Stack.java.
 * <p>
 * The program first creates Country objects one at a time from the .csv
 * file, and then based on the GDP per capita of that Country, it is
 * placed into one of 5 priority queues. It is placed in the POOR priority queue
 * if its < 1000, FAIR if it's 1000 <= Country < 5000, GOOD if it's 5000 <= Country < 20,000
 * VGOOD if it's 20,000 <= Country < 50,000, and EXCELLENT if it's 50,000 or greater.  
 * The priority queues are sorted by the higher the GDP per capita, the higher the priority.
 * 
 * @author Hailey Francis (n01402670)
 * @version September 27, 2019
 *  
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Project2 {
	
	/**
	 * MAIN FUNCTION
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); //user input for fileName
		String fileName; //name of file given by user
		
		System.out.println("COP3538 Project 2");
		System.out.println("Instructor: Xudong Liu\n");
		
		System.out.println("Stacks and Queues");
		System.out.print("Enter the name of the file: ");
		fileName = input.next();
		input.close();
		
		// preventing NegativeSizeArray error if file not found //
		int maxSize = findSize(fileName);
		if(maxSize < 0) {
			maxSize = 0;
		}
		
		if(maxSize != 0) { //program will stop if the maxSize is 0
			// initialize priority queues //
			Priority POOR = new Priority(maxSize);
			Priority FAIR = new Priority(maxSize);
			Priority GOOD = new Priority(maxSize);
			Priority VGOOD = new Priority(maxSize);
			Priority EXCELLENT = new Priority(maxSize);
			
			readFile(fileName, POOR, FAIR, GOOD, VGOOD, EXCELLENT);  //add Countries to priority queues
			
			// Print out Priority queue contents //
			System.out.print("\nPOOR Priority Queue Contents: "); POOR.printQueue();
			System.out.print("FAIR Priority Queue Contents: "); FAIR.printQueue();
			System.out.print("GOOD Priority Queue Contents: "); GOOD.printQueue();
			System.out.print("VGOOD Priority Queue Contents: "); VGOOD.printQueue();
			System.out.print("EXCELLENT Priority Queue Contents: "); EXCELLENT.printQueue();
			
			// moving Countries from priority queues to stack //
			Stack stack = new Stack(maxSize);
			moveToStack(POOR, stack);
			moveToStack(FAIR, stack);
			moveToStack(GOOD, stack);
			moveToStack(VGOOD, stack);
			moveToStack(EXCELLENT, stack);
			
			System.out.print("Stack contents: "); stack.printStack(); //print stack contents
			
			
		}else {
			System.out.println("Since this file had no contents, (or there was no file to be found), the program has terminated.");
		}
	} //end main
	
	/**
	 * Finds the number of countries in the .csv file,
	 * using the delimiter to seperate lines, increasing
	 * int count by 1 every time it makes it to a new line,
	 * therefore a new country. It returns count - 1 to account
	 * for the first line of headers at the top of the file,
	 * and this value will be used as maxSize for the priority
	 * queues and stack. This is the same method of finding
	 * the size used in Project 1.
	 * 
	 * @param fileName name of file given by user
	 * @return int maxSize to be used in priority queues and stack
	 */
	public static int findSize(String fileName) {
		int count = 0;
		try { //every time there is a new line, count will increase by 1
			Scanner c = new Scanner(new File(fileName));
			c.useDelimiter("\r\n|\n");
			while(c.hasNext()) { 
				c.next();
				count++;
			}
			c.close();
			
		}
		catch(FileNotFoundException f) {
			System.out.println("File not found. "
					+ "Please make sure you entered the name of the file correctly "
					+ "and that the file is accessable the next time you run this program.\n"
					+ "(This project was submitted with two files called Countries1.csv and Countries2.csv.) [Error thrown by findSize method]");
		}
		return count - 1;
	} //end findSize
	
	/**
	 *  This method reads the .csv file, and creates a Country object from
	 *  each line in the file. It then takes that Country and adds it into one
	 *  of five different priority queues, based on its GDP per capita.
	 *  
	 * @param fileName -- name of file given by user
	 * @param poor -- POOR priority queue
	 * @param fair -- FAIR priority queue
	 * @param good -- GOOD priority queue
	 * @param vGood -- VGOOD priority queue
	 * @param excellent -- EXCELLENT priority queue
	 */
	public static void readFile(String fileName, Priority poor, Priority fair, Priority good, Priority vGood, Priority excellent) {
		String name, code, capital; 
		int population, rank;
		double GDP; // doubles will all be read as exponentials
		Scanner r;
		// OPENING FILE //
		try {
			r = new Scanner(new File(fileName));
			r.useDelimiter("\\,|\r\n|\n");
		    // READING FILE //
			for(int i = 0; i < 6; i++) { //meant to cycle through first line of file, which presumably is labels such as in Countries1.csv and Countries2.csv
				r.next();
			}
			while(r.hasNext()) { //assigns values from file to variables, then forming Country object added to priority queues
				name = r.next();
				code = r.next();
				capital = r.next();
				population = r.nextInt();
				GDP = r.nextDouble();
				rank = r.nextInt();
			
				Country temp = new Country(name, code, capital, population, GDP, rank);
				if(temp.calcGDPperCapita() < 1000) {
					poor.insert(temp);
				}else if(temp.calcGDPperCapita() >= 1000 && temp.calcGDPperCapita() < 5000) {
					fair.insert(temp);
				}else if(temp.calcGDPperCapita() >= 5000 && temp.calcGDPperCapita() < 20000) {
					good.insert(temp);
				}else if(temp.calcGDPperCapita() >= 20000 && temp.calcGDPperCapita() < 50000) {
					vGood.insert(temp);
				}else {
					excellent.insert(temp);
				}
			}
		r.close();
		}
		catch(InputMismatchException i) {
			System.out.println("Input Mismatch Exception. Program likely tried assigning value to wrong variable due to format of file.");
		}
		catch(NumberFormatException j) {
			System.out.println("Number format exception. Program likely tried assigning value to wrong variable due to format of file.");
		}catch(FileNotFoundException e) {
			System.out.println("File not found. "
					+ "Please make sure you entered the name of the file correctly "
					+ "and that the file is accessable the next time you run this program.\n"
					+ "(This project was submitted with a file called Countries2.csv.) [This error was thrown by the readFile method]");
		}
	} //end readFile
	
	/**
	 * This method removes Countries from a given priority queue and
	 * pushes the Countries onto the given stack until the priority
	 * queue is empty.
	 * 
	 * @param priority -- priority queue
	 * @param stack -- stack
	 */
	public static void moveToStack(Priority priority, Stack stack) {
		while(!(priority.isEmpty())) {
			Country temp = priority.remove();
			stack.push(temp);
		}
	} //end moveToStack
}
