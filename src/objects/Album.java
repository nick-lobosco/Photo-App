package objects;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Album
{
	String name;
	ObservableList<Photo> photos;
	LocalDate earliestDate;
	LocalDate latestDate;
	
	public Album(String name){
		this.name = name;
		photos = FXCollections.observableArrayList();
		earliestDate = LocalDate.now();
		latestDate = LocalDate.now();
		photos.add(new Photo("file:C:/image.jpg","Caption1"));
//		photos.add(new Photo("file:C:/Users/basis_000/Desktop/image.jpg","Caption1"));
		photos.add(new Photo(getClass().getResource("/data/camera000.jpg").toExternalForm(),"Caption2"));
	}
	
	public Album(String name, ObservableList<Photo> photos){
		this.name = name;
		this.photos = photos;
		earliestDate = LocalDate.now();
		latestDate = LocalDate.now();
	}
	
	public void addPhoto(Photo p){
		this.photos.add(p);
	}
	
	public ObservableList<Photo> getPhotos(){
		return photos;
	}
	public String getName(){
		return name;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof Album))
			return false;
		if(((Album)o).name.equals(name))
			return true;
		return false;
	}
	
	public String getDisplayInfo(){
		return name + " (" + photos.size() + ")\n" + earliestDate.toString() + " - " + latestDate.toString();
	}
	
	public void changeName(String newName){
		this.name = newName;
	}
	
	/*Photo[] getPhotos(Calendar start, Calendar end){
		
	}*/
}
