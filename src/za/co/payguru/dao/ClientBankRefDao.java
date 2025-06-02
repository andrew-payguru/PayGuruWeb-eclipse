package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.Bank;
import za.co.payguru.model.ClientBankRef;

public class ClientBankRefDao {

    public static ClientBankRef loadClientBankRef(Connection connection, int clientid, int bankid, String bankref) {
        ClientBankRef clientBankRef = new ClientBankRef();
        try (
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTBANKREFS WHERE clientid = ? AND bankid = ? AND bankref = ?");
        ) {
            statement.setInt(1, clientid);
            statement.setInt(2, bankid);
            statement.setString(3, bankref);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                clientBankRef.setClientid(rs.getInt("clientid"));
                clientBankRef.setBankid(rs.getInt("bankid"));
                clientBankRef.setBankref(rs.getString("bankref"));
                clientBankRef.setSubacc(rs.getInt("subacc"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error loading ClientBankRef: " + e.toString());
        }
        return clientBankRef;
    }

    public static ClientBankRef addClientBankRef(Connection connection, ClientBankRef clientBankRef) {
        try (
            PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENTBANKREFS VALUES (?, ?, ?, ?)");
        ) {
            statement.setInt(1, clientBankRef.getClientid());
            statement.setInt(2, clientBankRef.getBankid());
            statement.setString(3, clientBankRef.getBankref());
            statement.setInt(4, clientBankRef.getSubacc());
            if (statement.executeUpdate() <= 0)
                clientBankRef = new ClientBankRef();
        } catch (Exception e) {
            System.out.println("Error creating ClientBankRef: " + e.toString());
        }
        return clientBankRef;
    }
    
    public static ArrayList<ClientBankRef> getClientBankRefs(Connection connection, int clientid){
    	ArrayList<ClientBankRef> bankRefs = new ArrayList<ClientBankRef>();
    	try (
    			PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTBANKREFS WHERE clientid = ? ORDER BY bankid, subacc");
    	){
    		statement.setInt(1, clientid);
    		ResultSet rs = statement.executeQuery();
    		while(rs.next()) {
    			ClientBankRef bankRef = new ClientBankRef();
    			bankRef.setClientid(rs.getInt("clientid"));
    			bankRef.setBankid(rs.getInt("bankid"));
    			bankRef.setBankref(rs.getString("bankref"));
    			bankRef.setSubacc(rs.getInt("subacc"));
    			bankRefs.add(bankRef);
    		}rs.close();
    	}catch (Exception e) {
			System.out.println("Error querying table CLIENTBANKREFS: " + e.toString());
		}
    	return bankRefs;
    }
    public static JSONArray getClientBankRefsJSON(ArrayList<ClientBankRef> bankRefs) {
    	JSONArray jsonAr = new JSONArray();
    	try {
    		for(ClientBankRef bankRef : bankRefs) {
    			jsonAr.put(bankRef.toJSON());
    		}
    	}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
    	return jsonAr;
    }
    
}
