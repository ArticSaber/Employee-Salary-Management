package presentation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import DaoImpl.AdminDAOImpl;
import DaoImpl.AuditLogDAOImpl;
import DaoImpl.TaxSummaryReportDAOImpl;
import DaoImpl.UserDAOImpl;
import DaoImpl.UserFinancialDetailsDAOImpl;
import ServiceImpl.AdminServiceImpl;
import ServiceImpl.AuditLogServiceImpl;
import ServiceImpl.InvestmentServiceImpl;
import ServiceImpl.TaxSummaryReportServiceImpl;
import ServiceImpl.UserFinancialDetailsServiceImpl;
import ServiceImpl.UserServiceImpl;
import dao.TaxSummaryReportDAO;
import dao.UserFinancialDetailsDAO;
import pojo.Admin;
import pojo.AuditLog;
import pojo.InvestmentDetails;
import pojo.TaxSummaryReport;
import pojo.User;
import pojo.UserFinancialDetails;
import service.AdminService;
import service.AuditLogService;
import service.InvestmentService;
import service.TaxSummaryReportService;
import service.UserFinancialDetailsService;
import service.UserService;

public class Presentation {
	private int loggedInUserID = -1;

	private final UserService userService = new UserServiceImpl(new UserDAOImpl(),
			new UserFinancialDetailsServiceImpl(new UserFinancialDetailsDAOImpl()),
			new TaxSummaryReportServiceImpl(new TaxSummaryReportDAOImpl(), new UserFinancialDetailsDAOImpl()));
	private final AdminService adminService = new AdminServiceImpl(new AdminDAOImpl(), new UserDAOImpl(),
			new TaxSummaryReportDAOImpl(), new AuditLogDAOImpl());
	private final UserFinancialDetailsService userFinancialDetailsService = new UserFinancialDetailsServiceImpl(
			new UserFinancialDetailsDAOImpl());
	TaxSummaryReportDAO taxSummaryReportDAO = new TaxSummaryReportDAOImpl();
	UserFinancialDetailsDAO userFinancialDetailsDAO = new UserFinancialDetailsDAOImpl();
	// Ensure this class exists
	TaxSummaryReportService taxSummaryReportService = new TaxSummaryReportServiceImpl(taxSummaryReportDAO,
			userFinancialDetailsDAO);
	private final AuditLogService auditLogService = new AuditLogServiceImpl(new AuditLogDAOImpl());
	private final InvestmentService investmentService = new InvestmentServiceImpl();

	public void present() {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			try {
				System.out.println("Menu:");
				System.out.println("1. User Login");
				System.out.println("2. User Sign Up");
				System.out.println("3. Admin Login");
				System.out.println("4. Exit");

				int choice = Integer.parseInt(scanner.nextLine());

				switch (choice) {
				case 1:
					handleUserLogin(scanner);
					break;
				case 2:
					handleUserSignUp(scanner);
					break;
				case 3:
					handleAdminLogin(scanner);
					break;
				case 4:
					running = false;
					System.out.println("Exiting...");
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
			}
//				finally {
//				System.out.println("Exiting presentation layer.");
//			}
		}
		scanner.close();
	}

