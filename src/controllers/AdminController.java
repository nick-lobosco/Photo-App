/**
 * @author Nick
 * @author Nithin
 */
package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import objects.Admin;
import objects.User;

/**
 * Controls the stage after the admin logs in
 * stores admin's list of users in ObservableList to be displayed in the app
 * serializes any changes to users after logging out or quitting 
 */
public class AdminController extends Controller
{
	/** Name of new user to be added to admin */
	@FXML TextField newUser;
	/** list of users to be displayed */
	@FXML ListView<User> userListView;
	/** reference to Admin object, initialized in start */
	Admin admin;
	/** stores admin's list of users in ObservableList to be displayed in the app */
	ObservableList<User> users;
	/** sets scenes to be shown by app */
	Stage primaryStage;
	
	/**
	 * sets admin scene on primaryStage
	 * @param primaryStage Stage to be used throughout app
	 * @param admin reference to Admin object
	 */
	public void start(Stage primaryStage, Admin admin) {
		this.admin = admin;
		this.primaryStage = primaryStage;
		users = FXCollections.observableArrayList();
		for(User i:admin.getList()){
			users.add(i);
		}
		userListView.setItems(users);
		userListView.setCellFactory(param -> new XCell());
	}
	
	/**
	 * gets input from user as String from newUser TextField
	 * if newUser is not already taken, creates new user object and stores in list of users
	 * @param e 
	 */
	public void newUser(ActionEvent e){
		if(!users.contains(new User(newUser.getText()))){
			users.add(new User(newUser.getText()));
		}
		else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid username");
			alert.setContentText("Invalid username");
			alert.showAndWait();
		}
		newUser.setText("");
	}
	
	/**
	 * exits app and serializes admin object
	 */
	public void quit(){
		admin.updateArray(users);
		super.parentQuit(admin, primaryStage);
	}
	/**
	 * Takes user back to login screen, serializes admin object
	 */
	public void logout(){
		admin.updateArray(users);
		super.parentLogout(admin, primaryStage);
	}
	
	/**
	 * creates class to be used for each user cell object
	 */
	static class XCell extends ListCell<User> {
        HBox hbox = new HBox();
        Label label = new Label("");
        Pane pane = new Pane();
        Button button = new Button("Del");

        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, button);
            HBox.setHgrow(pane, Priority.ALWAYS);
            button.setOnAction(event -> getListView().getItems().remove(getItem()));
        }

        @Override
        protected void updateItem(User item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                label.setText(item.toString());
                setGraphic(hbox);
            }
        }
    }
}
