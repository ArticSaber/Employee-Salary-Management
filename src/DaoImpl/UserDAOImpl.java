package DaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.UserDAO;
import pojo.User;

public class UserDAOImpl implements UserDAO {
	private static final Map<Integer, User> usersDB = new HashMap<>();
	private static final Map<Integer, User> deletedUsersDB = new HashMap<>();
	private static int userCounter = 1;

	static {
		// Pre-populate with some default users for login
		User user1 = new User("user1", "password1", "user1@example.com");
		user1.setUserID(userCounter++);
		usersDB.put(user1.getUserID(), user1);

		User user2 = new User("user2", "password2", "user2@example.com");
		user2.setUserID(userCounter++);
		usersDB.put(user2.getUserID(), user2);

		User user3 = new User("user3", "userpass", "user@example.com");
		user3.setUserID(userCounter++);
		usersDB.put(user3.getUserID(), user3);
	}

	@Override
	public void signUp(User user) {
		user.setUserID(userCounter++);
		usersDB.put(user.getUserID(), user);
	}

	@Override
	public User logIn(String username, String password) {
		return usersDB.values().stream()
				.filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password)).findFirst()
				.orElseThrow(() -> new RuntimeException("Invalid credentials"));
	}

	@Override
	public User findById(int userID) {
		return usersDB.get(userID);
	}

	@Override
	public void updateUser(User user) {
		if (usersDB.containsKey(user.getUserID())) {
			usersDB.put(user.getUserID(), user);
		} else {
			throw new RuntimeException("User not found.");
		}
	}

	@Override
	public void addUser(User user) {
		user.setUserID(userCounter++);
		usersDB.put(user.getUserID(), user);
	}

	@Override
	public User getUserById(int userID) {
		return usersDB.get(userID);
	}

	@Override
	public void deleteUser(int userID) {
		User user = usersDB.remove(userID);
		if (user != null) {
			deletedUsersDB.put(userID, user);
		} else {
			throw new RuntimeException("User not found.");
		}
	}

	@Override
	public List<User> getAllUsers() {
		return new ArrayList<>(usersDB.values());
	}

	@Override
	public List<User> getAllDeletedUsers() {
		return new ArrayList<>(deletedUsersDB.values());
	}
}
