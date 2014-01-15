package com.innovazions.jbm.view;

public class ActionStatus {

	public static String STATUS_TYPE_SUCCESS = "Success";

	public static String STATUS_TYPE_ERROR = "Error";

	private String statusType;

	private String statusMessage;

	private String statusDesc;

	private Long generatedId;

	private String generatedCode;

	/**
	 * 
	 * @param statusType
	 * @param statusMessage
	 * @param generatedId
	 */
	public ActionStatus(String statusType, String statusMessage,
			Long generatedId) {
		super();
		this.statusType = statusType;
		this.statusMessage = statusMessage;
		this.generatedId = generatedId;
	}

	/**
	 * 
	 * @param statusType
	 * @param statusMessage
	 * @param generatedId
	 * @param generatedCode
	 */
	public ActionStatus(String statusType, String statusMessage,
			Long generatedId, String generatedCode) {
		super();
		this.statusType = statusType;
		this.statusMessage = statusMessage;
		this.generatedId = generatedId;
		this.generatedCode = generatedCode;
	}

	/**
	 * 
	 * @param statusType
	 * @param statusMessage
	 */
	public ActionStatus(String statusType, String statusMessage) {
		super();
		this.statusType = statusType;
		this.statusMessage = statusMessage;
	}

	/**
	 * 
	 * @param statusType
	 * @param statusMessage
	 * @param statusDesc
	 * @param generatedId
	 * @param generatedCode
	 */
	public ActionStatus(String statusType, String statusMessage,
			String statusDesc, Long generatedId, String generatedCode) {
		super();
		this.statusType = statusType;
		this.statusMessage = statusMessage;
		this.statusDesc = statusDesc;
		this.generatedId = generatedId;
		this.generatedCode = generatedCode;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public Long getGeneratedId() {
		return generatedId;
	}

	public void setGeneratedId(Long generatedId) {
		this.generatedId = generatedId;
	}

	public String getGeneratedCode() {
		return generatedCode;
	}

	public void setGeneratedCode(String generatedCode) {
		this.generatedCode = generatedCode;
	}

}