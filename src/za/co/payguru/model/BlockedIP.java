package za.co.payguru.model;

import java.sql.Date;
import java.sql.Time;

public class BlockedIP {
	public static final int BLOCK_ACTIVE = 1;
	public static final int BLOCK_INACTIVE = 0;

	private String ipAddr = new String("");
	private Date blockDate = Date.valueOf("2023-02-06");
	private Time blockTime = Time.valueOf("08:00:00");
	private String blockDesc = new String("");
	private int blockActive = 1;
	public BlockedIP() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BlockedIP(String ipAddr, Date blockDate, Time blockTime, String blockDesc, int blockActive) {
		super();
		this.ipAddr = ipAddr;
		this.blockDate = blockDate;
		this.blockTime = blockTime;
		this.blockDesc = blockDesc;
		this.blockActive = blockActive;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public Date getBlockDate() {
		return blockDate;
	}
	public void setBlockDate(Date blockDate) {
		this.blockDate = blockDate;
	}
	public Time getBlockTime() {
		return blockTime;
	}
	public void setBlockTime(Time blockTime) {
		this.blockTime = blockTime;
	}
	public String getBlockDesc() {
		return blockDesc;
	}
	public void setBlockDesc(String blockDesc) {
		this.blockDesc = blockDesc;
	}
	public int getBlockActive() {
		return blockActive;
	}
	public void setBlockActive(int blockActive) {
		this.blockActive = blockActive;
	}
	@Override
	public String toString() {
		return "BlockedIP [ipAddr=" + ipAddr + ", blockDate=" + blockDate + ", blockTime=" + blockTime + ", blockDesc="
				+ blockDesc + ", blockActive=" + blockActive + "]";
	}
	
	
}
