package pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {
	private static int userCounter = 1; // Static counter to assign unique user IDs
	private int userID;
	private String username;
	private String password;
	private String email;
	private Map<String, String> personalDetails = new HashMap<>();
	private String taxRegime;
	private Date createdAt;
	private Date updatedAt;

	public User() {
		this.userID = userCounter++;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public User(String username, String password, String email, Map<String, String> personalDetails, String taxRegime) {
		this.userID = userCounter++;
		this.username = username;
		this.password = password;
		this.email = email;
		this.personalDetails = personalDetails;
		this.taxRegime = taxRegime;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	public User(String username, String password, String email) {
		this.userID = userCounter++;
		this.username = username;
		this.password = password;
		this.email = email;
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	// Getters and Setters
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Map<String, String> getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(Map<String, String> personalDetails) {
		this.personalDetails = personalDetails;
	}

	public String getTaxRegime() {
		return taxRegime;
	}

	public void setTaxRegime(String taxRegime) {
		this.taxRegime = taxRegime;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Users are " + "userID=" + userID + ", username='" + username + '\'' + ", email='" + email + '\''
				+ ", personalDetails=" + personalDetails + ", taxRegime='" + taxRegime + '\'' + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + '\n';
	}
}
