package service;

import java.util.List;

import pojo.TaxSummaryReport;
import pojo.User;

public interface UserService {
	void signUp(User user);

	User logIn(String username, String password);

	void updateUser(User user);

	User getUserById(int userID);

	void deleteUser(int userID);

	List<User> getAllUsers();

	void updatePersonalDetails(User user);

	User getUserDetails(int userID);

	TaxSummaryReport generateTaxReport(int userID, int year);

	List<TaxSummaryReport> viewAllReports(int userID);

	void logOut();
}
