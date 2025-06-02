package za.co.payguru.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Utility {

	public static void main(String[] args) {
		if(args.length<=0) {
			System.out.println("Provide method name!");
			return;
		}
		if(args[0].equalsIgnoreCase("clientcelltofile")) {
			clientCellToFile();
		}else if(args[0].equalsIgnoreCase("hollard_report_1")) {
			if(args.length<4){
				System.out.println("hollard_report_1 needs more args");
				return;
			}
			String paynext = args[1];
			String dateFrom = args[2];
			String dateTo = args[3];
			hollardReport1(paynext,dateFrom,dateTo);
		}
			
	}
	public static void clientCellToFile() {
		try(
				Connection connection = DBUtil.getConnection("payguru","postgres","");
				PreparedStatement statement = connection.prepareStatement("SELECT clientcellno FROM CLIENTS WHERE clientid IN (SELECT clientid FROM COMPANYCLIENTS WHERE compid = 15)");
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/tmp/hollard_cells.txt")))
		){
			ResultSet rs = statement.executeQuery();
			ArrayList<String> cells = new ArrayList<String>(); 
			while(rs.next()) {
				String cell = rs.getString("clientcellno");
				cells.add(cell);
			}rs.close();
			bw.write("");
			for(String cell : cells) {
				bw.append(cell+"\n");
			}
			System.out.println("DONE");
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	public static void hollardReport1(String paynext, String dateFrom, String dateTo) {
		try(
				Connection connection = DBUtil.getConnection("payguru","postgres","");
				PreparedStatement statement = connection.prepareStatement("select payref,count(*) from clientinvoices where compid = 15 and status = '4' and paynext = ? and paydate >= ? and paydate <= ? group by payref order by payref");
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File("/tmp/hollard_policy_"+paynext+".txt")))
		){
			statement.setString(1, paynext);
			statement.setString(2, dateFrom);
			statement.setString(3, dateTo);
			ResultSet rs = statement.executeQuery();
			ArrayList<String> data = new ArrayList<String>();
			while(rs.next()) {
				String payRef = rs.getString("payref");
				int count = rs.getInt("count");
				data.add(payRef.toUpperCase()+","+count);
			}rs.close();
			bw.write("");
			for(String line : data) {
				bw.append(line+"\n");
			}
			System.out.println("DONE");
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
