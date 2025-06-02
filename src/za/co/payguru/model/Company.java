package za.co.payguru.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Company {
	public static String COMP_ACTIVE = "1";
	public static String COMP_INACTIVE = "2";
	
	public static final int PAYGURU_COMPID = -1;
	
	private int compId = 0;
	private String compName = "";
	private String compTelNo = "";
	private String compCellNo = "";
	private String compVatNo = "";
	private String compRegNo = "";
	private String compEmail = "";
	private String compWebsite = "";
	private String compPhysAddr1 = "";
	private String compPhysAddr2 = "";
	private String compPhysAddr3 = "";
	private String compPhysTown = "";
	private String compPhysProv = "";
	private String compPhysPostCode = "";
	private String compPostAddr1 = "";
	private String compPostAddr2 = "";
	private String compPostAddr3 = "";
	private String compPostTown = "";
	private String compPostProv = "";
	private String compPostCode = "";
	private String compJoinName = "";
	private String compContName = "";
	private String compContSurname = "";
	private String compContEmail = "";
	private String compContCellNo = "";
	private String compContTelNo = "";
	private int compDefProdId = 0;
	private String compSysUserId = "";
	private String compSysPassword = "";
	private String compActive = COMP_ACTIVE;
	private String compRef = "";	
	private String compSmsSendRef = "";
	private String compSmsMessage1 = "";
	private String compInvPrefix = "";
	private double compTaxPerc = 0;
	private String compPayInterfaceId = "";
	private String compBankEntity = "";
	private String compShortenUrl = "";
	private String compShortenDir = "";
	private String compWebRef = "";
	
	public Company() {
		this.compId = 0;
		this.compName = "";
		this.compTelNo = "";
		this.compCellNo = "";
		this.compVatNo = "";
		this.compRegNo = "";
		this.compEmail = "";
		this.compWebsite = "";
		this.compPhysAddr1 = "";
		this.compPhysAddr2 = "";
		this.compPhysAddr3 = "";
		this.compPhysTown = "";
		this.compPhysProv = "";
		this.compPhysPostCode = "";
		this.compPostAddr1 = "";
		this.compPostAddr2 = "";
		this.compPostAddr3 = "";
		this.compPostTown = "";
		this.compPostProv = "";
		this.compPostCode = "";
		this.compJoinName = "";
		this.compContName = "";
		this.compContSurname = "";
		this.compContEmail = "";
		this.compContCellNo = "";
		this.compContTelNo = "";
		this.compDefProdId = 0;
		this.compSysUserId = "";
		this.compSysPassword = "";
		this.compActive = COMP_ACTIVE;
		this.compRef = "";	
		this.compSmsSendRef = "";
		this.compSmsMessage1 = "";
		this.compInvPrefix = "";
		this.compTaxPerc = 0;
		this.compPayInterfaceId = "";
		this.compBankEntity = "";
		this.compShortenUrl = "";
		this.compShortenDir = "";
		this.compWebRef = "";
	}

	public Company(int compId, String compName, String compTelNo, String compCellNo, String compVatNo, String compRegNo,
			String compEmail, String compWebsite, String compPhysAddr1, String compPhysAddr2, String compPhysAddr3,
			String compPhysTown, String compPhysProv, String compPhysPostCode, String compPostAddr1, String compPostAddr2,
			String compPostAddr3, String compPostTown, String compPostProv, String compPostCode, String compJoinName,
			String compContName, String compContSurname, String compContEmail, String compContCellNo, String compContTelNo,
			int compDefProdId, String compSysUserId, String compSysPassword, String compActive, String compRef,
			String compSmsSendRef, String compSmsMessage1, String compInvPrefix, double compTaxPerc,
			String compPayInterfaceId, String compBankEntity, String compShortenUrl, String compShortenDir, String compWebRef) {
		super();
		this.compId = compId;
		this.compName = compName;
		this.compTelNo = compTelNo;
		this.compCellNo = compCellNo;
		this.compVatNo = compVatNo;
		this.compRegNo = compRegNo;
		this.compEmail = compEmail;
		this.compWebsite = compWebsite;
		this.compPhysAddr1 = compPhysAddr1;
		this.compPhysAddr2 = compPhysAddr2;
		this.compPhysAddr3 = compPhysAddr3;
		this.compPhysTown = compPhysTown;
		this.compPhysProv = compPhysProv;
		this.compPhysPostCode = compPhysPostCode;
		this.compPostAddr1 = compPostAddr1;
		this.compPostAddr2 = compPostAddr2;
		this.compPostAddr3 = compPostAddr3;
		this.compPostTown = compPostTown;
		this.compPostProv = compPostProv;
		this.compPostCode = compPostCode;
		this.compJoinName = compJoinName;
		this.compContName = compContName;
		this.compContSurname = compContSurname;
		this.compContEmail = compContEmail;
		this.compContCellNo = compContCellNo;
		this.compContTelNo = compContTelNo;
		this.compDefProdId = compDefProdId;
		this.compSysUserId = compSysUserId;
		this.compSysPassword = compSysPassword;
		this.compActive = compActive;
		this.compRef = compRef;
		this.compSmsSendRef = compSmsSendRef;
		this.compSmsMessage1 = compSmsMessage1;
		this.compInvPrefix = compInvPrefix;
		this.compTaxPerc = compTaxPerc;
		this.compPayInterfaceId = compPayInterfaceId;
		this.compBankEntity = compBankEntity;
		this.compShortenUrl = compShortenUrl;
		this.compShortenDir = compShortenDir;
	}

	public int getCompId() {
		return compId;
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompTelNo() {
		return compTelNo;
	}

	public void setCompTelNo(String compTelNo) {
		this.compTelNo = compTelNo;
	}

	public String getCompCellNo() {
		return compCellNo;
	}

	public void setCompCellNo(String compCellNo) {
		this.compCellNo = compCellNo;
	}

	public String getCompVatNo() {
		return compVatNo;
	}

	public void setCompVatNo(String compVatNo) {
		this.compVatNo = compVatNo;
	}

	public String getCompRegNo() {
		return compRegNo;
	}

	public void setCompRegNo(String compRegNo) {
		this.compRegNo = compRegNo;
	}

	public String getCompEmail() {
		return compEmail;
	}

	public void setCompEmail(String compEmail) {
		this.compEmail = compEmail;
	}

	public String getCompWebsite() {
		return compWebsite;
	}

	public void setCompWebsite(String compWebsite) {
		this.compWebsite = compWebsite;
	}

	public String getCompPhysAddr1() {
		return compPhysAddr1;
	}

	public void setCompPhysAddr1(String compPhysAddr1) {
		this.compPhysAddr1 = compPhysAddr1;
	}

	public String getCompPhysAddr2() {
		return compPhysAddr2;
	}

	public void setCompPhysAddr2(String compPhysAddr2) {
		this.compPhysAddr2 = compPhysAddr2;
	}

	public String getCompPhysAddr3() {
		return compPhysAddr3;
	}

	public void setCompPhysAddr3(String compPhysAddr3) {
		this.compPhysAddr3 = compPhysAddr3;
	}

	public String getCompPhysTown() {
		return compPhysTown;
	}

	public void setCompPhysTown(String compPhysTown) {
		this.compPhysTown = compPhysTown;
	}

	public String getCompPhysProv() {
		return compPhysProv;
	}

	public void setCompPhysProv(String compPhysProv) {
		this.compPhysProv = compPhysProv;
	}

	public String getCompPhysPostCode() {
		return compPhysPostCode;
	}

	public void setCompPhysPostCode(String compPhysPostCode) {
		this.compPhysPostCode = compPhysPostCode;
	}

	public String getCompPostAddr1() {
		return compPostAddr1;
	}

	public void setCompPostAddr1(String compPostAddr1) {
		this.compPostAddr1 = compPostAddr1;
	}

	public String getCompPostAddr2() {
		return compPostAddr2;
	}

	public void setCompPostAddr2(String compPostAddr2) {
		this.compPostAddr2 = compPostAddr2;
	}

	public String getCompPostAddr3() {
		return compPostAddr3;
	}

	public void setCompPostAddr3(String compPostAddr3) {
		this.compPostAddr3 = compPostAddr3;
	}

	public String getCompPostTown() {
		return compPostTown;
	}

	public void setCompPostTown(String compPostTown) {
		this.compPostTown = compPostTown;
	}

	public String getCompPostProv() {
		return compPostProv;
	}

	public void setCompPostProv(String compPostProv) {
		this.compPostProv = compPostProv;
	}

	public String getCompPostCode() {
		return compPostCode;
	}

	public void setCompPostCode(String compPostCode) {
		this.compPostCode = compPostCode;
	}

	public String getCompJoinName() {
		return compJoinName;
	}

	public void setCompJoinName(String compJoinName) {
		this.compJoinName = compJoinName;
	}

	public String getCompContName() {
		return compContName;
	}

	public void setCompContName(String compContName) {
		this.compContName = compContName;
	}

	public String getCompContSurname() {
		return compContSurname;
	}

	public void setCompContSurname(String compContSurname) {
		this.compContSurname = compContSurname;
	}

	public String getCompContEmail() {
		return compContEmail;
	}

	public void setCompContEmail(String compContEmail) {
		this.compContEmail = compContEmail;
	}

	public String getCompContCellNo() {
		return compContCellNo;
	}

	public void setCompContCellNo(String compContCellNo) {
		this.compContCellNo = compContCellNo;
	}

	public String getCompContTelNo() {
		return compContTelNo;
	}

	public void setCompContTelNo(String compContTelNo) {
		this.compContTelNo = compContTelNo;
	}

	public int getCompDefProdId() {
		return compDefProdId;
	}

	public void setCompDefProdId(int compDefProdId) {
		this.compDefProdId = compDefProdId;
	}

	public String getCompSysUserId() {
		return compSysUserId;
	}

	public void setCompSysUserId(String compSysUserId) {
		this.compSysUserId = compSysUserId;
	}

	public String getCompSysPassword() {
		return compSysPassword;
	}

	public void setCompSysPassword(String compSysPassword) {
		this.compSysPassword = compSysPassword;
	}

	public String getCompActive() {
		return compActive;
	}

	public void setCompActive(String compActive) {
		this.compActive = compActive;
	}

	public String getCompRef() {
		return compRef;
	}

	public void setCompRef(String compRef) {
		this.compRef = compRef;
	}

	public String getCompSmsSendRef() {
		return compSmsSendRef;
	}

	public void setCompSmsSendRef(String compSmsSendRef) {
		this.compSmsSendRef = compSmsSendRef;
	}

	public String getCompSmsMessage1() {
		return compSmsMessage1;
	}

	public void setCompSmsMessage1(String compSmsMessage1) {
		this.compSmsMessage1 = compSmsMessage1;
	}

	public String getCompInvPrefix() {
		return compInvPrefix;
	}

	public void setCompInvPrefix(String compInvPrefix) {
		this.compInvPrefix = compInvPrefix;
	}

	public double getCompTaxPerc() {
		return compTaxPerc;
	}

	public void setCompTaxPerc(double compTaxPerc) {
		this.compTaxPerc = compTaxPerc;
	}

	public String getCompPayInterfaceId() {
		return compPayInterfaceId;
	}

	public void setCompPayInterfaceId(String compPayInterfaceId) {
		this.compPayInterfaceId = compPayInterfaceId;
	}

	public String getCompBankEntity() {
		return compBankEntity;
	}

	public void setCompBankEntity(String compBankEntity) {
		this.compBankEntity = compBankEntity;
	}

	public String getCompShortenUrl() {
		return compShortenUrl;
	}

	public void setCompShortenUrl(String compShortenUrl) {
		this.compShortenUrl = compShortenUrl;
	}

	public String getCompShortenDir() {
		return compShortenDir;
	}

	public void setCompShortenDir(String compShortenDir) {
		this.compShortenDir = compShortenDir;
	}

	
	public String getCompWebRef() {
		return compWebRef;
	}

	public void setCompWebRef(String compWebRef) {
		this.compWebRef = compWebRef;
	}

	
	@Override
	public String toString() {
		return "Company [compId=" + compId + ", compName=" + compName + ", compTelNo=" + compTelNo + ", compCellNo="
				+ compCellNo + ", compVatNo=" + compVatNo + ", compRegNo=" + compRegNo + ", compEmail=" + compEmail
				+ ", compWebsite=" + compWebsite + ", compPhysAddr1=" + compPhysAddr1 + ", compPhysAddr2="
				+ compPhysAddr2 + ", compPhysAddr3=" + compPhysAddr3 + ", compPhysTown=" + compPhysTown
				+ ", compPhysProv=" + compPhysProv + ", compPhysPostCode=" + compPhysPostCode + ", compPostAddr1="
				+ compPostAddr1 + ", compPostAddr2=" + compPostAddr2 + ", compPostAddr3=" + compPostAddr3
				+ ", compPostTown=" + compPostTown + ", compPostProv=" + compPostProv + ", compPostCode=" + compPostCode
				+ ", compJoinName=" + compJoinName + ", compContName=" + compContName + ", compContSurname="
				+ compContSurname + ", compContEmail=" + compContEmail + ", compContCellNo=" + compContCellNo
				+ ", compContTelNo=" + compContTelNo + ", compDefProdId=" + compDefProdId + ", compSysUserId="
				+ compSysUserId + ", compSysPassword=" + compSysPassword + ", compActive=" + compActive + ", compRef="
				+ compRef + ", compSmsSendRef=" + compSmsSendRef + ", compSmsMessage1=" + compSmsMessage1
				+ ", compInvPrefix=" + compInvPrefix + ", compTaxPerc=" + compTaxPerc + ", compPayInterfaceId="
				+ compPayInterfaceId + ", compBankEntity=" + compBankEntity + ", compShortenUrl=" + compShortenUrl
				+ ", compShortenDir=" + compShortenDir + ", compWebRef=" + compWebRef + "]";
	}

	public String toJsonString() {
		return "{ \"compid\" : " + compId + ", \"compname\" : \"" + compName + "\", \"comptelno\" : \"" + compTelNo  
				+ "\", \"compcellno\" : \""	+ compCellNo + "\", \"compvatno\" : \"" + compVatNo + "\", \"compregno\" : \"" + compRegNo 
				+ "\", \"compemail\" : \"" + compEmail + "\", \"compwebsite\" : \"" + compWebsite + "\", \"compphysaddr1\"  : \"" + compPhysAddr1
				+ "\", \"compphysaddr2\" : \"" + compPhysAddr2 + "\", \"compphysaddr3\" : \"" + compPhysAddr3 + "\", \"compphystown\" : \"" + compPhysTown
				+ "\", \"compphysprov\" : \"" + compPhysProv + "\", \"compphyspostcode\" : \"" + compPhysPostCode 
				+ "\", \"comppostaddr1\" : \"" + compPostAddr1 + "\", \"comppostaddr2\" : \"" + compPostAddr2 + "\", \"comppostaddr3\" : \"" + compPostAddr3
				+ "\", \"compposttown\" : \"" + compPostTown + "\", \"comppostprov\" : \"" + compPostProv + "\", \"comppostcode\" : \"" + compPostCode
				+ "\", \"compjoinname\" : \"" + compJoinName + "\", \"compcontname\" : \"" + compContName + "\", \"compcontsurname\" : \"" + compContSurname 
				+ "\", \"compcontemail\" : \"" + compContEmail + "\", \"compcontcellno\" : \"" + compContCellNo + "\", \"compconttelno\" : \"" + compContTelNo
				+ "\", \"compdefprodid\" : " + compDefProdId + ", \"compsysuserid\" : \"" + compSysUserId + "\", \"compsyspassword\" : \"" + compSysPassword
				+ "\", \"compactive\" : \"" + compActive + "\", \"compref\" : \"" + compRef + "\", \"compsmssendref\" : \"" + compSmsSendRef
				+ "\", \"compsmsmessage1\" : \"" + compSmsMessage1 + "\", \"compinvprefix\" : \"" + compInvPrefix + "\", \"comptaxperc\" : " + compTaxPerc
				+ ", \"comppayinterfaceid\" : \"" + compPayInterfaceId + "\", \"compbankentity\" : \"" + compBankEntity
				+ "\", \"compshortenurl\" : \"" + compShortenUrl + "\", \"compshortendir\" : \"" + compShortenDir + "\", \"compwebref\" : \"" + compWebRef + "\" }";
	}
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("compid", compId);
			json.put("compname", compName);
			json.put("comptelno", compTelNo);
			json.put("compcellno", compCellNo);
			json.put("compvatno", compVatNo);
			json.put("compregno", compRegNo);
			json.put("compemail", compEmail);
			json.put("compwebsite", compWebsite);
			json.put("compphysaddr1", compPhysAddr1);
			json.put("compphysaddr2", compPhysAddr2);
			json.put("compphysaddr3", compPhysAddr3);
			json.put("compphystown", compPhysTown);
			json.put("compphysprov", compPhysProv);
			json.put("compphyspostcode", compPhysPostCode);
			json.put("comppostaddr1", compPostAddr1);
			json.put("comppostaddr2", compPostAddr2);
			json.put("comppostaddr3", compPostAddr3);
			json.put("compposttown", compPostTown);
			json.put("comppostprov", compPostProv);
			json.put("comppostcode", compPostCode);
			json.put("compjoinname", compJoinName);
			json.put("compcontname", compContName);
			json.put("compcontsurname", compContSurname);
			json.put("compcontemail", compContEmail);
			json.put("compcontcellno", compContCellNo);
			json.put("compconttelno", compContTelNo);
			json.put("compdefprodid", compDefProdId);
			json.put("compsysuserid", compSysUserId);
			json.put("compsyspassword", compSysPassword);
			json.put("compactive", compActive);
			json.put("compref", compRef);
			json.put("compsmssendref", compSmsSendRef);
			json.put("compsmsmessage1", compSmsMessage1);
			json.put("compinvprefix", compInvPrefix);
			json.put("comptaxperc", compTaxPerc);
			json.put("comppayinterfaceid", compPayInterfaceId);
			json.put("compbankentity", compBankEntity);
			json.put("compshortenurl", compShortenUrl);
			json.put("compshortendir", compShortenDir);		
			json.put("compwebref", compWebRef);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
	
			
}
