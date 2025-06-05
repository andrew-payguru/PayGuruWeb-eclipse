package za.co.payguru.servlet.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.rmi.Naming;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;

import pay.guru.recovery.RecoveryRMIMethods;
import za.co.payguru.dao.AgentCompanyProductPromoDao;
import za.co.payguru.dao.AgentDao;
import za.co.payguru.dao.AppClientDao;
import za.co.payguru.dao.BankDao;
import za.co.payguru.dao.ClientBalanceDao;
import za.co.payguru.dao.ClientBankRefDao;
import za.co.payguru.dao.ClientBlacklistDao;
import za.co.payguru.dao.ClientCompanyBalanceDao;
import za.co.payguru.dao.ClientCompanyBankRefDao;
import za.co.payguru.dao.ClientDao;
import za.co.payguru.dao.ClientHistoryDao;
import za.co.payguru.dao.ClientInvoiceDao;
import za.co.payguru.dao.ClientInvoiceDataDao;
import za.co.payguru.dao.ClientInvoiceExtDocItemDao;
import za.co.payguru.dao.ClientInvoiceItemDao;
import za.co.payguru.dao.ClientInvoiceQueryDao;
import za.co.payguru.dao.ClientInvoiceStatusDao;
import za.co.payguru.dao.ClientLoyaltyDao;
import za.co.payguru.dao.ClientLoyaltyPointsEventsDao;
import za.co.payguru.dao.ClientOtpDao;
import za.co.payguru.dao.ClientPaymentDao;
import za.co.payguru.dao.ClientPaymentRefDao;
import za.co.payguru.dao.ClientProductDao;
import za.co.payguru.dao.ClientProductRefDao;
import za.co.payguru.dao.CompanyAdvertDao;
import za.co.payguru.dao.CompanyAgentParamsDao;
import za.co.payguru.dao.CompanyBankDao;
import za.co.payguru.dao.CompanyBankTranDao;
import za.co.payguru.dao.CompanyClientDao;
import za.co.payguru.dao.CompanyClientLoyaltyDao;
import za.co.payguru.dao.CompanyClientLoyaltyRewardTokenDao;
import za.co.payguru.dao.CompanyClientSupportDao;
import za.co.payguru.dao.CompanyClientSupportGroupDao;
import za.co.payguru.dao.CompanyDao;
import za.co.payguru.dao.CompanyDetailsDao;
import za.co.payguru.dao.CompanyInternalDao;
import za.co.payguru.dao.CompanyLoyaltyDao;
import za.co.payguru.dao.CompanyLoyaltyProductDao;
import za.co.payguru.dao.CompanyLoyaltyRewardDao;
import za.co.payguru.dao.CompanyParamDao;
import za.co.payguru.dao.CompanyProductDao;
import za.co.payguru.dao.CompanyUserDao;
import za.co.payguru.dao.CompanyUserEventDao;
import za.co.payguru.dao.CompanyUserProductDao;
import za.co.payguru.dao.CompanyUserSessionDao;
import za.co.payguru.dao.CompanyWebPagePermissionTemplateDao;
import za.co.payguru.dao.DonorClientDao;
import za.co.payguru.dao.DonorCompanyProductDao;
import za.co.payguru.dao.DonorDao;
import za.co.payguru.dao.EmailQueueDao;
import za.co.payguru.dao.FinTranDao;
import za.co.payguru.dao.LoyaltyAppUserDao;
import za.co.payguru.dao.ParamDao;
import za.co.payguru.dao.ProdRefBlacklistDao;
import za.co.payguru.dao.SmsBulkSummaryDao;
import za.co.payguru.dao.SmsQueueDao;
import za.co.payguru.dao.WebPagePermissionDao;
import za.co.payguru.dao.WebPagePermissionGroupDao;
import za.co.payguru.http.HTTPUrlConnectionClient;
import za.co.payguru.model.Agent;
import za.co.payguru.model.AgentCompanyProductPromo;
import za.co.payguru.model.AppClient;
import za.co.payguru.model.Bank;
import za.co.payguru.model.Client;
import za.co.payguru.model.ClientBalance;
import za.co.payguru.model.ClientBankRef;
import za.co.payguru.model.ClientBlacklist;
import za.co.payguru.model.ClientCompanyBalance;
import za.co.payguru.model.ClientCompanyBankRef;
import za.co.payguru.model.ClientHistory;
import za.co.payguru.model.ClientInvoice;
import za.co.payguru.model.ClientInvoiceData;
import za.co.payguru.model.ClientInvoiceExtDocItem;
import za.co.payguru.model.ClientInvoiceItem;
import za.co.payguru.model.ClientInvoiceQuery;
import za.co.payguru.model.ClientInvoiceStatus;
import za.co.payguru.model.ClientLoyalty;
import za.co.payguru.model.ClientLoyaltyHistory;
import za.co.payguru.model.ClientLoyaltyPointsEvents;
import za.co.payguru.model.ClientOtp;
import za.co.payguru.model.ClientPayment;
import za.co.payguru.model.ClientPaymentRef;
import za.co.payguru.model.ClientProduct;
import za.co.payguru.model.ClientProductRef;
import za.co.payguru.model.Company;
import za.co.payguru.model.CompanyAdvert;
import za.co.payguru.model.CompanyBank;
import za.co.payguru.model.CompanyBankTran;
import za.co.payguru.model.CompanyClient;
import za.co.payguru.model.CompanyClientLoyalty;
import za.co.payguru.model.CompanyClientLoyaltyRewardToken;
import za.co.payguru.model.CompanyClientSupport;
import za.co.payguru.model.CompanyClientSupportGroup;
import za.co.payguru.model.CompanyDetails;
import za.co.payguru.model.CompanyInternal;
import za.co.payguru.model.CompanyLoyalty;
import za.co.payguru.model.CompanyLoyaltyProduct;
import za.co.payguru.model.CompanyLoyaltyReward;
import za.co.payguru.model.CompanyParam;
import za.co.payguru.model.CompanyProduct;
import za.co.payguru.model.CompanyUser;
import za.co.payguru.model.CompanyUserEvent;
import za.co.payguru.model.CompanyUserProduct;
import za.co.payguru.model.CompanyUserSession;
import za.co.payguru.model.CompanyWebPagePermissionTemplate;
import za.co.payguru.model.Donor;
import za.co.payguru.model.DonorClient;
import za.co.payguru.model.DonorCompanyProduct;
import za.co.payguru.model.EmailQueue;
import za.co.payguru.model.FinTran;
import za.co.payguru.model.LoyaltyAppUser;
import za.co.payguru.model.ProdRefBlacklist;
import za.co.payguru.model.SmsBulkSummary;
import za.co.payguru.model.SmsQueue;
import za.co.payguru.model.WebPagePermission;
import za.co.payguru.model.WebPagePermissionGroup;
import za.co.payguru.security.SecurityUtil;
import za.co.payguru.util.Constants;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.FileUtil;
import za.co.payguru.util.HTTPUtil;
import za.co.payguru.util.JSONHelper;
import za.co.payguru.util.Util;

public class Process_webapp {

	public static final String SEARCH_ALL = "ALL";


	public static StringBuilder getCompanyParam(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				String paramid = JSONHelper.getValue(jsonBody, "paramid");
				if(paramid==null||paramid.length()<=0) {
					errMsg = "Param ID invalid!";
					break;
				}
				CompanyParam compParam = CompanyParamDao.getCompanyParam(connection, compid, paramid);
				if(compParam==null||compParam.getParamid().length()<=0) {
					errMsg = "Company Parameter Invalid!";
					break;
				}
				sb = CompanyParamDao.getParamValueJSON(compParam);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyPram -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getSaleChannelInvoiceData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				String channel = JSONHelper.getValue(jsonBody, "channel");
				if(channel==null||channel.length()<=0) 
					channel = "ALL";

				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");

				String startDate = JSONHelper.getValue(jsonBody, "startdate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid start date";
					break;
				}
				String endDate = JSONHelper.getValue(jsonBody, "enddate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid end date";
					break;
				}
				ArrayList<ClientInvoiceData> clientInvoiceDatas = ClientInvoiceDataDao.getChannelClientInvoiceData(connection, compid, compinternalid, prodid, channel, startDate, endDate);
				sb = ClientInvoiceDataDao.getClientInvoiceDatasJSON(clientInvoiceDatas);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getSaleChannelInvoiceData -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder getSaleChannelInvoiceDataSales(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				String channel = JSONHelper.getValue(jsonBody, "channel");
				if(channel==null||channel.length()<=0) 
					channel = "ALL";

				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");
				int bankid = JSONHelper.getIntValue(jsonBody, "bankid");
				if(channel.equals("SMS")==false)
					bankid = 0;

				String startDate = JSONHelper.getValue(jsonBody, "startdate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid start date";
					break;
				}
				startDate = startDate.replace('-', '/');
				String endDate = JSONHelper.getValue(jsonBody, "enddate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid end date";
					break;
				}
				endDate = endDate.replace('-', '/');
				ArrayList<ClientInvoiceData> clientInvoiceDatas = ClientInvoiceDataDao.getChannelClientInvoiceDataSales(connection, compid, compinternalid, prodid, channel, startDate, endDate);
				if(bankid>0) {
					ArrayList<ClientInvoiceData> tmpInvoices = new ArrayList<ClientInvoiceData>();
					ArrayList<String> payrefs = ClientPaymentDao.getCompBankClientPayRefs(connection, compid, bankid, startDate, endDate);
					for(int i=0;i<clientInvoiceDatas.size();i++) {
						ClientInvoiceData data = clientInvoiceDatas.get(i);
						if(payrefs.indexOf(data.getInvNo())>=0) 
							tmpInvoices.add(data);
					}
					clientInvoiceDatas = tmpInvoices;
				}
				sb = ClientInvoiceDataDao.getClientInvoiceDatasJSON(clientInvoiceDatas);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getSaleChannelInvoiceDataSales -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getSaleChannelInvoices(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				String channel = JSONHelper.getValue(jsonBody, "channel");
				if(channel==null||channel.length()<=0) 
					channel = "ALL";

				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");

				String startDate = JSONHelper.getValue(jsonBody, "startdate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid start date";
					break;
				}
				String endDate = JSONHelper.getValue(jsonBody, "enddate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid end date";
					break;
				}
				ArrayList<ClientInvoice> clientInvoices = ClientInvoiceDao.getChannelClientInvoices(connection, compid, compinternalid, prodid, channel, startDate, endDate);
				sb = ClientInvoiceDao.getClientInvoicesJSON(clientInvoices);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getSaleChannelInvoices -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder getSaleChannelInvoiceSales(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				String channel = JSONHelper.getValue(jsonBody, "channel");
				if(channel==null||channel.length()<=0) 
					channel = "ALL";

				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");
				int bankid = JSONHelper.getIntValue(jsonBody, "bankid");
				if(channel.equals("SMS")==false)
					bankid = 0;
				String startDate = JSONHelper.getValue(jsonBody, "startdate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid start date";
					break;
				}
				startDate = startDate.replace('-', '/');
				String endDate = JSONHelper.getValue(jsonBody, "enddate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid end date";
					break;
				}
				endDate = endDate.replace('-', '/');
				ArrayList<ClientInvoice> clientInvoices = ClientInvoiceDao.getChannelClientInvoiceSales(connection, compid, compinternalid, prodid, channel, startDate, endDate);
				System.out.println("INV sizes: " + clientInvoices.size());
				if(bankid>0) {
					ArrayList<ClientInvoice> tmpInvoices = new ArrayList<ClientInvoice>();
					ArrayList<String> payrefs = ClientPaymentDao.getCompBankClientPayRefs(connection, compid, bankid, startDate, endDate);
					for(int i=0;i<clientInvoices.size();i++) { 
						ClientInvoice inv = clientInvoices.get(i);
						System.out.println(inv.getInvNo());
						if(payrefs.indexOf(inv.getInvNo())>=0) {
							System.out.println("Matched");
							tmpInvoices.add(inv);
						}
					}
					clientInvoices = tmpInvoices;
				}
				sb = ClientInvoiceDao.getClientInvoicesJSON(clientInvoices);
				System.out.println(sb.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getSaleChannelInvoiceSales -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder getCompanyInternals(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				ArrayList<CompanyInternal> compInternals = CompanyInternalDao.loadCompanyInternals(connection, compid);
				sb = CompanyInternalDao.getCompanyInternalsJSON(compInternals);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyInternals -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCompanyInternalProducts(HttpServletRequest req, HttpServletResponse resp,JSONObject jsonBody, Connection connection) {
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
				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");
				ArrayList<CompanyProduct> companyProducts = CompanyProductDao.getCompanyInternalProducts(company.getCompId(), compinternalid, connection);
				sb = CompanyProductDao.getCompanyProductsJSON(companyProducts);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyInternalProduct -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder getCompanyBanks(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				ArrayList<CompanyBank> banks = CompanyBankDao.getCompanyBanks(connection, compid);
				sb = CompanyBankDao.getCompanyBankJSON(banks);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyBanks -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getClientPayments(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody,Connection connection) {
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
				int bankid = JSONHelper.getIntValue(jsonBody, "bankid");
				if(bankid==0) {
					errMsg = "Invalid bankid!";
					break;
				}
				CompanyBank compBank = CompanyBankDao.loadCompanyBanks(connection, bankid);
				if(compBank==null||compBank.getBankId()==0) {
					errMsg = "Invalid bankid!";
					break;
				}
				String startDate = JSONHelper.getValue(jsonBody, "startdate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid start date!";
					break;
				}
				String endDate = JSONHelper.getValue(jsonBody, "enddate");
				if(endDate==null||endDate.length()<=0) {
					errMsg = "Invalid end date!";
					break;
				}
				ArrayList<ClientPayment> clientpayments = ClientPaymentDao.getCompBankClientPayments(connection, company.getCompId(), bankid, startDate, endDate);
				sb = ClientPaymentDao.getClientPaymentsJson(clientpayments);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientPayments -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getPaymentClients(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				int bankid = JSONHelper.getIntValue(jsonBody, "bankid");
				if(bankid==0) {
					errMsg = "Invalid bankid!";
					break;
				}
				CompanyBank compBank = CompanyBankDao.loadCompanyBanks(connection, bankid);
				if(compBank==null||compBank.getBankId()==0) {
					errMsg = "Invalid bankid!";
					break;
				}
				String startDate = JSONHelper.getValue(jsonBody, "startdate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid start date!";
					break;
				}
				String endDate = JSONHelper.getValue(jsonBody, "enddate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid end date!";
					break;
				}
				ArrayList<Client> clients = ClientDao.getPaymentClients(connection, company.getCompId(), bankid, startDate, endDate);
				sb = ClientDao.getClientsJSON(clients);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getPaymentClients -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder reverseClientPayment(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				long tranid = JSONHelper.getLongValue(jsonBody, "paytran");
				if(tranid==0) {
					errMsg = "Invalid tranid";
					break;
				}
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				CompanyBankTran bankTran = CompanyBankTranDao.loadBankTran(connection, compid, tranid);
				if(bankTran==null||bankTran.getBankTranId()==0) {
					errMsg = "Invalid tranid";
					break;
				}
				String newcell = JSONHelper.getValue(jsonBody, "newcell");
				if(newcell==null) {
					errMsg = "Invalid Cell Number";
					break;
				}
				if(newcell.length()<=0)
					newcell = "-";
				newcell = newcell.trim();
				String reversedesc = JSONHelper.getValue(jsonBody, "reversedesc");
				if(reversedesc==null||reversedesc.length()<=0) {
					errMsg = "Invalid Cell Number";
					break;
				}
				reversedesc = reversedesc.trim();
				if(CompanyBankTranDao.reversBankTran(connection, compid, tranid, newcell, userid, reversedesc)==false) {
					errMsg = "Error updating BANKTRANSACTION!";
					break;
				}
				sb = JSONHelper.getSuccessJson("Payment Reversed Succesfully!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getPaymentClients -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getInactiveSmsInvoices(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");
				if(prodid==0) {
					errMsg = "Invalid prodid";
					break;
				}
				if(prodid!=-1) {
					CompanyProduct companyProduct = CompanyProductDao.getCompanyProduct(compid, prodid, connection);
					if(companyProduct==null||companyProduct.getProdId()==0) {
						errMsg = "Invalid Company Product";
						break;
					}
				}
				Date fromdate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "fromdate"));
				if(fromdate.equals(DateUtil.DEFAULT_DATE)) {
					errMsg = "Invalid From Date";
					break;
				}
				Date todate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "todate"));
				if(todate.equals(DateUtil.DEFAULT_DATE)) {
					errMsg = "Invalid To Date";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				String regno = JSONHelper.getValue(jsonBody, "regno");
				ArrayList<ClientInvoice> invoices = ClientInvoiceDao.getInactiveSmsInvoices(connection, compid, prodid, fromdate, todate, invno, regno);
				sb = ClientInvoiceDao.getClientInvoicesJSON(invoices);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getInactiveInvoices -> " + e.toString());
			errMsg = "Server Error";
		}

		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getInactiveSmsInvoicedata(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");
				if(prodid==0) {
					errMsg = "Invalid prodid";
					break;
				}
				if(prodid!=-1) {
					CompanyProduct companyProduct = CompanyProductDao.getCompanyProduct(compid, prodid, connection);
					if(companyProduct==null||companyProduct.getProdId()==0) {
						errMsg = "Invalid Company Product";
						break;
					}
				}
				Date fromdate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "fromdate"));
				if(fromdate.equals(DateUtil.DEFAULT_DATE)) {
					errMsg = "Invalid From Date";
					break;
				}
				Date todate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "todate"));
				if(todate.equals(DateUtil.DEFAULT_DATE)) {
					errMsg = "Invalid To Date";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				String regno = JSONHelper.getValue(jsonBody, "regno");
				ArrayList<ClientInvoiceData> invoiceData = ClientInvoiceDataDao.getInactiveSmsInvoiceData(connection, compid, prodid, fromdate, todate, invno, regno);
				sb = ClientInvoiceDataDao.getClientInvoiceDatasJSON(invoiceData);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getInactiveInvoicedata -> " + e.toString());
			errMsg = "Server Error";
		}

		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder activateSmsPolicy(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno==null||invno.length()<=0) {
					errMsg = "Invalid Policy Number!";
					break;
				}
				ClientInvoice clientInvoice = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(clientInvoice==null||clientInvoice.getInvNo().length()<=0) {
					errMsg = "Invalid Policy Number!";
					break;
				}
				int bankid = JSONHelper.getIntValue(jsonBody, "bankid");
				if(bankid==0) {
					errMsg = "Bank ID Invalid!";
					break;
				}
				CompanyBank companyBank = CompanyBankDao.loadCompanyBanks(connection, bankid);
				if(companyBank==null||companyBank.getBankId()==0) {
					errMsg = "Bank ID Invalid!";
					break;
				}
				String tranref = JSONHelper.getValue(jsonBody, "banktranref");
				if(tranref==null||tranref.length()<=0) {
					errMsg = "Invalid Transaction Reference!";
					break;
				}
				ClientPaymentRef paymentRef = ClientPaymentRefDao.loadPaymentRef(connection, compid, tranref);
				if(paymentRef==null||paymentRef.getPayref().length()<=0) {
					errMsg = "Payment Reference does not exist";
					break;
				}
				if(paymentRef.getInvno().equals(clientInvoice.getInvNo())==false) {
					errMsg = "Payment Reference does not exist for invoice";
					break;
				}
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				if(userid==0) {
					errMsg = "User ID Invalid!";
					break;
				}
				double tranamt = JSONHelper.getDoubleValue(jsonBody, "tranamt");
				if(tranamt==0) {
					errMsg = "Transaction Amount Invalid!";
					break;
				}
				if(paymentRef.getPayamt()>tranamt) {
					errMsg = "Insufficient Transaction Amount!";
					break;
				}
				paymentRef.setStatus(ClientPaymentRef.STATUS_WAITING);
				if(ClientPaymentRefDao.updatePaymentRefStatus(connection, paymentRef)==false) {
					errMsg = "Error Updating Payment Reference!";
					break;
				}

				CompanyBankTran companyBankTran = new CompanyBankTran();
				companyBankTran.setBankCompId(company.getCompId());
				companyBankTran.setBankId(companyBank.getBankId());
				companyBankTran.setBankTranType(CompanyBankTran.TYPE_FILE);
				companyBankTran.setBankTranRef(CompanyBankTran.FAKE_TRAN);
				companyBankTran.setBankTranRef1(tranref);
				companyBankTran.setBankTranRef2(tranref);
				companyBankTran.setBankTranStatus(CompanyBankTran.STATUS_ACTIVE);
				companyBankTran.setBankTranStatusDate(DateUtil.getCurrentCCYYMMDDStr());
				companyBankTran.setBankTranStatusTime(DateUtil.getCurrentHHMMSSStr());
				companyBankTran.setBankTranAmt1(tranamt);
				companyBankTran.setBankTranDate(DateUtil.getCurrentCCYYMMDDStr());
				companyBankTran.setBankTranCreatedDate(DateUtil.getCurrentCCYYMMDDStr());
				companyBankTran.setBankTranCreatedTime(DateUtil.getCurrentHHMMSSStr());
				companyBankTran.setBankTranCredit(true);
				companyBankTran.setBankTranActive(CompanyBankTran.COMPBANKTRAN_ACTIVE);
				companyBankTran.setBankTranStatusUser(userid);

				System.out.println("CREATING BANKTRAN");
				if(CompanyBankTranDao.createBankTran(connection, companyBankTran)==false) {
					errMsg = "Error Creating Bank Transaction!";
					break;
				}

				sb = JSONHelper.getSuccessJson("Invoice Activated");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: activateSmsPolicy -> " + e.toString());
			errMsg = "Server Error";
		}

		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder getParam(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String paramid = JSONHelper.getValue(jsonBody, "paramid");
				if(paramid==null||paramid.length()<=0) {
					errMsg = "Invalid Paramid!";
					break;
				}
				String paramvalue = ParamDao.getParam(paramid, connection);
				sb = JSONHelper.createJsonMessage("param", paramvalue);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getParam -> " + e.toString());
			errMsg = "Server Error";
		}

		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder postProduct(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				String prodcode = JSONHelper.getValue(jsonBody, "prodcode");
				if(prodcode.length()<=0) {
					errMsg = "Invalid Product Code!";
					break;
				}
				String prodname = JSONHelper.getValue(jsonBody, "prodname");
				if(prodname.length()<=0) {
					errMsg = "Invalid Product Name!";
					break;
				}
				String prodshortname = JSONHelper.getValue(jsonBody, "prodshortname");				
				String prodsmsjoinword = JSONHelper.getValue(jsonBody, "prodsmsjoinword");
				//				if(prodsmsjoinword.length()<=0) {
				//					errMsg = "Invalid Product SMS Join Word!";
				//					break;
				//				}
				String prodregsuccessmsg = JSONHelper.getValue(jsonBody, "prodregsuccessmsg");
				int prodstructure = JSONHelper.getIntValue(jsonBody, "prodstructure");

				int prodsupplylimit = JSONHelper.getIntValue(jsonBody, "prodsupplylimit");
				int prodtype = JSONHelper.getIntValue(jsonBody, "prodtype");
				int prodrebillinterval = JSONHelper.getIntValue(jsonBody, "prodrebillinterval");
				if(prodtype==CompanyProduct.PROD_REBILL_FREQ_CUSTOM) {
					if(prodrebillinterval<=0) {
						errMsg = "Invalid Product Rebill Interval!";
						break;
					}
				}
				int prodpaytype = JSONHelper.getIntValue(jsonBody, "prodpaytype");
				if(prodpaytype==0) {
					errMsg = "Invalid Product Payment Type!";
					break;
				}
				double prodsellingprice = 0;
				double prodcostprice = 0;
				if(prodpaytype==CompanyProduct.PROD_PAY_TYPE_FIXED) {
					prodsellingprice = JSONHelper.getDoubleValue(jsonBody, "prodsellingprice");
					if(prodsellingprice==0) {
						errMsg = "Invalid Product Selling Price!";
						break;
					}
					prodcostprice = JSONHelper.getDoubleValue(jsonBody, "prodcostprice");
				}
				int prodrebilltype = JSONHelper.getIntValue(jsonBody, "prodrebilltype");
				if(prodrebilltype==0) {
					errMsg = "Invalid Product Rebill Type!";
					break;
				}
				int prodlifecycle = JSONHelper.getIntValue(jsonBody, "prodlifecycle");
				if(prodlifecycle==0) {
					errMsg = "Invalid Product Lifecycle!";
					break;
				}
				int prodlifecyclecap = JSONHelper.getIntValue(jsonBody, "prodlifecyclecap");
				//				if(prodlifecycle==CompanyProduct.PROD_LIFECYCLE_LIMITED) {
				//					if(prodlifecyclecap==0) {
				//						errMsg = "Invalid Product Lifecycle Cap!";
				//						break;
				//					}
				//				}
				Date prodstartdate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "prodstartdate"));
				Date prodenddate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "prodenddate"));
				int prodtermination = JSONHelper.getIntValue(jsonBody, "prodtermination");
				if(prodtermination==0) 
					prodenddate = DateUtil.MAX_DATE;
				int prodprorata = JSONHelper.getIntValue(jsonBody, "prodprorata");

				CompanyProduct companyProduct = new CompanyProduct();
				companyProduct.setCompId(compid);
				companyProduct.setProdCode(prodcode);
				companyProduct.setProdName(prodname);
				companyProduct.setProdShortName(prodshortname);
				companyProduct.setProdType(prodtype);
				companyProduct.setProdTypeAmt1(prodlifecyclecap);
				companyProduct.setProdTypeAmt2(prodrebillinterval);
				companyProduct.setProdTypeRsp(prodsellingprice);
				companyProduct.setProdTypeCost(prodcostprice);
				companyProduct.setProdJoinName(prodsmsjoinword);
				companyProduct.setProdProRata(""+prodprorata);
				companyProduct.setProdTypePay(prodpaytype);
				companyProduct.setProdTypeRebill(prodrebilltype);
				companyProduct.setProdTypeCycle(prodlifecycle);
				companyProduct.setProdStartDate(String.valueOf(prodstartdate));
				companyProduct.setProdEndDate(String.valueOf(prodenddate));
				companyProduct.setProdSmsMessage1(prodregsuccessmsg);
				companyProduct.setProdStruct(prodstructure);
				companyProduct.setProdStructAmt1(prodsupplylimit);

