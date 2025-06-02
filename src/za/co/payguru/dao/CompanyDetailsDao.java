package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import za.co.payguru.model.CompanyDetails;

public class CompanyDetailsDao {
	public static CompanyDetails loadCompanyDetails(Connection connection, int compid) {
		CompanyDetails compdetails = new CompanyDetails();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYDETAILS WHERE compid = ?");
		){
			statement.setInt(1, compid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				compdetails.setCompid(rs.getInt("compid"));
				compdetails.setCompref1(rs.getString("compref1"));
				compdetails.setCompref2(rs.getString("compref2"));
				compdetails.setCompref3(rs.getString("compref3"));
				compdetails.setCompref4(rs.getString("compref4"));
				compdetails.setCompref5(rs.getString("compref5"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table COMPANYDETAILS: " + e.toString());
		}
		return compdetails;
	}
}
