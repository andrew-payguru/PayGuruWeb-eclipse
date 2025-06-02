package za.co.payguru.util;

import java.util.HashMap;

public class IniUtil {
	private String iniDir = "/pgweb.ini";
	private HashMap<String,String> iniMap = new HashMap<String, String>();
	
	public IniUtil() {
		String errMsg = "";
		iniMap = FileUtil.readKeyValueFile(iniDir);
	}
	
	public String getValue(String key) {
		String value = "";
		value = iniMap.get(key);
		return value;
	}
}
