package EE364Project;

import java.io.*;
import java.util.*;
import java.net.URL;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TextArea;
/**
 * the main controller of the GUI with all event driven programming required to function properly
 * 
 * @author Ali + Azeez 
 *
 */
public class FXMLmainController  implements Initializable{
	//lists to save data for reports
	public static ArrayList<Student> studentsAll = new ArrayList<>();
	public static ArrayList<Flight> flightsAll = new ArrayList<>();
	public static int flightNumber;
	public static String summaryP1;
	public static String summaryP2;
	public static String daySummaryP1;
	public static String daySummaryP2;
	public static ArrayList<ArrayList<Flight>> flightReportP1 = new ArrayList<ArrayList<Flight>>();
	public static ArrayList<Integer> currentStudetnsNumList = new ArrayList<>();
	public static ArrayList<ArrayList<Student>> randomStudentsGeneratedForP2 = new ArrayList<ArrayList<Student>>();
    //efficiency percentage
	public static String EfficiencyString = "";
	
	//Starting
	@FXML
	private Button Start;
	@FXML
	private TextField NumberOfDays;
	@FXML
	private TextField NumberOfBusses;
	@FXML
	private TextField NumberOfStudents;

	// Exporting-------------------------------------
	@FXML
	private Button ExportPhase1Button;
	@FXML
	private Button ExportPhase2Button;
	// Reporting Phase 1 and 2-----------------------
	@FXML
	private TextArea ReportTextAreaPhase1;
	@FXML
	private TextArea TotalSummaryPhase1;
	@FXML
	private TextArea ReportTextAreaPhase2;
	@FXML
	private TextArea TotalSummaryPhase2;
	//-----------------------------------------------
	//SearchID pop Up
	@FXML
	private TextField SearchIDTextField;
	@FXML
	private TextField SearchIDTextField2;
	@FXML
	private Button CloseSearchIDButton;
	//Inside SearchID Window
	@FXML
	private Button SearchIDButton;
	@FXML
	private Button SearchIDButton2;
	@FXML
	private Button ButtonSearchIDButton3;
	@FXML
	private TextArea SearchIDReport;
	//-----------------------------------------------
	//Flight pop Up
	@FXML
	private TextField DayNumberTextField;
	@FXML
	private TextField FlightNumberTextField;
	@FXML
	private Button FlightButton;
	//Inside Flight Window
	@FXML
	private TextArea FlightSummaryReport;
	@FXML
	private Button CloseFlightButton;
	//-----------------------------------------------
	//Day Summary pop Up
	@FXML
	private TextField DayNumberTextField2;
	@FXML
	private Button SummaryDayButton;
	//Inside Day Summary Window
	@FXML
	private Button SearchDayButton;
	@FXML
	private TextArea DaySummaryReport;
	@FXML
	private Button CloseDaySummaryButton;
	//-----------------------------------------------
	//Secret Bouns
	@FXML
	private ImageView Katchup;
    @FXML
    private TextArea EfficiencyTextArea;
	
	//Explanation pop up
	@FXML
	private Button OpenAboutButton;
	//Inside Explanation Window
	@FXML
	private TextArea AboutTextArea;
	@FXML
	private Button CloseAboutButton;
		
    //About Pop up event
	@FXML
	public void About(ActionEvent e) throws IOException{
			Stage stage;
			Parent root;
			
			if(e.getSource() == OpenAboutButton) {
				stage = new Stage();
				root = FXMLLoader.load(getClass().getResource("FXMLAbout.fxml"));
				stage.setScene(new Scene(root));
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initOwner(OpenAboutButton.getScene().getWindow());
				stage.showAndWait();
			}else {
				stage = (Stage) CloseAboutButton.getScene().getWindow();
				stage.close();
				}
			
		}


