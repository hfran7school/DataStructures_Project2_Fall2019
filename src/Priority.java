/**
 * This method is used to create priority queues
 * for Project2.java. It has insert and remove methods,
 * as well as methods to check whether or not the priority
 * queue is empty and if it is full. 
 * <p>
 * The higher the GDP per capita, the higher the priority.
 * 
 * @author Hailey Francis
 * @version September 27, 2019
 *
 */
public class Priority {

	private int maxSize; //maximum size of priority queue
	private Country[] priority; //priority queue array
	private int numItems; //number of items in priority queue
	
	/**
	 * -- CONSTRUCTOR --
	 * @param size -- maximum size of priority queue array
	 */
	public Priority(int size) {
		
		maxSize = size;
		priority = new Country[size];
		numItems = 0;
	}
	
	/**
	 * -- INSERT METHOD --
	 * (Priority based on GDP per capita: higher GDP per capita,
	 * higher priority)
	 * 
	 * @param newCountry -- new Country being inserted into priority queue
	 */
	public void insert(Country newCountry) {
		int i;
		if(this.isFull()) {
			System.out.println(newCountry + " not inserted because priority queue is full.");
		}else {
			if(numItems == 0) {
				priority[1] = newCountry;
			}else {
				for(i = numItems - 1; i >= 0; i--) {
					if(priority[i] != null) {
						if(newCountry.calcGDPperCapita() < priority[i].calcGDPperCapita()) {
							priority[i+1] = priority[i];
						}else {
							break;
						}
					}
					
				}
				priority[i+1] = newCountry;
			}
		}
		numItems++;
	}
	
	/**
	 * -- REMOVE METHOD --
	 * (removes Country with highest priority, aka Country at end of
	 * priority queue array)
	 * 
	 * @return Country that was removed from priority queue
	 */
	public Country remove() {
		if(!(this.isEmpty())) {
			return priority[--numItems];
		}else {
			System.out.println("This priority queue is empty. A blank Country is return.");
			Country blank = new Country("","","", 0, 0.0, 0);
			return blank;
		}
	}
	
	/**
	 * Prints priority queue contents, similar to how Project1.java printed
	 * a Country array.
	 */
	public void printQueue() {
		System.out.println("\nName                              Code                Capital                  Population                GDP                           HappinessRank ");
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------ ");
		for(int i = 0; i < maxSize; i++) {
			if(priority[i] != null) {
				System.out.printf("%-35s", priority[i].getName());
				System.out.printf("%-20s", priority[i].getCode());
				System.out.printf("%-25s", priority[i].getCapital());
				System.out.printf("%-25s", priority[i].getPopulation());
				System.out.printf("%-30s", priority[i].getGDP());
				System.out.println(priority[i].getRank());
				System.out.println("GDP per capita: " + priority[i].calcGDPperCapita());
			}
		}
		System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------ ");
		System.out.println();
	}
	
	/**
	 * determines whether or not
	 * the priority queue is empty by whether or
	 * not numItems == 0
	 * 
	 * @return true if the priority queue if empty, false if the priority queue isn't empty
	 */
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	/**
	 * determines whether or not
	 * the priority queue is full by whether or not 
	 * numItems == maxSize
	 * 
	 * @return true if the priority queue is full, false if the priority queue isn't full
	 */
	public boolean isFull() {
		return (numItems == maxSize);
	}

	/**
	 * gets and returns the number of items in the priority
	 * queue
	 * @return the number of items in the priority queue
	 */
	public int getNumItems() {
		return numItems;
	}
}