	private void handleUserLogin(Scanner scanner) {
		System.out.println("User Login:");
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();

		User user = userService.logIn(username, password);
		if (user != null) {
			loggedInUserID = user.getUserID();
			boolean loggedIn = true;
			while (loggedIn) {
				try {
					System.out.println("User Menu:");
					System.out.println("1. Add Personal Details");
					System.out.println("2. Add Financial Details");
					System.out.println("3. Display Personal Details");
					System.out.println("4. Get Financial Details by ID and Year");
					System.out.println("5. Update Financial Details");
					System.out.println("6. Generate Tax Summary");
					System.out.println("7. Create Investment Report");
					System.out.println("8. Get Investment Report");
					System.out.println("9. Delete Financial Details");
					System.out.println("10. Delete User");
					System.out.println("11. Logout");

					int choice = Integer.parseInt(scanner.nextLine());

					switch (choice) {
					case 1:
						System.out.println("Calling addPersonalDetails...");
						addPersonalDetails();
						break;
					case 2:
						addFinancialDetails();
						break;
					case 3:
						try {
							System.out.print("Enter User ID to display details: ");
							int userID = Integer.parseInt(scanner.nextLine());

							// Check if the entered userID matches the logged-in userID
							if (userID != loggedInUserID) {
								System.out.println("Access denied. You can only view your own details.");
								break;
							}

							// Call displayAllDetails with the obtained userID
							displayAllDetails(userID);
						} catch (NumberFormatException e) {
							System.out.println("Invalid User ID format. Please enter a valid number.");
						}
						break;
					case 4:
						getFinancialDetailsByIdAndYear();
						break;
					case 5:
						updateFinancialDetails();
						break;
					case 6:
						generateTaxSummary();
						break;
					case 7:
						createInvestmentReport();
						break;
					case 8:
						getInvestmentReport();
						break;
					case 9:
						deleteFinancialDetails();
						break;
					case 10:
						deleteUser();
						loggedIn = false;
						break;
					case 11:
						loggedIn = false;
						System.out.println("Logged out.");
						break;
					default:
						System.out.println("Invalid choice. Please try again.");
					}
				} catch (Exception e) {
					System.out.println("An error occurred: " + e.getMessage());
				}
			}
		} else {
			System.out.println("Login failed. Please try again.");
		}
	}

	private void handleUserSignUp(Scanner scanner) {
		System.out.println("User Sign Up:");
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		System.out.print("Email: ");
		String email = scanner.nextLine();

		User newUser = new User(username, password, email);
		userService.signUp(newUser);
		System.out.println("User signed up successfully.");
	}

	private void handleAdminLogin(Scanner scanner) {
		System.out.println("Admin Login:");
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();

		Admin admin = adminService.logIn(username, password);
		if (admin != null) {
			boolean loggedIn = true;
			while (loggedIn) {
				try {
					System.out.println("Admin Menu:");
					System.out.println("1. View All Users");
					System.out.println("2. View Audit Logs");
					System.out.println("3. View Financial Details by Tax Regime");
					System.out.println("4.Logout ");
					int choice = Integer.parseInt(scanner.nextLine());

					switch (choice) {
					case 1:
						viewAllUsers();
						break;

					case 2:
						viewAuditLogs();
						break;
					case 3:
						viewFinancialDetailsByTaxRegime();
						break;
					case 4:
						loggedIn = false;
						System.out.println("Logged out.");
						break;
					default:
						System.out.println("Invalid choice. Please try again.");
					}
				} catch (Exception e) {
					System.out.println("An error occurred: " + e.getMessage());
				}
			}
		} else {
			System.out.println("Login failed. Please try again.");
		}
	}

