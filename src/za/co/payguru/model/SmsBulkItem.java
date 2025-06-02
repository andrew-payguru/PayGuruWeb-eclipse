package za.co.payguru.model;

public class SmsBulkItem {
	private int summaryID;
	private int itemNo;
	private String itemMSISDN;
	private String itemStatus;
	private String itemSentDate;
	private String itemReceivedDate;
	public SmsBulkItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SmsBulkItem(int summaryID, int itemNo, String itemMSISDN, String itemStatus, String itemSentDate,
			String itemReceivedDate) {
		super();
		this.summaryID = summaryID;
		this.itemNo = itemNo;
		this.itemMSISDN = itemMSISDN;
		this.itemStatus = itemStatus;
		this.itemSentDate = itemSentDate;
		this.itemReceivedDate = itemReceivedDate;
	}
	public int getSummaryID() {
		return summaryID;
	}
	public void setSummaryID(int summaryID) {
		this.summaryID = summaryID;
	}
	public int getItemNo() {
		return itemNo;
	}
	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemMSISDN() {
		return itemMSISDN;
	}
	public void setItemMSISDN(String itemMSISDN) {
		this.itemMSISDN = itemMSISDN;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public String getItemSentDate() {
		return itemSentDate;
	}
	public void setItemSentDate(String itemSentDate) {
		this.itemSentDate = itemSentDate;
	}
	public String getItemReceivedDate() {
		return itemReceivedDate;
	}
	public void setItemReceivedDate(String itemReceivedDate) {
		this.itemReceivedDate = itemReceivedDate;
	}
	@Override
	public String toString() {
		return "SmsBulkItems [summaryID=" + summaryID + ", itemNo=" + itemNo + ", itemMSISDN=" + itemMSISDN
				+ ", itemStatus=" + itemStatus + ", itemSentDate=" + itemSentDate + ", itemReceivedDate=" + itemReceivedDate
				+ "]";
	}
	
	
	
}
