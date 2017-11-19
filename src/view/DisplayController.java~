package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.Album;
import objects.Photo;
import objects.User;

public class DisplayController {
	Photo photo;
	Album album;
	Stage primaryStage;
	User user;
	@FXML ImageView imView;
	@FXML Label label;
	@FXML Button back;
	
	public void start(Stage primaryStage, Photo photo, Album album, User user)
	{
		this.photo = photo;
		this.album = album;
		this.primaryStage = primaryStage;
		this.user = user;
		
		Image i = new Image(photo.getPath());
		imView.setImage(i);
		imView.setFitHeight(240);
		
		label.setText("Caption: "+ photo.getCaption() + "\n"+
					   "Tags: "+ photo.tagString() + "\n"+
						"Date: "+ photo.formatedDate());
		
	}
	
	public void back()
	{
		 try{
			 	FXMLLoader albumLoader = new FXMLLoader();
				albumLoader.setLocation(getClass().getResource("/view/Album.fxml"));
				AnchorPane root = (AnchorPane)albumLoader.load();
				AlbumController controller = albumLoader.getController();
				controller.start(primaryStage, album, user);
				Scene scene = new Scene(root,420,450);
				primaryStage.setScene(scene);
			 }
			 catch(Exception e){e.printStackTrace();}
	}

}
