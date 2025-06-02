package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.ClientInvoiceExtDocItem;

public class ClientInvoiceExtDocItemDao {

    public static ClientInvoiceExtDocItem loadClientInvoiceExtDocItem(Connection connection, String invNo, int compId, int extDocType) {
    	ClientInvoiceExtDocItem item = new ClientInvoiceExtDocItem();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM clientinvoiceextdocitems WHERE invno = ? AND compid = ? AND extdoctype = ?")) {
            statement.setString(1, invNo);
            statement.setInt(2, compId);
            statement.setInt(3, extDocType);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                item.setInvNo(rs.getString("invno"));
                item.setCompId(rs.getInt("compid"));
                item.setExtDocType(rs.getInt("extdoctype"));
                item.setExtDocData(rs.getString("extdocdata"));
                item.setExtDocRef1(rs.getString("extdocref1"));
                item.setExtDocRef2(rs.getString("extdocref2"));
                item.setExtDocRef3(rs.getString("extdocref3"));
                item.setExtDocStatus(rs.getString("extdocstatus"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error querying table: " + e.toString());
        }
        return item;
    }

    public static ClientInvoiceExtDocItem addClientInvoiceExtDocItem(Connection connection, ClientInvoiceExtDocItem item) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO clientinvoiceextdocitems (invno, compid, extdoctype, extdocdata, extdocref1, extdocref2, extdocref3, extdocstatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, item.getInvNo());
            statement.setInt(2, item.getCompId());
            statement.setInt(3, item.getExtDocType());
            statement.setString(4, item.getExtDocData());
            statement.setString(5, item.getExtDocRef1());
            statement.setString(6, item.getExtDocRef2());
            statement.setString(7, item.getExtDocRef3());
            statement.setString(8, item.getExtDocStatus());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting into table: " + e.toString());
        }
        return item;
    }

    public static ArrayList<ClientInvoiceExtDocItem> getClientInvoiceExtDocItemsByStatus(Connection connection, String status) {
        ArrayList<ClientInvoiceExtDocItem> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM clientinvoiceextdocitems WHERE extdocstatus = ?")) {
            statement.setString(1, status);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	ClientInvoiceExtDocItem item = new ClientInvoiceExtDocItem();
                item.setInvNo(rs.getString("invno"));
                item.setCompId(rs.getInt("compid"));
                item.setExtDocType(rs.getInt("extdoctype"));
                item.setExtDocData(rs.getString("extdocdata"));
                item.setExtDocRef1(rs.getString("extdocref1"));
                item.setExtDocRef2(rs.getString("extdocref2"));
                item.setExtDocRef3(rs.getString("extdocref3"));
                item.setExtDocStatus(rs.getString("extdocstatus"));
                items.add(item);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error querying table: " + e.toString());
        }
        return items;
    }
}
