package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import za.co.payguru.model.ClientOtp;

public class ClientOtpDao {

	public static long getNextOtpId(Connection connection) {
		long id = 0;
		try (
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('clientotp_otpid_seq') AS id");
				) {
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				id = rs.getLong("id");
			rs.close();
		} catch (Exception e) {
			System.out.println("Error getting next otpid: " + e.toString());
		}
		return id;
	}

	public static ClientOtp loadClientOtp(Connection connection, long otpid) {
		ClientOtp clientOtp = new ClientOtp();
		try (
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM clientotp WHERE otpid = ?");
				) {
			statement.setLong(1, otpid);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				clientOtp.setOtpid(rs.getLong("otpid"));
				clientOtp.setClientid(rs.getInt("clientid"));
				clientOtp.setOtpdate(rs.getDate("otpdate"));
				clientOtp.setOtptime(rs.getTime("otptime"));
				clientOtp.setOtpno(rs.getString("otpno"));
				clientOtp.setOtptype(rs.getInt("otptype"));
				clientOtp.setOtpsendref(rs.getString("otpsendref"));
				clientOtp.setOtpstatus(rs.getString("otpstatus"));
				clientOtp.setOtpstatusdate(rs.getDate("otpstatusdate"));
				clientOtp.setOtpstatustime(rs.getTime("otpstatustime"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading ClientOtp: " + e.toString());
		}
		return clientOtp;
	}

	public static ClientOtp addClientOtp(Connection connection, ClientOtp clientOtp) {
		clientOtp.setOtpid(getNextOtpId(connection));
		try (
				PreparedStatement statement = connection.prepareStatement("INSERT INTO clientotp (otpid, clientid, otpdate, otptime, otpno, otptype, otpsendref, otpstatus, otpstatusdate, otpstatustime) VALUES (?,?,?,?,?,?,?,?,?,?)");
				) {
			statement.setLong(1, clientOtp.getOtpid());
			statement.setInt(2, clientOtp.getClientid());
			statement.setDate(3, clientOtp.getOtpdate());
			statement.setTime(4, clientOtp.getOtptime());
			statement.setString(5, clientOtp.getOtpno());
			statement.setInt(6, clientOtp.getOtptype());
			statement.setString(7, clientOtp.getOtpsendref());
			statement.setString(8, clientOtp.getOtpstatus());
			statement.setDate(9, clientOtp.getOtpstatusdate());
			statement.setTime(10, clientOtp.getOtpstatustime());
			if (statement.executeUpdate() <= 0)
				clientOtp = new ClientOtp();
		} catch (Exception e) {
			System.out.println("Error creating ClientOtp: " + e.toString());
		}
		return clientOtp;
	}

	public static boolean updateClientOtp(Connection connection, ClientOtp clientOtp) {
		boolean updated = false;
		try (
				PreparedStatement statement = connection.prepareStatement("UPDATE clientotp SET otpstatus = ?, otpstatusdate = ?, otpstatustime = ? WHERE otpid = ?");
				) {
			statement.setString(1, clientOtp.getOtpstatus());
			statement.setDate(2, clientOtp.getOtpstatusdate());
			statement.setTime(3, clientOtp.getOtpstatustime());
			statement.setLong(4, clientOtp.getOtpid());
			if (statement.executeUpdate() > 0)
				updated = true;
		} catch (Exception e) {
			System.out.println("Error updating ClientOtp: " + e.toString());
		}
		return updated;
	}
	
	public static String generateOtp(int len) {
		StringBuffer otp = new StringBuffer();
		for(int i=0;i<len;i++) {
			double digit = Math.round(Math.random()*10);
			if(digit==10)
				digit=0;
			otp.append((int)digit);
		}
		return otp.toString();
	}
}
