package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import za.co.payguru.model.ClientLoyalty;
import za.co.payguru.util.Util;

public class ClientLoyaltyDao {
	public static long getNextLoyaltyNo(Connection connection) {
		long loyaltyno = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('clientloyalty_loyaltyno_seq') AS loyaltyno");
				){
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				loyaltyno = rs.getLong("loyaltyno");
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error getting nextval: " + e.toString());
		}
		return loyaltyno;
	}

	public static ClientLoyalty loadClientLoyalty(Connection connection, int clientid) {
		ClientLoyalty clientLoyalty = new ClientLoyalty();
		try (
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientloyalty WHERE clientid = ? AND active = ?");
				) {
			statement.setInt(1, clientid);
			statement.setString(2, ClientLoyalty.LOYALTY_ACTIVE);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				clientLoyalty.setClientid(rs.getInt("clientid"));
				clientLoyalty.setLoyaltyno(rs.getString("loyaltyno"));
				clientLoyalty.setLoyaltyjoindate(rs.getDate("loyaltyjoindate"));
				clientLoyalty.setLoyaltystatus(rs.getString("loyaltystatus"));
				clientLoyalty.setLoyaltystatusdate(rs.getDate("loyaltystatusdate"));
				clientLoyalty.setLoyaltystatustime(rs.getTime("loyaltystatustime"));
				clientLoyalty.setLoyaltyref1(rs.getString("loyaltyref1"));
				clientLoyalty.setLoyaltyref2(rs.getString("loyaltyref2"));
				clientLoyalty.setActive(rs.getString("active"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading CLIENTLOYALTY: " + e.toString());
		}
		return clientLoyalty;
	}
	public static ClientLoyalty loadClientLoyaltyByLoyaltyNo(Connection connection, String loyaltyno) {
		ClientLoyalty clientLoyalty = new ClientLoyalty();
		try (
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientloyalty WHERE loyaltyno = ? AND active = ?");
				) {
			statement.setString(1, loyaltyno);
			statement.setString(2, ClientLoyalty.LOYALTY_ACTIVE);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				clientLoyalty.setClientid(rs.getInt("clientid"));
				clientLoyalty.setLoyaltyno(rs.getString("loyaltyno"));
				clientLoyalty.setLoyaltyjoindate(rs.getDate("loyaltyjoindate"));
				clientLoyalty.setLoyaltystatus(rs.getString("loyaltystatus"));
				clientLoyalty.setLoyaltystatusdate(rs.getDate("loyaltystatusdate"));
				clientLoyalty.setLoyaltystatustime(rs.getTime("loyaltystatustime"));
				clientLoyalty.setLoyaltyref1(rs.getString("loyaltyref1"));
				clientLoyalty.setLoyaltyref2(rs.getString("loyaltyref2"));
				clientLoyalty.setActive(rs.getString("active"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading CLIENTLOYALTY: " + e.toString());
		}
		return clientLoyalty;
	}
	public static ClientLoyalty addClientLoyalty(Connection connection, ClientLoyalty clientLoyalty) {
		clientLoyalty.setLoyaltyno(Util.generateRandomLetter()+Util.prefixChar(""+getNextLoyaltyNo(connection), '0', 9));
		try (
				PreparedStatement statement = connection.prepareStatement("INSERT INTO clientloyalty (clientid, loyaltyno, loyaltyjoindate, loyaltystatus, loyaltystatusdate, loyaltystatustime, loyaltyref1, loyaltyref2, active) VALUES (?,?,?,?,?,?,?,?,?)");
				) {
			statement.setInt(1, clientLoyalty.getClientid());
			statement.setString(2, clientLoyalty.getLoyaltyno());
			statement.setDate(3, clientLoyalty.getLoyaltyjoindate());
			statement.setString(4, clientLoyalty.getLoyaltystatus());
			statement.setDate(5, clientLoyalty.getLoyaltystatusdate());
			statement.setTime(6, clientLoyalty.getLoyaltystatustime());
			statement.setString(7, clientLoyalty.getLoyaltyref1());
			statement.setString(8, clientLoyalty.getLoyaltyref2());
			statement.setString(9, clientLoyalty.getActive());
			if (statement.executeUpdate() <= 0) {
				clientLoyalty = new ClientLoyalty();
			}
		} catch (Exception e) {
			System.out.println("Error creating CLIENTLOYALTY: " + e.toString());
		}
		return clientLoyalty;
	}

	public static ClientLoyalty updateClientLoyalty(Connection connection, ClientLoyalty clientLoyalty) {
		try (
				PreparedStatement statement = connection.prepareStatement("UPDATE clientloyalty SET loyaltyno = ?, loyaltyjoindate = ?, loyaltystatus = ?, loyaltystatusdate = ?, loyaltystatustime = ?, loyaltyref1 = ?, loyaltyref2 = ?, active = ? WHERE clientid = ?");
				) {
			statement.setString(1, clientLoyalty.getLoyaltyno());
			statement.setDate(2, clientLoyalty.getLoyaltyjoindate());
			statement.setString(3, clientLoyalty.getLoyaltystatus());
			statement.setDate(4, clientLoyalty.getLoyaltystatusdate());
			statement.setTime(5, clientLoyalty.getLoyaltystatustime());
			statement.setString(6, clientLoyalty.getLoyaltyref1());
			statement.setString(7, clientLoyalty.getLoyaltyref2());
			statement.setString(8, clientLoyalty.getActive());
			statement.setInt(9, clientLoyalty.getClientid());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error updating CLIENTLOYALTY: " + e.toString());
		}
		return clientLoyalty;
	}
}
