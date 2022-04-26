package EE364Project;
/**
 * Student class is for creating students with all required data related to university student
 * 
 * @author Azeez + Ahmed
 *
 */
public class Student implements Comparable<Student>, Cloneable{
	
	//--------------------Student Variables----------------------
	/**
	 * Represent ID of the student
	 */
	int id;						
	/**
	 * Represent if the student has an exam
	 */
	boolean hasExam;		
	/**
	 * Represent if the student caught the lecture on time
	 */
	boolean isCatch;		
	/**
	 * Represent the Intended time of arriving to uni.
	 */
	int intendedArrivalTime;
	/**
	 * Represent the time student showed up at bus station
	 */
	int showupTime;			
	
	/**
	 * default constructor to create a students with fixed attributes
	 */
	public Student() {
		this.setID(randomID());
		this.setHasExam(randomExamCondition());
		this.setIntendedArrivalTime(randomIntendedArrivalTime());
		this.setShowupTime(randomShowupTime());
	}
	/**
	 * Student constructor to create a students with specified attributes
	 * 
	 * @param id the KAU identifier of the student
	 * @param hasExam boolean indicating wither the student has an exam or not
	 * @param IAT Intended Arrival Time to the campus 
	 * @param ST Showed up time in the bus station 
	 */
	public Student(int id, boolean hasExam, int IAT, int ST) {
		this.setID(id);
		this.setHasExam(hasExam);
		this.setIntendedArrivalTime(IAT);
		this.setShowupTime(ST);
	}

	//--------------Setters & Getters-------------------
	/**
	 * 
	 * @param id the KAU identifier of the student
	 */
	public void setID(int id) {
		this.id = id;
	}
	/**
	 * 
	 * @return id the KAU identifier of the student
	 */
	public int getID() {
		return this.id;
	}
	/**
	 * 
	 * @param HX boolean indicating wither the student has an exam or not
	 */
	public void setHasExam(boolean HX) {
		this.hasExam = HX;
	}
	/**
	 * 
	 * @return boolean indicating wither the student has an exam or not
	 */
	public boolean getHasExam() {
		return this.hasExam;
	}
	/**
	 * 
	 * @param isCatch boolean of wither the student has arrived to campus on time 
	 */
	public void setIsCatch(boolean isCatch ) {
		this.isCatch = isCatch; 
		
	}
	/**
	 * 
	 * @return boolean of wither the student has arrived to campus on time 
	 */
	public boolean isCatch() {
		return isCatch;
	}
	/**
	 * 
	 * @param mins Showed up time in the bus station in minutes from the reference starting hour 
	 */
	public void setShowupTime(int mins) {
		showupTime = mins;
	}
	/**
	 * 
	 * @return Showed up time in the bus station in minutes from the reference starting hour 
	 */
	public int getShowupTime() {
		return showupTime;
	}
	/**
	 * 
	 * @param mins Intended Arrival Time to the campus in minutes from the reference starting hour 
	 */
	public void setIntendedArrivalTime(int mins) {
		intendedArrivalTime = mins;
	}
	/**
	 * 
	 * @return Intended Arrival Time to the campus in minutes from the reference starting hour 
	 */
	public int getIntendedArrivalTime() {
		return intendedArrivalTime ;
	}
	
	//---------------------Methods---------------------------
	/**
	 * Generate random ID for the student
	 * 
	 * @return id the KAU identifier of the student
	 */
	public int randomID() {
		// Random number between 2200000 & 1000000
		int id = (int) (Math.random() * (1200000) ) + 1000000;
		return id;
	}
	/**
	 * Generate random probability of student having an exam
	 * 
	 * @return A boolean indicating wither the student has an exam or not
	 */
	public boolean randomExamCondition() {
		// Random exam probability of 5%
		boolean Exam = Math.random() < 0.95? false:true;
		return Exam;
	}
	/**
	 * Generate random showed up time at the bus station
	 * 
	 * @return ST Showed up time in the bus station 
	 */
	public int randomShowupTime() {
		// Random show up time of 30 minutes before bus departure time
		int randomMinutes =  getIntendedArrivalTime() - 60 + (int) (Math.random() * 30) ;
		return randomMinutes;
	}
	/**
	 * Generate random Intended time of arriving to KAU.
	 * 
	 * @return Intended Arrival Time to the campus
	 */
	public int randomIntendedArrivalTime() {
		
//		double rand = Math.random();
//		double rand2= Math.random();
//		int randomTime ;
//		//More random student in the rush hour between 6:00 AM - 10:00 AM 
//		if (rand <= 0.28) {
//			  randomTime = (int) (rand * ( 32 ) + 1) * 30;
//			  if (randomTime == 960) {
//				  return randomTime;
//			  } else if (randomTime < 60) {
//				  return randomTime + 60;
//			  }else 
//				  return randomTime;			  
//	    // Make the random range between 0 - 1
//		}else
//
//			if (rand2<= 0.28) {
//				  randomTime = (int) (rand2 * ( 32 ) + 1) * 30;
//				  if (randomTime == 960) {
//					  return randomTime;
//				  } else if (randomTime < 60) {
//					  return randomTime + 60;
//				  }else 
//					  return randomTime;	}else {
//				  randomTime = (int) (Math.random() * ( 32 ) + 1) * 30;
//				  if (randomTime == 960) {
//					  return randomTime;
//				  } else if (randomTime < 60) {
//					  return randomTime + 60;
//				  }else 
//					  return randomTime;  
//					  }
		double rand = Math.random();
		int randomTime ;
		//More random student in the rush hour between 6:00 AM - 10:00 AM 
		if (rand <= 0.28) {
			  randomTime = (int) (rand * ( 32 ) + 1) * 30;
			  if (randomTime == 960) {
				  return randomTime;
			  } else if (randomTime < 60) {
				  return randomTime + 60;
			  }else 
				  return randomTime;			  
	    // Make the random range between 0 - 1
		}else
			
			  randomTime = (int) (Math.random() * ( 32 ) + 1) * 30;
			  if (randomTime == 960) {
				  return randomTime;
			  } else if (randomTime < 60) {
				  return randomTime + 60;
			  }else 
				  return randomTime;  

	}

	/**
	 * Compare show up times of students
	 * 
	 * @param student
	 */
	@Override
	public int compareTo(Student comparestu)
    {
        int compareShowUpTime = ((Student)comparestu).getShowupTime();
        //  For Ascending order
        return this.showupTime - compareShowUpTime;

    }
	/**
	 * Customized string representation of the student with all important data 
	 */
	@Override
	public String toString() {
		int ID = this.getID();
		boolean hasE = this.getHasExam();
		String ST = Time.MinutesToTime(this.getShowupTime());
		String IAT =  Time.MinutesToTime(this.getIntendedArrivalTime());
		boolean c = this.isCatch;
		String a = String.format("ID: %s\tHas Exam: %s\tshowup Time: %s\tIntended Arrival Time: %s\tCatch: %s", 
                    ID, hasE, ST, IAT, c);
		return a;
		

	}
	
	/**
	 * shallow copying the student
	 */
    @Override
    protected Object clone() throws CloneNotSupportedException {
    	//shallow copy
        return super.clone();
    }


}