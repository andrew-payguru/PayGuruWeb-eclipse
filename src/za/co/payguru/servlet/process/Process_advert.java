package za.co.payguru.servlet.process;

import java.rmi.Naming;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import pay.guru.qrcode.QRRMIMethods;
import za.co.payguru.dao.CompanyAdvertDao;
import za.co.payguru.dao.CompanyDao;
import za.co.payguru.dao.ParamDao;
import za.co.payguru.model.Company;
import za.co.payguru.model.CompanyAdvert;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.FileUtil;
import za.co.payguru.util.HTTPUtil;
import za.co.payguru.util.JSONHelper;
import za.co.payguru.util.Util;

public class Process_advert {

	public static StringBuilder uploadAdvert(HttpServletRequest req, HttpServletResponse resp, Connection connection, Connection connectionAdvert) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = Util.parseInt(req.getParameter("compid"),0);
				if(compid==0) {
					errMsg = "Invalid compid!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Invalid Company!";
					break;
				}
				String addesc = req.getParameter("advertdesc");
				if(addesc==null||addesc.length()<=0) {
					errMsg = "Invalid Advert Description!";
					break;
				}
				int adtype = Util.parseInt(req.getParameter("adverttype"),0);
				if(adtype==0) {
					errMsg = "Invalid Advert Type!";
					break;
				}
				int adlinktype = Util.parseInt(req.getParameter("adlinktype"),0);
				if(adtype==CompanyAdvert.ADVERT_TYPE_DATACAPTURE)
					adlinktype = CompanyAdvert.ADLINK_TYPE_INTERNAL;

				Date startDate = DateUtil.getDateValue(req.getParameter("startdate"));
				if(startDate.equals(DateUtil.DEFAULT_DATE)) {
					errMsg = "Invalid Start Date!";
					break;
				}
				Date endDate = DateUtil.getDateValue(req.getParameter("enddate"));
				if(endDate.equals(DateUtil.DEFAULT_DATE)) {
					errMsg = "Invalid End Date!";
					break;
				}
				if(DateUtil.dateBefore(String.valueOf(startDate), String.valueOf(endDate))) {
					errMsg = "End Date Cannot Precede Start Date";
					break;
				}
				
				int compadid = CompanyAdvertDao.getNextAdId(connection);
				String adlinkref = Util.prefixChar(""+compid,'0',3) + Util.prefixChar(""+compadid, '0', 8);
				String adlinkdir = ParamDao.getParam("compadvertlinkdir", connection);
				String adlinkurl = ParamDao.getParam("compadvertlinkurl", connection);
				String adqrdir = ParamDao.getParam("compadvertqrdir", connection);
				String adlinkredirecturl = ParamDao.getParam("redirectcompadvertlinkurl", connection);
				String serverip = ParamDao.getParam("advertserverip", connection);
				
				CompanyAdvert companyAdvert = new CompanyAdvert();
				companyAdvert.setCompid(compid);
				companyAdvert.setCompadid(compadid);
				companyAdvert.setCompaddesc(addesc);
				companyAdvert.setCompadtype(adtype);
				companyAdvert.setCompadlinktype(adlinktype);
				companyAdvert.setCompadlink(adlinkurl+adlinkref);
				
