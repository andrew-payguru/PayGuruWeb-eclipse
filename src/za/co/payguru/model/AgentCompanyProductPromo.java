package za.co.payguru.model;

import java.sql.Date;
import org.json.JSONObject;

import za.co.payguru.util.DateUtil;

public class AgentCompanyProductPromo {
	public static final String ACTIVE = "1";
	public static final String INACTIVE = "0";

	private int compid = 0;
	private int prodid = 0;
	private String agent = "";
	private String subagent = "";
	private int promodiscamt = 0;
	private String promoref1 = "";
	private String promoref2 = "";
	private Date promodatefrom = DateUtil.DEFAULT_DATE;
	private Date promodateto = DateUtil.DEFAULT_DATE;
	private String promoactive = ACTIVE;

	// Constructors
	public AgentCompanyProductPromo() {}

	public AgentCompanyProductPromo(int compid, int prodid, String agent, String subagent, int promodiscamt, String promoref1, String promoref2, Date promodatefrom, Date promodateto, String promoactive) {
		this.compid = compid;
		this.prodid = prodid;
		this.agent = agent;
		this.subagent = subagent;
		this.promodiscamt = promodiscamt;
		this.promoref1 = promoref1;
		this.promoref2 = promoref2;
		this.promodatefrom = promodatefrom;
		this.promodateto = promodateto;
		this.promoactive = promoactive;
	}

	// Getters and Setters
	public int getCompid() {
		return compid;
	}

	public void setCompid(int compid) {
		this.compid = compid;
	}

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getSubagent() {
		return subagent;
	}

	public void setSubagent(String subagent) {
		this.subagent = subagent;
	}

	public int getPromodiscamt() {
		return promodiscamt;
	}

	public void setPromodiscamt(int promodiscamt) {
		this.promodiscamt = promodiscamt;
	}

	public String getPromoref1() {
		return promoref1;
	}

	public void setPromoref1(String promoref1) {
		this.promoref1 = promoref1;
	}

	public String getPromoref2() {
		return promoref2;
	}

	public void setPromoref2(String promoref2) {
		this.promoref2 = promoref2;
	}

	public Date getPromodatefrom() {
		return promodatefrom;
	}

	public void setPromodatefrom(Date promodatefrom) {
		this.promodatefrom = promodatefrom;
	}

	public Date getPromodateto() {
		return promodateto;
	}

	public void setPromodateto(Date promodateto) {
		this.promodateto = promodateto;
	}

	public String getPromoactive() {
		return promoactive;
	}

	public void setPromoactive(String promoactive) {
		this.promoactive = promoactive;
	}

	@Override
	public String toString() {
		return "AgentCompanyProductPromo{" +
				"compid=" + compid +
				", prodid=" + prodid +
				", agent='" + agent + '\'' +
				", subagent='" + subagent + '\'' +
				", promodiscamt=" + promodiscamt +
				", promoref1='" + promoref1 + '\'' +
				", promoref2='" + promoref2 + '\'' +
				", promodatefrom=" + promodatefrom +
				", promodateto=" + promodateto +
				", promoactive='" + promoactive + '\'' +
				'}';
	}

	public JSONObject toJSON() {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("compid", compid);
			jsonObject.put("prodid", prodid);
			jsonObject.put("agent", agent);
			jsonObject.put("subagent", subagent);
			jsonObject.put("promodiscamt", promodiscamt);
			jsonObject.put("promoref1", promoref1);
			jsonObject.put("promoref2", promoref2);
			jsonObject.put("promodatefrom", promodatefrom);
			jsonObject.put("promodateto", promodateto);
			jsonObject.put("promoactive", promoactive);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return jsonObject;
	}
}
