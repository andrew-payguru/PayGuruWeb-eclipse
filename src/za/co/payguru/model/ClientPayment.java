package za.co.payguru.model;

import za.co.payguru.util.DateUtil;

public class ClientPayment {
	public static final int TRANSFER_STATUS_INACTIVE = -1;
	public static final int TRANSFER_STATUS_ACTIVE = 1;
	public static final int TRANSFER_STATUS_BUSY = 2;
	public static final int TRANSFER_STATUS_ERROR = 3;
	public static final int TRANSFER_STATUS_DONE= 4;
	public static final int TRANSFER_STATUS_SAMEDAY = 7;
	
	public static final int STATUS_ACTIVE    = 1;
	public static final int STATUS_PENDING   = 2;
	public static final int STATUS_REPROCESS = 3;
	public static final int STATUS_ERROR     = 4;
	
	public static final String DEFAULT_REVERSE_DATE = "1970/01/01";
	
	private long payId;
	private int clientId;
	private int compId;
	private int prodId;
	private int bankId;
	private String createDate;
	private String createTime;
	private int status;
	private String statusDate;
	private String statusTime;
	private String payDate;
	private double payAmt;
	private String payRef;
	private double payTran;
	private int payTransferStatus;
	private String payTransferStatusDate;
	private String payTransferStatusTime;
	private String payTransferStatusUserId;
	private String payTransferRef;
	private double payTransferClientId;
	private double payTransferPayId;
	private String payReverseDate;
	private String payReverseTime;
	private String payReverseDesc;
	private int payReverseUserId;
	public ClientPayment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientPayment(long payId, int clientId, int compId, int prodId, int bankId, String createDate,
			String createTime, int status, String statusDate, String statusTime, String payDate, double payAmt, String payRef,
			double payTran, int payTransferStatus, String payTransferStatusDate, String payTransferStatusTime,
			String payTransferStatusUserId, String payTransferRef, double payTransferClientId, double payTransferPayId,
			String payReverseDate, String payReverseTime, String payReverseDesc, int payReverseUserId) {
		super();
		this.payId = payId;
		this.clientId = clientId;
		this.compId = compId;
		this.prodId = prodId;
		this.bankId = bankId;
		this.createDate = createDate;
		this.createTime = createTime;
		this.status = status;
		this.statusDate = statusDate;
		this.statusTime = statusTime;
		this.payDate = payDate;
		this.payAmt = payAmt;
		this.payRef = payRef;
		this.payTran = payTran;
		this.payTransferStatus = payTransferStatus;
		this.payTransferStatusDate = payTransferStatusDate;
		this.payTransferStatusTime = payTransferStatusTime;
		this.payTransferStatusUserId = payTransferStatusUserId;
		this.payTransferRef = payTransferRef;
		this.payTransferClientId = payTransferClientId;
		this.payTransferPayId = payTransferPayId;
		this.payReverseDate = payReverseDate;
		this.payReverseTime = payReverseTime;
		this.payReverseDesc = payReverseDesc;
		this.payReverseUserId = payReverseUserId;
	}
	public long getPayId() {
		return payId;
	}
	public void setPayId(long payId) {
		this.payId = payId;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}
	public String getStatusTime() {
		return statusTime;
	}
	public void setStatusTime(String statusTime) {
		this.statusTime = statusTime;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public double getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(double payAmt) {
		this.payAmt = payAmt;
	}
	public String getPayRef() {
		return payRef;
	}
	public void setPayRef(String payRef) {
		this.payRef = payRef;
	}
	public double getPayTran() {
		return payTran;
	}
	public void setPayTran(double payTran) {
		this.payTran = payTran;
	}
	public int getPayTransferStatus() {
		return payTransferStatus;
	}
	public void setPayTransferStatus(int payTransferStatus) {
		this.payTransferStatus = payTransferStatus;
	}
	public String getPayTransferStatusDate() {
		return payTransferStatusDate;
	}
	public void setPayTransferStatusDate(String payTransferStatusDate) {
		this.payTransferStatusDate = payTransferStatusDate;
	}
	public String getPayTransferStatusTime() {
		return payTransferStatusTime;
	}
	public void setPayTransferStatusTime(String payTransferStatusTime) {
		this.payTransferStatusTime = payTransferStatusTime;
	}
	public String getPayTransferStatusUserId() {
		return payTransferStatusUserId;
	}
	public void setPayTransferStatusUserId(String payTransferStatusUserId) {
		this.payTransferStatusUserId = payTransferStatusUserId;
	}
	public String getPayTransferRef() {
		return payTransferRef;
	}
	public void setPayTransferRef(String payTransferRef) {
		this.payTransferRef = payTransferRef;
	}
	public double getPayTransferClientId() {
		return payTransferClientId;
	}
	public void setPayTransferClientId(double payTransferClientId) {
		this.payTransferClientId = payTransferClientId;
	}
	public double getPayTransferPayId() {
		return payTransferPayId;
	}
	public void setPayTransferPayId(double payTransferPayId) {
		this.payTransferPayId = payTransferPayId;
	}
	public String getPayReverseDate() {
		return payReverseDate;
	}
	public void setPayReverseDate(String payReverseDate) {
		this.payReverseDate = payReverseDate;
	}
	public String getPayReverseTime() {
		return payReverseTime;
	}
	public void setPayReverseTime(String payReverseTime) {
		this.payReverseTime = payReverseTime;
	}
	public String getPayReverseDesc() {
		return payReverseDesc;
	}
	public void setPayReverseDesc(String payReverseDesc) {
		this.payReverseDesc = payReverseDesc;
	}
	public int getPayReverseUserId() {
		return payReverseUserId;
	}
	public void setPayReverseUserId(int payReverseUserId) {
		this.payReverseUserId = payReverseUserId;
	}
	@Override
	public String toString() {
		return "ClientPayment [payId=" + payId + ", clientId=" + clientId + ", compId=" + compId + ", prodId=" + prodId
				+ ", bankId=" + bankId + ", createDate=" + createDate + ", createTime=" + createTime + ", status=" + status
				+ ", statusDate=" + statusDate + ", statusTime=" + statusTime + ", payDate=" + payDate + ", payAmt=" + payAmt
				+ ", payRef=" + payRef + ", payTran=" + payTran + ", payTransferStatus=" + payTransferStatus
				+ ", payTransferStatusDate=" + payTransferStatusDate + ", payTransferStatusTime=" + payTransferStatusTime
				+ ", payTransferStatusUserId=" + payTransferStatusUserId + ", payTransferRef=" + payTransferRef
				+ ", payTransferClientId=" + payTransferClientId + ", payTransferPayId=" + payTransferPayId
				+ ", payReverseDate=" + payReverseDate + ", payReverseTime=" + payReverseTime + ", payReverseDesc="
				+ payReverseDesc + ", payReverseUserId=" + payReverseUserId + "]";
	}
	
}
