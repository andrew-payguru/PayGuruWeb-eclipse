package za.co.payguru.model;

import java.sql.Date;
import java.time.LocalDate;

import org.json.JSONObject;

import za.co.payguru.util.JSONHelper;

public class ClientInvoice {
	public static final String INVOICE_DATE_TYPE_PAYDATE = "1";
	public static final String INVOICE_DATE_TYPE_STARTDATE = "2";

	public static final String INVOICE_INACTIVE = "0";
	public static final String INVOICE_ACTIVE = "1";
	public static final String INVOICE_PENDING = "2";
	public static final String INVOICE_ERROR = "3";
	public static final String INVOICE_PAID = "4";
	public static final String INVOICE_SAVINGS_BALANCE = "5";
	public static final String INVOICE_PART_PAID    		  = "6"; 
	public static final String INVOICE_REVERSED 	  = "7";
	public static final String INVOICE_PART_COMPLETE  = "9"; 
	
	
	public static final int INVOICE_FILTER_TYPE_INVOICE_NO = 1;
	public static final int INVOICE_FILTER_TYPE_CELL_NO = 2;
	public static final int INVOICE_FILTER_TYPE_CUST_NAME = 3;
	public static final int INVOICE_FILTER_TYPE_CUST_SURNAME = 4;
	public static final int INVOICE_FILTER_TYPE_VEHICLE_REG = 5;

