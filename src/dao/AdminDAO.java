package dao;

import java.util.List;

import pojo.Admin;
import pojo.UserFinancialDetails;

public interface AdminDAO {
	Admin logIn(String adminUsername, String adminPassword);

	List<UserFinancialDetails> getFinancialDetailsByTaxRegime(String taxRegime);

}
