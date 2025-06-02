package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import za.co.payguru.model.CompanySmsCustomer;
import za.co.payguru.model.CompanySmsGroup;
import za.co.payguru.model.CompanySmsGroupCustomer;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.Util;

public class CompanySmsGroupCustomerDao {

	public static boolean bulkLinkCompGroupCustomers(int compId, int userId, ArrayList<String> custCells, String groupIdsStr, boolean unlink, Connection connection) {
		boolean done = false;
		boolean allowCreateNewCust = false;
		if(groupIdsStr==null)
			return done;

		String [] groupIds = groupIdsStr.split(",");
		for(int c=0;c<custCells.size();c++) {
			String cell = custCells.get(c).trim();
			CompanySmsCustomer smsCustomer = CompanySmsCustomerDao.loadSmsCustomerByCell(compId, cell, connection);
			if(smsCustomer==null||smsCustomer.getCustId()==0) {
				if(allowCreateNewCust) {
					smsCustomer.setCustCell(cell);
					smsCustomer.setCustCreatedUserId(userId);
					smsCustomer = CompanySmsCustomerDao.createSmsCustomer(smsCustomer, connection);
					if(smsCustomer.getCustId()==0) {
						System.out.println("Could not create customer ["+cell+"] continuing...");
						continue;
					}
				}else
					continue;
			}
			for(int g=0;g<groupIds.length;g++) {
				int groupId = Util.parseInt(groupIds[g], 0);
				if(groupId==0) {
					System.out.println("Invalid Group ID, continuing...");
					continue;
				}
				CompanySmsGroup smsGroup = CompanySmsGroupDao.loadCompanySmsGroup(compId, groupId, connection);
				if(smsGroup==null||smsGroup.getSmsGroupId()==0) {
					System.out.println("Invalid Group ID, continuing...");
					continue;
				}
				if(unlink) {
					System.out.println("UNLINKING");
					if(unlinkCustomerGroup(smsCustomer.getCustId(), groupId, connection)==false) 
						System.out.println("Error Unlinking CustId ["+smsCustomer.getCustId()+"] with GroupId ["+groupId+"]");
				}
				else {
					System.out.println("LINKING");
					if(linkCustomerGroup(smsCustomer.getCustId(), groupId, connection)==false) 
						System.out.println("Error Linking CustId ["+smsCustomer.getCustId()+"] with GroupId ["+groupId+"]");
				}

			}
		}
		done = true;
		return done;
	}

	public static boolean linkCustomerGroup(long custId, int groupId, Connection connection) {
		boolean updated = false;
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYSMSGROUPCUSTOMERS VALUES (?,?,?,?,?,?,?)");
				){

			statement.setLong(1, custId);
			statement.setInt(2, groupId);
			statement.setDate(3, DateUtil.getCurrentCCYYMMDDDate());
			statement.setTime(4, DateUtil.getCurrentHHMMSSTime());
			statement.setDate(5, DateUtil.DEFAULT_DATE);
			statement.setTime(6, DateUtil.DEFAULT_TIME);
			statement.setString(7, CompanySmsGroupCustomer.ACTIVE);
			System.out.println("\n\n\n"+statement.toString());
			if(statement.executeUpdate()>0)
				updated = true;
		}catch (Exception e) {
			System.out.println("Error Linking COMPANYSMSGROUPCUSTOMER: " + e.toString());
		}
		return updated;
	}
	public static boolean unlinkCustomerGroup(long custId, int groupId, Connection connection) {
		boolean deleted = false;
		try(
				PreparedStatement statement = connection.prepareStatement("DELETE FROM COMPANYSMSGROUPCUSTOMERS WHERE custid = ? AND smsgroupid = ?");
				){
			statement.setLong(1, custId);
			statement.setInt(2, groupId);
			if(statement.executeUpdate()>0)
				deleted = true;
		}catch (Exception e) {
			System.out.println("Error Unlinking COMPANYSMSGROUPCUSTOMER: " + e.toString());
		}
		return deleted;
	}
}
