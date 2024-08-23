package ServiceImpl;

import java.util.List;

import dao.AuditLogDAO;
import pojo.AuditLog;
import service.AuditLogService;

public class AuditLogServiceImpl implements AuditLogService {
	private final AuditLogDAO auditLogDAO;

	// Constructor with dependency injection
	public AuditLogServiceImpl(AuditLogDAO auditLogDAO) {
		this.auditLogDAO = auditLogDAO;
	}

	@Override
	public void logAction(AuditLog log) {
		auditLogDAO.logAction(log);
	}

	@Override
	public List<AuditLog> getAllLogs() {
		return auditLogDAO.getAllLogs();
	}
}
