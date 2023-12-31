package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent parent = FXMLLoader.load(
					getClass().getResource("Main.fxml"));
			
			Scene scene = new Scene(parent);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Patient Records");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			System.out.println("Unable to load Main.fxml file");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
