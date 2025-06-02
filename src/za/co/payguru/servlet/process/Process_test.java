package za.co.payguru.servlet.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import za.co.payguru.util.JSONHelper;

public class Process_test {

	public static StringBuilder testParams(HttpServletRequest req, HttpServletResponse resp) {
		StringBuilder sb = new StringBuilder();
		String refno = req.getParameter("refno");
		if(refno==null||refno.length()<=0) { 
			sb = JSONHelper.getErrorJson("No refno supplied!");
			resp.setStatus(400);
		}else
			sb = JSONHelper.getSuccessJson("Test Complete");
		return sb;
	}

	public static StringBuilder testJson(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody) {
		StringBuilder sb = new StringBuilder();
		String refno = JSONHelper.getValue(jsonBody, "refno");
		if(refno==null||refno.length()<=0) { 
			sb = JSONHelper.getErrorJson("No refno supplied!");
			resp.setStatus(400);
		}else
			sb = JSONHelper.getSuccessJson("Test Complete");
		return sb;
	}

	public static StringBuilder test(HttpServletRequest req, HttpServletResponse resp) {
		return JSONHelper.getSuccessJson("Test Complete");
	}


	
}
