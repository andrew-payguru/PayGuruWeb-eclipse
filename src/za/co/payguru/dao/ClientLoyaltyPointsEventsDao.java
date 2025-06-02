package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.ClientLoyaltyPointsEvents;

public class ClientLoyaltyPointsEventsDao {

	public static ClientLoyaltyPointsEvents loadClientLoyaltyPointsEvent(Connection connection, int eventid) {
		ClientLoyaltyPointsEvents event = new ClientLoyaltyPointsEvents();
		try (
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTLOYALTYPOINTSEVENTS WHERE eventid = ?");
				) {
			statement.setInt(1, eventid);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				event.setEventid(rs.getInt("eventid"));
				event.setClientid(rs.getInt("clientid"));
				event.setCompid(rs.getInt("compid"));
				event.setLoyaltyid(rs.getInt("loyaltyid"));
				event.setLoyaltyeventtype(rs.getInt("loyaltyeventtype"));
				event.setLoyaltypoints(rs.getDouble("loyaltypoints"));
				event.setEventdate(rs.getDate("eventdate"));
				event.setEventtime(rs.getTime("eventtime"));
				event.setEventref1(rs.getString("eventref1"));
				event.setEventref2(rs.getString("eventref2"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading CLIENTLOYALTYPOINTSEVENTS: " + e.toString());
		}
		return event;
	}

	public static ArrayList<ClientLoyaltyPointsEvents> getClientLoyaltyPointsEvents(Connection connection, int clientid, int compid, int loyaltyid) {
		ArrayList<ClientLoyaltyPointsEvents> events = new ArrayList<ClientLoyaltyPointsEvents>();
		try (
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTLOYALTYPOINTSEVENTS WHERE clientid = ? AND compid = ? AND loyaltyid = ? ORDER BY eventdate DESC, eventtime DESC, eventid DESC");
		) {
			statement.setInt(1, clientid);
			statement.setInt(2, compid);
			statement.setInt(3, loyaltyid);
			System.out.println(statement.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientLoyaltyPointsEvents event = new ClientLoyaltyPointsEvents();
				event.setEventid(rs.getInt("eventid"));
				event.setClientid(rs.getInt("clientid"));
				event.setCompid(rs.getInt("compid"));
				event.setLoyaltyid(rs.getInt("loyaltyid"));
				event.setLoyaltyeventtype(rs.getInt("loyaltyeventtype"));
				event.setLoyaltypoints(rs.getDouble("loyaltypoints"));
				event.setEventdate(rs.getDate("eventdate"));
				event.setEventtime(rs.getTime("eventtime"));
				event.setEventref1(rs.getString("eventref1"));
				event.setEventref2(rs.getString("eventref2"));
				events.add(event);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading CLIENTLOYALTYPOINTSEVENTS: " + e.toString());
		}
		return events;
	}

	public static ClientLoyaltyPointsEvents addClientLoyaltyPointsEvent(Connection connection, ClientLoyaltyPointsEvents event) {
		try (
				PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENTLOYALTYPOINTSEVENTS (eventid, clientid, compid, loyaltyid, loyaltyeventtype, loyaltypoints, eventdate, eventtime, eventref1, eventref2) VALUES (?,?,?,?,?,?,?,?,?,?)");
				) {
			statement.setLong(1, event.getEventid());
			statement.setInt(2, event.getClientid());
			statement.setInt(3, event.getCompid());
			statement.setInt(4, event.getLoyaltyid());
			statement.setInt(5, event.getLoyaltyeventtype());
			statement.setDouble(6, event.getLoyaltypoints());
			statement.setDate(7, event.getEventdate());
			statement.setTime(8, event.getEventtime());
			statement.setString(9, event.getEventref1());
			statement.setString(10, event.getEventref2());
			if (statement.executeUpdate() <= 0)
				event = new ClientLoyaltyPointsEvents();
		} catch (Exception e) {
			System.out.println("Error creating CLIENTLOYALTYPOINTSEVENTS: " + e.toString());
		}
		return event;
	}
	
	public static JSONArray getLoyaltyPointsEventsJSON(ArrayList<ClientLoyaltyPointsEvents> events) {
		JSONArray json = new JSONArray();
		for(int i=0;i<events.size();i++) {
			ClientLoyaltyPointsEvents event = events.get(i);
			json.put(event.toJSON());
		}
		return json;
	}
}
