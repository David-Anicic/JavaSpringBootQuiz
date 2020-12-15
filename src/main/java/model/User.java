package model;

import java.sql.Date;

public class User {

	private int UserID;
	private String Name;
	private String Surname;
	private String Username;
	private String Password;
	private Date Date;
	private String TypeOfUser;
	private int Count;

	public int getUserID() {
		return UserID;
	}

	public int getCount() {
		return Count;
	}

	public void setCount(int count) {
		Count = count;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Date getDate() {
		return Date;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public String getTypeOfUser() {
		return TypeOfUser;
	}

	public void setTypeOfUser(String typeOfUser) {
		TypeOfUser = typeOfUser;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "count="+this.getCount();
	}
}
