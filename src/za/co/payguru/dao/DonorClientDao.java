package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import za.co.payguru.model.DonorClient;



public class DonorClientDao {

    public static DonorClient loadDonorClient(Connection connection, int donorid, int clientid) {
    	DonorClient donorClient = new DonorClient();
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM donorclients WHERE donorid = ? AND clientid = ?")
        ) {
            statement.setInt(1, donorid);
            statement.setInt(2, clientid);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                donorClient.setDonorid(rs.getInt("donorid"));
                donorClient.setClientid(rs.getInt("clientid"));
                donorClient.setStatus(rs.getInt("status"));
                donorClient.setStatustime(rs.getTime("statustime"));
                donorClient.setStatusdate(rs.getDate("statusdate"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error loading DonorClient: " + e.toString());
        }
        return donorClient;
    }

    public static DonorClient addDonorClient(Connection connection, DonorClient donorClient) {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO donorclients (donorid, clientid, status, statustime, statusdate) VALUES (?,?,?,?,?)")
        ) {
            statement.setInt(1, donorClient.getDonorid());
            statement.setInt(2, donorClient.getClientid());
            statement.setInt(3, donorClient.getStatus());
            statement.setTime(4, donorClient.getStatustime());
            statement.setDate(5, donorClient.getStatusdate());
            if (statement.executeUpdate() <= 0) {
                donorClient = new DonorClient();
            }
        } catch (Exception e) {
            System.out.println("Error creating DonorClient: " + e.toString());
        }
        return donorClient;
    }

    public static DonorClient updateDonorClient(Connection connection, DonorClient donorClient) {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE donorclients SET status = ?, statustime = ?, statusdate = ? WHERE donorid = ? AND clientid = ?")
        ) {
            statement.setInt(1, donorClient.getStatus());
            statement.setTime(2, donorClient.getStatustime());
            statement.setDate(3, donorClient.getStatusdate());
            statement.setInt(4, donorClient.getDonorid());
            statement.setInt(5, donorClient.getClientid());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating DonorClient: " + e.toString());
        }
        return donorClient;
    }
}
