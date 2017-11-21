package controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.Admin;

public class Controller
{

	public void parentQuit(Admin admin, Stage primaryStage){
		try {
	         FileOutputStream fileOut = 
	         new FileOutputStream("src/serializedObjects/admin");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(admin);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		primaryStage.close();
	}
	
	public void parentLogout(Admin admin, Stage primaryStage){
		try {
	         FileOutputStream fileOut = 
	         new FileOutputStream("src/serializedObjects/admin");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(admin);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		try{
			FXMLLoader loginLoader = new FXMLLoader();
			loginLoader.setLocation(getClass().getResource("/view/Login.fxml"));
			AnchorPane root = (AnchorPane)loginLoader.load();
			LoginController controller = loginLoader.getController();
			controller.start(primaryStage);
			Scene scene = new Scene(root,125,125);
			primaryStage.setScene(scene);
		}
		catch(Exception e){System.out.println(e);}
	}
}
