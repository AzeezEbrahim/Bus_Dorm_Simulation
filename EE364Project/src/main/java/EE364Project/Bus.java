package EE364Project;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 12/9/2021
 * This class is for creating busses with all specifications required
 * and for performing some related procedures such as loading students, 
 * sending the bus and so on.
 * 
 * @author Ali Almousa + Ahmed Alhkyal
 *
 */
public class Bus implements Cloneable{
	
	//--------------------Bus Variables----------------------
	
	/**
	 * Number of trips the bus made
	 */
	private int numberTrips;
	/**
	 * Distance the bus has traveled
	 */
	private double distanceKm;	
	/**
	 * Fuel the bus has consumed
	 */
	private double fuelConsumption;
	/**
	 * Indicate if the bus is available to make a trip
	 */
	private boolean available;
	/**
	 * Seat capacity of the bus
	 */
	private int capacity;		
	/**
	 * Time bus is available at
	 */
	private int avalAt;				
	/**
	 * Bus ID
	 */
	private int ID;				
	/**
	 * Array of trips made by bus
	 */
	public ArrayList<Flight> tripsArray = new ArrayList<>();		 
	/**
	 * Array of student in the flight
	 */
	public ArrayList<Student> flightStudents = new ArrayList<>();	 
	/**
	 * Time the bus Arrived at campus
	 */
	private int campusArrival;	
	/**
	 * Time the bus left the dorms
	 */
	private int dormDeparture;	
	/**
	 * Time the bus will leave dorms 
	 */
	private int scheduledDormDeparture;								 
	/**
	 * Array of students delivered to campus
	 */
	private ArrayList<Student> studentsDelivered = new ArrayList<>(); 
	
	/**
	 * A constructor for creating a bus with two specifications determined by the caller
	 * 
	 * @param scheduledDormDeparture is the time this bus has to move to campus 
	 * @param ID is the unique identifier of the bus
	 */
	public Bus(int scheduledDormDeparture, int ID) {
		this.setAvailable(true);
		this.setCapacity(10);
		this.setID(ID);
		this.setScheduledDormDeparture(scheduledDormDeparture);
	}
	
	/**
	 * 
	 * @return all students delivered by this bus in an ArrayList
	 */
	public ArrayList<Student> getStudentsDelivered() {
		return studentsDelivered;
	}
	/**
	 * 
	 * @param ArrayList of delivered students
	 */
	public void setStudentsDelivered(ArrayList<Student> studentsDelivered) {

		this.studentsDelivered = studentsDelivered;
	}
	/**
	 * 
	 * @param Student to be added to the list of delivered students 
	 */
	public void addStudentsDelivered(Student S) {
		this.studentsDelivered.add(S);
	}
	/**
	 * 
	 * @return the ID of theis bus 
	 */
	public int getID() {
		return ID;
	}
	/**
	 * 
	 * @param ID to be set for this bus 
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
	/**
	 * 
	 * @return the Scheduled time the bus has to move 
	 */
	public int getScheduledDormDeparture() {
		return scheduledDormDeparture;
	}

