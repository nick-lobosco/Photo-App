package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.Admin;
import objects.User;

/**
 * Controller to handle logging in
 * @author Nick
 * @author Nithin
 */
public class LoginController extends Controller
{
	
	@FXML TextField username;
	@FXML Label errorMessage;
	Admin admin;
	Stage primaryStage;
	
	/**
	 * loads admin object from file if it exists and starts login scene
	 * @param primaryStage stage to set scenes on
	 */
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
	         FileInputStream fileIn = new FileInputStream("src/serializedObjects/admin");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         admin = (Admin) in.readObject();
	         if(admin == null)
	        	 admin = new Admin();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	    	 admin = new Admin();
	      } catch (ClassNotFoundException c) {
	    	 admin = new Admin();
	      }
	}
	
	/**
	 * if username entered is admin this method is called
	 * loads admin controller
	 */
	public void startAdmin(){
		try{
			FXMLLoader adminLoader = new FXMLLoader();
			adminLoader.setLocation(getClass().getResource("/view/Admin.fxml"));
			AnchorPane adminPane = (AnchorPane)adminLoader.load();
			AdminController adminController = adminLoader.getController();
			adminController.start(primaryStage, admin);
			Scene adminScene = new Scene(adminPane,200,215);
			primaryStage.setScene(adminScene);
		}catch(Exception z){
			System.out.println(z);
		}
	}
	
	/**
	 * if username entered exists this is called
	 * loads usercontroller from given user
	 * @param user user to be loaded
	 */
	public void startUser(User user){
		try{
			FXMLLoader userLoader = new FXMLLoader();
			userLoader.setLocation(getClass().getResource("/view/User.fxml"));
			AnchorPane userPane = (AnchorPane)userLoader.load();
			UserController userController = userLoader.getController();
			userController.start(primaryStage, user, admin);
			Scene userScene = new Scene(userPane,500,300);
			primaryStage.setScene(userScene);
		}catch(Exception z){
			System.out.println(z);
		}
	}
	
	/** reads username from TextField and validates */
	public void checkUsername(){
		if(username.getText().equals("admin")){
			startAdmin();
		}
		else{
			int index = admin.getList().indexOf(new User(username.getText()));
			if(index != -1)
				startUser(admin.getList().get(index));
			else
				errorMessage.setVisible(true);
				username.setText("");
		}

	}
	
}
