package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.Admin;
import objects.Album;
import objects.Photo;
import objects.User;

/**
 * Controller to display photo from album
 * @author Nick
 * @author Nithin
 */
public class DisplayController extends Controller
{
	Admin admin;
	Photo photo;
	Album album;
	Stage primaryStage;
	User user;
	@FXML ImageView imView;
	@FXML Label label;
	@FXML Button back;
	
	/** sets the scene on primary Stage and stores params in class 
	 * @param primaryStage stage for each scene
	 * @param album album to be loaded
	 * @param user user that the album belongs to
	 * @param admin admin object that the user belongs to
	 * @param photo photo to be displayed
	 * */
	public void start(Stage primaryStage, Photo photo, Album album, User user, Admin admin)
	{
		this.admin = admin;
		this.photo = photo;
		this.album = album;
		this.primaryStage = primaryStage;
		this.user = user;
		
		Image i = new Image(photo.getPath());
		imView.setImage(i);
		imView.setFitHeight(340);
		
		label.setText("Caption: "+ photo.getCaption() + "\n"+
					   "Tags: "+ photo.tagString() + "\n"+
						"Date: "+ photo.formatedDate());
		
	}
	/** sets scene to previous scene */
	public void back()
	{
		 try{
			 	FXMLLoader albumLoader = new FXMLLoader();
				albumLoader.setLocation(getClass().getResource("/view/Album.fxml"));
				AnchorPane root = (AnchorPane)albumLoader.load();
				AlbumController controller = albumLoader.getController();
				controller.start(primaryStage, album, user, admin);
				Scene scene = new Scene(root,420,450);
				primaryStage.setScene(scene);
			 }
			 catch(Exception e){e.printStackTrace();}
	}
	
	/** serializes admin and sets scene to login */
	public void logout(){
		super.parentLogout(admin, primaryStage);
	}
	/** serializes admin and exits app */
	public void quit(){
		super.parentQuit(admin, primaryStage);
	}

}
