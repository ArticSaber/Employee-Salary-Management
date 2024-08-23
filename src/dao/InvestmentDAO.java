package dao;

import java.util.List;

import pojo.InvestmentDetails;

public interface InvestmentDAO {
	void addInvestmentDetails(InvestmentDetails details);

	InvestmentDetails findByUserIDAndYear(int userID, int year);

	void updateInvestmentDetails(InvestmentDetails details);

	void deleteInvestmentDetails(int investmentID);

	List<InvestmentDetails> getAllInvestmentDetails();
}
