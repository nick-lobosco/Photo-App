package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Pair;
import objects.Album;
import objects.Photo;
import objects.Tag;
import objects.User;

public class UserController
{
	
	User user;
	Stage primaryStage;
	ObservableList<Tag> tags;
	@FXML TextField newAlbum;
	@FXML ListView<Album> albumList;
	@FXML DatePicker startDate;
	@FXML DatePicker endDate;
	@FXML ListView<Tag> TagLV;
	
	public void start(Stage primaryStage, User user){
		//System.out.println(3);
		this.primaryStage = primaryStage;
		this.user = user;
		albumList.setItems(user.getAlbums());
		albumList.setCellFactory(param -> new XCell());
		tags = FXCollections.observableArrayList();
		TagLV.setItems(tags);
	}
	
	public void logout(){
		try{
			FXMLLoader loginLoader = new FXMLLoader();
			loginLoader.setLocation(getClass().getResource("/view/Login.fxml"));
			AnchorPane root = (AnchorPane)loginLoader.load();
			LoginController controller = loginLoader.getController();
			controller.start(primaryStage);
			Scene scene = new Scene(root,125,125);
			primaryStage.setScene(scene);
		}
		catch(Exception e){}
	}
	public void quit(){
		primaryStage.close();
	}

	public void newAlbum(){
		if(user.getAlbums().contains(new Album(newAlbum.getText()))){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Album Name");
			alert.setContentText("Invalid Album Name");
			alert.showAndWait();
		}
		else{
			user.getAlbums().add(new Album(newAlbum.getText()));
		}
		newAlbum.setText("");
	}
	
	public void search(){
		//System.out.println(startDate.getValue());
		//System.out.println(endDate.getValue());
		//System.out.println(startDate.getValue().compareTo(endDate.getValue()));
		//tags.forEach(i -> System.out.println(i));
		//tags = FXCollections.observableArrayList();
		ArrayList<Photo> matchingPhotos = new ArrayList<Photo>();
		//user.getAlbums().forEach(i -> i.getPhotos().forEach(j -> ););
		Iterator<Album> i = user.getAlbums().iterator();
		while(i.hasNext()){
			Album a = i.next();
			Iterator<Photo> p = a.getPhotos().iterator();
			while(p.hasNext()){
				Photo photo = p.next();
				if(photo.matches(startDate.getValue(), endDate.getValue(), tags))
					matchingPhotos.add(photo);
			}
		}
		ObservableList<Photo> obsList = FXCollections.observableArrayList(matchingPhotos);
		openAlbum(new Album("", obsList));
		//go through each photo in each album and see if it matches criteria
		tags.clear();
		startDate.setValue(null);
		endDate.setValue(null);
	}
	
	public void addTag(){
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Add Tag");
		
		ButtonType addButtonType = new ButtonType("Add", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField type = new TextField();
		type.setPromptText("type");
		TextField value = new TextField();
		value.setPromptText("value");

		grid.add(new Label("Type:"), 0, 0);
		grid.add(type, 1, 0);
		grid.add(new Label("Value:"), 0, 1);
		grid.add(value, 1, 1);


		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> type.requestFocus());
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == addButtonType) {
		        return new Pair<>(type.getText(), value.getText());
		    }
		    return null;
		});
		Optional<Pair<String, String>> result = dialog.showAndWait();
		while(result.get().getKey().equals("") || result.get().getValue().equals("")){
			dialog.setHeaderText("Invalid Tag");
    		result = dialog.showAndWait();
		}
		Tag newTag = new Tag(result.get().getKey(), result.get().getValue());
		if(!tags.contains(newTag))
			tags.add(newTag);
	}
	//public Album search(){	}
	 public void openAlbum(Album album){
		 
		 try{
		 	FXMLLoader albumLoader = new FXMLLoader();
			albumLoader.setLocation(getClass().getResource("/view/Album.fxml"));
			AnchorPane root = (AnchorPane)albumLoader.load();
			AlbumController controller = albumLoader.getController();
			controller.start(primaryStage, album);
			Scene scene = new Scene(root,420,450);
			primaryStage.setScene(scene);
		 }
		 catch(Exception e){e.printStackTrace();}
     }
	 
	class XCell extends ListCell<Album> {
        HBox hbox = new HBox();
        Label label = new Label("");
        Pane pane = new Pane();
        Button delButton = new Button("Del");
        Button editButton = new Button("Rename");
        Button openButton = new Button("Open");

        public void editBtn(Album album){
        	TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Edit Album Name");
            dialog.setHeaderText("");
            dialog.setContentText("Please enter new album name:");
            
            Optional<String> result = dialog.showAndWait();
            while(result.isPresent()){
            		if(!result.get().equals("")){
            			String newName = result.get();
                    	if(user.getAlbums().contains(new Album(newName))){
                    		dialog.setHeaderText("Invalid Album Name");
                            dialog.setContentText("Please enter new album name:");
                    		result = dialog.showAndWait();
                    	}
                    	else{
    	                	user.renameAlbum(album.getName(), newName);
    	                	albumList.refresh();
    	                	break;
                    	}
                }
            	else{
            		dialog.setHeaderText("Invalid Album Name");
                    dialog.setContentText("Please enter new album name:");
            		result = dialog.showAndWait();
            	}
            }
            

        }
        
        public XCell() {
            super();
            hbox.getChildren().addAll(label, pane, delButton, editButton, openButton);
            HBox.setHgrow(pane, Priority.ALWAYS);
            delButton.setOnAction(event -> getListView().getItems().remove(getItem()));
            editButton.setOnAction(event -> editBtn(getItem()));//getListView().getItems().get(getListView().getItems().indexOf(getItem()))));
            openButton.setOnAction(event -> openAlbum(getItem()));
        }

        @Override
        protected void updateItem(Album item, boolean empty) {
            super.updateItem(item, empty);
            setText(null);
            setGraphic(null);

            if (item != null && !empty) {
                label.setText(item.getDisplayInfo());
                setGraphic(hbox);
            }
        }
    }
}