	private int clientId = 0;
	private int prodId = 0;
	private int compId = 0;
	private String invNo = "";
	private double payAmt = 0;
	private double payVat = 0;
	private String payDate = "";
	private String payRef = "";
	private String payNext = "";
	private String status = "";
	private String statusDate = "";
	private String statusTime = "";
	private int batchId = 0;
	private double payMin = 0;
	private double payRebateAmt = 0;
	private double payRebateMin = 0;
	private String payDesc = "";
	private long payId = 0;
	private Date prodDate;
	private String cancelDate = "";
	private String cancelTime = "";
	private int cancelUserId = 0;
	private String cancelReason = "";
	private String cancelRefNo = "";
	private String saleChannel = "";
	private String salesSubChannel = "";
	private String reverseDate = "";
	private String reverseTime = "";
	private String reverseDesc = "";
	private int reverseUserId = 0;
	private String active = INVOICE_ACTIVE;
	public ClientInvoice() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ClientInvoice(int clientId, int prodId, int compId, String invNo, double payAmt, double payVat, String payDate,
			String payRef, String payNext, String status, String statusDate, String statusTime, int batchId, double payMin,
			double payRebateAmt, double payRebateMin, String payDesc, long payId, Date prodDate, String cancelDate,
			String cancelTime, int cancelUserId, String cancelReason, String cancelRefNo, String saleChannel,
			String salesSubChannel, String reverseDate, String reverseTime, String reverseDesc, int reverseUserId,
			String active) {
		super();
		this.clientId = clientId;
		this.prodId = prodId;
		this.compId = compId;
		this.invNo = invNo;
		this.payAmt = payAmt;
		this.payVat = payVat;
		this.payDate = payDate;
		this.payRef = payRef;
		this.payNext = payNext;
		this.status = status;
		this.statusDate = statusDate;
		this.statusTime = statusTime;
		this.batchId = batchId;
		this.payMin = payMin;
		this.payRebateAmt = payRebateAmt;
		this.payRebateMin = payRebateMin;
		this.payDesc = payDesc;
		this.payId = payId;
		this.prodDate = prodDate;
		this.cancelDate = cancelDate;
		this.cancelTime = cancelTime;
		this.cancelUserId = cancelUserId;
		this.cancelReason = cancelReason;
		this.cancelRefNo = cancelRefNo;
		this.saleChannel = saleChannel;
		this.salesSubChannel = salesSubChannel;
		this.reverseDate = reverseDate;
		this.reverseTime = reverseTime;
		this.reverseDesc = reverseDesc;
		this.reverseUserId = reverseUserId;
		this.active = active;
	}

	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}
	public int getCompId() {
		return compId;
	}
	public void setCompId(int compId) {
		this.compId = compId;
	}
	public String getInvNo() {
		return invNo;
	}
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}
	public double getPayAmt() {
		return payAmt;
	}
	public void setPayAmt(double payAmt) {
		this.payAmt = payAmt;
	}
	public double getPayVat() {
		return payVat;
	}
	public void setPayVat(double payVat) {
		this.payVat = payVat;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayRef() {
		return payRef;
	}
	public void setPayRef(String payRef) {
		this.payRef = payRef;
	}
	public String getPayNext() {
		return payNext;
	}
	public void setPayNext(String payNext) {
		this.payNext = payNext;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public double getPayMin() {
		return payMin;
	}
	public void setPayMin(double payMin) {
		this.payMin = payMin;
	}
	public double getPayRebateAmt() {
		return payRebateAmt;
	}
	public void setPayRebateAmt(double payRebateAmt) {
		this.payRebateAmt = payRebateAmt;
	}
	public double getPayRebateMin() {
		return payRebateMin;
	}
	public void setPayRebateMin(double payRebateMin) {
		this.payRebateMin = payRebateMin;
	}
	public String getPayDesc() {
		return payDesc;
	}
	public void setPayDesc(String payDesc) {
		this.payDesc = payDesc;
	}
	public long getPayId() {
		return payId;
	}
	public void setPayId(long payId) {
		this.payId = payId;
	}
	public Date getProdDate() {
		return prodDate;
	}
	public void setProdDate(Date prodDate) {
		this.prodDate = prodDate;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}
	public int getCancelUserId() {
		return cancelUserId;
	}
	public void setCancelUserId(int cancelUserId) {
		this.cancelUserId = cancelUserId;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getCancelRefNo() {
		return cancelRefNo;
	}
	public void setCancelRefNo(String cancelRefNo) {
		this.cancelRefNo = cancelRefNo;
	}
	public String getSaleChannel() {
		return saleChannel;
	}
	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}
	public String getSalesSubChannel() {
		return salesSubChannel;
	}
	public void setSalesSubChannel(String salesSubChannel) {
		this.salesSubChannel = salesSubChannel;
	}
	
	public String getReverseDate() {
		return reverseDate;
	}

	public void setReverseDate(String reverseDate) {
		this.reverseDate = reverseDate;
	}

	public String getReverseTime() {
		return reverseTime;
	}

	public void setReverseTime(String reverseTime) {
		this.reverseTime = reverseTime;
	}

	public String getReverseDesc() {
		return reverseDesc;
	}

	public void setReverseDesc(String reverseDesc) {
		this.reverseDesc = reverseDesc;
	}

	public int getReverseUserId() {
		return reverseUserId;
	}

	public void setReverseUserId(int reverseUserId) {
		this.reverseUserId = reverseUserId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	
	@Override
	public String toString() {
		return "ClientInvoice [clientId=" + clientId + ", prodId=" + prodId + ", compId=" + compId + ", invNo=" + invNo
				+ ", payAmt=" + payAmt + ", payVat=" + payVat + ", payDate=" + payDate + ", payRef=" + payRef + ", payNext="
				+ payNext + ", status=" + status + ", statusDate=" + statusDate + ", statusTime=" + statusTime + ", batchId="
				+ batchId + ", payMin=" + payMin + ", payRebateAmt=" + payRebateAmt + ", payRebateMin=" + payRebateMin
				+ ", payDesc=" + payDesc + ", payId=" + payId + ", prodDate=" + prodDate + ", cancelDate=" + cancelDate
				+ ", cancelTime=" + cancelTime + ", cancelUserId=" + cancelUserId + ", cancelReason=" + cancelReason
				+ ", cancelRefNo=" + cancelRefNo + ", saleChannel=" + saleChannel + ", salesSubChannel=" + salesSubChannel
				+ ", reverseDate=" + reverseDate + ", reverseTime=" + reverseTime + ", reverseDesc=" + reverseDesc
				+ ", reverseUserId=" + reverseUserId + ", active=" + active + "]";
	}
	
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("invno", invNo);
			json.put("clientid", clientId);
			json.put("compid", compId);
			json.put("prodid", prodId);
			json.put("payamt", payAmt);
			json.put("payvat", payVat);
			json.put("paydate", payDate);
			json.put("payref", payRef);
			json.put("paynext", payNext);
			json.put("status", status);
			json.put("statusdate", statusDate);
			json.put("statustime", statusTime);
			json.put("batchid", batchId);
			json.put("paymin", payMin);
			json.put("payrebateamt", payRebateAmt);
			json.put("payrebatemin", payRebateMin);
			json.put("paydesc", payDesc);
			json.put("payid", payId);
			json.put("proddate", prodDate);
			json.put("canceldate", cancelDate);
			json.put("canceltime", cancelTime);
			json.put("canceluserid", cancelUserId);
			json.put("cancelreason", cancelReason);
			json.put("salechannel", saleChannel);
			json.put("salesubchannel", salesSubChannel);
			json.put("reversedate", reverseDate);
			json.put("reversetime", reverseTime);
			json.put("reversedesc", reverseDesc);
			json.put("reverseuserid", reverseUserId);
			json.put("active", active);
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}

	public String toJsonString() {
		return "{\"clientid\" : " + clientId + ", \"prodid\" : " + prodId + ", \"compid\" : " + compId + ", \"invno\" : \"" + JSONHelper.fixJSONString(invNo)
				+ "\", \"payamt\" : " + payAmt + ", \"payvat\" : " + payVat + ", \"paydate\" : \"" + payDate + "\", \"payref\" : \"" + JSONHelper.fixJSONString(payRef) 
				+ "\", \"paynext\" : \"" + payNext + "\", \"status\" : \"" + status + "\", \"statusdate\" : \"" + statusDate 
				+ "\", \"statustime\" : \"" + statusTime + "\", \"batchid\" : " + batchId + ", \"paymin\" : " + payMin 
				+ ", \"payrebateamt\" : " + payRebateAmt + ", \"payrebatemin\" : " + payRebateMin
				+ ", \"paydesc\" : \"" + JSONHelper.fixJSONString(payDesc) + "\", \"payid\" : " + payId + ", \"proddate\" : \"" + prodDate + "\", \"canceldate\" : \"" + cancelDate
				+ "\", \"canceltime\" : \"" + cancelTime + "\", \"canceluserid\" : " + cancelUserId + ", \"cancelreason\" : \"" + cancelReason
				+ "\", \"cancelrefno\" : \"" + cancelRefNo + "\", \"salechannel\" : \"" + saleChannel + "\", \"salessubchannel\" : \"" + salesSubChannel
				+ "\", \"reversedate\" : \""+reverseDate+"\", \"reversetime\" : \"" + reverseTime + "\", \"reversedesc\" : \"" + reverseDesc + "\", \"reverseuserid\" : " + reverseUserId + ", \"active\" : \""+active+"\"}";
	}
	
	public String toJsonStringCamelCase() {
		return "{\"clientId\" : " + clientId + ", \"prodId\" : " + prodId + ", \"compId\" : " + compId + ", \"invNo\" : \"" + JSONHelper.fixJSONString(invNo)
				+ "\", \"payAmt\" : " + payAmt + ", \"payVat\" : " + payVat + ", \"payDate\" : \"" + payDate + "\", \"payRef\" : \"" + JSONHelper.fixJSONString(payRef) 
				+ "\", \"payNext\" : \"" + payNext + "\", \"status\" : \"" + status + "\", \"statusDate\" : \"" + statusDate 
				+ "\", \"statusTime\" : \"" + statusTime + "\", \"batchId\" : " + batchId + ", \"payMin\" : " + payMin 
				+ ", \"payRebateAmt\" : " + payRebateAmt + ", \"payRebateMin\" : " + payRebateMin
				+ ", \"payDesc\" : \"" + JSONHelper.fixJSONString(payDesc) + "\", \"payId\" : " + payId + ", \"prodDate\" : \"" + prodDate + "\", \"cancelDate\" : \"" + cancelDate
				+ "\", \"cancelTime\" : \"" + cancelTime + "\", \"cancelUserId\" : " + cancelUserId + ", \"cancelReason\" : \"" + cancelReason
				+ "\", \"cancelRefNo\" : \"" + cancelRefNo + "\", \"saleChannel\" : \"" + saleChannel + "\", \"salesSubChannel\" : \"" + salesSubChannel
				+ "\", \"reverseDate\" : \""+reverseDate+"\", \"reverseTime\" : \"" + reverseTime + "\", \"reverseDesc\" : \"" + reverseDesc + "\", \"reverseUserId\" : " + reverseUserId + ", \"active\" : \""+active+"\"}";
	}
}
