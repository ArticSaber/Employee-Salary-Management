package service;

import java.util.List;

import pojo.UserFinancialDetails;

public interface UserFinancialDetailsService {
	void addFinancialDetails(UserFinancialDetails details);

	UserFinancialDetails getFinancialDetails(int userID, int year);

	void updateFinancialDetails(UserFinancialDetails details);

	void deleteFinancialDetails(int financialID);

	UserFinancialDetails getFinancialDetailsByUserID(int userID);

	List<UserFinancialDetails> getFinancialDetailsByTaxRegime(String taxRegime);

}
