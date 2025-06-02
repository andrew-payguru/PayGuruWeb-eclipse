package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import za.co.payguru.model.Param;
import za.co.payguru.util.DBUtil;

public class ParamDao extends TemplateDao{
	public static String getParam(String paramID, Connection connection){
		String param = "";
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM PARAMS WHERE paramid = ?");
		){
			statement.setString(1, paramID);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) 
				param = rs.getString("paramvalue");
			rs.close();
		}catch(Exception e) {
			System.out.println("Error querying PARAMS table: " + e.toString());
		}
		return param;
	}
}
