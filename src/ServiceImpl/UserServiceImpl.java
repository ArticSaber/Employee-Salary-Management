package ServiceImpl;

import java.util.List;

import DaoImpl.TaxSummaryReportDAOImpl;
import DaoImpl.UserDAOImpl;
import DaoImpl.UserFinancialDetailsDAOImpl;
import dao.UserDAO;
import pojo.TaxSummaryReport;
import pojo.User;
import pojo.UserFinancialDetails;
import service.TaxSummaryReportService;
import service.UserFinancialDetailsService;
import service.UserService;

public class UserServiceImpl implements UserService {
	private final UserDAO userDAO;
	private final UserFinancialDetailsService userFinancialDetailsService;
	private final TaxSummaryReportService taxSummaryReportService;

	public UserServiceImpl(UserDAO userDAO, UserFinancialDetailsService userFinancialDetailsService,
			TaxSummaryReportService taxSummaryReportService) {
		this.userDAO = userDAO;
		this.userFinancialDetailsService = userFinancialDetailsService;
		this.taxSummaryReportService = taxSummaryReportService;
	}

	// Default constructor
	public UserServiceImpl() {
		this.userDAO = new UserDAOImpl();
		this.userFinancialDetailsService = new UserFinancialDetailsServiceImpl(new UserFinancialDetailsDAOImpl());
		this.taxSummaryReportService = new TaxSummaryReportServiceImpl(new TaxSummaryReportDAOImpl(), null);
	}

	@Override
	public void signUp(User user) {
		userDAO.signUp(user);
	}

	@Override
	public User logIn(String username, String password) {
		return userDAO.logIn(username, password);
	}

	@Override
	public void updateUser(User user) {
		userDAO.updateUser(user);
	}

	@Override
	public User getUserById(int userID) {
		return userDAO.findById(userID);
	}

	@Override
	public void deleteUser(int userID) {
		userDAO.deleteUser(userID);
	}

	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	@Override
	public void updatePersonalDetails(User user) {
		User existingUser = userDAO.findById(user.getUserID());
		if (existingUser != null) {
			existingUser.setPersonalDetails(user.getPersonalDetails());
			userDAO.updateUser(existingUser);
		} else {
			throw new RuntimeException("User not found");
		}
	}

	@Override
	public User getUserDetails(int userID) {
		return userDAO.findById(userID);
	}

	@Override
	public TaxSummaryReport generateTaxReport(int userID, int year) {
		UserFinancialDetails financialDetails = userFinancialDetailsService.getFinancialDetails(userID, year);
		if (financialDetails == null) {
			throw new RuntimeException("Financial details not found for the specified year");
		}

		return taxSummaryReportService.generateTaxSummary(financialDetails);
	}

	@Override
	public List<TaxSummaryReport> viewAllReports(int userID) {
		return taxSummaryReportService.getAllReportsByUserID(userID);
	}

	@Override
	public void logOut() {
		// Implement logout logic here if needed
		System.out.println("Logged Out user");
	}
}
