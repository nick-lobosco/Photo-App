package objects;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class Admin implements Serializable
{
	private ArrayList<User> list;
	//private ObservableList<User> userList;
	
	public Admin(){
		list = new ArrayList<User>();
	}
	public ArrayList<User> getList(){
		return list;
	}
	
	public void addNewUser(String username){
		list.add(new User(username));
	}
	
	public void updateArray(ObservableList<User> users){
		list.clear();
		for(User u:users){
			list.add(u);
		}
	}
}
