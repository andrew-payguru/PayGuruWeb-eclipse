package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.CompanyBankTran;

public class CompanyBankTranDao {

	public static long getNextBankTranId(Connection connection) {
		long tranid = 0;
		try (
				Statement statement = connection.createStatement();
		){
			ResultSet rs = statement.executeQuery("SELECT nextval('companybanktrans_tranid_seq') AS tranid");
			if(rs.next()) 
				tranid = rs.getLong("tranid");
			rs.close();
		}catch (Exception e) {
			System.out.println("Error getting companybanktrans_tranid_seq: " + e.toString());
		}
		return tranid;
	}
	public static ArrayList<CompanyBankTran> getCompanyBankTrans(Connection connection, int compid, int bankid, String fromDate, String toDate, String status){
		ArrayList<CompanyBankTran> bankTrans = new ArrayList<CompanyBankTran>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYBANKTRANS WHERE bankcompid = ? AND bankid = ? AND banktrandate >= ? AND banktrandate <= ? AND banktranactive = ? " + ( (status!=null && status.length()>0) ? ("AND banktranstatus = '"+status+"'") : "") + " ORDER BY banktrancreateddate DESC, banktrancreatedtime DESC");
		){
			statement.setInt(1, compid);
			statement.setInt(2, bankid);
			statement.setString(3, fromDate.replace('-', '/'));
			statement.setString(4, toDate.replace('-', '/'));
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyBankTran companyBankTran = new CompanyBankTran();
				companyBankTran.setBankTranId(rs.getLong("banktranid"));
				companyBankTran.setBankCompId(rs.getInt("bankcompid"));
				companyBankTran.setBankId(rs.getInt("bankid"));
				companyBankTran.setBankTranType(rs.getInt("banktrantype"));
				companyBankTran.setBankTranRef(rs.getString("banktranref"));
				companyBankTran.setBankTranRef1(rs.getString("banktranref1"));
				companyBankTran.setBankTranRef2(rs.getString("banktranref2"));
				companyBankTran.setBankTranData(rs.getString("banktrandata"));
				companyBankTran.setBankTranStatus(rs.getString("banktranstatus"));
				companyBankTran.setBankTranStatusDate(rs.getString("banktranstatusdate"));
				companyBankTran.setBankTranStatusTime(rs.getString("banktranstatustime"));
				companyBankTran.setBankTranAmt1(rs.getDouble("banktranamt1"));
				companyBankTran.setBankTranAmt2(rs.getDouble("banktranamt2"));
				companyBankTran.setBankTranPayNo(rs.getDouble("banktranpayno"));
				companyBankTran.setBankTranDate(rs.getString("banktrandate"));
				companyBankTran.setBankTranCreatedDate(rs.getString("banktrancreateddate"));
				companyBankTran.setBankTranCreatedTime(rs.getString("banktrancreatedtime"));
				companyBankTran.setBankTranCredit(rs.getBoolean("banktrancredit"));
				companyBankTran.setBankTranActive(rs.getString("banktranactive"));
				bankTrans.add(companyBankTran);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYBANKTRANS: " + e.toString());
		}
		return bankTrans;
	}
	
	public static CompanyBankTran loadBankTran(Connection connection, int compid, long banktranid) {
		CompanyBankTran companyBankTran = new CompanyBankTran();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYBANKTRANS WHERE banktranid = ? AND bankcompid = ? AND banktrantransferref = '' ");
		){
			statement.setLong(1, banktranid);
			statement.setInt(2, compid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				companyBankTran.setBankTranId(rs.getLong("banktranid"));
				companyBankTran.setBankCompId(rs.getInt("bankcompid"));
				companyBankTran.setBankId(rs.getInt("bankid"));
				companyBankTran.setBankTranType(rs.getInt("banktrantype"));
				companyBankTran.setBankTranRef(rs.getString("banktranref"));
				companyBankTran.setBankTranRef1(rs.getString("banktranref1"));
				companyBankTran.setBankTranRef2(rs.getString("banktranref2"));
				companyBankTran.setBankTranData(rs.getString("banktrandata"));
				companyBankTran.setBankTranStatus(rs.getString("banktranstatus"));
				companyBankTran.setBankTranStatusDate(rs.getString("banktranstatusdate"));
				companyBankTran.setBankTranStatusTime(rs.getString("banktranstatustime"));
				companyBankTran.setBankTranAmt1(rs.getDouble("banktranamt1"));
				companyBankTran.setBankTranAmt2(rs.getDouble("banktranamt2"));
				companyBankTran.setBankTranPayNo(rs.getDouble("banktranpayno"));
				companyBankTran.setBankTranDate(rs.getString("banktrandate"));
				companyBankTran.setBankTranCreatedDate(rs.getString("banktrancreateddate"));
				companyBankTran.setBankTranCreatedTime(rs.getString("banktrancreatedtime"));
				companyBankTran.setBankTranCredit(rs.getBoolean("banktrancredit"));
				companyBankTran.setBankTranActive(rs.getString("banktranactive"));			
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying COMPANYBANKTRANS: " + e.toString());
		}
		return companyBankTran;
	}
	
	
	public static boolean createBankTran(Connection connection, CompanyBankTran companyBankTran) {
		boolean created = false;
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYBANKTRANS (banktranid,bankcompid,bankid,banktrantype,banktranref,banktranref1,banktranref2,banktrandata,banktranstatus,banktranstatusdate,banktranstatustime,banktranamt1,banktranamt2,banktranpayno,banktrandate,banktrancreateddate,banktrancreatedtime,banktrancredit,banktranactive,banktrantransfertranid,banktrantransferref,banktrantransferdesc,banktranstatususer) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")
		){
			statement.setLong(1, getNextBankTranId(connection));
			statement.setInt(2, companyBankTran.getBankCompId());
			statement.setInt(3, companyBankTran.getBankId());
			statement.setInt(4, companyBankTran.getBankTranType());
			statement.setString(5, companyBankTran.getBankTranRef());
			statement.setString(6, companyBankTran.getBankTranRef1());
			statement.setString(7, companyBankTran.getBankTranRef2());
			statement.setString(8, companyBankTran.getBankTranData());
			statement.setString(9, companyBankTran.getBankTranStatus());
			statement.setString(10, companyBankTran.getBankTranStatusDate());
			statement.setString(11, companyBankTran.getBankTranStatusTime());
			statement.setDouble(12, companyBankTran.getBankTranAmt1());
			statement.setDouble(13, companyBankTran.getBankTranAmt2());
			statement.setDouble(14, companyBankTran.getBankTranPayNo());
			statement.setString(15, companyBankTran.getBankTranDate());
			statement.setString(16, companyBankTran.getBankTranCreatedDate());
			statement.setString(17, companyBankTran.getBankTranCreatedTime());
			statement.setBoolean(18, companyBankTran.isBankTranCredit());
			statement.setString(19, companyBankTran.getBankTranActive());
			statement.setLong(20, companyBankTran.getBankTranTransferTranId());
			statement.setString(21, companyBankTran.getBankTranTransferRef());
			statement.setString(22, companyBankTran.getBankTranTransferDesc());
			statement.setInt(23, companyBankTran.getBankTranStatusUser());
			if(statement.executeUpdate()>0)
				created = true;
		}catch (Exception e) {
			System.out.println("Error creating COMPANYBANKTRAN: " + e.toString());
		}
		return created;
	}
	public static boolean reversBankTran(Connection connection, int compid, long tranid, String transferref, int transferuser, String transferdesc) {
		boolean reversed = false;
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE COMPANYBANKTRANS SET banktranstatus = ?, banktrantransferref = ?, banktrantransferdesc = ?, banktranstatususer = ? WHERE bankcompid = ? AND banktranid = ?");
		){
			statement.setString(1, CompanyBankTran.STATUS_REVERSE);
			statement.setString(2, transferref);
			statement.setString(3, transferdesc);
			statement.setInt(4, transferuser);
			statement.setInt(5, compid);
			statement.setLong(6, tranid);
			if(statement.executeUpdate()>0)
				reversed = true;
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYBANKTRANS: " + e.toString());
		}
		return reversed;
	}
	
	public static JSONArray getBankTransJSON(ArrayList<CompanyBankTran> bankTrans) {
		JSONArray jsonAr = new JSONArray();
		try {
			for(int i=0;i<bankTrans.size();i++) {
				jsonAr.put(bankTrans.get(i).toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return jsonAr;
	}
}
