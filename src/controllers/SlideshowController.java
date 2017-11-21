package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.Admin;
import objects.Album;
import objects.User;

public class SlideshowController  extends Controller{
	Album album;
	Admin admin;
	Stage primaryStage;
	User user;
	int index;
	int length;
	@FXML ImageView imView;
	
	public void start(Stage primaryStage, Album album, User user, Admin admin)
	{
		this.admin = admin;
		this.album = album;
		this.primaryStage = primaryStage;
		this.user = user;
		this.index = 0;
		this.length = album.getPhotos().size();
		
		Image i = new Image(album.getPhotos().get(index).getPath());
		imView.setImage(i);
		imView.setFitHeight(450);
		
	}
	
	public void next()
	{
		if(index<length-1)
		{
			index++;
		}
		Image i = new Image(album.getPhotos().get(index).getPath());
		imView.setImage(i);
		imView.setFitHeight(450);		
	}
	
	public void prev()
	{
		if(index>0)
		{
			index--;
		}
		Image i = new Image(album.getPhotos().get(index).getPath());
		imView.setImage(i);
		imView.setFitHeight(450);		
	}
	
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
	
	public void logout(){
		super.parentLogout(admin, primaryStage);
	}
	public void quit(){
		super.parentQuit(admin, primaryStage);
	}

}