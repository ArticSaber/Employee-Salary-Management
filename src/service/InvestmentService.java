package service;

import pojo.InvestmentDetails;

public interface InvestmentService {
	String createInvestmentDetails(InvestmentDetails details);

	InvestmentDetails getInvestmentDetails(int userID, int year);

	void generateTaxReport(InvestmentDetails details);
}
