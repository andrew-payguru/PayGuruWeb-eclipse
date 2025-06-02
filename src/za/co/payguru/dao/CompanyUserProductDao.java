package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.CompanyUserProduct;

public class CompanyUserProductDao {

	public static boolean addCompUserProd(Connection connection, CompanyUserProduct compUserProd) {
		boolean created = false;
		try(
			PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYUSERPRODUCTS VALUES (?,?,?,?)");
		){
			statement.setInt(1, compUserProd.getCompId());
			statement.setInt(2, compUserProd.getUserId());
			statement.setInt(3, compUserProd.getProdId());
			statement.setString(4, compUserProd.getProdActive());
			
			if(statement.executeUpdate()>0)
				created = true;
		}catch (Exception e) {
			System.out.println("Error creating COMPANYUSERPRODUCT: " + e.toString());
		}
		return created;
	}
	
	public static ArrayList<CompanyUserProduct> getCompanyUserProds(Connection connection, int compId){
		ArrayList<CompanyUserProduct> userProds = new ArrayList<CompanyUserProduct>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYUSERPRODUCTS WHERE compid = ? AND prodactive = ?");
		){
			statement.setInt(1, compId);
			statement.setString(2, CompanyUserProduct.PROD_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyUserProduct userProd = new CompanyUserProduct();
				userProd.setCompId(rs.getInt("compid"));
				userProd.setUserId(rs.getInt("userid"));
				userProd.setProdId(rs.getInt("prodid"));
				userProd.setProdActive(rs.getString("prodactive"));
				userProds.add(userProd);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying COMPANYUSERPRODUCTS: " + e.toString());
		}
		return userProds;
	}
	
	public static ArrayList<CompanyUserProduct> getCompanyUserProds(Connection connection, int compId, int userId){
		ArrayList<CompanyUserProduct> userProds = new ArrayList<CompanyUserProduct>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYUSERPRODUCTS WHERE compid = ? AND userid = ? AND prodactive = ?");
		){
			statement.setInt(1, compId);
			statement.setInt(2, userId);
			statement.setString(3, CompanyUserProduct.PROD_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyUserProduct userProd = new CompanyUserProduct();
				userProd.setCompId(rs.getInt("compid"));
				userProd.setUserId(rs.getInt("userid"));
				userProd.setProdId(rs.getInt("prodid"));
				userProd.setProdActive(rs.getString("prodactive"));
				userProds.add(userProd);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying COMPANYUSERPRODUCTS: " + e.toString());
		}
		return userProds;
	}
	public static boolean deleteCompUserProd(Connection connection, CompanyUserProduct userProd) {
		boolean deleted = false;
		try(
			PreparedStatement statement = connection.prepareStatement("DELETE FROM COMPANYUSERPRODUCTS WHERE compid = ? AND userid = ? AND prodid = ?");
		){
			statement.setInt(1, userProd.getCompId());
			statement.setInt(2, userProd.getUserId());
			statement.setInt(3, userProd.getProdId());
			if(statement.executeUpdate()>0)
				deleted = true;
		}catch (Exception e) {
			System.out.println("Error deleting COMPANYUSER: " + e.toString());
		}
		return deleted;
	}
	public static boolean deleteAllUserProds(Connection connection, int compid, int userid) {
		boolean deleted = false;
		try(
			PreparedStatement statement = connection.prepareStatement("DELETE FROM COMPANYUSERPRODUCTS WHERE compid = ? AND userid = ?");
		){
			statement.setInt(1, compid);
			statement.setInt(2, userid);
			if(statement.executeUpdate()>0)
				deleted = true;
		}catch (Exception e) {
			System.out.println("Error deleting COMPANYUSER: " + e.toString());
		}
		return deleted;
	}
	public static JSONArray getCompUserProdsJSON(ArrayList<CompanyUserProduct> userProds) {
		JSONArray jsonAr = new JSONArray();
		try {
			for(CompanyUserProduct prod : userProds) {
				jsonAr.put(prod.toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return jsonAr;
	}
}
