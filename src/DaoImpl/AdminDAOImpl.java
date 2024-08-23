package DaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dao.AdminDAO;
import dao.UserFinancialDetailsDAO;
import pojo.Admin;
import pojo.UserFinancialDetails;

public class AdminDAOImpl implements AdminDAO {
	private static final Map<Integer, Admin> adminsDB = new HashMap<>();
	private static final UserFinancialDetailsDAO userFinancialDetailsDAO = new UserFinancialDetailsDAOImpl();

	static {
		Admin admin = new Admin();
		admin.setAdminID(1);
		admin.setAdminUsername("admin");
		admin.setAdminPassword("admin123");
		adminsDB.put(admin.getAdminID(), admin);
	}

	@Override
	public Admin logIn(String adminUsername, String adminPassword) {
		return adminsDB.values().stream()
				.filter(admin -> admin.getAdminUsername().equals(adminUsername)
						&& admin.getAdminPassword().equals(adminPassword))
				.findFirst().orElseThrow(() -> new RuntimeException("Invalid credentials"));
	}

	@Override
	public List<UserFinancialDetails> getFinancialDetailsByTaxRegime(String taxRegime) {
		return userFinancialDetailsDAO.getAllDetails().stream()
				.filter(details -> details.getTaxRegime().equalsIgnoreCase(taxRegime)).collect(Collectors.toList());
	}
}
