package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import za.co.payguru.model.CompanyParam;

public class CompanyParamDao {

	public static CompanyParam getCompanyParam(Connection connection, int compid, String paramid) {
		CompanyParam compParam = new CompanyParam();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYPARAMS WHERE compid = ? AND paramid = ?");
		){
			statement.setInt(1, compid);
			statement.setString(2, paramid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				compParam.setCompid(rs.getInt("compid"));
				compParam.setParamid(rs.getString("paramid"));
				compParam.setParamvalue(rs.getString("paramvalue"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYPARAMS : " + e.toString());
		}
		return compParam;
	}
	
	public static String getCompParamValue(Connection connection, int compid, String paramid) {
		String value = "";
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT paramvalue FROM COMPANYPARAMS WHERE compid = ? AND paramid = ?");
		){
			statement.setInt(1, compid);
			statement.setString(2, paramid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) 
				value = rs.getString("paramvalue");
			rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYPARAMS : " + e.toString());
		}
		return value;
	}
	
	public static StringBuilder getParamValueJSON(CompanyParam compParam) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\"compid\" : "+compParam.getCompid()+",\n");
		sb.append("\"paramid\" : \""+compParam.getParamid()+"\",\n");
		sb.append("\"paramvalue\" : \""+compParam.getParamvalue()+"\"\n");
		sb.append("}");
		return sb;
	}
}
