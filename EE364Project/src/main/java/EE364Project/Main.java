package EE364Project;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * Main class to run the GUI with the specified stage and attributes
 * 
 * @author Azeez
 *
 */
public class Main extends Application {
	/**
	 * this is the primary method that is ran automatically to create the GUI
	 */
	@Override
	public void start(Stage primaryStage) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("FXMLmain.fxml"));
			Scene scene = new Scene(root,1450,960);


			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
			primaryStage.setMinWidth(1490);
			primaryStage.setMinHeight(960);
			primaryStage.setMaxWidth(1490);
			primaryStage.setMaxHeight(910);
			
			primaryStage.setTitle("KAU Transportation System");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * the main method calling the start method to start displaying the GUI
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
