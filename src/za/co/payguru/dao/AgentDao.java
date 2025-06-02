package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.Agent;

public class AgentDao {

    public static Agent loadAgent(Connection connection, int agentId) {
        Agent agent = new Agent();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM agents WHERE agentid = ?")) {
            statement.setInt(1, agentId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                agent.setAgentId(rs.getInt("agentid"));
                agent.setWebUserId(rs.getString("webuserid"));
                agent.setWebUserPass(rs.getString("webuserpass"));
                agent.setApiRef(rs.getString("apiref"));
                agent.setApiUserId(rs.getString("apiuserid"));
                agent.setApiUserPass(rs.getString("apiuserpass"));
                agent.setDailyLimit(rs.getDouble("dailylimit"));
                agent.setSrcIp(rs.getString("srcip"));
                agent.setApiAuthType(rs.getInt("apiauthtype"));
                agent.setApiAuthTokenExpireTime(rs.getLong("apiauthtokenexpiretime"));
                agent.setApiAuthToken(rs.getString("apiauthtoken"));
                agent.setApiAuthTokenDate(rs.getDate("apiauthtokendate"));
                agent.setApiAuthTokenTime(rs.getTime("apiauthtokentime"));
                agent.setApiAuthTokenExpireMillis(rs.getLong("apiauthtokenexpiremillis"));
                agent.setApiAuthTokenRefresh(rs.getString("apiauthtokenrefresh"));
                agent.setApiAuthTokenRefreshExpireMillis(rs.getLong("apiauthtokenrefreshexpiremillis"));
                agent.setApiAuthTokenType(rs.getInt("apiauthtokentype"));
                agent.setApiMaxType1(rs.getInt("apimaxtype1"));
                agent.setApiMaxType2(rs.getInt("apimaxtype2"));
                agent.setApiMaxType3(rs.getInt("apimaxtype3"));
                agent.setCompId(rs.getInt("compid"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error querying agents: " + e.toString());
        }
        return agent;
    }
    
    public static Agent loadAgentByApiRef(Connection connection, String apiRef) {
        Agent agent = new Agent();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM agents WHERE apiref = ?")) {
            statement.setString(1, apiRef);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                agent.setAgentId(rs.getInt("agentid"));
                agent.setWebUserId(rs.getString("webuserid"));
                agent.setWebUserPass(rs.getString("webuserpass"));
                agent.setApiRef(rs.getString("apiref"));
                agent.setApiUserId(rs.getString("apiuserid"));
                agent.setApiUserPass(rs.getString("apiuserpass"));
                agent.setDailyLimit(rs.getDouble("dailylimit"));
                agent.setSrcIp(rs.getString("srcip"));
                agent.setApiAuthType(rs.getInt("apiauthtype"));
                agent.setApiAuthTokenExpireTime(rs.getLong("apiauthtokenexpiretime"));
                agent.setApiAuthToken(rs.getString("apiauthtoken"));
                agent.setApiAuthTokenDate(rs.getDate("apiauthtokendate"));
                agent.setApiAuthTokenTime(rs.getTime("apiauthtokentime"));
                agent.setApiAuthTokenExpireMillis(rs.getLong("apiauthtokenexpiremillis"));
                agent.setApiAuthTokenRefresh(rs.getString("apiauthtokenrefresh"));
                agent.setApiAuthTokenRefreshExpireMillis(rs.getLong("apiauthtokenrefreshexpiremillis"));
                agent.setApiAuthTokenType(rs.getInt("apiauthtokentype"));
                agent.setApiMaxType1(rs.getInt("apimaxtype1"));
                agent.setApiMaxType2(rs.getInt("apimaxtype2"));
                agent.setApiMaxType3(rs.getInt("apimaxtype3"));
                agent.setCompId(rs.getInt("compid"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error querying agents: " + e.toString());
        }
        return agent;
    }

    public static boolean addAgent(Connection connection, Agent agent) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO agents (webuserid, webuserpass, apiref, apiuserid, apiuserpass, dailylimit, srcip, apiauthtype, apiauthtokenexpiretime, apiauthtoken, apiauthtokendate, apiauthtokentime, apiauthtokenexpiremillis, apiauthtokenrefresh, apiauthtokenrefreshexpiremillis, apiauthtokentype, apimaxtype1, apimaxtype2, apimaxtype3, compid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, agent.getWebUserId());
            statement.setString(2, agent.getWebUserPass());
            statement.setString(3, agent.getApiRef());
            statement.setString(4, agent.getApiUserId());
            statement.setString(5, agent.getApiUserPass());
            statement.setDouble(6, agent.getDailyLimit());
            statement.setString(7, agent.getSrcIp());
            statement.setInt(8, agent.getApiAuthType());
            statement.setLong(9, agent.getApiAuthTokenExpireTime());
            statement.setString(10, agent.getApiAuthToken());
            statement.setDate(11, agent.getApiAuthTokenDate());
            statement.setTime(12, agent.getApiAuthTokenTime());
            statement.setLong(13, agent.getApiAuthTokenExpireMillis());
            statement.setString(14, agent.getApiAuthTokenRefresh());
            statement.setLong(15, agent.getApiAuthTokenRefreshExpireMillis());
            statement.setInt(16, agent.getApiAuthTokenType());
            statement.setInt(17, agent.getApiMaxType1());
            statement.setInt(18, agent.getApiMaxType2());
            statement.setInt(19, agent.getApiMaxType3());
            statement.setInt(20, agent.getCompId());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error inserting into agents: " + e.toString());
        }
        return false;
    }

    // Similarly, implement update and delete methods here
}