				companyProduct = CompanyProductDao.createCompanyProduct(connection, companyProduct);
				if(companyProduct.getProdId()==0) {
					errMsg = "Unable To Create Product!";
					break;
				}
				sb = CompanyProductDao.getCompanyProductJSON(companyProduct);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: postProduct -> " + e.toString());
			errMsg = "Server Error";
		}

		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;

	}
	public static StringBuilder searchCompProducts(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
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
				String prodcode = JSONHelper.getValue(jsonBody, "prodcode");
				if(prodcode==null)
					prodcode = "";

				ArrayList<CompanyProduct> companyProducts = CompanyProductDao.getCompanyProductsLikeProdCode(company.getCompId(), prodcode, connection);
				sb = CompanyProductDao.getCompanyProductsJSON(companyProducts);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchCompProducts -> " + e.toString());
			errMsg = "Server Error";
		}

		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder updateCompProduct(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");
				if(prodid==0) {
					errMsg = "Invalid Product ID!";
					break;
				}
				CompanyProduct compProduct = CompanyProductDao.getCompanyProduct(compid, prodid, connection);
				if(compProduct==null||compProduct.getProdId()==0) {
					errMsg = "Invalid Company Product!";
					break;
				}
				String prodcode = JSONHelper.getValue(jsonBody, "prodcode");
				if(prodcode.length()<=0) {
					errMsg = "Invalid Product Code!";
					break;
				}
				String prodname = JSONHelper.getValue(jsonBody, "prodname");
				if(prodname.length()<=0) {
					errMsg = "Invalid Product Name!";
					break;
				}
				String prodshortname = JSONHelper.getValue(jsonBody, "prodshortname");				
				String prodsmsjoinword = JSONHelper.getValue(jsonBody, "prodsmsjoinword");
				if(prodsmsjoinword.length()<=0) {
					errMsg = "Invalid Product SMS Join Word!";
					break;
				}
				String prodregsuccessmsg = JSONHelper.getValue(jsonBody, "prodregsuccessmsg");
				int prodstructure = JSONHelper.getIntValue(jsonBody, "prodstructure");

				int prodsupplylimit = JSONHelper.getIntValue(jsonBody, "prodsupplylimit");
				int prodrebillfreq = JSONHelper.getIntValue(jsonBody, "prodrebillfreq");
				int prodrebillinterval = JSONHelper.getIntValue(jsonBody, "prodrebillinterval");
				if(prodrebillfreq==CompanyProduct.PROD_REBILL_FREQ_CUSTOM) {
					if(prodrebillinterval<=0) {
						errMsg = "Invalid Product Rebill Interval!";
						break;
					}
				}
				int prodpaytype = JSONHelper.getIntValue(jsonBody, "prodpaytype");
				if(prodpaytype==0) {
					errMsg = "Invalid Product Payment Type!";
					break;
				}
				double prodsellingprice = 0;
				double prodcostprice = 0;
				if(prodpaytype==CompanyProduct.PROD_PAY_TYPE_FIXED) {
					prodsellingprice = JSONHelper.getDoubleValue(jsonBody, "prodsellingprice");
					if(prodsellingprice==0) {
						errMsg = "Invalid Product Selling Price!";
						break;
					}
					prodcostprice = JSONHelper.getDoubleValue(jsonBody, "prodcostprice");
				}
				int prodrebilltype = JSONHelper.getIntValue(jsonBody, "prodrebilltype");
				if(prodrebilltype==0) {
					errMsg = "Invalid Product Rebill Type!";
					break;
				}
				int prodlifecycle = JSONHelper.getIntValue(jsonBody, "prodlifecycle");
				if(prodlifecycle==0) {
					errMsg = "Invalid Product Lifecycle!";
					break;
				}
				int prodlifecyclecap = JSONHelper.getIntValue(jsonBody, "prodlifecyclecap");
				if(prodlifecycle==CompanyProduct.PROD_LIFECYCLE_LIMITED) {
					//					if(prodlifecyclecap==0) {
					//						errMsg = "Invalid Product Lifecycle Cap!";
					//						break;
					//					}
				}
				Date prodstartdate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "prodstartdate"));
				Date prodenddate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "prodenddate"));
				int prodtermination = JSONHelper.getIntValue(jsonBody, "prodtermination");
				if(prodtermination==0) 
					prodenddate = DateUtil.MAX_DATE;
				int prodprorata = JSONHelper.getIntValue(jsonBody, "prodprorata");

				compProduct.setProdCode(prodcode);
				compProduct.setProdName(prodname);
				compProduct.setProdShortName(prodshortname);
				compProduct.setProdJoinName(prodsmsjoinword);
				compProduct.setProdType(prodrebillfreq);
				compProduct.setProdTypeAmt1(prodlifecyclecap);
				compProduct.setProdTypeAmt2(prodrebillinterval);
				compProduct.setProdTypeRsp(prodsellingprice);
				compProduct.setProdTypeCost(prodcostprice);
				compProduct.setProdProRata(""+prodprorata);
				compProduct.setProdTypePay(prodpaytype);
				compProduct.setProdTypeRebill(prodrebilltype);
				compProduct.setProdTypeCycle(prodlifecycle);
				compProduct.setProdStartDate(String.valueOf(prodstartdate));
				compProduct.setProdEndDate(String.valueOf(prodenddate));
				compProduct.setProdSmsMessage1(prodregsuccessmsg);
				compProduct.setProdStruct(prodstructure);
				compProduct.setProdStructAmt1(prodsupplylimit);
				compProduct = CompanyProductDao.updateCompanyProduct(connection, compProduct);
				if(compProduct.getProdId()==0) {
					errMsg = "Unable To Update Company Product!";
					break;
				}
				sb = CompanyProductDao.getCompanyProductJSON(compProduct);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: updateCompProduct -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchCompanyClients(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String cellno = JSONHelper.getValue(jsonBody, "cellno");
				if(cellno.length()<3) {
					errMsg = "Invalid Cell Number!";
					break;
				}

				ArrayList<Client> clients = ClientDao.getCompanyClientsLikeCell(connection, compid, cellno);
				sb = ClientDao.getClientsJSON(clients);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchCompanyClients -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchCompanyClientBalances(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String cellno = JSONHelper.getValue(jsonBody, "cellno");
				if(cellno.length()<3) {
					errMsg = "Invalid Cell Number!";
					break;
				}

				ArrayList<ClientBalance> balances = ClientBalanceDao.getCompanyClientBalances(connection, compid, cellno);
				sb = ClientBalanceDao.getClientBalancesJson(balances);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchCompanyClientBalances -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchCompanyClient(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int clientid= JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				CompanyClient compClient = CompanyClientDao.loadCompanyClient(connection, compid, clientid);
				if(compClient.getClientId()==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				sb = new StringBuilder(compClient.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchCompanyClient -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder updateClientExtended(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client==null||client.getClientid()==0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				CompanyClient compClient = CompanyClientDao.loadCompanyClient(connection, compid, clientid);
				if(compClient==null||compClient.getClientId()==0) {
					errMsg = "Company Client Does Not Exist!";
					break;
				}
				String custname = JSONHelper.getValue(jsonBody, "custname"); 
				if(custname.length()<=0) {
					errMsg = "Invalid Customer Name!";
					break;
				}
				String custsurname = JSONHelper.getValue(jsonBody, "custsurname");
				if(custsurname.length()<=0) {
					errMsg = "Invalid Customer Surname!";
					break;
				}
				String custcellno = JSONHelper.getValue(jsonBody, "custcellno");
				if(custcellno.length()<=0) {
					errMsg = "Invalid Customer Cell No!";
					break;
				}
				Client compareClient = ClientDao.loadClientByCellNo(connection, custcellno);
				if(compareClient.getClientid()>0&&client.getClientid()!=compareClient.getClientid()) {
					errMsg = "Cell Number ["+custcellno+"] Is Already In Use By Client ["+compareClient.getClientname() + " " + compareClient.getClientsurname()+"]!";
					break;
				}
				String custemail = JSONHelper.getValue(jsonBody, "custemail");
				String custidno = JSONHelper.getValue(jsonBody, "custidno");
				String custtelno = JSONHelper.getValue(jsonBody, "custtelno");
				String custextref = JSONHelper.getValue(jsonBody, "custextref");
				if(custextref.length()<=0) {
					errMsg = "Invalid Customer Ext Ref!";
					break;
				}
				String custStatus = JSONHelper.getValue(jsonBody, "custstatus");

				client.setClientname(custname);
				client.setClientsurname(custsurname);
				client.setClientcellno(custcellno);
				client.setClientemail(custemail);
				client.setClientidno(custidno);
				client.setClienttelno(custtelno);
				client = ClientDao.updateClientBasic(connection, client);
				if(client.getClientid()==0) {
					errMsg = "Could Not Update Client!";
					break;
				}
				compClient.setClientExtRef(custextref);
				compClient.setStatus(custStatus);
				compClient = CompanyClientDao.updateCompanyClientBasic(connection, compClient);
				if(compClient.getClientId()==0) {
					errMsg = "Could Not Update Company Client!";
					break;
				}

				sb.append("{");
				sb.append("\"client\" : "+ client.toJsonString() + ",");
				sb.append("\"companyclient\" : "+ compClient.toJsonString());
				sb.append("}");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: updateClientExtended -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder updateBlacklistCustomer(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client==null||client.getClientid()==0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				CompanyClient compClient = CompanyClientDao.loadCompanyClient(connection, compid, clientid);
				if(compClient==null||compClient.getClientId()==0) {
					errMsg = "Company Client Does Not Exist!";
					break;
				}
				ArrayList<Integer> prodids = new ArrayList<Integer>();
				String [] tmpProds = JSONHelper.getValue(jsonBody, "prodids").split(",");
				for(int i=0;i<tmpProds.length;i++) {
					int pId = Util.parseInt(tmpProds[i].replace("[", "").replace("]", ""),0);
					if(pId==-1)
						continue;
					prodids.add(pId);
				}

				ArrayList<Integer> updatedProdIds = ClientBlacklistDao.updateClientBlacklist(connection, compid, clientid, prodids);
				sb.append("[");
				for(int i=0;i<updatedProdIds.size();i++) {
					sb.append((i==0 ? "" : ",") + updatedProdIds.get(i));
				}
				sb.append("]");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: updateBlacklistCustomer -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCompanyProducts(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				ArrayList<CompanyProduct> compProds = CompanyProductDao.getCompanyProducts(compid, connection);
				sb = CompanyProductDao.getCompanyProductsJSON(compProds);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyProducts -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchBlacklistedClients(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String clientcell = JSONHelper.getValue(jsonBody, "cellno");
				if(clientcell.length()<=0) {
					errMsg = "Invalid Client Cell!";
					break;
				}

				ArrayList<ClientBlacklist> blacklistedClients = ClientBlacklistDao.searchBlacklistedClientsLike(connection, compid, clientcell);
				sb = ClientBlacklistDao.getBlacklistClientsJson(blacklistedClients);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchBlacklistedClients -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCompanyAdverts(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				ArrayList<CompanyAdvert> compAds = CompanyAdvertDao.getCompanyAds(connection, compid);
				sb = CompanyAdvertDao.getCompanyAdvertsJSON(compAds);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyAdvert -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchLatestInvoiceByProdRef(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}

				String prodref = JSONHelper.getValue(jsonBody, "prodref");
				if(prodref.length()<=0) {
					errMsg = "Invalid Product Reference!";
					break;
				}

				ClientInvoice clientInvoice = ClientInvoiceDao.searchLatestClientInvoiceByProdRef(connection, compid, prodref);
				sb = new StringBuilder(clientInvoice.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchInvoicesByProdRef -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder uploadProdRefBlacklist(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String prodref = JSONHelper.getValue(jsonBody, "prodref");
				String reason = JSONHelper.getValue(jsonBody, "reason");
				
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				if(prodref.length()<=0) {
					errMsg = "Invalid Product Reference!";
					break;
				}
				if(reason.length()<=0) {
					errMsg = "Invalid Reason";
					break;
				}
				System.out.println("HELLO ANDREW");
				ProdRefBlacklist prodRefBlacklist = new ProdRefBlacklist();
				prodRefBlacklist.setCompid(compid);
				prodRefBlacklist.setProdref(prodref);
				prodRefBlacklist.setProdid(ProdRefBlacklist.PRODREFBLACKLIST_ALL);
				prodRefBlacklist.setReason(reason);
				prodRefBlacklist.setBlacklistdate(DateUtil.getCurrentCCYYMMDDStr());
				prodRefBlacklist.setBlacklisttime(DateUtil.getCurrentHHMMSSStr());
				boolean unblacklist = JSONHelper.getBooleanValue(jsonBody, "unblacklist");
				if(unblacklist) {
					if(ProdRefBlacklistDao.deleteProdRefBlacklist(connection, prodRefBlacklist)==false) {
						errMsg = "Unable To Un-blacklist Product Reference!";
						break;
					}

				}else {
					if(ProdRefBlacklistDao.uploadProdRefBlacklist(connection, prodRefBlacklist)==false) {
						errMsg = "Unable To Blacklist Product Reference!";
						break;
					}
				}

				sb = JSONHelper.getSuccessJson("Successfully Blacklist Product Reference!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: uploadProdRefBlacklist -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	//old can remove when change to payguru2
	public static StringBuilder searchInvoicesByProd(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");
				if(prodid==0) {
					errMsg = "Invalid Product ID!";
					break;
				}
				CompanyProduct product = CompanyProductDao.getCompanyProduct(compid, prodid, connection);
				if(product==null||product.getProdId()==0) {
					errMsg = "No Such Product";
					break;
				}
				Date fromDate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "fromdate"));
				if(fromDate==null||fromDate.equals(DateUtil.DEFAULT_DATE))
					fromDate = DateUtil.getPrevDate(fromDate, 30);
				Date toDate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "todate"));
				if(toDate==null||toDate.equals(DateUtil.DEFAULT_DATE))
					toDate = DateUtil.getCurrentCCYYMMDDDate();

				ArrayList<ClientInvoice> invoices = ClientInvoiceDao.getInvoicesByProduct(compid, prodid, String.valueOf(fromDate), String.valueOf(toDate), connection);
				sb = ClientInvoiceDao.getClientInvoicesJSON(invoices);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchInvoicesByProd -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchClientsByInvoicesProd(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");
				if(prodid==0) {
					errMsg = "Invalid Product ID!";
					break;
				}
				CompanyProduct product = CompanyProductDao.getCompanyProduct(compid, prodid, connection);
				if(product==null||product.getProdId()==0) {
					errMsg = "No Such Product";
					break;
				}

				int period = JSONHelper.getIntValue(jsonBody, "period");
				Date from = DateUtil.getCurrentCCYYMMDDDate();
				Date to = DateUtil.getCurrentCCYYMMDDDate();
				if(period==0) { 
					from = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "fromdate"));
					if(from==null||from.equals(DateUtil.DEFAULT_DATE))
						from = DateUtil.getPrevDate(from, 30);
					to = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "todate"));
					if(to==null||to.equals(DateUtil.DEFAULT_DATE))
						to = DateUtil.getCurrentCCYYMMDDDate();
				}else {
					if(period==Constants.PERIOD_TYPE_CURRENT)
						from = DateUtil.getPrevDate(from,30);
					if(period==Constants.PERIOD_TYPE_60_DAY)
						from = DateUtil.getPrevDate(from,60);
					if(period==Constants.PERIOD_TYPE_90_DAY)
						from = DateUtil.getPrevDate(from,90);
					if(period==Constants.PERIOD_TYPE_120_DAY)
						from = DateUtil.getPrevDate(from,120);
					if(period==Constants.PERIOD_TYPE_FUTURE)
						to = DateUtil.getNextDate(from,30);
				}


				ArrayList<Client> clients = ClientDao.getClientsByInvoicesProd(compid, prodid, String.valueOf(from), String.valueOf(to), connection);
				sb = ClientDao.getClientsJSON(clients);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchInvoicesByProd -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchUserProdInvoicesCell(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				if(userid==0) {
					errMsg = "Invalid User ID";
					break;
				}
				CompanyUser companyUser = CompanyUserDao.loadCompanyUser(compid, userid, connection);
				if(companyUser==null||companyUser.getUserId()==0) {
					errMsg = "Company User does not exist!";
					break;
				}
				String cellno = JSONHelper.getValue(jsonBody, "cellno");
				if(cellno.length()<=0) {
					errMsg = "Invalid Cellphone Number!";
					break;
				}
				Date fromDate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "fromdate"));
				if(fromDate==null||fromDate.equals(DateUtil.DEFAULT_DATE))
					fromDate = DateUtil.getPrevDate(fromDate, 30);
				Date toDate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "todate"));
				if(toDate==null||toDate.equals(DateUtil.DEFAULT_DATE))
					toDate = DateUtil.getCurrentCCYYMMDDDate();

				ArrayList<ClientInvoice> invoices;
				if(CompanyParamDao.getCompParamValue(connection, compid, "compuserprodcheck").equalsIgnoreCase("true")) {
					invoices = ClientInvoiceDao.searchUserProdInvoicesByCell(compid, userid, cellno, String.valueOf(fromDate), String.valueOf(toDate), connection);
				}else
					invoices = ClientInvoiceDao.searchInvoicesByCell(compid, cellno, String.valueOf(fromDate), String.valueOf(toDate), connection);

				sb = ClientInvoiceDao.getClientInvoicesJSON(invoices);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchUserProdInvoicesCell -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchClientByCell(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String clientCell = JSONHelper.getValue(jsonBody, "cellno");
				if(clientCell.length()<9) {
					errMsg = "Invalid Cell Number!";
					break;
				}
				Client client = ClientDao.loadClientByCellNo(connection, clientCell);
				sb = new StringBuilder(client.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchClientByCell -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchCompClientByCell(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String clientCell = JSONHelper.getValue(jsonBody, "cellno");
				if(clientCell.length()<9) {
					errMsg = "Invalid Cell Number!";
					break;
				}
				Client client = ClientDao.loadCompClientByCellNo(connection, compid, clientCell);
				sb = new StringBuilder(client.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchClientByCell -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder loadAppClient(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientId= JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientId==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				AppClient appClient = AppClientDao.loadAppClient(connection, clientId);
				sb = new StringBuilder(appClient.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: loadAppClient -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder createAppClient(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientId= JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientId==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				AppClient existAppClient =  AppClientDao.loadAppClient(connection, clientId);
				if(existAppClient.getClientId()>0) {
					errMsg = "App User Already Exists! Client ID: ["+existAppClient.getClientId()+"]";
					break;
				}
				String cellNo = JSONHelper.getValue(jsonBody, "clientcellno");
				if(cellNo.length()<=0){
					errMsg = "Invalid Cell Number!";
					break;
				}
				existAppClient = AppClientDao.loadAppClientByCell(connection, cellNo);
				if(existAppClient.getClientId()>0) {
					errMsg = "App User Already Exists! Cell Number: ["+existAppClient.getClientCellNo()+"]";
					break;
				}
				String emailAddr = JSONHelper.getValue(jsonBody, "clientemail");
				if(emailAddr.length()<=0){
					errMsg = "Invalid Email Address!";
					break;
				}
				existAppClient = AppClientDao.loadAppClientByEmail(connection, emailAddr);
				if(existAppClient.getClientId()>0) {
					errMsg = "App User Already Exists! Email: ["+existAppClient.getClientEmail()+"]";
					break;
				}
				String password = JSONHelper.getValue(jsonBody, "clientpassword");
				if(Util.validatePasswordStrength(password)==false) {
					errMsg = "Invalid Password!";
					break;
				}
				AppClient appClient = new AppClient();
				appClient = AppClientDao.addAppClient(connection, appClient);				
				sb = new StringBuilder(appClient.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: createAppClient -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder registerClient(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int regType= JSONHelper.getIntValue(jsonBody, "regtype");
				String email = JSONHelper.getValue(jsonBody, "email");
				String password = JSONHelper.getValue(jsonBody, "password");
				String confirmPassword = JSONHelper.getValue(jsonBody, "confirmpassword");
				String name = JSONHelper.getValue(jsonBody, "name"); 
				String surname = JSONHelper.getValue(jsonBody, "surname");
				String cell = JSONHelper.getValue(jsonBody, "cell");

				if(regType==0) {
					errMsg = "Invalid Reg Type!";
					break;
				}
				if(email.length()<=0) {
					errMsg = "Invalid Email!";
					break;
				}
				if(Util.validatePasswordStrength(password)==false) {
					errMsg = "Invalid Password!";
					break;
				}
				if(confirmPassword.equals(password)==false) {
					errMsg = "Password's Do Not Match!";
					break;
				}


				cell = Util.removeSpecialCharactersSpace(cell.trim());
				Client client = ClientDao.loadClientByCellNo(connection, cell);
				if(regType==Client.REG_TYPE_APP_ONLY) {
					if(client.getClientid()==0) {
						errMsg = "Server Error, Client Does Not Exist. Please Reload The App And Try Again!";
						break;
					}
				}

				AppClient appClient = AppClientDao.loadAppClientByCell(connection, cell);
				if(appClient.getClientId()>0) {
					errMsg = "App User Already Exists! Cell Number: ["+appClient.getClientCellNo()+"]";
					break;
				}
				appClient = AppClientDao.loadAppClientByEmail(connection, email);
				if(appClient.getClientId()>0) {
					errMsg = "App User Already Exists! Email: ["+appClient.getClientEmail()+"]";
					break;
				}

				if(regType==Client.REG_TYPE_FULL) {
					if(name.length()<=0) {
						errMsg = "Invalid Name!";
						break;
					}
					if(surname.length()<=0) {
						errMsg = "Invalid Surname!";
						break;
					}
					if(cell.length()<9) {
						errMsg = "Invalid Cell!";
						break;
					}
					String prefix = ParamDao.getParam("smsproviderprefix_recargaaki", connection);
					if(cell.length()==9&&prefix.length()>0&&cell.indexOf(prefix)!=0)
						cell = prefix+cell;
					if(client.getClientid()>0) {
						errMsg = "Client Cell ["+cell+"] Already Exists!";
						break;
					}
					client.setClientname(name);
					client.setClientsurname(surname);
					client.setClientcellno(cell);
					client.setClientemail(email);
					client.setClientpassword(password);
					client = ClientDao.createClient(connection, client);
					if(client.getClientid()==0) {
						errMsg = "Unable To Create Client!";
						break;
					}
				}appClient.setClientStatusTime(DateUtil.getCurrentHHMMSSTime());
				appClient.setClientId(client.getClientid());
				appClient.setClientCellNo(client.getClientcellno());
				appClient.setClientEmail(email);
				appClient.setClientPassword(password);
				appClient = AppClientDao.addAppClient(connection, appClient);
				if(appClient.getClientId()<=0) {
					errMsg = "Unable To Create App Client!";
					break;
				}
				sb = new StringBuilder("{\"client\" : " + client.toJsonString() + ", \"appclient\" : " + appClient.toJsonString() + "}");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: registerClient -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder loginClientApp(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String cell = JSONHelper.getValue(jsonBody, "cellphone");
				String password = JSONHelper.getValue(jsonBody, "password");

				if(cell.length()<=0) {
					errMsg = "9001";
					break;
				}
				if(password.length()<=0) {
					errMsg = "9002";
					break;
				}
				AppClient appClient = AppClientDao.loadAppClientByCell(connection, cell);
				if(appClient.getClientId()<=0) {
					errMsg = "9003";
					break;
				}
				if(appClient.getClientPassword().equals(password)==false) {
					errMsg = "9004";
					break;
				}
				Client client = ClientDao.loadClient(connection, appClient.getClientId());
				if(client.getClientid()<=0) {
					errMsg = "9005";
					break;
				}

				//if appClient.status = IDLE, request OTP....

				appClient.setClientLastLogDate(DateUtil.getCurrentCCYYMMDDDate());
				appClient.setClientLastLogTime(DateUtil.getCurrentHHMMSSTime());
				appClient.setClientStatus(AppClient.STATUS_ACTIVE);
				appClient.setClientStatusDate(DateUtil.getCurrentCCYYMMDDDate());
				appClient.setClientStatusTime(DateUtil.getCurrentHHMMSSTime());
				appClient = AppClientDao.updateAppClient(connection, appClient);
				if(appClient.getClientId()<=0) {
					errMsg = "9006";
					break;
				}
				JSONObject json = new JSONObject();
				JSONObject clientJson = client.toJSON();
				JSONObject clientAppJson = appClient.toJSON();
				json.put("client", clientJson);
				json.put("appclient", clientAppJson);
				sb = new StringBuilder(json.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: loginClientApp -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getAppCompatCompanys(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				ArrayList<Company> companys = CompanyDao.getAppCompatCompanys(connection);
				sb = CompanyDao.getJsonCompanys(companys);				
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getAppCompatCompanys -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getClientCompIds(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				ArrayList<Integer> compids = CompanyClientDao.loadClientCompIds(connection, clientid);
				sb.append("[");
				for(int i=0;i<compids.size();i++) {
					sb.append((i==0 ? "" : ",") + compids.get(i));
				}
				sb.append("]");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientCompIds -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getRecentCompClientClientInvoices(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()==0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				ArrayList<ClientInvoice> allClientInvoices = ClientInvoiceDao.loadClientClientInvoices(connection, compid, clientid);
				ArrayList<ClientInvoiceData> allClientInvoiceData = ClientInvoiceDataDao.loadClientClientInvoiceData(connection, compid, clientid);
				ArrayList<String> distinctRecentPayRefs = new ArrayList<String>();
				ArrayList<ClientInvoice> clientInvoices = new ArrayList<ClientInvoice>();
				ArrayList<ClientInvoiceData> clientInvoiceData = new ArrayList<ClientInvoiceData>();
				for(int i=0;i<allClientInvoices.size();i++) {
					ClientInvoice inv = allClientInvoices.get(i);
					if(distinctRecentPayRefs.indexOf(inv.getPayRef())>=0)
						continue;
					distinctRecentPayRefs.add(inv.getPayRef());
					clientInvoices.add(inv);
					for(int j=0;j<allClientInvoiceData.size();j++) {
						ClientInvoiceData invData = allClientInvoiceData.get(j);
						if(inv.getInvNo().equals(invData.getInvNo())) {
							clientInvoiceData.add(allClientInvoiceData.get(j));
							break;
						}
					}
				}

				sb = new StringBuilder("{\"clientinvoices\" : " + ClientInvoiceDao.getClientInvoicesJSON(clientInvoices) + ", \"clientinvoicedata\" : " + ClientInvoiceDataDao.getClientInvoiceDatasJSON(clientInvoiceData) + "}");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getRecentCompClientClientInvoices -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder loadClientInvoiceData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<=0) {
					errMsg = "Invalid Invoice Number!";
					break;
				}

				ClientInvoiceData clientInvoiceData = ClientInvoiceDataDao.loadClientInvoiceData(connection, compid, invno);
				sb = new StringBuilder(clientInvoiceData.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: loadClientInvoiceData -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder loadCompParamValue(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String paramid = JSONHelper.getValue(jsonBody, "paramid");
				if(paramid.length()<=0) {
					errMsg = "Invalid Param ID!";
					break;
				}
				String paramValue = CompanyParamDao.getCompParamValue(connection, compid, paramid);
				sb = JSONHelper.createJsonMessage("paramvalue", paramValue);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: loadCompParam -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder loadClientInvoices(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()==0) {
					errMsg = "Client does not exist!";
					break;
				}
				int period = JSONHelper.getIntValue(jsonBody, "period");

				Date from = DateUtil.getCurrentCCYYMMDDDate();
				Date to = DateUtil.getCurrentCCYYMMDDDate();

				if(period==Constants.PERIOD_TYPE_CURRENT)
					from = DateUtil.getPrevDate(from,30);
				if(period==Constants.PERIOD_TYPE_60_DAY)
					from = DateUtil.getPrevDate(from,60);
				if(period==Constants.PERIOD_TYPE_90_DAY)
					from = DateUtil.getPrevDate(from,90);
				if(period==Constants.PERIOD_TYPE_120_DAY)
					from = DateUtil.getPrevDate(from,120);
				if(period==Constants.PERIOD_TYPE_FUTURE)
					to = DateUtil.getNextDate(from,30);

				boolean ignoreStatus = JSONHelper.getBooleanValue(jsonBody, "ignorestatus");

				ArrayList<ClientInvoice> invoices = ClientInvoiceDao.loadClientClientInvoices(connection, compid, clientid, (period==0?false:true), from.toString(), to.toString(), ignoreStatus);
				ArrayList<ClientInvoiceData> invoiceData = ClientInvoiceDataDao.loadClientClientInvoiceData(connection, compid, clientid, (period==0?false:true), from.toString(), to.toString(), ignoreStatus);
				StringBuilder sbInv = ClientInvoiceDao.getClientInvoicesJSON(invoices);
				StringBuilder sbInvData = ClientInvoiceDataDao.getClientInvoiceDatasJSON(invoiceData);
				sb = new StringBuilder("{\"clientinvoices\" : " + sbInv + ",\"clientinvoicedata\" : " + sbInvData+"}");

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: loadClientInvoices -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getPolicyLink(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String policyNo = JSONHelper.getValue(jsonBody, "policyno");
				if(policyNo.length()<=0) {
					errMsg = "Invalid Policy Number!";
					break;
				}
				String prodlinkurl = ParamDao.getParam("clientprodlinkurl", connection);
				String policyprodref = Util.prefixChar(""+compid, '0', 3)+Util.prefixChar(policyNo, '0', 8);

				sb = JSONHelper.createJsonMessage("policylink", prodlinkurl+policyprodref);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getPolicyLink -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getActiveClientPayRefs(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()==0) {
					errMsg = "Client does not exist!";
					break;
				}

				ArrayList<ClientPaymentRef> payRefs = ClientPaymentRefDao.loadActiveClientPaymentRefs(connection, compid, clientid);
				sb = ClientPaymentRefDao.getPaymentRefsJson(payRefs);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getActiveClientPayRefs -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCompanyDetails(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				CompanyDetails compdetails = CompanyDetailsDao.loadCompanyDetails(connection, compid);
				sb = new StringBuilder(compdetails.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyDetails -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder createCompanyClient(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()==0) {
					errMsg = "Client does not exist!";
					break;
				}
				if(CompanyClientDao.createCompanyClient(connection, compid, client)==false) {
					errMsg = "Company Client could not be created!";
					break;
				}
				sb = JSONHelper.getSuccessJson("Company Client Registered!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyDetails -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder createCompClientSupportGroup(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()==0) {
					errMsg = "Client does not exist!";
					break;
				}
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				String groupdesc = JSONHelper.getValue(jsonBody, "groupdesc");
				if(groupdesc.length()<=0) {
					errMsg = "Invalid Group Description!";
					break;
				}

				CompanyClientSupportGroup supportGroup = new CompanyClientSupportGroup();
				supportGroup.setSupportgroupcompid(compid);
				supportGroup.setSupportgroupclientid(clientid);
				supportGroup.setSupportgroupuserid(userid);
				supportGroup.setSupportgroupdesc(groupdesc);
				supportGroup = CompanyClientSupportGroupDao.createSupportGroup(connection, supportGroup);
				sb = new StringBuilder(supportGroup.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: createCompClientSupportGroup -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder updateCompClientSupportGroup(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int supportgroupid = JSONHelper.getIntValue(jsonBody, "supportgroupid");
				if(supportgroupid==0) {
					errMsg = "Invalid Support Group ID!";
					break;
				}
				CompanyClientSupportGroup supportgroup = CompanyClientSupportGroupDao.loadSupportGroup(connection, supportgroupid);
				if(supportgroup.getSupportgroupid()<=0) {
					errMsg = "Company Client Support Group Does Not Exist!";
					break;
				}
				String groupdesc = JSONHelper.getValue(jsonBody, "groupdesc");
				if(groupdesc.length()<=0) {
					errMsg = "Invalid Group Description!";
					break;
				}
				String groupstatus = JSONHelper.getValue(jsonBody, "groupstatus");
				if(groupstatus.length()<=0) {
					errMsg = "Invalid Group Status!";
					break;
				}
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				supportgroup.setSupportgroupuserid(userid);
				supportgroup.setSupportgroupdesc(groupdesc);
				supportgroup.setSupportgroupstatus(groupstatus);
				if(CompanyClientSupportGroupDao.updateSupportGroup(connection, supportgroup)==false) {
					errMsg = "Error updating Support Group!";
					break;
				}
				sb = JSONHelper.createJsonMessage("companyclientsupportgroup", supportgroup.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: updateCompClientSupportGroup -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCompClientSupportGroups(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()==0) {
					errMsg = "Client does not exist!";
					break;
				}
				ArrayList<CompanyClientSupportGroup> supportGroups = CompanyClientSupportGroupDao.loadCompanyClientSupportGroups(connection, compid, clientid);
				sb = CompanyClientSupportGroupDao.getCompClientSupportGroupsJson(supportGroups);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompClientSupportGroups -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getGroupCompCliSupport(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid==0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()==0) {
					errMsg = "Client does not exist!";
					break;
				}
				int supportgroupid = JSONHelper.getIntValue(jsonBody, "supportgroupid");
				if(supportgroupid==0) {
					errMsg = "Group ID Invalid!";
					break;
				}
				CompanyClientSupportGroup supportgroup = CompanyClientSupportGroupDao.loadSupportGroup(connection, supportgroupid);
				if(supportgroup.getSupportgroupid()==0) {
					errMsg = "Support Group Does Not Exist!";
					break;
				}
				ArrayList<CompanyClientSupport> support = CompanyClientSupportDao.getGroupCompanyClientSupport(connection, supportgroupid);
				sb = CompanyClientSupportDao.getCompanyClientsSupportJson(support);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getGroupCompCliSupport -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder createCompClientSupport(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int supportgroupid = JSONHelper.getIntValue(jsonBody, "supportgroupid");
				if(supportgroupid==0) {
					errMsg = "Invalid Support Group ID";
					break;
				}
				String supportmessage = JSONHelper.getValue(jsonBody, "supportmessage");
				if(supportmessage.length()<=0) {
					errMsg = "Invalid Support Message!";
					break;
				}
				int supporttype = JSONHelper.getIntValue(jsonBody, "supporttype");
				if(supporttype==0) {
					errMsg = "Invalid Support Type!";
					break;
				}
				CompanyClientSupport clientSupport = new CompanyClientSupport();
				clientSupport.setSupportgroupid(supportgroupid);
				clientSupport.setSupportmessage(supportmessage);
				clientSupport.setSupporttype(supporttype);
				clientSupport = CompanyClientSupportDao.createCompanyClientSupport(connection, clientSupport);
				sb = new StringBuilder(clientSupport.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getRecentCompClientClientSupport -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder updateCompClientSupport(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int supportid = JSONHelper.getIntValue(jsonBody, "supportid");
				if(supportid==0) {
					errMsg = "Invalid Support ID!";
					break;
				}
				CompanyClientSupport clientSupport = CompanyClientSupportDao.loadCompClientSupport(connection, supportid);
				if(clientSupport.getSupportid()<=0) {
					errMsg = "Company Client Support Does Not Exist!";
					break;
				}
				String supportmessage = JSONHelper.getValue(jsonBody, "supportmessage");
				if(supportmessage.length()<=0) {
					errMsg = "Invalid Support Message!";
					break;
				}
				String supportstatus = JSONHelper.getValue(jsonBody, "supportstatus");
				if(supportstatus.length()<=0) {
					errMsg = "Invalid Support Status!";
					break;
				}
				clientSupport.setSupportmessage(supportmessage);
				clientSupport.setSupportstatus(supportstatus);
				if(CompanyClientSupportDao.updateCompClientSupport(connection, clientSupport)==false) {
					errMsg = "Unable to update Company Client Support!";
					break;
				}
				sb = new StringBuilder(clientSupport.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: updateCompClientSupport -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder loadProdRefBlacklist(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String prodref = JSONHelper.getValue(jsonBody, "prodref");
				if(prodref.length()<=0) {
					errMsg = "Invalid Product Reference!";
					break;
				}

				ArrayList<ProdRefBlacklist> prodRefBlacklists = ProdRefBlacklistDao.loadProdRefBlacklists(connection, compid, prodref);
				sb = ProdRefBlacklistDao.getProdRefBlacklistJson(prodRefBlacklists);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: loadProdRefBlacklist -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder remakePolicy(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<=0) {
					errMsg = "Invalid Invoice Number!";
					break;
				}
				ClientInvoice invoice = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(invoice==null||invoice.getInvNo().length()<=0) {
					errMsg = "Invoice Does Not Exist!";
					break;
				}

				RecoveryRMIMethods recoveryService = (RecoveryRMIMethods)Naming.lookup("rmi://localhost:2003/recoveryrmiservice");
				if(recoveryService.remakeProdLink(compid, invno)==false) {
					errMsg = "Error remaking invoice!";
					break;
				}
				sb = JSONHelper.createJsonMessage("SUCCESS", "Invoice Recovered Successfully!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: remakePolicy -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder loadClientInvoice(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<=0) {
					errMsg = "Invalid Invoice Number!";
					break;
				}
				ClientInvoice invoice = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(invoice==null||invoice.getInvNo().length()<=0) {
					errMsg = "Invoice Does Not Exist!";
					break;
				}

				sb = new StringBuilder(invoice.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: loadClientInvoice -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getProductInvoices(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");
				if(prodid==0) {
					errMsg = "Invalid Product ID!";
					break;
				}
				CompanyProduct compProd = CompanyProductDao.getCompanyProduct(compid, prodid, connection);
				if(compProd==null||compProd.getProdId()==0) {
					errMsg = "Product Does Not Exist!";
					break;
				}
				int period = JSONHelper.getIntValue(jsonBody, "period");
				if(period==0) 
					period = Constants.PERIOD_TYPE_CURRENT;

				Date from = DateUtil.getCurrentCCYYMMDDDate();
				Date to = DateUtil.getCurrentCCYYMMDDDate();

				if(period==Constants.PERIOD_TYPE_CURRENT)
					from = DateUtil.getPrevDate(from,30);
				if(period==Constants.PERIOD_TYPE_60_DAY)
					from = DateUtil.getPrevDate(from,60);
				if(period==Constants.PERIOD_TYPE_90_DAY)
					from = DateUtil.getPrevDate(from,90);
				if(period==Constants.PERIOD_TYPE_120_DAY)
					from = DateUtil.getPrevDate(from,120);
				if(period==Constants.PERIOD_TYPE_FUTURE)
					to = DateUtil.getNextDate(from,30);

				ArrayList<ClientInvoice> invoices = ClientInvoiceDao.getInvoicesByProduct(compid, prodid, from.toString().replace('-', '/'), to.toString().replace('-', '/'), connection);
				sb = ClientInvoiceDao.getClientInvoicesJSON(invoices);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getProductInvoices -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder deactivateInvoice(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				if(compid==0) {
					errMsg = "Invalid User ID!";
					break;
				}
				CompanyUser companyUser = CompanyUserDao.loadCompanyUser(compid, userid, connection);
				if(companyUser==null||companyUser.getUserId()==0) {
					errMsg = "Company User Does Not Exist!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<=0) {
					errMsg = "Invalid Invoice Number!";
					break;
				}
				ClientInvoice invoice = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(invoice==null||invoice.getInvNo().length()<=0) {
					errMsg = "Invoice Number Does Not Exist!";
					break;
				}
				String cancelReason = JSONHelper.getValue(jsonBody, "cancelreason");
				if(cancelReason.length()<=0) {
					errMsg = "Please Provide a Reason!";
					break;
				}
				if(ClientInvoiceDao.cancelInvoice(connection, compid, invno, userid, cancelReason)==false) {
					errMsg = "Unable To Cancel Invoice!";
					break;
				}
				sb = JSONHelper.createJsonMessage("SUCCESS", "Invoice Cancelled Successfully!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getProductInvoices -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchPolicys(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");
				int filterid = JSONHelper.getIntValue(jsonBody, "filterid");
				String filterword = JSONHelper.getValue(jsonBody, "filterword");
				String fromDate = JSONHelper.getValue(jsonBody, "fromdate");
				String toDate = JSONHelper.getValue(jsonBody, "todate");
				String dateType = JSONHelper.getValue(jsonBody,"dateoption");
				int internalCompId = JSONHelper.getIntValue(jsonBody, "internalcompid");
				String payChannel = JSONHelper.getValue(jsonBody, "paychannel");
				int getInactive = JSONHelper.getIntValue(jsonBody, "smsinactive");

				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				if(prodid==0) {
					errMsg = "Invalid Product ID!";
					break;
				}
				if(filterid>0){
					if(filterword.length()<=0) {
						errMsg = "Invalid Filter!";
						break;
					}
				}
				if(fromDate.length()<10) {
					errMsg = "Invalid From Date!";
					break;
				}
				if(fromDate.length()<10) {
					errMsg = "Invalid To Date!";
					break;
				}	
				if(DateUtil.dateBefore(fromDate, toDate)) {
					errMsg = "Invalid Date Logic!";
					break;
				}

				boolean usePayDate = false;
				if(dateType.equals(ClientInvoice.INVOICE_DATE_TYPE_PAYDATE))
					usePayDate = true;
				if(filterid==ClientInvoice.INVOICE_FILTER_TYPE_CELL_NO) {
					if(filterword.length()<6) {
						errMsg = "Cellphone Number Search Too Broad!";
						break;
					}
				}

			
				boolean smsInactive = false;
				if(getInactive==1)
					smsInactive = true;

				sb = ClientInvoiceDao.searchPolicysJSON(connection, compid, prodid, fromDate, toDate, filterid, filterword, usePayDate, internalCompId, payChannel, smsInactive);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchPolicys -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder updatePolicy(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				if(userid==0) {
					errMsg = "Invalid User ID!";
					break;
				}
				CompanyUser user = CompanyUserDao.loadCompanyUser(compid, userid, connection);
				if(user==null||user.getUserId()<=0) {
					errMsg = "User Does Not Exist!";
					break;
				}
				String updatereason = JSONHelper.getValue(jsonBody, "updatereason");
				if(updatereason.length()<=0) {
					errMsg = "Invalid Update Reason";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<=0) {
					errMsg = "Invalid Invoice Number!";
					break;
				}
				ClientInvoice invoice = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(invoice==null||invoice.getInvNo().length()<=0) {
					errMsg = "Invoice Does Not Exist!";
					break;
				}

				Client client = ClientDao.loadClient(connection, invoice.getClientId());

				ClientInvoiceData invoicedata = ClientInvoiceDataDao.loadClientInvoiceData(connection, compid, invno);
				if(invoicedata==null||invoicedata.getInvNo().length()<=0) {
					errMsg = "Invoice Data Does Not Exist!";
					break;
				}
				String clientcell = JSONHelper.getValue(jsonBody, "clientcell");
				if(clientcell.length()<9) {
					errMsg = "Invalid Cell Number!";
					break;
				}
				String clientname = JSONHelper.getValue(jsonBody, "clientname");
				if(clientname.length()<=0) {
					errMsg = "Invalid Client Name!";
					break;
				}
				String clientsurname = JSONHelper.getValue(jsonBody, "clientsurname");
				if(clientsurname.length()<=0) {
					errMsg = "Invalid Client Surname!";
					break;
				}
				String vehiclereg = JSONHelper.getValue(jsonBody, "vehiclereg");
				if(vehiclereg.length()<=0) {
					errMsg = "Invalid License Plate!";
					break;
				}
				String vehiclemake = JSONHelper.getValue(jsonBody, "vehiclemake");
				if(vehiclemake.length()<=0) {
					errMsg = "Invalid Vehicle Make!";
					break;
				}
				String engineno = JSONHelper.getValue(jsonBody, "engineno");
				if(engineno.length()<=0) {
					errMsg = "Invalid Engine Number!";
					break;
				}
				String startdate = JSONHelper.getValue(jsonBody, "startdate");
				if(startdate.length()<=0) 
					startdate = invoicedata.getInvDataRef8();

				String expirydate = JSONHelper.getValue(jsonBody, "expirydate");
				if(expirydate.length()<=0) 
					expirydate = String.valueOf(invoice.getProdDate());

				boolean resendLink = JSONHelper.getBooleanValue(jsonBody, "resendlink");

				invoice.setProdDate(DateUtil.getDateValue(expirydate));


				CompanyUserEvent userEvent = new CompanyUserEvent();
				userEvent.setCompId(company.getCompId());
				userEvent.setUserId(user.getUserId());
				userEvent.setEventType(CompanyUserEvent.EVENT_TYPE_UPDATE);
				userEvent.setEventRef1(invoice.getInvNo());
				userEvent.setEventRef2(updatereason);
				if(CompanyUserEventDao.createUserEvent(connection, userEvent)==false) {
					errMsg = "Error Creating User Event!";
					break;
				}

				if(ClientInvoiceDao.updateInvoice(connection, invoice)==false) {
					errMsg = "Server Error, Couldn't Update Client Invoices!";
					break;
				}

				String invdata = invoicedata.getInvData();
				invdata = Util.replaceValueAt(invdata, "|", 0, clientname);
				invdata = Util.replaceValueAt(invdata, "|", 1, clientcell);
				invdata = Util.replaceValueAt(invdata, "|", 2, clientsurname);
				invdata = Util.replaceValueAt(invdata, "|", 3, vehiclereg);
				invdata = Util.replaceValueAt(invdata, "|", 4, vehiclemake);
				invdata = Util.replaceValueAt(invdata, "|", 5, engineno);

				invoicedata.setInvData(invdata);
				invoicedata.setInvDataRef1(clientname);
				invoicedata.setInvDataRef2(clientsurname);
				invoicedata.setInvDataRef3(vehiclereg);
				invoicedata.setInvDataRef4(vehiclemake);
				invoicedata.setInvDataRef5(engineno);
				invoicedata.setInvDataRef8(startdate.replace('-', '/'));
				if(ClientInvoiceDataDao.updateInvoiceData(connection, invoicedata)==false) {
					errMsg = "Server Error, Couldn't Update Client Invoice Data!";
					break;
				}
				RecoveryRMIMethods recoveryService = (RecoveryRMIMethods)Naming.lookup("rmi://localhost:2003/recoveryrmiservice");
				if(recoveryService.remakeProdLink(compid, invno)==false) {
					errMsg = "Error Remaking Certificate!";
					break;
				}

				if(resendLink) {
					ClientInvoiceItem item = ClientInvoiceItemDao.loadInvoiceItem(connection, invoice.getInvNo(), company.getCompId(), invoice.getProdId());
					SmsQueue smsQueue = new SmsQueue();
					StringBuffer smsMessage = new StringBuffer();
					//using tmpProdCode to decide if Hollsure or hollard
					smsMessage.append("Hollard Seguros - recebemos seu pagamento.");
					smsMessage.append("Apolice " + Util.prefixChar(invoice.getInvNo(),'0',8) + ". ");
					smsMessage.append("Inicio em " + invoicedata.getInvDataRef8() + ", ");//clientInvoiceData.invdataref8 + ", "); //DateUtil.getNextDate(clientInvoices.paydate) + ", ");
					smsMessage.append("Fim em " + invoice.getProdDate().toString().replace('-', '/')+ ". ");
					//					smsMessage.append("Recibo no.: " + receiptNo + ". ");
					smsMessage.append("Baixe selo em " + "https://"+item.getProdLink());

					smsQueue.setSmsCellNo(client.getClientcellno());
					smsQueue.setSmsDate(DateUtil.getCurrentCCYYMMDDStr());
					smsQueue.setSmsTime(DateUtil.getCurrentHHMMSSStr());
					smsQueue.setSmsMessage(smsMessage.toString());
					smsQueue.setSmsPayRef(""+company.getCompId());
					smsQueue.setSmsSendRef(company.getCompSmsSendRef());

					if(SmsQueueDao.createSms(connection, smsQueue)==false)
						System.out.println("Error resending SMS!");

				}


				sb = JSONHelper.getSuccessJson("Policy Updated Successfully!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: updatePolicy -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder cancelPolicy(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<=0) {
					errMsg = "Invalid Invoice Number!";
					break;
				}
				ClientInvoice invoice = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(invoice==null||invoice.getInvNo().length()<=0) {
					errMsg = "Invoice Does Not Exist!";
					break;
				}
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				if(userid==0) {
					errMsg = "Invalid User ID!";
					break;
				}
				CompanyUser user = CompanyUserDao.loadCompanyUser(compid, userid, connection);
				if(user==null||user.getUserId()<=0) {
					errMsg = "User Does Not Exist!";
					break;
				}
				String cancelreason = JSONHelper.getValue(jsonBody, "cancelreason");
				if(cancelreason.length()<=0) {
					errMsg = "Invalid Cancel Reason!";
					break;
				}
				if(ClientInvoiceDao.cancelInvoice(connection, compid, invno, userid, cancelreason)==false) {
					errMsg = "Unable To Cancel Invoice!";
					break;
				}

				CompanyUserEvent userEvent = new CompanyUserEvent();
				userEvent.setCompId(company.getCompId());
				userEvent.setUserId(user.getUserId());
				userEvent.setEventType(CompanyUserEvent.EVENT_TYPE_CANCELPOLICY);
				userEvent.setEventRef1(invoice.getInvNo());
				userEvent.setEventRef2(cancelreason);
				CompanyUserEventDao.createUserEvent(connection, userEvent);

				String prodlinkref = Util.prefixChar(""+compid, '0', 3)+invno;
				String prodlinkdir = ParamDao.getParam("clientprodlinkdir", connection);
				File prodlink = new File(prodlinkdir+File.separatorChar+prodlinkref);
				File prodlink_cancelled = new File(prodlinkdir+File.separatorChar+prodlinkref+"_cancelled");
				if(prodlink!=null&&prodlink.exists()) {
					if(prodlink.renameTo(prodlink_cancelled))
						FileUtil.writeLog(prodlinkref + " cancelled!", Process_webapp.class.getName(), logdir);
				}

				sb = JSONHelper.getSuccessJson("Invoice Cancelled Successfully");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: cancelPolicy -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder movePolicy(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<=0) {
					errMsg = "Invalid Invoice Number!";
					break;
				}
				ClientInvoice invoice = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(invoice==null||invoice.getInvNo().length()<=0) {
					errMsg = "Invoice Does Not Exist!";
					break;
				}
				ClientInvoiceData invoicedata = ClientInvoiceDataDao.loadClientInvoiceData(connection, compid, invno);
				if(invoicedata==null||invoicedata.getInvNo().length()<=0) {
					errMsg = "Invoice Data Does Not Exist!";
					break;
				}
				ClientInvoiceItem invoiceitem = ClientInvoiceItemDao.loadInvoiceItem(connection, invoice.getInvNo(), company.getCompId(), invoice.getProdId());
				if(invoiceitem==null||invoiceitem.getInvNo().length()<=0) {
					errMsg = "Invoice Item Does Not Exist!";
					break;
				}
				int newClientId = JSONHelper.getIntValue(jsonBody, "newclientid");
				if(newClientId==0) {
					errMsg = "Invalid New Client ID!";
					break;
				}
				Client newClient = ClientDao.loadClient(connection, newClientId);
				if(newClient==null||newClient.getClientid()==0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				Client oldClient = ClientDao.loadClient(connection, invoice.getClientId());
				ClientProduct oldClientProd = ClientProductDao.loadClientProd(connection, oldClient.getClientid(), compid, invoice.getProdId(), invoice.getPayRef());

				ClientProduct newClientProd = new ClientProduct();
				newClientProd.setClientId(newClient.getClientid());
				newClientProd.setCompId(compid);
				newClientProd.setProdId(invoice.getProdId());
				newClientProd.setProdDisc(oldClientProd.getProdDisc());
				newClientProd.setProdRsp(oldClientProd.getProdRsp());
				newClientProd.setProdCycle(oldClientProd.getProdCycle());
				newClientProd.setProdRef(invoice.getPayRef());
				newClientProd.setProdData(invoicedata.getInvData());
				newClientProd = ClientProductDao.createClientProduct(connection, newClientProd);
				if(newClientProd.getClientId()<=0) {
					errMsg = "Unable To Create Client Product!";
					break;
				}

				invoice.setClientId(newClient.getClientid());
				if(ClientInvoiceDao.updateInvoice(connection, invoice)==false) {
					errMsg = "Unable To Update Client Invoice!";
					break;
				}

				invoicedata.setClientId(newClient.getClientid());
				if(ClientInvoiceDataDao.updateInvoiceData(connection, invoicedata)==false) {
					errMsg = "Unable To Update Client Invoice!";
					break;
				}

				StringBuffer smsMessage = new StringBuffer();
				smsMessage.append("Hollard Seguros-recebemos seu pagamento.");
				smsMessage.append("Apolice " + invoice.getInvNo() + ". ");
				smsMessage.append("Inicio em " + invoicedata.getInvDataRef8().trim() + ", ");
				smsMessage.append("Fim em " + invoice.getProdDate() + ". ");
				smsMessage.append("Recibo no.: " + invoice.getPayId() + ". ");
				smsMessage.append("Baixe selo em " + invoiceitem.getProdLink());
				SmsQueue smsqueue = new SmsQueue();
				smsqueue.setSmsCellNo(newClient.getClientcellno());
				smsqueue.setSmsDate(DateUtil.getCurrentCCYYMMDDStr());
				smsqueue.setSmsTime(DateUtil.getCurrentHHMMSSStr());
				smsqueue.setSmsMessage(smsMessage.toString());
				smsqueue.setSmsStatus("1");
				smsqueue.setSmsStatusDate(DateUtil.getCurrentCCYYMMDDStr());
				smsqueue.setSmsStatusTime(DateUtil.getCurrentHHMMSSStr());
				smsqueue.setSmsSendRef("11");
				smsqueue.setSmsPayRef(""+company.getCompId());
				smsqueue.setSmsActive(SmsQueue.SMS_ACTIVE);
				SmsQueueDao.createSms(connection, smsqueue);

				sb = JSONHelper.getSuccessJson("Policy Successfully Moved!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: movePolicy -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder createClient(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				String name = JSONHelper.getValue(jsonBody, "custname");
				if(name.length()<=0) {
					errMsg = "Invalid Customer Name!";
					break;
				}
				String surname= JSONHelper.getValue(jsonBody, "custsurname");
				if(surname.length()<=0) {
					errMsg = "Invalid Customer Surname!";
					break;
				}
				String cell = JSONHelper.getValue(jsonBody, "custcell");
				if(cell.length()<=0) {
					errMsg = "Invalid Customer Cell!";
					break;
				}
				String custref = JSONHelper.getValue(jsonBody, "custref");
				if(custref.length()<=0) {
					errMsg = "Invalid Customer Reference!";
					break;
				}
				Client client = ClientDao.loadClientByCellNo(connection, cell);
				//create new client
				if(client==null||client.getClientid()==0) {
					client.setClientname(name);
					client.setClientsurname(surname);
					client.setClientcellno(cell);
					client.setClientref(custref);
					client = ClientDao.createClient(connection, client);
					if(client==null||client.getClientid()==0) {
						errMsg = "Error Creating Customer!";
						break;
					}
				}
				CompanyClient compClient = CompanyClientDao.loadCompanyClient(connection, compid, client.getClientid());
				//create new companyclient
				if(compClient==null||compClient.getClientId()==0) {
					compClient.setCompId(compid);
					compClient.setCreateDate(DateUtil.getCurrentCCYYMMDDStr());
					compClient.setCreateTime(DateUtil.getCurrentHHMMSSStr());
					compClient.setStatus(Client.ACTIVE);
					compClient.setStatusDate(DateUtil.getCurrentCCYYMMDDStr());
					compClient.setStatusTime(DateUtil.getCurrentHHMMSSStr());
					compClient.setClientExtRef(name+"_"+surname+"_"+client.getClientid());
					if(CompanyClientDao.createCompanyClient(connection, compid, client)==false) {
						errMsg = "Error Creating Company Client!";
						break;
					}
				}

				sb = new StringBuilder(client.toJsonString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: movePolicy -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder resendPolicyLink(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				if(userid==0) {
					errMsg = "Invalid User ID!";
					break;
				}
				CompanyUser user = CompanyUserDao.loadCompanyUser(compid, userid, connection);
				if(user==null||user.getUserId()<=0) {
					errMsg = "User Does Not Exist!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<=0) {
					errMsg = "Invalid Policy Number!";
					break;
				}
				ClientInvoice invoice = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(invoice==null||invoice.getInvNo().length()<=0) {
					errMsg = "Invoice Does Not Exist!";
					break;
				}
				ClientInvoiceData invoicedata = ClientInvoiceDataDao.loadClientInvoiceData(connection, compid, invno);
				if(invoicedata==null||invoicedata.getInvNo().length()<=0) {
					errMsg = "Invoice Data Does Not Exist!";
					break;
				}
				ClientInvoiceItem invoiceitem = ClientInvoiceItemDao.loadInvoiceItem(connection, invoice.getInvNo(), company.getCompId(), invoice.getProdId());
				if(invoiceitem==null||invoiceitem.getInvNo().length()<=0) {
					errMsg = "Invoice Item Does Not Exist!";
					break;
				}
				String resendReason = JSONHelper.getValue(jsonBody, "resendreason");
				if(resendReason.length()<=0) {
					errMsg = "Invalid Resend Reason";
					break;
				}
				Client client = ClientDao.loadClient(connection, invoice.getClientId());
				if(client==null||client.getClientid()<=0) {
					errMsg = "Error Loading Client!";
					break;
				}


				CompanyUserEvent userEvent = new CompanyUserEvent();
				userEvent.setCompId(company.getCompId());
				userEvent.setUserId(user.getUserId());
				userEvent.setEventType(CompanyUserEvent.EVENT_TYPE_RESENDLINK);
				userEvent.setEventRef1(invoice.getInvNo());
				userEvent.setEventRef2(resendReason);
				if(CompanyUserEventDao.createUserEvent(connection, userEvent)==false) {
					errMsg = "Error Creating User Event!";
					break;
				}

				StringBuffer smsMessage = new StringBuffer();
				smsMessage.append("Hollard Seguros-recebemos seu pagamento.");
				smsMessage.append("Apolice " + invoice.getInvNo() + ". ");
				smsMessage.append("Inicio em " + invoicedata.getInvDataRef8().trim() + ", ");
				smsMessage.append("Fim em " + invoice.getProdDate() + ". ");
				smsMessage.append("Recibo no.: " + invoice.getPayId() + ". ");
				smsMessage.append("Baixe selo em " + invoiceitem.getProdLink());
				SmsQueue smsqueue = new SmsQueue();
				smsqueue.setSmsCellNo(client.getClientcellno());
				smsqueue.setSmsDate(DateUtil.getCurrentCCYYMMDDStr());
				smsqueue.setSmsTime(DateUtil.getCurrentHHMMSSStr());
				smsqueue.setSmsMessage(smsMessage.toString());
				smsqueue.setSmsStatus("1");
				smsqueue.setSmsStatusDate(DateUtil.getCurrentCCYYMMDDStr());
				smsqueue.setSmsStatusTime(DateUtil.getCurrentHHMMSSStr());
				smsqueue.setSmsSendRef("11");
				smsqueue.setSmsPayRef(""+company.getCompId());
				smsqueue.setSmsActive(SmsQueue.SMS_ACTIVE);
				if(SmsQueueDao.createSms(connection, smsqueue)==false) {
					errMsg = "Error Sending SMS!";
					break;
				}
				sb = JSONHelper.getSuccessJson("Policy Successfully Resent!");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Server Error Process: resendPolicyLink -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getClientInvoiceSalesData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}

				String today = DateUtil.getCurrentCCYYMMDDDate().toString().replace('-', '/');
				String startMonth = DateUtil.getStartOfMonth(DateUtil.getDateValue(today)).toString().replace('-', '/');
				String prevMonthToday = DateUtil.getPrevMonthCurrDate(DateUtil.getDateValue(today)).toString().replace('-', '/');
				String startPrevMonth = DateUtil.getStartPrevMonth(DateUtil.getDateValue(today)).toString().replace('-', '/');
				String endPrevMonth = DateUtil.getLastDateOfMonth(DateUtil.getDateValue(startPrevMonth)).toString().replace('-', '/');

				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");

				int currMTDQuant = ClientInvoiceDao.getClientInvoiceQuantity(connection, compid, startMonth, today, compinternalid);
				int prevMTDQuant = ClientInvoiceDao.getClientInvoiceQuantity(connection, compid, startPrevMonth, prevMonthToday, compinternalid);

				double currMTDAmt = ClientInvoiceDao.getClientInvoiceTotal(connection, compid, startMonth, today, compinternalid);
				double prevMTDAmt = ClientInvoiceDao.getClientInvoiceTotal(connection, compid, startPrevMonth, prevMonthToday, compinternalid);

				int prevMonthQuant = ClientInvoiceDao.getClientInvoiceQuantity(connection, compid, startPrevMonth, endPrevMonth, compinternalid);
				double prevMonthAmt = ClientInvoiceDao.getClientInvoiceTotal(connection, compid, startPrevMonth, endPrevMonth, compinternalid);

				double currMonthPerc = Math.round((currMTDAmt/prevMonthAmt)*100);
				double prevMonthPerc = Math.round((prevMTDAmt/prevMonthAmt)*100);

				sb = new StringBuilder("{\"currmtdquant\" : " + currMTDQuant + ", \"currmtdamt\" : " + currMTDAmt + ", \"prevmtdquant\" : " + prevMTDQuant + ", \"prevmtdamt\" : " + prevMTDAmt + ", \"prevmonthtotquant\" : " + prevMonthQuant + ", \"prevmonthtotamt\" : " + prevMonthAmt + ", \"currmtdperc\" : " + currMonthPerc + ", \"prevmtdperc\" : " + prevMonthPerc + "}");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientInvoiceSalesData -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCurrentSaleChannelPolicyData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");

				Date today = DateUtil.getCurrentCCYYMMDDDate();
				Date monthStart = DateUtil.getStartOfMonth(today);
				Date yearStart = DateUtil.getStartOfYear();
				ArrayList<String> dayData = ClientInvoiceDao.getDailySaleChannelSaleData(connection, compid, monthStart.toString(), today.toString(),compinternalid);
				ArrayList<String> monthData = ClientInvoiceDao.getMonthlySaleChannelSaleData(connection, compid, yearStart.toString(), today.toString(),compinternalid);

				StringBuilder sbData = Util.createCurrentPolicyData(dayData, monthData, "channel");

				sb = sbData;
			}


		}catch (Exception e) {
			System.out.println("Server Error Process: getClientInvoiceSalesData -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getPreviousSaleChannelPolicyData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int period = JSONHelper.getIntValue(jsonBody, "periodid");
				if(period==0)
					period = Constants.SALES_GRAPH_PERIOD_YEAR;
				String year = JSONHelper.getValue(jsonBody, "year");
				if(year.length()<=0)
					year = ""+DateUtil.getYear();
				String month = JSONHelper.getValue(jsonBody, "month");
				if(month.length()<=0)
					month = ""+DateUtil.getPrevMonth();

				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");

				Date monthStart = DateUtil.getDateValue(year+"-"+Util.prefixChar(month, '0', 2)+"-01");
				Date yearStart = DateUtil.getDateValue(year+"-01-01");

				Date monthEnd = DateUtil.getEndMonth(monthStart);
				Date yearEnd = DateUtil.getEndYear(yearStart);

				ArrayList<String> data = new ArrayList<String>();
				if(period==Constants.SALES_GRAPH_PERIOD_YEAR)
					data = ClientInvoiceDao.getMonthlySaleChannelSaleData(connection, compid, yearStart.toString(), yearEnd.toString(),compinternalid);
				else if(period==Constants.SALES_GRAPH_PERIOD_MONTH)
					data = ClientInvoiceDao.getDailySaleChannelSaleData(connection, compid, monthStart.toString(), monthEnd.toString(),compinternalid);

				ArrayList<String> uniqueChannels = Util.getUniqueData(data);

				StringBuilder sbData = new StringBuilder();
				sbData.append("{");
				if(period==Constants.SALES_GRAPH_PERIOD_YEAR)
					sbData.append(Util.createMonthlyPolicyGraphData(uniqueChannels, data, "data", "channel"));
				else if(period==Constants.SALES_GRAPH_PERIOD_MONTH) {
					int monthLength = DateUtil.getTotDaysInMonth(monthStart);
					sbData.append(Util.createDailyPolicyGraphData(uniqueChannels, monthLength, data,"data", "channel"));
				}
				sbData.append("}");
				sb = sbData;
			}


		}catch (Exception e) {
			System.out.println("Server Error Process: getPreviousSaleChannelPolicyData -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCompanyLoginConfig(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}

				String currencySuffix = CompanyParamDao.getCompParamValue(connection,compid,"currencysuffix");
				String currencyPrefix = CompanyParamDao.getCompParamValue(connection,compid,"currencyprefix");

				sb.append("{");
				sb.append("\"currencyprefix\" : \""+currencyPrefix+"\",");
				sb.append("\"currencysuffix\" : \""+currencySuffix+"\"");
				sb.append("}");
			}


		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyLoginConfig -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCurrentProductSalesPolicyData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");


				Date today = DateUtil.getCurrentCCYYMMDDDate();
				Date monthStart = DateUtil.getStartOfMonth(today);
				Date yearStart = DateUtil.getStartOfYear();
				ArrayList<String> dayData = ClientInvoiceDao.getDailyProductSaleData(connection, compid, monthStart.toString(), today.toString(), compinternalid);
				ArrayList<String> monthData = ClientInvoiceDao.getMonthlyProductSaleData(connection, compid, yearStart.toString(), today.toString(), compinternalid);

				StringBuilder sbData = Util.createCurrentPolicyData(dayData, monthData, "product");

				sb = sbData;
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCurrentProductSalesPolicyData -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getPreviousSalesProductPolicyData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");


				int period = JSONHelper.getIntValue(jsonBody, "periodid");
				if(period==0)
					period = Constants.SALES_GRAPH_PERIOD_YEAR;
				String year = JSONHelper.getValue(jsonBody, "year");
				if(year.length()<=0)
					year = ""+DateUtil.getYear();
				String month = JSONHelper.getValue(jsonBody, "month");
				if(month.length()<=0)
					month = ""+DateUtil.getPrevMonth();

				Date monthStart = DateUtil.getDateValue(year+"-"+Util.prefixChar(month, '0', 2)+"-01");
				Date yearStart = DateUtil.getDateValue(year+"-01-01");

				Date monthEnd = DateUtil.getEndMonth(monthStart);
				Date yearEnd = DateUtil.getEndYear(yearStart);

				ArrayList<String> data = new ArrayList<String>();
				if(period==Constants.SALES_GRAPH_PERIOD_YEAR)
					data = ClientInvoiceDao.getMonthlyProductSaleData(connection, compid, yearStart.toString(), yearEnd.toString(),compinternalid);
				else if(period==Constants.SALES_GRAPH_PERIOD_MONTH)
					data = ClientInvoiceDao.getDailyProductSaleData(connection, compid, monthStart.toString(), monthEnd.toString(),compinternalid);

				ArrayList<String> uniqueChannels = Util.getUniqueData(data);

				StringBuilder sbData = new StringBuilder();
				sbData.append("{");
				if(period==Constants.SALES_GRAPH_PERIOD_YEAR)
					sbData.append(Util.createMonthlyPolicyGraphData(uniqueChannels, data, "data", "product"));
				else if(period==Constants.SALES_GRAPH_PERIOD_MONTH) {
					int monthLength = DateUtil.getTotDaysInMonth(monthStart);
					sbData.append(Util.createDailyPolicyGraphData(uniqueChannels, monthLength, data, "data", "product"));
				}
				sbData.append("}");
				sb = sbData;
			}


		}catch (Exception e) {
			System.out.println("Server Error Process: getPreviousSalesProductPolicyData -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getPolicyRenewData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");

				String year = JSONHelper.getValue(jsonBody, "year");
				String month = JSONHelper.getValue(jsonBody, "month");
				Date startMonth = DateUtil.getStartOfMonth();
				Date endMonth = DateUtil.getEndMonth();

				if(year.length()>0 && month.length()>0) {
					startMonth = DateUtil.getDateValue(year+"-"+Util.prefixChar(month,'0',2)+"-01");
					endMonth = DateUtil.getEndMonth(startMonth);
				}

				System.out.println("YEAR : " + year);
				System.out.println("MONTH : " + month);
				System.out.println("STARTMONMTH: "+ startMonth);
				System.out.println("ENDMONTH: " + endMonth);
				int expiring = ClientInvoiceDao.getExpiringPolicyCount(connection, compid, startMonth, endMonth, compinternalid);
				int renewed = ClientInvoiceDao.getRenewedPolicyCount(connection, compid, startMonth, endMonth, compinternalid);
				int unrenewed = expiring-renewed;

				sb = new StringBuilder("{\"renewed\" : " + renewed + ", \"unrenewed\" : " + unrenewed + "}"); 

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getPolicyRenewData -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getPolicyMonthlySalesComparison(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");

				Date today = DateUtil.getCurrentCCYYMMDDDate();
				Date startDate = DateUtil.minusMonths(today, 5);
				startDate = DateUtil.getStartOfMonth(startDate);

				ArrayList<String> data = ClientInvoiceDao.getPolicyMonthlySalesComparison(connection, compid, compinternalid, startDate.toString(), today.toString());
				ArrayList<String> uniqueMonths = Util.getUniqueData(data, 2);


				sb = Util.createDailyPolicyGraphDataPerMonth(uniqueMonths, data, "data", "month");				
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getPolicyMonthlySalesComparison -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchCancelledPolicys(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");

				String fromDate = JSONHelper.getValue(jsonBody, "fromdate");
				if(fromDate.length()<10) {
					errMsg = "Invalid From Date!";
					break;
				}
				String toDate = JSONHelper.getValue(jsonBody, "todate");
				if(fromDate.length()<10) {
					errMsg = "Invalid To Date!";
					break;
				}	
				if(DateUtil.dateBefore(fromDate, toDate)) {
					errMsg = "Invalid Date Logic!";
					break;
				}
				sb = new StringBuilder(ClientInvoiceDao.searchCancelledPolicys(connection,compid,fromDate,toDate,compinternalid).toString());

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchCancelledPolicys -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchChangedPolicys(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");

				String fromDate = JSONHelper.getValue(jsonBody, "fromdate");
				if(fromDate.length()<10) {
					errMsg = "Invalid From Date!";
					break;
				}
				String toDate = JSONHelper.getValue(jsonBody, "todate");
				if(fromDate.length()<10) {
					errMsg = "Invalid To Date!";
					break;
				}	
				if(DateUtil.dateBefore(fromDate, toDate)) {
					errMsg = "Invalid Date Logic!";
					break;
				}

				JSONArray userEvents = CompanyUserEventDao.searchUserEvents(connection, company.getCompId(), fromDate, toDate, compinternalid);

				sb = new StringBuilder(userEvents.toString());

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchChangedPolicys -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder fetchInvoiceQuerys(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<=0) {
					errMsg = "Invalid Policy Number!";
					break;
				}
				ClientInvoice invoice = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(invoice==null||invoice.getInvNo().length()<=0) {
					errMsg = "Invoice Does Not Exist!";
					break;
				}

				ArrayList<ClientInvoiceQuery> querys = ClientInvoiceQueryDao.getInvoiceQuerys(connection, company.getCompId(), invoice.getInvNo());
				sb = ClientInvoiceQueryDao.getInvoiceQuerysJSON(querys);

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: fetchInvoiceQuerys -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder createInvoiceQuery(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<=0) {
					errMsg = "Invalid Policy Number!";
					break;
				}
				ClientInvoice invoice = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(invoice==null||invoice.getInvNo().length()<=0) {
					errMsg = "Invoice Does Not Exist!";
					break;
				}
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				if(userid==0) {
					errMsg = "Invalid User ID!";
					break;
				}
				CompanyUser user = CompanyUserDao.loadCompanyUser(company.getCompId(), userid, connection);
				if(user==null||user.getUserId()==0) {
					errMsg = "User Does Not Exist!";
					break;
				}
				String invqueryno = JSONHelper.getValue(jsonBody, "queryno");
				if(invqueryno.length()<=0) {
					errMsg = "Invalid Query Number!";
					break;
				}
				String custname = JSONHelper.getValue(jsonBody, "custname");
				if(custname.length()<=0) {
					errMsg = "Invalid Customer Name!";
					break;
				}
				String custsurname = JSONHelper.getValue(jsonBody, "custsurname");
				if(custsurname.length()<=0) {
					errMsg = "Invalid Customer Surname!";
					break;
				}
				String vehiclereg = JSONHelper.getValue(jsonBody, "vehiclereg").replace("-", "").replace(" ", "").replace("_", "").trim().toUpperCase();
				if(vehiclereg.length()<=0) {
					errMsg = "Invalid Vehicle Registration!";
					break;
				}

				if(vehiclereg.equals(invoice.getPayRef().replace(" ", "").replace("-", "").replace("_", "").trim().toUpperCase())==false) {
					errMsg = "Vehicle Registration: " + vehiclereg + " Does Not Match Insured Registration!";
					break;
				}

				String querycomment = JSONHelper.getValue(jsonBody, "querycomment");
				if(querycomment.length()<=0) {
					errMsg = "Invalid Claim Comment!";
					break;
				}
				double amtclaimed = JSONHelper.getDoubleValue(jsonBody, "amountclaimed");
				if(amtclaimed<0) {
					errMsg = "Invalid Claim Amount!";
					break;
				}
				double amtpaid = JSONHelper.getDoubleValue(jsonBody, "amountpaid");
				if(amtpaid<0) {
					errMsg = "Invalid Amount Paid!";
					break;
				}
				Date dateofloss = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "dateofloss"));
				if(dateofloss.equals(DateUtil.DEFAULT_DATE)) {
					errMsg = "Invalid Date of Loss!";
					break;
				}
				String querytype = JSONHelper.getValue(jsonBody, "querytype");
				if(querytype.length()<=0)
					querytype = ClientInvoiceQuery.INV_QUERY_TYPE_CLAIM;

				ClientInvoiceQuery query = new ClientInvoiceQuery();
				query.setInvNo(invoice.getInvNo());
				query.setInvQueryNo(invqueryno);
				query.setInvQueryCompId(company.getCompId());
				query.setInvQueryUserId(user.getUserId());
				query.setInvQueryRef1(custname);
				query.setInvQueryRef2(custsurname);
				query.setInvQueryRef3(vehiclereg);
				query.setInvQueryComment(querycomment);
				query.setInvQueryAmt1(amtclaimed);
				query.setInvQueryAmt2(amtpaid);
				query.setInvQueryType(querytype);
				query.setInvQueryDate1(dateofloss);

				query = ClientInvoiceQueryDao.addClientInvoiceQuery(connection, query);
				if(query.getInvQueryId()<=0) {
					errMsg = "Unable To Create Invoice Query!";
					break;
				}
				sb = query.toJsonString();

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: createInvoiceQuery -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder updateInvoiceQuery(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int invqueryid = JSONHelper.getIntValue(jsonBody, "queryid");
				if(invqueryid<=0) {
					errMsg = "Invalid Query ID!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<=0) {
					errMsg = "Invalid Policy Number!";
					break;
				}
				ClientInvoice invoice = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(invoice==null||invoice.getInvNo().length()<=0) {
					errMsg = "Invoice Does Not Exist!";
					break;
				}
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				if(userid==0) {
					errMsg = "Invalid User ID!";
					break;
				}
				CompanyUser user = CompanyUserDao.loadCompanyUser(company.getCompId(), userid, connection);
				if(user==null||user.getUserId()==0) {
					errMsg = "User Does Not Exist!";
					break;
				}
				String invqueryno = JSONHelper.getValue(jsonBody, "queryno");
				if(invqueryno.length()<=0) {
					errMsg = "Invalid Query Number!";
					break;
				}
				String custname = JSONHelper.getValue(jsonBody, "custname");
				if(custname.length()<=0) {
					errMsg = "Invalid Customer Name!";
					break;
				}
				String custsurname = JSONHelper.getValue(jsonBody, "custsurname");
				if(custsurname.length()<=0) {
					errMsg = "Invalid Customer Surname!";
					break;
				}
				String vehiclereg = JSONHelper.getValue(jsonBody, "vehiclereg").replace("-", "").replace(" ", "").replace("_", "").trim().toUpperCase();
				if(vehiclereg.length()<=0) {
					errMsg = "Invalid Vehicle Registration!";
					break;
				}

				if(vehiclereg.equals(invoice.getPayRef().replace(" ", "").replace("-", "").replace("_", "").trim().toUpperCase())==false) {
					errMsg = "Vehicle Registration: " + vehiclereg + " Does Not Match Insured Registration!";
					break;
				}

				String querycomment = JSONHelper.getValue(jsonBody, "querycomment");
				if(querycomment.length()<=0) {
					errMsg = "Invalid Claim Comment!";
					break;
				}
				double amtclaimed = JSONHelper.getDoubleValue(jsonBody, "amountclaimed");
				if(amtclaimed<0) {
					errMsg = "Invalid Claim Amount!";
					break;
				}
				double amtpaid = JSONHelper.getDoubleValue(jsonBody, "amountpaid");
				if(amtpaid<0) {
					errMsg = "Invalid Amount Paid!";
					break;
				}
				Date dateofloss = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "dateofloss"));
				if(dateofloss.equals(DateUtil.DEFAULT_DATE)) {
					errMsg = "Invalid Date of Loss!";
					break;
				}
				String querytype = JSONHelper.getValue(jsonBody, "querytype");
				if(querytype.length()<=0) {
					errMsg = "Invalid Query Type!";
					break;
				}


				ClientInvoiceQuery query = ClientInvoiceQueryDao.loadInvoiceQuery(connection, compid, invqueryid);
				if(query.getInvQueryId()<=0) {
					errMsg = "Unable To Load ClientInvoiceQuery!";
					break;
				}

				query.setInvNo(invoice.getInvNo());
				query.setInvQueryNo(invqueryno);
				query.setInvQueryCompId(company.getCompId());
				query.setInvQueryUserId(user.getUserId());
				query.setInvQueryRef1(custname);
				query.setInvQueryRef2(custsurname);
				query.setInvQueryRef3(vehiclereg);
				query.setInvQueryComment(querycomment);
				query.setInvQueryAmt1(amtclaimed);
				query.setInvQueryAmt2(amtpaid);
				query.setInvQueryType(querytype);
				query.setInvQueryDate1(dateofloss);

				if(ClientInvoiceQueryDao.updateInvQuery(connection, query)==false) {
					errMsg = "Unable To Update Invoice Query!";
					break;
				}
				sb = query.toJsonString();

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: updateInvoiceQuery -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder deletInvoiceQuery(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int invqueryid = JSONHelper.getIntValue(jsonBody, "queryid");
				if(invqueryid<=0) {
					errMsg = "Invalid Query ID!";
					break;
				}
				ClientInvoiceQuery query = ClientInvoiceQueryDao.loadInvoiceQuery(connection, compid, invqueryid);
				if(ClientInvoiceQueryDao.deleteInvQuery(connection, query)==false) {
					errMsg = "Unable to Cancel Invoice Query!";
					break;
				}

				sb = JSONHelper.getSuccessJson("Delete Successful!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: deletInvoiceQuery -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchInvoiceQuerys(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");

				Date fromdate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "fromdate"));
				if(fromdate.equals(DateUtil.DEFAULT_DATE)) {
					errMsg = "Invalid From Date!";
					break;
				}
				Date todate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "todate"));
				if(todate.equals(DateUtil.DEFAULT_DATE)) {
					errMsg = "Invalid To Date!";
					break;
				}	
				if(DateUtil.dateBefore(fromdate.toString(), todate.toString())) {
					errMsg = "Invalid Date Logic!";
					break;
				}

				sb = ClientInvoiceQueryDao.searchCompanyPolicyClaims(connection, compid, fromdate, todate, compinternalid);

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchInvoiceQuerys -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder processRegister(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {

		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String compname = JSONHelper.getValue(jsonBody, "compname");
				if(compname.length()<=0) {
					errMsg = "Invalid Company Name!";
					break;
				}
				String contname = JSONHelper.getValue(jsonBody, "contname");
				if(compname.length()<=0) {
					errMsg = "Invalid Contact Name!";
					break;
				}
				String contsurname = JSONHelper.getValue(jsonBody, "contsurname");
				if(compname.length()<=0) {
					errMsg = "Invalid Contact Surname!";
					break;
				}
				String useremail = JSONHelper.getValue(jsonBody, "useremail");
				if(compname.length()<=0) {
					errMsg = "Invalid User Email!";
					break;
				}
				String userpass = JSONHelper.getValue(jsonBody, "userpass");
				if(compname.length()<=0) {
					errMsg = "Invalid User Password!";
					break;
				}
				if(Util.validatePasswordStrength(userpass)==false) {
					errMsg = "Password Too Weak!";
					break;
				}
				String confirmpass = JSONHelper.getValue(jsonBody, "confirmpass");
				if(compname.length()<=0) {
					errMsg = "Invalid Confirm Password!";
					break;
				}
				if(userpass.equals(confirmpass)==false) {
					errMsg = "Passwords Do Not Match!";
					break;
				}

				Company company = new Company();
				company.setCompName(compname);
				company.setCompContName(contname);
				company.setCompContSurname(contsurname);
				company.setCompContEmail(useremail);
				company.setCompEmail(useremail);
				company.setCompContName(contname);
				company.setCompEmail(useremail);
				company.setCompSysUserId(useremail);
				company.setCompSysPassword(userpass);
				company = CompanyDao.addCompany(connection, company);
				if(company==null||company.getCompId()<=0) {
					errMsg = "Unable To Create Company!";
					break;
				}

				CompanyUser compUser = new CompanyUser();
				compUser.setCompId(company.getCompId());
				compUser.setUserName(contname);
				compUser.setUserSurname(contsurname);
				compUser.setUserEmail(useremail);
				compUser.setUserPassword(userpass);
				compUser.setUserPermissions(CompanyUser.ADMIN_USER_PERMISSIONS);
				compUser = CompanyUserDao.addCompanyUser(connection, compUser);
				if(compUser==null||compUser.getUserId()<=0) {
					errMsg = "Unable To Create Company User!";
					break;
				}
				sb = CompanyDao.getJsonCompany(company);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: processRegister -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder createCompanyLoyalty(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				String loyaltydesc = JSONHelper.getValue(jsonBody, "loyaltydesc");
				if(loyaltydesc.length()<=0) {
					errMsg = "Loyalty Description Invalid!";
					break;
				}
				int loyaltytype = JSONHelper.getIntValue(jsonBody, "loyaltytype");
				if(loyaltytype<=0) {
					errMsg = "Invalid Loyalty Type!";
					break;
				}
				int loyaltycapturetype = JSONHelper.getIntValue(jsonBody, "loyaltycapturetype");
				if(loyaltycapturetype<=0) {
					errMsg = "Invalid Loyalty Capture Type!";
					break;
				}
				Date loyaltystart = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "startdate"));
				if(loyaltystart.equals(DateUtil.DEFAULT_DATE)) {
					errMsg = "Invalid Start Date!";
					break;
				}

				boolean loyaltyexpire = Util.parseBoolean(JSONHelper.getValue(jsonBody, "loyaltyexpire"));
				Date loyaltyexpiredate = null;
				if(loyaltyexpire) {
					loyaltyexpiredate = DateUtil.getDateValue(JSONHelper.getValue(jsonBody, "loyaltyexpiredate"));
					if(loyaltyexpiredate.equals(DateUtil.DEFAULT_DATE)) {
						errMsg = "Invalid Expire Date!";
						break;
					}
					if(DateUtil.dateBefore(DateUtil.getCurrentCCYYMMDDStr(), loyaltyexpiredate.toString())) {
						errMsg = "Expire Date Can't Be Before Today!";
						break;
					}
				}

				double loyaltypointperc = JSONHelper.getDoubleValue(jsonBody, "loyaltypointperc");
				int loyaltypointlifespan = JSONHelper.getIntValue(jsonBody, "loyaltypointlifespan");

				if(loyaltytype==CompanyLoyalty.LOYALTY_TYPE_SIMPLE) {
					if(loyaltypointperc==0) {
						errMsg = "Invalid Loyalty Point Percentage [0] For Simple Loyalty!";
						break;
					}
					if(loyaltypointlifespan==0) {
						errMsg = "Invalid Loyalty Point Lifespan [0] For Simple Loyalty!";
						break;
					}
				}
				CompanyLoyalty compLoyalty = new CompanyLoyalty();
				compLoyalty.setCompid(company.getCompId());
				compLoyalty.setComployaltydesc(loyaltydesc);
				compLoyalty.setComployaltytype(loyaltytype);
				compLoyalty.setComployaltycapturetype(loyaltycapturetype);
				compLoyalty.setComployaltystart(loyaltystart);
				compLoyalty.setComployaltyexpire(loyaltyexpiredate);
				compLoyalty.setComployaltypointslifespan(loyaltypointlifespan);
				compLoyalty.setComployaltypointsperc(loyaltypointperc);

				compLoyalty = CompanyLoyaltyDao.addCompanyLoyalty(connection, compLoyalty);
				if(compLoyalty.getComployaltyid()<=0) {
					errMsg = "Error Creating Loyalty Program";
					break;
				}

				if(loyaltytype==CompanyLoyalty.LOYALTY_TYPE_SIMPLE) {
					ArrayList<CompanyProduct> prods = CompanyProductDao.getCompanyProducts(company.getCompId(), connection);
					for(int p=0;p<prods.size();p++) {
						CompanyProduct prod = prods.get(p);
						CompanyLoyaltyProduct loyaltyProd = new CompanyLoyaltyProduct();
						loyaltyProd.setCompid(company.getCompId());
						loyaltyProd.setComployaltyid(compLoyalty.getComployaltyid());
						loyaltyProd.setProdid(prod.getProdId());
						loyaltyProd.setProdpointperc(loyaltypointperc);
						loyaltyProd.setProdpointlifespan(loyaltypointlifespan);
						loyaltyProd = CompanyLoyaltyProductDao.addCompanyLoyaltyProduct(connection, loyaltyProd);
						if(loyaltyProd.getCompid()<=0) 
							System.out.println("Error creating COMPANYLOYALTYPRODUCT!");

					}
				}

				if(loyaltytype==CompanyLoyalty.LOYALTY_TYPE_PROD_BASED) {
					JSONArray prods = JSONHelper.getJSONArrayValue(jsonBody, "loyaltyproducts");
					for(int p=0;p<prods.length();p++) {
						JSONObject prod = prods.getJSONObject(p);
						int prodid = prod.getInt("prodid");
						if(prodid<=0) {
							System.out.println("Invalid Prod ID! ["+prodid+"]");
							continue;
						}
						double pointperc = prod.getDouble("pointperc");
						if(pointperc<=0) {
							System.out.println("Invalid Point Percentage! ["+pointperc+"]");
							continue;
						}
						int pointlifespan = prod.getInt("pointlifespan");

						CompanyLoyaltyProduct loyaltyProd = new CompanyLoyaltyProduct();
						loyaltyProd.setCompid(company.getCompId());
						loyaltyProd.setComployaltyid(compLoyalty.getComployaltyid());
						loyaltyProd.setProdid(prod.getInt("prodid"));
						loyaltyProd.setProdpointperc(pointperc);
						loyaltyProd.setProdpointlifespan(pointlifespan);
						loyaltyProd = CompanyLoyaltyProductDao.addCompanyLoyaltyProduct(connection, loyaltyProd);
						if(loyaltyProd.getCompid()<=0) 
							System.out.println("Error creating COMPANYLOYALTYPRODUCT!");

					}
				}

				sb = JSONHelper.getSuccessJson("SUCCESS");

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: createCompanyLoyalty -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}


	public static StringBuilder searchPolicy(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody,Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				String policyno = JSONHelper.getValue(jsonBody, "policyno");
				if(policyno.length()<5) {
					errMsg = "Invalid Policy No!";
					break;
				}
				sb = ClientInvoiceDao.searchPolicyJSON(connection, compid, policyno);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchPolicy -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	//*******using camelcase JSON for old Site, change when update site
	public static StringBuilder searchClientInvoice(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<5) {
					errMsg = "Invalid Invoice Number!";
					break;
				}

				ArrayList<ClientInvoice> invoices = ClientInvoiceDao.searchInvoices(connection, compid, invno);
				sb = ClientInvoiceDao.getClientInvoicesJSONCamelCase(invoices);

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchClientInvoice -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchClientInvoiceData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				String invno = JSONHelper.getValue(jsonBody, "invno");
				if(invno.length()<5) {
					errMsg = "Invalid Invoice No!";
					break;
				}

				ArrayList<ClientInvoiceData> invData = ClientInvoiceDataDao.searchClientInvoiceData(connection, compid, invno);

				sb = ClientInvoiceDataDao.getClientInvoiceDatasJSONCamelCase(invData);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchClientInvoiceData -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	//************
	public static StringBuilder getCompanyLoyalty(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int comployaltyid = JSONHelper.getIntValue(jsonBody, "comployaltyid");
				if(comployaltyid==0) {
					errMsg = "Invalid Company Loyalty ID!";
					break;
				}
				CompanyLoyalty compLoyalty = CompanyLoyaltyDao.loadCompanyLoyalty(connection, company.getCompId(), comployaltyid);
				if(compLoyalty==null||compLoyalty.getComployaltyid()==0) {
					errMsg = "Company Loyalty ["+comployaltyid+"] Does Not Exist For " + company.getCompName();
					break;
				}

				JSONObject json = compLoyalty.toJSON();
				json.put("compname", company.getCompName());

				sb = new StringBuilder(json.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchClientInvoiceData -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getLoyaltyClientDetails(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int apptype = JSONHelper.getIntValue(jsonBody, "apptype");
				if(apptype==0) {
					errMsg = "Invalid App Type!";
					break;
				}
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				Company company = new Company();
				if(apptype!=CompanyLoyalty.LOYALTY_APP_TYPE_GENERIC) {
					if(compid==0) {
						errMsg = "Invalid Company ID!";
						break;
					}
					company = CompanyDao.getCompany(compid, connection);
					if(company==null||company.getCompId()==0) {
						errMsg = "Company Does Not Exist!";
						break;
					}
				}
				int comployaltyid = JSONHelper.getIntValue(jsonBody, "comployaltyid");
				CompanyLoyalty compLoyalty = new CompanyLoyalty();
				if(apptype==CompanyLoyalty.LOYALTY_APP_TYPE_COMPANY_LOYALTY) {
					if(comployaltyid==0) {
						errMsg = "Invalid Company Loyalty ID!";
						break;
					}
					compLoyalty = CompanyLoyaltyDao.loadCompanyLoyalty(connection, company.getCompId(), comployaltyid);
					if(compLoyalty==null||compLoyalty.getComployaltyid()==0) {
						errMsg = "Company Loyalty Does Not Exist!";
						break;
					}
				}
				//making uppercase
				String loyaltyno = JSONHelper.getValue(jsonBody, "loyaltyno").toUpperCase();
				if(loyaltyno.length()<=0) {
					errMsg = "Loyalty Number Not Provided!";
					break;
				}
				ClientLoyalty clientLoyalty = ClientLoyaltyDao.loadClientLoyaltyByLoyaltyNo(connection, loyaltyno);
				if(clientLoyalty.getClientid()<=0) {
					errMsg = "Loyalty Number Does Not Exist! ["+loyaltyno+"]";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientLoyalty.getClientid());
				if(client.getClientid()<=0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				if(client.getClientcellno().length()<=0&&client.getClientemail().length()<=0) {
					errMsg = "Client Does Not Have a Registered Contact Cell or Email Address!";
					break;
				}

				CompanyClient compClient = CompanyClientDao.loadCompanyClient(connection, company.getCompId(), client.getClientid()); 
				CompanyClientLoyalty compClientLoyalty = new CompanyClientLoyalty();
				ArrayList<CompanyClientLoyalty> compClientLoyaltys = new ArrayList<CompanyClientLoyalty>();
				if(apptype!=CompanyLoyalty.LOYALTY_APP_TYPE_GENERIC) {
					if(compClient==null||compClient.getClientId()<=0) {
						errMsg = "Client Is Not Registered With Company!";
						break;
					}
					if(apptype==CompanyLoyalty.LOYALTY_APP_TYPE_COMPANY) {
						compClientLoyaltys = CompanyClientLoyaltyDao.loadCompanyClientLoyaltys(connection, company.getCompId(), client.getClientid());
						if(compClientLoyaltys.size()<=0) {
							errMsg = "Client Is Not Registered With Any Company Loyalty!";
							break;
						}
					}else if(apptype==CompanyLoyalty.LOYALTY_APP_TYPE_COMPANY_LOYALTY) {
						compClientLoyalty = CompanyClientLoyaltyDao.loadCompanyClientLoyalty(connection, company.getCompId(), compLoyalty.getComployaltyid(), client.getClientid());
						if(compClientLoyalty==null||compClientLoyalty.getClientid()<=0) {
							errMsg = "Client Is Not Registered With Company Loyalty!";
							break;
						}
					}
				}

				JSONObject json = new JSONObject();
				json.put("clientid", client.getClientid());
				json.put("loyaltyno", clientLoyalty.getLoyaltyno());
				json.put("clientcell", client.getClientcellno());
				json.put("clientemail", client.getClientemail());
				json.put("clientname", client.getClientname());
				json.put("clientsurname", client.getClientsurname());

				sb = new StringBuilder(json.toString());

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getLoyaltyClientDetails -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getClientOtp(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				int otptype = JSONHelper.getIntValue(jsonBody, "otptype");
				boolean appWalletClient = JSONHelper.getBooleanValue(jsonBody, "appwalletclient");

				if(clientid<=0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()<=0) {
					errMsg = "Client Does Not Exist!";
					break;
				}

				Company company = new Company();
				String messageSender = "PayGuru";
				if(appWalletClient==false) {
					company = CompanyDao.getCompany(compid, connection);
					if(company==null||company.getCompId()==0) {
						errMsg = "Invalid Company!";
						break;
					}
					messageSender = company.getCompName();
				}
				if(otptype<=0) 
					otptype = ClientOtp.OTP_TYPE_SMS;

				String otp = ClientOtpDao.generateOtp(5);

				ClientOtp clientOtp = new ClientOtp();
				clientOtp.setClientid(clientid);
				clientOtp.setOtpno(otp);
				clientOtp.setOtptype(otptype);

				if(otptype==ClientOtp.OTP_TYPE_SMS) {
					SmsQueue smsqueue = new SmsQueue();
					smsqueue.setSmsCellNo(client.getClientcellno());
					smsqueue.setSmsMessage(messageSender + " OTP: " + otp);
					smsqueue.setSmsPayRef(""+company.getCompId());
					smsqueue = SmsQueueDao.createSmsQueue(connection, smsqueue);
					if(smsqueue.getSmsId()<=0) {
						errMsg = "Error creating SMS!";
						break;
					}
					clientOtp.setOtpsendref(""+smsqueue.getSmsId());
				}else if(otptype==ClientOtp.OTP_TYPE_EMAIL) {
					if(client.getClientemail()==null||client.getClientemail().length()<=0) {
						errMsg = "Client Does Not Have a Registered Email! Please Use Sms Option!";
						break;
					}
					EmailQueue emailqueue = new EmailQueue();
					emailqueue.setEmailaddress(client.getClientemail());
					emailqueue.setEmailmessage(messageSender + " OTP: " + otp);
					emailqueue.setEmailpayref(""+company.getCompId());
					emailqueue = EmailQueueDao.addEmailQueue(connection, emailqueue);
					if(emailqueue.getEmailid()<=0) {
						errMsg = "Error creating email!";
						break;
					}
					clientOtp.setOtpsendref(""+emailqueue.getEmailid());
				}

				clientOtp = ClientOtpDao.addClientOtp(connection, clientOtp);
				if(clientOtp.getOtpid()<=0) {
					errMsg = "OTP Error, please try again!";
					break;
				}

				JSONObject json = new JSONObject();
				json.put("otpid", clientOtp.getOtpid());
				json.put("otpno", clientOtp.getOtpno());

				sb = new StringBuilder(json.toString());


			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientOtp -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder submitClientOtp(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid<=0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()<=0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				int otpid = JSONHelper.getIntValue(jsonBody, "otpid");
				if(otpid<=0) {
					errMsg = "Invalid OTP ID!";
					break;
				}
				ClientOtp clientOtp = ClientOtpDao.loadClientOtp(connection, otpid);
				if(clientOtp==null||clientOtp.getOtpid()<=0) {
					errMsg = "Could Not Load OTP!";
					break;
				}
				String otpno = JSONHelper.getValue(jsonBody, "otpno").trim();
				if(otpno.length()<=0) {
					errMsg = "Please Input an OTP!";
					break;
				}
				if(otpno.equals(clientOtp.getOtpno())==false) {
					errMsg = "Incorrect OTP!";
					break;
				}

				clientOtp.setOtpstatus(ClientOtp.OTP_DONE);
				clientOtp.setOtpstatusdate(DateUtil.getCurrentCCYYMMDDDate());
				clientOtp.setOtpstatustime(DateUtil.getCurrentHHMMSSTime());
				ClientOtpDao.updateClientOtp(connection, clientOtp);

				sb = JSONHelper.getSuccessJson("SUCCESS");


			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientOtp -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder loadLoyaltyAppUser(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int apptype = JSONHelper.getIntValue(jsonBody, "apptype");
				if(apptype==0) {
					errMsg = "Invalid App Type!";
					break;
				}
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				Company company = new Company();
				if(apptype!=CompanyLoyalty.LOYALTY_APP_TYPE_GENERIC) {
					if(compid==0) {
						errMsg = "Invalid Company ID!";
						break;
					}
					company = CompanyDao.getCompany(compid, connection);
					if(company==null||company.getCompId()==0) {
						errMsg = "Company Does Not Exist!";
						break;
					}
				}
				int comployaltyid = JSONHelper.getIntValue(jsonBody, "comployaltyid");
				CompanyLoyalty compLoyalty = new CompanyLoyalty();
				if(apptype==CompanyLoyalty.LOYALTY_APP_TYPE_COMPANY_LOYALTY) {
					if(comployaltyid==0) {
						errMsg = "Invalid Company Loyalty ID!";
						break;
					}
					compLoyalty = CompanyLoyaltyDao.loadCompanyLoyalty(connection, company.getCompId(), comployaltyid);
					if(compLoyalty==null||compLoyalty.getComployaltyid()==0) {
						errMsg = "Company Loyalty Does Not Exist!";
						break;
					}
				}
				//making uppercase
				String loyaltyno = JSONHelper.getValue(jsonBody, "loyaltyno").toUpperCase();
				if(loyaltyno.length()<=0) {
					errMsg = "Loyalty Number Not Provided!";
					break;
				}
				ClientLoyalty clientLoyalty = ClientLoyaltyDao.loadClientLoyaltyByLoyaltyNo(connection, loyaltyno);
				if(clientLoyalty.getClientid()<=0) {
					errMsg = "Loyalty Number Does Not Exist! ["+loyaltyno+"]";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientLoyalty.getClientid());
				if(client.getClientid()<=0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				if(client.getClientcellno().length()<=0&&client.getClientemail().length()<=0) {
					errMsg = "Client Does Not Have a Registered Contact Cell or Email Address!";
					break;
				}

				CompanyClient compClient = CompanyClientDao.loadCompanyClient(connection, company.getCompId(), client.getClientid()); 
				CompanyClientLoyalty compClientLoyalty = new CompanyClientLoyalty();
				ArrayList<CompanyClientLoyalty> compClientLoyaltys = new ArrayList<CompanyClientLoyalty>();
				if(apptype!=CompanyLoyalty.LOYALTY_APP_TYPE_GENERIC) {
					if(compClient==null||compClient.getClientId()<=0) {
						errMsg = "Client Is Not Registered With Company!";
						break;
					}
					if(apptype==CompanyLoyalty.LOYALTY_APP_TYPE_COMPANY) {
						compClientLoyaltys = CompanyClientLoyaltyDao.loadCompanyClientLoyaltys(connection, company.getCompId(), client.getClientid());
						if(compClientLoyaltys.size()<=0) {
							errMsg = "Client Is Not Registered With Any Company Loyalty!";
							break;
						}
					}else if(apptype==CompanyLoyalty.LOYALTY_APP_TYPE_COMPANY_LOYALTY) {
						compClientLoyalty = CompanyClientLoyaltyDao.loadCompanyClientLoyalty(connection, company.getCompId(), compLoyalty.getComployaltyid(), client.getClientid());
						if(compClientLoyalty==null||compClientLoyalty.getClientid()<=0) {
							errMsg = "Client Is Not Registered With Company Loyalty!";
							break;
						}
					}
				}

				LoyaltyAppUser appUser = LoyaltyAppUserDao.loadLoyaltyAppUser(connection, company.getCompId(), compLoyalty.getComployaltyid(), clientLoyalty.getLoyaltyno());
				if(appUser.getUserid()==0) {
					sb = JSONHelper.createJsonMessage("INIT", "Registration Required!");
				}else {
					sb = new StringBuilder(appUser.toJson().toString());
				}


			}
		}catch (Exception e) {
			System.out.println("Server Error Process: loadLoyaltyAppUser -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder registerLoyaltyAppUser(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int apptype = JSONHelper.getIntValue(jsonBody, "apptype");
				if(apptype==0) {
					errMsg = "Invalid App Type!";
					break;
				}
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				Company company = new Company();
				if(apptype!=CompanyLoyalty.LOYALTY_APP_TYPE_GENERIC) {
					if(compid==0) {
						errMsg = "Invalid Company ID!";
						break;
					}
					company = CompanyDao.getCompany(compid, connection);
					if(company==null||company.getCompId()==0) {
						errMsg = "Company Does Not Exist!";
						break;
					}
				}
				int comployaltyid = JSONHelper.getIntValue(jsonBody, "comployaltyid");
				CompanyLoyalty compLoyalty = new CompanyLoyalty();
				if(apptype==CompanyLoyalty.LOYALTY_APP_TYPE_COMPANY_LOYALTY) {
					if(comployaltyid==0) {
						errMsg = "Invalid Company Loyalty ID!";
						break;
					}
					compLoyalty = CompanyLoyaltyDao.loadCompanyLoyalty(connection, company.getCompId(), comployaltyid);
					if(compLoyalty==null||compLoyalty.getComployaltyid()==0) {
						errMsg = "Company Loyalty Does Not Exist!";
						break;
					}
				}
				//making uppercase
				String loyaltyno = JSONHelper.getValue(jsonBody, "loyaltyno").toUpperCase();
				if(loyaltyno.length()<=0) {
					errMsg = "Loyalty Number Not Provided!";
					break;
				}
				ClientLoyalty clientLoyalty = ClientLoyaltyDao.loadClientLoyaltyByLoyaltyNo(connection, loyaltyno);
				if(clientLoyalty.getClientid()<=0) {
					errMsg = "Loyalty Number Does Not Exist! ["+loyaltyno+"]";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientLoyalty.getClientid());
				if(client.getClientid()<=0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				if(client.getClientcellno().length()<=0&&client.getClientemail().length()<=0) {
					errMsg = "Client Does Not Have a Registered Contact Cell or Email Address!";
					break;
				}

				CompanyClient compClient = CompanyClientDao.loadCompanyClient(connection, company.getCompId(), client.getClientid()); 
				CompanyClientLoyalty compClientLoyalty = new CompanyClientLoyalty();
				ArrayList<CompanyClientLoyalty> compClientLoyaltys = new ArrayList<CompanyClientLoyalty>();
				if(apptype!=CompanyLoyalty.LOYALTY_APP_TYPE_GENERIC) {
					if(compClient==null||compClient.getClientId()<=0) {
						errMsg = "Client Is Not Registered With Company!";
						break;
					}
					if(apptype==CompanyLoyalty.LOYALTY_APP_TYPE_COMPANY) {
						compClientLoyaltys = CompanyClientLoyaltyDao.loadCompanyClientLoyaltys(connection, company.getCompId(), client.getClientid());
						if(compClientLoyaltys.size()<=0) {
							errMsg = "Client Is Not Registered With Any Company Loyalty!";
							break;
						}
					}else if(apptype==CompanyLoyalty.LOYALTY_APP_TYPE_COMPANY_LOYALTY) {
						compClientLoyalty = CompanyClientLoyaltyDao.loadCompanyClientLoyalty(connection, company.getCompId(), compLoyalty.getComployaltyid(), client.getClientid());
						if(compClientLoyalty==null||compClientLoyalty.getClientid()<=0) {
							errMsg = "Client Is Not Registered With Company Loyalty!";
							break;
						}
					}
				}

				LoyaltyAppUser appUser = LoyaltyAppUserDao.loadLoyaltyAppUser(connection, company.getCompId(), compLoyalty.getComployaltyid(), clientLoyalty.getLoyaltyno());
				if(appUser.getUserid()>0) {
					errMsg = "App User Already Exists!";
					break;
				}

				String password = JSONHelper.getValue(jsonBody, "password");
				if(Util.validatePasswordStrength(password)==false) {
					errMsg = "Password Too Weak!";
					break;
				}
				String confpassword = JSONHelper.getValue(jsonBody, "confirmpassword");
				if(password.equals(confpassword)==false) {
					errMsg = "Password's Do Not Match!";
					break;
				}

				appUser.setClientid(client.getClientid());
				appUser.setLoyaltyno(clientLoyalty.getLoyaltyno());
				appUser.setCompid(company.getCompId());
				appUser.setComployaltyid(compLoyalty.getComployaltyid());
				appUser.setPassword(password);

				appUser = LoyaltyAppUserDao.addLoyaltyAppUser(connection, appUser);
				if(appUser.getUserid()<=0) {
					errMsg = "Unable To Create App User!";
					break;
				}

				sb = new StringBuilder(appUser.toJson().toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: registerLoyaltyAppUser -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	public static StringBuilder loginLoyaltyAppUser(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int apptype = JSONHelper.getIntValue(jsonBody, "apptype");
				if(apptype==0) {
					errMsg = "Invalid App Type!";
					break;
				}
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				Company company = new Company();
				if(apptype!=CompanyLoyalty.LOYALTY_APP_TYPE_GENERIC) {
					if(compid==0) {
						errMsg = "Invalid Company ID!";
						break;
					}
					company = CompanyDao.getCompany(compid, connection);
					if(company==null||company.getCompId()==0) {
						errMsg = "Company Does Not Exist!";
						break;
					}
				}
				int comployaltyid = JSONHelper.getIntValue(jsonBody, "comployaltyid");
				CompanyLoyalty compLoyalty = new CompanyLoyalty();
				if(apptype==CompanyLoyalty.LOYALTY_APP_TYPE_COMPANY_LOYALTY) {
					if(comployaltyid==0) {
						errMsg = "Invalid Company Loyalty ID!";
						break;
					}
					compLoyalty = CompanyLoyaltyDao.loadCompanyLoyalty(connection, company.getCompId(), comployaltyid);
					if(compLoyalty==null||compLoyalty.getComployaltyid()==0) {
						errMsg = "Company Loyalty Does Not Exist!";
						break;
					}
				}

				//making uppercase
				String loyaltyno = JSONHelper.getValue(jsonBody, "loyaltyno").toUpperCase();
				if(loyaltyno.length()<=0) {
					errMsg = "Loyalty Number Not Provided!";
					break;
				}
				ClientLoyalty clientLoyalty = ClientLoyaltyDao.loadClientLoyaltyByLoyaltyNo(connection, loyaltyno);
				if(clientLoyalty.getClientid()<=0) {
					errMsg = "Loyalty Number Does Not Exist! ["+loyaltyno+"]";
					break;
				}

				String password = JSONHelper.getValue(jsonBody, "password");
				if(Util.validatePasswordStrength(password)==false) {
					errMsg = "Password Too Weak!";
					break;
				}

				LoyaltyAppUser appUser = LoyaltyAppUserDao.loadLoyaltyAppUser(connection, company.getCompId(), compLoyalty.getComployaltyid(), clientLoyalty.getLoyaltyno());


				if(appUser.getPassword().equals(password)==false) {
					errMsg = "Login Details Are Incorrect!";
					break;
				}

				appUser.setStatus(LoyaltyAppUser.LOGGED_IN);
				appUser.setStatusdate(DateUtil.getCurrentCCYYMMDDDate());
				appUser.setStatustime(DateUtil.getCurrentHHMMSSTime());

				LoyaltyAppUserDao.updateLoyaltyAppUser(connection, appUser);

				sb = JSONHelper.getSuccessJson("SUCCESS");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: loginLoyaltyAppUser -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCompLoyaltyRewards(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int comployaltyid = JSONHelper.getIntValue(jsonBody, "comployaltyid");
				CompanyLoyalty compLoyalty = new CompanyLoyalty();
				if(comployaltyid==0) {
					errMsg = "Invalid Company Loyalty ID!";
					break;
				}
				compLoyalty = CompanyLoyaltyDao.loadCompanyLoyalty(connection, company.getCompId(), comployaltyid);
				if(compLoyalty==null||compLoyalty.getComployaltyid()==0) {
					errMsg = "Company Loyalty Does Not Exist!";
					break;
				}

				ArrayList<CompanyLoyaltyReward> rewards = CompanyLoyaltyRewardDao.getCompanyLoyaltyRewards(connection, company.getCompId(), compLoyalty.getComployaltyid());
				JSONArray jsonRewards = CompanyLoyaltyRewardDao.getLoyaltyRewardsJson(rewards);

				sb = new StringBuilder(jsonRewards.toString());

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompLoyaltyRewards -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder loadCompanyClientLoyalty(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int comployaltyid = JSONHelper.getIntValue(jsonBody, "comployaltyid");
				CompanyLoyalty compLoyalty = new CompanyLoyalty();
				if(comployaltyid==0) {
					errMsg = "Invalid Company Loyalty ID!";
					break;
				}
				compLoyalty = CompanyLoyaltyDao.loadCompanyLoyalty(connection, company.getCompId(), comployaltyid);
				if(compLoyalty==null||compLoyalty.getComployaltyid()==0) {
					errMsg = "Company Loyalty Does Not Exist!";
					break;
				}

				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid<=0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()<=0) {
					errMsg = "Client Does Not Exist!";
					break;
				}

				CompanyClientLoyalty compCliLoyalty = CompanyClientLoyaltyDao.loadCompanyClientLoyalty(connection, company.getCompId(), compLoyalty.getComployaltyid(), client.getClientid());
				if(compCliLoyalty.getClientid()<=0) {
					errMsg = "Company CLient Loyalty Does Not Exist!";
					break;
				}

				sb = new StringBuilder(compCliLoyalty.toJSON().toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: loadCompanyClientLoyalty -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder createSms(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String cellno = JSONHelper.getValue(jsonBody, "cellno");
				String message = JSONHelper.getValue(jsonBody, "message");
				String smsdate = JSONHelper.getValue(jsonBody, "smsdate");
				String smstime = JSONHelper.getValue(jsonBody, "smstime");


				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				if(cellno.length()<0) {
					errMsg = "Cellphone Number Invalid!";
					break;
				}
				if(message.length()<=0) {
					errMsg = "Message Can't Be Empty!";
					break;
				}
				message = Util.fixDbString(message);
				if(message.length()>500) {
					errMsg = "Message too Long!";
					break;
				}
				if(smsdate.length()<=0)
					smsdate = DateUtil.getCurrentCCYYMMDDStr();
				if(smstime.length()<=0)
					smstime = DateUtil.getCurrentHHMMSSStr();

				SmsQueue smsqueue = new SmsQueue();
				smsqueue.setSmsCellNo(cellno);
				smsqueue.setSmsMessage(message);
				smsqueue.setSmsDate(smsdate);
				smsqueue.setSmsTime(smstime);
				smsqueue.setSmsPayRef(""+company.getCompId());
				if(SmsQueueDao.createSms(connection, smsqueue)==false) {
					errMsg = "Error Creating SMS!";
					break;
				}
				sb = JSONHelper.createJsonMessage("SUCCESS", "SUCCESS");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: createSms -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getClientLoyaltyRewardTokens(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int comployaltyid = JSONHelper.getIntValue(jsonBody, "comployaltyid");
				CompanyLoyalty compLoyalty = new CompanyLoyalty();
				if(comployaltyid==0) {
					errMsg = "Invalid Company Loyalty ID!";
					break;
				}
				compLoyalty = CompanyLoyaltyDao.loadCompanyLoyalty(connection, company.getCompId(), comployaltyid);
				if(compLoyalty==null||compLoyalty.getComployaltyid()==0) {
					errMsg = "Company Loyalty Does Not Exist!";
					break;
				}

				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid<=0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()<=0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				CompanyClientLoyalty compCliLoyalty = CompanyClientLoyaltyDao.loadCompanyClientLoyalty(connection, company.getCompId(), compLoyalty.getComployaltyid(), client.getClientid());
				if(compCliLoyalty.getClientid()<=0) {
					errMsg = "Client Not Part of Loyalty Programme!";
					break;
				}

				ArrayList<CompanyClientLoyaltyRewardToken> clientRewardTokens = CompanyClientLoyaltyRewardTokenDao.getClientTokens(connection, company.getCompId(), compLoyalty.getComployaltyid(), client.getClientid());

				JSONArray json = CompanyClientLoyaltyRewardTokenDao.getTokensJson(clientRewardTokens);
				sb = new StringBuilder(json.toString());

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientLoyaltyRewardTokens -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getClientLoyaltyPointsEvents(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				int comployaltyid = JSONHelper.getIntValue(jsonBody, "comployaltyid");
				CompanyLoyalty compLoyalty = new CompanyLoyalty();
				if(comployaltyid==0) {
					errMsg = "Invalid Company Loyalty ID!";
					break;
				}
				compLoyalty = CompanyLoyaltyDao.loadCompanyLoyalty(connection, company.getCompId(), comployaltyid);
				if(compLoyalty==null||compLoyalty.getComployaltyid()==0) {
					errMsg = "Company Loyalty Does Not Exist!";
					break;
				}

				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				if(clientid<=0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()<=0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				CompanyClientLoyalty compCliLoyalty = CompanyClientLoyaltyDao.loadCompanyClientLoyalty(connection, company.getCompId(), compLoyalty.getComployaltyid(), client.getClientid());
				if(compCliLoyalty.getClientid()<=0) {
					errMsg = "Client Not Part of Loyalty Programme!";
					break;
				}

				ArrayList<ClientLoyaltyPointsEvents> pointEvents = ClientLoyaltyPointsEventsDao.getClientLoyaltyPointsEvents(connection, client.getClientid(), company.getCompId(), compLoyalty.getComployaltyid());
				sb = new StringBuilder(ClientLoyaltyPointsEventsDao.getLoyaltyPointsEventsJSON(pointEvents).toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientLoyaltyPointsEvents -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder createBulkSmsWithFile(HttpServletRequest req, HttpServletResponse resp, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				Part jsonPart = req.getPart("json");
				Part filePart = req.getPart("file");
				if(jsonPart==null) {
					errMsg = "Incorrect Request Format!";
					break;
				}
				JSONObject jsonBody = JSONHelper.createObjectFromPart(jsonPart);
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String smsDesc = JSONHelper.getValue(jsonBody, "smsdesc");
				String smsMessage = JSONHelper.getValue(jsonBody, "smsmessage");
				String sendDate = JSONHelper.getValue(jsonBody, "senddate");
				String sendTime = JSONHelper.getValue(jsonBody, "sendtime");
				int smsMethod = JSONHelper.getIntValue(jsonBody, "smsmethod");
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				String smsbulkdir = ParamDao.getParam("smsbulkdir", connection);
						
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				if(smsDesc.length()<=0) {
					errMsg = "Invalid SMS Description!";
					break;
				}
				if(smsMessage.length()<=0) {
					errMsg = "Invalid SMS Message!";
					break;
				}
				smsMessage = Util.fixDbString(smsMessage);
				smsMessage = Util.replaceRepetitions(smsMessage, ' ', ' ');
				smsMessage = smsMessage.replace("\n", " ");;
				if(smsMessage.length()>240) {
					errMsg = "SMS Message Exceeds Max Length!";
					break;
				}
				if(sendDate.length()<10) {
					errMsg = "Invalid Date!";
					break;
				}
				if(DateUtil.dateBefore(DateUtil.getCurrentCCYYMMDDStr(), sendDate.toString())) {
					errMsg = "Invalid Date Logic!";
					break;
				}
				if(sendTime.length()>8 || sendTime.length()<5) {
					errMsg = "Invalid Send Time!";
					break;
				}
				if(sendTime.length()==5) 
					sendTime = sendTime+":00";

				if(smsMethod==0) {
					errMsg = "Invalid SMS Method!";
					break;
				}
				CompanyUser user = CompanyUserDao.loadCompanyUser(company.getCompId(), userid, connection);
				if(user.getUserId()==0) {
					errMsg = "Invalid User";
				}
				
				//file stuff
				if(filePart==null) {
					errMsg = "No File Present!";
					break;
				}
				
				String typeref1 = compid+"_"+DateUtil.getCurrentCCYYMMDDStr(false)+DateUtil.getCurrentHHMMSSStr(false)+".READY";
				
				Path filePath = Paths.get(smsbulkdir, typeref1);
				try (
					InputStream is = filePart.getInputStream();
				){
					Files.copy(is, filePath, StandardCopyOption.REPLACE_EXISTING);
				}catch (Exception e) {
					System.out.println("Error copying smsbulk file: " + e.toString());
				}
				
//				StringBuilder sb2 = new StringBuilder();
//				InputStream is = filePart.getInputStream();
//				ArrayList<String> fileContent = new ArrayList<String>();
//				try(
//						BufferedReader br = new BufferedReader(new InputStreamReader(is,Charset.forName(StandardCharsets.UTF_8.name())));
//						){
//					int c = 0;
//					while((c= br.read()) != -1) {
//						sb2.append((char)c);
//					}
//					fileContent = Util.fileSBToArrayList(sb2);
//				}catch (Exception e) {
//					System.out.println("Server Error Process: createBulkSmsWithFile -> " + e.toString());
//					errMsg = "Server Error";
//					break;
//				}finally {
//					if(is!=null)
//						is.close();
//				}
				//
				
				SmsBulkSummary smsBulkSummary = new SmsBulkSummary();
				smsBulkSummary.setSummaryCompId(company.getCompId());
				smsBulkSummary.setSummaryDesc(smsDesc);
				smsBulkSummary.setSummaryType(""+smsMethod);
				smsBulkSummary.setSummaryTypeRef1(typeref1);
				smsBulkSummary.setSummaryCreatedDate(DateUtil.getCurrentCCYYMMDDStr());
				smsBulkSummary.setSummaryCreatedTime(DateUtil.getCurrentHHMMSSStr());
				smsBulkSummary.setSummaryCreatedUserID(userid);
				smsBulkSummary.setSummarySmsMessage(smsMessage);
				smsBulkSummary.setSummarySmsSendDate(sendDate);
				smsBulkSummary.setSummarySmsSendTime(sendTime);
				smsBulkSummary.setSummaryStatus(SmsBulkSummary.SUMMARY_STATUS_ACTIVE);
				smsBulkSummary.setSummaryStatusDate(DateUtil.getCurrentCCYYMMDDStr());
				smsBulkSummary.setSummaryStatusTime(DateUtil.getCurrentHHMMSSStr());
				
				if(SmsBulkSummaryDao.insert(connection, smsBulkSummary)==false) {
					errMsg = "Unable to update bulk sms!";
					break;
				}

				

				sb = JSONHelper.getSuccessJson("Bulk SMS Created Successfully!");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: createBulkSmsWithFile -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder createBulkSms(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String smsDesc = JSONHelper.getValue(jsonBody, "smsdesc");
				String smsMessage = JSONHelper.getValue(jsonBody, "smsmessage");
				String sendDate = JSONHelper.getValue(jsonBody, "senddate");
				String sendTime = JSONHelper.getValue(jsonBody, "sendtime");
				int smsMethod = JSONHelper.getIntValue(jsonBody, "smsmethod");
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				String typeref1 = JSONHelper.getValue(jsonBody, "typeref1");
				
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				if(smsDesc.length()<=0) {
					errMsg = "Invalid SMS Description!";
					break;
				}
				if(smsMessage.length()<=0) {
					errMsg = "Invalid SMS Message!";
					break;
				}
				smsMessage = Util.fixDbString(smsMessage);
				smsMessage = Util.replaceRepetitions(smsMessage, ' ', ' ');
				smsMessage = smsMessage.replace("\n", " ");;
				if(smsMessage.length()>240) {
					errMsg = "SMS Message Exceeds Max Length!";
					break;
				}
				if(sendDate.length()<10) {
					errMsg = "Invalid Date!";
					break;
				}
				if(DateUtil.dateBefore(DateUtil.getCurrentCCYYMMDDStr(), sendDate.toString())) {
					errMsg = "Invalid Date Logic!";
					break;
				}
				if(sendTime.length()>8 || sendTime.length()<5) {
					errMsg = "Invalid Send Time!";
					break;
				}
				if(sendTime.length()==5) 
					sendTime = sendTime+":00";

				if(smsMethod==0) {
					errMsg = "Invalid SMS Method!";
					break;
				}
				CompanyUser user = CompanyUserDao.loadCompanyUser(company.getCompId(), userid, connection);
				if(user.getUserId()==0) {
					errMsg = "Invalid User";
				}
				if((""+smsMethod).equals(SmsBulkSummary.SUMMARY_TYPE_PRODUCT)) {
					int prodid = Util.parseInt(typeref1,0);
					CompanyProduct compProd = CompanyProductDao.getCompanyProduct(company.getCompId(), prodid, connection);
					if(compProd.getCompId()==0) {
						errMsg = "Invalid Product!";
						break;
					}
				}
				SmsBulkSummary smsBulkSummary = new SmsBulkSummary();
				smsBulkSummary.setSummaryCompId(company.getCompId());
				smsBulkSummary.setSummaryDesc(smsDesc);
				smsBulkSummary.setSummaryType(""+smsMethod);
				smsBulkSummary.setSummaryTypeRef1(typeref1);
				smsBulkSummary.setSummaryCreatedDate(DateUtil.getCurrentCCYYMMDDStr());
				smsBulkSummary.setSummaryCreatedTime(DateUtil.getCurrentHHMMSSStr());
				smsBulkSummary.setSummaryCreatedUserID(userid);
				smsBulkSummary.setSummarySmsMessage(smsMessage);
				smsBulkSummary.setSummarySmsSendDate(sendDate);
				smsBulkSummary.setSummarySmsSendTime(sendTime);
				smsBulkSummary.setSummaryStatus(SmsBulkSummary.SUMMARY_STATUS_ACTIVE);
				smsBulkSummary.setSummaryStatusDate(DateUtil.getCurrentCCYYMMDDStr());
				smsBulkSummary.setSummaryStatusTime(DateUtil.getCurrentHHMMSSStr());
				
				if(SmsBulkSummaryDao.insert(connection, smsBulkSummary)==false) {
					errMsg = "Unable to update bulk sms!";
					break;
				}
				sb = JSONHelper.getSuccessJson("Bulk SMS Created Successfully!");
			}

		}catch (Exception e) {
			System.out.println("Server Error Process: createBulkSms -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder updateCompanyProfile(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String compname = JSONHelper.getValue(jsonBody, "compname");
				String compcell = JSONHelper.getValue(jsonBody, "compcell");
				String comptel = JSONHelper.getValue(jsonBody, "comptel");
				String compvatno = JSONHelper.getValue(jsonBody, "compvatno");
				String compregno = JSONHelper.getValue(jsonBody, "compregno");
				String compemail = JSONHelper.getValue(jsonBody, "compemail");
				String compwebsite = JSONHelper.getValue(jsonBody, "compwebsite");
				String compjoinname = JSONHelper.getValue(jsonBody, "compjoinname");
				int compdefprodid = JSONHelper.getIntValue(jsonBody, "compdefprodid");
				String compsmsmessage1 = JSONHelper.getValue(jsonBody, "compsmsmessage1");
				String compphysaddr1 = JSONHelper.getValue(jsonBody, "compphysaddr1");
				String compphysaddr2 = JSONHelper.getValue(jsonBody, "compphysaddr2");
				String compphysaddr3 = JSONHelper.getValue(jsonBody, "compphysaddr3");
				String compphystown = JSONHelper.getValue(jsonBody, "compphystown");
				String compphysprov = JSONHelper.getValue(jsonBody, "compphysprov");
				String compphyspostcode = JSONHelper.getValue(jsonBody, "compphyspostcode");
				String comppostaddr1 = JSONHelper.getValue(jsonBody, "comppostaddr1");
				String comppostaddr2 = JSONHelper.getValue(jsonBody, "comppostaddr2");
				String comppostaddr3 = JSONHelper.getValue(jsonBody, "comppostaddr3");
				String compposttown = JSONHelper.getValue(jsonBody, "compposttown");
				String comppostprov = JSONHelper.getValue(jsonBody, "comppostprov");
				String comppostcode = JSONHelper.getValue(jsonBody, "comppostcode");
				String compcontname = JSONHelper.getValue(jsonBody, "compcontname");
				String compcontsurname = JSONHelper.getValue(jsonBody, "compcontsurname");
				String compcontemail = JSONHelper.getValue(jsonBody, "compcontemail");
				String compcontcell = JSONHelper.getValue(jsonBody, "compcontcell");
				String compconttel = JSONHelper.getValue(jsonBody, "compconttel");
				String compsysuserid = JSONHelper.getValue(jsonBody, "compsysuserid");
				String compsyspassword = JSONHelper.getValue(jsonBody, "compsyspassword");

				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				if(compname.length()<=0) {
					errMsg = "Invalid Company Name!";
					break;
				}
				if(compcell.length()<=8) {
					errMsg = "Invalid Company Cell!";
					break;
				}
				if(compemail.length()<=0||Util.validEmail(compemail)==false) {
					errMsg = "Invalid Company Email!";
					break;
				}
				if(compphysaddr1.length()<=0) {
					errMsg = "Invalid Company Physical Address!";
					break;
				}
				if(compphystown.length()<=0) {
					errMsg = "Invalid Company Physical Town!";
					break;
				}
				if(compphysprov.length()<=0) {
					errMsg = "Invalid Company Physical Province!";
					break;
				}
				if(compphyspostcode.length()<=0) {
					errMsg = "Invalid Company Physical Post Code!";
					break;
				}
				if(compcontname.length()<=0) {
					errMsg = "Invalid Contact Name!";
					break;
				}
				if(compcontsurname.length()<=0) {
					errMsg = "Invalid Contact Surname!";
					break;
				}
				if(compcontemail.length()<=0||Util.validEmail(compcontemail)==false) {
					errMsg = "Invalid Contact Email!";
					break;
				}
				if(compcontcell.length()<=0) {
					errMsg = "Invalid Contact Cell!";
					break;
				}
				if(compsysuserid.length()<=0||Util.validEmail(compcontemail)==false) {
					errMsg = "Invalid Company System User ID!";
					break;
				}
				if(compsyspassword.length()<=0) {
					errMsg = "Invalid Company System User Password!";
					break;
				}


				company.setCompName(compname);
				company.setCompName(compname);
				company.setCompCellNo(compcell);
				company.setCompTelNo(comptel);
				company.setCompVatNo(compvatno);
				company.setCompRegNo(compregno);
				company.setCompEmail(compemail);
				company.setCompWebsite(compwebsite);
				company.setCompJoinName(compjoinname);
				company.setCompDefProdId(compdefprodid);
				company.setCompSmsMessage1(compsmsmessage1);
				company.setCompPhysAddr1(compphysaddr1);
				company.setCompPhysAddr2(compphysaddr2);
				company.setCompPhysAddr3(compphysaddr3);
				company.setCompPhysTown(compphystown);
				company.setCompPhysProv(compphysprov);
				company.setCompPhysPostCode(compphyspostcode);
				company.setCompPostAddr1(comppostaddr1);
				company.setCompPostAddr2(comppostaddr2);
				company.setCompPostAddr3(comppostaddr3);
				company.setCompPostTown(compposttown);
				company.setCompPostProv(comppostprov);
				company.setCompPostCode(comppostcode);
				company.setCompContName(compcontname);
				company.setCompContSurname(compcontsurname);
				company.setCompContEmail(compcontemail);
				company.setCompContCellNo(compcontcell);
				company.setCompContTelNo(compconttel);
				company.setCompSysUserId(compsysuserid);
				company.setCompSysPassword(compsyspassword);

				if(CompanyDao.updateCompany(connection, company)==false) {
					errMsg = "Unable To Update Company Profile!";
					break;
				}
				sb = CompanyDao.getJsonCompany(company);

			}

		}catch (Exception e) {
			System.out.println("Server Error Process: updateCompanyProfile -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder createCustomer(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String custname = JSONHelper.getValue(jsonBody, "custname").toUpperCase().trim();
				String custsurname = JSONHelper.getValue(jsonBody, "custsurname").toUpperCase().trim();
				String custcell = JSONHelper.getValue(jsonBody, "custcell").replace("+", "").trim();
				String custidno = JSONHelper.getValue(jsonBody, "custidno").trim();
				String custemail = JSONHelper.getValue(jsonBody, "custemail").trim();
				String custextref = JSONHelper.getValue(jsonBody, "custextref").trim();

				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}if(CompanyDao.updateCompany(connection, company)==false) {
					errMsg = "Unable To Update Company Profile!";
					break;
				}
				if(custname.length()<=0) {
					errMsg = "Invalid Customer Name!";
					break;
				}
				if(custsurname.length()<=0) {
					errMsg = "Invalid Customer Surname!";
					break;
				}
				if(custcell.length()<=8) {
					errMsg = "Invalid Customer Cell!";
					break;
				}
				if(custemail.length()>0&&Util.validEmail(custemail)==false) {
					errMsg = "Invalid Customer Email!";
					break;
				}
				if(custextref.length()<=0) {
					errMsg = "Invalid External Reference!";
					break;
				}

				Client client = ClientDao.loadClientByCellNo(connection, custcell);
				boolean clientExist = false;
				CompanyClient compClient = new CompanyClient();
				if(client.getClientid()>0) {
					compClient = CompanyClientDao.loadCompanyClient(connection, company.getCompId(), client.getClientid());
					if(compClient.getClientId()>0) {
						errMsg = "Error, Customer ["+client.getClientcellno()+"] already exists!";
						break;
					}
					if(custemail.length()<=0&&client.getClientemail().length()>0)
						custemail = client.getClientemail();
					clientExist = true;
				}

				client.setClientname(custname);
				client.setClientsurname(custsurname);
				client.setClientcellno(custcell);
				client.setClientemail(custemail);
				client.setClientidno(custidno);
				if(clientExist)
					client = ClientDao.updateClientBasic(connection, client);
				else
					client = ClientDao.createClient(connection, client);

				if(client.getClientid()<=0) {
					errMsg = "Unable To Create Customer!";
					break;
				}

				compClient.setCompId(company.getCompId());
				compClient.setClientId(client.getClientid());
				compClient.setCreateDate(DateUtil.getCurrentCCYYMMDDStr());
				compClient.setCreateTime(DateUtil.getCurrentHHMMSSStr());
				compClient.setStatus(CompanyClient.COMP_CLIENT_ACTIVE);
				compClient.setStatusDate(DateUtil.getCurrentCCYYMMDDStr());
				compClient.setStatusTime(DateUtil.getCurrentHHMMSSStr());
				compClient.setClientExtRef(custextref);
				if(CompanyClientDao.createCompanyClient(connection, compClient)==false) {
					errMsg = "Unable To Create Companny Customer!";
					break;
				}


				sb = JSONHelper.getSuccessJson("Customer Created Successfully!");

			}

		}catch (Exception e) {
			System.out.println("Server Error Process: createCustomer -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder uploadcustomerimport(HttpServletRequest req, HttpServletResponse resp, Connection connection, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				Part jsonPart = req.getPart("json");
				if(jsonPart==null) {
					errMsg = "Incorrect Request Format!";
					break;
				}
				StringBuilder jsonStringBuilder = new StringBuilder();
				InputStream jsonStream = jsonPart.getInputStream();
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(jsonStream))) {
					String line;
					while ((line = reader.readLine()) != null) {
						jsonStringBuilder.append(line);
					}
				}if(jsonStream!=null)
					jsonStream.close();

				JSONObject jsonData = new JSONObject(jsonStringBuilder.toString());
				int compid = JSONHelper.getIntValue(jsonData, "compid");
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				String lang = JSONHelper.getValue(jsonData, "lang");
				if(lang.isEmpty())
					lang = defaultLang;

				Part filePart = req.getPart("file");
				if(filePart==null) {
					errMsg = "No File Present!";
					break;
				}
				StringBuilder sb2 = new StringBuilder();
				InputStream is = filePart.getInputStream();
				ArrayList<String> fileContent = new ArrayList<String>();
				try(
						BufferedReader br = new BufferedReader(new InputStreamReader(is,Charset.forName(StandardCharsets.UTF_8.name())));
						){
					int c = 0;
					while((c= br.read()) != -1) {
						sb2.append((char)c);
					}
					fileContent = Util.fileSBToArrayList(sb2);
				}catch (Exception e) {
					System.out.println("Server Error Process: uploadcustomerimport -> " + e.toString());
					errMsg = "Server Error";
					break;
				}finally {
					if(is!=null)
						is.close();
				}

				StringBuilder errSb = new StringBuilder();
				int lineNo = 0;
				for(String line : fileContent) {
					lineNo++;
					String err = "";

					String name = Util.getValueAt(line, ",", 0).trim().toUpperCase();
					String surname = Util.getValueAt(line, ",", 1).trim().toUpperCase();
					String idno = Util.getValueAt(line, ",", 2).trim();
					String tel = Util.getValueAt(line, ",", 3).trim();
					String cell = Util.getValueAt(line, ",", 4).trim();
					String vat = Util.getValueAt(line, ",", 5).trim();
					String regno = Util.getValueAt(line, ",", 6).trim();
					String email = Util.getValueAt(line, ",", 7).trim().toLowerCase();
					String website = Util.getValueAt(line, ",", 8).trim();
					String physaddr1 = Util.getValueAt(line, ",", 9).trim();
					String physaddr2 = Util.getValueAt(line, ",", 10).trim();
					String physaddr3 = Util.getValueAt(line, ",", 11).trim();
					String physaddrtown = Util.getValueAt(line, ",", 12).trim();
					String physaddrprov = Util.getValueAt(line, ",", 13).trim();
					String physaddrcode = Util.getValueAt(line, ",", 14).trim();
					String postaddr1 = Util.getValueAt(line, ",", 15).trim();
					String postaddr2 = Util.getValueAt(line, ",", 16).trim();
					String postaddr3 = Util.getValueAt(line, ",", 17).trim();
					String postaddrtown = Util.getValueAt(line, ",", 18).trim();
					String postaddrprov = Util.getValueAt(line, ",", 19).trim();
					String postaddrcode = Util.getValueAt(line, ",", 20).trim();
					String contname = Util.getValueAt(line, ",", 21).trim();
					String contsurname = Util.getValueAt(line, ",", 22).trim();
					String contemail = Util.getValueAt(line, ",", 23).trim();
					String contcell = Util.getValueAt(line, ",", 24).trim();
					String conttel = Util.getValueAt(line, ",", 25).trim();
					String payref = Util.getValueAt(line, ",", 26).trim();
					String deviceno = Util.getValueAt(line, ",", 27).trim();
					String clientref = Util.getValueAt(line, ",", 28).trim();
					String password = Util.getValueAt(line, ",", 29).trim();
					String uniqueid = Util.getValueAt(line, ",", 30).trim();
					if(name.isEmpty()||name.equals("-")) 
						err = "Line: " + lineNo + ", Invalid Name";
					else if(surname.isEmpty()||surname.equals("-"))
						err = "Line: " + lineNo + ", Invalid Surname";
					else if(cell.isEmpty()||cell.equals("-"))
						err = "Line: " + lineNo + ", Invalid Cellphone Number";
					if(err.isEmpty()==false) {
						errSb.append((lineNo==1 ? "" : "\n") + err);
						continue;
					}

					if(contcell.isEmpty())
						contcell = cell;
					if(contname.isEmpty())
						contname = name;
					if(contsurname.isEmpty())
						contsurname = surname;

					long passhash = 0;
					if(password.isEmpty()==false) 
						passhash = (long)password.hashCode()& 0xffffffffL;
					long uniqueidhash = 0;
					if(uniqueid.isEmpty()==false)
						uniqueidhash = (long)uniqueid.hashCode()& 0xffffffffL;

					Client client = ClientDao.loadClientByCellNo(connection, cell);
					if(client.getClientid()<=0) {
						client.setClientname(name);
						client.setClientsurname(surname);
						client.setClientidno(idno);
						client.setClientcellno(cell);
						client.setClienttelno(tel);
						client.setClientvatno(vat);
						client.setClientregno(regno);
						client.setClientemail(email);
						client.setClientwebsite(website);
						client.setClientphysaddr1(physaddr1);
						client.setClientphysaddr2(physaddr2);
						client.setClientphysaddr3(physaddr3);
						client.setClientphystown(physaddrtown);
						client.setClientphysprov(physaddrprov);
						client.setClientphyspostcode(physaddrcode);
						client.setClientpostaddr1(postaddr1);
						client.setClientpostaddr2(postaddr2);
						client.setClientpostaddr3(postaddr3);
						client.setClientposttown(postaddrtown);
						client.setClientpostprov(postaddrprov);
						client.setClientpostcode(postaddrcode);
						client.setContname(contname);
						client.setContsurname(contsurname);
						client.setContemail(contemail);
						client.setContcellno(contcell);
						client.setConttelno(conttel);
						client.setClientpayref(payref);
						client.setClientinitdate(DateUtil.getCurrentCCYYMMDDStr());
						client.setClientdeviceno(deviceno);
						client.setClientref(clientref);
						client.setClientpassword(password);
						client.setClientuniqueid(uniqueid);
						client.setClientuniquemillis(uniqueidhash);
						client.setClientpasswordmillis(passhash);
						client = ClientDao.createClient(connection, client);
					}
					if(client.getClientid()<=0) {
						errMsg = "Error Creating Client: ["+client.getClientid()+"]";
						break;
					}
					CompanyClient compClient = CompanyClientDao.loadCompanyClient(connection, company.getCompId(), client.getClientid());
					if(compClient.getClientId()>0) {
						errSb.append((lineNo==1 ? "" : "\n") + "Line: " + lineNo + ", Client Already Exists");
						continue;
					}
					compClient.setCompId(company.getCompId());
					compClient.setClientId(client.getClientid());
					compClient.setCreateDate(DateUtil.getCurrentCCYYMMDDStr());
					compClient.setCreateTime(DateUtil.getCurrentHHMMSSStr());
					compClient.setStatus(CompanyClient.COMP_CLIENT_ACTIVE);
					compClient.setStatusDate(DateUtil.getCurrentCCYYMMDDStr());
					compClient.setStatusTime(DateUtil.getCurrentHHMMSSStr());
					compClient.setClientExtRef(client.getClientpayref());

					if(CompanyClientDao.createCompanyClient(connection, compClient)==false) {
						errMsg = "Error Creating Company Client: ["+client.getClientid()+","+company.getCompId()+"]";
						break;
					}
				}

				if(errMsg.isEmpty()==false)
					break;


				sb = JSONHelper.getSuccessJson("Customers Imported  Successfully!");

			}

		}catch (Exception e) {
			System.out.println("Server Error Process: uploadcustomerimport -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.isEmpty()==false) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder loadCustomerByCell(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String lang = JSONHelper.getValue(jsonBody, "lang");
				if(lang.isEmpty())
					lang = defaultLang;
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				String cellno = JSONHelper.getValue(jsonBody, "cellno");
				if(cellno.length()<9) {
					errMsg = "Invalid Cell Number!";
					break;
				}

				Client client = ClientDao.loadClientByCellNo(connection, cellno);
				if(client.getClientid()<=0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				CompanyClient compCli = CompanyClientDao.loadCompanyClient(connection, company.getCompId(), client.getClientid());
				if(compCli.getClientId()<=0) {
					errMsg = "Client Not Registered With: " + company.getCompName() + "!";
					break;
				}

				sb = new StringBuilder(client.toJsonString());
			}

		}catch (Exception e) {
			System.out.println("Server Error Process: loadCustomerByCell -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.isEmpty()==false) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder linkCustomerToProd(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String lang = JSONHelper.getValue(jsonBody, "lang");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				String prodref = JSONHelper.getValue(jsonBody, "prodref");
				int prodprice = JSONHelper.getIntValue(jsonBody, "prodprice");
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");

				if(lang.isEmpty())
					lang = defaultLang;
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				if(clientid<=0) {
					errMsg = "Invalid Client ID!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()<=0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				CompanyClient compClient = CompanyClientDao.loadCompanyClient(connection, compid, clientid);
				if(compClient.getClientId()<=0) {
					errMsg = "Company Client Does Not Exist!";
					break;
				}
				if(prodid<=0) {
					errMsg = "Invalid Product ID!";
					break;
				}
				CompanyProduct compProd = CompanyProductDao.getCompanyProduct(company.getCompId(), prodid, connection);
				if(compProd.getProdId()<=0) {
					errMsg = "Company Product Does Not Exist!";
					break;
				}
				if(prodref.length()<=0) {
					errMsg = "Invalid Product Reference!";
					break;
				}
				if(prodprice<0) {
					errMsg = "Invalid Product Price!";
					break;
				}
				ClientProduct clientProduct = ClientProductDao.loadClientProd(connection, client.getClientid(), company.getCompId(), compProd.getProdId(), prodref);
				if(clientProduct.getProdId()>0&&clientProduct.getStatus()==ClientProduct.ACTIVE) {
					errMsg = "Customer is Already linked To Product!";
					break;
				}
				if(clientProduct.getProdId()>0&&clientProduct.getStatus()==ClientProduct.IN_ACTIVE) {
					clientProduct.setStatus(ClientProduct.ACTIVE);
					if(ClientProductDao.updateClientProduct(connection, clientProduct)==false) {
						errMsg = "Error Activating Customer Product Link!";
						break;
					}
				}else {
					clientProduct.setClientId(client.getClientid());
					clientProduct.setCompId(company.getCompId());
					clientProduct.setProdId(compProd.getProdId());
					clientProduct.setProdRsp(prodprice);				
					clientProduct.setProdRef(prodref);
					clientProduct = ClientProductDao.createClientProduct(connection, clientProduct);
					if(clientProduct.getClientId()<=0) {
						errMsg = "Unable To Link Customer To Product!";
						break;
					}
				}
				sb = JSONHelper.getSuccessJson("Successfully Linked Customer To Product");
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: linkCustomerToProd -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.isEmpty()==false) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchCustomerProducts(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String lang = JSONHelper.getValue(jsonBody, "lang");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String custcell = JSONHelper.getValue(jsonBody, "custcell");

				if(lang.isEmpty())
					lang = defaultLang;
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				if(custcell.length()<9) {
					errMsg = "Invalid Cell Number!";
					break;
				}
				Client client = ClientDao.loadClientByCellNo(connection, custcell);
				if(client.getClientid()<=0) {
					errMsg = "Client Cell Does Not Exist!";
					break;
				}
				CompanyClient compClient = CompanyClientDao.loadCompanyClient(connection, company.getCompId(), client.getClientid());
				if(compClient.getClientId()<=0) {
					errMsg = "Client Cell is Not Registered With Company!";
					break;
				}
				ArrayList<ClientProduct> clientProducts = ClientProductDao.getClientProducts(connection, company.getCompId(), client.getClientid());

				JSONObject json = new JSONObject();


				json.put("client", client.toJSON());
				json.put("clientproducts", ClientProductDao.toJSONArray(clientProducts));

				sb = new StringBuilder(json.toString());

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchCustomerProducts -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.isEmpty()==false) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder unlinkClientProduct(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String lang = JSONHelper.getValue(jsonBody, "lang");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");
				String prodref = JSONHelper.getValue(jsonBody, "prodref");

				if(lang.isEmpty())
					lang = defaultLang;
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()<=0) {
					errMsg = "Client Does Not Exist!";
					break;
				}
				CompanyProduct prod = CompanyProductDao.getCompanyProduct(company.getCompId(), prodid, connection);
				if(prod.getProdId()<=0) {
					errMsg = "Product Does Not Exist!";
					break;
				}
				if(prodref.length()<=0) {
					errMsg = "Invalid Product Reference!";
					break;
				}
				ClientProduct cliProd = ClientProductDao.loadClientProd(connection, client.getClientid(), company.getCompId(), prod.getProdId(), prodref);
				if(cliProd.getClientId()<=0) {
					errMsg = "Customer Is Not Linked To Product!";
					break;
				}

				if(ClientProductDao.deactiveClientProduct(connection, cliProd)==false) {
					errMsg = "Error Unlinking Customer And Product!";
					break;
				}

				sb = JSONHelper.getSuccessJson("Customer Successfully Unlinked!");

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: unlinkClientProduct -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.isEmpty()==false) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getWebPagePermissions(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				ArrayList<WebPagePermission> permissions =  WebPagePermissionDao.getWebPagePermissions(connection);
				ArrayList<WebPagePermissionGroup> permissionGroups = WebPagePermissionGroupDao.getPagePermissionGroups(connection);

				JSONArray jsonPermissions = WebPagePermissionDao.getJSONArray(permissions);
				JSONArray jsonPermissionGroups = WebPagePermissionGroupDao.getJSONArray(permissionGroups);

				JSONObject json = new JSONObject();
				json.put("permissions", jsonPermissions);
				json.put("permissiongroups", jsonPermissionGroups);

				sb = new StringBuilder(json.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getWebPagePermissions -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.isEmpty()==false) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCompaWebPagePermissionTemplates(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String lang = JSONHelper.getValue(jsonBody, "lang");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");

				if(lang.isEmpty())
					lang = defaultLang;
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}

				ArrayList<CompanyWebPagePermissionTemplate> templates = CompanyWebPagePermissionTemplateDao.getCompanyPermissionTemplates(connection, company.getCompId());
				sb = new StringBuilder(CompanyWebPagePermissionTemplateDao.getJSONArray(templates).toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompaWebPagePermissionTemplates -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.isEmpty()==false) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder captureCompanyUser(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String lang = JSONHelper.getValue(jsonBody, "lang");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String name = JSONHelper.getValue(jsonBody, "name");
				String surname = JSONHelper.getValue(jsonBody, "surname");
				String cellno = JSONHelper.getValue(jsonBody, "cellno");
				String email = JSONHelper.getValue(jsonBody, "email");
				String telno = JSONHelper.getValue(jsonBody, "telno");
				String password = JSONHelper.getValue(jsonBody, "password");
				String confirmpassword = JSONHelper.getValue(jsonBody, "confirmpassword");
				String permissionids = JSONHelper.getValue(jsonBody, "permissions");
				String productids = JSONHelper.getValue(jsonBody, "productids");

				if(lang.isEmpty())
					lang = defaultLang;
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				if(name.isEmpty()) {
					errMsg = "Invalid Name!";
					break;
				}
				if(surname.isEmpty()) {
					errMsg = "Invalid Surname!";
					break;
				}
				if(cellno.isEmpty()) {
					errMsg = "Invalid Cell Number!";
					break;
				}
				if(email.isEmpty()) {
					errMsg = "Invalid Email!";
					break;
				}
				if(Util.validatePasswordStrength(password)==false) {
					errMsg = "Invalid Password!";
					break;
				}
				if(password.equals(confirmpassword)==false) {
					errMsg = "Password's Do Not Match!";
					break;
				}
				if(permissionids.isEmpty()) {
					errMsg = "User Must Have Permissions!";
					break;
				}
				if(productids.isEmpty()) {
					errMsg = "User Products Invalid!";
					break;
				}

				String [] permissionsAr = permissionids.split(",");
				ArrayList<WebPagePermissionGroup> permissionGroups = WebPagePermissionGroupDao.getPagePermissionGroups(connection);
				ArrayList<WebPagePermission> permissions = WebPagePermissionDao.getWebPagePermissions(connection);
				StringBuilder tmpPermissionSb = new StringBuilder();
				for(WebPagePermission permission : permissions) {
					for(String permissionId : permissionsAr) {
						if(permissionId.equals(""+permission.getPermissionid())==false)
							continue;
						if(permission.getPermissiongroup()>0) {
							for(WebPagePermissionGroup permissionGroup : permissionGroups) {
								if(permission.getPermissiongroup()!=permissionGroup.getPermissiongroupid()) 
									continue;
								if(tmpPermissionSb.indexOf(permissionId)<0)
									tmpPermissionSb.append((tmpPermissionSb.length()==0 ? "" : ",")+permissionId);
							}
						}else
							tmpPermissionSb.append((tmpPermissionSb.length()==0 ? "" : ",")+permissionId);
					}
				}
				permissionids = tmpPermissionSb.toString();

				CompanyUser compUser = new CompanyUser();
				compUser.setUserName(name);
				compUser.setUserSurname(surname);
				compUser.setCompId(company.getCompId());
				compUser.setUserCellNo(cellno);
				compUser.setUserTelNo(telno);
				compUser.setUserEmail(email);
				compUser.setUserPassword(password);
				compUser.setUserLoginDateTime("");
				compUser.setUserActive(CompanyUser.ACTIVE);
				compUser.setUserType(CompanyUser.USER_TYPE_USER);
				compUser.setProfileId(0);
				compUser.setUserPermissions(permissionids);

				compUser = CompanyUserDao.addCompanyUser(connection, compUser);
				if(compUser.getUserId()<=0) {
					errMsg = "Error Creating User!";
					break;
				}

				String [] prodids = productids.split(",");
				for(String id : prodids) {
					int prodId = Util.parseInt(id, 0);
					if(prodId==0)
						continue;
					CompanyUserProduct userProd = new CompanyUserProduct();
					userProd.setCompId(company.getCompId());
					userProd.setUserId(compUser.getUserId());
					userProd.setProdId(Util.parseInt(id, 0));
					userProd.setProdActive(CompanyUserProduct.PROD_ACTIVE);
					CompanyUserProductDao.addCompUserProd(connection, userProd);
				}
				sb = JSONHelper.getSuccessJson("Company User Created Successfully!");

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: captureCompanyUser -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.isEmpty()==false) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCompanyUsers(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String lang = JSONHelper.getValue(jsonBody, "lang");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				if(lang.isEmpty())
					lang = defaultLang;
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				ArrayList<CompanyUser> users = CompanyUserDao.getCompanyUsers(company.getCompId(), connection);


				sb = new StringBuilder(CompanyUserDao.getCompanyUsersJSON(users).toString());

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyUsers -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.isEmpty()==false) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCompanyUserProds(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String lang = JSONHelper.getValue(jsonBody, "lang");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				int userid = JSONHelper.getIntValue(jsonBody, "userid");

				if(lang.isEmpty())
					lang = defaultLang;
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				CompanyUser user = CompanyUserDao.loadCompanyUser(company.getCompId(), userid, connection);
				if(user.getUserId()<=0) {
					errMsg = "Invalid User!";
					break;
				}

				ArrayList<CompanyUserProduct> userProds = CompanyUserProductDao.getCompanyUserProds(connection, company.getCompId(), user.getUserId());
				sb = new StringBuilder(CompanyUserProductDao.getCompUserProdsJSON(userProds).toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyUserProds -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.isEmpty()==false) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder updateCompanyUser(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String lang = JSONHelper.getValue(jsonBody, "lang");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				int userid = JSONHelper.getIntValue(jsonBody, "userid");
				String name = JSONHelper.getValue(jsonBody, "name");
				String surname = JSONHelper.getValue(jsonBody, "surname");
				String cellno = JSONHelper.getValue(jsonBody, "cellno");
				String telno = JSONHelper.getValue(jsonBody, "telno");
				String permissionids = JSONHelper.getValue(jsonBody, "permissions");
				String productids = JSONHelper.getValue(jsonBody, "productids");

				if(lang.isEmpty())
					lang = defaultLang;
				Company company = new Company();
				if(compid==0) {
					errMsg = "Invalid Company ID!";
					break;
				}
				company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company Does Not Exist!";
					break;
				}
				CompanyUser compUser = CompanyUserDao.loadCompanyUser(company.getCompId(), userid, connection);
				if(compUser.getUserId()<=0) {
					errMsg = "Invalid User!";
					break;
				}
				if(name.isEmpty()) {
					errMsg = "Invalid Name!";
					break;
				}
				if(surname.isEmpty()) {
					errMsg = "Invalid Surname!";
					break;
				}
				if(cellno.isEmpty()) {
					errMsg = "Invalid Cell Number!";
					break;
				}

				if(permissionids.isEmpty()) {
					errMsg = "User Must Have Permissions!";
					break;
				}
				if(productids.isEmpty()) {
					errMsg = "User Products Invalid!";
					break;
				}

				String [] permissionsAr = permissionids.split(",");
				ArrayList<WebPagePermissionGroup> permissionGroups = WebPagePermissionGroupDao.getPagePermissionGroups(connection);
				ArrayList<WebPagePermission> permissions = WebPagePermissionDao.getWebPagePermissions(connection);
				StringBuilder tmpPermissionSb = new StringBuilder();
				for(WebPagePermission permission : permissions) {
					for(String permissionId : permissionsAr) {
						if(permissionId.equals(""+permission.getPermissionid())==false)
							continue;
						if(permission.getPermissiongroup()>0) {
							for(WebPagePermissionGroup permissionGroup : permissionGroups) {
								if(permission.getPermissiongroup()!=permissionGroup.getPermissiongroupid()) 
									continue;
								if(tmpPermissionSb.indexOf(permissionId)<0)
									tmpPermissionSb.append((tmpPermissionSb.length()==0 ? "" : ",")+permissionId);
							}
						}else
							tmpPermissionSb.append((tmpPermissionSb.length()==0 ? "" : ",")+permissionId);
					}
				}
				permissionids = tmpPermissionSb.toString();


				compUser.setUserName(name);
				compUser.setUserSurname(surname);
				compUser.setUserCellNo(cellno);
				compUser.setUserTelNo(telno);
				compUser.setUserPermissions(permissionids);

				if(CompanyUserDao.updateCompanyUser(connection, compUser)==false) {
					errMsg = "Error Updating User!";
					break;
				}

				ArrayList<CompanyUserProduct> currUserProds = CompanyUserProductDao.getCompanyUserProds(connection, company.getCompId(), compUser.getUserId());
				StringBuilder newUserProds = new StringBuilder();
				String [] prodids = productids.split(",");

				if(CompanyUserProductDao.deleteAllUserProds(connection, company.getCompId(), compUser.getUserId())==false) {
					errMsg = "Error Updating User Products 1!";
					break;
				}


				for(String id : prodids) {
					int prodId = Util.parseInt(id, 0);
					if(prodId==0)
						continue;
					CompanyUserProduct userProd = new CompanyUserProduct();
					userProd.setCompId(company.getCompId());
					userProd.setUserId(compUser.getUserId());
					userProd.setProdId(Util.parseInt(id, 0));
					userProd.setProdActive(CompanyUserProduct.PROD_ACTIVE);
					CompanyUserProductDao.addCompUserProd(connection, userProd);
				}
				sb = JSONHelper.getSuccessJson("Company User Created Successfully!");

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: updateCompanyUser -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.isEmpty()==false) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			System.out.println(errMsg);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getCompClientPaymentsDetailed(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
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
				int bankid = JSONHelper.getIntValue(jsonBody, "bankid");
				if(bankid==0) {
					errMsg = "Invalid bankid!";
					break;
				}
				CompanyBank compBank = CompanyBankDao.loadCompanyBanks(connection, bankid);
				if(compBank==null||compBank.getBankId()==0) {
					errMsg = "Invalid bankid!";
					break;
				}
				String startDate = JSONHelper.getValue(jsonBody, "startdate");
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid start date!";
					break;
				}
				String endDate = JSONHelper.getValue(jsonBody, "enddate");
				if(endDate==null||endDate.length()<=0) {
					errMsg = "Invalid end date!";
					break;
				}

				JSONArray jsonAr = ClientPaymentDao.getCompBankClientPaymentsWithClientDetails(connection, company.getCompId(), bankid, startDate, endDate);
				sb = new StringBuilder(jsonAr.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompClientPaymentsDetailed -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;

	}
	public static StringBuilder getCompBankTrans(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				int bankid = JSONHelper.getIntValue(jsonBody, "bankid");
				String startDate = JSONHelper.getValue(jsonBody, "startdate");
				String endDate = JSONHelper.getValue(jsonBody, "enddate");
				String status = JSONHelper.getValue(jsonBody, "status");				

				if(compid==0) {
					errMsg = "Invalid compid!";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company does not exist!";
					break;
				}
				if(bankid==0) {
					errMsg = "Invalid bankid!";
					break;
				}
				CompanyBank compBank = CompanyBankDao.loadCompanyBanks(connection, bankid);
				if(compBank==null||compBank.getBankId()==0) {
					errMsg = "Invalid bankid!";
					break;
				}
				if(startDate==null||startDate.length()<=0) {
					errMsg = "Invalid start date!";
					break;
				}
				if(endDate==null||endDate.length()<=0) {
					errMsg = "Invalid end date!";
					break;
				}
				if(status==null)
					status = "";
				if(status.equals("0"))
					status = "";

				ArrayList<CompanyBankTran> bankTrans = CompanyBankTranDao.getCompanyBankTrans(connection, company.getCompId(), compBank.getBankId(), startDate, endDate, status);

				sb = new StringBuilder(CompanyBankTranDao.getBankTransJSON(bankTrans).toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompBankTrans -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder registerWalletAppClient(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String cell = JSONHelper.getValue(jsonBody, "cell");
				String email = JSONHelper.getValue(jsonBody, "email");
				String name = JSONHelper.getValue(jsonBody, "name");
				String surname = JSONHelper.getValue(jsonBody, "surname");
				String password = JSONHelper.getValue(jsonBody, "password");
				String confirmpass = JSONHelper.getValue(jsonBody, "confirmpassword");

				if(cell.length()<9) {
					errMsg = "9001";
					break;
				}
				if(name.length()<=0) {
					errMsg = "9002";
					break;
				}
				if(surname.length()<=0) {
					errMsg = "9003";
					break;
				}
				if(Util.validatePasswordStrength(password)==false) {
					errMsg = "9004";
					break;
				}
				if(password.equals(confirmpass)==false) {
					errMsg = "9005";
					break;
				}
				if(email.length()>0&&Util.validEmail(email)==false) {
					errMsg = "9009";
					break;
				}
				Client client = ClientDao.loadClientByCellNo(connection, cell);
				boolean isPayClient = false;
				if(client.getClientid()>0) 
					isPayClient = true;
				AppClient appClient = AppClientDao.loadAppClientByCell(connection, cell);
				if(isPayClient&&appClient.getClientId()<=0) {
					errMsg = "9006";
					break;
				}

				if(isPayClient==false) {
					client.setClientname(name);
					client.setClientsurname(surname);
					client.setClientcellno(cell);
					client.setClientemail(email);
					client = ClientDao.createClient(connection, client);
					if(client.getClientid()<=0) {
						errMsg = "9007";
						break;
					}
				}
				appClient.setClientId(client.getClientid());
				appClient.setClientCellNo(cell);
				appClient.setClientEmail(email);
				appClient.setClientPassword(password);
				appClient.setClientStatus(AppClient.STATUS_ACTIVE);
				appClient.setClientStatusDate(DateUtil.getCurrentCCYYMMDDDate());
				appClient.setClientStatusTime(DateUtil.getCurrentHHMMSSTime());
				appClient.setClientActive(AppClient.STATUS_ACTIVE);
				appClient.setCreatedDate(DateUtil.getCurrentCCYYMMDDDate());
				appClient.setCreatedTime(DateUtil.getCurrentHHMMSSTime());
				appClient = AppClientDao.addAppClient(connection, appClient);
				if(appClient.getClientId()<=0) {
					errMsg = "9008";
					break;
				}

				JSONObject jsonClient = client.toJSON();
				JSONObject jsonAppClient = appClient.toJSON();
				JSONObject json = new JSONObject();
				json.put("client", jsonClient);
				json.put("appclient", jsonAppClient);
				sb = new StringBuilder(json.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: registerWalletAppClient -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getBanks(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				ArrayList<Bank> banks = BankDao.getBanks(connection);
				sb.append(BankDao.getBanksJSON(banks).toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getBanks -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	//PayGuru only -1
	public static StringBuilder getClientBankRefs(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");


				Client client = ClientDao.loadClient(connection, clientid);

				if(client.getClientid()<=0) {
					errMsg = "9001";
					break;
				}


				ArrayList<ClientProduct> wallets = ClientProductDao.getMasterWallets(connection, clientid);
				ClientProduct masterWallet = ClientProductDao.loadMasterClientWallet(connection, client.getClientid());
				ArrayList<ClientBankRef> bankRefs = ClientBankRefDao.getClientBankRefs(connection, client.getClientid());

				JSONObject clientProdJSON = masterWallet.toJSON();
				JSONArray bankRefsJSON = ClientBankRefDao.getClientBankRefsJSON(bankRefs);
				JSONArray walletsJSON = ClientProductDao.toJSONArray(wallets);

				JSONObject json = new JSONObject();
				json.put("clientwallet", clientProdJSON);
				json.put("clientbankrefs", bankRefsJSON);
				json.put("wallets", walletsJSON);
				sb.append(json.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientBankRefs -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getClientFinTrans(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");

				Client client = ClientDao.loadClient(connection, clientid);

				if(client.getClientid()<=0) {
					errMsg = "9001";
					break;
				}

				ArrayList<FinTran> finTrans = FinTranDao.getClientFinTrans(connection, client.getClientid());

				JSONObject json = new JSONObject();
				json.put("fintrans", FinTranDao.getFinTransJSON(finTrans));
				sb.append(json.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientFinTrans -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getClientWallets(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid"); 

				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()<=0) {
					errMsg = "9001";
					break;
				}

				ArrayList<ClientProduct> clientProducts = ClientProductDao.getClientWallets(connection, client.getClientid());
				ArrayList<ClientBalance> clientBals = ClientBalanceDao.getClientBalances(connection, client.getClientid());
				ArrayList<ClientProduct> clientCompProducts = ClientProductDao.getAllCompanyClientWallets(connection, client.getClientid());
				ArrayList<ClientCompanyBalance> clientCompBalances = ClientCompanyBalanceDao.getClientCompanyBalances(connection, client.getClientid());
				JSONArray clientWalletComps = new JSONArray();
				JSONArray clientWallets = new JSONArray();
				JSONArray clientCompWallets = new JSONArray();

				ArrayList<Integer> compids = new ArrayList<Integer>();
				for(int i=0;i<clientProducts.size();i++) {
					Company walletComp = CompanyDao.getCompany(clientProducts.get(i).getCompId(),connection);

					ClientProduct clientProd = clientProducts.get(i);
					JSONObject wallet = clientProd.toJSON();
					ClientProductRef prodRef = ClientProductRefDao.loadClientProductRef(connection, clientProd.getClientId(), clientProd.getCompId(), clientProd.getProdId(), clientProd.getProdRef());
					wallet.put("prodrefno", prodRef.getProdrefno());
					clientWallets.put(wallet);

					if(compids.indexOf(walletComp.getCompId())>=0)
						continue;
					compids.add(walletComp.getCompId());
					clientWalletComps.put(walletComp.toJSON());
				}
				for(int i=0;i<clientCompProducts.size();i++) {
					Company walletComp = CompanyDao.getCompany(clientCompProducts.get(i).getCompId(),connection);

					ClientProduct clientProd = clientCompProducts.get(i);
					JSONObject wallet = clientProd.toJSON();
					ClientProductRef prodRef = ClientProductRefDao.loadClientProductRef(connection, clientProd.getClientId(), clientProd.getCompId(), clientProd.getProdId(), clientProd.getProdRef());
					wallet.put("prodrefno", prodRef.getProdrefno());
					clientCompWallets.put(wallet);

					if(compids.indexOf(walletComp.getCompId())>=0)
						continue;
					compids.add(walletComp.getCompId());
					clientWalletComps.put(walletComp.toJSON());
				}

				JSONObject json = new JSONObject();
				json.put("clientproducts", clientWallets);
				json.put("clientbalances", ClientBalanceDao.toJSONArray(clientBals));
				json.put("clientcompanyproducts", clientCompWallets);
				json.put("clientcompanybalances", ClientCompanyBalanceDao.toJSONArray(clientCompBalances));
				json.put("walletcomps", clientWalletComps);
				sb.append(json.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientWallets -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getClientCompanyWallets(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()<=0) {
					errMsg = "9001";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company.getCompId()<=0) {
					errMsg = "9002";
					break;
				}

				ArrayList<ClientProduct> compClientWallets = ClientProductDao.getCompanyClientWallets(connection, client.getClientid(), company.getCompId());
				ArrayList<ClientProductRef> clientProductRefs = ClientProductRefDao.getClientCompProductRefs(connection, client.getClientid(), company.getCompId());
				
				String walletType = CompanyParamDao.getCompParamValue(connection, company.getCompId(), "wallettype");
				if(walletType.isEmpty())
					walletType = ClientProduct.WALLET_TYPE_NORMAL;
					
				System.out.println("WALLET TYPE: " + walletType);
				
				double curBal = 0;

				

				
				ArrayList<ClientCompanyBalance> compClientWalletBalances = ClientCompanyBalanceDao.getClientCompanyBalances(connection, client.getClientid(), company.getCompId());
				
				JSONArray compClientWalletsJSON = ClientProductDao.toJSONArray(compClientWallets);
				JSONArray compClientWalletBalsJSON = ClientCompanyBalanceDao.toJSONArray(compClientWalletBalances);
				JSONArray compClientWalletRefsJSON = ClientProductRefDao.toJSONArray(clientProductRefs);
				
				
				
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("wallets", compClientWalletsJSON);
				json.put("walletbalances", compClientWalletBalsJSON);
				json.put("walletrefs", compClientWalletRefsJSON);
				
				sb.append(json.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientCompanyWallets -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;

	}
	public static StringBuilder getClientWalletTransactions(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String walletkey = JSONHelper.getValue(jsonBody, "walletkey");
				if(walletkey.isEmpty()) {
					errMsg = "9001";
					break;
				}
				System.out.println("WALLETKEY: " + walletkey);
				int clientid = Util.parseInt(Util.getValueAt(walletkey, "_", 0),0);
				int compid = Util.parseInt(Util.getValueAt(walletkey, "_", 1), 0);
				int prodid = Util.parseInt(Util.getValueAt(walletkey, "_", 2), 0);
				int subacc = Util.parseInt(Util.getValueAt(walletkey, "_", 3), 0);
						
				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()<=0) {
					errMsg = "9002";
					break;
				}
				
				Company company = CompanyDao.getCompany(compid, connection);
				if(company.getCompId()==0) {
					errMsg = "9003";
					break;
				}
				
				CompanyProduct compProd = CompanyProductDao.getCompanyProduct(company.getCompId(), prodid, connection);
				if(compProd.getProdId()==0) {
					errMsg = "9004";
					break;
				}
				
				ClientProduct clientProd = ClientProductDao.loadClientProd(connection, client.getClientid(), company.getCompId(), compProd.getProdId(), ""+subacc);
				if(clientProd.getClientId()<=0) {
					errMsg = "9005";
					break;
				}
				
				ClientProductRef clientProdRef = ClientProductRefDao.loadClientProductRef(connection, clientProd.getClientId(), clientProd.getCompId(), clientProd.getProdId(), clientProd.getProdRef());
				if(clientProdRef.getProdrefno().isEmpty()) {
					errMsg = "9006";
					break;
				}
					
			
				ArrayList<ClientInvoice> clientInvoices = ClientInvoiceDao.loadWalletTransactionsByRefNo(connection, clientProdRef.getProdrefno());
		

				sb.append(ClientInvoiceDao.getClientInvoicesJSON(clientInvoices));
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientWalletTransactions -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	
	public static StringBuilder getClientDonorWallets(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				int donorid = JSONHelper.getIntValue(jsonBody, "donorid");

				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()<=0) {
					errMsg = "9001";
					break;
				}
				Donor donor = DonorDao.loadDonorById(connection, donorid);
				if(donor.getDonorid()<=0) {
					errMsg = "9002";
					break;
				}

				ArrayList<ClientProduct> donorClientWallets = ClientProductDao.getClientDonorWallets(connection, clientid, donorid);
				ArrayList<ClientCompanyBalance> donorClientWalletBalances = ClientCompanyBalanceDao.getClientDonorWalletBalances(connection, clientid, donorid);


				JSONObject json = new JSONObject();
				json.put("donorclientwallets", ClientProductDao.toJSONArray(donorClientWallets));
				json.put("donorclientwalletbalances", ClientCompanyBalanceDao.toJSONArray(donorClientWalletBalances));
				json.put("donor", donor.toJSON());
				sb.append(json.toString());

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientDonorWallets -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getAvailableClientDonorWallets(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				int donorid = JSONHelper.getIntValue(jsonBody, "donorid");

				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()==0) {
					errMsg = "9001";
					break;
				}
				Donor donor = DonorDao.loadDonorById(connection, donorid);
				if(donor.getDonorid()==0) {
					errMsg = "9002";
					break;
				}
				DonorClient donorClient = DonorClientDao.loadDonorClient(connection, donor.getDonorid(), client.getClientid());
				if(donorClient.getClientid()==0) {
					errMsg = "9003";
					break;
				}

				ArrayList<DonorCompanyProduct> donorCompProds = DonorCompanyProductDao.getDonorCompanyProducts(connection, donorid);
				ArrayList<CompanyProduct> compProds = new ArrayList<CompanyProduct>();
				JSONArray jsonComps = new JSONArray();

				for(int i=0;i<donorCompProds.size();i++) {
					DonorCompanyProduct donorCompProd = donorCompProds.get(i);
					CompanyProduct compProd = CompanyProductDao.getCompanyProduct(donorCompProd.getCompid(), donorCompProd.getProdid(), connection);
					if(compProd.getProdId()==0)
						continue;
					compProds.add(compProd);
					Company comp = CompanyDao.getCompany(compProd.getCompId(), connection);
					JSONObject jsonComp = comp.toJSON();
					if(JSONHelper.containsJSONObject(jsonComps, jsonComp)) 
						continue;
					jsonComps.put(jsonComp);
				}


				JSONObject json = new JSONObject();
				json.put("donorcompprods", CompanyProductDao.toJSONArray(compProds));
				json.put("companys", jsonComps);

				sb.append(json.toString());

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getAvailableClientDonorWallets -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;

	}

	public static StringBuilder getCompanyBanks(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");

				Company company = CompanyDao.getCompany(compid, connection);
				if(company.getCompId()==0) {
					errMsg = "9001";
					break;
				}

				ArrayList<CompanyBank> compBanks = CompanyBankDao.getCompanyBanks(connection, company.getCompId());
				sb.append(CompanyBankDao.getCompanyBankJSON(compBanks).toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyBanks -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getClientCompanyBankRefs(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");

				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()==0) {
					errMsg = "9001";
					break;
				}
				Company company = CompanyDao.getCompany(compid, connection);
				if(company.getCompId()==0) {
					errMsg = "9002";
					break;
				}

				ArrayList<ClientCompanyBankRef> bankRefs = ClientCompanyBankRefDao.getClientCompBankRefs(connection, compid, clientid);
				ArrayList<ClientProduct> clientProds = ClientProductDao.getCompanyClientWallets(connection, client.getClientid(), company.getCompId());

				JSONArray clientProdJSON = ClientProductDao.toJSONArray(clientProds);
				JSONArray bankRefsJSON = ClientCompanyBankRefDao.toJSONArray(bankRefs);

				JSONObject json = new JSONObject();
				json.put("clientwallets", clientProdJSON);
				json.put("clientbankrefs", bankRefsJSON);
				sb.append(json.toString());			
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientCompanyBankRefs -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getWalletTransferHist(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");

				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()==0) {
					errMsg = "9001";
					break;
				}


				ArrayList<ClientHistory> clientHistorys = ClientHistoryDao.getClientWalletTransfers(connection, clientid);
				ArrayList<ClientProductRef> clientProdRefs = new ArrayList<ClientProductRef>();
				ArrayList<ClientProduct> clientProducts = new ArrayList<ClientProduct>();
				if(compid>0) {
					Company company = CompanyDao.getCompany(compid, connection);
					if(company.getCompId()==0) {
						errMsg = "9002";
						break;
					}
					clientProdRefs = ClientProductRefDao.getClientCompProductRefs(connection, clientid, compid);
					clientProducts = ClientProductDao.getCompanyClientWallets(connection, clientid, compid);
				}
				else {
					clientProdRefs = ClientProductRefDao.getClientProductRefs(connection, clientid);
					clientProducts = ClientProductDao.getClientWallets(connection, clientid);
				}
				JSONArray jsonAr = new JSONArray();

				for(int i=0;i<clientHistorys.size();i++) {
					ClientHistory clientHistory = clientHistorys.get(i);

					String origprodrefno = clientHistory.getTranref1();
					String recprodrefno = clientHistory.getTranref2();

					ClientProductRef origclientprodref = ClientProductRefDao.loadClientProductRefByReNo(connection, origprodrefno);
					ClientProductRef recclientprodref = ClientProductRefDao.loadClientProductRefByReNo(connection, recprodrefno);

					ClientProduct origwallet = ClientProductDao.loadClientProd(connection, origclientprodref.getClientid(), origclientprodref.getCompid(), origclientprodref.getProdid(), origclientprodref.getProdref());
					ClientProduct recwallet = ClientProductDao.loadClientProd(connection, recclientprodref.getClientid(), recclientprodref.getCompid(), recclientprodref.getProdid(), recclientprodref.getProdref());

					JSONObject json = clientHistory.toJSON();
					json.put("origwalletid", origwallet.getClientId()+"_"+origwallet.getCompId()+"_"+origwallet.getProdId()+"_"+origwallet.getProdRef());
					json.put("recwalletid", recwallet.getClientId()+"_"+recwallet.getCompId()+"_"+recwallet.getProdId()+"_"+recwallet.getProdRef());
					json.put("origwalletname", origwallet.getProdData());
					json.put("recwalletname", recwallet.getProdData());

					jsonAr.put(json);
				}

				sb.append(jsonAr.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getWalletTransferHist -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getClientWalletCompanys(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int clientid = JSONHelper.getIntValue(jsonBody, "clientid");

				Client client = ClientDao.loadClient(connection, clientid);
				if(client.getClientid()==0) {
					errMsg = "9001";
					break;
				}

				ArrayList<Company> companys = CompanyDao.getClientWalletCompanys(connection, client.getClientid());

				sb.append(CompanyDao.getJsonCompanys(companys).toString());

			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientWalletCompanys -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getAgentPromoProdSelectData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String agent = JSONHelper.getValue(jsonBody, "agent");
				String subagent = JSONHelper.getValue(jsonBody, "subagent");
				int compinternalid = JSONHelper.getIntValue(jsonBody, "compinternalid");

				Company company = CompanyDao.getCompany(compid,connection);
				if(company.getCompId()==0) {
					errMsg = "9001";
					break;
				}
				if(agent.isEmpty()) {
					errMsg = "9002";
					break;
				}
				if(subagent.isEmpty()) {
					errMsg = "9003";
					break;
				}
				CompanyInternal compInternal = CompanyInternalDao.loadCompanyInternal(connection, company.getCompId(), compinternalid);

				ArrayList<AgentCompanyProductPromo> agentPromos = AgentCompanyProductPromoDao.getPromos(connection, company.getCompId(), agent, subagent);
				ArrayList<CompanyProduct> products = new ArrayList<CompanyProduct>();
				if(compInternal!=null && compInternal.getCompid()>0) 
					products = CompanyProductDao.getCompanyInternalProducts(company.getCompId(), compInternal.getCompinternalid(), connection);
				else
					products = CompanyProductDao.getCompanyProducts(company.getCompId(), connection);

				JSONArray jsonProds = new JSONArray();
				for(CompanyProduct prod : products) {
					System.out.println(prod.toString());
					double prodAmt = prod.getProdTypeRsp();
					int discAmt = 0;
					for(AgentCompanyProductPromo promo : agentPromos) {
						discAmt = promo.getPromodiscamt();

						if(promo.getCompid()==prod.getCompId() && promo.getProdid()==prod.getProdId() && discAmt>0 && discAmt<100) {
							prodAmt = prodAmt - Util.calcDiscountAmt(prodAmt, discAmt, true);
							break;
						}
					}
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("key", prod.getProdCode());
					jsonObject.put("value", prod.getProdName() + (discAmt>0 ? (" - " + discAmt + "% de desconto ") : " ") + Util.formatDecimals(prodAmt));

					jsonProds.put(jsonObject);
				}

				sb.append(jsonProds.toString());
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getClientWalletCompanys -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder getZoneSoftWalletTranProds(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String invno = JSONHelper.getValue(jsonBody, "invno");
				
			
				Company company = CompanyDao.getCompany(compid,connection);
				if(company.getCompId()==0) {
					errMsg = "9001";
					break;
				}
				ClientInvoice inv = ClientInvoiceDao.loadClientInvoice(connection, compid, invno);
				if(inv.getInvNo()==null||inv.getInvNo().isEmpty()) {
					errMsg = "9002";
					break;
				}
				ClientInvoiceStatus invStatus = ClientInvoiceStatusDao.loadClientInvoiceStatus(connection, inv.getInvNo(), company.getCompId());
				if(invStatus.getInvNo().isEmpty()) {
					errMsg = "9003";
					break;
				}
				if(invStatus.getStatus().equals(ClientInvoiceStatus.DONE)==false) {
					errMsg = "9004";
					break;
				}
				String payref = invStatus.getExtRefNo();
				if(payref.isEmpty()) {
					errMsg = "9007";
					break;
				}
				
				//in future check if company does this type... i.e method to get products... this for zonesoft implementation...
				ClientInvoiceExtDocItem docItem = ClientInvoiceExtDocItemDao.loadClientInvoiceExtDocItem(connection, inv.getInvNo(), company.getCompId(), ClientInvoiceExtDocItem.DOCTYPE_RECEIPT);
				if(docItem.getCompId()<=0||docItem.getInvNo().isEmpty()||docItem.getExtDocData().isEmpty()||docItem.getExtDocStatus()==ClientInvoiceExtDocItem.ACTIVE) {
					Agent agent = AgentDao.loadAgentByApiRef(connection, inv.getSaleChannel());
					if(agent.getAgentId()==0) {
						errMsg = "9005";
						break;
					}
					
					String docUrl = CompanyAgentParamsDao.loadCompanyAgentParamValue(connection, company.getCompId(), agent.getAgentId(), "wallettranextdocurl");
					String docAppSecret =  CompanyAgentParamsDao.loadCompanyAgentParamValue(connection, company.getCompId(), agent.getAgentId(), "wallettranextdocappsecret");
					String docClientId = CompanyAgentParamsDao.loadCompanyAgentParamValue(connection, company.getCompId(), agent.getAgentId(), "wallettranextdocclientid");
					String docAppKey = CompanyAgentParamsDao.loadCompanyAgentParamValue(connection, company.getCompId(), agent.getAgentId(), "wallettranextdocappkey");
					String docTemplate = CompanyAgentParamsDao.loadCompanyAgentParamValue(connection, company.getCompId(), agent.getAgentId(), "wallettranextdoctemplate");
					if(docAppSecret.isEmpty()||docUrl.isEmpty()||docClientId.isEmpty()||docAppKey.isEmpty()||docTemplate.isEmpty()) {
						errMsg = "9006";
						break;
					}
					String body = docTemplate.replace("<|payref|>", payref);
							
				
					HTTPUrlConnectionClient urlConnection = new HTTPUrlConnectionClient(docUrl);
					String signature = SecurityUtil.generateHmacSHA256(body, docAppSecret);
					HashMap<String, String> headers = new HashMap<String, String>();
					headers.put("X-ZS-SIGNATURE", signature);
					headers.put("X-ZS-CLIENT-ID", docClientId);
					headers.put("X-ZS-APP-KEY", docAppKey);
					urlConnection.setHeaders(headers);
					urlConnection.setData(new JSONObject(body));
					urlConnection.sendUrlRequest();
					
					String response = urlConnection.respMessage;
					if(response==null||response.isEmpty()){
						errMsg = "9008";
						break;
					}
					System.out.println(response);
					JSONObject json = new JSONObject(response);
					JSONObject jsonResp = json.getJSONObject("Response");
					JSONObject jsonCont = jsonResp.getJSONObject("Content");
					JSONObject jsonDoc = jsonCont.getJSONArray("document").getJSONObject(0);
					String receiptNo = jsonDoc.getString("numero");
					JSONArray jsonVendas = jsonDoc.getJSONArray("vendas");
					StringBuilder docData = new StringBuilder();
					for(int i=0;i<jsonVendas.length();i++) {
						JSONObject venda = jsonVendas.getJSONObject(i);
						String prodname = ""+venda.get("descricao");
						int prodqty = Util.parseInt(""+venda.get("qtd"),0);
						double prodprice = Util.parseDouble(""+venda.get("precomenu"),0);
						double prodtotal = Util.parseDouble(venda.getString("total"), 0);
						docData.append((i>0?",":"") + prodname+"|"+prodqty+"|"+prodprice+"|"+prodtotal);
					}
					docItem = new ClientInvoiceExtDocItem();
					docItem.setCompId(company.getCompId());
					docItem.setInvNo(inv.getInvNo());
					docItem.setExtDocType(ClientInvoiceExtDocItem.DOCTYPE_RECEIPT);
					docItem.setExtDocData(docData.toString());
					docItem.setExtDocRef1(receiptNo);
					docItem.setExtDocStatus(ClientInvoiceExtDocItem.RETRIEVED);
					docItem = ClientInvoiceExtDocItemDao.addClientInvoiceExtDocItem(connection, docItem);
					
					if(docItem==null||docItem.getInvNo().isEmpty()) {
						errMsg = "9009";
						break;
					}
				}
				
				JSONObject docItemJSON = docItem.toJSON();
				sb.append(docItemJSON.toString());
			}

		}catch (Exception e) {
			System.out.println("Server Error Process: getZoneSoftWalletTranProds -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder searchClientWalletSubAccsLikeProdData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errCode = "";
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String ref1 = JSONHelper.getValue(jsonBody, "ref1");
				String ref2 = JSONHelper.getValue(jsonBody, "ref2");
				int prodreftype = JSONHelper.getIntValue(jsonBody, "prodreftype");
				String token = JSONHelper.getValue(jsonBody, "token");
				
			
				Company company = CompanyDao.getCompany(compid,connection);
				if(company.getCompId()==0) {
					errMsg = "Error Loading Company";
					errCode = "9001";
					break;
				}
				
				CompanyUserSession companyUserSession = CompanyUserSessionDao.loadUserSessionByToken(connection, compid, token);
				if(companyUserSession==null||companyUserSession.getUserid()<=0) {
					errMsg = "Error loading Client Session";
					errCode = "9003";
					break;
				}
				long tokenExpire = companyUserSession.getExpiretimemillis();
				long currentTime = System.currentTimeMillis();
				System.out.println("tokenExpire: "+tokenExpire);
				System.out.println("currentTime: "+currentTime);
 
				if (tokenExpire < currentTime) {
					errMsg = "Token has expired";
					errCode = "9003";
					break;
				} 
				
				CompanyUser compUser = CompanyUserDao.loadCompanyUser(company.getCompId(), companyUserSession.getUserid(), connection);
				if(compUser==null||compUser.getUserId()<=0) {
					errMsg = "User Doesnt Exist";
					errCode = "9003";
					break;
				}
				
				
				if(ref2.isEmpty()) {
					errMsg = "9002";
					break;
				}
				
				if(prodreftype==0)
					prodreftype = ClientProductRef.REF_TYPE_ACC_NO;
				
				ArrayList<ClientProduct> clientProducts = ClientProductDao.getClientProductsLikeProddata(connection, compid, ref1, ref2);
				ArrayList<Client> clients = ClientDao.getClientProductsLikeProddataClients(connection, compid, ref1, ref2);
				ArrayList<ClientProductRef> clientProdRefs = ClientProductRefDao.getClientProductsLikeProddataRefs(connection, compid, ref1, ref2, prodreftype);
				JSONArray jsonClientProds = ClientProductDao.toJSONArray(clientProducts);
				JSONArray jsonClients = ClientDao.toJSONArray(clients);
				JSONArray jsonClientProdRef = ClientProductRefDao.toJSONArray(clientProdRefs);
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("clientproducts", jsonClientProds);
				json.put("clients", jsonClients);
				json.put("clientproductrefs",jsonClientProdRef);
				sb.append(json);
				
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: searchClientWalletSubAccsLikeProdData -> " + e.toString());
			errMsg = "Server Error";
			errCode = "9200";
		}
		if(errCode.length()>0) {
			System.out.println(errMsg);
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errCode);
		}
		return sb;
	}
	public static StringBuilder getCompanyByWebRef(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String compWebRef = JSONHelper.getValue(jsonBody, "compwebref");
				
				if(compWebRef.isEmpty()) {
					errMsg = "9001";
					break;
				}
			
				Company company = CompanyDao.loadCompanyByWebRef(compWebRef,connection);
				if(company.getCompId()==0) {
					errMsg = "9002";
					break;
				}
				
				
				JSONObject json =company.toJSON();
 				sb.append(json);
				
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getCompanyByWebRef -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	public static StringBuilder loginCompanyUser(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang, String ipAddress) {
		StringBuilder sb = new StringBuilder();
		String errCode = "";
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String userEmail = JSONHelper.getValue(jsonBody, "useremail");
				String userPassword = JSONHelper.getValue(jsonBody, "userpass");
				
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Error loading client";
					errCode = "9001";
					break;
				}
				
				CompanyUser compUser = CompanyUserDao.getCompanyUserEmail(company.getCompId(), userEmail, connection);
				if(compUser==null||compUser.getCompId()==0) {
					errMsg = "Could Not Load User: ["+company.getCompId()+","+userEmail+"]";
					errCode = "9001";
					break;
				}
				
				if(compUser.getUserPassword().equals(userPassword)==false) {
					errMsg = "Password Incorrect";
					errCode = "9001";
					break;
				}
				
				CompanyUserSessionDao.deactivateUserSessions(connection, company.getCompId(), compUser.getUserId());
				
				CompanyUserSession userSession = new CompanyUserSession();
				userSession.setCompid(company.getCompId());
				userSession.setUserid(compUser.getUserId());
				userSession.setIpaddress(ipAddress);
				long currTimeMillis = System.currentTimeMillis();
				long expireTimeMillis = currTimeMillis+CompanyUserSession.EXPIRE_DURATION;
				userSession.setExpiretimemillis(expireTimeMillis);
				
				userSession = CompanyUserSessionDao.addCompanyUserSession(connection, userSession);
				if(userSession==null||userSession.getCompid()==0) {
					errMsg = "Could Not Create COMPANYUSERSESSION";
					errCode = "9002";
					break;
				}
				
				
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("token", userSession.getToken());
				json.put("tokenexpire", userSession.getExpiretimemillis());
				json.put("username", compUser.getUserName() + " " + compUser.getUserSurname());
 				sb.append(json);
				
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: loginCompanyUser -> " + e.toString());
			errMsg = "Server Error";
			errCode = "9200";
		}
		if(errCode.length()>0) {
			System.out.println(errMsg);
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errCode);
		}
		return sb;
	}
	
	public static StringBuilder getWalletDashboardData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang, String ipAddress) {
		StringBuilder sb = new StringBuilder();
		String errCode = "";
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String compName = JSONHelper.getValue(jsonBody, "compname");
				String token = JSONHelper.getValue(jsonBody, "token");
				String username = JSONHelper.getValue(jsonBody, "username");
 
				
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Error loading client";
					errCode = "9001";
					break;
				}
				
 
				CompanyUserSession companyUserSession = CompanyUserSessionDao.loadUserSessionByToken(connection, compid, token);
				if(companyUserSession==null||companyUserSession.getUserid()<=0) {
					errMsg = "Error loading Client Session";
					errCode = "9003";
					break;
				}
				long tokenExpire = companyUserSession.getExpiretimemillis();
				long currentTime = System.currentTimeMillis();
				System.out.println("tokenExpire: "+tokenExpire);
				System.out.println("currentTime: "+currentTime);
 
				if (tokenExpire < currentTime) {
					errMsg = "Token has expired";
					errCode = "9003";
					break;
				} 
				
				int totalClients = CompanyClientDao.totalClientsInComp(connection, compid);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
				int totalTransactions = ClientInvoiceDao.getClientInvoiceQuantity(connection, compid, formatter.format(DateUtil.getStartOfMonth()), formatter.format(DateUtil.getCurrentCCYYMMDDDate()), 0);
				double totalDebt = ClientCompanyBalanceDao.getTotalPositiveBalances(connection, compid, 5205);
 
				double totalOwing = ClientCompanyBalanceDao.getTotalNegativeBalances(connection, compid, 5205);
				
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("totalclients", totalClients);
				json.put("totaltransactions", totalTransactions);
				json.put("totaldebt", totalDebt);
				json.put("totalowing", totalOwing);
				sb.append(json);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getWalletDashboardData -> " + e.toString());
			errMsg = "Server Error";
			errCode = "9200";
		}
		if(errCode.length()>0) {
			System.out.println(errMsg);
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errCode);
		}
		return sb;
	}

	public static StringBuilder getSalesTableData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang, String ipAddress) {
		StringBuilder sb = new StringBuilder();
		String errCode = "";
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String fromDate = JSONHelper.getValue(jsonBody, "fromdate");
				String toDate = JSONHelper.getValue(jsonBody, "todate");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String compName = JSONHelper.getValue(jsonBody, "compname");
				String token = JSONHelper.getValue(jsonBody, "token");
				String username = JSONHelper.getValue(jsonBody, "username");
				int prodid = JSONHelper.getIntValue(jsonBody, "prodid");
				
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Error loading client";
					errCode = "9001";
					break;
				}
				
				if(prodid==0) {
					prodid = Util.parseInt(CompanyParamDao.getCompParamValue(connection, compid, "defaultwalletproduct"), 0);
				}
				if(prodid<=0) {
					errMsg = "Invalid Prod ID";
					errCode = "9004";
					break;
				}
 
				CompanyUserSession companyUserSession = CompanyUserSessionDao.loadUserSessionByToken(connection, compid, token);
				if(companyUserSession==null||companyUserSession.getUserid()<=0) {
					errMsg = "Error loading Client Session";
					errCode = "9003";
					break;
				}
				long tokenExpire = companyUserSession.getExpiretimemillis();
				long currentTime = System.currentTimeMillis();
 
				if (tokenExpire < currentTime) {
					errMsg = "Token has expired";
					errCode = "9003";
					break;
				} 
				
				ArrayList<ClientInvoice> clientInvoices = ClientInvoiceDao.getClientInvoiceSalesHistory(connection, toDate, fromDate, compid, compid);
				JSONArray jsonClientInvoices = ClientInvoiceDao.toJSONArray(clientInvoices);

				
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("clientinvoices", jsonClientInvoices);
				sb.append(json);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getSalesTableData -> " + e.toString());
			errMsg = "Server Error";
			errCode = "9200";
		}
		if(errCode.length()>0) {
			System.out.println(errMsg);
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errCode);
		}
		return sb;
	}
	
	
	public static StringBuilder getRechargesTableData(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang, String ipAddress) {
		StringBuilder sb = new StringBuilder();
		String errCode = "";
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				String fromDate = JSONHelper.getValue(jsonBody, "fromdate");
				String toDate = JSONHelper.getValue(jsonBody, "todate");
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String compName = JSONHelper.getValue(jsonBody, "compname");
				String token = JSONHelper.getValue(jsonBody, "token");
				String username = JSONHelper.getValue(jsonBody, "username");
				
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Error loading client";
					errCode = "9001";
					break;
				}
				
 
				CompanyUserSession companyUserSession = CompanyUserSessionDao.loadUserSessionByToken(connection, compid, token);
				if(companyUserSession==null||companyUserSession.getUserid()<=0) {
					errMsg = "Error loading Client Session";
					errCode = "9003";
					break;
				}
				long tokenExpire = companyUserSession.getExpiretimemillis();
				long currentTime = System.currentTimeMillis();
 
				if (tokenExpire < currentTime) {
					errMsg = "Token has expired";
					errCode = "9003";
					break;
				} 
				
//				JSONArray rechargeData = ExtTranDAO;
				
				JSONObject json = new JSONObject();
				json.put("status", "ok");
//				json.put("rechargeData", rechargeData);
				sb.append(json);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: getRechargesTableData -> " + e.toString());
			errMsg = "Server Error";
			errCode = "9200";
		}
		if(errCode.length()>0) {
			System.out.println(errMsg);
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errCode);
		}
		return sb;
	}
	
	public static StringBuilder logoutCommpanyUser(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang, String ipAddress) {
		StringBuilder sb = new StringBuilder();
		String errCode = "";
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String token = JSONHelper.getValue(jsonBody, "token");
				
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Error loading client";
					errCode = "9001";
					break;
				}
				
				String compWebRef = company.getCompWebRef();
				
				CompanyUserSessionDao.deactivateUserSessionsToken(connection, compid, token);
				
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("compwebref", compWebRef);
 				sb.append(json);
				
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: logoutCommpanyUser -> " + e.toString());
			errMsg = "Server Error";
			errCode = "9200";
		}
		if(errCode.length()>0) {
			System.out.println(errMsg);
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errCode);
		}
		return sb;
	}
	
	public static StringBuilder checkWebToken(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection, String logdir, String defaultLang, String ipAddress) {
		StringBuilder sb = new StringBuilder();
		String errCode = "";
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				int compid = JSONHelper.getIntValue(jsonBody, "compid");
				String token = JSONHelper.getValue(jsonBody, "token");
				
				Company company = CompanyDao.getCompany(compid, connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Error loading client";
					errCode = "9001";
					break;
				}
				
				String compWebRef = company.getCompWebRef();
				
				CompanyUserSession companyUserSession = CompanyUserSessionDao.loadUserSessionByToken(connection, compid, token);
				
				long tokenExpire = companyUserSession.getExpiretimemillis();
				long currentTime = System.currentTimeMillis();
 				
				boolean active = (companyUserSession.getStatus() == CompanyUserSession.STATUS_ACTIVE ? true : false) && (tokenExpire > currentTime);
						
				JSONObject json = new JSONObject();
				json.put("status", "ok");
				json.put("tokenactive", active);
				json.put("compwebref", compWebRef);
 				sb.append(json);
				
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: checkWebToken -> " + e.toString());
			errMsg = "Server Error";
			errCode = "9200";
		}
		if(errCode.length()>0) {
			System.out.println(errMsg);
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errCode);
		}
		return sb;
	}
}

