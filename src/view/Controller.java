package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.User;

public class Controller
{
	@FXML TextField username;
	@FXML TextField newUser;
	@FXML ListView<User> userListView;
	
	Stage primaryStage;
	ObservableList<User> userList;
	
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		userList = FXCollections.observableArrayList();
		userList.add(new User("nick"));
		userList.add(new User("user 2"));
	}
	
	public void checkUsername(ActionEvent e){
		if(username.getText().equals("admin")){
			startAdmin();
		}

	}
	
	public void startAdmin(){
		try{
			FXMLLoader adminLoader = new FXMLLoader();
			adminLoader.setController(this);
			adminLoader.setLocation(getClass().getResource("/view/Admin.fxml"));
			AnchorPane adminPane = (AnchorPane)adminLoader.load();
			System.out.println(newUser.getText());
			userListView.setItems(userList);
			//AdminController adminController = adminLoader.getController();
			//adminController.start(primaryStage);
			Scene adminScene = new Scene(adminPane,200,215);
			primaryStage.setScene(adminScene);
			
		}catch(Exception z){
			
		}
	}
	
}

