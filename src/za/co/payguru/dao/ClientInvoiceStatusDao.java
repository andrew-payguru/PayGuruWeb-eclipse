package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.ClientInvoiceStatus;

public class ClientInvoiceStatusDao {
	
	public static ClientInvoiceStatus loadClientInvoiceStatus(Connection connection, String invno, int compId) {
        ClientInvoiceStatus invoiceStatus = new ClientInvoiceStatus();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM clientinvoicestatus WHERE invno = ? AND compid = ?")) {
            statement.setString(1, invno);
            statement.setInt(2, compId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                invoiceStatus.setInvNo(rs.getString("invno"));
                invoiceStatus.setExtRefNo(rs.getString("extrefno"));
                invoiceStatus.setCompId(rs.getInt("compid"));
                invoiceStatus.setStatus(rs.getString("status"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error querying clientinvoicestatus: " + e.toString());
        }
        return invoiceStatus;
    }
	

    public static ClientInvoiceStatus loadClientInvoiceStatusbyRef(Connection connection, String extRefNo, int compId) {
        ClientInvoiceStatus invoiceStatus = new ClientInvoiceStatus();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM clientinvoicestatus WHERE extrefno = ? AND compid = ?")) {
            statement.setString(1, extRefNo);
            statement.setInt(2, compId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                invoiceStatus.setInvNo(rs.getString("invno"));
                invoiceStatus.setExtRefNo(rs.getString("extrefno"));
                invoiceStatus.setCompId(rs.getInt("compid"));
                invoiceStatus.setStatus(rs.getString("status"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error querying clientinvoicestatus: " + e.toString());
        }
        return invoiceStatus;
    }


    public static boolean addClientInvoiceStatus(Connection connection, ClientInvoiceStatus invoiceStatus) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO clientinvoicestatus (invno, extrefno, compid, status) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, invoiceStatus.getInvNo());
            statement.setString(2, invoiceStatus.getExtRefNo());
            statement.setInt(3, invoiceStatus.getCompId());
            statement.setString(4, invoiceStatus.getStatus());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error inserting into clientinvoicestatus: " + e.toString());
        }
        return false;
    }

    public static boolean updateClientInvoiceStatus(Connection connection, ClientInvoiceStatus invoiceStatus) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE clientinvoicestatus SET invno = ?, status = ? WHERE extrefno = ? AND compid = ?")) {
            statement.setString(1, invoiceStatus.getInvNo());
            statement.setString(2, invoiceStatus.getStatus());
            statement.setString(3, invoiceStatus.getExtRefNo());
            statement.setInt(4, invoiceStatus.getCompId());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error updating clientinvoicestatus: " + e.toString());
        }
        return false;
    }

    public static boolean deleteClientInvoiceStatus(Connection connection, String extRefNo, int compId) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM clientinvoicestatus WHERE extrefno = ? AND compid = ?")) {
            statement.setString(1, extRefNo);
            statement.setInt(2, compId);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error deleting from clientinvoicestatus: " + e.toString());
        }
        return false;
    }
}
