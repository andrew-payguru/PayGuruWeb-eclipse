package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.CompanyClientLoyalty;

public class CompanyClientLoyaltyDao {

	public static CompanyClientLoyalty loadCompanyClientLoyalty(Connection connection, int compid, int comployaltyid, int clientid) {
		CompanyClientLoyalty companyClientLoyalty = new CompanyClientLoyalty();
		try (
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM companyclientloyalty WHERE compid = ? AND comployaltyid = ? AND clientid = ? AND active = ?");
				) {
			statement.setInt(1, compid);
			statement.setInt(2, comployaltyid);
			statement.setInt(3, clientid);
			statement.setString(4, CompanyClientLoyalty.LOYALTY_ACTIVE);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				companyClientLoyalty.setCompid(rs.getInt("compid"));
				companyClientLoyalty.setComployaltyid(rs.getInt("comployaltyid"));
				companyClientLoyalty.setClientid(rs.getInt("clientid"));
				companyClientLoyalty.setLoyaltypointsbalance(rs.getDouble("loyaltypointsbalance"));
				companyClientLoyalty.setLoyaltypointsredeemed(rs.getDouble("loyaltypointsredeemed"));
				companyClientLoyalty.setLoyaltypointsexpired(rs.getDouble("loyaltypointsexpired"));
				companyClientLoyalty.setLoyaltystatus(rs.getString("loyaltystatus"));
				companyClientLoyalty.setLoyaltystatusdate(rs.getDate("loyaltystatusdate"));
				companyClientLoyalty.setLoyaltystatustime(rs.getTime("loyaltystatustime"));
				companyClientLoyalty.setLoyaltytier(rs.getInt("loyaltytier"));
				companyClientLoyalty.setCompcustomloyaltyno(rs.getString("compcustomloyaltyno"));
				companyClientLoyalty.setActive(rs.getString("active"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading COMPANYCLIENTLOYALTY: " + e.toString());
		}
		return companyClientLoyalty;
	}

	public static ArrayList<CompanyClientLoyalty> loadCompanyClientLoyaltys(Connection connection, int compid, int clientid) {
		ArrayList<CompanyClientLoyalty> loyaltys = new ArrayList<CompanyClientLoyalty>();
		try (
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM companyclientloyalty WHERE compid = ? AND clientid = ? AND active = ?");
				) {
			statement.setInt(1, compid);
			statement.setInt(2, clientid);
			statement.setString(3, CompanyClientLoyalty.LOYALTY_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				CompanyClientLoyalty companyClientLoyalty = new CompanyClientLoyalty();
				companyClientLoyalty.setCompid(rs.getInt("compid"));
				companyClientLoyalty.setComployaltyid(rs.getInt("comployaltyid"));
				companyClientLoyalty.setClientid(rs.getInt("clientid"));
				companyClientLoyalty.setLoyaltypointsbalance(rs.getDouble("loyaltypointsbalance"));
				companyClientLoyalty.setLoyaltypointsredeemed(rs.getDouble("loyaltypointsredeemed"));
				companyClientLoyalty.setLoyaltypointsexpired(rs.getDouble("loyaltypointsexpired"));
				companyClientLoyalty.setLoyaltystatus(rs.getString("loyaltystatus"));
				companyClientLoyalty.setLoyaltystatusdate(rs.getDate("loyaltystatusdate"));
				companyClientLoyalty.setLoyaltystatustime(rs.getTime("loyaltystatustime"));
				companyClientLoyalty.setLoyaltytier(rs.getInt("loyaltytier"));
				companyClientLoyalty.setCompcustomloyaltyno(rs.getString("compcustomloyaltyno"));
				companyClientLoyalty.setActive(rs.getString("active"));
				loyaltys.add(companyClientLoyalty);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error loading COMPANYCLIENTLOYALTY: " + e.toString());
		}
		return loyaltys;
	}


	public static CompanyClientLoyalty addCompanyClientLoyalty(Connection connection, CompanyClientLoyalty companyClientLoyalty) {
		try (
				PreparedStatement statement = connection.prepareStatement("INSERT INTO companyclientloyalty (compid, comployaltyid, clientid, loyaltypointsbalance, loyaltypointsredeemed, loyaltypointsexpired, loyaltystatus, loyaltystatusdate, loyaltystatustime, loyaltytier, compcustomloyaltyno, active) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
				) {
			statement.setInt(1, companyClientLoyalty.getCompid());
			statement.setInt(2, companyClientLoyalty.getComployaltyid());
			statement.setInt(3, companyClientLoyalty.getClientid());
			statement.setDouble(4, companyClientLoyalty.getLoyaltypointsbalance());
			statement.setDouble(5, companyClientLoyalty.getLoyaltypointsredeemed());
			statement.setDouble(6, companyClientLoyalty.getLoyaltypointsexpired());
			statement.setString(7, companyClientLoyalty.getLoyaltystatus());
			statement.setDate(8, companyClientLoyalty.getLoyaltystatusdate());
			statement.setTime(9, companyClientLoyalty.getLoyaltystatustime());
			statement.setInt(10, companyClientLoyalty.getLoyaltytier());
			statement.setString(11, companyClientLoyalty.getCompcustomloyaltyno());
			statement.setString(12, companyClientLoyalty.getActive());
			if (statement.executeUpdate() <= 0) {
				companyClientLoyalty = new CompanyClientLoyalty();
			}
		} catch (Exception e) {
			System.out.println("Error creating COMPANYCLIENTLOYALTY: " + e.toString());
		}
		return companyClientLoyalty;
	}

	public static CompanyClientLoyalty updateCompanyClientLoyalty(Connection connection, CompanyClientLoyalty companyClientLoyalty) {
		try (
				PreparedStatement statement = connection.prepareStatement("UPDATE companyclientloyalty SET loyaltypointsbalance = ?, loyaltypointsredeemed = ?, loyaltypointsexpired = ?, loyaltystatus = ?, loyaltystatusdate = ?, loyaltystatustime = ?, loyaltytier = ?, compcustomloyaltyno = ?, active = ? WHERE compid = ? AND comployaltyid = ? AND clientid = ?");
				) {
			statement.setDouble(1, companyClientLoyalty.getLoyaltypointsbalance());
			statement.setDouble(2, companyClientLoyalty.getLoyaltypointsredeemed());
			statement.setDouble(3, companyClientLoyalty.getLoyaltypointsexpired());
			statement.setString(4, companyClientLoyalty.getLoyaltystatus());
			statement.setDate(5, companyClientLoyalty.getLoyaltystatusdate());
			statement.setTime(6, companyClientLoyalty.getLoyaltystatustime());
			statement.setInt(7, companyClientLoyalty.getLoyaltytier());
			statement.setString(8, companyClientLoyalty.getCompcustomloyaltyno());
			statement.setString(9, companyClientLoyalty.getActive());
			statement.setInt(10, companyClientLoyalty.getCompid());
			statement.setInt(11, companyClientLoyalty.getComployaltyid());
			statement.setInt(12, companyClientLoyalty.getClientid());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error updating COMPANYCLIENTLOYALTY: " + e.toString());
		}
		return companyClientLoyalty;
	}

}
