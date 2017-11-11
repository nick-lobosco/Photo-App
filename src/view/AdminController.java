package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import objects.Admin;
import objects.User;

public class AdminController
{
	
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
	
	
	
	@FXML TextField newUser;
	@FXML ListView<User> userListView;
	Admin admin;
	
	Stage primaryStage;
	
	
	public void start(Stage primaryStage, Admin admin) {
		this.admin = admin;
		this.primaryStage = primaryStage;
		userListView.setItems(admin.getUserList());
		userListView.setCellFactory(param -> new XCell());
	}
	
	public void newUser(ActionEvent e){
		this.admin.addNewUser(newUser.getText());
		newUser.setText("");
	}
	
	
	public void logout(){
		try{
			FXMLLoader loginLoader = new FXMLLoader();
			loginLoader.setLocation(getClass().getResource("/view/Login.fxml"));
			AnchorPane root = (AnchorPane)loginLoader.load();
			LoginController controller = loginLoader.getController();
			controller.start(primaryStage);
			Scene scene = new Scene(root,100,100);
			primaryStage.setScene(scene);
		}
		catch(Exception e){}
	}
}
