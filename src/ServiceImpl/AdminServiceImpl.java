package ServiceImpl;

import java.util.Date;
import java.util.List;

import dao.AdminDAO;
import dao.AuditLogDAO;
import dao.TaxSummaryReportDAO;
import dao.UserDAO;
import pojo.Admin;
import pojo.AuditLog;
import pojo.TaxSummaryReport;
import pojo.User;
import pojo.UserFinancialDetails;
import service.AdminService;

public class AdminServiceImpl implements AdminService {
	private final AdminDAO adminDAO;
	private final UserDAO userDAO;
	private final TaxSummaryReportDAO taxSummaryReportDAO;
	private final AuditLogDAO auditLogDAO;

	// Constructor with dependency injection
	public AdminServiceImpl(AdminDAO adminDAO, UserDAO userDAO, TaxSummaryReportDAO taxSummaryReportDAO,
			AuditLogDAO auditLogDAO) {
		this.adminDAO = adminDAO;
		this.userDAO = userDAO;
		this.taxSummaryReportDAO = taxSummaryReportDAO;
		this.auditLogDAO = auditLogDAO;

	}

	@Override
	public Admin logIn(String adminUsername, String adminPassword) {
		Admin admin = adminDAO.logIn(adminUsername, adminPassword);
		if (admin != null) {
			// Record the admin login action with timestamp
			AuditLog log = new AuditLog();
			log.setAdminID(admin.getAdminID());
			log.setAction("Admin logged in");
			log.setTimestamp(new Date()); // Set the current date and time

			auditLogDAO.logAction(log);
		} else {
			throw new RuntimeException("Invalid admin credentials");
		}
		return admin;
	}

	@Override
	public List<User> viewAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public List<TaxSummaryReport> generateReportsForAllUsers() {
		return taxSummaryReportDAO.getAllReports();
	}

	@Override
	public List<AuditLog> viewAuditLogs() {
		return auditLogDAO.getAllLogs();
	}

	@Override
	public List<UserFinancialDetails> viewFinancialDetailsByTaxRegime(String taxRegime) {
		return adminDAO.getFinancialDetailsByTaxRegime(taxRegime);
	}

	@Override
	public void logOut() {
		// Log out logic
		System.out.println("Admin has been logged out.");
		// Additional session handling or clean-up can be added here
	}
}