	/**
	 * 
	 * @param the Scheduled time the bus has to move 
	 */
	public void setScheduledDormDeparture(int scheduledDormDeparture) {
		//if scheduledDormDeparture equals to or greater than 10:00pm then set this bus as unavailable  
		if(scheduledDormDeparture >= 960) {
			this.setAvailable(false);
		}
		this.scheduledDormDeparture = scheduledDormDeparture;
	}
	/**
	 * 
	 * @return number of trips the bus has made so far 
	 */
	public int getNumberTrips() {
		return numberTrips;
	}
	/**
	 * 
	 * @param number of trips
	 */
	public void setNumberTrips(int numberTrips) {
		this.numberTrips = numberTrips;
	}
	/**
	 * 
	 * @return the distance in KM this bus has covered do far
	 */
	public double getDistanceKm() {
		return distanceKm;
	}
	/**
	 * 
	 * @param the distance in KM this bus has covered do far
	 */
	public void setDistanceKm(double distanceKm) {
		this.distanceKm = distanceKm;
	}
	/**
	 * 
	 * @return the fuel this bus has consumed so far
	 */
	public double getFuelConsumption() {
		return fuelConsumption;
	}
	/**
	 * 
	 * @param the fuel this bus has consumed so far
	 */
	public void setFuelConsumption(double fuelConsumption) {
		this.fuelConsumption = fuelConsumption;
	}
	/**
	 * 
	 * @return the availability of the bus
	 */
	public boolean isAvailable() {
		return available;
	}
	/**
	 * 
	 * @param the availability of the bus
	 */
	public void setAvailable(boolean aval) {
		this.available = aval;
	}
	/**
	 * 
	 * @return the seat capacity of this bus
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * 
	 * @param the seat capacity of this bus
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	/**
	 * 
	 * @return the ArrayList of the trips this bus has made
	 */
	public ArrayList<Flight> getTripsArray() {
		return tripsArray;
	}
	/**
	 * 
	 * @return the time the bus will arrive to the campus in minutes form the reference time (starting hour)
	 */
	public int getCampusArrival() {
		return campusArrival;
	}
	/**
	 * 
	 * @param the time this bus has arrived to campus in minutes form the reference time (starting hour)
	 */
	public void setCampusArrival(int mins) {
		this.campusArrival = mins;
	}
	/**
	 * 
	 * @return the time this bus has moved to campus in minutes form the reference time (starting hour)
	 */
	public int getDormDeparture() {
		return dormDeparture;
	}
	/**
	 * 
	 * @param the time this bus has moved to campus in minutes form the reference time (starting hour)
	 */
	public void setDormDeparture(int mins) {
		this.dormDeparture = mins;
	}
	/**
	 * 
	 * @return ArrayList of students in the current flight
	 */
	public ArrayList<Student> getFlightStudents() {
		return flightStudents;
	}
	/**
	 * 
	 * @return the availability of the bus
	 */
	public int getAvalAt() {
		return avalAt;
	}
	/**
	 * 
	 * @param the time (in minutes) in which the bus will be available 
	 */
	public void setAvalAt(int avalAt) {
		this.avalAt = avalAt;
	}
	
	//-------------------Methods------------------------
	/**
	 * Add a trip to the array of trips & update attribute of the bus (Polymorphism)
	 * 
	 * @param trip
	 */
	public void addTripsArray( Flight trip ) {
		this.tripsArray.add(trip);
		// setting cumulative attributes
		this.setDistanceKm(this.getDistanceKm() + 2 * trip.getDISTANCE_TO_KAU());
		this.setFuelConsumption(trip.getFUEL_TO_KAU() + this.getFuelConsumption());
		this.setNumberTrips(this.getNumberTrips() + 1);
	}
	/**
	 * 
	 * @param A student to be added to the flight array holding student currently delivered to campus
	 */
	public void addFlightStudents(Student student) {
		this.flightStudents.add(student);
	}
	/**
	 * Load the student into flight and adjust number of available seats 
	 * 
	 * @param s is a student riding the bus
	 */
	public void loadStudent(Student s) {
		// reduce available seats by one
		this.setCapacity(this.getCapacity() - 1);
		//update the availability after decrementing the capacity of the bus
		if (this.getCapacity() == 0) {
			this.setAvailable(false);
		}
		// add the student to the current flightStudents 
		// this array will be cleared when the bus is sent
		this.addFlightStudents(s);
	}
	/**
	 * Check & update available time of the bus
	 * 
	 * @param mins is the real time at this moment
	 */
	public void checkAval(int mins) {
		// if there is a time set for available then update the availability accordingly
		if(this.getAvalAt() != 0)
		this.setAvailable(mins >= this.getAvalAt());
	}
	/**
	 * Send the bus to campus and conclude the flight data and update all students data 
	 * 
	 * @param days is the day this sending is taken place at 
	 * @param tempStudents a temporary ArrayList of students in the whole day 
	 * @param flightReport ArrayList of ArrayList of flights holding all data of flights and students and busses in it
	 * @param studentsALl ArrayList of the whole students in this day with updated data
	 * @param flightsAll ArrayList of flights holding all flights this bus had made
	 */
	public void sendBus(int days, ArrayList<Student> tempStudents, ArrayList<ArrayList<Flight>> flightReport, ArrayList<Student> studentsALl, ArrayList<Flight> flightsAll) {
		//create an object of the calling bus
		Bus callingBus = this;
		
		//create a new flight to be launched and add it to tripsArray
		Flight currentFlight = new RegularFlight();
		
		//set the busUsed attribute of the current flight
		currentFlight.setBusUsed(callingBus);
		
		//add the current flight to the trip array
		callingBus.addTripsArray(currentFlight);
		
		//update the bus data
		Updater.updateBusData(callingBus, currentFlight);
		
		//update the data of the new flight that about to be launched
		Updater.updateFlightData(callingBus, currentFlight);
		
		//make students in this flight miss or catch
		Updater.updateMissCatch(callingBus, currentFlight, tempStudents, studentsALl);
		
		//print the launched flight data along with the students in it
		Updater.updateFlightReport(days, currentFlight, flightReport, flightsAll);
		
		// clear the attribute flightStudents to fill it with the students of the next flight
		callingBus.getFlightStudents().clear();
	}
	
	
	
