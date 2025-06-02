package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.model.CompanyUserEvent;
import za.co.payguru.util.DateUtil;

public class CompanyUserEventDao {

	private static int getNextEventId(Connection connection) {
		int id = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('companyuserevent_eventid_seq') AS eventid");
		){
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				id = rs.getInt("eventid");
			rs.close();
		}catch (Exception e) {
			System.out.println("Error getting next eventid: " + e.toString());
		}
		return id;
	}
	
	public static boolean createUserEvent(Connection connection, CompanyUserEvent userEvent) {
		boolean created = false;
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYUSEREVENT VALUES (?,?,?,?,?,?,?,?)");
		){
			statement.setInt(1, userEvent.getCompId());
			statement.setInt(2, getNextEventId(connection));
			statement.setInt(3, userEvent.getUserId());
			statement.setString(4, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(5, DateUtil.getCurrentHHMMSSStr());
			statement.setInt(6, userEvent.getEventType());
			statement.setString(7, userEvent.getEventRef1());
			statement.setString(8, userEvent.getEventRef2().toUpperCase());
			if(statement.executeUpdate()>0)
				created = true;
		}catch (Exception e) {
			System.out.println("Error updating table COMPANYUSEREVENT: " + e.toString());
		}
		return created;
	}
	
	public static JSONArray searchUserEvents(Connection connection, int compid, String fromdate, String todate, int compinternalid){
		JSONArray userEvents = new JSONArray();
		System.out.println("HELLO");
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT CLIENTINVOICES.compid, CLIENTINVOICES.invno, CLIENTINVOICES.payref, COMPANYUSEREVENT.eventdate, COMPANYUSEREVENT.eventtime, COMPANYUSEREVENT.eventtype, COMPANYUSEREVENT.eventref2, COMPANYUSERS.username, COMPANYUSERS.usersurname FROM COMPANYUSEREVENT JOIN CLIENTINVOICES ON (COMPANYUSEREVENT.compid = CLIENTINVOICES.compid AND COMPANYUSEREVENT.eventref1 = CLIENTINVOICES.invno) JOIN COMPANYUSERS ON (COMPANYUSEREVENT.compid = COMPANYUSERS.compid AND COMPANYUSEREVENT.userid = COMPANYUSERS.userid) JOIN COMPANYPRODUCTS ON (CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid AND CLIENTINVOICES.compid = COMPANYPRODUCTS.compid) WHERE COMPANYUSEREVENT.compid = ? AND COMPANYUSEREVENT.eventdate >= ? AND COMPANYUSEREVENT.eventdate <= ?" + ( (compinternalid>0) ? (" AND COMPANYPRODUCTS.compinternalid = " + compinternalid + " ") : (" ") ) + "ORDER BY COMPANYUSEREVENT.eventdate,COMPANYUSEREVENT.eventtime");
		){
			statement.setInt(1, compid);
			statement.setString(2, fromdate.replace('/', '-'));
			statement.setString(3, todate.replace('/', '-'));
			System.out.println(statement);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				int compId = rs.getInt("compid");
				String invNo = rs.getString("invno");
				String vehicleReg = rs.getString("payref").toUpperCase();
				String eventDate = rs.getString("eventdate").replace('-', '/');
				String eventTime = rs.getString("eventtime");
				String eventReason = rs.getString("eventref2").toUpperCase();
				String userName = rs.getString("username").toUpperCase();
				String userSurname = rs.getString("usersurname").toUpperCase();
				int eventType = rs.getInt("eventtype");
				JSONObject event = new JSONObject();
				event.put("compid", compId);
				event.put("invno", invNo);
				event.put("vehiclereg", vehicleReg);
				event.put("eventdate", eventDate);
				event.put("eventtime", eventTime);
				event.put("eventreason",eventReason);
				event.put("username", userName);
				event.put("usersurname", userSurname);
				event.put("eventtype", eventType);
				userEvents.put(event);
			}rs.close();
						
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYUSEREVENT: " + e.toString());
		}
		return userEvents;
	}
	
}
