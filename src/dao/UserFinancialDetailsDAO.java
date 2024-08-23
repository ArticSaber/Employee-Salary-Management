package dao;

import java.util.List;

import pojo.UserFinancialDetails;

public interface UserFinancialDetailsDAO {
	void addFinancialDetails(UserFinancialDetails details);

	UserFinancialDetails findByUserIDAndYear(int userID, int year);

	void updateFinancialDetails(UserFinancialDetails details);

	void deleteFinancialDetails(int financialID);

	UserFinancialDetails findById(int financialID);

	UserFinancialDetails getFinancialDetailsByUserID(int userID);

	List<UserFinancialDetails> getAllDetails();

}
