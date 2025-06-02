package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.CompanyBank;

public class CompanyBankDao {
	
	public static CompanyBank loadCompanyBanks(Connection connection, int bankid){
		CompanyBank companyBank = new CompanyBank();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYBANKS WHERE bankid = ? AND bankactive = ?");
		){
			statement.setInt(1, bankid);
			statement.setString(2, CompanyBank.ACTIVE);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				companyBank.setCompId(rs.getInt ("compid"));
				companyBank.setBankType(rs.getInt ("banktype"));
				companyBank.setBankAccNo(rs.getString("bankaccno").trim());
				companyBank.setBankId(rs.getInt("bankid"));
				companyBank.setBankDesc(rs.getString("bankdesc").trim());
				companyBank.setCreateDate(rs.getString ("createdate").trim());
				companyBank.setCreateTime(rs.getString ("createtime").trim());
				companyBank.setSftpActive(rs.getString ("sftpactive").trim());
				companyBank.setSftpIpAddr(rs.getString ("sftpipaddr").trim());
				companyBank.setSftpUser(rs.getString ("sftpuser").trim());
				companyBank.setSftpPass(rs.getString ("sftppass").trim());
				companyBank.setSftpDir(rs.getString ("sftpdir").trim());
				companyBank.setBankExclDescs(rs.getString("bankexcldescs").trim());
				companyBank.setBankInclDescs(rs.getString("bankincldescs").trim());
				companyBank.setBankActive(rs.getString("bankactive").trim());
				companyBank.setBankEmailActive(rs.getString("bankemailactive").trim().equalsIgnoreCase("t") ? true : false);
				companyBank.setBankFileActive(rs.getString("bankfileactive").trim().equalsIgnoreCase("t") ? true : false);
				companyBank.setBankEmailDetails(rs.getString("bankemaildetails").trim());
				companyBank.setBankFileDetails(rs.getString("bankfiledetails").trim());
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return companyBank;
	}

	public static ArrayList<CompanyBank> getCompanyBanks(Connection connection, int compid){
		ArrayList<CompanyBank> banks = new ArrayList<CompanyBank>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYBANKS WHERE compid = ? AND bankactive = ?");
		){
			statement.setInt(1, compid);
			statement.setString(2, CompanyBank.ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyBank companyBank = new CompanyBank();
				companyBank.setCompId(rs.getInt ("compid"));
				companyBank.setBankType(rs.getInt ("banktype"));
				companyBank.setBankAccNo(rs.getString("bankaccno").trim());
				companyBank.setBankId(rs.getInt("bankid"));
				companyBank.setBankDesc(rs.getString("bankdesc").trim());
				companyBank.setCreateDate(rs.getString ("createdate").trim());
				companyBank.setCreateTime(rs.getString ("createtime").trim());
				companyBank.setSftpActive(rs.getString ("sftpactive").trim());
				companyBank.setSftpIpAddr(rs.getString ("sftpipaddr").trim());
				companyBank.setSftpUser(rs.getString ("sftpuser").trim());
				companyBank.setSftpPass(rs.getString ("sftppass").trim());
				companyBank.setSftpDir(rs.getString ("sftpdir").trim());
				companyBank.setBankExclDescs(rs.getString("bankexcldescs").trim());
				companyBank.setBankInclDescs(rs.getString("bankincldescs").trim());
				companyBank.setBankActive(rs.getString("bankactive").trim());
				companyBank.setBankEmailActive(rs.getString("bankemailactive").trim().equalsIgnoreCase("t") ? true : false);
				companyBank.setBankFileActive(rs.getString("bankfileactive").trim().equalsIgnoreCase("t") ? true : false);
				companyBank.setBankEmailDetails(rs.getString("bankemaildetails").trim());
				companyBank.setBankFileDetails(rs.getString("bankfiledetails").trim());
				banks.add(companyBank);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return banks;
	}
	
	public static StringBuilder getCompanyBankJSON(ArrayList<CompanyBank> banks) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<banks.size();i++) {
			CompanyBank bank = banks.get(i);
			sb.append("{\n");
			sb.append("\"compid\" : "+bank.getCompId()+",\n");
			sb.append("\"banktype\" : "+bank.getBankType()+",\n");
			sb.append("\"bankaccno\" : \""+bank.getBankAccNo()+"\",\n");
			sb.append("\"bankid\" : \""+bank.getBankId()+"\",\n");
			sb.append("\"bankdesc\" : \""+bank.getBankDesc()+"\",\n");
			sb.append("\"createdate\" : \""+bank.getCreateDate()+"\",\n");
			sb.append("\"createtime\" : \""+bank.getCreateTime()+"\",\n");
			sb.append("\"sftpactive\" : \""+bank.getSftpActive()+"\",\n");
			sb.append("\"sftpipaddr\" : \""+bank.getSftpIpAddr()+"\",\n");
			sb.append("\"sftpuser\" : \""+bank.getSftpUser()+"\",\n");
			sb.append("\"sftppass\" : \""+bank.getSftpPass()+"\",\n");
			sb.append("\"sftpdir\" : \""+bank.getSftpDir()+"\",\n");
			sb.append("\"bankexcldescs\" : \""+bank.getBankExclDescs()+"\",\n");
			sb.append("\"bankincldescs\" : \""+bank.getBankInclDescs()+"\",\n");
			sb.append("\"bankactive\" : \""+bank.getBankActive()+"\",\n");
			sb.append("\"bankemailactive\" : \""+bank.isBankEmailActive()+"\",\n");
			sb.append("\"bankfileactive\" : \""+bank.isBankFileActive()+"\",\n");
			sb.append("\"bankemaildetails\" : \""+bank.getBankEmailDetails()+"\",\n");
			sb.append("\"bankfiledetails\" : \""+bank.getBankFileDetails()+"\"\n");
			sb.append("}"+(i==banks.size()-1 ? "" : ",")+"\n");
		}
		sb.append("]");
		return sb;
	}
}
