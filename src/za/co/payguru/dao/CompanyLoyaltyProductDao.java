package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import za.co.payguru.model.CompanyLoyaltyProduct;

public class CompanyLoyaltyProductDao {

	public static CompanyLoyaltyProduct addCompanyLoyaltyProduct(Connection connection, CompanyLoyaltyProduct loyaltyProd) {
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYLOYALTYPRODUCTS VALUES (?,?,?,?,?,?)");
		){
			statement.setInt(1, loyaltyProd.getCompid());
			statement.setInt(2, loyaltyProd.getComployaltyid());
			statement.setLong(3, loyaltyProd.getProdid());
			statement.setDouble(4, loyaltyProd.getProdpointperc());
			statement.setInt(5, loyaltyProd.getProdpointlifespan());
			statement.setString(6, loyaltyProd.getProdactive());
			if(statement.executeUpdate()<=0) 
				loyaltyProd = new CompanyLoyaltyProduct();
			
		}catch (Exception e) {
			System.out.println("Error Updating COMPANYLOYALTYPRODUCTS: " + e.toString());
		}
		return loyaltyProd;
	}
}
