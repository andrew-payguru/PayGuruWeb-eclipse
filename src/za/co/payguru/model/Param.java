package za.co.payguru.model;

public class Param {
	public static final String PARAM_ID_WEBORIGINCODE = "weborigincode";
	
	private String paramID;
	private String paramValue;
	public Param() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Param(String paramID, String paramValue) {
		super();
		this.paramID = paramID;
		this.paramValue = paramValue;
	}
	public String getParamID() {
		return paramID;
	}
	public void setParamID(String paramID) {
		this.paramID = paramID;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	@Override
	public String toString() {
		return "Param [paramID=" + paramID + ", paramValue=" + paramValue + "]";
	}
	
	
}
