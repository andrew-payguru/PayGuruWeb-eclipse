package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import za.co.payguru.model.ClientInvoiceItem;

public class ClientInvoiceItemDao {

	public static ClientInvoiceItem loadInvoiceItem(Connection connection, String invno, int compid, int prodid) {
		ClientInvoiceItem invoiceitem = new ClientInvoiceItem();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTINVOICEITEMS WHERE invno = ? AND compid = ? AND prodid = ?");
		){
			statement.setString(1, invno);
			statement.setInt(2, compid);
			statement.setInt(3, prodid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				invoiceitem.setInvNo(rs.getString("invno"));
				invoiceitem.setCompId(rs.getInt("compid"));
				invoiceitem.setProdId(rs.getInt("prodid"));
				invoiceitem.setProdQty(rs.getInt("prodqty"));
				invoiceitem.setProdRsp(rs.getDouble("prodrsp"));
				invoiceitem.setProdRef(rs.getString("prodref"));
				invoiceitem.setProdLink(rs.getString("prodlink"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICEITEMS: " + e.toString());
		}
		return invoiceitem;
	}
}
