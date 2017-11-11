package objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Admin
{
	private ObservableList<User> userList;
	
	public Admin(){
		userList = FXCollections.observableArrayList();
		userList.add(new User("nick"));
	}
	
	public ObservableList<User> getUserList(){
		return userList;
	}
	
	public void addNewUser(String username){
		userList.add(new User(username));
	}
}
