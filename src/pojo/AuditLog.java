package pojo;

import java.util.Date;

public class AuditLog {
	private int logID;
	private Integer userID;
	private Integer adminID;
	private String action;
	private Date timestamp;

	public AuditLog() {
	}

	public AuditLog(int logID, Integer userID, Integer adminID, String action, Date timestamp) {
		this.logID = logID;
		this.userID = userID;
		this.adminID = adminID;
		this.action = action;
		this.timestamp = timestamp;
	}

	public int getLogID() {
		return logID;
	}

	public void setLogID(int logID) {
		this.logID = logID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "AuditLog{" + "logID=" + logID + ", userID=" + userID + ", adminID=" + adminID + ", action='" + action
				+ '\'' + ", timestamp=" + timestamp + '}';
	}

}