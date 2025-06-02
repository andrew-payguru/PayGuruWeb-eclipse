package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.model.CompanyClientLoyaltyRewardToken;

public class CompanyClientLoyaltyRewardTokenDao {


	public static CompanyClientLoyaltyRewardToken loadToken(Connection connection, String tokenNo, int compId, int compLoyaltyId) {
		CompanyClientLoyaltyRewardToken token = new CompanyClientLoyaltyRewardToken();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYCLIENTLOYALTYREWARDTOKEN WHERE tokenno = ? AND compid = ? AND comployaltyid = ? AND tokenstatus != ?")) {
			statement.setString(1, tokenNo);
			statement.setInt(2, compId);
			statement.setInt(3, compLoyaltyId);
			statement.setString(4, CompanyClientLoyaltyRewardToken.TOKEN_STATUS_INACTIVE);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				token.setTokenno(rs.getString("tokenno"));
				token.setTokenuid(rs.getString("tokenuid"));
				token.setCompid(rs.getInt("compid"));
				token.setComployaltyid(rs.getInt("comployaltyid"));
				token.setRewardid(rs.getLong("rewardid"));
				token.setClientid(rs.getInt("clientid"));
				token.setTokendate(rs.getDate("tokendate"));
				token.setTokentime(rs.getTime("tokentime"));
				token.setTokenstatus(rs.getString("tokenstatus"));
				token.setTokenstatusdate(rs.getDate("tokenstatusdate"));
				token.setTokenstatustime(rs.getTime("tokenstatustime"));
				token.setTokenexpiredate(rs.getDate("tokenexpiredate"));
				token.setTokenexpiretime(rs.getTime("tokenexpiretime"));
				token.setTokenpointsval(rs.getDouble("tokenpointsval"));
				token.setTokenredeemref1(rs.getString("tokenredeemref1"));
				token.setTokenredeemref2(rs.getString("tokenredeemref2"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading token: " + e.toString());
		}
		return token;
	}
	public static CompanyClientLoyaltyRewardToken loadTokenByUid(Connection connection, String tokenuid) {
		CompanyClientLoyaltyRewardToken token = new CompanyClientLoyaltyRewardToken();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYCLIENTLOYALTYREWARDTOKEN WHERE tokenuid = ? AND tokenstatus != ?")) {
			statement.setString(1, tokenuid);
			statement.setString(4, CompanyClientLoyaltyRewardToken.TOKEN_STATUS_INACTIVE);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				token.setTokenno(rs.getString("tokenno"));
				token.setTokenuid(rs.getString("tokenuid"));
				token.setCompid(rs.getInt("compid"));
				token.setComployaltyid(rs.getInt("comployaltyid"));
				token.setRewardid(rs.getLong("rewardid"));
				token.setClientid(rs.getInt("clientid"));
				token.setTokendate(rs.getDate("tokendate"));
				token.setTokentime(rs.getTime("tokentime"));
				token.setTokenstatus(rs.getString("tokenstatus"));
				token.setTokenstatusdate(rs.getDate("tokenstatusdate"));
				token.setTokenstatustime(rs.getTime("tokenstatustime"));
				token.setTokenexpiredate(rs.getDate("tokenexpiredate"));
				token.setTokenexpiretime(rs.getTime("tokenexpiretime"));
				token.setTokenpointsval(rs.getDouble("tokenpointsval"));
				token.setTokenredeemref1(rs.getString("tokenredeemref1"));
				token.setTokenredeemref2(rs.getString("tokenredeemref2"));

			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading token: " + e.toString());
		}
		return token;
	}
	public static ArrayList<CompanyClientLoyaltyRewardToken> getClientTokens(Connection connection, int compId, int compLoyaltyId, int clientId) {
		ArrayList<CompanyClientLoyaltyRewardToken> tokens = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYCLIENTLOYALTYREWARDTOKEN WHERE compid = ? AND comployaltyid = ? AND clientid = ? AND tokenstatus != ? ORDER BY tokenstatus ASC")) {
			statement.setInt(1, compId);
			statement.setInt(2, compLoyaltyId);
			statement.setInt(3, clientId);
			statement.setString(4, CompanyClientLoyaltyRewardToken.TOKEN_STATUS_INACTIVE);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				CompanyClientLoyaltyRewardToken token = new CompanyClientLoyaltyRewardToken();
				token.setTokenno(rs.getString("tokenno"));
				token.setTokenuid(rs.getString("tokenuid"));
				token.setCompid(rs.getInt("compid"));
				token.setComployaltyid(rs.getInt("comployaltyid"));
				token.setRewardid(rs.getLong("rewardid"));
				token.setClientid(rs.getInt("clientid"));
				token.setTokendate(rs.getDate("tokendate"));
				token.setTokentime(rs.getTime("tokentime"));
				token.setTokenstatus(rs.getString("tokenstatus"));
				token.setTokenstatusdate(rs.getDate("tokenstatusdate"));
				token.setTokenstatustime(rs.getTime("tokenstatustime"));
				token.setTokenexpiredate(rs.getDate("tokenexpiredate"));
				token.setTokenexpiretime(rs.getTime("tokenexpiretime"));
				token.setTokenpointsval(rs.getDouble("tokenpointsval"));
				token.setTokenredeemref1(rs.getString("tokenredeemref1"));
				token.setTokenredeemref2(rs.getString("tokenredeemref2"));
				tokens.add(token);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading tokens: " + e.toString());
		}
		return tokens;
	}

	public static JSONArray getTokensJson(ArrayList<CompanyClientLoyaltyRewardToken> data) {
		JSONArray json = new JSONArray();
		for (CompanyClientLoyaltyRewardToken token : data) {
			JSONObject jsonToken = token.toJson();
			json.put(jsonToken);
		}
		return json;
	}
}
