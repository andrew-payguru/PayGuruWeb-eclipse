package za.co.payguru.model;

public class BankType {
	private int bankTypeId;
	private String bankName;
	private String bankDesc;
	private String bankInterfaceId;
	private String bankActive;
	
	public static String BANKTYPE_ACTIVE = "1";
	public static String BANKTYPE_INACTIVE = "0";
	
	public BankType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BankType(int bankTypeId, String bankName, String bankDesc, String bankInterfaceId, String bankActive) {
		super();
		this.bankTypeId = bankTypeId;
		this.bankName = bankName;
		this.bankDesc = bankDesc;
		this.bankInterfaceId = bankInterfaceId;
		this.bankActive = bankActive;
	}

	public int getBankTypeId() {
		return bankTypeId;
	}

	public void setBankTypeId(int bankTypeId) {
		this.bankTypeId = bankTypeId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankDesc() {
		return bankDesc;
	}

	public void setBankDesc(String bankDesc) {
		this.bankDesc = bankDesc;
	}

	public String getBankInterfaceId() {
		return bankInterfaceId;
	}

	public void setBankInterfaceId(String bankInterfaceId) {
		this.bankInterfaceId = bankInterfaceId;
	}

	public String getBankActive() {
		return bankActive;
	}

	public void setBankActive(String bankActive) {
		this.bankActive = bankActive;
	}
	
	
}
