package za.co.payguru.dao;

import java.nio.file.spi.FileSystemProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.ClientCompanyBankRef;

public class ClientCompanyBankRefDao {

	public static ClientCompanyBankRef loadRecord(Connection connection, int clientid, int compid, int bankid, int subacc) {
		ClientCompanyBankRef bankRef = new ClientCompanyBankRef();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTCOMPANYBANKREFS WHERE clientid = ? AND compid = ? AND bankid = ? and subacc = ? ");
		){
			statement.setInt(1, clientid);
			statement.setInt(2, compid);
			statement.setInt(3, bankid);
			statement.setInt(4, subacc);
			
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				bankRef.setClientid(rs.getInt("clientid"));
				bankRef.setCompid(rs.getInt("compid"));
				bankRef.setBankid(rs.getInt("bankid"));
				bankRef.setSubacc(rs.getInt("subacc"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return bankRef;
	}
	
	public static ArrayList<ClientCompanyBankRef> getClientCompBankRefs(Connection connection, int clientid){
		ArrayList<ClientCompanyBankRef> bankRefs = new ArrayList<ClientCompanyBankRef>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTCOMPANYBANKREFS WHERE clientid = ? ORDER BY compid, subacc");
		){
			statement.setInt(1, clientid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientCompanyBankRef bankRef = new ClientCompanyBankRef();
				bankRef.setClientid(rs.getInt("clientid"));
				bankRef.setCompid(rs.getInt("compid"));
				bankRef.setBankid(rs.getInt("bankid"));
				bankRef.setBankref(rs.getString("bankref"));
				bankRef.setSubacc(rs.getInt("subacc"));
				bankRefs.add(bankRef);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table ClientCompanyBankRef: " + e.toString());
		}
		return bankRefs;
	}
	public static ArrayList<ClientCompanyBankRef> getClientCompBankRefs(Connection connection, int compid, int clientid){
		ArrayList<ClientCompanyBankRef> bankRefs = new ArrayList<ClientCompanyBankRef>();
		try(
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTCOMPANYBANKREFS WHERE compid = ? AND clientid = ? ORDER BY subacc");
		){
			statement.setInt(1, compid);
			statement.setInt(2, clientid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				ClientCompanyBankRef bankRef = new ClientCompanyBankRef();
				bankRef.setClientid(rs.getInt("clientid"));
				bankRef.setCompid(rs.getInt("compid"));
				bankRef.setBankid(rs.getInt("bankid"));
				bankRef.setBankref(rs.getString("bankref"));
				bankRef.setSubacc(rs.getInt("subacc"));
				bankRefs.add(bankRef);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table ClientCompanyBankRef: " + e.toString());
		}
		return bankRefs;
	}
	public static JSONArray toJSONArray(ArrayList<ClientCompanyBankRef> bankRefs) {
		JSONArray jsonAr = new JSONArray();
		try {
			for(ClientCompanyBankRef bankRef : bankRefs) {
				jsonAr.put(bankRef.toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return jsonAr;
	}
}
