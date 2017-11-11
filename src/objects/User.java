package objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User
{
	public String username;
	private ObservableList<Album> albums;
	
	public User(String username){
		this.username = username;
		albums = FXCollections.observableArrayList();
		albums.add(new Album("new album"));
	}
	
	public ObservableList<Album> getAlbums(){
		return albums;
	}
	public String toString(){
		return username;
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
	
	
}
