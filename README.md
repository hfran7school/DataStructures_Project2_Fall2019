# DataStructures_Project2_Fall2019
COP 3538: Project 2 -- Stacks and Priority Queues
 * @author Hailey Francis (n01402670@unf.edu)
 * @version September 27, 2019
 
Project2.java
 * This class creates 5 priority queues using Countries2.csv
   file and the Priority.java, then removes the countries from
  their priority queues into a stack, using Stack.java.
 * The program first creates Country objects one at a time from the .csv
   file, and then based on the GDP per capita of that Country, it is
   placed into one of 5 priority queues. It is placed in the POOR priority queue
   if its < 1000, FAIR if it's 1000 <= Country < 5000, GOOD if it's 5000 <= Country < 20,000
   VGOOD if it's 20,000 <= Country < 50,000, and EXCELLENT if it's 50,000 or greater.  
   The priority queues are sorted by the higher the GDP per capita, the higher the priority.
   
Stack.java
 * This class is used to create a stack
   for Project2.java. It has methods to push and pop
   Countries to and from the stack, as well as a method
   to print the stack. It also has methods to identify if
   the stack is full and if the stack is empty.
   
Priority.java
 * This method is used to create priority queues
   for Project2.java. It has insert and remove methods,
   as well as methods to check whether or not the priority
   queue is empty and if it is full. 
 * The higher the GDP per capita, the higher the priority.
 
 Country.java
 * This class is to make Country objects for Project1.java,
   and is now being used for Project 2. It includes getter and setter methods, 
   as well as a method that calculates the GDP, a method that compares Country
   objects by their names, and a method that prints the information
   about a Country object.
 * This is the same Country.java as seen in hfran7school/DataStructures_Project1_Fall2019
