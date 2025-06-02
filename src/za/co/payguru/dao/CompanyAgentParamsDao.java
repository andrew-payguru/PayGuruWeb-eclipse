package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompanyAgentParamsDao {

    public static String loadCompanyAgentParamValue(Connection connection, int compId, int agentId, String paramId) {
    	String paramVal = "";
        try (PreparedStatement statement = connection.prepareStatement("SELECT paramvalue FROM companyagentparams WHERE compid = ? AND agentid = ? AND paramid = ?")) {
            statement.setInt(1, compId);
            statement.setInt(2, agentId);
            statement.setString(3, paramId);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                paramVal = rs.getString("paramvalue");
            rs.close();
        } catch (Exception e) {
            System.out.println("Error querying table: " + e.toString());
        }
        return paramVal;
    }





}
