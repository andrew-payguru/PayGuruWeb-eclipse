package za.co.payguru.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import za.co.payguru.dao.CompanyAdvertDao;
import za.co.payguru.dao.ParamDao;
import za.co.payguru.http.HTTPUrlConnectionClient;
import za.co.payguru.util.DBUtil;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.FileUtil;
import za.co.payguru.util.IniUtil;
import za.co.payguru.util.Util;

public class AdController extends HttpServlet{

	public final static int ERROR_CODE_SERVLET = 9001;
	public static String dbName = ""; 
	public static String dbUser = "";
	public static String dbPass = "";
	public static String dbNameAdvert = "";
	public static String dbUserAdvert = "";
	public static String dbPassAdvert = "";
	public static boolean apiForwardRequest = false;
	public static String apiForwardRequestUrl = "";

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
		apiForwardRequest = Util.parseBoolean(ini.getValue("apiforwardrequest"));
		apiForwardRequestUrl = ini.getValue("apiforwardrequesturl");
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter output = null;
		String pagedetails = new String();
		resp.setContentType("text/html; charset=UTF-8");
		
		
		for(int z=0;z<1;z++) {
			
			if(apiForwardRequest) {
				System.out.println("REDIRECT: " + apiForwardRequest);
				String redirectUrl = apiForwardRequestUrl;
				HTTPUrlConnectionClient.redirectRequest(req, resp, redirectUrl);
				return;
				
			}
			
			try(
					Connection connection = DBUtil.getConnection(dbName,dbUser,dbPass);
					Connection connectionAd = DBUtil.getConnection(dbNameAdvert,dbUserAdvert,dbPassAdvert);
			){
				output = resp.getWriter();
				String web_req = req.getParameter("web_request");
				if(web_req!=null&&web_req.length()>0) {
					if(web_req.equals("glow-redirect")) {
						String name = "";
						String surname = "";
						String cell = "";
						String ticketref = req.getParameter("ref");
						String advertlinkref = req.getParameter("advertlinkref");
						String adlinkdir = ParamDao.getParam("compadvertlinkdir", connection);
						try(
								Statement statement = connectionAd.createStatement();
						){
							System.out.println("SELECT * FROM ad_"+advertlinkref + " WHERE id = '" + ticketref+"'");
							ResultSet rs = statement.executeQuery("SELECT * FROM ad_"+advertlinkref + " WHERE id = '" + ticketref+"'");
							if(rs.next()) {
								name = rs.getString("nome_1");
								surname = rs.getString("sobrenome_2");
								cell = rs.getString("numero_3");
							}
						}catch (Exception e) {System.out.println("ERROR QUERYING AD TABLE: " + e.toString());}
						
						
						if(name.length()<=0||surname.length()<=0||cell.length()<=0||ticketref==null||advertlinkref==null) {
							pagedetails = "<html><body><h1>Invalid!</h1></body></html>";
							break;
						}
						
						String filedetails = FileUtil.readFromFile(adlinkdir+"/"+advertlinkref+"_ticket");
						filedetails = filedetails.replace("<!--name-->", name);
						filedetails = filedetails.replace("<!--surname-->", surname);
						filedetails = filedetails.replace("<!--cell-->", cell);
						filedetails = filedetails.replace("<!--ticketref-->", ticketref);
						pagedetails = filedetails;
					}
				}else {
					String advertlinkref = req.getParameter("advertlinkref");
					String adlinkdir = ParamDao.getParam("compadvertlinkdir", connection);
					String addatadir = ParamDao.getParam("compadvertdatadir", connection);
					pagedetails = FileUtil.readFromFile(adlinkdir+"/"+advertlinkref);
					int advertid = Util.parseInt(advertlinkref.substring(3), 0);
					int compid = Util.parseInt(advertlinkref.substring(0,3), 0);
					String ipAddress = req.getHeader("X-FORWARDED-FOR");
					if(ipAddress==null)
						ipAddress = req.getRemoteAddr();
					String dateStr = DateUtil.getCurrentCCYYMMDDStr() + "," + DateUtil.getCurrentHHMMSSStr(true);
					if(advertlinkref.indexOf('_')!=advertlinkref.length()-1) {
						long hitCount = CompanyAdvertDao.incrementHitCount(connection, advertid, compid);
						System.out.println("count: " + hitCount);
						FileUtil.writeFile(addatadir+"/"+advertlinkref, dateStr+","+ipAddress+"\n", true);
					}
				}

			}catch (Exception e) {
				System.out.println("Servlet error: " + e.toString());
			}
		}
		if(output!=null) {
			output.write(pagedetails);
			output.close();
		}
	}
}
