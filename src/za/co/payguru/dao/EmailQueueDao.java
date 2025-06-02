package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import za.co.payguru.model.EmailQueue;

public class EmailQueueDao {

    public static long getNextEmailId(Connection connection) {
        long id = 0;
        try (
                PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('emailqueue_emailid_seq') AS id");
        ) {
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                id = rs.getLong("id");
            rs.close();
        } catch (Exception e) {
            System.out.println("Error getting next emailid: " + e.toString());
        }
        return id;
    }

    public static EmailQueue loadEmailQueue(Connection connection, long emailid) {
        EmailQueue emailQueue = new EmailQueue();
        try (
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM emailqueue WHERE emailid = ? AND emailactive = ?");
        ) {
            statement.setLong(1, emailid);
            statement.setString(2, EmailQueue.ACTIVE);  
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                emailQueue.setEmailid(rs.getLong("emailid"));
                emailQueue.setEmailaddress(rs.getString("emailaddress"));
                emailQueue.setEmaildate(rs.getDate("emaildate"));
                emailQueue.setEmailtime(rs.getTime("emailtime"));
                emailQueue.setEmailmessage(rs.getString("emailmessage"));
                emailQueue.setEmailstatus(rs.getString("emailstatus"));
                emailQueue.setEmailstatusdate(rs.getDate("emailstatusdate"));
                emailQueue.setEmailstatustime(rs.getTime("emailstatustime"));
                emailQueue.setEmailsendref(rs.getString("emailsendref"));
                emailQueue.setEmailpayref(rs.getString("emailpayref"));
                emailQueue.setEmailactive(rs.getString("emailactive"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error loading EmailQueue: " + e.toString());
        }
        return emailQueue;
    }

    public static EmailQueue addEmailQueue(Connection connection, EmailQueue emailQueue) {
        emailQueue.setEmailid(System.currentTimeMillis());
        try (
                PreparedStatement statement = connection.prepareStatement("INSERT INTO emailqueue (emailid, emailaddress, emaildate, emailtime, emailmessage, emailstatus, emailstatusdate, emailstatustime, emailsendref, emailpayref, emailactive) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        ) {
            statement.setLong(1, emailQueue.getEmailid());
            statement.setString(2, emailQueue.getEmailaddress());
            statement.setDate(3, emailQueue.getEmaildate());
            statement.setTime(4, emailQueue.getEmailtime());
            statement.setString(5, emailQueue.getEmailmessage());
            statement.setString(6, emailQueue.getEmailstatus());
            statement.setDate(7, emailQueue.getEmailstatusdate());
            statement.setTime(8, emailQueue.getEmailstatustime());
            statement.setString(9, emailQueue.getEmailsendref());
            statement.setString(10, emailQueue.getEmailpayref());
            statement.setString(11, emailQueue.getEmailactive());
            if (statement.executeUpdate() <= 0)
                emailQueue = new EmailQueue();
        } catch (Exception e) {
            System.out.println("Error creating EmailQueue: " + e.toString());
        }
        return emailQueue;
    }
}