	private void addPersonalDetails() {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Adding Personal Details:");

			// Collect user ID to identify which user to update
			System.out.print("Enter User ID: ");
			int userID = Integer.parseInt(scanner.nextLine());

			// Fetch existing user details
			User user = userService.getUserById(userID);
			if (user == null) {
				System.out.println("User not found.");
				return;
			}

			// Create a map to store personal details
			Map<String, String> personalDetails = new HashMap<>();

			// Collect personal details
			System.out.print("Enter Address: ");
			String address = scanner.nextLine();
			personalDetails.put("Address", address);

			System.out.print("Enter Phone Number: ");
			String phoneNumber = scanner.nextLine();
			personalDetails.put("Phone Number", phoneNumber);

			System.out.print("Enter PAN Card Number: ");
			String panCard = scanner.nextLine();
			personalDetails.put("PAN Card", panCard);

			System.out.print("Enter Aadhar Card Number: ");
			String aadharCard = scanner.nextLine();
			personalDetails.put("Aadhar Card", aadharCard);

			// Update user object with personal details
			user.setPersonalDetails(personalDetails);
			userService.updatePersonalDetails(user);

			System.out.println("Personal details updated successfully.");

		} catch (NumberFormatException e) {
			System.out.println("Invalid input for User ID. Please enter a valid number.");
		} catch (RuntimeException e) {
			System.out.println("An error occurred: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}

	}

	private void addFinancialDetails() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Add Financial Details:");

			// Collect user ID to identify which user to update
			System.out.print("Enter User ID: ");
			int userID = Integer.parseInt(scanner.nextLine());

			// Collect financial details
			System.out.print("Enter Year: ");
			int year = Integer.parseInt(scanner.nextLine());

			System.out.print("Enter Income: ");
			double income = Double.parseDouble(scanner.nextLine());

			System.out.print("Enter Deductions: ");
			double deductions = Double.parseDouble(scanner.nextLine());

			System.out.print("Enter Tax Paid: ");
			double taxPaid = Double.parseDouble(scanner.nextLine());

			// Collect investment details
			Map<String, Double> investmentDetails = new HashMap<>();
			System.out.println("Enter investment details (type 'done' to finish):");
			while (true) {
				System.out.print("Investment Type: ");
				String type = scanner.nextLine();
				if ("done".equalsIgnoreCase(type)) {
					break;
				}
				System.out.print("Investment Amount: ");
				double amount = Double.parseDouble(scanner.nextLine());
				investmentDetails.put(type, amount);
			}

			System.out.print("Enter Tax Regime (e.g., Old/New): ");
			String taxRegime = scanner.nextLine();

			UserFinancialDetails financialDetails = new UserFinancialDetails(userID, year, income, deductions, taxPaid,
					investmentDetails, taxRegime, new Date(), // createdAt
					new Date() // updatedAt
			);

			userFinancialDetailsService.addFinancialDetails(financialDetails);

			System.out.println("Financial details added successfully.");

		} catch (NumberFormatException e) {
			System.out.println("Invalid input for number fields. Please enter valid numbers.");
		} catch (RuntimeException e) {
			System.out.println("An error occurred: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An unexpected error occurred: " + e.getMessage());
		}
	}

	public void displayAllDetails(int userID) {
		try {
			// Fetch user details
			User user = userService.getUserById(userID);
			if (user == null) {
				System.out.println("User not found.");
				return;
			}

			// Display personal details
			System.out.println("Personal Details:");
			System.out.println("Username: " + user.getUsername());
			System.out.println("Email: " + user.getEmail());
			System.out.println("Tax Regime: " + user.getTaxRegime());
			System.out.println("Created At: " + user.getCreatedAt());
			System.out.println("Updated At: " + user.getUpdatedAt());

			System.out.println("\nPersonal Information:");
			Map<String, String> personalDetails = user.getPersonalDetails();
			for (Map.Entry<String, String> entry : personalDetails.entrySet()) {
				System.out.println(entry.getKey() + ": " + entry.getValue());
			}

			// Display financial details

		} catch (

		Exception e) {
			System.out.println("An error occurred while fetching details: " + e.getMessage());
		}
	}

	private void getFinancialDetailsByIdAndYear() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter User ID: ");
			int userID = scanner.nextInt();
			System.out.print("Enter Year: ");
			int year = scanner.nextInt();

			// Assume financialService is an instance of the service class
			UserFinancialDetails details = userFinancialDetailsService.getFinancialDetails(userID, year);
			if (details != null) {
				System.out.println("Financial Details: " + details);
			} else {
				System.out.println("No financial details found for the given ID and year.");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while fetching financial details: " + e.getMessage());
		}
	}

	private void updateFinancialDetails() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Financial ID: ");
			int financialID = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			// Reuse the addFinancialDetails method logic to get the details
			UserFinancialDetails details = new UserFinancialDetails();

			System.out.print("Enter Year: ");
			int year = scanner.nextInt();
			details.setYear(year);

			System.out.print("Enter Income: ");
			double income = scanner.nextDouble();
			details.setIncome(income);

			System.out.print("Enter Deductions: ");
			double deductions = scanner.nextDouble();
			details.setDeductions(deductions);

			details.setFinancialID(financialID);

			// Use the service to update the details
			userFinancialDetailsService.updateFinancialDetails(details);
			System.out.println("Financial details updated successfully.");
		} catch (Exception e) {
			System.out.println("An error occurred while updating financial details: " + e.getMessage());
		}
	}

	private void generateTaxSummary() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter User ID: ");
			int userID = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			System.out.print("Enter Tax Regime (Old/New): ");
			String taxRegime = scanner.nextLine();

			// Validate tax regime
			if (!taxRegime.equalsIgnoreCase("Old") && !taxRegime.equalsIgnoreCase("New")) {
				throw new InvalidRegimeException("Invalid tax regime. Please enter 'Old' or 'New'.");
			}

			// Call the generateReport method to get the tax summary report
			TaxSummaryReport report = taxSummaryReportService.generateReport(userID);
			if (report != null) {
				System.out.println("Tax Summary for User ID " + userID + ":");
				System.out.println("Year: " + report.getYear());
				System.out.println("Tax Regime: " + report.getTaxRegime());
				System.out.printf("Salary Before Tax: ₹%.2f%n", report.getSalaryBeforeTax());
				System.out.printf("Salary After Tax: ₹%.2f%n", report.getSalaryAfterTax());
				System.out.println("Tax Summary: ");
				for (Map.Entry<String, Double> entry : report.getTaxSummary().entrySet()) {
					System.out.printf("  %s: ₹%.2f%n", entry.getKey(), entry.getValue());
				}
				System.out.println("Report Generated At: " + report.getGeneratedAt());
			} else {
				System.out.println("No tax summary found for the given User ID.");
			}
		} catch (InvalidRegimeException e) {
			System.out.println(e.getMessage());
			// Optionally log the error if needed
		} catch (Exception e) {
			System.out.println("An error occurred while generating the tax summary: " + e.getMessage());
		}
	}

	private void createInvestmentReport() {
		try {
			Scanner scanner = new Scanner(System.in);

			// Collect necessary details from the user
			System.out.print("Enter Investment ID: ");
			int investmentID = scanner.nextInt();
			System.out.print("Enter User ID: ");
			int userID = scanner.nextInt();

			// Collect other details with yes/no prompts
			System.out.print("Do you want to enter Total Income? (yes/no): ");
			String input = scanner.next().trim().toLowerCase();
			double income = "yes".equals(input) ? scanner.nextDouble() : 0;

			System.out.print("Do you want to enter LIC Premium? (yes/no): ");
			input = scanner.next().trim().toLowerCase();
			double licPremium = "yes".equals(input) ? scanner.nextDouble() : 0;

			System.out.print("Do you want to enter PPF Investment? (yes/no): ");
			input = scanner.next().trim().toLowerCase();
			double ppfInvestment = "yes".equals(input) ? scanner.nextDouble() : 0;

			System.out.print("Do you want to enter Short-term Gains? (yes/no): ");
			input = scanner.next().trim().toLowerCase();
			double shortTermGains = "yes".equals(input) ? scanner.nextDouble() : 0;

			System.out.print("Do you want to enter Long-term Gains? (yes/no): ");
			input = scanner.next().trim().toLowerCase();
			double longTermGains = "yes".equals(input) ? scanner.nextDouble() : 0;

			System.out.print("Do you want to enter Financial Year (e.g., 2023)? (yes/no): ");
			input = scanner.next().trim().toLowerCase();
			int year = "yes".equals(input) ? scanner.nextInt() : 0;

			System.out.print("Do you want to enter Investment Date (yyyy-mm-dd)? (yes/no): ");
			input = scanner.next().trim().toLowerCase();
			Date investmentDate = "yes".equals(input) ? java.sql.Date.valueOf(scanner.next())
					: java.sql.Date.valueOf("1970-01-01");

			System.out.print("Enter tax Regime (Old/New) : ");
			input = scanner.next().trim().toLowerCase();
			String taxRegime = "yes".equals(input) ? scanner.next() : "InvalidRegime";

			InvestmentDetails details = new InvestmentDetails(investmentID, userID, income, licPremium, ppfInvestment,
					shortTermGains, longTermGains, year, investmentDate, taxRegime);

			// Call the service method to create investment details
			String investmentReport = investmentService.createInvestmentDetails(details);
			System.out.println("Investment Report Created: \n" + investmentReport);

		} catch (Exception e) {
			System.out.println("An error occurred while creating investment report: " + e.getMessage());
		}
	}

	private void getInvestmentReport() {
		try {
			Scanner scanner = new Scanner(System.in);

			// Collect necessary details from the user
			System.out.print("Enter User ID: ");
			int userID = scanner.nextInt();
			System.out.print("Enter Financial Year (e.g., 2023): ");
			int year = scanner.nextInt();

			// Call the service method to get investment details
			InvestmentDetails investmentDetails = investmentService.getInvestmentDetails(userID, year);

			if (investmentDetails == null) {
				System.out.println("No investment details found for User ID: " + userID + " and Year: " + year);
			} else {
				// Display investment details
				System.out.println("Investment Report:");
				System.out.println("Investment ID: " + investmentDetails.getInvestmentID());
				System.out.println("User ID: " + investmentDetails.getUserID());
				System.out.println("Total Income: " + investmentDetails.getIncome());
				System.out.println("LIC Premium: " + investmentDetails.getLicPremium());
				System.out.println("PPF Investment: " + investmentDetails.getPpfInvestment());
				System.out.println("Short-term Gains: " + investmentDetails.getShortTermGains());
				System.out.println("Long-term Gains: " + investmentDetails.getLongTermGains());
				System.out.println("Financial Year: " + investmentDetails.getYear());
				System.out.println("Investment Date: " + investmentDetails.getInvestmentDate());
				System.out.println("Tax Regime: " + investmentDetails.getTaxRegime());
			}

		} catch (Exception e) {
			System.out.println("An error occurred while fetching investment report: " + e.getMessage());
		}
	}

	private void deleteUser() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter User ID: ");
			int userID = scanner.nextInt();

			// Assume userService is an instance of the service class
			userService.deleteUser(userID);
			System.out.println("User deleted successfully.");
		} catch (Exception e) {
			System.out.println("An error occurred while deleting the user: " + e.getMessage());
		}
	}

	private void deleteFinancialDetails() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Financial ID: ");
			int financialID = scanner.nextInt();

			// Assume financialService is an instance of the service class
			userFinancialDetailsService.deleteFinancialDetails(financialID);
			System.out.println("Financial details deleted successfully.");
		} catch (Exception e) {
			System.out.println("An error occurred while deleting financial details: " + e.getMessage());
		}
	}

	private void viewAllUsers() {
		try {
			// Assume userService is an instance of the service class
			List<User> users = userService.getAllUsers();
			if (users.isEmpty()) {
				System.out.println("No users found.");
			} else {
				System.out.println("All Users:");
				for (User user : users) {
					System.out.println(user);
				}
			}
		} catch (Exception e) {
			System.out.println("An error occurred while fetching all users: " + e.getMessage());
		}
	}

	private void viewAuditLogs() {
		try {
			// Assume auditService is an instance of the service class
			List<AuditLog> logs = auditLogService.getAllLogs();
			if (logs.isEmpty()) {
				System.out.println("No audit logs available.");
			} else {
				System.out.println("Audit Logs:");
				for (AuditLog log : logs) {
					System.out.println(log);
				}
			}
		} catch (Exception e) {
			System.out.println("An error occurred while fetching audit logs: " + e.getMessage());
		}
	}

	private void viewFinancialDetailsByTaxRegime() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Tax Regime (New Regime/Old Regime): ");
			String taxRegime = scanner.nextLine();

			// Assume financialService is an instance of the service class
			List<UserFinancialDetails> details = userFinancialDetailsService.getFinancialDetailsByTaxRegime(taxRegime);
			if (details.isEmpty()) {
				System.out.println("No financial details found for the given tax regime.");
			} else {
				System.out.println("Financial Details by Tax Regime:");
				for (UserFinancialDetails detail : details) {
					System.out.println(detail);
				}
			}
		} catch (Exception e) {
			System.out.println("An error occurred while fetching financial details by tax regime: " + e.getMessage());
		}
	}

}
