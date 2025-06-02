package za.co.payguru.servlet.process;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;

import za.co.payguru.dao.CompanyDao;
import za.co.payguru.dao.CompanySmsCampaignDao;
import za.co.payguru.dao.CompanySmsCampaignItemDao;
import za.co.payguru.dao.CompanySmsCustomerDao;
import za.co.payguru.dao.CompanySmsGroupCustomerDao;
import za.co.payguru.dao.CompanySmsGroupDao;
import za.co.payguru.dao.CompanyUserDao;
import za.co.payguru.model.Company;
import za.co.payguru.model.CompanySmsCampaign;
import za.co.payguru.model.CompanySmsCampaignItem;
import za.co.payguru.model.CompanySmsGroup;
import za.co.payguru.model.CompanyUser;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.HTTPUtil;
import za.co.payguru.util.JSONHelper;
import za.co.payguru.util.Util;

public class Process_campaigns {

	public static StringBuilder linkCustGroupsBulkImport(HttpServletRequest req, HttpServletResponse resp,Connection connection) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				Part filePart = req.getPart("file");
				if(filePart==null) {
					errMsg = "File Not Present!";
					break;
				}
				int compId= Util.parseInt(req.getParameter("compid"),0);
				if(compId==0) {
					errMsg = "Invalid compid";
					break;
				}
				Company company = CompanyDao.getCompany(compId, connection);
				if(company==null||company.getCompId()<=0) {
					errMsg = "Invalid compid";
					break;
				}
				int userId= Util.parseInt(req.getParameter("userid"),0);
				if(userId==0) {
					errMsg = "Invalid userid";
					break;
				}
				CompanyUser companyUser = CompanyUserDao.loadCompanyUser(company.getCompId(), userId, connection);
				if(companyUser==null||companyUser.getUserId()<=0) {
					errMsg = "Invalid userid";
					break;
				}
				String groupidStr = req.getParameter("groupids");
				if(groupidStr==null||groupidStr.length()<=0) {
					errMsg = "Invalid Groups!";
					break;
				}
				String link = req.getParameter("link");
				if(link==null||link.length()<=0) {
					link = "link";
				}
				boolean unlink = (link.equals("unlink") ? true : false);
				InputStream is = filePart.getInputStream();
				try(
						BufferedReader br = new BufferedReader(new InputStreamReader(is,Charset.forName(StandardCharsets.UTF_8.name())));
						){
					int c = 0;
					while((c= br.read()) != -1) {
						sb2.append((char)c);
					}
					if(is!=null)
						is.close();
					ArrayList<String> fileContent = Util.fileSBToArrayList(sb2);
					if(CompanySmsGroupCustomerDao.bulkLinkCompGroupCustomers(company.getCompId(),companyUser.getUserId(),fileContent,groupidStr,unlink,connection)==false) {
						errMsg = "Unable to link/unlink group customers!";
						break;
					}

				}catch (Exception e) {
					System.out.println("Server Error Process: linkCustGroupsBulkImport -> " + e.toString());
					errMsg = "Server Error";
					break;
				}
				sb = JSONHelper.getSuccessJson("Linking Customer Groups Successful!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: linkCustGroupsBulkImport -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_BAD_REQUEST);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;		
	}

