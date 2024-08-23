package DaoImpl;

import java.util.ArrayList;
import java.util.List;

import dao.AuditLogDAO;
import pojo.AuditLog;

public class AuditLogDAOImpl implements AuditLogDAO {
	private static final List<AuditLog> auditLogsDB = new ArrayList<>();
	private static int logCounter = 1;

	@Override
	public void logAction(AuditLog log) {
		log.setLogID(logCounter++);
		auditLogsDB.add(log);
	}

	@Override
	public List<AuditLog> getAllLogs() {
		return new ArrayList<>(auditLogsDB);
	}
}
