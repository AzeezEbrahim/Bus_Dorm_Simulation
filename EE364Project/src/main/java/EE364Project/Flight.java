package EE364Project;

import java.util.ArrayList;
/**
 * An abstract class that provide all common attributes and methods of all types of flight 
 * 
 * @author Azeez + Ahmed 
 *
 */
public abstract class Flight {
	
	//--------------------Flight Variables----------------------
	/**
	 * Number of flights
	 */
	public static int numFlights;
	/**
	 * Type of flight (Phase2)
	 */
	public String typeOfFilght;	
	/**
	 * Time of flight departure
	 */
	public int timeOfDeparture;   
	/**
	 * Time of flight arrival
	 */
	public int timeOfArrival;	  
	/**
	 * Object of bus being used
	 */
	public Bus busUsed;			  
	/**
	 * Number of students caught their lecture in flight
	 */
	public int catches;			
	/**
	 * Percentage of students caught their lecture in flight
	 */
	public String catchesPer;     
	/**
	 * Array of students in flight
	 */
	ArrayList<Student> studentsInTrip = new ArrayList<Student>(); 
	
	//--------------Setters & Getters-------------------
	/**
	 * 
	 * @return Number of students caught their lecture in flight
	 */
	public int getCatches() {
		return catches;
	}
	/**
	 * increment number of catches by 1
	 */
	public void incCatches() {
		this.catches++;
	}
	/**
	 * 
	 * @return Number of students caught their lecture in flight
	 */
	public String getCatchesPer() {
		return catchesPer;
	}
	/**
	 *  
	 * @param catchesPer percentage of students caught their lecture in flight
	 */
	public void setCatchesPer(String catchesPer) {
		this.catchesPer = catchesPer;
	}
	/**
	 * 
	 * @return ID of the bus
	 */
	public Bus getBusUsed() {
		return busUsed;
	}
	/**
	 * 
	 * @param busUsed the bus used to make this flight
	 */
	public void setBusUsed(Bus busUsed) {
		this.busUsed = busUsed;
	}
	/**
	 * 
	 * @return integer representing the number of flights have been taken in the whole day 
	 */
	public static int getNumFlights() {
		return numFlights;
	}
	/**
	 * 
	 * @param numFlights integer representing the number of flights have been taken in the whole day
	 */
	public static void setNumFlights(int numFlights) {
		Flight.numFlights = numFlights;
	}
	/**
	 * increment number of flights in this day by 1
	 */
	public static void incNumFlights() {
		Flight.numFlights++;
	}
	/**
	 * 
	 * @return String representation of the type of this flight 
	 */
	public String getTypeOfFilght() {
		return typeOfFilght;
	}
	/**
	 * 
	 * @param typeOfFilght the type of this flight 
	 */
	public void setTypeOfFilght(String typeOfFilght) {
		this.typeOfFilght = typeOfFilght; 
	};
	
	// Abstract Setters & Getters
	/**
	 * 
	 * @return Time of flight departure
	 */
	public abstract int getTimeOfDeparture();
	/**
	 * 
	 * @param timeOfDeparture Time of flight departure
	 */
	public abstract void setTimeOfDeparture(int timeOfDeparture);
	/**
	 * 
	 * @return Time of flight arrival
	 */
	public abstract int getTimeOfArrival();
	/**
	 * 
	 * @param timeOfArrival Time of flight arrival
	 */
	public abstract void setTimeOfArrival(int timeOfArrival);
	/**
	 * 
	 * @return the distance this flight cover to arrive at the campus 
	 */
	public abstract double getDISTANCE_TO_KAU();
	/**
	 * 
	 * @return the minutes this flight take to arrive at the campus 
	 */
	public abstract int getMINUTES_TO_KAU();
	/**
	 * 
	 * @return the fuel this flight consume to arrive at the campus 
	 */
	public abstract int getFUEL_TO_KAU();
	
	//-------------------Methods------------------------
	/**
	 * Print flight information
	 */
	public String toString() {
		// get the flight number
		int numFlight = Flight.getNumFlights();
		// get the time of departure and arrival of the flight
		String TimeDep = Time.MinutesToTime(this.getTimeOfDeparture());
		String TimeArr = Time.MinutesToTime(this.getTimeOfArrival());
		
		// get the type of flight
		String type = this.getTypeOfFilght();
		// get number of students in the flight
		int numS = this.studentsInTrip.size();
		boolean isFull = numS==10; // check if flight is at max capacity
		
		// loop for getting catch state
		int c = 0;
		for(Student S : this.studentsInTrip) {
			if(S.isCatch) c++;
		}
		// number of students missed the lecture (reminder of people didn't catch of total) 
		int misses = numS - c;
		// percentage of student caught their lecture
		String perCC = String.format("%.2f", 100*((double)(c) / (c+misses))) + "%";
		// All data of flight
		String a = String.format("Students: %d\t Moved At: %s\t\t Arrived At: %s\t Catches: %d\t Misses: %d\t\t Catch%%: %s\t Flight Type: %s\t BusId: %d\t", 
				numS, TimeDep, TimeArr, c, misses, perCC, type, busUsed.getID());
		return a;
	}
}