				String docdata = "";
				String dataCaptureHead = "";
				String pgsticker = "";
				String submitBtn = "";
				String formscript = "";
				if(adlinktype==CompanyAdvert.ADLINK_TYPE_INTERNAL) {
					if(adtype==CompanyAdvert.ADVERT_TYPE_REDIRECT) {
						String doctype = req.getParameter("doctype");
						if(doctype==null||doctype.length()<=0) {
							errMsg = "Invalid Document Type!";
							break;
						}
						Part filePart = req.getPart("file");
						if(filePart==null) {
							errMsg = "File Not Present!";
							break;
						}
						String docpath = adlinkdir+"/"+adlinkref+doctype;
						
						filePart.write(docpath);
						if(doctype.equals(CompanyAdvert.DOC_TYPE_PDF)) 
							docdata = "<iframe src=\""+adlinkredirecturl+adlinkref+doctype+"\" id=\"pdf\" ></iframe>";
						if(doctype.equals(CompanyAdvert.DOC_TYPE_JPG)||doctype.equals(CompanyAdvert.DOC_TYPE_JPEG)||doctype.equals(CompanyAdvert.DOC_TYPE_PNG))
							docdata = "<img src=\""+adlinkredirecturl+adlinkref+doctype+"\" id=\"img\"/>";
						if(doctype.equals(CompanyAdvert.DOC_TYPE_HTML))
							docdata = "<iframe src=\""+adlinkredirecturl+adlinkref+doctype+"\" id=\"html\" ></iframe>";
					}else if(adtype==CompanyAdvert.ADVERT_TYPE_DATACAPTURE) {
						String fieldData = req.getParameter("fields");
						if(fieldData==null||fieldData.length()<=0) {
							errMsg = "Ivalid Field Data!";
							break;
						}
						dataCaptureHead = req.getParameter("datacapturehead");
						if(dataCaptureHead==null||dataCaptureHead.length()<=0) {
							errMsg = "Ivalid Data Capture Header!";
							break;
						}
						
						companyAdvert.setCompaddata(fieldData);
						companyAdvert.setCompaddataref(adlinkref);
						
						//create database table
						String tableSql = "CREATE TABLE ad_"+adlinkref+" (";
						String pkSql = "";
						String [] dataAr = fieldData.split(";");
						for(int i=0;i<dataAr.length;i++) {
							String data = dataAr[i];
							String fieldColName = Util.fixDbFieldName(Util.getValueAt(data, "|", 1));
							String fieldType = Util.getValueAt(data, "|", 2);
							boolean fieldPk = (Util.getValueAt(data, "|", 3).equals("1") ? true : false);
							tableSql+=((i==0?"" : ",") + fieldColName +" "+fieldType+" NOT NULL");
							if(fieldPk)
								pkSql+=((pkSql.length()>0 ? "," : "") + fieldColName);
								
						}
						tableSql+=(", createdate DATE NOT NULL, createtime TIME NOT NULL, sourceip VARCHAR NOT NULL");
						tableSql+=(pkSql.length()>0 ? ",PRIMARY KEY ("+pkSql+")" : "") + ")";
						System.out.println(tableSql);
						try(
								Statement statement = connectionAdvert.createStatement();
						){
							statement.executeUpdate(tableSql);
						}catch (Exception e) {
							errMsg = "Error creating database: " + e.toString();
							System.out.println(errMsg);
							break;
						}
						//**
						
						
						//create html for datacapture
						dataCaptureHead = "<h1>"+dataCaptureHead+"</h1>";
						pgsticker = "<img src=\""+adlinkredirecturl+"poweredby.png\" class=\"pgsticker\">";
						submitBtn = "<div class=\"margin-top\"><button id=\"datacapturesubmit\" class=\"submit-btn\">SUBMIT</button></div>";
						StringBuilder htmlsb = new StringBuilder();
						StringBuilder jssb = new StringBuilder();
						htmlsb.append("<div id=\"form-content-container\" class=\"form-content-container\">\n");
						jssb.append("compid : "+compid+",\n");
						jssb.append("compadid : "+compadid+",\n");
						for(int i=0;i<dataAr.length;i++) {
							String data = dataAr[i];
							String fieldDisplay = Util.fixInputName(Util.getValueAt(data, "|", 0));
							String fieldColName = Util.fixDbFieldName(Util.getValueAt(dataAr[i],"|",1));
							String fieldtype = CompanyAdvert.getInputTypeFromDBType(Util.getValueAt(dataAr[i],"|",2));
							htmlsb.append("<div class=\"form-content\">\n");
							htmlsb.append("<label>"+fieldDisplay+"</label>\n");
							htmlsb.append("<input id=\""+fieldColName+"\" class=\"custom-input\" type=\""+fieldtype+"\"/>\n");
							htmlsb.append("</div>\n");
							jssb.append( fieldColName+" : "+"`${document.getElementById('"+fieldColName+"').value}`"+(i==dataAr.length-1?"":",")+"\n");
						}
						htmlsb.append("</div>\n");
						docdata = htmlsb.toString();
						formscript = jssb.toString();
					}
				}else if(adlinktype==CompanyAdvert.ADLINK_TYPE_EXTERNAL) {
					String externallink = req.getParameter("externallink");
					if(externallink==null||externallink.length()<=0) {
						errMsg = "Invalid External Link!";
						System.out.println(errMsg);
						break;
					}
					docdata = "<iframe src=\""+externallink+"\" id=\"html\" ></iframe>";

				}

