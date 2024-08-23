package service;

import java.util.List;

import pojo.Admin;
import pojo.AuditLog;
import pojo.TaxSummaryReport;
import pojo.User;
import pojo.UserFinancialDetails;

public interface AdminService {
	Admin logIn(String adminUsername, String adminPassword);

	List<User> viewAllUsers();

	List<TaxSummaryReport> generateReportsForAllUsers();

	List<AuditLog> viewAuditLogs();

	List<UserFinancialDetails> viewFinancialDetailsByTaxRegime(String taxRegime);

	void logOut();
}