	//Display the summary of the given day
	@FXML
	public void dayNumberSearch(ActionEvent e) throws IOException{
		int dayNum = Integer.parseInt(DayNumberTextField2.getText()) - 1;
		ArrayList<Flight> flights = FXMLmainController.flightReportP1.get(dayNum);
		

		
		int catches = 0;
		int totalStudetnsDelivered = 0;
		double totalDistance = 0;
		double totalFuel = 0;
		int ID = -1;
		// loop to save data of the flights in the given day
		for(int i = 0; i < flights.size(); i++) {
			if (ID != flights.get(i).busUsed.getID()) {
				totalFuel+= flights.get(i).busUsed.getFuelConsumption();
				totalDistance += flights.get(i).busUsed.getDistanceKm();
				ID = flights.get(i).busUsed.getID();
			}
			for (Student S : flights.get(i).studentsInTrip) {
				totalStudetnsDelivered++;
				if (S.isCatch) {
					catches++;
				}
			}
		}
		//Total misses
		int totalStudetns = FXMLmainController.currentStudetnsNumList.get(dayNum);
		int misses = totalStudetns - catches;
		//catches%
		String perCC = String.format("%.2f", 100*((double)(catches) / (catches+misses))) + "%";

		
		String daySummary = String.format("Total Catches: %d\t\tTotal Misses: %d\t\tTotal Catches: %s\t\tTotal Number of Flights: %d\t\t\nTotal Students Delivered: %d\t\t"
				+ "Total Students not Delivered: %d\t\tTotal Distance KM: %.2f\t\tTotal Fuel L: %.2f"
				, catches, misses, perCC, flights.size(), totalStudetnsDelivered, totalStudetns - totalStudetnsDelivered, totalDistance, totalFuel);
		DaySummaryReport.setText(daySummary);
		DaySummaryReport.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em;");

		
	}
	//Animation Bonus
	@FXML
	public void Katchup(ActionEvent e) throws IOException{

			TranslateTransition translate = new TranslateTransition();
			translate.setNode (Katchup);
			translate.setByX(670);
			translate.setByY(400);

			translate.setAutoReverse(true);
			translate.play();
			
			// rotate
			RotateTransition rotate = new RotateTransition();
			rotate.setNode(Katchup);
			rotate.setInterpolator (Interpolator.LINEAR);
			rotate.setByAngle(360);
			rotate.play();
			
			// fade
			FadeTransition fade = new FadeTransition();
			fade.setNode (Katchup);
			fade.setInterpolator(Interpolator.LINEAR);
			fade.setFromValue(0);
			fade.setToValue(1);
			fade.play();
			
			// scale
			ScaleTransition scale =  new ScaleTransition();
			scale.setNode(Katchup);
			scale.setInterpolator(Interpolator.LINEAR);
			scale.setByX(5);
			scale.setByY(5);
			scale.setAutoReverse(true);
			scale.play();
			
		
	}
	//Display the summary of the flight in the given day
	@FXML
	public void flightNumberSearch(ActionEvent e) throws IOException{
		int flightNum = Integer.parseInt(FlightNumberTextField.getText());
		int dayNum = Integer.parseInt(DayNumberTextField.getText());
		
		FlightSummaryReport.setText("Flight Number: " + flightNum + "     " + FXMLmainController.flightReportP1.get(dayNum - 1).get(flightNum - 1).toString());
		FlightSummaryReport.setStyle("-fx-text-fill: red; -fx-font-size: 1.3em;");
		
	}
	//Display the window for the summary of the flight in the given day
	@FXML
	public void flightNumberButton(ActionEvent e) throws IOException{

		Stage stage;
		Parent root;
			try {
				stage = new Stage();
				root = FXMLLoader.load(getClass().getResource("FXMLFlightSummary.fxml"));
				stage.setScene(new Scene(root));	
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initOwner(SearchIDButton.getScene().getWindow());
				stage.showAndWait();
			}catch(Exception e1) {
				System.out.println(e1.getMessage() + " " + e1);
			}

		
	}
	//closing windows
	@FXML
	public void close(ActionEvent e) throws IOException{
		Stage stage;
		try {
			stage = (Stage) CloseSearchIDButton.getScene().getWindow();
			stage.close();
		}catch(Exception e1) {
			System.out.print(e1.getMessage());
		}
		
	}
	//Display the student with the given ID
	@FXML
	public void SearchIDButton(ActionEvent e) throws IOException{
		String ID = SearchIDTextField2.getText();

		for(Student S : studentsAll) {
			if(S.getID() == Integer.parseInt(ID)) {
				SearchIDReport.setText(S.toString());
				SearchIDReport.setStyle("-fx-font-size: 1.3em; -fx-text-fill: red ;");
			}
		}		
	}
	//Display the Search ID PopUp window
	@FXML
	public void SearchIDButtonPopUp(ActionEvent e) throws IOException{
		Stage stage;
		Parent root;
		try {
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("FXMLsearchID.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(SearchIDButton.getScene().getWindow());
			stage.showAndWait();
		}catch(Exception e1) {
			System.out.println(e1.getMessage() + " " + e1);
		}
		
	}
	//Display the Flight Summary PopUp window
	@FXML
	public void FlightButtonPopUp(ActionEvent e) throws IOException{
		
		Stage stage;
		Parent root;
		
		if(e.getSource() == FlightButton) {
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("FXMLFlightSummary.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(FlightButton.getScene().getWindow());
			stage.showAndWait();
		}else {
			stage = (Stage) CloseFlightButton.getScene().getWindow();
			stage.close();
			}
		
	}
	//Display the Summary Day PopUp window
	@FXML
	public void SummaryDayButtonPopUp(ActionEvent e) throws IOException{
		
		Stage stage;
		Parent root;
		
		if(e.getSource() == SummaryDayButton) {
			stage = new Stage();
			root = FXMLLoader.load(getClass().getResource("FXMLDaySummary.fxml"));
			stage.setScene(new Scene(root));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initOwner(SummaryDayButton.getScene().getWindow());
			stage.showAndWait();
		}else {
			stage = (Stage) CloseDaySummaryButton.getScene().getWindow();
			stage.close();
			}
		
		
	}
	//Exporting Phase1
	@FXML
	public void ExportPhase1(ActionEvent e1) throws IOException, FileNotFoundException{
		File file = new File("ReportOfPhase1.txt");
		try(PrintWriter write = new PrintWriter(file)){
			write.printf("Number of Days: %s\nNumber of Busses: %s\nNumbebr of Students: %s\n\n",NumberOfDays.getText(),NumberOfBusses.getText(), NumberOfStudents.getText());
			write.printf("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& %s &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n\n", "Pahse#1 Full Report");
			write.print(FXMLmainController.summaryP1);
			write.printf("\n\n\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& %s &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n\n", "Summary Pahse#1");
			write.print(FXMLmainController.daySummaryP1);
			write.printf("\n\n\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& %s &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n\n", "Efficincey");
			write.print(EfficiencyString);

		}
		Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
		alert2.initStyle(StageStyle.UTILITY);
		alert2.setTitle("Success");
		alert2.setHeaderText(null);
		alert2.setContentText(String.format("%s", "Phase1 report exported successfully to 'ReportOfPhase1.txt' in the same directory!"));
		alert2.showAndWait();
	}
	//Exporting Phase2
	@FXML
	public void ExportPhase2(ActionEvent e) throws IOException{
		File file = new File("ReportOfPhase2.txt");
		try(PrintWriter write = new PrintWriter(file)){
			write.printf("Number of Days: %s\nNumber of Busses: %s\nNumbebr of Students: %s\n\n",NumberOfDays.getText(),NumberOfBusses.getText(), NumberOfStudents.getText());
			write.printf("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& %s &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n\n", "Pahse#2 Full Report");
			write.print(FXMLmainController.summaryP2);
			write.printf("\n\n\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& %s &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n\n", "Summary Pahse#2");
			write.print(FXMLmainController.daySummaryP2);
			write.printf("\n\n\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& %s &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n\n", "Efficincey");
			write.print(EfficiencyString);

		}
		Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
		alert2.initStyle(StageStyle.UTILITY);
		alert2.setTitle("Success");
		alert2.setHeaderText(null);
		alert2.setContentText(String.format("%s", "Phase2 report exported successfully to 'ReportOfPhase2.txt' in the same directory!"));
		alert2.showAndWait();	
		
	}
	
	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$STRAT RUNNING THE MAIN LOOP$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
	//start running the GUI
	@FXML
	public void start(ActionEvent ae) {
		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.initStyle(StageStyle.UTILITY);
		alert.setTitle("Error Message");
		alert.setHeaderText(null);
		
		//clear number of flights
		Flight.setNumFlights(0);
		
		
		
		System.out.println("Start Simulation");
		int KAUdays;
		try {
			if(!isNumeric(NumberOfDays.getText())) {
				throw new InputMismatchException();
			}
			if(isNumeric(NumberOfDays.getText()) && Integer.parseInt(NumberOfDays.getText()) <= 0) {
				throw new BusNumberException(Integer.parseInt(NumberOfDays.getText()));
			}
			 
			KAUdays = Integer.parseInt(NumberOfDays.getText());
		}catch(InputMismatchException e){
			alert.setContentText(String.format("'%s' %s", NumberOfDays.getText(),  "Not a valid input for the number of days. the default value is set {1 day}"));
			alert.showAndWait();
			KAUdays = 1;
		}catch(BusNumberException e){
			alert.setContentText(String.format("'%s' %s", NumberOfDays.getText(),  "Not a valid input for the number of days. the default value is set {1 day}"));
			alert.showAndWait();
			KAUdays = 1;
		}catch(Exception e){
			System.out.println("Not a valid input for the number of days. the default value is set {1 day}");
			KAUdays = 1;
		}
		
		
		int KAUbusses;
		try {
			if(!isNumeric(NumberOfBusses.getText())) {
				throw new InputMismatchException();
			}
			if(isNumeric(NumberOfBusses.getText()) && Integer.parseInt(NumberOfBusses.getText()) <= 0) {
				throw new BusNumberException(Integer.parseInt(NumberOfBusses.getText()));
			}
			 
			KAUbusses = Integer.parseInt(NumberOfBusses.getText());
		}catch(InputMismatchException e){
			alert.setContentText(String.format("'%s' %s", NumberOfBusses.getText(),  "Not a valid input for the number of busses. the default value is set {2 busses}"));
			alert.showAndWait();
			KAUbusses = 2;
		}catch(BusNumberException e){
			alert.setContentText(String.format("'%s' %s", NumberOfBusses.getText(),  "Not a valid number for the number of busses. the default value is set {2 busses}"));
			alert.showAndWait();
			KAUbusses = 2;
		}catch(Exception e){
			System.out.println("Not a valid input for the number of busses. the default value is set {2 busses}");
			KAUbusses = 2;
		}
		
		
		
		
		
		String KAUstudents = NumberOfStudents.getText();

		
		String[] parts = KAUstudents.split(",");
		int[] n1 = new int[parts.length];
		for(int n = 0; n < parts.length; n++) {
		   n1[n] = Integer.parseInt(parts[n]);
		}
		FXMLmainController.currentStudetnsNumList.clear();
		for(int i = 0; i<n1.length; i++) {
			FXMLmainController.currentStudetnsNumList.add(n1[i]);
		}
		
		
		try {
			if(n1.length != KAUdays) {
				throw new Exception();
			}
		}catch(Exception e){
			alert.setContentText(String.format("'%s' %s", NumberOfStudents.getText(),  "the length of the array of the number of students doesn't match the number of days!"));
			alert.showAndWait();
//			System.exit(1);
		}
		
		ArrayList<Object> report = GUI(KAUdays, KAUbusses, n1);
		ArrayList<ArrayList<Flight>> flightReport = (ArrayList<ArrayList<Flight>>)report.get(0);
		ArrayList<ArrayList<Bus>> BusReport = (ArrayList<ArrayList<Bus>>)report.get(1);
		double[] summaryP1 =  (double[]) report.get(2);
		
		String comprehensiveReport ="";
		String summaryReportP1 = "";
        // custom formating for the txt file
        String txtExportComp = "";
        String txtExportSum = "";
        

        
        for(int day = 0; day < flightReport.size(); day++) {
        	comprehensiveReport+=String.format("\n%n%s Day %d %s%n\n",star(60), day + 1, star(69));
        	comprehensiveReport+=String.format("%n%s%n",minus(142));
        	comprehensiveReport+=String.format(" 									        %s","Flights Information:");
        	
        	txtExportComp+=String.format("\n%n%s Day %d %s%n\n",star(60), day + 1, star(69));
        	txtExportComp+=String.format("%n%s%n",minus(142));
        	txtExportComp+=String.format(" 									        %s","Flights Information:");
        	
        	for(int flight = 0; flight < flightReport.get(day).size(); flight++) {
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
        		Flight currentFlight = flightReport.get(day).get(flight);
        		comprehensiveReport+=String.format("%n%s%n",minus(142));
                comprehensiveReport+=String.format("\s\s\s\s%s", "Flight");
                comprehensiveReport+=String.format("	   %s", "Students");
                comprehensiveReport+=String.format("	   %s", "Moved at");
                comprehensiveReport+=String.format("	      %s", "Arraived at");
                comprehensiveReport+=String.format("	 	   	%s", "Cathces");
                comprehensiveReport+=String.format("	    %s", "Misses");
                comprehensiveReport+=String.format("	 	  %s", "Catch %");
                comprehensiveReport+=String.format(" 	 %s", "Flight Type");
                comprehensiveReport+=String.format("	    %s\n", "BusID");
                
                comprehensiveReport+=String.format("	%d", flight + 1);
                comprehensiveReport+=String.format("		  %d", currentFlight.studentsInTrip.size());
                comprehensiveReport+=String.format("		     %s", Time.MinutesToTime(currentFlight.getTimeOfDeparture()));
                comprehensiveReport+=String.format("		%s", Time.MinutesToTime(currentFlight.getTimeOfArrival()));
                comprehensiveReport+=String.format("\t\t\t\s\s\s\s\s%d", currentFlight.getCatches());
                comprehensiveReport+=String.format("			%d", currentFlight.studentsInTrip.size() - currentFlight.getCatches());
                comprehensiveReport+=String.format("		  %s", currentFlight.getCatchesPer());
                comprehensiveReport+=String.format("	        %s", currentFlight.getTypeOfFilght());
                comprehensiveReport+=String.format("		%s", currentFlight.getBusUsed().getID());
                comprehensiveReport+=String.format("%n%s%n",minus(142));
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
                
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
//        		Flight currentFlight = flightReport.get(day).get(flight);
        		txtExportComp+=String.format("%n%s%n",minus(142));
                txtExportComp+=String.format("\s\s\s\s%s", "Flight");
                txtExportComp+=String.format("	   %s", "Students");
                txtExportComp+=String.format("	   %s", "Moved at");
                txtExportComp+=String.format("	      %s", "Arraived at");
                txtExportComp+=String.format("	 %s", "Cathces");
                txtExportComp+=String.format("	    %s", "Misses");
                txtExportComp+=String.format("	 %s", "Catch %");
                txtExportComp+=String.format(" 	 %s", "Flight Type");
                txtExportComp+=String.format("	    %s\n", "BusID");
                
                txtExportComp+=String.format("	%d", flight + 1);
                txtExportComp+=String.format("		%d", currentFlight.studentsInTrip.size());
                txtExportComp+=String.format("	    %s", Time.MinutesToTime(currentFlight.getTimeOfDeparture()));
                txtExportComp+=String.format("	       %s", Time.MinutesToTime(currentFlight.getTimeOfArrival()));
                txtExportComp+=String.format("       \t\s\s\s\s\s%d", currentFlight.getCatches());
                txtExportComp+=String.format("			%d", currentFlight.studentsInTrip.size() - currentFlight.getCatches());
                txtExportComp+=String.format("	   %s", currentFlight.getCatchesPer());
                txtExportComp+=String.format("	   %s", currentFlight.getTypeOfFilght());
                txtExportComp+=String.format("      %s", currentFlight.getBusUsed().getID());
                txtExportComp+=String.format("%n%s%n",minus(142));
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
                
//        		+++++++++++++++++++++++++++Student in Flight Information+++++++++++++++++++++++++++++++++++++
                
                comprehensiveReport+=String.format("   %s", "Student");
                comprehensiveReport+=String.format("         %s", "ID");
                comprehensiveReport+=String.format("	         %s", "ShowUp Time");
                comprehensiveReport+=String.format("	    %s", "Intended Arraival");
                comprehensiveReport+=String.format(" 	%s", "isCatch?");
                comprehensiveReport+=String.format("	   %s\n", "HasExam?");

                
                txtExportComp+=String.format("   %s", "Student");
                txtExportComp+=String.format("         %s", "ID");
                txtExportComp+=String.format("	         %s", "ShowUp Time");
                txtExportComp+=String.format("	    %s", "Intended Arraival");
                txtExportComp+=String.format(" 	%s", "isCatch?");
                txtExportComp+=String.format("	   %s\n", "HasExam?");
                
                
                for(int S = 0; S < currentFlight.studentsInTrip.size(); S++) {
                	Student stu = currentFlight.studentsInTrip.get(S);
                    comprehensiveReport+=String.format("	%d", S + 1);
                    comprehensiveReport+=String.format("	    %d", stu.getID());
                    comprehensiveReport+=String.format("	     %s", Time.MinutesToTime(stu.getShowupTime()));
                    comprehensiveReport+=String.format("		%s", Time.MinutesToTime(stu.getIntendedArrivalTime()));
                    comprehensiveReport+=String.format("			  %s", stu.isCatch());
                    comprehensiveReport+=String.format("		 %s\n", stu.getHasExam());
                    
                    txtExportComp+=String.format("	%d", S + 1);
                    txtExportComp+=String.format("	    %d", stu.getID());
                    txtExportComp+=String.format("	     %s", Time.MinutesToTime(stu.getShowupTime()));
                    txtExportComp+=String.format("		%s", Time.MinutesToTime(stu.getIntendedArrivalTime()));
                    txtExportComp+=String.format("		 %s", stu.isCatch());
                    txtExportComp+=String.format("		 %s\n", stu.getHasExam());
                }
//        		+++++++++++++++++++++++++++Student in Flight Information+++++++++++++++++++++++++++++++++++++
        		
        	}
//        		+++++++++++++++++++++++++++Bus in Flight Information+++++++++++++++++++++++++++++++++++++
        		comprehensiveReport+=String.format("%n\t\t\t%s",minus(110));
        		comprehensiveReport+=String.format("%n\t\t\t 				     	   	            %s","Bus Information:");
            	comprehensiveReport+=String.format("%n\t\t\t%s%n",minus(110));
                comprehensiveReport+=String.format("\t\t\t    %s", "BusID");
                comprehensiveReport+=String.format("	 %s", "Moved-Distance KM");
                comprehensiveReport+=String.format("	 %s", "FuelConsumbtion L");
                comprehensiveReport+=String.format(" 	 %s", "Number Of Trips");
                comprehensiveReport+=String.format("	%s\n", "Students Delivered");
                
        		txtExportComp+=String.format("%n\t\t\t%s",minus(110));
        		txtExportComp+=String.format("%n\t\t\t 				     	   	            %s","Bus Information:");
            	txtExportComp+=String.format("%n\t\t\t%s%n",minus(110));
                txtExportComp+=String.format("\t\t\t    %s", "BusID");
                txtExportComp+=String.format("	 %s", "Moved-Distance KM");
                txtExportComp+=String.format("	 %s", "FuelConsumbtion L");
                txtExportComp+=String.format(" 	 %s", "Number Of Trips");
                txtExportComp+=String.format("	%s\n", "Students Delivered");

                for(int B = 0; B < BusReport.get(day).size(); B++) {
                	Bus Cbus = BusReport.get(day).get(B);
                    comprehensiveReport+=String.format("\t\t\t	%d", Cbus.getID());
                    comprehensiveReport+=String.format("	            %.2f", Cbus.getDistanceKm());
                    comprehensiveReport+=String.format("				   %.2f", Cbus.getFuelConsumption());
                    comprehensiveReport+=String.format("				  %d", Cbus.getNumberTrips());
                    comprehensiveReport+=String.format("			 	 %d\n", Cbus.getStudentsDelivered().size());
                    
                    txtExportComp+=String.format("\t\t\t    %d", Cbus.getID());
                    txtExportComp+=String.format("	           %.2f", Cbus.getDistanceKm());
                    txtExportComp+=String.format("	           %.2f", Cbus.getFuelConsumption());
                    txtExportComp+=String.format("		       %d", Cbus.getNumberTrips());
                    txtExportComp+=String.format("		       %d\n", Cbus.getStudentsDelivered().size());
                }
//        		+++++++++++++++++++++++++++Bus in Flight Information+++++++++++++++++++++++++++++++++++++
                
                
        	
        	
        	
        }
        
         
        
        
        // **************************Resources Efficiency %***************************** 
        //X Liter/Satidfied_Student
        double fuelPerSatidfiedStudent  = ( (double)summaryP1[6] / (int)summaryP1[0] );
        //X SAR/Satidfied_Student 
        double pricePerSatidfiedStudent  = fuelPerSatidfiedStudent * 0.52;
        //X Liter/Student
        double fuelPerStudent = ( (double)summaryP1[6] / (int)summaryP1[3] );
        //X SAR/Student 
        double pricePerStudent = fuelPerStudent * 0.52;
        //To represent percentage of value of money spent efficiently
        double valueOfMoney = ( pricePerStudent / pricePerSatidfiedStudent) * 100;
        // **************************Resources Efficiency %*****************************
        
        

        double catchPer = 100*((double)(summaryP1[0]) / (summaryP1[0]+summaryP1[1]));

        summaryReportP1+=String.format("%s	     ", "Total Catches");
        summaryReportP1+=String.format("%s	     ", "Total Misses");
        summaryReportP1+=String.format("%s	     ", "Total Catches%");
        summaryReportP1+=String.format("%s          ", "Total Number of Flgihts");
        summaryReportP1+=String.format("%s          ", "Transport Capacity% ");
        summaryReportP1+=String.format("%s\n", "Total Misses%");
        summaryReportP1+=String.format("        %d", (int)summaryP1[0]);
        summaryReportP1+=String.format("				%d", (int)summaryP1[1]);
        summaryReportP1+=String.format("		           %.2f%%", catchPer);
        summaryReportP1+=String.format("		  	       %d", (int)summaryP1[2]);
        summaryReportP1+=String.format("			   	     %.2f%%", ((double)summaryP1[3] / ((int)summaryP1[2] * 10) )* 100);
        summaryReportP1+=String.format("				%.2f%%\n\n", 100 - catchPer);
        
        
        
        summaryReportP1+=String.format("%s	 	", "Total Students Delivered");
        summaryReportP1+=String.format("%s	 	", "Total Students not Delivered");
        summaryReportP1+=String.format("%s	 	", "Total Distance KM");
        summaryReportP1+=String.format("%s          ", "Total Fuel L");
        summaryReportP1+=String.format("%s\n", "Money Wasted");
        summaryReportP1+=String.format("		%d", (int)summaryP1[3]);
        summaryReportP1+=String.format("							%d", (int)summaryP1[4]);
        summaryReportP1+=String.format("				       %.2f", summaryP1[5]);
        summaryReportP1+=String.format("		          %.2f", summaryP1[6]);
        summaryReportP1+=String.format("	                 %.2f", ((summaryP1[6]*0.52)/(int)summaryP1[3]) * summaryP1[1]);

        
        
        // special formating for txt file
        txtExportSum+=String.format("%s	     ", "Total Catches");
        txtExportSum+=String.format("%s	     ", "Total Misses");
        txtExportSum+=String.format("%s	     ", "Total Catches%");
        txtExportSum+=String.format("%s          ", "Total Number of Flgihts");
        txtExportSum+=String.format("%s          ", "Transport Capacity% ");
        txtExportSum+=String.format("%s\n", "Total Misses%");
        txtExportSum+=String.format("        %d", (int)summaryP1[0]);
        txtExportSum+=String.format("	         %d", (int)summaryP1[1]);
        txtExportSum+=String.format("		          %.2f%%", catchPer);
        txtExportSum+=String.format("		  	  %d", (int)summaryP1[2]);
        txtExportSum+=String.format("			     %.2f%%", ((double)summaryP1[3] / ((int)summaryP1[2] * 10) )* 100);
        txtExportSum+=String.format("	               %.2f%%\n\n", 100 - catchPer);
        
        
        
        txtExportSum+=String.format("%s	 	", "Total Students Delivered");
        txtExportSum+=String.format("%s	 	", "Total Students not Delivered");
        txtExportSum+=String.format("%s	 	", "Total Distance KM");
        txtExportSum+=String.format("%s          ", "Total Fuel L");
        txtExportSum+=String.format("%s\n", "Money Wasted");
        txtExportSum+=String.format("	  %d", (int)summaryP1[3]);
        txtExportSum+=String.format("		  \t\t\t\t%d", (int)summaryP1[4]);
        txtExportSum+=String.format("		        \t%.2f", summaryP1[5]);
        txtExportSum+=String.format("		           %.2f", summaryP1[6]);
        txtExportSum+=String.format("	            %.2f", ((summaryP1[6]*0.52)/(int)summaryP1[3]) * summaryP1[1]);

        
        FXMLmainController.summaryP1 = txtExportComp;
        FXMLmainController.daySummaryP1 = txtExportSum;
        ReportTextAreaPhase1.setText(comprehensiveReport);
        TotalSummaryPhase1.setText(summaryReportP1);
        
        
        
        
        
        
        
        
        
		ArrayList<Object> report2 = GUI_Phase2(KAUdays, KAUbusses, FXMLmainController.randomStudentsGeneratedForP2);
		ArrayList<ArrayList<Flight>> flightReport2 = (ArrayList<ArrayList<Flight>>)report2.get(0);
		ArrayList<ArrayList<Bus>> BusReport2 = (ArrayList<ArrayList<Bus>>)report2.get(1);
		double[] summaryP2 =  (double[]) report2.get(2);
		
		String comprehensiveReport2 ="";
		String summaryReportP2 = "";
		// custom formating for the txt file
        String txtExportComp2 = "";
        String txtExportSum2 = "";
		
        
        for(int day = 0; day < flightReport2.size(); day++) {
        	comprehensiveReport2+=String.format("\n%n%s Day %d %s%n\n",star(60), day + 1, star(69));
        	comprehensiveReport2+=String.format("%n%s%n",minus(142));
        	comprehensiveReport2+=String.format(" 									        %s","Flights Information:");
        	
        	txtExportComp2+=String.format("\n%n%s Day %d %s%n\n",star(60), day + 1, star(69));
        	txtExportComp2+=String.format("%n%s%n",minus(142));
        	txtExportComp2+=String.format(" 									        %s","Flights Information:");
        	
        	for(int flight = 0; flight < flightReport2.get(day).size(); flight++) {
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
        		Flight currentFlight = flightReport2.get(day).get(flight);
        		comprehensiveReport2+=String.format("%n%s%n",minus(142));
                comprehensiveReport2+=String.format("\s\s\s\s%s", "Flight");
                comprehensiveReport2+=String.format("	   %s", "Students");
                comprehensiveReport2+=String.format("	   %s", "Moved at");
                comprehensiveReport2+=String.format("	      %s", "Arraived at");
                comprehensiveReport2+=String.format("	 	   	%s", "Cathces");
                comprehensiveReport2+=String.format("	    %s", "Misses");
                comprehensiveReport2+=String.format("	 	  %s", "Catch %");
                comprehensiveReport2+=String.format(" 	 %s", "Flight Type");
                comprehensiveReport2+=String.format("	    %s\n", "BusID");
                
                comprehensiveReport2+=String.format("	%d", flight + 1);
                comprehensiveReport2+=String.format("		  %d", currentFlight.studentsInTrip.size());
                comprehensiveReport2+=String.format("		     %s", Time.MinutesToTime(currentFlight.getTimeOfDeparture()));
                comprehensiveReport2+=String.format("		%s", Time.MinutesToTime(currentFlight.getTimeOfArrival()));
                comprehensiveReport2+=String.format("\t\t\t\s\s\s\s\s%d", currentFlight.getCatches());
                comprehensiveReport2+=String.format("			%d", currentFlight.studentsInTrip.size() - currentFlight.getCatches());
                comprehensiveReport2+=String.format("		  %s", currentFlight.getCatchesPer());
                comprehensiveReport2+=String.format("	        %s", currentFlight.getTypeOfFilght());
                comprehensiveReport2+=String.format("		%s", currentFlight.getBusUsed().getID());
                comprehensiveReport2+=String.format("%n%s%n",minus(142));
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
                
                
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
//        		Flight currentFlight = flightReport2.get(day).get(flight);
        		txtExportComp2+=String.format("%n%s%n",minus(142));
                txtExportComp2+=String.format("\s\s\s\s%s", "Flight");
                txtExportComp2+=String.format("	   %s", "Students");
                txtExportComp2+=String.format("	   %s", "Moved at");
                txtExportComp2+=String.format("	      %s", "Arraived at");
                txtExportComp2+=String.format("	 %s", "Cathces");
                txtExportComp2+=String.format("	    %s", "Misses");
                txtExportComp2+=String.format("	 %s", "Catch %");
                txtExportComp2+=String.format(" 	 %s", "Flight Type");
                txtExportComp2+=String.format("	    %s\n", "BusID");
                
                txtExportComp2+=String.format("	%d", flight + 1);
                txtExportComp2+=String.format("		%d", currentFlight.studentsInTrip.size());
                txtExportComp2+=String.format("	    %s", Time.MinutesToTime(currentFlight.getTimeOfDeparture()));
                txtExportComp2+=String.format("	       %s", Time.MinutesToTime(currentFlight.getTimeOfArrival()));
                txtExportComp2+=String.format("       \t\s\s\s\s\s%d", currentFlight.getCatches());
                txtExportComp2+=String.format("			%d", currentFlight.studentsInTrip.size() - currentFlight.getCatches());
                txtExportComp2+=String.format("	   %s", currentFlight.getCatchesPer());
                txtExportComp2+=String.format("	   %s", currentFlight.getTypeOfFilght());
                txtExportComp2+=String.format("      %s", currentFlight.getBusUsed().getID());
                txtExportComp2+=String.format("%n%s%n",minus(142));
//        		+++++++++++++++++++++++++++Flights Information+++++++++++++++++++++++++++++++++++++
                
//        		+++++++++++++++++++++++++++Student in Flight Information+++++++++++++++++++++++++++++++++++++
                
                comprehensiveReport2+=String.format("   %s", "Student");
                comprehensiveReport2+=String.format("         %s", "ID");
                comprehensiveReport2+=String.format("	         %s", "ShowUp Time");
                comprehensiveReport2+=String.format("	    %s", "Intended Arraival");
                comprehensiveReport2+=String.format(" 	%s", "isCatch?");
                comprehensiveReport2+=String.format("	   %s\n", "HasExam?");
                
                txtExportComp2+=String.format("   %s", "Student");
                txtExportComp2+=String.format("         %s", "ID");
                txtExportComp2+=String.format("	         %s", "ShowUp Time");
                txtExportComp2+=String.format("	    %s", "Intended Arraival");
                txtExportComp2+=String.format(" 	%s", "isCatch?");
                txtExportComp2+=String.format("	   %s\n", "HasExam?");

                for(int S = 0; S < currentFlight.studentsInTrip.size(); S++) {
                	Student stu = currentFlight.studentsInTrip.get(S);
                    comprehensiveReport2+=String.format("	%d", S + 1);
                    comprehensiveReport2+=String.format("	    %d", stu.getID());
                    comprehensiveReport2+=String.format("	     %s", Time.MinutesToTime(stu.getShowupTime()));
                    comprehensiveReport2+=String.format("		%s", Time.MinutesToTime(stu.getIntendedArrivalTime()));
                    comprehensiveReport2+=String.format("			  %s", stu.isCatch());
                    comprehensiveReport2+=String.format("		 %s\n", stu.getHasExam());
                    
                    txtExportComp2+=String.format("	%d", S + 1);
                    txtExportComp2+=String.format("	    %d", stu.getID());
                    txtExportComp2+=String.format("	     %s", Time.MinutesToTime(stu.getShowupTime()));
                    txtExportComp2+=String.format("		%s", Time.MinutesToTime(stu.getIntendedArrivalTime()));
                    txtExportComp2+=String.format("		 %s", stu.isCatch());
                    txtExportComp2+=String.format("		 %s\n", stu.getHasExam());
                }
//        		+++++++++++++++++++++++++++Student in Flight Information+++++++++++++++++++++++++++++++++++++
        		
        	}
//        		+++++++++++++++++++++++++++Bus in Flight Information+++++++++++++++++++++++++++++++++++++
        		comprehensiveReport2+=String.format("%n\t\t\t%s",minus(110));
        		comprehensiveReport2+=String.format("%n\t\t\t 				     	   	            %s","Bus Information:");
            	comprehensiveReport2+=String.format("%n\t\t\t%s%n",minus(110));
                comprehensiveReport2+=String.format("\t\t\t    %s", "BusID");
                comprehensiveReport2+=String.format("	 %s", "Moved-Distance KM");
                comprehensiveReport2+=String.format("	 %s", "FuelConsumbtion L");
                comprehensiveReport2+=String.format(" 	 %s", "Number Of Trips");
                comprehensiveReport2+=String.format("	%s\n", "Students Delivered");
                
        		txtExportComp2+=String.format("%n\t\t\t%s",minus(110));
        		txtExportComp2+=String.format("%n\t\t\t 				     	   	            %s","Bus Information:");
            	txtExportComp2+=String.format("%n\t\t\t%s%n",minus(110));
                txtExportComp2+=String.format("\t\t\t    %s", "BusID");
                txtExportComp2+=String.format("	 %s", "Moved-Distance KM");
                txtExportComp2+=String.format("	 %s", "FuelConsumbtion L");
                txtExportComp2+=String.format(" 	 %s", "Number Of Trips");
                txtExportComp2+=String.format("	%s\n", "Students Delivered");

                for(int B = 0; B < BusReport2.get(day).size(); B++) {
                	Bus Cbus = BusReport2.get(day).get(B);
                    comprehensiveReport2+=String.format("\t\t\t	%d", Cbus.getID());
                    comprehensiveReport2+=String.format("	            %.2f", Cbus.getDistanceKm());
                    comprehensiveReport2+=String.format("				   %.2f", Cbus.getFuelConsumption());
                    comprehensiveReport2+=String.format("				  %d", Cbus.getNumberTrips());
                    comprehensiveReport2+=String.format("			 	 %d\n", Cbus.getStudentsDelivered().size());
                    
                    txtExportComp2+=String.format("\t\t\t    %d", Cbus.getID());
                    txtExportComp2+=String.format("	           %.2f", Cbus.getDistanceKm());
                    txtExportComp2+=String.format("	           %.2f", Cbus.getFuelConsumption());
                    txtExportComp2+=String.format("		       %d", Cbus.getNumberTrips());
                    txtExportComp2+=String.format("		       %d\n", Cbus.getStudentsDelivered().size());
                }
//        		+++++++++++++++++++++++++++Bus in Flight Information+++++++++++++++++++++++++++++++++++++
                
                
        	
        	
        	
        }
        
           
        // **************************Resources Efficiency %***************************** 
        //X Liter/Satidfied_Student
        double fuelPerSatidfiedStudent2  = ( (double)summaryP2[6] / (int)summaryP2[0] );
        //X SAR/Satidfied_Student 
        double pricePerSatidfiedStudent2  = fuelPerSatidfiedStudent2 * 0.52;
        //X Liter/Student
        double fuelPerStudent2 = ( (double)summaryP2[6] / (int)summaryP2[3] );
        //X SAR/Student 
        double pricePerStudent2 = fuelPerStudent2 * 0.52;
        //To represent percentage of value of money spent efficiently
        double valueOfMoney2 = ( pricePerStudent2 / pricePerSatidfiedStudent2) * 100;
        // **************************Resources Efficiency %*****************************
        
        

        double catchPer2 = 100*((double)(summaryP2[0]) / (summaryP2[0]+summaryP2[1]));

        summaryReportP2+=String.format("%s	     ", "Total Catches");
        summaryReportP2+=String.format("%s	     ", "Total Misses");
        summaryReportP2+=String.format("%s	     ", "Total Catches%");
        summaryReportP2+=String.format("%s          ", "Total Number of Flgihts");
        summaryReportP2+=String.format("%s          ", "Transport Capacity% ");
        summaryReportP2+=String.format("%s\n", "Total Misses%");
        summaryReportP2+=String.format("        %d", (int)summaryP2[0]);
        summaryReportP2+=String.format("				%d", (int)summaryP2[1]);
        summaryReportP2+=String.format("		           %.2f%%", catchPer2);
        summaryReportP2+=String.format("		  	       %d", (int)summaryP2[2]);
        summaryReportP2+=String.format("			   	     %.2f%%", ((double)summaryP2[3] / ((int)summaryP2[2] * 10) )* 100);
        summaryReportP2+=String.format("				%.2f%%\n\n", 100 - catchPer2);
        
        
        
        summaryReportP2+=String.format("%s	 	", "Total Students Delivered");
        summaryReportP2+=String.format("%s	 	", "Total Students not Delivered");
        summaryReportP2+=String.format("%s	 	", "Total Distance KM");
        summaryReportP2+=String.format("%s          ", "Total Fuel L");
        summaryReportP2+=String.format("%s\n", "Money Wasted");
        summaryReportP2+=String.format("		%d", (int)summaryP2[3]);
        summaryReportP2+=String.format("							%d", (int)summaryP2[4]);
        summaryReportP2+=String.format("				       %.2f", summaryP2[5]);
        summaryReportP2+=String.format("		          %.2f", summaryP2[6]);
        summaryReportP2+=String.format("	                 %.2f", ((summaryP2[6]*0.52)/(int)summaryP2[3]) * summaryP2[1]);

        
        
        // special formating for txt file
        txtExportSum2+=String.format("%s	     ", "Total Catches");
        txtExportSum2+=String.format("%s	     ", "Total Misses");
        txtExportSum2+=String.format("%s	     ", "Total Catches%");
        txtExportSum2+=String.format("%s          ", "Total Number of Flgihts");
        txtExportSum2+=String.format("%s          ", "Transport Capacity% ");
        txtExportSum2+=String.format("%s\n", "Total Misses%");
        txtExportSum2+=String.format("        %d", (int)summaryP2[0]);
        txtExportSum2+=String.format("	         %d", (int)summaryP2[1]);
        txtExportSum2+=String.format("		          %.2f%%", catchPer);
        txtExportSum2+=String.format("		  	  %d", (int)summaryP2[2]);
        txtExportSum2+=String.format("			     %.2f%%", ((double)summaryP2[3] / ((int)summaryP2[2] * 10) )* 100);
        txtExportSum2+=String.format("	               %.2f%%\n\n", 100 - catchPer);
        
        
        
        txtExportSum2+=String.format("%s	 	", "Total Students Delivered");
        txtExportSum2+=String.format("%s	 	", "Total Students not Delivered");
        txtExportSum2+=String.format("%s	 	", "Total Distance KM");
        txtExportSum2+=String.format("%s          ", "Total Fuel L");
        txtExportSum2+=String.format("%s\n", "Money Wasted");
        txtExportSum2+=String.format("	  %d", (int)summaryP2[3]);
        txtExportSum2+=String.format("		  \t\t\t\t%d", (int)summaryP2[4]);
        txtExportSum2+=String.format("		        \t%.2f", summaryP2[5]);
        txtExportSum2+=String.format("		           %.2f", summaryP2[6]);
        txtExportSum2+=String.format("	            %.2f", ((summaryP2[6]*0.52)/(int)summaryP2[3]) * summaryP2[1]);


          double Phase1Efficiency =  ((summaryP1[6]*0.52)/(int)summaryP1[3]) * summaryP1[1];
	  	  double Phase2Efficiency = ((summaryP2[6]*0.52)/(int)summaryP2[3]) * summaryP2[1];
		  double Diff_P2_P1 = Math.abs(Phase1Efficiency - Phase2Efficiency);
		  double Money_perc = 1;

	  	  
		  if (Phase1Efficiency != 0) {
			  if (Phase1Efficiency > Phase2Efficiency) {
				  Money_perc = (Diff_P2_P1 / Phase1Efficiency) * 100;
				  EfficiencyString = String.format("Money spent MORE effectively by: %.2f%%", Money_perc);
			  }
			  else if (Phase2Efficiency > Phase1Efficiency) {
				  Money_perc = (Diff_P2_P1 / Phase1Efficiency) * 100;
				  EfficiencyString = String.format("Money spent LESS effectively by: %.2f%%", Money_perc);
			  }
			  else {
				  EfficiencyString = "Money spent in phase1 is equal to phase2";
			  }
		  }
		  else if (Phase1Efficiency == Phase2Efficiency) 
              EfficiencyString = "Money spent in phase1 is equal to phase2";

		  else {
				  EfficiencyString = "Money spent in phase1 is 100% effective!!";
			}

	  	  
	  	EfficiencyTextArea.setText(EfficiencyString);
	  	
		  
        FXMLmainController.summaryP2 = txtExportComp2;
        FXMLmainController.daySummaryP2 = txtExportSum2;
        ReportTextAreaPhase2.setText(comprehensiveReport2);
        TotalSummaryPhase2.setText(summaryReportP2);
        
		
	}
	//print *
	public static String star(int a){
        String star="";
        for(int i=0;i<a;i++){
            star+="*";
        }
        return star;
    }
	//print -
    public static String minus(int a){
        String star="";
        for(int i=0;i<a;i++){
            star+="-";
        }
        return star;
    }
    //update availability of busses according to current clock
	public static void updateAval(int testBusses, Queue busses) {
		//update availability after incrementing the clock
		for(int i = 0; i < testBusses; i++) {
			Bus currentBus = busses.peek();
			currentBus.checkAval(Time.clock);
			busses.dequeue();
			busses.enqueue(currentBus);
		}
	}
	//generate random students
	public static ArrayList<Student> generateRandomStudents(int days, int[] KAUStudent) {
		Scanner input = new Scanner(System.in);
//		System.out.println("Number of students in day#" + days);
		int testStudents = KAUStudent[days-1];
		ArrayList<Student> students = new ArrayList<>();
		for(int i = 0; i < testStudents; i++) {
			students.add(new Student());
		}
		Collections.sort(students);
		
		return students;
	}
	//update the scheduled departure time of the busses if it's time to move & no student in the bus 
	public static void noStudentsAndTimeToMove(Bus bus, Queue busses) {
		if (bus.getCapacity() == 10 && bus.getScheduledDormDeparture() == Time.clock) {
            //update the scheduled dormDeparture to the current scheduled bus departure time + 30
			// whish is (period for waiting students)
			for(int i = 1; i <= busses.size(); i++) {
				int NSDD = Time.clock + i*30;
				Bus current = busses.peek();
				current.setScheduledDormDeparture(NSDD);
				busses.dequeue();
				busses.enqueue(current);
			}
	}

}
	//check if the string is numerical
    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }
	//main drive of the project (main loop)
	public static ArrayList<Object> GUI(int KAUdays, int KAUbus, int[] KAUstudent) {
		//clear randomStudentsGeneratedForP2
		FXMLmainController.randomStudentsGeneratedForP2.clear();
		// report lists
		double[] totalSummary = new double[7];
		//Initialize number of catches to 0
		totalSummary[0] = 0;
		//Initialize number of total students delivered to 0
		totalSummary[3] = 0;
		//Initialize number of misses to 0
		totalSummary[1] = 0;
		//Initialize number of total students not delivered to 0
		totalSummary[4] = 0;
		//Initialize number of total fuel and distance to 0
		totalSummary[5] = 0;
		totalSummary[6] = 0;
		ArrayList<ArrayList<Flight>> flightReport = new ArrayList<ArrayList<Flight>>(KAUdays);
		ArrayList<ArrayList<Bus>> busReport = new ArrayList<ArrayList<Bus>>(KAUdays);		
		for(int i = 0; i<KAUdays; i++) {
			flightReport.add(new ArrayList<Flight>());
			busReport.add(new ArrayList<Bus>());
		}

		
		
        // create time object
		Time realClock = new Time();
		
		
        //************initilize required number of days varaible************
		
		int testDays;
		//Exception handling
		try {

			testDays = KAUdays;
		}catch(InputMismatchException e){
			System.out.println("Not a valid input for the number of days. the default value is set {1 day}");
			testDays = 1;
		}catch(Exception e){
			System.out.println("Not a valid input for the number of days. the default value is set {1 day}");
			testDays = 1;
		}
		
		
		//************************BUSSES*************************
        //create a Queue of busses
		int testBusses;
		//Exception handling
		try {
			testBusses = KAUbus;
			if(testBusses <= 0) throw new BusNumberException(testBusses);
		}catch(BusNumberException e) {
			System.out.println(e.getMessage() + "the default value is set {2 busses}");

			testBusses = 2;
		}catch(InputMismatchException e){
			System.out.println("Not a valid input for the number of busses. the default value is set {2 busses}");

			testBusses = 2;
		}
		
		Queue busses = new Queue(testBusses);
		int scheduledDormDeparture;
		for(int i = 0; i < testBusses; i++) {
			//formula for cal the schduled dorm departure
			scheduledDormDeparture = 30*i + 30;
			busses.enqueue(new Bus(scheduledDormDeparture, i));
		}
		
		//********************LOOPS(MAIN TASK)************************
        //initillize main while loop (number of days)
		int days = 1;
		while(testDays > 0) {
			
			//************************STUDENTS*************************
	        //create an ordered array of random students based on showed up times
			ArrayList<Student> students = generateRandomStudents(days, KAUstudent);
			FXMLmainController.randomStudentsGeneratedForP2.add(students);
			
			//create a temp array of arrived students
			ArrayList<Student> tempStudents = new ArrayList<>();

			//create pointer student
			int studentPointer = 0;
			
			//while loop on the array of students
			while (tempStudents.size() < students.size()) {
				// if clock >= realClock.endingHour
				if (Time.clock >= realClock.endingHour) break;
				
				//update availability after incrementing the clock
				updateAval(testBusses, busses);

				
				//current bus & current student
				Bus bus = busses.peek();
				Student student = students.get(studentPointer);

				//if not aval update the bus pointer to
				// the next bus and continue back
				if (!bus.isAvailable()) {
					busses.dequeue();
					busses.enqueue(bus);
					Time.incrementClock();
					continue;
				}

                //else if showed up = clock
				if (student.getShowupTime() <= Time.clock) {
                    //load to bus and increment student pointer
					bus.loadStudent(student);
					studentPointer++;
				}
				else {
					//increment the clock                     
					Time.incrementClock();
				}
				//update availability after incrementing the clock
				updateAval(testBusses, busses);

				// if capacity is full:
				if (bus.getCapacity() == 0) {
                    // 1- send the bus (update availability, time of arrival, time of departure, distance covered, fuel consumption, trips)
//					System.out.println();
					bus.sendBus(days, tempStudents, flightReport, studentsAll, flightsAll);
                    // 3- increment the bus pointer of the array
					busses.dequeue();
					busses.enqueue(bus);
                    // 4- increment the clock 
					Time.incrementClock();
                    // 5- continue loading students with updated clock
					continue;
				}
        		// else if there is at least one student in the bus(cap < 10):
				else if (bus.getCapacity() < 10) {
					// if clock meets (every 30 mins a bus should move regardless of students) && there is at least one student in the bus:
					if (bus.getScheduledDormDeparture() == Time.clock) {
	                    // 1- send the bus (update availability, time of arrival, time of departure, distance covered, fuel consumption, trips)
//						System.out.println();
						bus.sendBus(days, tempStudents, flightReport, studentsAll, flightsAll);
	                // 3- increment the bus pointer of the array
						busses.dequeue();
						busses.enqueue(bus);
                    // 4- increment the clock 
						Time.incrementClock();
	                // 5- continue loading students with updated clock
						continue;
				}
					// else if the student pointer is null && there is at least one student in the bus
					else if (studentPointer >= students.size()) {
						//send the bus
						while (true) {
							if (bus.getScheduledDormDeparture() == Time.clock) break;
							Time.incrementClock();	
						}
						bus.sendBus(days, tempStudents, flightReport, studentsAll, flightsAll);
						break;
					}
			
					}
				
				// if there are no students in the bus and clock meets scheduled dormDeparture:
				noStudentsAndTimeToMove(bus, busses);
		}//end students loop
		//loop in the array of arrived students and print the report
		for(int i = 0; i < tempStudents.size(); i++) {
			Student s = tempStudents.get(i);
//			System.out.println(s);
		}
		//reinitilize the array of students and busses and reset clock
		Time.clock = realClock.getStartingHour();
		tempStudents.clear();
		//reinitilize pointers(bus and student)
		studentPointer = 0;
		int totalDeliveredStudents = 0;
		double totalDistance = 0;
		double totalFuelConsumption = 0;
		for(Bus B : busses.arr) {
			totalDeliveredStudents = totalDeliveredStudents + B.getStudentsDelivered().size();
			totalDistance = totalDistance + B.getDistanceKm();
			totalFuelConsumption = totalFuelConsumption + B.getFuelConsumption();
			try {
				busReport.get(days - 1).add((Bus)B.clone());
			}catch(CloneNotSupportedException e) {
				System.out.println(e.getMessage());
			}
		} 
		//decrement the testDays
		testDays--;
		days++;
		//new busses
		
		//************************BUSSES*************************
		//send busses used for today for report list

        //create a Queue of busses
		busses = new Queue(testBusses);
		scheduledDormDeparture = 0;
		for(int i = 0; i < testBusses; i++) {
			//formula for cal the schduled dorm departure
			scheduledDormDeparture = 30*i + 30;
			busses.enqueue(new Bus(scheduledDormDeparture, i));
		}
		
		// total catches & misses
		int catchesOfTheDay = 0;
		for (Student S : students) {
			if(S.isCatch) catchesOfTheDay++;
		}
		totalSummary[0] = totalSummary[0] + catchesOfTheDay;
		totalSummary[1] = totalSummary[1] + (students.size() - catchesOfTheDay);
		totalSummary[3] = totalSummary[3] + totalDeliveredStudents;
		totalSummary[4] = totalSummary[4] + (students.size() - totalDeliveredStudents);
		totalSummary[5] = totalSummary[5] + totalDistance;
		totalSummary[6] = totalSummary[6] + totalFuelConsumption;
		
		
		} //end days loop
		// make the flight report accessible by other members of the class
		FXMLmainController.flightReportP1.clear();
		FXMLmainController.flightReportP1 = flightReport;
		totalSummary[2] = Flight.getNumFlights();
		ArrayList<Object> result = new ArrayList<>();
		result.add(flightReport);
		result.add(busReport);
		result.add(totalSummary);
		return result;
		
	}
	
	
	
	public static ArrayList<Object> GUI_Phase2(int KAUdays, int KAUbus, ArrayList<ArrayList<Student>> randomStudentsGenerated) {
//		clear number of flights
		Flight.setNumFlights(0);
		// report lists
		double[] totalSummary = new double[7];
		//Initialize number of catches to 0
		totalSummary[0] = 0;
		//Initialize number of total students delivered to 0
		totalSummary[3] = 0;
		//Initialize number of misses to 0
		totalSummary[1] = 0;
		//Initialize number of total students not delivered to 0
		totalSummary[4] = 0;
		//Initialize number of total fuel and distance to 0
		totalSummary[5] = 0;
		totalSummary[6] = 0;
		ArrayList<ArrayList<Flight>> flightReport = new ArrayList<ArrayList<Flight>>(KAUdays);
		ArrayList<ArrayList<Bus>> busReport = new ArrayList<ArrayList<Bus>>(KAUdays);		
		for(int i = 0; i<KAUdays; i++) {
			flightReport.add(new ArrayList<Flight>());
			busReport.add(new ArrayList<Bus>());
		}

		
		
        // create time object
		Time realClock = new Time();
		
		
        //************initilize required number of days varaible************
		
		int testDays;
		//Exception handling
		try {

			testDays = KAUdays;
		}catch(InputMismatchException e){
			System.out.println("Not a valid input for the number of days. the default value is set {1 day}");
			testDays = 1;
		}catch(Exception e){
			System.out.println("Not a valid input for the number of days. the default value is set {1 day}");
			testDays = 1;
		}
		
		
		//************************BUSSES*************************
        //create a Queue of busses
		int testBusses;
		//Exception handling
		try {
			testBusses = KAUbus;
			if(testBusses <= 0) throw new BusNumberException(testBusses);
		}catch(BusNumberException e) {
			System.out.println(e.getMessage() + "the default value is set {2 busses}");

			testBusses = 2;
		}catch(InputMismatchException e){
			System.out.println("Not a valid input for the number of busses. the default value is set {2 busses}");

			testBusses = 2;
		}
		
		Queue busses = new Queue(testBusses);
		int scheduledDormDeparture;
		int trafficTime = 0;
		
		for(int i = 0; i < testBusses; i++) {
			//formula for cal the schduled dorm departure
			scheduledDormDeparture = 15*i + 15;
			busses.enqueue(new Bus(scheduledDormDeparture, i));
			trafficTime++;
			if(trafficTime == 16) break;
		}
		if(testBusses > trafficTime) {
			for(int i = 0; i < testBusses - 16; i++) {
				//formula for cal the schduled dorm departure
				scheduledDormDeparture = 30*i + 270;
				busses.enqueue(new Bus(scheduledDormDeparture, i));
			}
		}
		
		//********************LOOPS(MAIN TASK)************************
        //initillize main while loop (number of days)
		int days = 1;
		while(testDays > 0) {
			
			//************************STUDENTS*************************
	        //create an ordered array of random students based on showed up times
			ArrayList<Student> students = randomStudentsGenerated.get(days - 1);
			
			//create a temp array of arrived students
			ArrayList<Student> tempStudents = new ArrayList<>();

			//create pointer student
			int studentPointer = 0;
			
			//while loop on the array of students
			while (tempStudents.size() < students.size()) {
				// if clock >= realClock.endingHour
				if (Time.clock >= realClock.endingHour) break;
				
				//update availability after incrementing the clock
				updateAval(testBusses, busses);

				
				//current bus & current student
				Bus bus = busses.peek();
				Student student = students.get(studentPointer);

				//if not aval update the bus pointer to
				// the next bus and continue back
				if (!bus.isAvailable()) {
					busses.dequeue();
					busses.enqueue(bus);
					Time.incrementClock();
					continue;
				}

                //else if showed up = clock
				if (student.getShowupTime() <= Time.clock) {
                    //load to bus and increment student pointer
					bus.loadStudent(student);
					studentPointer++;
				}
				else {
					//increment the clock                     
					Time.incrementClock();
				}
				//update availability after incrementing the clock
				updateAval(testBusses, busses);

				// if capacity is full:
				if (bus.getCapacity() == 0) {
					//PHASE2 specific 
		            //update the scheduled dormDeparture to the current scheduled bus departure time + 30
					// which is (period for waiting students)
					int currentSDD = bus.getScheduledDormDeparture();
					int newSDD = 0;
					int temp = 0;
					for(int i = 1; i <= busses.size(); i++) {

						
						Bus current = busses.peek();
						if(currentSDD == current.getScheduledDormDeparture()) {
							newSDD = current.getScheduledDormDeparture();
							busses.dequeue();
							busses.enqueue(current);
							continue;
						}
							temp = newSDD;
							newSDD = current.getScheduledDormDeparture();
							current.setScheduledDormDeparture(temp);
							busses.dequeue();
							busses.enqueue(current);
					}
					
					
                    // 1- send the bus (update availability, time of arrival, time of departure, distance covered, fuel consumption, trips)
//					System.out.println();
					
					bus.sendBus(days, tempStudents, flightReport, studentsAll, flightsAll, true);
                    // 3- increment the bus pointer of the array
					busses.dequeue();
					busses.enqueue(bus);
						
				
                    // 4- increment the clock 
					Time.incrementClock();
                    // 5- continue loading students with updated clock
					continue;
				}
        		// else if there is at least one student in the bus(cap < 10):
				else if (bus.getCapacity() < 10) {
					// if clock meets (every 30 mins a bus should move regardless of students) && there is at least one student in the bus:
					if (bus.getScheduledDormDeparture() == Time.clock) {
	                    // 1- send the bus (update availability, time of arrival, time of departure, distance covered, fuel consumption, trips)
//						System.out.println();
						bus.sendBus(days, tempStudents, flightReport, studentsAll, flightsAll, true);
	                // 3- increment the bus pointer of the array
						busses.dequeue();
						busses.enqueue(bus);
                    // 4- increment the clock 
						Time.incrementClock();
	                // 5- continue loading students with updated clock
						continue;
				}
					// else if the student pointer is null && there is at least one student in the bus
					else if (studentPointer >= students.size()) {
						//send the bus
						while (true) {
							if (bus.getScheduledDormDeparture() == Time.clock) break;
							Time.incrementClock();	
						}
						bus.sendBus(days, tempStudents, flightReport, studentsAll, flightsAll, true);
						break;
					}
			
					}
				
				// if there are no students in the bus and clock meets scheduled dormDeparture:
				noStudentsAndTimeToMove(bus, busses);
		}//end students loop
		//loop in the array of arrived students and print the report
		for(int i = 0; i < tempStudents.size(); i++) {
			Student s = tempStudents.get(i);
//			System.out.println(s);
		}
		//reinitilize the array of students and busses and reset clock
		Time.clock = realClock.getStartingHour();
		tempStudents.clear();
		//reinitilize pointers(bus and student)
		studentPointer = 0;
		int totalDeliveredStudents = 0;
		double totalDistance = 0;
		double totalFuelConsumption = 0;
		for(Bus B : busses.arr) {
			totalDeliveredStudents = totalDeliveredStudents + B.getStudentsDelivered().size();
			totalDistance = totalDistance + B.getDistanceKm();
			totalFuelConsumption = totalFuelConsumption + B.getFuelConsumption();
			try {
				busReport.get(days - 1).add((Bus)B.clone());
			}catch(CloneNotSupportedException e) {
				System.out.println(e.getMessage());
			}
		} 
		//decrement the testDays
		testDays--;
		days++;
		//new busses
		
		//************************BUSSES*************************
		//send busses used for today for report list

		
        //create a Queue of busses
//		busses = new Queue(testBusses);
//		scheduledDormDeparture = 0;
//		for(int i = 0; i < testBusses; i++) {
//			//formula for cal the schduled dorm departure
//			scheduledDormDeparture = 30*i + 30;
//			busses.enqueue(new Bus(scheduledDormDeparture, i));
//		}
		
		
		busses = new Queue(testBusses);
		scheduledDormDeparture = 0;
		trafficTime = 0;
		
		for(int i = 0; i < testBusses; i++) {
			//formula for cal the schduled dorm departure
			scheduledDormDeparture = 15*i + 15;
			busses.enqueue(new Bus(scheduledDormDeparture, i));
			trafficTime++;
			if(trafficTime == 16) break;
		}
		if(testBusses > trafficTime) {
			for(int i = 0; i < testBusses - 16; i++) {
				//formula for cal the schduled dorm departure
				scheduledDormDeparture = 30*i + 270;
				busses.enqueue(new Bus(scheduledDormDeparture, i));
			}
		}
		
		
		
		
		
		// total catches & misses
		int catchesOfTheDay = 0;
		for (Student S : students) {
			if(S.isCatch) catchesOfTheDay++;
		}
		totalSummary[0] = totalSummary[0] + catchesOfTheDay;
		totalSummary[1] = totalSummary[1] + (students.size() - catchesOfTheDay);
		totalSummary[3] = totalSummary[3] + totalDeliveredStudents;
		totalSummary[4] = totalSummary[4] + (students.size() - totalDeliveredStudents);
		totalSummary[5] = totalSummary[5] + totalDistance;
		totalSummary[6] = totalSummary[6] + totalFuelConsumption;
		
		
		} //end days loop
		// make the flight report accessible by other members of the class
		FXMLmainController.flightReportP1.clear();
		FXMLmainController.flightReportP1 = flightReport;
		totalSummary[2] = Flight.getNumFlights();
		ArrayList<Object> result = new ArrayList<>();
		result.add(flightReport);
		result.add(busReport);
		result.add(totalSummary);
		return result;
		
	}
	//running code before starting the GUI
		@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	
	
	
	
}