package za.co.payguru.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.ClientCompanyBalance;
import za.co.payguru.model.ClientProduct;
import za.co.payguru.model.ClientProductRef;

public class ClientProductRefDao {
	public static ClientProductRef loadClientProductRef(Connection connection, int clientid, int compid, int prodid, String prodref) {
		return loadClientProductRef(connection, clientid, compid, prodid, prodref, -1);
	}
	
	public static ClientProductRef loadClientProductRef(Connection connection, int clientid, int compid, int prodid, String prodref, int donorid) {
		ClientProductRef prodRef = new ClientProductRef();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTREFS WHERE clientid = ? AND compid = ? AND prodid = ? AND prodref = ? AND donorid = ? AND prodrefactive = ?");
		){
			statement.setInt(1, clientid);
			statement.setInt(2, compid);
			statement.setInt(3, prodid);
			statement.setString(4, prodref);
			statement.setInt(5, donorid);
			statement.setString(6, ClientProductRef.ACTIVE);

			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				prodRef.setClientid(rs.getInt("clientid"));
				prodRef.setCompid(rs.getInt("compid"));
				prodRef.setProdid(rs.getInt("prodid"));
				prodRef.setProdref(rs.getString("prodref"));
				prodRef.setProdrefno(rs.getString("prodrefno"));
				prodRef.setProdreftype(rs.getInt("prodreftype"));
				prodRef.setProdrefactive(rs.getString("prodrefactive"));
				prodRef.setDonorid(rs.getInt("donorid"));
				prodRef.setExtref1(rs.getString("extref1"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTREFS: " + e.toString());
		}
		return prodRef;
	}
	public static ClientProductRef loadClientProductRefByReNo(Connection connection, String prodRefNo) {
		ClientProductRef prodRef = new ClientProductRef();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTREFS WHERE prodrefno = ? AND prodrefactive = ?");
		){
			statement.setString(1, prodRefNo);
			statement.setString(2, ClientProductRef.ACTIVE);

			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				prodRef.setClientid(rs.getInt("clientid"));
				prodRef.setCompid(rs.getInt("compid"));
				prodRef.setProdid(rs.getInt("prodid"));
				prodRef.setProdref(rs.getString("prodref"));
				prodRef.setProdrefno(rs.getString("prodrefno"));
				prodRef.setProdreftype(rs.getInt("prodreftype"));
				prodRef.setProdrefactive(rs.getString("prodrefactive"));
				prodRef.setDonorid(rs.getInt("donorid"));
				prodRef.setExtref1(rs.getString("extref1"));

			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTREFS: " + e.toString());
		}
		return prodRef;
	}
	public static ArrayList<ClientProductRef> getClientProductRefs(Connection connection, int clientid){
		ArrayList<ClientProductRef> prodRefs = new ArrayList<ClientProductRef>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTREFS WHERE clientid = ? AND prodrefactive = ?");
		){
			statement.setInt(1, clientid);
			statement.setString(2, ClientProductRef.ACTIVE);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				ClientProductRef prodRef = new ClientProductRef();
				prodRef.setClientid(rs.getInt("clientid"));
				prodRef.setCompid(rs.getInt("compid"));
				prodRef.setProdid(rs.getInt("prodid"));
				prodRef.setProdref(rs.getString("prodref"));
				prodRef.setProdrefno(rs.getString("prodrefno"));
				prodRef.setProdreftype(rs.getInt("prodreftype"));
				prodRef.setProdrefactive(rs.getString("prodrefactive"));
				prodRef.setDonorid(rs.getInt("donorid"));
				prodRef.setExtref1(rs.getString("extref1"));
				prodRefs.add(prodRef);
			}rs.close();
			
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTREFS: " + e.toString());
		}
		return prodRefs;
	}
	public static ArrayList<ClientProductRef> getClientCompProductRefs(Connection connection, int clientid, int compid){
		ArrayList<ClientProductRef> prodRefs = new ArrayList<ClientProductRef>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTREFS WHERE clientid = ? AND compid = ? AND prodrefactive = ?");
		){
			statement.setInt(1, clientid);
			statement.setInt(2, compid);
			statement.setString(3, ClientProductRef.ACTIVE);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				ClientProductRef prodRef = new ClientProductRef();
				prodRef.setClientid(rs.getInt("clientid"));
				prodRef.setCompid(rs.getInt("compid"));
				prodRef.setProdid(rs.getInt("prodid"));
				prodRef.setProdref(rs.getString("prodref"));
				prodRef.setProdrefno(rs.getString("prodrefno"));
				prodRef.setProdreftype(rs.getInt("prodreftype"));
				prodRef.setProdrefactive(rs.getString("prodrefactive"));
				prodRef.setDonorid(rs.getInt("donorid"));
				prodRef.setExtref1(rs.getString("extref1"));

				prodRefs.add(prodRef);
			}rs.close();
			
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTREFS: " + e.toString());
		}
		return prodRefs;
	}
	
	public static ArrayList<ClientProductRef> getClientProductsLikeProddataRefs(Connection connection, int compid, String ref1, String ref2, int prodreftype){
		ArrayList<ClientProductRef> prodRefs = new ArrayList<ClientProductRef>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTPRODUCTREFS WHERE clientid IN (SELECT clientid FROM CLIENTPRODUCTS WHERE compid = ? AND proddata ILIKE ? AND proddata ILIKE ? AND status = ? ORDER BY clientid) AND prodreftype = ?");
		){
			statement.setInt(1, compid);
			statement.setString(2, "%"+ref1+"%");
			statement.setString(3, "%"+ref2+"%");
			statement.setInt(4, ClientProduct.ACTIVE);
			statement.setInt(5, prodreftype);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				ClientProductRef prodRef = new ClientProductRef();
				prodRef.setClientid(rs.getInt("clientid"));
				prodRef.setCompid(rs.getInt("compid"));
				prodRef.setProdid(rs.getInt("prodid"));
				prodRef.setProdref(rs.getString("prodref"));
				prodRef.setProdrefno(rs.getString("prodrefno"));
				prodRef.setProdreftype(rs.getInt("prodreftype"));
				prodRef.setProdrefactive(rs.getString("prodrefactive"));
				prodRef.setDonorid(rs.getInt("donorid"));
				prodRef.setExtref1(rs.getString("extref1"));

				prodRefs.add(prodRef);
			}rs.close();
			
		}catch (Exception e) {
			System.out.println("Error querying table CLIENTPRODUCTREFS: " + e.toString());
		}
		return prodRefs;
	}
	
	public static JSONArray toJSONArray(ArrayList<ClientProductRef> prodRefs) {
		JSONArray jsonArray = new JSONArray();
		try {
			for(int i=0;i<prodRefs.size();i++) {
				jsonArray.put(prodRefs.get(i).toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString()); 
		}
		return jsonArray;
	}
}
