package za.co.payguru.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import za.co.payguru.dao.ParamDao;
import za.co.payguru.servlet.process.Process_advert;
import za.co.payguru.servlet.process.Process_campaigns;
import za.co.payguru.servlet.process.Process_webapp;
import za.co.payguru.util.DBUtil;
import za.co.payguru.util.HTTPUtil;
import za.co.payguru.util.IniUtil;
import za.co.payguru.util.JSONHelper;


@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10,      // 10 MB
		maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class FileUploadController extends HttpServlet{
	public final static int ERROR_CODE_SERVLET = 9001;
	public static String dbName = ""; 
	public static String dbUser = "";
	public static String dbPass = "";
	public static String dbNameAdvert = "";
	public static String dbUserAdvert = "";
	public static String dbPassAdvert = "";
	public static String advertServerIp = "";
	public static String defaultLang = ""; 

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		IniUtil ini = new IniUtil();
		dbName = ini.getValue("db");
		dbUser = ini.getValue("dbuser");
		dbPass = ini.getValue("dbpass");
		dbNameAdvert = ini.getValue("dbadvert");
		dbUserAdvert = ini.getValue("dbuseradvert");
		dbPassAdvert = ini.getValue("dbpassadvert");
		advertServerIp = ini.getValue("advertserverip");
		defaultLang = ini.getValue("defaultlang");
		if(defaultLang.length()<=0)
			defaultLang = "ENG";

	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		String origincode = req.getParameter("weborigincode");
		System.out.println(action + " : " + req.getContentType());

		PrintWriter pw = resp.getWriter();
		StringBuilder sb = new StringBuilder();
		resp.setContentType("application/json");
		for(int z=0;z<1;z++) {
			try(
					Connection connection = DBUtil.getConnection(dbName,dbUser,dbPass);
					Connection advertConnection = DBUtil.getConnection(dbNameAdvert,dbUserAdvert,dbPassAdvert);
					){
				String origincodeparam = ParamDao.getParam("weborigincode", connection);
				if(origincode==null || origincode.equals(origincodeparam)==false) {
					sb = JSONHelper.getErrorJson("Not Permitted!");
					resp.setStatus(HTTPUtil.HTTP_UNAUTHORIZED);
					break;
				}
				String ipAddress = req.getHeader("X-FORWARDED-FOR");
				if(ipAddress==null)
					ipAddress = req.getRemoteAddr();
				System.out.println("IP ADDRESS: " + ipAddress);
				if(action==null || action.length()<=0) {
					sb = JSONHelper.getErrorJson("No Action Specified!");
					resp.setStatus(HTTPUtil.HTTP_BAD_REQUEST);
					break;
				}else {
					

					if(action.equals("linkcustgroups"))
						sb = Process_campaigns.linkCustGroupsBulkImport(req, resp, connection);
					else if(action.equals("bulkimportcustomers"))
						sb = Process_campaigns.addCustSmsBulkImport(req, resp, connection);
					else if(action.equals("createcampaign"))
						sb = Process_campaigns.createCampaign(req,resp,connection);
					else if(action.equals("uploadadvert"))
						sb = Process_advert.uploadAdvert(req,resp,connection,advertConnection);
					else if(action.equals("createbulksmswithfile"))
						sb = Process_webapp.createBulkSmsWithFile(req,resp,connection);
					else if(action.equals("uploadcustomerimport"))
						sb = Process_webapp.uploadcustomerimport(req,resp,connection,defaultLang);
					else {
						sb = JSONHelper.getErrorJson("Unknown action!");
						resp.setStatus(HTTPUtil.HTTP_BAD_REQUEST);
						break;
					}
				}
			}catch (Exception e) {
				sb = JSONHelper.getErrorJson("Server Error, please report to the PayGuru team!");
				resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
				break;
			}
		}
		pw.write(sb.toString());
		pw.close();
	}
}
