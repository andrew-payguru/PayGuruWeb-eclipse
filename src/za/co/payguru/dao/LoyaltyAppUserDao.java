package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import za.co.payguru.model.LoyaltyAppUser;
import za.co.payguru.util.DateUtil;

public class LoyaltyAppUserDao {

    public static int getNextUserId(Connection connection) {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('loyaltyappuser_userid_seq') AS id")) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error getting next userid: " + e.toString());
        }
        return id;
    }

    public static LoyaltyAppUser loadLoyaltyAppUser(Connection connection, long userid) {
        LoyaltyAppUser user = new LoyaltyAppUser();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM LOYALTYAPPUSER WHERE userid = ? AND active = ?")) {
            statement.setLong(1, userid);
            statement.setString(2, LoyaltyAppUser.ACTIVE);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user.setUserid(rs.getLong("userid"));
                user.setClientid(rs.getInt("clientid"));
                user.setLoyaltyno(rs.getString("loyaltyno"));
                user.setCompid(rs.getInt("compid"));
                user.setComployaltyid(rs.getInt("comployaltyid"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getString("status"));
                user.setStatusdate(rs.getDate("statusdate"));
                user.setStatustime(rs.getTime("statustime"));
                user.setActive(rs.getString("active"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error loading LOYALTYAPPUSER: " + e.toString());
        }
        return user;
    }

    
    public static LoyaltyAppUser loadLoyaltyAppUser(Connection connection, int compid, int comployaltyid, String loyaltyno) {
      LoyaltyAppUser user = new LoyaltyAppUser();
      try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM LOYALTYAPPUSER WHERE compid = ? AND comployaltyid = ? AND loyaltyno = ? AND active = ?")) {
          statement.setInt(1, compid);
          statement.setInt(2, comployaltyid);
          statement.setString(3, loyaltyno);
          statement.setString(4, LoyaltyAppUser.ACTIVE);
          ResultSet rs = statement.executeQuery();
          if (rs.next()) {
              user.setUserid(rs.getLong("userid"));
              user.setClientid(rs.getInt("clientid"));
              user.setLoyaltyno(rs.getString("loyaltyno"));
              user.setCompid(rs.getInt("compid"));
              user.setComployaltyid(rs.getInt("comployaltyid"));
              user.setPassword(rs.getString("password"));
              user.setStatus(rs.getString("status"));
              user.setStatusdate(rs.getDate("statusdate"));
              user.setStatustime(rs.getTime("statustime"));
              user.setActive(rs.getString("active"));
          }
          rs.close();
      } catch (Exception e) {
          System.out.println("Error loading LOYALTYAPPUSER: " + e.toString());
      }
      return user;
  }
    public static LoyaltyAppUser addLoyaltyAppUser(Connection connection, LoyaltyAppUser user) {
        user.setUserid(getNextUserId(connection));
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO LOYALTYAPPUSER VALUES (?,?,?,?,?,?,?,?,?,?)")) {
            statement.setLong(1, user.getUserid());
            statement.setInt(2, user.getClientid());
            statement.setString(3, user.getLoyaltyno());
            statement.setInt(4, user.getCompid());
            statement.setInt(5, user.getComployaltyid());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getStatus());
            statement.setDate(8, user.getStatusdate());
            statement.setTime(9, user.getStatustime());
            statement.setString(10, user.getActive());
            if (statement.executeUpdate() <= 0) {
                user = new LoyaltyAppUser();
            }
        } catch (Exception e) {
            System.out.println("Error creating LOYALTYAPPUSER: " + e.toString());
        }
        return user;
    }

    public static boolean updateLoyaltyAppUser(Connection connection, LoyaltyAppUser user) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE LOYALTYAPPUSER SET clientid = ?, loyaltyno = ?, compid = ?, comployaltyid = ?, password = ?, status = ?, statusdate = ?, statustime = ?, active = ? WHERE userid = ?")) {
            statement.setInt(1, user.getClientid());
            statement.setString(2, user.getLoyaltyno());
            statement.setInt(3, user.getCompid());
            statement.setInt(4, user.getComployaltyid());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getStatus());
            statement.setDate(7, user.getStatusdate());
            statement.setTime(8, user.getStatustime());
            statement.setString(9, user.getActive());
            statement.setLong(10, user.getUserid());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error updating LOYALTYAPPUSER: " + e.toString());
        }
        return false;
    }

    public static boolean deleteLoyaltyAppUser(Connection connection, long userid) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM LOYALTYAPPUSER WHERE userid = ?")) {
            statement.setLong(1, userid);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error deleting LOYALTYAPPUSER: " + e.toString());
        }
        return false;
    }
}