				String adlinktemplatedetails = FileUtil.readFromFile(adlinkdir+"/adverttemplate.html");
				adlinktemplatedetails = adlinktemplatedetails.replace("<!--adcontent-->", docdata);
				adlinktemplatedetails = adlinktemplatedetails.replace("<!--datacapturehead-->", dataCaptureHead);
				adlinktemplatedetails = adlinktemplatedetails.replace("<!--submitbtn-->", submitBtn);
				adlinktemplatedetails = adlinktemplatedetails.replace("<!--pgsticker-->", pgsticker);
				adlinktemplatedetails = adlinktemplatedetails.replace("<!--serverip-->", serverip);
				adlinktemplatedetails = adlinktemplatedetails.replace("<!--formscripts-->", formscript);

				System.out.println("writing adlink: " + FileUtil.writeFile(adlinkdir+"/"+adlinkref, adlinktemplatedetails, false));
				
				QRRMIMethods qrRmiService = (QRRMIMethods)Naming.lookup("rmi://localhost:2002/qrrmiservice");
				System.out.println(adlinkurl+adlinkref);
				System.out.println("CREATED PDF ["+adqrdir+"/"+adlinkref+".png] : "+qrRmiService.createQRCode(adlinkurl+adlinkref, adqrdir+"/"+adlinkref+".png"));
				
				
				companyAdvert.setCompadstartdate(startDate);
				companyAdvert.setCompadenddate(endDate);

