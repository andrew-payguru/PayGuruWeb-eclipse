package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.model.Company;
import za.co.payguru.model.CompanyClient;
import za.co.payguru.model.CompanyProduct;
import za.co.payguru.model.CompanyUser;
import za.co.payguru.util.DBUtil;

public class CompanyDao extends TemplateDao{

	public static int getNextCompId(Connection connection) {
		int id = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('companys_compid_seq') AS compid");
				){
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				id = rs.getInt("compid");
			rs.close();
		}catch (Exception e) {
			System.out.println("Error querying sequence: " + e.toString());
		}
		return id;
	}

	public static Company addCompany(Connection connection, Company company) {
		String sql = "INSERT INTO COMPANYS (" +
				"compid, compname, comptelno, compcellno, compvatno, compregno, " +
				"compemail, compwebsite, compphysaddr1, compphysaddr2, compphysaddr3, " +
				"compphystown, compphysprov, compphyspostcode, comppostaddr1, comppostaddr2, " +
				"comppostaddr3, compposttown, comppostprov, comppostcode, compjoinname, " +
				"compcontname, compcontsurname, compcontemail, compcontcellno, compconttelno, " +
				"compdefprodid, compsysuserid, compsyspassword, compactive, compref, " +
				"compsmssendref, compsmsmessage1, compinvprefix, comptaxperc, comppayinterfaceid, " +
				"compbankentity, compshortenurl, compshortendir) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		company.setCompId(getNextCompId(connection));
		company.setCompActive(Company.COMP_ACTIVE);
		try (
				PreparedStatement statement = connection.prepareStatement(sql);
				) {
			statement.setInt(1, company.getCompId());
			statement.setString(2, company.getCompName());
			statement.setString(3, company.getCompTelNo());
			statement.setString(4, company.getCompCellNo());
			statement.setString(5, company.getCompVatNo());
			statement.setString(6, company.getCompRegNo());
			statement.setString(7, company.getCompEmail());
			statement.setString(8, company.getCompWebsite());
			statement.setString(9, company.getCompPhysAddr1());
			statement.setString(10, company.getCompPhysAddr2());
			statement.setString(11, company.getCompPhysAddr3());
			statement.setString(12, company.getCompPhysTown());
			statement.setString(13, company.getCompPhysProv());
			statement.setString(14, company.getCompPhysPostCode());
			statement.setString(15, company.getCompPostAddr1());
			statement.setString(16, company.getCompPostAddr2());
			statement.setString(17, company.getCompPostAddr3());
			statement.setString(18, company.getCompPostTown());
			statement.setString(19, company.getCompPostProv());
			statement.setString(20, company.getCompPostCode());
			statement.setString(21, company.getCompJoinName());
			statement.setString(22, company.getCompContName());
			statement.setString(23, company.getCompContSurname());
			statement.setString(24, company.getCompContEmail());
			statement.setString(25, company.getCompContCellNo());
			statement.setString(26, company.getCompContTelNo());
			statement.setInt(27, company.getCompDefProdId());
			statement.setString(28, company.getCompSysUserId());
			statement.setString(29, company.getCompSysPassword());
			statement.setString(30, company.getCompActive());
			statement.setString(31, company.getCompRef());
			statement.setString(32, company.getCompSmsSendRef());
			statement.setString(33, company.getCompSmsMessage1());
			statement.setString(34, company.getCompInvPrefix());
			statement.setDouble(35, company.getCompTaxPerc());
			statement.setString(36, company.getCompPayInterfaceId());
			statement.setString(37, company.getCompBankEntity());
			statement.setString(38, company.getCompShortenUrl());
			statement.setString(39, company.getCompShortenDir());
			statement.setString(40,	company.getCompWebRef());

			if(statement.executeUpdate()<=0)
				company = new Company();
		} catch (SQLException e) {
			System.out.println("Error updating COMPANYS: " + e.toString());
		}
		return company;
	}

	public static boolean updateCompany(Connection connection, Company company) {
		String sql = "UPDATE COMPANYS SET " +
				"compname = ?, comptelno = ?, compcellno = ?, compvatno = ?, compregno = ?, " +
				"compemail = ?, compwebsite = ?, compphysaddr1 = ?, compphysaddr2 = ?, compphysaddr3 = ?, " +
				"compphystown = ?, compphysprov = ?, compphyspostcode = ?, comppostaddr1 = ?, comppostaddr2 = ?, " +
				"comppostaddr3 = ?, compposttown = ?, comppostprov = ?, comppostcode = ?, compjoinname = ?, " +
				"compcontname = ?, compcontsurname = ?, compcontemail = ?, compcontcellno = ?, compconttelno = ?, " +
				"compdefprodid = ?, compsysuserid = ?, compsyspassword = ?, compactive = ?, compref = ?, " +
				"compsmssendref = ?, compsmsmessage1 = ?, compinvprefix = ?, comptaxperc = ?, comppayinterfaceid = ?, " +
				"compbankentity = ?, compshortenurl = ?, compshortendir = ?, compwebref = ? " +
				"WHERE compid = ?";

		try (
				PreparedStatement statement = connection.prepareStatement(sql);
				) {
			statement.setString(1, company.getCompName());
			statement.setString(2, company.getCompTelNo());
			statement.setString(3, company.getCompCellNo());
			statement.setString(4, company.getCompVatNo());
			statement.setString(5, company.getCompRegNo());
			statement.setString(6, company.getCompEmail());
			statement.setString(7, company.getCompWebsite());
			statement.setString(8, company.getCompPhysAddr1());
			statement.setString(9, company.getCompPhysAddr2());
			statement.setString(10, company.getCompPhysAddr3());
			statement.setString(11, company.getCompPhysTown());
			statement.setString(12, company.getCompPhysProv());
			statement.setString(13, company.getCompPhysPostCode());
			statement.setString(14, company.getCompPostAddr1());
			statement.setString(15, company.getCompPostAddr2());
			statement.setString(16, company.getCompPostAddr3());
			statement.setString(17, company.getCompPostTown());
			statement.setString(18, company.getCompPostProv());
			statement.setString(19, company.getCompPostCode());
			statement.setString(20, company.getCompJoinName());
			statement.setString(21, company.getCompContName());
			statement.setString(22, company.getCompContSurname());
			statement.setString(23, company.getCompContEmail());
			statement.setString(24, company.getCompContCellNo());
			statement.setString(25, company.getCompContTelNo());
			statement.setInt(26, company.getCompDefProdId());
			statement.setString(27, company.getCompSysUserId());
			statement.setString(28, company.getCompSysPassword());
			statement.setString(29, company.getCompActive());
			statement.setString(30, company.getCompRef());
			statement.setString(31, company.getCompSmsSendRef());
			statement.setString(32, company.getCompSmsMessage1());
			statement.setString(33, company.getCompInvPrefix());
			statement.setDouble(34, company.getCompTaxPerc());
			statement.setString(35, company.getCompPayInterfaceId());
			statement.setString(36, company.getCompBankEntity());
			statement.setString(37, company.getCompShortenUrl());
			statement.setString(38, company.getCompShortenDir());
			statement.setString(39, company.getCompWebRef());
			statement.setInt(40, company.getCompId());

			int rowsUpdated = statement.executeUpdate();
			return rowsUpdated > 0;

		} catch (SQLException e) {
			System.out.println("Error updating COMPANYS: " + e.toString());
			return false;
		}
	}

	public static Company getCompany(int compId,Connection connection) {
		Company company = new Company();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYS WHERE compid = ?");
				){
			statement.setInt(1, compId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				company.setCompId(rs.getInt("compid"));
				company.setCompName(rs.getString("compname"));
				company.setCompTelNo(rs.getString("comptelno"));
				company.setCompCellNo(rs.getString("compcellno"));
				company.setCompVatNo(rs.getString("compvatno"));
				company.setCompRegNo(rs.getString("compregno"));
				company.setCompEmail(rs.getString("compemail"));
				company.setCompWebsite(rs.getString("compwebsite"));
				company.setCompPhysAddr1(rs.getString("compphysaddr1"));
				company.setCompPhysAddr2(rs.getString("compphysaddr2"));
				company.setCompPhysAddr3(rs.getString("compphysaddr3"));
				company.setCompPhysTown(rs.getString("compphystown"));
				company.setCompPhysProv(rs.getString("compphysprov"));
				company.setCompPhysPostCode(rs.getString("compphyspostcode"));
				company.setCompPostAddr1(rs.getString("comppostaddr1"));
				company.setCompPostAddr2(rs.getString("comppostaddr2"));
				company.setCompPostAddr3(rs.getString("comppostaddr3"));
				company.setCompPostTown(rs.getString("compposttown"));
				company.setCompPostProv(rs.getString("comppostprov"));
				company.setCompPostCode(rs.getString("comppostcode"));
				company.setCompJoinName(rs.getString("compjoinname"));
				company.setCompContName(rs.getString("compcontname"));
				company.setCompContSurname(rs.getString("compcontsurname"));
				company.setCompContEmail(rs.getString("compcontemail"));
				company.setCompContCellNo(rs.getString("compcontcellno"));
				company.setCompContTelNo(rs.getString("compconttelno"));
				company.setCompDefProdId(rs.getInt("compdefprodid"));
				company.setCompSysUserId(rs.getString("compsysuserid"));
				company.setCompSysPassword(rs.getString("compsyspassword"));
				company.setCompActive(rs.getString("compactive"));
				company.setCompRef(rs.getString("compref"));
				company.setCompSmsSendRef(rs.getString("compsmssendref"));
				company.setCompSmsMessage1(rs.getString("compsmsmessage1"));
				company.setCompInvPrefix(rs.getString("compinvprefix"));
				company.setCompTaxPerc(rs.getDouble("comptaxperc"));
				company.setCompPayInterfaceId(rs.getString("comppayinterfaceid"));
				company.setCompBankEntity(rs.getString("compbankentity"));
				company.setCompShortenUrl(rs.getString("compshortenurl"));
				company.setCompShortenDir(rs.getString("compshortendir"));
				company.setCompWebRef(rs.getString("compwebref"));
			}rs.close();
		}catch(Exception e) {
			System.out.println("Error querying COMPANY: " + e.toString());
		}
		return company;
	}

	public static Company loadCompanyByWebRef(String webRef,Connection connection) {
		Company company = new Company();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYS WHERE compwebref = ?");
				){
			statement.setString(1, webRef);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				company.setCompId(rs.getInt("compid"));
				company.setCompName(rs.getString("compname"));
				company.setCompTelNo(rs.getString("comptelno"));
				company.setCompCellNo(rs.getString("compcellno"));
				company.setCompVatNo(rs.getString("compvatno"));
				company.setCompRegNo(rs.getString("compregno"));
				company.setCompEmail(rs.getString("compemail"));
				company.setCompWebsite(rs.getString("compwebsite"));
				company.setCompPhysAddr1(rs.getString("compphysaddr1"));
				company.setCompPhysAddr2(rs.getString("compphysaddr2"));
				company.setCompPhysAddr3(rs.getString("compphysaddr3"));
				company.setCompPhysTown(rs.getString("compphystown"));
				company.setCompPhysProv(rs.getString("compphysprov"));
				company.setCompPhysPostCode(rs.getString("compphyspostcode"));
				company.setCompPostAddr1(rs.getString("comppostaddr1"));
				company.setCompPostAddr2(rs.getString("comppostaddr2"));
				company.setCompPostAddr3(rs.getString("comppostaddr3"));
				company.setCompPostTown(rs.getString("compposttown"));
				company.setCompPostProv(rs.getString("comppostprov"));
				company.setCompPostCode(rs.getString("comppostcode"));
				company.setCompJoinName(rs.getString("compjoinname"));
				company.setCompContName(rs.getString("compcontname"));
				company.setCompContSurname(rs.getString("compcontsurname"));
				company.setCompContEmail(rs.getString("compcontemail"));
				company.setCompContCellNo(rs.getString("compcontcellno"));
				company.setCompContTelNo(rs.getString("compconttelno"));
				company.setCompDefProdId(rs.getInt("compdefprodid"));
				company.setCompSysUserId(rs.getString("compsysuserid"));
				company.setCompSysPassword(rs.getString("compsyspassword"));
				company.setCompActive(rs.getString("compactive"));
				company.setCompRef(rs.getString("compref"));
				company.setCompSmsSendRef(rs.getString("compsmssendref"));
				company.setCompSmsMessage1(rs.getString("compsmsmessage1"));
				company.setCompInvPrefix(rs.getString("compinvprefix"));
				company.setCompTaxPerc(rs.getDouble("comptaxperc"));
				company.setCompPayInterfaceId(rs.getString("comppayinterfaceid"));
				company.setCompBankEntity(rs.getString("compbankentity"));
				company.setCompShortenUrl(rs.getString("compshortenurl"));
				company.setCompShortenDir(rs.getString("compshortendir"));
				company.setCompWebRef(rs.getString("compwebref"));
			}rs.close();
		}catch(Exception e) {
			System.out.println("Error querying COMPANY: " + e.toString());
		}
		return company;
	}

	

	public static ArrayList<Company> getAppCompatCompanys(Connection connection) {
		ArrayList<Company> companys = new ArrayList<Company>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYS WHERE compid IN (SELECT compid FROM COMPANYPARAMS WHERE paramid = ?) AND compactive = ?");
				){
			statement.setString(1, "clientappcompat");
			statement.setString(2, Company.COMP_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Company company = new Company();
				company.setCompId(rs.getInt("compid"));
				company.setCompName(rs.getString("compname"));
				company.setCompTelNo(rs.getString("comptelno"));
				company.setCompCellNo(rs.getString("compcellno"));
				company.setCompVatNo(rs.getString("compvatno"));
				company.setCompRegNo(rs.getString("compregno"));
				company.setCompEmail(rs.getString("compemail"));
				company.setCompWebsite(rs.getString("compwebsite"));
				company.setCompPhysAddr1(rs.getString("compphysaddr1"));
				company.setCompPhysAddr2(rs.getString("compphysaddr2"));
				company.setCompPhysAddr3(rs.getString("compphysaddr3"));
				company.setCompPhysTown(rs.getString("compphystown"));
				company.setCompPhysProv(rs.getString("compphysprov"));
				company.setCompPhysPostCode(rs.getString("compphyspostcode"));
				company.setCompPostAddr1(rs.getString("comppostaddr1"));
				company.setCompPostAddr2(rs.getString("comppostaddr2"));
				company.setCompPostAddr3(rs.getString("comppostaddr3"));
				company.setCompPostTown(rs.getString("compposttown"));
				company.setCompPostProv(rs.getString("comppostprov"));
				company.setCompPostCode(rs.getString("comppostcode"));
				company.setCompJoinName(rs.getString("compjoinname"));
				company.setCompContName(rs.getString("compcontname"));
				company.setCompContSurname(rs.getString("compcontsurname"));
				company.setCompContEmail(rs.getString("compcontemail"));
				company.setCompContCellNo(rs.getString("compcontcellno"));
				company.setCompContTelNo(rs.getString("compconttelno"));
				company.setCompDefProdId(rs.getInt("compdefprodid"));
				company.setCompSysUserId(rs.getString("compsysuserid"));
				company.setCompSysPassword(rs.getString("compsyspassword"));
				company.setCompActive(rs.getString("compactive"));
				company.setCompRef(rs.getString("compref"));
				company.setCompSmsSendRef(rs.getString("compsmssendref"));
				company.setCompSmsMessage1(rs.getString("compsmsmessage1"));
				company.setCompInvPrefix(rs.getString("compinvprefix"));
				company.setCompTaxPerc(rs.getDouble("comptaxperc"));
				company.setCompPayInterfaceId(rs.getString("comppayinterfaceid"));
				company.setCompBankEntity(rs.getString("compbankentity"));
				company.setCompShortenUrl(rs.getString("compshortenurl"));
				company.setCompShortenDir(rs.getString("compshortendir"));
				company.setCompWebRef(rs.getString("compwebref"));
				companys.add(company);
			}rs.close();
		}catch(Exception e) {
			System.out.println("Error querying COMPANY: " + e.toString());
		}
		return companys;
	}

	
	public static ArrayList<Company> getClientWalletCompanys(Connection connection, int clientid) {
		ArrayList<Company> companys = new ArrayList<Company>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYS WHERE compid IN (SELECT compid FROM COMPANYPRODUCTS WHERE (prodtype = ? OR prodtype = ?) AND prodactive = ?) AND compid IN (SELECT compid FROM COMPANYCLIENTS WHERE clientid = ? AND status = ?) AND compactive = ?");
				){
			statement.setInt(1, CompanyProduct.TYPE_WALLET);
			statement.setInt(2, CompanyProduct.TYPE_LOYALTY_WALLET);
			statement.setString(3, CompanyProduct.PROD_ACTIVE);
			statement.setInt(4, clientid);
			statement.setString(5, CompanyClient.COMP_CLIENT_ACTIVE);
			statement.setString(6, Company.COMP_ACTIVE);


			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Company company = new Company();
				company.setCompId(rs.getInt("compid"));
				company.setCompName(rs.getString("compname"));
				company.setCompTelNo(rs.getString("comptelno"));
				company.setCompCellNo(rs.getString("compcellno"));
				company.setCompVatNo(rs.getString("compvatno"));
				company.setCompRegNo(rs.getString("compregno"));
				company.setCompEmail(rs.getString("compemail"));
				company.setCompWebsite(rs.getString("compwebsite"));
				company.setCompPhysAddr1(rs.getString("compphysaddr1"));
				company.setCompPhysAddr2(rs.getString("compphysaddr2"));
				company.setCompPhysAddr3(rs.getString("compphysaddr3"));
				company.setCompPhysTown(rs.getString("compphystown"));
				company.setCompPhysProv(rs.getString("compphysprov"));
				company.setCompPhysPostCode(rs.getString("compphyspostcode"));
				company.setCompPostAddr1(rs.getString("comppostaddr1"));
				company.setCompPostAddr2(rs.getString("comppostaddr2"));
				company.setCompPostAddr3(rs.getString("comppostaddr3"));
				company.setCompPostTown(rs.getString("compposttown"));
				company.setCompPostProv(rs.getString("comppostprov"));
				company.setCompPostCode(rs.getString("comppostcode"));
				company.setCompJoinName(rs.getString("compjoinname"));
				company.setCompContName(rs.getString("compcontname"));
				company.setCompContSurname(rs.getString("compcontsurname"));
				company.setCompContEmail(rs.getString("compcontemail"));
				company.setCompContCellNo(rs.getString("compcontcellno"));
				company.setCompContTelNo(rs.getString("compconttelno"));
				company.setCompDefProdId(rs.getInt("compdefprodid"));
				company.setCompSysUserId(rs.getString("compsysuserid"));
				company.setCompSysPassword(rs.getString("compsyspassword"));
				company.setCompActive(rs.getString("compactive"));
				company.setCompRef(rs.getString("compref"));
				company.setCompSmsSendRef(rs.getString("compsmssendref"));
				company.setCompSmsMessage1(rs.getString("compsmsmessage1"));
				company.setCompInvPrefix(rs.getString("compinvprefix"));
				company.setCompTaxPerc(rs.getDouble("comptaxperc"));
				company.setCompPayInterfaceId(rs.getString("comppayinterfaceid"));
				company.setCompBankEntity(rs.getString("compbankentity"));
				company.setCompShortenUrl(rs.getString("compshortenurl"));
				company.setCompShortenDir(rs.getString("compshortendir"));
				company.setCompWebRef(rs.getString("compwebref"));
				companys.add(company);
			}rs.close();
		}catch(Exception e) {
			System.out.println("Error querying COMPANY: " + e.toString());
		}
		return companys;
	}



	//JSON HELPER METHODS
	public static StringBuilder getJsonCompanys(ArrayList<Company> companys) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for(int i=0;i<companys.size();i++) {
			sb.append((i==0 ? "" : ",") + companys.get(i).toJsonString());
		}
		sb.append("]");
		return sb;		
	}
	
	public static JSONArray getJSONArray(ArrayList<Company> companys) {
		JSONArray jsonAr = new JSONArray();
		try {
			for(Company company : companys) {
				jsonAr.put(company.toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return jsonAr;
	}
	public static StringBuilder getJsonCompany(Company company) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\"compid\" : "+company.getCompId()+",\n");
		sb.append("\"compname\" : \""+company.getCompName()+"\",\n");
		sb.append("\"comptelno\" : \""+company.getCompTelNo()+"\",\n");
		sb.append("\"compcellno\" : \""+company.getCompCellNo()+"\",\n");
		sb.append("\"compvatno\" : \""+company.getCompVatNo()+"\",\n");
		sb.append("\"compregno\" : \""+company.getCompRegNo()+"\",\n");
		sb.append("\"compemail\" : \""+company.getCompEmail()+"\",\n");
		sb.append("\"compwebsite\" : \""+company.getCompWebsite()+"\",\n");
		sb.append("\"compphysaddr1\" : \""+company.getCompPhysAddr1()+"\",\n");
		sb.append("\"compphysaddr2\" : \""+company.getCompPhysAddr2()+"\",\n");
		sb.append("\"compphysaddr3\" : \""+company.getCompPhysAddr3()+"\",\n");
		sb.append("\"compphystown\" : \""+company.getCompPhysTown()+"\",\n");
		sb.append("\"compphysprov\" : \""+company.getCompPhysProv()+"\",\n");
		sb.append("\"compphyspostcode\" : \""+company.getCompPhysPostCode()+"\",\n");
		sb.append("\"comppostaddr1\" : \""+company.getCompPostAddr1()+"\",\n");
		sb.append("\"comppostaddr2\" : \""+company.getCompPostAddr2()+"\",\n");
		sb.append("\"comppostaddr3\" : \""+company.getCompPostAddr3()+"\",\n");
		sb.append("\"compposttown\" : \""+company.getCompPostTown()+"\",\n");
		sb.append("\"comppostprov\" : \""+company.getCompPostProv()+"\",\n");
		sb.append("\"comppostcode\" : \""+company.getCompPostCode()+"\",\n");
		sb.append("\"compjoinname\" : \""+company.getCompJoinName()+"\",\n");
		sb.append("\"compcontname\" : \""+company.getCompContName()+"\",\n");
		sb.append("\"compcontsurname\" : \""+company.getCompContSurname()+"\",\n");
		sb.append("\"compcontemail\" : \""+company.getCompContEmail()+"\",\n");
		sb.append("\"compcontcellno\" : \""+company.getCompContCellNo()+"\",\n");
		sb.append("\"compconttelno\" : \""+company.getCompContTelNo()+"\",\n");
		sb.append("\"compdefprodid\" : "+company.getCompDefProdId()+",\n");
		sb.append("\"compsysuserid\" : \""+company.getCompSysUserId()+"\",\n");
		sb.append("\"compsyspassword\" : \""+company.getCompSysPassword()+"\",\n");
		sb.append("\"compactive\" : \""+company.getCompActive()+"\",\n");
		sb.append("\"compref\" : \""+company.getCompRef()+"\",\n");
		sb.append("\"compsmssendref\" : \""+company.getCompSmsSendRef()+"\",\n");
		sb.append("\"compsmsmessage1\" : \""+company.getCompSmsMessage1()+"\",\n");
		sb.append("\"compinvprefix\" : \""+company.getCompInvPrefix()+"\",\n");
		sb.append("\"comptaxperc\" : "+company.getCompTaxPerc()+",\n");
		sb.append("\"comppayinterfaceid\" : \""+company.getCompPayInterfaceId()+"\",\n");
		sb.append("\"compbankentity\" : \""+company.getCompBankEntity()+"\",\n");
		sb.append("\"compshortenurl\" : \""+company.getCompShortenUrl()+"\",\n");
		sb.append("\"compshortendir\" : \""+company.getCompShortenDir()+"\",\n");
		sb.append("\"compwebref\" : \""+company.getCompWebRef()+"\"\n");
		sb.append("}");
		return sb;
	}
}
