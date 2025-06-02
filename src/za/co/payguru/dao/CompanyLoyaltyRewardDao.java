package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.model.CompanyLoyaltyReward;

public class CompanyLoyaltyRewardDao {

	public static int getNextRewardId(Connection connection) {
		int id = 0;
		try (PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('companyloyaltyrewards_rewardid_seq') AS id")) {
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error getting next rewardid: " + e.toString());
		}
		return id;
	}

	public static CompanyLoyaltyReward loadCompanyLoyaltyReward(Connection connection, long rewardid) {
		CompanyLoyaltyReward reward = new CompanyLoyaltyReward();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYLOYALTYREWARDS WHERE rewardid = ? AND rewardactive = ?")) {
			statement.setLong(1, rewardid);
			statement.setString(2, CompanyLoyaltyReward.ACTIVE);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				reward.setRewardid(rs.getLong("rewardid"));
				reward.setCompid(rs.getInt("compid"));
				reward.setComployaltyid(rs.getInt("comployaltyid"));
				reward.setRewarddesc(rs.getString("rewarddesc"));
				reward.setRewardpointval(rs.getDouble("rewardpointval"));
				reward.setRewardstatus(rs.getString("rewardstatus"));
				reward.setRewardstatusdate(rs.getDate("rewardstatusdate"));
				reward.setRewardstatustime(rs.getTime("rewardstatustime"));
				reward.setRewardactive(rs.getString("rewardactive"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading COMPANYLOYALTYREWARDS: " + e.toString());
		}
		return reward;
	}

	public static ArrayList<CompanyLoyaltyReward> getCompanyLoyaltyRewards(Connection connection, int compid, int comployaltyid) {
		ArrayList<CompanyLoyaltyReward> rewards = new ArrayList<CompanyLoyaltyReward>();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYLOYALTYREWARDS WHERE compid = ? AND comployaltyid = ? AND rewardactive = ? ORDER BY rewardpointval ASC, rewarddesc ASC")) {
			statement.setInt(1, compid);
			statement.setInt(2, comployaltyid);
			statement.setString(3, CompanyLoyaltyReward.ACTIVE);
			System.out.println(statement);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				CompanyLoyaltyReward reward = new CompanyLoyaltyReward();
				reward.setRewardid(rs.getLong("rewardid"));
				reward.setCompid(rs.getInt("compid"));
				reward.setComployaltyid(rs.getInt("comployaltyid"));
				reward.setRewarddesc(rs.getString("rewarddesc"));
				reward.setRewardpointval(rs.getDouble("rewardpointval"));
				reward.setRewardstatus(rs.getString("rewardstatus"));
				reward.setRewardstatusdate(rs.getDate("rewardstatusdate"));
				reward.setRewardstatustime(rs.getTime("rewardstatustime"));
				reward.setRewardactive(rs.getString("rewardactive"));
				rewards.add(reward);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading COMPANYLOYALTYREWARDS: " + e.toString());
		}
		return rewards;
	}

	public static CompanyLoyaltyReward addCompanyLoyaltyReward(Connection connection, CompanyLoyaltyReward reward) {
		reward.setRewardid(getNextRewardId(connection));
		try (PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYLOYALTYREWARDS VALUES (?,?,?,?,?,?,?,?,?)")) {
			statement.setLong(1, reward.getRewardid());
			statement.setInt(2, reward.getCompid());
			statement.setInt(3, reward.getComployaltyid());
			statement.setString(4, reward.getRewarddesc());
			statement.setDouble(5, reward.getRewardpointval());
			statement.setString(6, reward.getRewardstatus());
			statement.setDate(7, reward.getRewardstatusdate());
			statement.setTime(8, reward.getRewardstatustime());
			statement.setString(9, reward.getRewardactive());
			if (statement.executeUpdate() <= 0) {
				reward = new CompanyLoyaltyReward();
			}
		} catch (Exception e) {
			System.out.println("Error creating COMPANYLOYALTYREWARDS: " + e.toString());
		}
		return reward;
	}

	public static boolean updateCompanyLoyaltyReward(Connection connection, CompanyLoyaltyReward reward) {
		try (PreparedStatement statement = connection.prepareStatement("UPDATE COMPANYLOYALTYREWARDS SET compid = ?, comployaltyid = ?, rewarddesc = ?, rewardpointval = ?, rewardstatus = ?, rewardstatusdate = ?, rewardstatustime = ?, rewardactive = ? WHERE rewardid = ?")) {
			statement.setInt(1, reward.getCompid());
			statement.setInt(2, reward.getComployaltyid());
			statement.setString(3, reward.getRewarddesc());
			statement.setDouble(4, reward.getRewardpointval());
			statement.setString(5, reward.getRewardstatus());
			statement.setDate(6, reward.getRewardstatusdate());
			statement.setTime(7, reward.getRewardstatustime());
			statement.setString(8, reward.getRewardactive());
			statement.setLong(9, reward.getRewardid());
			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println("Error updating COMPANYLOYALTYREWARDS: " + e.toString());
		}
		return false;
	}

	public static boolean deleteCompanyLoyaltyReward(Connection connection, long rewardid) {
		try (PreparedStatement statement = connection.prepareStatement("DELETE FROM COMPANYLOYALTYREWARDS WHERE rewardid = ?")) {
			statement.setLong(1, rewardid);
			return statement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println("Error deleting COMPANYLOYALTYREWARDS: " + e.toString());
		}
		return false;
	}
	
	public static JSONArray getLoyaltyRewardsJson(ArrayList<CompanyLoyaltyReward> data) {
  	JSONArray json = new JSONArray();
  	for(CompanyLoyaltyReward loyaltyReward : data) {
  		JSONObject jsonReward = loyaltyReward.toJson();
  		json.put(jsonReward);
  	}
  	return json;
  }
}
