package application;
	
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	static Stage stage;
	
	private static StackPane pane = new StackPane();
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/controller/Menu.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pane.getChildren().add(root);
			
			Scene scene = new Scene(pane);
			//scene.getStylesheets().add(getClass().getResource("/gui/application.css").toExternalForm());
			primaryStage.setTitle("Morpionote");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		System.exit(1);
	}
}