	/**
	 * Send the bus to campus and conclude the flight data and update all students data 
	 * 
	 * @param days is the day this sending is taken place at 
	 * @param tempStudents a temporary ArrayList of students in the whole day 
	 * @param flightReport ArrayList of ArrayList of flights holding all data of flights and students and busses in it
	 * @param studentsALl ArrayList of the whole students in this day with updated data
	 * @param flightsAll ArrayList of flights holding all flights this bus had made
	 */
	public void sendBus(int days, ArrayList<Student> tempStudents, ArrayList<ArrayList<Flight>> flightReport, ArrayList<Student> studentsALl, ArrayList<Flight> flightsAll, boolean x) {
		//create an object of the calling bus
		Bus callingBus = this;
		
		//create a new flight to be launched and add it to tripsArray
		Flight currentFlight = new RegularFlight();
		
		//set the busUsed attribute of the current flight
		currentFlight.setBusUsed(callingBus);
		
		//add the current flight to the trip array
		callingBus.addTripsArray(currentFlight);
		
		//update the bus data
		Updater.updateBusData(callingBus, currentFlight);
		
		//update the data of the new flight that about to be launched
		Updater.updateFlightData(callingBus, currentFlight);
		
		//make students in this flight miss or catch
		Updater.updateMissCatch(callingBus, currentFlight, tempStudents, studentsALl);
		
		//print the launched flight data along with the students in it
		Updater.updateFlightReport(days, currentFlight, flightReport, flightsAll);
		
		// clear the attribute flightStudents to fill it with the students of the next flight
		callingBus.getFlightStudents().clear();
	}
	/**
	 * deep copying the this bus
	 */
	@Override
    public Object clone() throws CloneNotSupportedException{
        // Assign the shallow copy to new reference variable
        Bus CB = (Bus)super.clone();
  
        CB.setStudentsDelivered(new ArrayList<>());
        for(Student s : this.getStudentsDelivered()) {
        	CB.addStudentsDelivered((Student)s.clone());
        }
        
        CB.tripsArray = new ArrayList<>();
        for(Flight f : this.tripsArray) {
        	CB.tripsArray.add(f);
        }
  
        // Create a new object for the field c
        // and assign it to shallow copy obtained,
        // to make it a deep copy
        return CB;
    }
    /**
     * String representation of bus moving to campus at declared time
     */
	public String toString() {
		return "{Bus Moving at: " + this.getScheduledDormDeparture() + "mins}";
	}
	
	//---------------------Inner Classes---------------------------
	/**
	 * an inner class that is used to update data after concluding the flight
	 * @author Ahmed 
	 *
	 */
	class Updater {
		/**
		 * Updating the departing bus' data
		 * 
		 * @param callingBus the current bus moving
		 * @param currentFlight the flight that is being made currently
		 */
		public static void updateBusData(Bus callingBus, Flight currentFlight) {
			//setting the bus as unavailable
			callingBus.setAvailable(false);
			//setting the bus available time to current time + commute time to campus
			callingBus.setAvalAt(Time.clock  + 2*currentFlight.getMINUTES_TO_KAU());
			//setting the new schedule for the bus to make a new trip
//			if (callingBus.getScheduledDormDeparture() == Time.clock)
				callingBus.setScheduledDormDeparture(Time.clock  + 2*currentFlight.getMINUTES_TO_KAU() + 30);
//			else
//				callingBus.setScheduledDormDeparture(Time.clock  + 2*currentFlight.getMINUTES_TO_KAU() + (callingBus.getScheduledDormDeparture() - Time.clock));
			//updating the time bus departed the dorms
			callingBus.setDormDeparture(Time.clock);
			//updating the time the bus arrived at campus
			callingBus.setCampusArrival(Time.clock + currentFlight.getMINUTES_TO_KAU());
			//updating the capacity of the bus (all seats are available)
			callingBus.setCapacity(10);
		}
		
