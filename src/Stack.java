/**
 * This class is used to create a stack
 * for Project2.java. It has methods to push and pop
 * Countries to and from the stack, as well as a method
 * to print the stack. It also has methods to identify if
 * the stack is full and if the stack is empty.
 * 
 * @author Hailey Francis (n01402670)
 * @version September 27, 2019
 */
public class Stack {

	private int maxSize; //maximum size of array for stack
	private Country[] stack; //stack array
	private int top; //top of stack
	
	/**
	 * -- CONSTRUCTOR --
	 * @param size -- maximum size of stack array
	 */
	public Stack(int size) {
		
		maxSize = size;
		stack = new Country[size];
		top = -1;
	}
	
	/**
	 * --PUSH METHOD--
	 * (takes a new country and puts it on the stack)
	 * @param newCountry -- new Country pushed onto stack
	 */
	public void push(Country newCountry){
		if(!(this.isFull())){
			stack[++top] = newCountry;
		}else {
			System.out.println("Stack full. Country not inserted.");
		}
	}
	
	/**
	 * -- POP METHOD --
	 * (removes item on top of the stack, aka the last element put in the stack array,
	 * and returns the Country that was popped)
	 * @return Country popped off of stack
	 */
	public Country pop() {
		if(!(this.isEmpty())) {
			return stack[top--];
		}else {
			System.out.println("Nothing to pop. Blank country returned.");
			return new Country("","","", 0, 0.0, 0);
		}
		
	}
	
	/**
	 * Prints stack contents, similar to how Project1.java printed
	 * a Country array.
	 */
	public void printStack() {
		System.out.println("\nName                              Code                Capital                  Population                GDP                           HappinessRank ");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------ ");
		for(int i = 0; i < maxSize; i++) {
			if(stack[i] != null) {
				System.out.printf("%-35s", stack[i].getName());
				System.out.printf("%-20s", stack[i].getCode());
				System.out.printf("%-25s", stack[i].getCapital());
				System.out.printf("%-25s", stack[i].getPopulation());
				System.out.printf("%-30s", stack[i].getGDP());
				System.out.println(stack[i].getRank());
				System.out.println(stack[i].calcGDPperCapita());
			}
		}
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------ ");
		System.out.println();
	}
	
	/**
	 * determines whether or not
	 * the stack is empty by whether or
	 * not the top of the array == -1
	 * 
	 * @return true if the stack if empty, false if the stack isn't empty
	 */
	public boolean isEmpty() {
		return (top == -1);
	}
	
	/**
	 * determines whether or not
	 * the stack is full by whether or not 
	 * the top of the array == maxSize - 1
	 * (the index of the last element of the stack array)
	 * 
	 * @return true if stack is full, false if the stack isn't full
	 */
	public boolean isFull() {
		return (top == maxSize - 1);
	}
}
