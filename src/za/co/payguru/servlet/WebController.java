package za.co.payguru.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import za.co.payguru.dao.CompanyDao;
import za.co.payguru.dao.CompanyUserDao;
import za.co.payguru.dao.ParamDao;
import za.co.payguru.dao.WebPageDao;
import za.co.payguru.dao.WebPageGroupDao;
import za.co.payguru.http.HTTPUrlConnectionClient;
import za.co.payguru.model.Company;
import za.co.payguru.model.CompanyUser;
import za.co.payguru.model.WebPage;
import za.co.payguru.model.WebPageGroup;
import za.co.payguru.servlet.process.Process_webapp;
import za.co.payguru.servlet.process.Process_advert;
import za.co.payguru.servlet.process.Process_api;
import za.co.payguru.servlet.process.Process_campaigns;
import za.co.payguru.servlet.process.Process_test;
import za.co.payguru.util.DBUtil;
import za.co.payguru.util.HTTPUtil;
import za.co.payguru.util.IniUtil;
import za.co.payguru.util.JSONHelper;
import za.co.payguru.util.Util;

public class WebController extends HttpServlet{

	public final static int ERROR_CODE_SERVLET = 9001;
	public static String dbName = ""; 
	public static String dbUser = "";
	public static String dbPass = "";
	public static String dbNameAdvert = "";
	public static String dbUserAdvert = "";
	public static String dbPassAdvert = "";
	public static String advertServerIp = "";
	public static String defaultLang = ""; 
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
		advertServerIp = ini.getValue("advertserverip");
		defaultLang = ini.getValue("defaultlang");
		if(defaultLang==null||defaultLang.length()<=0)
			defaultLang = "ENG";
		Enumeration en = this.getServletConfig().getInitParameterNames();
		while(en.hasMoreElements()) {
			String name = en.nextElement().toString().trim();
			String value = this.getServletConfig().getInitParameter(name);
			if(name.equalsIgnoreCase("apiforwardrequest"))
				apiForwardRequest = Util.parseBoolean(value);
			if(name.equalsIgnoreCase("apiforwardrequesturl"))
				apiForwardRequestUrl = value;
		}
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String auth = req.getHeader("Authorization");
//		String credentials = auth
		
		String action = req.getParameter("action");
		String origincode = req.getParameter("weborigincode");
		System.out.println(action + " : " + req.getContentType());
		PrintWriter pw = null;
		StringBuilder sb = new StringBuilder();
		JSONObject jsonBody = null; 
		
		resp.setContentType("application/json");
		
