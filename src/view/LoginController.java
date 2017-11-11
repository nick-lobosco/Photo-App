package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.Admin;
import objects.User;


public class LoginController
{
	
	@FXML TextField username;
	Admin admin;
	Stage primaryStage;
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		admin = new Admin();
	}
	
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
	
	public void startUser(User user){
		try{
			FXMLLoader userLoader = new FXMLLoader();
			userLoader.setLocation(getClass().getResource("/view/User.fxml"));
			AnchorPane userPane = (AnchorPane)userLoader.load();
			UserController userController = userLoader.getController();
			System.out.println(1);
			userController.start(primaryStage, user);
			System.out.println(2);
			Scene userScene = new Scene(userPane,500,300);
			primaryStage.setScene(userScene);
		}catch(Exception z){
			System.out.println(z);
		}
	}
	
	public void checkUsername(){
		if(username.getText().equals("admin")){
			startAdmin();
		}
		else{
			int index = admin.getUserList().indexOf(new User(username.getText()));
			if(index != -1)
				startUser(admin.getUserList().get(index));
		}

	}
	
}
