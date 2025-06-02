package za.co.payguru.model;

import org.json.JSONObject;

public class Client {
	public static final String ACTIVE = "1";
	public static final int REG_TYPE_FULL = 1;
	public static final int REG_TYPE_APP_ONLY = 2;
	
	private int clientid = 0;
	private String clientname = "";
	private String clientsurname = "";
	private String clientidno = "";
	private String clienttelno = "";
	private String clientcellno = "";
	private String clientvatno = "";
	private String clientregno = "";
	private String clientemail = "";
	private String clientwebsite = "";
	private String clientphysaddr1 = "";
	private String clientphysaddr2 = "";
	private String clientphysaddr3 = "";
	private String clientphystown = "";
	private String clientphysprov = "";
	private String clientphyspostcode = "";
	private String clientpostaddr1 = "";
	private String clientpostaddr2 = "";
	private String clientpostaddr3 = "";
	private String clientposttown = "";
	private String clientpostprov = "";
	private String clientpostcode = "";
	private String contname = "";
	private String contsurname = "";
	private String contemail = "";
	private String contcellno = "";
	private String conttelno = "";
	private String clientpayref = "";
	private String clientdeviceno = "";
	private String clientinitdate = "";
	private String clientref = "";
	private String clientpassword = "";
	private long clientpasswordmillis = 0;
	private String clientuniqueid = "";
	private long clientuniquemillis = 0;
	public Client() {
		clientid = 0;
		this.clientname = "";
		this.clientsurname = "";
		this.clientidno = "";
		this.clienttelno = "";
		this.clientcellno = "";
		this.clientvatno = "";
		this.clientregno = "";
		this.clientemail = "";
		this.clientwebsite = "";
		this.clientphysaddr1 = "";
		this.clientphysaddr2 = "";
		this.clientphysaddr3 = "";
		this.clientphystown = "";
		this.clientphysprov = "";
		this.clientphyspostcode = "";
		this.clientpostaddr1 = "";
		this.clientpostaddr2 = "";
		this.clientpostaddr3 = "";
		this.clientposttown = "";
		this.clientpostprov = "";
		this.clientpostcode = "";
		this.contname = "";
		this.contsurname = "";
		this.contemail = "";
		this.contcellno = "";
		this.conttelno = "";
		this.clientpayref = "";
		this.clientdeviceno = "";
		this.clientinitdate = "";
		this.clientref = "";
		this.clientpassword = "";
		this.clientpasswordmillis = 0;
		this.clientuniqueid = "";
		this.clientuniquemillis = 0;
	}
	public Client(int clientid, String clientname, String clientsurname, String clientidno, String clienttelno,
			String clientcellno, String clientvatno, String clientregno, String clientemail, String clientwebsite,
			String clientphysaddr1, String clientphysaddr2, String clientphysaddr3, String clientphystown,
			String clientphysprov, String clientphyspostcode, String clientpostaddr1, String clientpostaddr2,
			String clientpostaddr3, String clientposttown, String clientpostprov, String clientpostcode, String contname,
			String contsurname, String contemail, String contcellno, String conttelno, String clientpayref,
			String clientdeviceno, String clientinitdate, String clientref, String clientpassword, long clientpasswordmillis,
			String clientuniqueid, long clientuniquemillis) {
		super();
		this.clientid = clientid;
		this.clientname = clientname;
		this.clientsurname = clientsurname;
		this.clientidno = clientidno;
		this.clienttelno = clienttelno;
		this.clientcellno = clientcellno;
		this.clientvatno = clientvatno;
		this.clientregno = clientregno;
		this.clientemail = clientemail;
		this.clientwebsite = clientwebsite;
		this.clientphysaddr1 = clientphysaddr1;
		this.clientphysaddr2 = clientphysaddr2;
		this.clientphysaddr3 = clientphysaddr3;
		this.clientphystown = clientphystown;
		this.clientphysprov = clientphysprov;
		this.clientphyspostcode = clientphyspostcode;
		this.clientpostaddr1 = clientpostaddr1;
		this.clientpostaddr2 = clientpostaddr2;
		this.clientpostaddr3 = clientpostaddr3;
		this.clientposttown = clientposttown;
		this.clientpostprov = clientpostprov;
		this.clientpostcode = clientpostcode;
		this.contname = contname;
		this.contsurname = contsurname;
		this.contemail = contemail;
		this.contcellno = contcellno;
		this.conttelno = conttelno;
		this.clientpayref = clientpayref;
		this.clientdeviceno = clientdeviceno;
		this.clientinitdate = clientinitdate;
		this.clientref = clientref;
		this.clientpassword = clientpassword;
		this.clientpasswordmillis = clientpasswordmillis;
		this.clientuniqueid = clientuniqueid;
		this.clientuniquemillis = clientuniquemillis;
	}
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getClientsurname() {
		return clientsurname;
	}
	public void setClientsurname(String clientsurname) {
		this.clientsurname = clientsurname;
	}
	public String getClientidno() {
		return clientidno;
	}
	public void setClientidno(String clientidno) {
		this.clientidno = clientidno;
	}
	public String getClienttelno() {
		return clienttelno;
	}
	public void setClienttelno(String clienttelno) {
		this.clienttelno = clienttelno;
	}
	public String getClientcellno() {
		return clientcellno;
	}
	public void setClientcellno(String clientcellno) {
		this.clientcellno = clientcellno;
	}
	public String getClientvatno() {
		return clientvatno;
	}
	public void setClientvatno(String clientvatno) {
		this.clientvatno = clientvatno;
	}
	public String getClientregno() {
		return clientregno;
	}
	public void setClientregno(String clientregno) {
		this.clientregno = clientregno;
	}
	public String getClientemail() {
		return clientemail;
	}
	public void setClientemail(String clientemail) {
		this.clientemail = clientemail;
	}
	public String getClientwebsite() {
		return clientwebsite;
	}
	public void setClientwebsite(String clientwebsite) {
		this.clientwebsite = clientwebsite;
	}
	public String getClientphysaddr1() {
		return clientphysaddr1;
	}
	public void setClientphysaddr1(String clientphysaddr1) {
		this.clientphysaddr1 = clientphysaddr1;
	}
	public String getClientphysaddr2() {
		return clientphysaddr2;
	}
	public void setClientphysaddr2(String clientphysaddr2) {
		this.clientphysaddr2 = clientphysaddr2;
	}
	public String getClientphysaddr3() {
		return clientphysaddr3;
	}
	public void setClientphysaddr3(String clientphysaddr3) {
		this.clientphysaddr3 = clientphysaddr3;
	}
	public String getClientphystown() {
		return clientphystown;
	}
	public void setClientphystown(String clientphystown) {
		this.clientphystown = clientphystown;
	}
	public String getClientphysprov() {
		return clientphysprov;
	}
	public void setClientphysprov(String clientphysprov) {
		this.clientphysprov = clientphysprov;
	}
	public String getClientphyspostcode() {
		return clientphyspostcode;
	}
	public void setClientphyspostcode(String clientphyspostcode) {
		this.clientphyspostcode = clientphyspostcode;
	}
	public String getClientpostaddr1() {
		return clientpostaddr1;
	}
	public void setClientpostaddr1(String clientpostaddr1) {
		this.clientpostaddr1 = clientpostaddr1;
	}
	public String getClientpostaddr2() {
		return clientpostaddr2;
	}
	public void setClientpostaddr2(String clientpostaddr2) {
		this.clientpostaddr2 = clientpostaddr2;
	}
	public String getClientpostaddr3() {
		return clientpostaddr3;
	}
	public void setClientpostaddr3(String clientpostaddr3) {
		this.clientpostaddr3 = clientpostaddr3;
	}
	public String getClientposttown() {
		return clientposttown;
	}
	public void setClientposttown(String clientposttown) {
		this.clientposttown = clientposttown;
	}
	public String getClientpostprov() {
		return clientpostprov;
	}
	public void setClientpostprov(String clientpostprov) {
		this.clientpostprov = clientpostprov;
	}
	public String getClientpostcode() {
		return clientpostcode;
	}
	public void setClientpostcode(String clientpostcode) {
		this.clientpostcode = clientpostcode;
	}
	public String getContname() {
		return contname;
	}
	public void setContname(String contname) {
		this.contname = contname;
	}
	public String getContsurname() {
		return contsurname;
	}
	public void setContsurname(String contsurname) {
		this.contsurname = contsurname;
	}
	public String getContemail() {
		return contemail;
	}
	public void setContemail(String contemail) {
		this.contemail = contemail;
	}
	public String getContcellno() {
		return contcellno;
	}
	public void setContcellno(String contcellno) {
		this.contcellno = contcellno;
	}
	public String getConttelno() {
		return conttelno;
	}
	public void setConttelno(String conttelno) {
		this.conttelno = conttelno;
	}
	public String getClientpayref() {
		return clientpayref;
	}
	public void setClientpayref(String clientpayref) {
		this.clientpayref = clientpayref;
	}
	public String getClientdeviceno() {
		return clientdeviceno;
	}
	public void setClientdeviceno(String clientdeviceno) {
		this.clientdeviceno = clientdeviceno;
	}
	public String getClientinitdate() {
		return clientinitdate;
	}
	public void setClientinitdate(String clientinitdate) {
		this.clientinitdate = clientinitdate;
	}
	public String getClientref() {
		return clientref;
	}
	public void setClientref(String clientref) {
		this.clientref = clientref;
	}
	public String getClientpassword() {
		return clientpassword;
	}
	public void setClientpassword(String clientpassword) {
		this.clientpassword = clientpassword;
	}
	public long getClientpasswordmillis() {
		return clientpasswordmillis;
	}
	public void setClientpasswordmillis(long clientpasswordmillis) {
		this.clientpasswordmillis = clientpasswordmillis;
	}
	public String getClientuniqueid() {
		return clientuniqueid;
	}
	public void setClientuniqueid(String clientuniqueid) {
		this.clientuniqueid = clientuniqueid;
	}
	public long getClientuniquemillis() {
		return clientuniquemillis;
	}
	public void setClientuniquemillis(long clientuniquemillis) {
		this.clientuniquemillis = clientuniquemillis;
	}
	
