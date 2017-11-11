package objects;

import java.time.LocalDate;
import java.util.ArrayList;

public class Album
{
	String name;
	ArrayList<Photo> photos;
	LocalDate earliestDate;
	LocalDate latestDate;
	
	public Album(String name){
		this.name = name;
		photos = new ArrayList<Photo>();
		earliestDate = LocalDate.now();
		latestDate = LocalDate.now();
	}
	
	public Album(String name, ArrayList<Photo> photos){
		this.name = name;
		this.photos = photos;
		earliestDate = LocalDate.now();
		latestDate = LocalDate.now();
	}
	
	void addPhoto(){
		
	}
	
	void deletePhoto(){
		
	}
	
	public ArrayList<Photo> getPhotos(){
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
