package co.grandcircus.Maze.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {
	@Id
	private String id;
	private String username;
	private String email;
	private String password;
	private ArrayList<String> userMazes; //(maze titles for mazes this user has made)
	private ArrayList<String> userFavorites; //(maze titles for mazes this user favorites)
	private ArrayList<String> userTempMazes; //(maze titles for mazes that are being edited)
	private ArrayList<Trophy> trophies;
	public User() {
		super();
	}
	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.userMazes = new ArrayList<String>();
		this.userFavorites = new ArrayList<String>();
		this.userTempMazes = new ArrayList<String>();
		this.trophies = new ArrayList<Trophy>();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public ArrayList<String> getUserTempMazes() {
		return userTempMazes;
	}
	public void setUserTempMazes(ArrayList<String> userTempMazes) {
		this.userTempMazes = userTempMazes;
	}
	public ArrayList<Trophy> getTrophies() {
		return trophies;
	}
	public void setTrophies(ArrayList<Trophy> trophies) {
		this.trophies = trophies;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
