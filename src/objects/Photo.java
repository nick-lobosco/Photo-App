package objects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.ObservableList;

public class Photo
{
	String caption;
	String path;
	LocalDateTime capture;
	ArrayList<Tag> tags = new ArrayList<Tag>();
	
//	public Photo(){
//		capture = LocalDateTime.now();
//	}
	public Photo(String path)
	{
		capture = LocalDateTime.now();
		this.path = path;
	}
	public Photo(String path, String caption){
		capture = LocalDateTime.now();
		this.caption = caption;
		this.path = path;
	}
	
	public boolean matches(LocalDate startDate, LocalDate endDate, ObservableList<Tag> searchTags){
		Iterator<Tag> tI = searchTags.iterator();
		while(tI.hasNext()){
			Tag t = tI.next();
			if(!tags.contains(t))
				return false;
		}
		if(startDate == null && endDate == null)
			return true;
		if(capture.toLocalDate().compareTo(startDate)>=0 && capture.toLocalDate().compareTo(endDate)<=0)
			return true;
		return false;
	}
	
	public String toString(){
		return this.caption;
	}
	public String getCaption(){
		return this.caption==null ? "" : this.caption;
	}
	public void setCaption(String s)
	{
		this.caption = s;
	}
	public String getPath(){
		return this.path;
	}
	public ArrayList<Tag> getTags()
	{
		return this.tags;
	}
}
