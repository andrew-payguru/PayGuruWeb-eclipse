package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import za.co.payguru.model.CompanyLoyalty;

public class CompanyLoyaltyDao {

	public static int getNextLoyaltyId(Connection connection) {
		int id = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('companyloyalty_loyaltyid_seq') AS id");
		){
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				id = rs.getInt("id");
			rs.close();
		}catch (Exception e) {
			System.out.println("Error getting next loyaltyid: " + e.toString());
		}
		return id;
	}
	public static CompanyLoyalty loadCompanyLoyalty(Connection connection, int compid, int comployaltyid) {
		CompanyLoyalty compLoyalty = new CompanyLoyalty();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYLOYALTY WHERE compid = ? AND comployaltyid = ? AND active = ?");
		){
			statement.setInt(1, compid);
			statement.setInt(2, comployaltyid);
			statement.setString(3, CompanyLoyalty.LOYALTY_ACTVIE);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				compLoyalty.setCompid(rs.getInt("compid"));
				compLoyalty.setComployaltyid(rs.getInt("comployaltyid"));
				compLoyalty.setComployaltydesc(rs.getString("comployaltydesc"));
				compLoyalty.setComployaltytype(rs.getInt("comployaltytype"));
				compLoyalty.setComployaltycapturetype(rs.getInt("comployaltycapturetype"));
				compLoyalty.setComployaltystart(rs.getDate("comployaltystart"));
				compLoyalty.setComployaltyexpire(rs.getDate("comployaltyexpire"));
				compLoyalty.setComployaltypointsperc(rs.getDouble("comployaltypointsperc"));
				compLoyalty.setComployaltypointsvalperc(rs.getDouble("comployaltypointsvalperc"));
				compLoyalty.setComployaltypointslifespan(rs.getInt("comployaltypointslifespan"));
				compLoyalty.setComployaltystatus(rs.getString("comployaltystatus"));
				compLoyalty.setComployaltystatusdate(rs.getDate("comployaltystatusdate"));
				compLoyalty.setComployaltystatustime(rs.getTime("comployaltystatustime"));
				compLoyalty.setActive(rs.getString("active"));
				compLoyalty.setCompanyloyaltyrewardtokenlifespan(rs.getInt("companyloyaltyrewardtokenlifespan"));

			}rs.close();
		}catch (Exception e) {
			System.out.println("Error loading COMPANYLOYALTY: " + e.toString());
		}
		return compLoyalty;
	}
	public static CompanyLoyalty addCompanyLoyalty(Connection connection, CompanyLoyalty compLoyalty) {
		compLoyalty.setComployaltyid(getNextLoyaltyId(connection));
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYLOYALTY VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		){
			statement.setInt(1, compLoyalty.getCompid());
			statement.setInt(2, compLoyalty.getComployaltyid());
			statement.setString(3, compLoyalty.getComployaltydesc());
			statement.setInt(4, compLoyalty.getComployaltytype());
			statement.setInt(5, compLoyalty.getComployaltycapturetype());
			statement.setDate(6, compLoyalty.getComployaltystart());
			statement.setDate(7, compLoyalty.getComployaltyexpire());
			statement.setDouble(8, compLoyalty.getComployaltypointsperc());
			statement.setDouble(9, compLoyalty.getComployaltypointsvalperc());
			statement.setInt(10, compLoyalty.getComployaltypointslifespan());
			statement.setString(11, compLoyalty.getComployaltystatus());
			statement.setDate(12, compLoyalty.getComployaltystatusdate());
			statement.setTime(13, compLoyalty.getComployaltystatustime());
			statement.setString(14, compLoyalty.getActive());
			statement.setInt(15, compLoyalty.getCompanyloyaltyrewardtokenlifespan());
			if(statement.executeUpdate()<=0)
				compLoyalty = new CompanyLoyalty();
		}catch (Exception e) {
			System.out.println("Error creating COMPANYLOYALTY: " + e.toString());
		}
		return compLoyalty;
	}
}
