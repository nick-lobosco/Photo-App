package view;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Pair;
import objects.Album;
import objects.Photo;
import objects.Tag;
import objects.User;

public class AlbumController {
	
	Stage primaryStage;
	Album album;
	User user;
	ObservableList<Photo> obsList;
	@FXML Text title;
	@FXML ListView<Photo> listView;
	@FXML MenuItem addTag;
	@FXML MenuItem deleteTag;
	@FXML MenuItem move;
	@FXML MenuItem copy;
	
	public void start(Stage primaryStage, Album album, User user)
	{
		this.primaryStage = primaryStage;
		this.album = album;
		this.user = user;
		this.obsList = album.getPhotos();
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
		
		listView.setItems(obsList);
		
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
	public void goBack()
	{
		try{
			FXMLLoader userLoader = new FXMLLoader();
			userLoader.setLocation(getClass().getResource("/view/User.fxml"));
			AnchorPane userPane = (AnchorPane)userLoader.load();
			UserController userController = userLoader.getController();
			userController.start(primaryStage, user);
			Scene userScene = new Scene(userPane,500,300);
			primaryStage.setScene(userScene);
		}catch(Exception z){
			System.out.println(z);
		}
	}
	
	public void view()
	{
		if(!listView.getSelectionModel().isEmpty())
		{
			Photo p = (listView.getSelectionModel().getSelectedItem());
		
			try{
				FXMLLoader dispLoader = new FXMLLoader();
				dispLoader.setLocation(getClass().getResource("/view/PhotoDisplay.fxml"));
				SplitPane dispPane = (SplitPane)dispLoader.load();
				DisplayController dispController = dispLoader.getController();
				dispController.start(primaryStage, p, album, user);
				Scene dispScene = new Scene(dispPane,600,440);
				primaryStage.setScene(dispScene);
			}catch(Exception z){
				System.out.println(z);
			}
		}
	}
	
	public void slideshow()
	{
		if(!obsList.isEmpty())
		{
		
			try{
				FXMLLoader dispLoader = new FXMLLoader();
				dispLoader.setLocation(getClass().getResource("/view/Slideshow.fxml"));
				SplitPane dispPane = (SplitPane)dispLoader.load();
				SlideshowController dispController = dispLoader.getController();
				dispController.start(primaryStage, album, user);
				Scene dispScene = new Scene(dispPane,700,500);
				primaryStage.setScene(dispScene);
			}catch(Exception z){
				System.out.println(z);
			}
		}
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
			album.getPhotos().add(new Photo(selectedFile.toURI().toURL().toString()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete()
	{
		if(!listView.getSelectionModel().isEmpty()){
			obsList.remove(listView.getSelectionModel().getSelectedIndex());
		}
	}
	
	public void save(){
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("Save Album");
		
		ButtonType okType = new ButtonType("Save", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okType, ButtonType.CANCEL);

		// Create the type and value labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField albumName = new TextField();
		albumName.setPromptText("album name");
		grid.add(new Label("Enter Album Name:"), 0, 0);
		grid.add(albumName, 1, 0);

		dialog.getDialogPane().setContent(grid);
		
		// Request focus on the caption field by default.
		Platform.runLater(() -> albumName.requestFocus());
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == okType) {
		        return albumName.getText();
		    }
		    return null;
		});
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent())
		{
			String name = result.get();
			
			if(user.getAlbums().contains(new Album(name))){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid Album Name");
				alert.setContentText("Invalid Album Name");
				alert.showAndWait();
			}
			else{
				ObservableList<Photo> newP = FXCollections.observableArrayList();
				for(Photo p: album.getPhotos())
				{
//					newP.add(new Photo(p.getPath(),p.getCaption(), p.getTags()));
					newP.add(p.copy());
				}
				
				user.getAlbums().add(new Album(name, newP));
			}
		}
	}
	
