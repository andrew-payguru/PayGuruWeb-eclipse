package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.Bank;

public class BankDao {

    public static int getNextBankId(Connection connection) {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('banks_bankid_seq') AS id")) {
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                id = rs.getInt("id");
            rs.close();
        } catch (Exception e) {
            System.out.println("Error getting next bankid: " + e.toString());
        }
        return id;
    }

    public static Bank loadBank(Connection connection, int bankid) {
        Bank bank = new Bank();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM banks WHERE bankid = ? AND bankactive = '1'")) {
            statement.setInt(1, bankid);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                bank.setBankid(rs.getInt("bankid"));
                bank.setBankdesc(rs.getString("bankdesc"));
                bank.setBankaccno(rs.getString("bankaccno"));
                bank.setBankactive(rs.getString("bankactive"));
                bank.setBankref1(rs.getString("bankref1"));
                bank.setBankref2(rs.getString("bankref2"));
                bank.setBankref3(rs.getString("bankref3"));
                bank.setBankref4(rs.getString("bankref4"));
                bank.setBankref5(rs.getString("bankref5"));
                bank.setBankref6(rs.getString("bankref6"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error loading bank: " + e.toString());
        }
        return bank;
    }
    
    public static ArrayList<Bank> getBanks(Connection connection){
    	ArrayList<Bank> banks = new ArrayList<Bank>();
    	try(
    		PreparedStatement statement = connection.prepareStatement("SELECT * FROM BANKS WHERE bankactive = ? ORDER BY bankid");
    	){
    		statement.setString(1, Bank.ACTIVE);
    		ResultSet rs = statement.executeQuery();
    		while(rs.next()) {
    			Bank bank = new Bank();
    			bank.setBankid(rs.getInt("bankid"));
                bank.setBankdesc(rs.getString("bankdesc"));
                bank.setBankaccno(rs.getString("bankaccno"));
                bank.setBankactive(rs.getString("bankactive"));
                bank.setBankref1(rs.getString("bankref1"));
                bank.setBankref2(rs.getString("bankref2"));
                bank.setBankref3(rs.getString("bankref3"));
                bank.setBankref4(rs.getString("bankref4"));
                bank.setBankref5(rs.getString("bankref5"));
                bank.setBankref6(rs.getString("bankref6"));
    			banks.add(bank);
    		}rs.close();
    	}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
    	return banks;
    }

    public static Bank addBank(Connection connection, Bank bank) {
        bank.setBankid(getNextBankId(connection));
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO banks VALUES (?,?,?,?,?,?,?,?,?,?)")) {
            statement.setInt(1, bank.getBankid());
            statement.setString(2, bank.getBankdesc());
            statement.setString(3, bank.getBankaccno());
            statement.setString(4, bank.getBankactive());
            statement.setString(5, bank.getBankref1());
            statement.setString(6, bank.getBankref2());
            statement.setString(7, bank.getBankref3());
            statement.setString(8, bank.getBankref4());
            statement.setString(9, bank.getBankref5());
            statement.setString(10, bank.getBankref6());
            if (statement.executeUpdate() <= 0)
                bank = new Bank();
        } catch (Exception e) {
            System.out.println("Error creating bank: " + e.toString());
        }
        return bank;
    }
    
    
    public static JSONArray getBanksJSON(ArrayList<Bank> banks) {
    	JSONArray jsonAr = new JSONArray();
    	try {
    		for(Bank bank : banks) {
    			jsonAr.put(bank.toJSON());
    		}
    	}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
    	return jsonAr;
    }
    
}