	public static StringBuilder addCustSmsBulkImport(HttpServletRequest req, HttpServletResponse resp,Connection connection) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String compidStr = req.getParameter("compid");
				int compid = Util.parseInt(compidStr, 0);
				if(compidStr==null||compidStr.length()<=0||compid==0) {
					errMsg = "Invalid compid: " + compidStr;
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String useridStr = req.getParameter("userid");
				int userid = Util.parseInt(useridStr, 0);
				if(useridStr==null||useridStr.length()<=0||userid==0) {
					errMsg = "Invalid userid: " + userid;
					break;
				}
				Part filePart = req.getPart("file");
				if(filePart==null) {
					errMsg = "File Not Present!";
					break;
				}
				InputStream is = filePart.getInputStream();
				try(
						BufferedReader br = new BufferedReader(new InputStreamReader(is,Charset.forName(StandardCharsets.UTF_8.name())));
						){
					int c = 0;
					while((c= br.read()) != -1) {
						sb2.append((char)c);
					}
					if(is!=null)
						is.close();
				}catch (Exception e) {
					System.out.println("Server Error Process: CustomerSmsCustomerss -> " + e.toString());
					errMsg = "Server Error";
					break;
				}
				ArrayList<String> fileContent = Util.fileSBToArrayList(sb2);
				if(CompanySmsCustomerDao.uploadCompanySmsCustomersBulk(compid, userid, fileContent, connection)==false) {
					errMsg = "Unable to upload customers!";
					break;
				}

				sb = JSONHelper.getSuccessJson("upload successful!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: CustomerSmsCustomers -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;		
	}

	public static StringBuilder createCustomerGroup(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
					errMsg = "Company does not exist!";
					break;
				}
				String groupname = JSONHelper.getValue(jsonBody, "groupname");
				if(groupname.length()<=0) {
					errMsg = "Invalid group name!";
					break;
				}
				CompanySmsGroup companySmsGroup = new CompanySmsGroup();
				companySmsGroup.setCompId(compid);
				companySmsGroup.setSmsGroupName(groupname.toUpperCase());
				if(CompanySmsGroupDao.createSmsGroup(companySmsGroup, connection)==false) {
					errMsg = "Error creating group!";
					break;
				}
				sb = CompanySmsGroupDao.getCompanySmsGroupJSON(companySmsGroup);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: createCustomerGroup -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder getGroups(HttpServletRequest req, HttpServletResponse resp, boolean inactive, JSONObject jsonBody,Connection connection) {
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
					errMsg = "Company does not exist!";
					break;
				}
				ArrayList<CompanySmsGroup> companySmsGroups = CompanySmsGroupDao.getCompanySmsGroups(compid, inactive, connection);
				sb = CompanySmsGroupDao.getCompanySmsGroupsJSON(companySmsGroups);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: createCustomerGroup -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder updateGroup(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
					errMsg = "Company does not exist!";
					break;
				}
				long groupid = JSONHelper.getLongValue(jsonBody, "groupid");
				if(groupid==0) {
					errMsg = "Invalid groupid!";
					break;
				}
				CompanySmsGroup smsGroup = CompanySmsGroupDao.loadCompanySmsGroup(compid, groupid, connection);
				if(smsGroup==null||smsGroup.getSmsGroupId()==0) {
					errMsg = "Group does not exist!";
					break;
				}
				String groupname = JSONHelper.getValue(jsonBody, "groupname");
				if(groupname.length()<=0) {
					errMsg = "Invalid group name!";
					break;
				} 
				boolean deactivate = JSONHelper.getBooleanValue(jsonBody, "deactivate");

				smsGroup.setSmsGroupName(groupname.toUpperCase());
				smsGroup.setSmsGroupActive((deactivate ? CompanySmsGroup.GROUP_INACTIVE : CompanySmsGroup.GROUP_ACTIVE));
				if(CompanySmsGroupDao.updateSmsGroup(smsGroup, connection)==false) {
					errMsg = "Unable to update group, please try again!";
					break;
				}
				sb = CompanySmsGroupDao.getCompanySmsGroupJSON(smsGroup);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: UpdateGroup -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder createCampaign(HttpServletRequest req, HttpServletResponse resp, Connection connection) {
		StringBuilder sb = new StringBuilder();
		StringBuilder fileBuilder = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String compidStr = req.getParameter("compid");
				int compid = Util.parseInt(compidStr, 0);
				if(compidStr==null||compidStr.length()<=0||compid==0) {
					errMsg = "Invalid compid: " + compidStr;
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String useridStr = req.getParameter("userid");
				int userid = Util.parseInt(useridStr, 0);
				if(useridStr==null||useridStr.length()<=0||userid==0) {
					errMsg = "Invalid userid: " + userid;
					break;
				}
				String campaignname = req.getParameter("campaignname");
				if(campaignname==null||campaignname.length()<=0) {
					errMsg = "Invalid Campaign Name";
					break;
				}
				String campaignmessages = req.getParameter("messages");
				if(campaignmessages==null||campaignmessages.length()<=0) {
					errMsg = "Invalid Campaign Message/s";
					break;
				}
				campaignmessages = campaignmessages.replace("\r", "");
				campaignmessages = campaignmessages.replace("\n", "");
				int smsRecurrence = Util.parseInt(req.getParameter("recurrence"),0);
				if(smsRecurrence==0) {
					errMsg = "Invalid Recurrence Type";
					break;
				}
				if(smsRecurrence!=CompanySmsCampaign.ONCE_OFF&&smsRecurrence!=CompanySmsCampaign.DAILY&&smsRecurrence!=CompanySmsCampaign.WEEKLY&&smsRecurrence!=CompanySmsCampaign.MONTHLY) {
					errMsg = "Invalid Recurrence Type";
					break;
				}
				int smsFreq = Util.parseInt(req.getParameter("frequency"),0);
				if(smsFreq==0) {
					errMsg = "Invalid Frequency";
					break;
				}
				String [] messageAr = campaignmessages.split(",");
				if(smsRecurrence==CompanySmsCampaign.DAILY&&messageAr.length!=smsFreq) {
					errMsg = "Frequency Greater Then Message Count";
					break;
				}
				String fromdate = req.getParameter("fromdate");
				if(fromdate==null||fromdate.length()<=0) {
					errMsg = "Invalid From Date";
					break;
				}
				String toDate = req.getParameter("todate");
				if(toDate ==null||toDate .length()<=0) {
					errMsg = "Invalid To Date";
					break;
				}
				String timesStr = req.getParameter("times");
				if(timesStr==null||timesStr.length()<=0) {
					errMsg = "Invalid Times";
					break;
				}
				String [] times = timesStr.split(",");
				if(smsRecurrence==CompanySmsCampaign.DAILY&&times.length!=smsFreq) {
					errMsg = "Invalid Times";
					break;
				}
				String groups = req.getParameter("groups");
				if(groups==null||groups.length()<=0) {
					errMsg = "Invalid Groups";
					break;
				}
				String monthDaysStr = req.getParameter("monthdays");
				if(smsRecurrence==CompanySmsCampaign.MONTHLY && (monthDaysStr==null||monthDaysStr.length()<=0)) {
					errMsg = "Month Days Not Present!";
					break;
				}
				String [] monthDays = new String [] {""};
				if(smsRecurrence==CompanySmsCampaign.MONTHLY) {
					monthDays = monthDaysStr.split(",");
					if(monthDays.length!=smsFreq) {
						errMsg = "Days of Month Less Than Frequency!";
						break;
					}
				}

				String weekDaysStr = req.getParameter("weekdays");
				if(smsRecurrence==CompanySmsCampaign.WEEKLY && (weekDaysStr==null||weekDaysStr.length()<=0)) {
					errMsg = "Week Days Not Present!";
					break;
				}
				String [] weekDays = new String [] {""};
				if(smsRecurrence==CompanySmsCampaign.WEEKLY) {
					weekDays = weekDaysStr.split(",");
					if(weekDays.length!=smsFreq) {
						errMsg = "Week Days Less Than Frequency!";
						break;
					}
				}
				//				boolean fileSelected = Util.parseBoolean(req.getParameter("fileselected"));
				//				ArrayList<String> cellNos = new ArrayList<String>();
				//				if(fileSelected) {
				//					Part filePart = req.getPart("file");
				//					if(filePart==null) {
				//						errMsg = "File Not Present!";
				//						break;
				//					}
				//					InputStream is = filePart.getInputStream();
				//					try(
				//							BufferedReader br = new BufferedReader(new InputStreamReader(is,Charset.forName(StandardCharsets.UTF_8.name())));
				//					){
				//						int c = 0;
				//						while((c= br.read()) != -1) {
				//							fileBuilder.append((char)c);
				//						}
				//						if(is!=null)
				//							is.close();
				//					}catch (Exception e) {
				//						System.out.println("Server Error Process: CustomerSmsCustomerss -> " + e.toString());
				//						errMsg = "Server Error";
				//						break;
				//					}
				//					cellNos = Util.fileSBToArrayList(fileBuilder);
				//				}else {
				//					String groups = req.getParameter("groups");
				//					if(groups==null||groups.length()<=0) {
				//						errMsg = "Invalid Groups";
				//						break;
				//					}
				//					
				//					cellNos = CompanySmsCustomerDao.getCustomerCellsByGroups(company.getCompId(), groups, connection);
				//				}
				//				
				//				if(cellNos.size()<=0) {
				//					errMsg = "No Cellphone numbers received!";
				//					break;
				//				}


				CompanySmsCampaign smsCampaign = new CompanySmsCampaign();
				smsCampaign.setCompId(compid);
				smsCampaign.setCampaignSendGroups(groups);
				smsCampaign.setCampaignName(campaignname);
				smsCampaign.setCampaignRecurrence(smsRecurrence);
				smsCampaign.setCampaignFrequency(smsFreq);
				smsCampaign.setCampaignStart(DateUtil.getDateValue(fromdate));
				smsCampaign.setCampaignEnd(DateUtil.getDateValue(toDate));
				smsCampaign.setCampaignStatusUser(Util.parseInt(useridStr, 0));
				smsCampaign = CompanySmsCampaignDao.createSmsCampaign(smsCampaign, connection);
				if(smsCampaign==null||smsCampaign.getCampaignId()==0) {
					errMsg = "Unable to create SMS CAMPAIGN!";
					break;
				}

				int max = 365;
				Date currDate = DateUtil.getDateValue(fromdate);
				Date endDate = DateUtil.getDateValue(toDate);
				Date startDate = DateUtil.getDateValue(fromdate);

				int iteration = 0;

				if(smsRecurrence==CompanySmsCampaign.ONCE_OFF) {
					CompanySmsCampaignItem smsCampaignItem = new CompanySmsCampaignItem();
					smsCampaignItem.setCompId(compid);
					smsCampaignItem.setCampaignId(smsCampaign.getCampaignId());
					smsCampaignItem.setCampaignItemMessage(campaignmessages);
					smsCampaignItem.setCampaignItemDate(startDate);
					smsCampaignItem.setCampaignItemTime(DateUtil.parseTime(timesStr));
					CompanySmsCampaignItemDao.createSmsCampaignItem(connection, smsCampaignItem);
				}else	if(smsRecurrence==CompanySmsCampaign.DAILY) {
					while(currDate.compareTo(endDate)<=0 || iteration==max) {
						currDate = DateUtil.getNextDate(currDate);
						startDate = DateUtil.getPrevDate(currDate);
						for(int i=0;i<smsFreq;i++) {
							CompanySmsCampaignItem smsCampaignItem = new CompanySmsCampaignItem();
							smsCampaignItem.setCompId(compid);
							smsCampaignItem.setCampaignId(smsCampaign.getCampaignId());
							smsCampaignItem.setCampaignItemMessage(messageAr[i]);
							smsCampaignItem.setCampaignItemDate(startDate);
							smsCampaignItem.setCampaignItemTime(DateUtil.parseTime(times[i]));
							CompanySmsCampaignItemDao.createSmsCampaignItem(connection, smsCampaignItem);
						}
						iteration++;
					}
				}else {
					while(currDate.compareTo(endDate)<=0 || iteration==max) {
						currDate = DateUtil.getNextDate(currDate);
						boolean found = false;
						startDate = DateUtil.getPrevDate(currDate);
						if(smsRecurrence==CompanySmsCampaign.MONTHLY) {
							int dayOfMonth = DateUtil.getDayOfMonth(startDate);
							for(int md=0;md<monthDays.length;md++) {
								if( ( Util.parseInt(monthDays[md], 32)==dayOfMonth ) || ( ( DateUtil.isLastDayOfMonth(startDate) ) && ( Util.parseInt(monthDays[md], 32) > DateUtil.getTotDaysInMonth(startDate) ) ) ) {
									found=true;
									break;
								}
							}
						}else if(smsRecurrence==CompanySmsCampaign.WEEKLY){
							int weekdayVal = DateUtil.getWeekDayInt(startDate);
							for(int wd=0;wd<weekDays.length;wd++) {
								if(Util.parseInt(weekDays[wd], 8)==weekdayVal) {
									found=true;
									break;
								}
							}
						}
						iteration++;
						if(!found)
							continue;

						CompanySmsCampaignItem smsCampaignItem = new CompanySmsCampaignItem();
						smsCampaignItem.setCompId(compid);
						smsCampaignItem.setCampaignId(smsCampaign.getCampaignId());
						smsCampaignItem.setCampaignItemMessage(campaignmessages);
						smsCampaignItem.setCampaignItemDate(startDate);
						smsCampaignItem.setCampaignItemTime(DateUtil.parseTime(timesStr));
						CompanySmsCampaignItemDao.createSmsCampaignItem(connection, smsCampaignItem);
					}
				}


				sb = JSONHelper.getSuccessJson("upload successful!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: CustomerSmsCustomers -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;		
	}

	public static StringBuilder searchCampaigns(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
					errMsg = "Company does not exist!";
					break;
				}

				String campaignName = JSONHelper.getValue(jsonBody, "campaignname");
				if(campaignName==null) {
					errMsg = "Campaign Name Invalid!";
					break;
				}
				String fromDate = JSONHelper.getValue(jsonBody, "fromdate");
				if(fromDate==null||fromDate.length()<=0) {
					errMsg = "Invalid From Date!";
					break;
				}
				String toDate= JSONHelper.getValue(jsonBody, "todate");
				if(toDate==null||toDate.length()<=0) {
					errMsg = "Invalid To Date!";
					break;
				}

				ArrayList<CompanySmsCampaign> smsCampaigns = CompanySmsCampaignDao.getSmsCampaigns(connection, company.getCompId(), campaignName, DateUtil.getDateValue(fromDate), DateUtil.getDateValue(toDate));
				sb = CompanySmsCampaignDao.getSmsCampaignsJSON(smsCampaigns);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: CustomerSmsCustomers -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;		
	}

	public static StringBuilder searchCampaignItems(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody,Connection connection) {
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
					errMsg = "Company does not exist!";
					break;
				}
				int campaignid = JSONHelper.getIntValue(jsonBody, "campaignid");
				if(campaignid==0) {
					errMsg = "Invalid campaignid";
					break;
				}
				ArrayList<CompanySmsCampaignItem> campaignItems = CompanySmsCampaignItemDao.getCampaignItems(connection, company.getCompId(), campaignid);
				sb = CompanySmsCampaignItemDao.getJsonSmsCampaignItems(campaignItems);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: CustomerSmsCustomers -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;		
	}
	
	public static StringBuilder getCampaignItem(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody,Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				long itemNumber = JSONHelper.getLongValue(jsonBody, "campaignitemno");
				if(itemNumber==0) {
					errMsg = "Invalid Item Number!";
					break;
				}

				CompanySmsCampaignItem companySmsCampaignItem = CompanySmsCampaignItemDao.getCampaignItem(connection, itemNumber);

				if(companySmsCampaignItem==null||companySmsCampaignItem.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				if(companySmsCampaignItem==null||companySmsCampaignItem.getCampaignId()==0) {
					errMsg = "Campaign does not exist!";
					break;
				}
				if(companySmsCampaignItem==null||companySmsCampaignItem.getCampaignItemNo()==0) {
					errMsg = "Item Number does not exist!";
					break;
				}

				sb = CompanySmsCampaignItemDao.getCampaignItemJSON(companySmsCampaignItem);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: UpdateCampaignItems -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder updateCampaignItems(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody,Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compId = JSONHelper.getIntValue(jsonBody, "compid");
				int campaignId = JSONHelper.getIntValue(jsonBody, "campaignid");
				long itemNumber = JSONHelper.getLongValue(jsonBody, "campaignitemno");
				if(itemNumber==0) {
					errMsg = "Invalid Item Number!";
					break;
				}

				CompanySmsCampaignItem companySmsCampaignItem = CompanySmsCampaignItemDao.getCampaignItem(connection, compId, campaignId, itemNumber) ;

				if(companySmsCampaignItem==null||companySmsCampaignItem.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				if(companySmsCampaignItem==null||companySmsCampaignItem.getCampaignId()==0) {
					errMsg = "Campaign does not exist!";
					break;
				}
				if(companySmsCampaignItem==null||companySmsCampaignItem.getCampaignItemNo()==0) {
					errMsg = "Item Number does not exist!";
					break;
				}

				String campaignitemmessage = JSONHelper.getValue(jsonBody, "campaignitemmessage");
				if(campaignitemmessage.length()==0) {
					errMsg = "Invalid Message!";
					break;
				}
				String campaignitemdate = JSONHelper.getValue(jsonBody, "campaignitemdate");
				if(campaignitemdate==null||campaignitemdate.length()==0) {
					errMsg = "Invalid date!";
					break;
				}
				Date date = DateUtil.getDateValue(campaignitemdate);
				Date currentDate = DateUtil.getCurrentCCYYMMDDDate();
				if(date.before(currentDate)) {
					errMsg = "Invalid date!";
					break;
				}
				String campaignitemtime = JSONHelper.getValue(jsonBody, "campaignitemtime");
				if(campaignitemtime.length()<=0) {
					errMsg = "Invalid time!";
					break;
				} 
				Time time = DateUtil.parseTime(campaignitemtime);
				String campaignitemstatus = JSONHelper.getValue(jsonBody, "campaignitemstatus");
				String campaignitemactive = JSONHelper.getValue(jsonBody, "campaignitemactive");

				CompanySmsCampaignItem updatedCompanySmsCampaignItem = new CompanySmsCampaignItem(compId, campaignId, itemNumber, campaignitemmessage, date, time, campaignitemstatus, campaignitemactive);
				if(CompanySmsCampaignItemDao.updateSmsCampaignItem(connection, updatedCompanySmsCampaignItem)==false) {
					errMsg = "Unable to update sms campaign item, please try again!";
					break;
				}
				sb = CompanySmsCampaignItemDao.getCampaignItemJSON(updatedCompanySmsCampaignItem);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: UpdateCampaignItems -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder processCampaignReport(HttpServletRequest req, HttpServletResponse resp, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String campaignStr = req.getParameter("campaignid");
				int campaignid = Util.parseInt(campaignStr, 0);
				if(campaignStr==null||campaignStr.length()<=0||campaignid==0) {
					errMsg = "Invalid campaigid: " + campaignStr;
					break;
				}
				String campaignname = req.getParameter("campaignname");
				if(campaignname==null||campaignname.length()<=0) {
					errMsg = "Invalid Campaign Name";
					break;
				}
				CompanySmsCampaign companySmsCampaign = CompanySmsCampaignDao.getCompSmsCampaign(connection, campaignid, campaignname);
				if(companySmsCampaign==null||companySmsCampaign.getCompId()==0) {
					errMsg = "Sms Campaign does not exist!";
					break;
				}

				//GRAPH DATA
				int sent = 60;
				StringBuilder delivered = new StringBuilder("5,6,2,4,10,3,12");
				StringBuilder times     = new StringBuilder("1,2,3,4,5,6,7");
				int notDelivered = 18;
				//

				sb = CompanySmsCampaignDao.getSmsCampaignGraphDataJSON(companySmsCampaign, sent, delivered, notDelivered, times);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: ProcessCampaignReport -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;		
	}
	
	public static StringBuilder getCampaigns(HttpServletRequest req, HttpServletResponse resp, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String compStr = req.getParameter("companyid");
				int compid = Util.parseInt(compStr, 0);
				if(compStr==null||compStr.length()<=0||compid==0) {
					errMsg = "Invalid compid: " + compStr;
					break;
				}
				ArrayList<CompanySmsCampaign> smsCampaigns = CompanySmsCampaignDao.getSmsCampaignsByCompID(connection, compid);			
				sb = CompanySmsCampaignDao.getSmsCampaignsJSON(smsCampaigns);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: GetCampaigns -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;		
	}
}
