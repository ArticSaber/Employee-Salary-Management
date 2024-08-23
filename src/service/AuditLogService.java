package service;

import java.util.List;

import pojo.AuditLog;

public interface AuditLogService {
	void logAction(AuditLog log);

	List<AuditLog> getAllLogs();
}
