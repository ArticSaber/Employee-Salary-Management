package dao;

import java.util.List;

import pojo.AuditLog;

public interface AuditLogDAO {
	void logAction(AuditLog log);

	List<AuditLog> getAllLogs();
}
