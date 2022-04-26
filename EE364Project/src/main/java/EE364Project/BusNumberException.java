package EE364Project;

/**
 * Custom Exception class raised when the number of busses inputed is not valid (<=0)
 * 
 * @author Ali
 *
 */
public class BusNumberException extends Exception {

	/**
	 * serialVersionUID of BusNumberException
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * numberOfBusses entered by the user 
	 */
	private int numberOfBusses;
	
	/**
	 * A constructor calling super to pass the message explaining the exception
	 * 
	 * @param numberOfBusses inputed by the user
	 */
	public BusNumberException(int numberOfBusses) {
		super("Invalid number of busses " + numberOfBusses);
		this.numberOfBusses = numberOfBusses;
	}

	/**
	 * @return the numberOfBusses ntered by the user
	 */
	public int getNumberOfBusses() {
		return numberOfBusses;
	}
	
}



