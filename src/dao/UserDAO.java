package dao;

import java.util.List;

import pojo.User;

public interface UserDAO {
	void signUp(User user);

	User logIn(String username, String password);

	User findById(int userID);

	void updateUser(User user);

	void addUser(User user);

	User getUserById(int userID);

	void deleteUser(int userID);

	List<User> getAllUsers();

	List<User> getAllDeletedUsers(); // Assuming you want this method
}
