package objects;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class User implements Serializable
{
	public String username;
	private ArrayList<Album> albums;
	//private ObservableList<Album> albums;
	
	public User(String username){
		this.username = username;
		albums = new ArrayList<Album>();
	}
	
	public ArrayList<Album> getAlbums(){
		return albums;
	}
	
	public boolean equals(Object o){
		if(!(o instanceof User))
			return false;
		if(((User)o).username.equals(username))
			return true;
		return false;
	}
	
	void newAlbum(String albumName, Photo[] pArr){
		
	}
	
	public void renameAlbum(String albumName, String newName){
		albums.get(albums.indexOf(new Album(albumName))).changeName(newName);
	}
	
	public String toString(){
		return username;
	}
	
	public void updateArray(ObservableList<Album> albums){
		this.albums.clear();
		for(Album a: albums){
			this.albums.add(a);
		}
	}
	
	
}
