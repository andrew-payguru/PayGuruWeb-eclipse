package za.co.payguru.servlet.process;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.payguru.dao.ClientInvoiceDao;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.HTTPUtil;
import za.co.payguru.util.JSONHelper;
import za.co.payguru.util.Util;

public class Process_api {

	public static StringBuilder getInvDayCount(HttpServletRequest req, HttpServletResponse resp, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = Util.parseInt(req.getParameter("compid"),0);
				if(compid==0) {
					errMsg = "Invalid COMPID";
					break;
				}
				String date = req.getParameter("startdate");
				if(date==null||date.length()<=0)
					date = DateUtil.getCurrentCCYYMMDDStr();
				date = date.replace('-', '/');
				
				int count = ClientInvoiceDao.getInvoiceDayCount(connection, compid, date);
				sb = JSONHelper.createJsonMessage("invtot", ""+count);
			}	
		}catch (Exception e) {
			System.out.println("Server Error Process: getInvCount -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

}
