package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.CompanyInternal;

public class CompanyInternalDao {

	public static CompanyInternal loadCompanyInternal(Connection connection, int compid, int compinternalid) {
		CompanyInternal companyInternal = new CompanyInternal();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYINTERNAL WHERE compid = ? AND compinternalid = ?");
		){
			statement.setInt(1, compid);
			statement.setInt(2, compinternalid);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				companyInternal.setCompid(rs.getInt("compid"));
				companyInternal.setCompinternalid(rs.getInt("compinternalid"));
				companyInternal.setCompinternalname(rs.getString("compinternalname"));
				companyInternal.setCompinternalref1(rs.getString("compinternalref1"));
				companyInternal.setCompinternalref2(rs.getString("compinternalref2"));
				companyInternal.setCompinternalref3(rs.getString("compinternalref3"));
				companyInternal.setCompinternalstatus(rs.getString("compinternalstatus"));
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return companyInternal;
	}
	
	public static ArrayList<CompanyInternal> loadCompanyInternals(Connection connection, int compid) {
		ArrayList<CompanyInternal> companyInternals = new ArrayList<CompanyInternal>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYINTERNAL WHERE compid = ?");
		){
			statement.setInt(1, compid);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyInternal companyInternal = new CompanyInternal();
				companyInternal.setCompid(rs.getInt("compid"));
				companyInternal.setCompinternalid(rs.getInt("compinternalid"));
				companyInternal.setCompinternalname(rs.getString("compinternalname"));
				companyInternal.setCompinternalref1(rs.getString("compinternalref1"));
				companyInternal.setCompinternalref2(rs.getString("compinternalref2"));
				companyInternal.setCompinternalref3(rs.getString("compinternalref3"));
				companyInternal.setCompinternalstatus(rs.getString("compinternalstatus"));
				companyInternals.add(companyInternal);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return companyInternals;
	}
	
	public static StringBuilder getCompanyInternalsJSON(ArrayList<CompanyInternal> companyInternals) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<companyInternals.size();i++) {
			CompanyInternal compInternal = companyInternals.get(i);
			sb.append("{\n");
			sb.append("\"compid\" : "+compInternal.getCompid()+",\n");
			sb.append("\"compinternalid\" : "+compInternal.getCompinternalid()+",\n");
			sb.append("\"compinternalname\" : \""+compInternal.getCompinternalname()+"\",\n");
			sb.append("\"compinternalref1\" : \""+compInternal.getCompinternalref1()+"\",\n");
			sb.append("\"compinternalref2\" : \""+compInternal.getCompinternalref2()+"\",\n");
			sb.append("\"compinternalref3\" : \""+compInternal.getCompinternalref3()+"\",\n");
			sb.append("\"compinternalstatus\" : \""+compInternal.getCompinternalstatus()+"\"\n");
			sb.append("}" + (i==companyInternals.size()-1 ? "" : ",") + "\n");
		}
		sb.append("]");
		return sb;
	}
}
