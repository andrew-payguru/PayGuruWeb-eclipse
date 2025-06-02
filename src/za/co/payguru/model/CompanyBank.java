package za.co.payguru.model;

public class CompanyBank {
	
	public static final String IN_ACTIVE = "0";
	public static final String ACTIVE = "1";
	
	private int compId;
	private int bankType;    
	private String bankAccNo;
	private int bankId; 
	private String bankDesc;  
	private String createDate;   
	private String createTime;  
	private String sftpActive;  
	private String sftpIpAddr;  
	private String sftpUser;     
	private String sftpPass;    
	private String sftpDir;    
	private String bankExclDescs; 
	private String bankInclDescs;
	private String bankActive;
	private boolean bankEmailActive = false;
	private boolean bankFileActive = false;
	private String bankEmailDetails = new String();
	private String bankFileDetails = new String();
	
	public CompanyBank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyBank(int compId, int bankType, String bankAccNo, int bankId, String bankDesc, String createDate,
			String createTime, String sftpActive, String sftpIpAddr, String sftpUser, String sftpPass, String sftpDir,
			String bankExclDescs, String bankInclDescs, String bankActive, boolean bankEmailActive, boolean bankFileActive,
			String bankEmailDetails, String bankFileDetails) {
		super();
		this.compId = compId;
		this.bankType = bankType;
		this.bankAccNo = bankAccNo;
		this.bankId = bankId;
		this.bankDesc = bankDesc;
		this.createDate = createDate;
		this.createTime = createTime;
		this.sftpActive = sftpActive;
		this.sftpIpAddr = sftpIpAddr;
		this.sftpUser = sftpUser;
		this.sftpPass = sftpPass;
		this.sftpDir = sftpDir;
		this.bankExclDescs = bankExclDescs;
		this.bankInclDescs = bankInclDescs;
		this.bankActive = bankActive;
		this.bankEmailActive = bankEmailActive;
		this.bankFileActive = bankFileActive;
		this.bankEmailDetails = bankEmailDetails;
		this.bankFileDetails = bankFileDetails;
	}

	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public int getBankType() {
		return bankType;
	}

	public void setBankType(int bankType) {
		this.bankType = bankType;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankDesc() {
		return bankDesc;
	}

	public void setBankDesc(String bankDesc) {
		this.bankDesc = bankDesc;
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

	public String getSftpActive() {
		return sftpActive;
	}

	public void setSftpActive(String sftpActive) {
		this.sftpActive = sftpActive;
	}

	public String getSftpIpAddr() {
		return sftpIpAddr;
	}

	public void setSftpIpAddr(String sftpIpAddr) {
		this.sftpIpAddr = sftpIpAddr;
	}

	public String getSftpUser() {
		return sftpUser;
	}

	public void setSftpUser(String sftpUser) {
		this.sftpUser = sftpUser;
	}

	public String getSftpPass() {
		return sftpPass;
	}

	public void setSftpPass(String sftpPass) {
		this.sftpPass = sftpPass;
	}

	public String getSftpDir() {
		return sftpDir;
	}

	public void setSftpDir(String sftpDir) {
		this.sftpDir = sftpDir;
	}

	public String getBankExclDescs() {
		return bankExclDescs;
	}

	public void setBankExclDescs(String bankExclDescs) {
		this.bankExclDescs = bankExclDescs;
	}

	public String getBankInclDescs() {
		return bankInclDescs;
	}

	public void setBankInclDescs(String bankInclDescs) {
		this.bankInclDescs = bankInclDescs;
	}

	public String getBankActive() {
		return bankActive;
	}

	public void setBankActive(String bankActive) {
		this.bankActive = bankActive;
	}

	public boolean isBankEmailActive() {
		return bankEmailActive;
	}

	public void setBankEmailActive(boolean bankEmailActive) {
		this.bankEmailActive = bankEmailActive;
	}

	public boolean isBankFileActive() {
		return bankFileActive;
	}

	public void setBankFileActive(boolean bankFileActive) {
		this.bankFileActive = bankFileActive;
	}

	public String getBankEmailDetails() {
		return bankEmailDetails;
	}

	public void setBankEmailDetails(String bankEmailDetails) {
		this.bankEmailDetails = bankEmailDetails;
	}

	public String getBankFileDetails() {
		return bankFileDetails;
	}

	public void setBankFileDetails(String bankFileDetails) {
		this.bankFileDetails = bankFileDetails;
	}

	@Override
	public String toString() {
		return "CompanyBank [compId=" + compId + ", bankType=" + bankType + ", bankAccNo=" + bankAccNo + ", bankId="
				+ bankId + ", bankDesc=" + bankDesc + ", createDate=" + createDate + ", createTime=" + createTime
				+ ", sftpActive=" + sftpActive + ", sftpIpAddr=" + sftpIpAddr + ", sftpUser=" + sftpUser + ", sftpPass="
				+ sftpPass + ", sftpDir=" + sftpDir + ", bankExclDescs=" + bankExclDescs + ", bankInclDescs=" + bankInclDescs
				+ ", bankActive=" + bankActive + ", bankEmailActive=" + bankEmailActive + ", bankFileActive=" + bankFileActive
				+ ", bankEmailDetails=" + bankEmailDetails + ", bankFileDetails=" + bankFileDetails + "]";
	}
	
	



}