		/**
		 * Updating the departing bus' data
		 * 
		 * @param callingBus the current bus moving
		 * @param currentFlight the flight that is being made currently
		 */
		public static void updateBusData(Bus callingBus, Flight currentFlight, boolean x) {
			//setting the bus as unavailable
			callingBus.setAvailable(false);
			//setting the bus available time to current time + commute time to campus
			callingBus.setAvalAt(Time.clock  + 2*currentFlight.getMINUTES_TO_KAU());
			//setting the new schedule for the bus to make a new trip
			if (callingBus.getScheduledDormDeparture() == Time.clock)
				callingBus.setScheduledDormDeparture(Time.clock  + 2*currentFlight.getMINUTES_TO_KAU() + 30);
			else
				callingBus.setScheduledDormDeparture(Time.clock  + 2*currentFlight.getMINUTES_TO_KAU() + (callingBus.getScheduledDormDeparture() - Time.clock));
			//updating the time bus departed the dorms
			callingBus.setDormDeparture(Time.clock);
			//updating the time the bus arrived at campus
			callingBus.setCampusArrival(Time.clock + currentFlight.getMINUTES_TO_KAU());
			//updating the capacity of the bus (all seats are available)
			callingBus.setCapacity(10);
		}
		/**
		 * update the data of the flight that about to be launched
		 * 
		 * @param callingBus the current bus moving
		 * @param currentFlight the flight that is being made currently
		 */
		public static void updateFlightData(Bus callingBuss, Flight currentFlight) {
			//setting the time that the flight has departed the dorms
			currentFlight.setTimeOfDeparture(callingBuss.getDormDeparture());
			//setting the time that the flight has arrived at campus
			currentFlight.setTimeOfArrival(callingBuss.getCampusArrival());
		}
		/**
		 * Update the condition of students in the flight to whether they caught or missed their lecture
		 */
		public static void updateMissCatch(Bus callingBus, Flight currentFlight, ArrayList<Student> tempStudents, ArrayList<Student> studentsALl) {
			int numStudetns = callingBus.getFlightStudents().size(); // number of students in the flight
			ArrayList<Student> S = new ArrayList<>();
			// Loop for adding students in the flight to a clone array...
			for(Student stu : callingBus.getFlightStudents()) {
				try {
				S.add((Student)stu.clone());
				}catch(CloneNotSupportedException e) {
					System.out.println(e.getMessage());
				}
			}
			// Loop for updating the catch condition...
			for (int i = 0; i < numStudetns; i++) {
				Student student = callingBus.getFlightStudents().get(i);
				student.setIsCatch(callingBus.getCampusArrival() <= student.getIntendedArrivalTime());
				if (callingBus.getCampusArrival() <= student.getIntendedArrivalTime()) currentFlight.incCatches();
				tempStudents.add(student);
				studentsALl.add(student);
				callingBus.getStudentsDelivered().add(student);
				currentFlight.studentsInTrip.add(student);
			}
		}
		/**
		 * Update the launched flight data
		 * 
		 * @param days is the day this sending is taken place at 
		 * @param currentFlight the flight that is being made currently
		 * @param flightReport ArrayList of ArrayList of flights holding all data of flights and students and busses in it
		 * @param flightsAll ArrayList of flights holding all flights this bus had made
		 */
		public static void updateFlightReport(int days, Flight currentFlight, ArrayList<ArrayList<Flight>> flightReport, ArrayList<Flight> flightsAll) {
			int catches = currentFlight.getCatches(); // number of student caught their lecture
			int misses = currentFlight.studentsInTrip.size() - currentFlight.getCatches(); // number of student missed their lecture
			//string representation of percentage of students caught their lecture in the flight
			String perCC = String.format("%.2f", 100*((double)(catches) / (catches+misses))) + "%";
			currentFlight.setCatchesPer(perCC);				//setting the catch %
			flightsAll.add(currentFlight);					//adding the departed flight to all flight array
			flightReport.get(days - 1).add(currentFlight);	//adding the flight to reports array
		}
		
	}
	
}