				companyAdvert = CompanyAdvertDao.uploadAdvert(connection, companyAdvert);
				if(companyAdvert.getCompadid()<=0) {
					errMsg = "Unable to upload advert!";
					break;
				}
				sb= JSONHelper.getSuccessJson("Upload Success");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: uploadAdvert -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			System.out.println(errMsg);
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder capturedata(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, Connection advertConnection, String ipAddress) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid compid!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Invalid Company!";
					break;
				}
				int compadid = JSONHelper.getIntValue(jsonBody, "compadid");
				if(compid==0) {
					errMsg = "Invalid compid!";
					break;
				}
				boolean createuuid = JSONHelper.getBooleanValue(jsonBody, "uniqueidrequired");
				String uuid = "";
				if(createuuid)
					uuid = Util.generateUUID();
				
				String qrDir = ParamDao.getParam("compadvertqrdir", connection);
				String adlinkdir = ParamDao.getParam("compadvertlinkdir", connection);
				String adlinkurl = ParamDao.getParam("compadvertlinkurl", connection);
				
				Iterator<String> keys = jsonBody.keys();
				StringBuilder sqlFieldSb = new StringBuilder();
				StringBuilder sqlDataSb = new StringBuilder();
				sqlFieldSb.append("(");
				sqlDataSb.append("(");
				if(createuuid) {
					sqlFieldSb.append("id");
					sqlDataSb.append("'"+uuid+"'");
				}
				boolean first = true;
				while(keys.hasNext()) {
					String key = keys.next();
					if(key.equals("compid") || key.equals("compadid") || key.equals("uniqueidrequired")) 
						continue;
					String value = JSONHelper.getValue(jsonBody, key);
					if(value==null)
						value="";
					sqlFieldSb.append( ( (first && createuuid==false) ? "" : ",") + Util.fixDbFieldName(key));
					sqlDataSb.append( ( (first && createuuid==false) ? "" : ",") +  "'"+value.trim()+"'");
					if(first)
						first = false;
				}
				sqlFieldSb.append(",createdate,createtime,sourceip");
				sqlDataSb.append(",'"+DateUtil.getCurrentCCYYMMDDStr()+"','"+DateUtil.getCurrentHHMMSSStr()+"','"+ipAddress+"'");
				sqlFieldSb.append(")");
				sqlDataSb.append(")");
				String tableName = Util.prefixChar(""+compid, '0', 3)+Util.prefixChar(""+compadid, '0', 8);
				String sqlUpdate = "INSERT INTO ad_" + tableName + " " + sqlFieldSb.toString() + " VALUES " + sqlDataSb.toString();
				System.out.println(sqlUpdate);
				try(
						Statement statement = advertConnection.createStatement();
				){
					if(statement.executeUpdate(sqlUpdate)<=0) {
						errMsg = "Error capturing data!";
						System.out.println(errMsg);
						break;
					}
				}catch (Exception e) {
					e.printStackTrace();
					errMsg = "Error updating table: " + tableName;
					System.out.println(errMsg);
					break;
				}
				
				
				JSONObject json = new JSONObject();
				json.put("SUCCESS", "SUCCESS");
				if(createuuid)
					json.put("UID", uuid);
				sb = new StringBuilder(json.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: capturedata -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			System.out.println(errMsg);
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder captureGlow2Data(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, Connection advertConnection, String ipAddress) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid compid!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Invalid Company!";
					break;
				}
				int compadid = JSONHelper.getIntValue(jsonBody, "compadid");
				if(compid==0) {
					errMsg = "Invalid compid!";
					break;
				}
				boolean createuuid = JSONHelper.getBooleanValue(jsonBody, "uniqueidrequired");
				String uuid = "";
				if(createuuid)
					uuid = Util.generateUUID();
				
				String adlinkref = Util.prefixChar(""+compid, '0', 3)+Util.prefixChar(""+compadid, '0', 8);
				
				String qrDir = ParamDao.getParam("compadvertqrdir", connection);
			
				Iterator<String> keys = jsonBody.keys();
				StringBuilder sqlFieldSb = new StringBuilder();
				StringBuilder sqlDataSb = new StringBuilder();
				StringBuilder urlParamsSb = new StringBuilder();
				urlParamsSb.append("https://www.payguru.co.mz:8443/pgadvert/api?web_request=glow-redirect&advertlinkref="+adlinkref+"&ref="+uuid);
				sqlFieldSb.append("(");
				sqlDataSb.append("(");
				if(createuuid) {
					sqlFieldSb.append("id");
					sqlDataSb.append("'"+uuid+"'");
				}
				boolean first = true;
				while(keys.hasNext()) {
					String key = keys.next();
					if(key.equals("compid") || key.equals("compadid") || key.equals("uniqueidrequired")) 
						continue;
					String value = JSONHelper.getValue(jsonBody, key);
					if(value==null)
						value="";
					sqlFieldSb.append( ( (first && createuuid==false) ? "" : ",") + Util.fixDbFieldName(key));
					sqlDataSb.append( ( (first && createuuid==false) ? "" : ",") +  "'"+value.trim()+"'");
					if(first)
						first = false;
				}
				sqlFieldSb.append(",createdate,createtime,sourceip");
				sqlDataSb.append(",'"+DateUtil.getCurrentCCYYMMDDStr()+"','"+DateUtil.getCurrentHHMMSSStr()+"','"+ipAddress+"'");
				sqlFieldSb.append(")");
				sqlDataSb.append(")");
				String sqlUpdate = "INSERT INTO ad_" + adlinkref + " " + sqlFieldSb.toString() + " VALUES " + sqlDataSb.toString();
				System.out.println(sqlUpdate);
				try(
						Statement statement = advertConnection.createStatement();
				){
					if(statement.executeUpdate(sqlUpdate)<=0) {
						errMsg = "Error capturing data!";
						System.out.println(errMsg);
						break;
					}
				}catch (Exception e) {
					e.printStackTrace();
					errMsg = "Error updating table: " + adlinkref;
					System.out.println(errMsg);
					break;
				}
				
				if(createuuid) {
					QRRMIMethods qrRmiService = (QRRMIMethods)Naming.lookup("rmi://localhost:2002/qrrmiservice");
					
					String qrPath = qrDir+"/"+uuid+".png";				
					System.out.println("CREATED PDF ["+qrPath+".png] : "+qrRmiService.createQRCode(urlParamsSb.toString(), qrPath));
				}
				
				JSONObject json = new JSONObject();
				json.put("SUCCESS", "SUCCESS");
				if(createuuid)
					json.put("UID", uuid);
				sb = new StringBuilder(json.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: capturedata -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			System.out.println(errMsg);
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}


}
