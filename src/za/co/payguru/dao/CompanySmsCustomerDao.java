package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;

import za.co.payguru.model.CompanySmsCustomer;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.Util;

public class CompanySmsCustomerDao {
	public static long getNextCustId(Connection connection) {
		long custId = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('companysmscustomers_custid_seq') AS custid");
				){
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				custId = rs.getLong("custid");
			}
		}catch (Exception e) {
			System.out.println("ERROR UPDATING COMPANYSMSCUSTOMERS: " + e.toString());
		}
		return custId;
	}

	public static CompanySmsCustomer loadSmsCustomerByCell(int compid, String cellNo, Connection connection) {
		CompanySmsCustomer smsCustomer = new CompanySmsCustomer();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYSMSCUSTOMERS WHERE compid = ? AND custcell = ?");
				){
			statement.setInt(1, compid);
			statement.setString(2, cellNo);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				smsCustomer.setCompId(rs.getInt("compid"));
				smsCustomer.setCustCell(rs.getString("custcell"));
				smsCustomer.setCustId(rs.getLong("custid"));
				smsCustomer.setCustName(rs.getString("custname"));
				smsCustomer.setCustSurname(rs.getString("custsurname"));
				smsCustomer.setCustEmail(rs.getString("custemail"));
				smsCustomer.setCustDOB(rs.getDate("custdob"));
				smsCustomer.setCustGender(rs.getInt("custgender"));
				smsCustomer.setCustSuburb(rs.getString("custsuburb"));
				smsCustomer.setCustTown(rs.getString("custtown"));
				smsCustomer.setCustProv(rs.getString("custprov"));
				smsCustomer.setCustCreatedDate(rs.getDate("custcreateddate"));
				smsCustomer.setCustCreatedTime(rs.getTime("custcreatedtime"));
				smsCustomer.setCustCreatedUserId(rs.getInt("custcreateduserid"));
				smsCustomer.setCustActive(rs.getString("custactive"));
			}
		}catch (Exception e) {
			System.out.println("ERROR LOADING COMPANYSMSCUSTOMERS: " + e.toString());
		}
		return smsCustomer;
	}

	public static ArrayList<String> getCustomerCellsByGroups(int compid, String groupIds, Connection connection) {
		ArrayList<String> cellNos = new ArrayList<String>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT custcell FROM COMPANYSMSCUSTOMERS WHERE compid = ? AND custid IN (SELECT custid FROM COMPANYSMSGROUPCUSTOMERS WHERE smsgroupid in ("+groupIds+"))");
				){
			statement.setInt(1, compid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				cellNos.add(rs.getString("custcell"));
			}
		}catch (Exception e) {
			System.out.println("ERROR LOADING COMPANYSMSCUSTOMERS: " + e.toString());
		}
		return cellNos;
	}
	
	public static CompanySmsCustomer createSmsCustomer(CompanySmsCustomer smsCustomer, Connection connection) {
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYSMSCUSTOMERS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		){
			statement.setInt(1, smsCustomer.getCompId());
			statement.setString(2, smsCustomer.getCustCell());
			statement.setLong(3, getNextCustId(connection));
			statement.setString(4, smsCustomer.getCustName());
			statement.setString(5, smsCustomer.getCustSurname());
			statement.setString(6, smsCustomer.getCustEmail());
			statement.setDate(7, smsCustomer.getCustDOB());
			statement.setInt(8, smsCustomer.getCustGender());
			statement.setString(9, smsCustomer.getCustSuburb());
			statement.setString(10, smsCustomer.getCustTown());
			statement.setString(11, smsCustomer.getCustProv());
			statement.setDate(12, DateUtil.getCurrentCCYYMMDDDate());
			statement.setTime(13, DateUtil.getCurrentHHMMSSTime());
			statement.setInt(14, smsCustomer.getCustCreatedUserId());
			statement.setString(15, CompanySmsCustomer.CUST_ACTIVE);
			if(statement.executeUpdate()<=0)
				return new CompanySmsCustomer();
		}catch (Exception e) {
			System.out.println("Error creating COMPANYSMSCUSTOMER : " + e.toString());
		}
		return smsCustomer;
	}
	
	public static boolean uploadCompanySmsCustomersBulk(int compid, int userid, ArrayList<String> bulkData, Connection connection) {
		boolean done = false;
		logic : for(int z=0;z<1;z++) {
			try(
					PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYSMSCUSTOMERS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					){
				for(int i=0;i<bulkData.size();i++) {
					String custDetails = bulkData.get(i);
					long custid = getNextCustId(connection);
					String custcell = Util.getValueAt(custDetails,",",0);
					String custname = Util.getValueAt(custDetails,",",1);
					String custsurname = Util.getValueAt(custDetails,",",2);
					String custemail = Util.getValueAt(custDetails,",",3);
					String custdob = Util.getValueAt(custDetails,",",4);
					custdob = custdob.replace("/", "-");
					int custgender = Util.parseInt(Util.getValueAt(custDetails,",",5),0);
					String custsuburb = Util.getValueAt(custDetails,",",6);
					String custtown = Util.getValueAt(custDetails,",",7);
					String custprov = Util.getValueAt(custDetails,",",8);
					Date dateOfBirth = DateUtil.getDateValue(custdob);
					statement.setInt(1, compid);
					statement.setString(2, custcell);
					statement.setLong(3, custid);
					statement.setString(4, custname);
					statement.setString(5, custsurname);
					statement.setString(6, custemail);
					statement.setDate(7, dateOfBirth);
					statement.setInt(8, custgender);
					statement.setString(9, custsuburb);
					statement.setString(10, custtown);
					statement.setString(11, custprov);
					statement.setDate(12, DateUtil.getCurrentCCYYMMDDDate());
					statement.setTime(13, DateUtil.getCurrentHHMMSSTime());
					statement.setInt(14, userid);
					statement.setString(15, CompanySmsCustomer.CUST_ACTIVE);
					try {
						statement.executeUpdate();
					}catch (Exception e) {
						System.out.println("Error executing statement :" + statement.toString());
						System.out.println("LINE: " + i);
					}
				}
			}catch (Exception e) {
				System.out.println("ERROR UPDATING COMPANYSMSCUSTOMERS: " + e.toString());
				break logic;
			}
			done = true;
		}
		return done;
	}
}
