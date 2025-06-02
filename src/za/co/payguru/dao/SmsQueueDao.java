package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import za.co.payguru.model.SmsQueue;
import za.co.payguru.util.DateUtil;

public class SmsQueueDao {
	public static boolean createSms(Connection connection, SmsQueue smsQueue, long currentMillis) {
		boolean created = false;
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO SMSQUEUE VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		){
			statement.setLong(1, currentMillis);
			statement.setString(2, smsQueue.getSmsCellNo());
			statement.setString(3, smsQueue.getSmsDate());
			statement.setString(4, smsQueue.getSmsTime());
			statement.setString(5, smsQueue.getSmsMessage());
			statement.setString(6, SmsQueue.SMS_ACTIVE);
			statement.setString(7, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(8, DateUtil.getCurrentHHMMSSStr());
			statement.setString(9, smsQueue.getSmsSendRef());
			statement.setString(10, smsQueue.getSmsPayRef());
			statement.setString(11, SmsQueue.SMS_ACTIVE);
			if(statement.executeUpdate()>0)
				created = true;
		}catch (Exception e) {
			System.out.println("Error creating SMSQUEUE: " + e.toString());
		}
		return created;
	}

	public static boolean createSms(Connection connection, SmsQueue smsQueue) {
		boolean created = false;
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO SMSQUEUE VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		){
			statement.setLong(1, System.currentTimeMillis());
			statement.setString(2, smsQueue.getSmsCellNo());
			statement.setString(3, smsQueue.getSmsDate());
			statement.setString(4, smsQueue.getSmsTime());
			statement.setString(5, smsQueue.getSmsMessage());
			statement.setString(6, SmsQueue.SMS_ACTIVE);
			statement.setString(7, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(8, DateUtil.getCurrentHHMMSSStr());
			statement.setString(9, smsQueue.getSmsSendRef());
			statement.setString(10, smsQueue.getSmsPayRef());
			statement.setString(11, SmsQueue.SMS_ACTIVE);
			if(statement.executeUpdate()>0)
				created = true;
		}catch (Exception e) {
			System.out.println("Error creating SMSQUEUE: " + e.toString());
		}
		return created;
	}
	
	public static SmsQueue createSmsQueue(Connection connection, SmsQueue smsQueue) {
		smsQueue.setSmsId(System.currentTimeMillis());
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO SMSQUEUE VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		){
			statement.setLong(1, smsQueue.getSmsId());
			statement.setString(2, smsQueue.getSmsCellNo());
			statement.setString(3, smsQueue.getSmsDate());
			statement.setString(4, smsQueue.getSmsTime());
			statement.setString(5, smsQueue.getSmsMessage());
			statement.setString(6, smsQueue.getSmsStatus());
			statement.setString(7, DateUtil.getCurrentCCYYMMDDStr());
			statement.setString(8, DateUtil.getCurrentHHMMSSStr());
			statement.setString(9, smsQueue.getSmsSendRef());
			statement.setString(10, smsQueue.getSmsPayRef());
			statement.setString(11, smsQueue.getSmsActive());
			if(statement.executeUpdate()<=0)
				smsQueue = new SmsQueue();
		}catch (Exception e) {
			System.out.println("Error creating SMSQUEUE: " + e.toString());
		}
		return smsQueue;
	}
}
