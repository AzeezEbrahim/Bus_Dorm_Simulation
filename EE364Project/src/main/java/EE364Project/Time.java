package EE364Project;
/**
 * this class is mainly for providing a real clock for the whole flow of the simulation
 * convention: everything is in minutes with 6:00Am as
 * the starting reference an 10:00Pm as the last reference
 * 
 * @author Azeez + Ali + Ahmed
 *
 */

public class Time {
	
	//-----------------Time Variables-------------------
	/**
	 * Used as a global clock
	 */
	public static int clock;   
	/**
	 * Minutes passed according to global clock
	 */
	public int minutesElapsed; 
	/**
	 * Start of working hours
	 */
	public int startingHour;   
	/**
	 * End of working hours
	 */
	public int endingHour;     
	
	//----------------Time Constructors-----------------
	/**
	 * default Time constructor to create a time object with fixed attributes
	 */
	public Time() {
		this.setMinutesElapsed(0);
		this.setStartingHour(0);
		this.setEndingHour(960);
		Time.clock = this.getStartingHour();
	}
	/**
	 * Time constructor to create a time object with specified attributes
	 * 
	 * @param mins elapsed form the starting hour
	 */
	public Time(int mins) {
		this.setMinutesElapsed(mins);
		this.setStartingHour(6);
		this.setEndingHour(22);
		Time.clock = this.getStartingHour();
	}
	
	//--------------Setters & Getters-------------------
	/**
	 * 
	 * @return mins elapsed form the starting hour
	 */
	public int getMinutesElapsed() {
		return minutesElapsed;
	}
	/**
	 * 
	 * @param minutesElapsed Minutes passed according to global clock
	 */
	public void setMinutesElapsed(int minutesElapsed) {
		this.minutesElapsed = minutesElapsed;
	}
	/**
	 * 
	 * @return Start of working hours in minutes from the reference starting hour 
	 */
	public int getStartingHour() {
		return startingHour;
	}
	/**
	 * 
	 * @param startingHour Start of working hours in minutes from the reference starting hour 
	 */
	public void setStartingHour(int startingHour) {
		this.startingHour = startingHour;
	}
	/**
	 * 
	 * @return End of working hours in minutes from the reference starting hour 
	 */
	public int getEndingHour() {
		return endingHour;
	}
	/**
	 * 
	 * @param endingHour End of working hours in minutes from the reference starting hour
	 */
	public void setEndingHour(int endingHour) {

		this.endingHour = endingHour;
	}
	/**
	 * Static setter for the global clock given minutes as parameter
	 * 
	 * @param mins elapsed form the starting hour
	 */
	public static void setClock(int mins) {
			Time.clock = mins;
	}
	
	//-------------------Methods------------------------
	/**
	 * Increment global clock by 1 minute
	 */
	public static void incrementClock() {
		Time.clock++;
	}
	/**
	 * Increment global clock by given minutes elapsed
	 * 
	 * @param mins elapsed form the starting hour
	 */
	public static void incrementClock(int mins) {
		Time.clock = Time.clock + mins;
	}
	/**
	 * Reset the global clock to Starting Hour
	 */
	public void resetTime() {
		Time.clock = this.getStartingHour();
	}
	/**
	 * Convert the value of minutes elapsed of this object to a proper time representation (HH:MM AM/PM)
	 * 
	 * @return String of the time representation (HH:MM AM/PM)
	 */
	public String MinutesToTime() {
		String amPm = "AM";
		int hours = (this.getMinutesElapsed() / 60)  + 6; // represent minutes in terms of hours
		int minutes = this.getMinutesElapsed() % 60;	  // add reminding minutes
		// check whether AM or PM
		if (hours > 12) {
			hours -= 12;
			amPm = amPm.replace('A', 'P');
		}
		/**
		 * create a string representation of proper time display
		 */
		String time = String.format("%d:%02d %s", hours, minutes, amPm);
		return time;
	}
	/**
	 * Convert the value of minutes GIVEN to a proper time representation (HH:MM AM/PM)
	 * 
	 * @param mins elapsed from staring hour
	 * @return String of the time representation (HH:MM AM/PM)
	 */
	public static String MinutesToTime(int mins) {
		String amPm = "AM";
		int hours = (mins / 60)  + 6; // represent minutes in terms of hours
		int minutes = mins % 60;	  // add reminding minutes
		// check whether AM or PM
		if (hours > 12) {
			hours -= 12;
			amPm = amPm.replace('A', 'P');
		}
		// create a string representation of proper time display
		String time = String.format("%d:%02d %s", hours, minutes, amPm);
		return time;
	}
		
}
