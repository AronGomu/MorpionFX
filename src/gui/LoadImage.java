package gui;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
*
* @author zoranpavlovic.blogspot.com
*/
public class LoadImage extends Application {
        /**
        * @param args the command line arguments
        */
        public static void main(String[] args) {
        Application.launch(args);
        }
        /*
         @Override
        public void start(Stage primaryStage) throws IOException {
	        primaryStage.setTitle("Load Image");
	 
	        StackPane sp = new StackPane();
	       
	        Image img = new Image("image/settings.png");
	        ImageView imgView = new ImageView(img);
	        sp.getChildren().add(imgView);
	 
	        //Adding HBox to the scene
	        Scene scene = new Scene(sp);
	        primaryStage.setScene(scene);
	        primaryStage.show();
        
        }
         */

		@Override
		public void start(Stage primaryStage) throws Exception {
			// TODO Auto-generated method stub
			
		}
}