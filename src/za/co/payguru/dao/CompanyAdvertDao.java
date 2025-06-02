package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import za.co.payguru.model.CompanyAdvert;
import za.co.payguru.util.DateUtil;

public class CompanyAdvertDao {

	public static int getNextAdId(Connection connection) {
		int id = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT nextval('companyadvert_compadid_seq') AS compadid");
				){
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				id = rs.getInt("compadid");
			rs.close();
		}catch (Exception e) {
			System.out.println("Error getting next value companyadvert_compadid_seq : " + e.toString());
		}
		return id;
	}

	public static ArrayList<CompanyAdvert> getCompanyAds(Connection connection, int compid){
		ArrayList<CompanyAdvert> adverts = new ArrayList<CompanyAdvert>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYADVERT WHERE compid = ? AND compadactive = ?");
				){
			statement.setInt(1, compid);
			statement.setString(2, CompanyAdvert.ADVERT_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyAdvert companyAd = new CompanyAdvert();
				companyAd.setCompid(rs.getInt("compid"));
				companyAd.setCompadid(rs.getInt("compadid"));
				companyAd.setCompaddesc(rs.getString("compaddesc"));
				companyAd.setCompadtype(rs.getInt("compadtype"));
				companyAd.setCompadlinktype(rs.getInt("compadlinktype"));
				companyAd.setCompadlink(rs.getString("compadlink"));
				companyAd.setCompadredirectref(rs.getString("compadredirectref"));
				companyAd.setCompaddata(rs.getString("compaddata"));
				companyAd.setCompaddataref(rs.getString("compaddataref"));
				companyAd.setCompadstartdate(rs.getDate("compadstartdate"));
				companyAd.setCompadenddate(rs.getDate("compadenddate"));
				companyAd.setCompadstatus(rs.getString("compadstatus"));
				companyAd.setCompadstatusdate(rs.getDate("compadstatusdate"));
				companyAd.setCompadstatustime(rs.getTime("compadstatustime"));
				companyAd.setCompadactive(rs.getString("compadactive"));
				adverts.add(companyAd);
			}rs.close();
		}catch (Exception e) {
			System.out.println("Error querying COMPANYADVERTS: " + e.toString());
		}
		return adverts;
	}

	public static long incrementHitCount(Connection connection,int advertid, int compid) {
		long hitCount = 0;
		boolean insert = true;
		try(
				Statement statement = connection.createStatement();
				){
			ResultSet rs = statement.executeQuery("SELECT hitcount FROM COMPANYADVERTHITCOUNT WHERE compid = "+compid+" AND advertid = "+advertid);
			if(rs.next()) {
				hitCount = rs.getLong("hitcount");
				insert = false;
			}
			++hitCount;
			if(insert)
				statement.executeUpdate("INSERT INTO COMPANYADVERTHITCOUNT VALUES("+compid+","+advertid+","+hitCount+")");
			else
				statement.executeUpdate("UPDATE COMPANYADVERTHITCOUNT SET hitcount = " +hitCount+" WHERE compid = "+compid+" AND advertid = "+advertid);
		}catch (Exception e) {
			System.out.println("Error querying table: " + e.toString());
		}
		return hitCount;
	}
	public static CompanyAdvert uploadAdvert(Connection connection, CompanyAdvert companyAdvert) {
		companyAdvert.setCompadid(companyAdvert.getCompadid()==0?getNextAdId(connection):companyAdvert.getCompadid());
		companyAdvert.setCompadstatus(CompanyAdvert.ADVERT_ACTIVE);
		companyAdvert.setCompadstatusdate(DateUtil.getCurrentCCYYMMDDDate());
		companyAdvert.setCompadstatustime(DateUtil.getCurrentHHMMSSTime());
		companyAdvert.setCompadactive(CompanyAdvert.ADVERT_ACTIVE);
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYADVERT VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				){
			statement.setInt(1, companyAdvert.getCompid());
			statement.setInt(2, companyAdvert.getCompadid());
			statement.setString(3, companyAdvert.getCompaddesc());
			statement.setInt(4, companyAdvert.getCompadtype());
			statement.setInt(5, companyAdvert.getCompadlinktype());
			statement.setString(6, companyAdvert.getCompadlink());
			statement.setString(7, companyAdvert.getCompadredirectref());
			statement.setString(8, companyAdvert.getCompaddata());
			statement.setString(9, companyAdvert.getCompaddataref());
			statement.setDate(10, companyAdvert.getCompadstartdate());
			statement.setDate(11, companyAdvert.getCompadenddate());
			statement.setString(12, companyAdvert.getCompadstatus());
			statement.setDate(13, companyAdvert.getCompadstatusdate());
			statement.setTime(14, companyAdvert.getCompadstatustime());
			statement.setString(15, companyAdvert.getCompadactive());
			if(statement.executeUpdate()<=0)
				companyAdvert = new CompanyAdvert();
		}catch (Exception e) {
			System.out.println("Error updating table COMPANYADVERT: " + e.toString());
		}
		return companyAdvert;
	}

	public static boolean createAdvertDataTable(Connection connection, String sql) {
		boolean created = false;
		try(
				Statement statement = connection.createStatement();
				){
			statement.executeUpdate(sql);
			created = true;				
		}catch (Exception e) {
			System.out.println("Error creating table! Query: " + sql);
			System.out.println(e.toString());
		}
		return created;
	}



	//JSON HELPER METHODS
	public static StringBuilder getCompanyAdvertJSON(CompanyAdvert compAd) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\"compid\" : "+compAd.getCompid()+",\n");
		sb.append("\"compadid\" : "+compAd.getCompadid()+",\n");
		sb.append("\"compaddesc\" : \""+compAd.getCompaddesc()+"\",\n");
		sb.append("\"compadtype\" : "+compAd.getCompadtype()+",\n");
		sb.append("\"compadlinktype\" : "+compAd.getCompadlinktype()+",\n");
		sb.append("\"compadlink\" : \""+compAd.getCompadlink()+"\",\n");
		sb.append("\"compadredirectref\" : \""+compAd.getCompadredirectref()+"\",\n");
		sb.append("\"compaddata\" : \""+compAd.getCompaddata()+"\",\n");
		sb.append("\"compaddataref\" : \""+compAd.getCompaddataref()+"\",\n");
		sb.append("\"compadstartdate\" : \""+compAd.getCompadstartdate()+"\",\n");
		sb.append("\"compadenddate\" : \""+compAd.getCompadenddate()+"\",\n");
		sb.append("\"compadstatus\" : \""+compAd.getCompadstatus()+"\",\n");
		sb.append("\"compadstatusdate\" : \""+compAd.getCompadstatusdate()+"\",\n");
		sb.append("\"compadstatustime\" : \""+compAd.getCompadstatustime()+"\",\n");
		sb.append("\"compadactive\" : \""+compAd.getCompadactive()+"\"\n");
		sb.append("}");
		return sb;
	}
	public static StringBuilder getCompanyAdvertsJSON(ArrayList<CompanyAdvert> compAds) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<compAds.size();i++) {
			CompanyAdvert compAd = compAds.get(i);
			sb.append((i==0 ? "" : ",")+"{\n");
			sb.append("\"compid\" : "+compAd.getCompid()+",\n");
			sb.append("\"compadid\" : "+compAd.getCompadid()+",\n");
			sb.append("\"compaddesc\" : \""+compAd.getCompaddesc()+"\",\n");
			sb.append("\"compadtype\" : "+compAd.getCompadtype()+",\n");
			sb.append("\"compadlinktype\" : "+compAd.getCompadlinktype()+",\n");
			sb.append("\"compadlink\" : \""+compAd.getCompadlink()+"\",\n");
			sb.append("\"compadredirectref\" : \""+compAd.getCompadredirectref()+"\",\n");
			sb.append("\"compaddata\" : \""+compAd.getCompaddata()+"\",\n");
			sb.append("\"compaddataref\" : \""+compAd.getCompaddataref()+"\",\n");
			sb.append("\"compadstartdate\" : \""+compAd.getCompadstartdate()+"\",\n");
			sb.append("\"compadenddate\" : \""+compAd.getCompadenddate()+"\",\n");
			sb.append("\"compadstatus\" : \""+compAd.getCompadstatus()+"\",\n");
			sb.append("\"compadstatusdate\" : \""+compAd.getCompadstatusdate()+"\",\n");
			sb.append("\"compadstatustime\" : \""+compAd.getCompadstatustime()+"\",\n");
			sb.append("\"compadactive\" : \""+compAd.getCompadactive()+"\"\n");
			sb.append("}\n");
		}
		sb.append("]");
		return sb;
	}
}
