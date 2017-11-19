package view;

import java.io.File;
import java.net.MalformedURLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import objects.Album;
import objects.Photo;

public class AlbumController {
	
	Stage primaryStage;
	Album album;
	ObservableList<Photo> obsList;
	@FXML Text title;
	@FXML ListView<Photo> listView;
	
	public void start(Stage primaryStage, Album album)
	{
		this.primaryStage = primaryStage;
		this.album = album;
		title.setText(album.getName());
//		listView.setCellFactory(p -> new PhotoCell());
		listView.setCellFactory(l -> new ListCell<Photo>(){
			 public void updateItem(final Photo item, final boolean empty) {
				 super.updateItem(item, empty);
				 Button button = new Button("(>)");
			        if (empty) {
			            setText(null);
			            setGraphic(null);
			        } else {
			            setText(item.getCaption());
			            
			            ImageView iv = new ImageView();
			            Image i = new Image(item.getPath(),50,50,false,true);
						iv.setImage(i);
						Rectangle2D viewportRect = new Rectangle2D(0, 0, 50, 50);
				        iv.setViewport(viewportRect);
			            setGraphic(iv);
			        }
			    }
		});
		listView.setItems(album.getPhotos());
		
	}
	
	public void logout()
	{
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
	
	public void add()
	{
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Resource File");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
		         new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
		         new ExtensionFilter("All Files", "*.*"));
		 File selectedFile = fileChooser.showOpenDialog(primaryStage);
		 try {
			System.out.println(selectedFile.toURI().toURL().toString());
			obsList.add(new Photo("file:C:/image.jpg","Caption1"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		 obsList.add(new Photo(selectedFile.toURI().toURL().toString()));
//		 if (selectedFile != null) {
//		    primaryStage.display(selectedFile);
//		 }
	}
	
	
//	class PhotoCell extends ListCell<Photo>{
//		HBox hbox = new HBox();
//	    Label label = new Label("(empty)");
//	    Pane pane = new Pane();
//		ImageView iv = new ImageView();
//		
//		public PhotoCell()
//		{
//			super();
//			hbox.getChildren().addAll(iv, pane, label);
//			HBox.setHgrow(pane, Priority.ALWAYS);
//		}
//		
//		@Override
//		protected void updateItem(Photo item, boolean empty)
//		{
//			super.updateItem(item, empty);
//			setText(null);
//			if(empty || item == null)
//			{
//				setGraphic(null);
//			}
//			else{
//				label.setText(item.getCaption());
//				Image i = new Image(item.getPath());
//				iv.setImage(i);
////				Rectangle2D viewportRect = new Rectangle2D(40, 35, 10,10);
////				iv.setViewport(viewportRect);
//				setGraphic(iv);
//			}
//		}
//		
//		
//	}


}
