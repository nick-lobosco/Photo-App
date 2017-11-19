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
//			            File f = new File(getClass().getResource("/img/camera000.jpg").toExternalForm());  
////			            System.out.println(f.getAbsolutePath());
//			            Image i;
//						try {
//							i = new Image(f.toURI().toURL().toString(),100,100,false,true);
//							iv.setImage(i);
//							System.out.println(f.toURI().toURL().toExternalForm());
//						} catch (MalformedURLException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
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
