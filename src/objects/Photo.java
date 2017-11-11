package objects;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.ObservableList;

public class Photo
{
	String caption;
	LocalDateTime capture;
	ArrayList<Tag> tags;
	
	public Photo(){
		capture = LocalDateTime.now();
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
}
