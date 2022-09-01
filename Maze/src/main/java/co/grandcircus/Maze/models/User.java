package co.grandcircus.Maze.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {
	@Id
	private String id;
	private String userName;
	private String email;
	private String password;
	private ArrayList<String> userMazes; //(maze IDs for mazes this user has made)
	private ArrayList<String> userFavorites; //(maze IDs for mazes this user favorites)
	private ArrayList<Trophy> trophies;
	public User() {
		super();
	}
	public User(String userName, String email, String password, ArrayList<String> userMazes,
			ArrayList<String> userFavorites, ArrayList<Trophy> trophies) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.userMazes = userMazes;
		this.userFavorites = userFavorites;
		this.trophies = trophies;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ArrayList<String> getUserMazes() {
		return userMazes;
	}
	public void setUserMazes(ArrayList<String> userMazes) {
		this.userMazes = userMazes;
	}
	public ArrayList<String> getUserFavorites() {
		return userFavorites;
	}
	public void setUserFavorites(ArrayList<String> userFavorites) {
		this.userFavorites = userFavorites;
	}
	public ArrayList<Trophy> getTrophies() {
		return trophies;
	}
	public void setTrophies(ArrayList<Trophy> trophies) {
		this.trophies = trophies;
	}
}