	@Override
	public String toString() {
		return "Client [clientid=" + clientid + ", clientname=" + clientname + ", clientsurname=" + clientsurname
				+ ", clientidno=" + clientidno + ", clienttelno=" + clienttelno + ", clientcellno=" + clientcellno
				+ ", clientvatno=" + clientvatno + ", clientregno=" + clientregno + ", clientemail=" + clientemail
				+ ", clientwebsite=" + clientwebsite + ", clientphysaddr1=" + clientphysaddr1 + ", clientphysaddr2="
				+ clientphysaddr2 + ", clientphysaddr3=" + clientphysaddr3 + ", clientphystown=" + clientphystown
				+ ", clientphysprov=" + clientphysprov + ", clientphyspostcode=" + clientphyspostcode + ", clientpostaddr1="
				+ clientpostaddr1 + ", clientpostaddr2=" + clientpostaddr2 + ", clientpostaddr3=" + clientpostaddr3
				+ ", clientposttown=" + clientposttown + ", clientpostprov=" + clientpostprov + ", clientpostcode="
				+ clientpostcode + ", contname=" + contname + ", contsurname=" + contsurname + ", contemail=" + contemail
				+ ", contcellno=" + contcellno + ", conttelno=" + conttelno + ", clientpayref=" + clientpayref
				+ ", clientdeviceno=" + clientdeviceno + ", clientinitdate=" + clientinitdate + ", clientref=" + clientref
				+ ", clientpassword=" + clientpassword + ", clientpasswordmillis=" + clientpasswordmillis + ", clientuniqueid="
				+ clientuniqueid + ", clientuniquemillis=" + clientuniquemillis + "]";
	}
	public String toJsonString() {
		return "{\"clientid\":" + clientid + ", \"clientname\": \"" + clientname + "\", \"clientsurname\": \"" + clientsurname
				+ "\", \"clientidno\": \"" + clientidno + "\", \"clienttelno\": \"" + clienttelno + "\", \"clientcellno\": \"" + clientcellno
				+ "\", \"clientvatno\": \"" + clientvatno + "\", \"clientregno\": \"" + clientregno + "\", \"clientemail\": \"" + clientemail
				+ "\", \"clientwebsite\": \"" + clientwebsite + "\", \"clientphysaddr1\": \"" + clientphysaddr1 + "\", \"clientphysaddr2\": \""
				+ clientphysaddr2 + "\", \"clientphysaddr3\": \"" + clientphysaddr3 + "\", \"clientphystown\": \"" + clientphystown
				+ "\", \"clientphysprov\": \"" + clientphysprov + "\", \"clientphyspostcode\": \"" + clientphyspostcode + "\", \"clientpostaddr1\": \""
				+ clientpostaddr1 + "\", \"clientpostaddr2\": \"" + clientpostaddr2 + "\", \"clientpostaddr3\": \"" + clientpostaddr3
				+ "\", \"clientposttown\": \"" + clientposttown + "\", \"clientpostprov\": \"" + clientpostprov + "\", \"clientpostcode\": \""
				+ clientpostcode + "\", \"contname\": \"" + contname + "\", \"contsurname\": \"" + contsurname + "\", \"contemail\": \"" + contemail
				+ "\", \"contcellno\": \"" + contcellno + "\", \"conttelno\": \"" + conttelno + "\", \"clientpayref\": \"" + clientpayref
				+ "\", \"clientdeviceno\": \"" + clientdeviceno + "\", \"clientinitdate\": \"" + clientinitdate + "\", \"clientref\": \"" + clientref
				+ "\", \"clientpassword\": \"" + clientpassword + "\", \"clientpasswordmillis\":" + clientpasswordmillis + ", \"clientuniqueid\":\""
				+ clientuniqueid + "\", \"clientuniquemillis\":" + clientuniquemillis + "}";
	}
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put("clientid", clientid);
			json.put("clientname", clientname);
			json.put("clientsurname", clientsurname);
			json.put("clientidno", clientidno);
			json.put("clienttelno", clienttelno);
			json.put("clientcellno", clientcellno);
			json.put("clientvatno", clientvatno);
			json.put("clientregno", clientregno);
			json.put("clientemail", clientemail);
			json.put("clientwebsite", clientwebsite);
			json.put("clientphysaddr1", clientphysaddr1);
			json.put("clientphysaddr2", clientphysaddr2);
			json.put("clientphysaddr3", clientphysaddr3);
			json.put("clientphystown", clientphystown);
			json.put("clientphysprov", clientphysprov);
			json.put("clientphyspostcode", clientphyspostcode);
			json.put("clientpostaddr1", clientpostaddr1);
			json.put("clientpostaddr2", clientpostaddr2);
			json.put("clientpostaddr3", clientpostaddr3);
			json.put("clientposttown", clientposttown);
			json.put("clientpostprov", clientpostprov);
			json.put("clientpostcode", clientpostcode);
			json.put("contname", contname);
			json.put("contsurname", contsurname);
			json.put("contemail", contemail);
			json.put("contcellno", contcellno);
			json.put("conttelno", conttelno);
			json.put("clientpayref", clientpayref);
			json.put("clientdeviceno", clientdeviceno);
			json.put("clientinitdate", clientinitdate);
			json.put("clientref", clientref);
			json.put("clientpassword", clientpassword);
			json.put("clientpasswordmillis", clientpasswordmillis);
			json.put("clientuniqueid", clientuniqueid);
			json.put("clientuniquemillis", clientuniquemillis);

		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
	
		
}