	public void editCaption()
	{
		Photo photo = listView.getSelectionModel().getSelectedItem();
		if(photo == null)
		{
			return;
		}
		
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle("EditCaption");
		
		ButtonType okType = new ButtonType("Ok", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okType, ButtonType.CANCEL);

		// Create the type and value labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField caption = new TextField();
		caption.setPromptText("caption");
		grid.add(new Label("Enter Caption:"), 0, 0);
		grid.add(caption, 1, 0);

		dialog.getDialogPane().setContent(grid);
		
		// Request focus on the caption field by default.
		Platform.runLater(() -> caption.requestFocus());
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == okType) {
		        return caption.getText();
		    }
		    return null;
		});
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent())
		{
			int index = listView.getSelectionModel().getSelectedIndex();
			obsList.remove(index);
			Photo copy = photo.copy();
			copy.setCaption(result.get());
			obsList.add(index,copy);
			listView.getSelectionModel().select(index);
		}
	}
	
	public void moveorcopy(ActionEvent e)
	{

		Photo photo = listView.getSelectionModel().getSelectedItem();
		if(photo == null)
		{
			return;
		}
		
		MenuItem item = (MenuItem)e.getSource();
		//Button btn = (Button) e.getSource();
		Boolean isMove = item==move;
		
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle(isMove ? "Move Photo":"Copy Photo");
		
		ButtonType okType = new ButtonType("Ok", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(okType, ButtonType.CANCEL);

		// Create the type and value labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField albumName = new TextField();
		albumName.setPromptText("album name");
		grid.add(new Label("Enter Album Name:"), 0, 0);
		grid.add(albumName, 1, 0);

		dialog.getDialogPane().setContent(grid);
		
		// Request focus on the caption field by default.
		Platform.runLater(() -> albumName.requestFocus());
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == okType) {
		        return albumName.getText();
		    }
		    return null;
		});
		Optional<String> result = dialog.showAndWait();
		
		if(result.isPresent())
		{
			String name = result.get();
			
			if(!user.getAlbums().contains(new Album(name))){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Invalid Album Name");
				alert.setContentText("Invalid Album Name: Album Does not exist");
				alert.showAndWait();
			}
			else{
				Album al = user.getAlbums().get(user.getAlbums().indexOf(new Album(name)));
				Photo photoRef = photo;
				al.addPhoto(photoRef);
				//al.addPhoto(photo.copy());
				if(isMove)
				{
					this.album.getPhotos().remove(photo);
				}
			}
		}
	}
	
	public void editTag(ActionEvent e)
	{
		//System.out.println(e);
		MenuItem item = (MenuItem)e.getSource();
		if(listView.getSelectionModel().getSelectedItem() ==null)
		{
			return;
		}
		ArrayList<Tag> tags = listView.getSelectionModel().getSelectedItem().getTags();
		// Create the custom dialog.
				Dialog<Pair<String, String>> dialog = new Dialog<>();
				dialog.setTitle("Add Tag");
				
				ButtonType addButtonType = new ButtonType("Add", ButtonData.OK_DONE);
				ButtonType delButtonType = new ButtonType("Delete", ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().addAll(item==addTag ? addButtonType:delButtonType
						, ButtonType.CANCEL);

				// Create the type and value labels and fields.
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

				// Request focus on the type field by default.
				Platform.runLater(() -> type.requestFocus());
				
				dialog.setResultConverter(dialogButton -> {
				    if (dialogButton == addButtonType || dialogButton == delButtonType) {
				        return new Pair<>(type.getText(), value.getText());
				    }
				    return null;
				});
				Optional<Pair<String, String>> result = dialog.showAndWait();
				if(result.isPresent())
				{
					while((result.get().getKey().equals("") || result.get().getValue().equals(""))){
						dialog.setHeaderText("Invalid Tag");
			    		result = dialog.showAndWait();
					}
					
					Tag newTag = new Tag(result.get().getKey(), result.get().getValue());
					if(item == addTag){
						if(!tags.contains(newTag))
							System.out.println("Addig:"+ newTag);
							tags.add(newTag);
					}
					if(item == deleteTag)
					{
						if(tags.remove(newTag))
						{
							System.out.println("Deleted: "+ newTag);
						}						
					}
				}
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