		for(int z=0;z<1;z++) {
			
			if(apiForwardRequest) {
				System.out.println("REDIRECT: " + apiForwardRequest);
				String redirectUrl = apiForwardRequestUrl;
				HTTPUrlConnectionClient.redirectRequest(req, resp, redirectUrl);
				return;
				
			}
			
			try(
					Connection connection = DBUtil.getConnection(dbName,dbUser,dbPass);
					Connection advertConnection = DBUtil.getConnection(dbNameAdvert,dbUser,dbPass);
			){
				pw = resp.getWriter();
				jsonBody = JSONHelper.getJsonBody(req);
				//logging
				if(jsonBody!=null) {
					Iterator<String> keys = jsonBody.keys(); 
					while(keys.hasNext()){
						String key = keys.next();
						Object value = jsonBody.get(key);
						System.out.println( key +" : " + value);
					}
				}
				System.out.println("CONN : " + connection);
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
				String logdir = ParamDao.getParam("weblogsdir", connection);
				if(action==null || action.length()<=0) {
					sb = JSONHelper.getErrorJson("No Action Specified!");
					resp.setStatus(HTTPUtil.HTTP_BAD_REQUEST);
					break;
				}else {
					if(action.equals("login"))
						sb = processLogin(req,resp,jsonBody,connection);
					else if(action.equals("getcompany"))
						sb = processGetCompany(req,resp,jsonBody,connection);
					else if(action.equals("getcompwebpages"))
						sb = processGetCompanyWebPages(req,resp,jsonBody,connection);
					else if(action.equals("getcompwebpagegroups"))
						sb = processGetCompanyWebPageGroups(req,resp,jsonBody,connection);
					else if(action.equals("getinvoicevspaymentsgraph"))
						sb = processGetInvoiceVsPaymentsGraph(req,resp,jsonBody,connection);
					else if(action.equals("createcustgroup"))
						sb = Process_campaigns.createCustomerGroup(req,resp,jsonBody,connection);
					else if(action.equals("getactivegroups"))
						sb = Process_campaigns.getGroups(req,resp,false,jsonBody,connection);
					else if(action.equals("getinactivegroups"))
						sb = Process_campaigns.getGroups(req,resp,true,jsonBody,connection);
					else if(action.equals("updategroup"))
						sb = Process_campaigns.updateGroup(req,resp,jsonBody,connection);
					else if(action.equals("searchcampaigns"))
						sb = Process_campaigns.searchCampaigns(req,resp,jsonBody,connection);
					else if(action.equals("searchcampaignitems"))
						sb = Process_campaigns.searchCampaignItems(req,resp,jsonBody,connection);
					else if(action.equals("updateCampaignItems"))
						sb = Process_campaigns.updateCampaignItems(req,resp,jsonBody,connection);
					else if(action.equals("getCampaignItem"))
						sb = Process_campaigns.getCampaignItem(req,resp,jsonBody,connection);
					else if(action.equals("getcampaigns"))
						sb = Process_campaigns.getCampaigns(req,resp,connection);
					else if(action.equals("campaignreport"))
						sb = Process_campaigns.processCampaignReport(req,resp,connection);
					else if(action.equals("uploadadvertdatacaptured"))
						sb = Process_advert.capturedata(req,resp,jsonBody,connection,advertConnection,ipAddress);
					else if(action.equals("uploadglow2data"))
						sb = Process_advert.captureGlow2Data(req,resp,jsonBody,connection,advertConnection,ipAddress);
					else if(action.equals("testparams"))
						sb = Process_test.testParams(req,resp);
					else if(action.equals("testjson"))
						sb = Process_test.testJson(req,resp,jsonBody);
					else if(action.equals("test"))
						sb = Process_test.test(req,resp);
					else if(action.equals("getinvdaycount"))
						sb = Process_api.getInvDayCount(req,resp,connection);
					else if(action.equals("register"))
						sb = Process_webapp.processRegister(req,resp,jsonBody,connection);
					else if(action.equals("getsalechannelinvoices"))
						sb = Process_webapp.getSaleChannelInvoices(req,resp,jsonBody,connection);
					else if(action.equals("getsalechannelinvoicedata"))
						sb = Process_webapp.getSaleChannelInvoiceData(req,resp,jsonBody,connection);
					else if(action.equals("getsalechannelinvoicesales"))
						sb = Process_webapp.getSaleChannelInvoiceSales(req,resp,jsonBody,connection);
					else if(action.equals("getsalechannelinvoicedatasales"))
						sb = Process_webapp.getSaleChannelInvoiceDataSales(req,resp,jsonBody,connection);
					else if(action.equals("getcompparam"))
						sb = Process_webapp.getCompanyParam(req,resp,jsonBody,connection);
					else if(action.equals("getcompinternals"))
						sb = Process_webapp.getCompanyInternals(req,resp,jsonBody,connection);
					else if(action.equals("getcompinternalproducts"))
						sb = Process_webapp.getCompanyInternalProducts(req,resp,jsonBody,connection);
					else if(action.equals("getcompbanks"))
						sb = Process_webapp.getCompanyBanks(req,resp,jsonBody,connection);
					else if(action.equals("getclientpayments"))
						sb = Process_webapp.getClientPayments(req,resp,jsonBody,connection);
					else if(action.equals("getpaymentclients")) 
						sb = Process_webapp.getPaymentClients(req,resp,jsonBody,connection);
					else if(action.equals("reverseclientpayment")) 
						sb = Process_webapp.reverseClientPayment(req,resp,jsonBody,connection);
					else if(action.equals("getinactivesmsinvoices")) 
						sb = Process_webapp.getInactiveSmsInvoices(req,resp,jsonBody,connection);
					else if(action.equals("getinactivesmsinvoicedata")) 
						sb = Process_webapp.getInactiveSmsInvoicedata(req,resp,jsonBody,connection);
					else if(action.equals("activatesmspolicy")) 
						sb = Process_webapp.activateSmsPolicy(req,resp,jsonBody,connection);
					else if(action.equals("getparam")) 
						sb = Process_webapp.getParam(req,resp,jsonBody,connection);
					else if(action.equals("postproduct")) 
						sb = Process_webapp.postProduct(req,resp,jsonBody,connection);
					else if(action.equals("searchcompproducts")) 
						sb = Process_webapp.searchCompProducts(req,resp,jsonBody,connection);
					else if(action.equals("updatecompproduct")) 
						sb = Process_webapp.updateCompProduct(req,resp,jsonBody,connection);
					else if(action.equals("searchcompanyclients")) 
						sb = Process_webapp.searchCompanyClients(req,resp,jsonBody,connection);
					else if(action.equals("searchcompanyclientbalances"))
						sb = Process_webapp.searchCompanyClientBalances(req,resp,jsonBody,connection);
					else if(action.equals("searchcompanyclient")) 
						sb = Process_webapp.searchCompanyClient(req,resp,jsonBody,connection);
					else if(action.equals("updateclientextended")) 
						sb = Process_webapp.updateClientExtended(req,resp,jsonBody,connection);
					else if(action.equals("updateclientblacklist")) 
						sb = Process_webapp.updateBlacklistCustomer(req,resp,jsonBody,connection);
					else if(action.equals("getcompanyproducts")) 
						sb = Process_webapp.getCompanyProducts(req,resp,jsonBody,connection);
					else if(action.equals("searchblacklistedcustomers")) 
						sb = Process_webapp.searchBlacklistedClients(req,resp,jsonBody,connection);
					else if(action.equals("getcompanyadverts")) 
						sb = Process_webapp.getCompanyAdverts(req,resp,jsonBody,connection);
					else if(action.equals("searchinvoicesprodrefs")) 
						sb = Process_webapp.searchLatestInvoiceByProdRef(req,resp,jsonBody,connection);
					else if(action.equals("uploadprodrefblacklist")) 
						sb = Process_webapp.uploadProdRefBlacklist(req,resp,jsonBody,connection);
					else if(action.equals("searchclientinvoicesbyprod"))
						sb = Process_webapp.searchInvoicesByProd(req,resp,jsonBody,connection);
					else if(action.equals("searchclientsbyinvoicesprod"))
						sb = Process_webapp.searchClientsByInvoicesProd(req,resp,jsonBody,connection);
					else if(action.equals("searchuserprodinvoices"))
						sb = Process_webapp.searchUserProdInvoicesCell(req,resp,jsonBody,connection);
					else if(action.equals("searchclientbycell"))
						sb = Process_webapp.searchClientByCell(req,resp,jsonBody,connection);
					else if(action.equals("searchcompclientbycell"))
						sb = Process_webapp.searchCompClientByCell(req,resp,jsonBody,connection);
					else if(action.equals("loadappclient"))
						sb = Process_webapp.loadAppClient(req,resp,jsonBody,connection);
					else if(action.equals("createappclient"))
						sb = Process_webapp.createAppClient(req,resp,jsonBody,connection);
					else if(action.equals("registerclient"))
						sb = Process_webapp.registerClient(req,resp,jsonBody,connection);
					else if(action.equals("loginclientapp"))
						sb = Process_webapp.loginClientApp(req,resp,jsonBody,connection);
					else if(action.equals("getappcompatcompanys"))
						sb = Process_webapp.getAppCompatCompanys(req,resp,jsonBody,connection);
					else if(action.equals("getclientcompids"))
						sb = Process_webapp.getClientCompIds(req,resp,jsonBody,connection);
					else if(action.equals("getrecentcompclientclientinvoices"))
						sb = Process_webapp.getRecentCompClientClientInvoices(req,resp,jsonBody,connection);
					else if(action.equals("loadclientinvoicedata"))
						sb = Process_webapp.loadClientInvoiceData(req,resp,jsonBody,connection);
					else if(action.equals("loadclientinvoice"))
						sb = Process_webapp.loadClientInvoice(req,resp,jsonBody,connection);
					else if(action.equals("loadcompparam"))
						sb = Process_webapp.loadCompParamValue(req,resp,jsonBody,connection);
					else if(action.equals("loadclientinvoices"))
						sb = Process_webapp.loadClientInvoices(req,resp,jsonBody,connection);
					else if(action.equals("getpolicylink"))
						sb = Process_webapp.getPolicyLink(req,resp,jsonBody,connection);
					else if(action.equals("getactiveclientpayrefs"))
						sb = Process_webapp.getActiveClientPayRefs(req,resp,jsonBody,connection);
					else if(action.equals("getcompanydetails"))
						sb = Process_webapp.getCompanyDetails(req,resp,jsonBody,connection);
					else if(action.equals("createcompanyclient"))
						sb = Process_webapp.createCompanyClient(req,resp,jsonBody,connection);
					else if(action.equals("createcompclientsupportgroup"))
						sb = Process_webapp.createCompClientSupportGroup(req,resp,jsonBody,connection);
					else if(action.equals("updatecompclientsupportgroup"))
						sb = Process_webapp.updateCompClientSupportGroup(req,resp,jsonBody,connection);
					else if(action.equals("getcompclientsupportgroups"))
						sb = Process_webapp.getCompClientSupportGroups(req,resp,jsonBody,connection);
					else if(action.equals("getgroupcompclisupport"))
						sb = Process_webapp.getGroupCompCliSupport(req,resp,jsonBody,connection);
					else if(action.equals("createclientsupport"))
						sb = Process_webapp.createCompClientSupport(req,resp,jsonBody,connection);
					else if(action.equals("updateclientsupport"))
						sb = Process_webapp.updateCompClientSupport(req,resp,jsonBody,connection);
					else if(action.equals("loadprodrefblacklist"))
						sb = Process_webapp.loadProdRefBlacklist(req,resp,jsonBody,connection);
					else if(action.equals("remakepolicy"))
						sb = Process_webapp.remakePolicy(req,resp,jsonBody,connection);
					else if(action.equals("getproductinvoices"))
						sb = Process_webapp.getProductInvoices(req,resp,jsonBody,connection);
					else if(action.equals("deactivateinvoice"))
						sb = Process_webapp.deactivateInvoice(req,resp,jsonBody,connection);
					else if(action.equals("searchpolicys"))
						sb = Process_webapp.searchPolicys(req,resp,jsonBody,connection);
					else if(action.equals("updatepolicy"))
						sb = Process_webapp.updatePolicy(req,resp,jsonBody,connection);
					else if(action.equals("cancelpolicy"))
						sb = Process_webapp.cancelPolicy(req,resp,jsonBody,connection,logdir);
					else if(action.equals("movepolicy"))
						sb = Process_webapp.movePolicy(req,resp,jsonBody,connection,logdir);
					else if(action.equals("createclient"))
						sb = Process_webapp.createClient(req,resp,jsonBody,connection,logdir);
					else if(action.equals("resendpolicylink"))
						sb = Process_webapp.resendPolicyLink(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getclientinvoicesalesdata"))
						sb = Process_webapp.getClientInvoiceSalesData(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getcurrentsalechannelpolicydata"))
						sb = Process_webapp.getCurrentSaleChannelPolicyData(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getprevioussalechannelpolicydata"))
						sb = Process_webapp.getPreviousSaleChannelPolicyData(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getcompanyloginconfig"))
						sb = Process_webapp.getCompanyLoginConfig(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getcurrentproductsalepolicydata"))
						sb = Process_webapp.getCurrentProductSalesPolicyData(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getpreviousproductsalespolicydata"))
						sb = Process_webapp.getPreviousSalesProductPolicyData(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getrenewpolicydata"))
						sb = Process_webapp.getPolicyRenewData(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getpolicymonthlysalescomparison"))
						sb = Process_webapp.getPolicyMonthlySalesComparison(req,resp,jsonBody,connection,logdir);
					else if(action.equals("searchcancelledpolicys"))
						sb = Process_webapp.searchCancelledPolicys(req,resp,jsonBody,connection,logdir);
					else if(action.equals("searchangedpolicys"))
						sb = Process_webapp.searchChangedPolicys(req,resp,jsonBody,connection,logdir);
					else if(action.equals("fetchinvoicequerys"))
						sb = Process_webapp.fetchInvoiceQuerys(req,resp,jsonBody,connection,logdir);
					else if(action.equals("createinvoicequery"))
						sb = Process_webapp.createInvoiceQuery(req,resp,jsonBody,connection,logdir);
					else if(action.equals("updateinvoicequery"))
						sb = Process_webapp.updateInvoiceQuery(req,resp,jsonBody,connection,logdir);
					else if(action.equals("deleteinvoicequery"))
						sb = Process_webapp.deletInvoiceQuery(req,resp,jsonBody,connection,logdir);
					else if(action.equals("searchpolicyclaims"))
						sb = Process_webapp.searchInvoiceQuerys(req,resp,jsonBody,connection,logdir);
					else if(action.equals("createcompanyloyalty"))
						sb = Process_webapp.createCompanyLoyalty(req,resp,jsonBody,connection,logdir);
					else if(action.equals("searchpolicy"))
						sb = Process_webapp.searchPolicy(req,resp,jsonBody,connection,logdir);
					else if(action.equals("searchclientinvoice"))
						sb = Process_webapp.searchClientInvoice(req,resp,jsonBody,connection,logdir);
					else if(action.equals("searchclientinvoicedata"))
						sb = Process_webapp.searchClientInvoiceData(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getcompanyloyalty"))
						sb = Process_webapp.getCompanyLoyalty(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getloyaltyclientdetails"))
						sb = Process_webapp.getLoyaltyClientDetails(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getclientotp"))
						sb = Process_webapp.getClientOtp(req,resp,jsonBody,connection,logdir);
					else if(action.equals("submitclientotp"))
						sb = Process_webapp.submitClientOtp(req,resp,jsonBody,connection,logdir);
					else if(action.equals("loadloyaltyappuser"))
						sb = Process_webapp.loadLoyaltyAppUser(req,resp,jsonBody,connection,logdir);
					else if(action.equals("registerloyaltyappuser"))
						sb = Process_webapp.registerLoyaltyAppUser(req,resp,jsonBody,connection,logdir);
					else if(action.equals("loginloyaltyappuser"))
						sb = Process_webapp.loginLoyaltyAppUser(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getcomployaltyrewards"))
						sb = Process_webapp.getCompLoyaltyRewards(req,resp,jsonBody,connection,logdir);
					else if(action.equals("loadcompanyclientloyalty"))
						sb = Process_webapp.loadCompanyClientLoyalty(req,resp,jsonBody,connection,logdir);
					else if(action.equals("createsms"))
						sb = Process_webapp.createSms(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getclientloyaltyrewardtokens"))
						sb = Process_webapp.getClientLoyaltyRewardTokens(req,resp,jsonBody,connection,logdir);
					else if(action.equals("getclientloyaltypointsevents"))
						sb = Process_webapp.getClientLoyaltyPointsEvents(req,resp,jsonBody,connection,logdir);
					else if(action.equals("createbulksms"))
						sb = Process_webapp.createBulkSms(req,resp,jsonBody,connection,logdir);
					else if(action.equals("updatecompanyprofile"))
						sb = Process_webapp.updateCompanyProfile(req,resp,jsonBody,connection,logdir);
					else if(action.equals("createcustomer"))
						sb = Process_webapp.createCustomer(req,resp,jsonBody,connection,logdir);
					else if(action.equals("loadcustomerbycell"))
						sb = Process_webapp.loadCustomerByCell(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("linkcustomertoprod"))
						sb = Process_webapp.linkCustomerToProd(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("searchcustomerproducts"))
						sb = Process_webapp.searchCustomerProducts(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("unlinkclientproduct"))
						sb = Process_webapp.unlinkClientProduct(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getwebpagepermissions"))
						sb = Process_webapp.getWebPagePermissions(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getcompwebpagepermissiontemplates"))
						sb = Process_webapp.getCompaWebPagePermissionTemplates(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getcompwebpagepermissiontemplates"))
						sb = Process_webapp.getCompaWebPagePermissionTemplates(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("capturecompanyuser"))
						sb = Process_webapp.captureCompanyUser(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getcompanyusers"))
						sb = Process_webapp.getCompanyUsers(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getcompanyuserprods"))
						sb = Process_webapp.getCompanyUserProds(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("updatecompanyuser"))
						sb = Process_webapp.updateCompanyUser(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getcompclientpaymentsdetailed"))
						sb = Process_webapp.getCompClientPaymentsDetailed(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getcompanybanktrans"))
						sb = Process_webapp.getCompBankTrans(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("registerappclient"))
						sb = Process_webapp.registerWalletAppClient(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getbanks"))
						sb = Process_webapp.getBanks(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getclientbankrefs"))
						sb = Process_webapp.getClientBankRefs(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getclientfintrans"))
						sb = Process_webapp.getClientFinTrans(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getclientwallets"))
						sb = Process_webapp.getClientWallets(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getclientwallettransactions"))
						sb = Process_webapp.getClientWalletTransactions(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getclientdonorwallets")) //getting main beneficiary wallets, per donor
						sb = Process_webapp.getClientDonorWallets(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getavailableclientdonorwallets")) //getting available company wallet products that the client has access to as being a DONORCLIENT
						sb = Process_webapp.getAvailableClientDonorWallets(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getcompanybanks"))
						sb = Process_webapp.getCompanyBanks(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getclientcompanybankrefs"))
						sb = Process_webapp.getClientCompanyBankRefs(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getwallettransferhist"))
						sb = Process_webapp.getWalletTransferHist(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getclientwalletcompanys"))
						sb = Process_webapp.getClientWalletCompanys(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getagentpromoprodselectdata"))
						sb = Process_webapp.getAgentPromoProdSelectData(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getzonesoftwallettranprods"))
						sb = Process_webapp.getZoneSoftWalletTranProds(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getclientcompanywallets"))
						sb = Process_webapp.getClientCompanyWallets(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("searchclientwalletsubaccbyproddata"))
						sb = Process_webapp.searchClientWalletSubAccsLikeProdData(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("getcompanybywebref"))
						sb = Process_webapp.getCompanyByWebRef(req,resp,jsonBody,connection,logdir,defaultLang);
					else if(action.equals("logincompanyuser"))
						sb = Process_webapp.loginCompanyUser(req,resp,jsonBody,connection,logdir,defaultLang,ipAddress);
					else if(action.equals("getwalletdashboarddata"))
						sb = Process_webapp.getWalletDashboardData(req,resp,jsonBody,connection,logdir,defaultLang,ipAddress);	
					else if(action.equals("getsalestabledata"))
						sb = Process_webapp.getSalesTableData(req,resp,jsonBody,connection,logdir,defaultLang,ipAddress);	
					else if(action.equals("getrechargestabledata"))
						sb = Process_webapp.getRechargesTableData(req,resp,jsonBody,connection,logdir,defaultLang,ipAddress);	
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
		if(pw!=null) {
			pw.write(sb.toString());
			pw.close();
		}
	}

	private StringBuilder processGetInvoiceVsPaymentsGraph(HttpServletRequest req, HttpServletResponse resp,JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				Company company = CompanyDao.getCompany(JSONHelper.getIntValue(jsonBody, "compid"),connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company ID ["+company.getCompId()+"] Does Not Exist";
					break;
				}
				
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: GetInvoiceVsPaymentsGraph -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;		
	}

	private StringBuilder processGetCompanyWebPageGroups(HttpServletRequest req, HttpServletResponse resp,JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				Company company = CompanyDao.getCompany(JSONHelper.getIntValue(jsonBody, "compid"),connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company ID ["+company.getCompId()+"] Does Not Exist";
					break;
				}
				ArrayList<WebPageGroup> webPageGroups = WebPageGroupDao.getCompWebPageGroups(company.getCompId(), connection);
				sb = WebPageGroupDao.getJsonWebPageGroups(webPageGroups);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: GetCompanyWebPageGroups -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	private StringBuilder processGetCompanyWebPages(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody,Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				Company company = CompanyDao.getCompany(JSONHelper.getIntValue(jsonBody, "compid"),connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company ID ["+company.getCompId()+"] Does Not Exist";
					break;
				}
				ArrayList<WebPage> webPages = WebPageDao.getCompWebPages(company.getCompId(), connection);
				sb = WebPageDao.getJsonWebPages(webPages);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: GetCompanyWebPages -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}

	private StringBuilder processGetCompany(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {
				Company company = CompanyDao.getCompany(JSONHelper.getIntValue(jsonBody, "compid"),connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company ID ["+company.getCompId()+"] Does Not Exist";
					break;
				}
				sb = CompanyDao.getJsonCompany(company);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: GetCompany -> " + e.toString());
			errMsg = "Server Error";
		}
		if(errMsg.length()>0) {
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;
	}
	private StringBuilder processLogin(HttpServletRequest req, HttpServletResponse resp, JSONObject jsonBody, Connection connection) {
		StringBuilder sb = new StringBuilder();
		String errMsg = "";
		try {
			for(int z=0;z<1;z++) {

				Company company = CompanyDao.getCompany(JSONHelper.getIntValue(jsonBody, "compid"),connection);
				if(company==null||company.getCompId()==0) {
					errMsg = "Company ID ["+company.getCompId()+"] Does Not Exist";
					break;
				}
				CompanyUser companyUser = CompanyUserDao.getCompanyUserEmail(company.getCompId(), JSONHelper.getValue(jsonBody, "useremail"),connection);
				if(companyUser==null||companyUser.getCompId()==0) {
					errMsg = "Username Password Combination Incorrect!";
					break;
				}
				if(companyUser.getUserPassword().equals(JSONHelper.getValue(jsonBody, "password"))==false) {
					errMsg = "Username Password Combination Incorrect!";
					break;
				}

				sb = CompanyUserDao.getJsonCompanyUser(companyUser);
			}
		}catch (Exception e) {
			System.out.println("Server Error Process: Login -> " + e.toString());
			errMsg = "Server Error";
			resp.setStatus(HTTPUtil.HTTP_INTERNAL_SERVLET_ERROR);
		}
		if(errMsg.length()>0) {
			sb = JSONHelper.getErrorJson(errMsg);
		}
		return sb;

	}


	

}
