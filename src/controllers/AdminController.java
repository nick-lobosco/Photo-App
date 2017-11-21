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

public class AdminController extends Controller
{
	
	@FXML TextField newUser;
	@FXML ListView<User> userListView;
	Admin admin;
	ObservableList<User> users;
	Stage primaryStage;
	
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
	
	public void quit(){
		admin.updateArray(users);
		super.parentQuit(admin, primaryStage);
	}
	public void logout(){
		admin.updateArray(users);
		super.parentLogout(admin, primaryStage);
	}
	
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
