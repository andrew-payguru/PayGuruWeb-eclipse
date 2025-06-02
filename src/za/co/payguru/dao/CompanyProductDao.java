package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.model.CompanyProduct;

public class CompanyProductDao {
	
	public static int getNextProdId(Connection connection) {
		int prodid = 0;
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT nextval('companyproducts_prodid_seq') AS prodid"); 
		){
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				prodid = rs.getInt("prodid");
		}catch (Exception e) {
			System.out.println("Error querying sequence companyproducts_prodid_seq: " + e.toString());
		}
		return prodid;
	}
	
	public static CompanyProduct createCompanyProduct(Connection connection,CompanyProduct companyProduct) {
		companyProduct.setProdId(getNextProdId(connection));
		try(
				PreparedStatement statement = connection.prepareStatement("INSERT INTO COMPANYPRODUCTS (compid,prodid,prodcode,prodname,prodshortname,prodtype,"
						+ "prodtypeamt1,prodtypeamt2,prodtypersp,prodtypecost,prodjoinname,prodprorata,prodtypepay,prodtyperebill,prodtypecycle,prodstartdate,prodenddate,"
						+ "prodactive,prodsmsmessage1,prodsmsdays1,prodsmsdays2,prodextref1,prodextref2,prodextamt1,prodsmsmessage2,prodstruct,prodstructamt1,prodstructamt2,"
						+ "prodsmsmessage3,compinternalid) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		){
			statement.setInt(1, companyProduct.getCompId());
			statement.setLong(2, companyProduct.getProdId());
			statement.setString(3, companyProduct.getProdCode());
			statement.setString(4, companyProduct.getProdName());
			statement.setString(5, companyProduct.getProdShortName());
			statement.setInt(6, companyProduct.getProdType());
			statement.setDouble(7, companyProduct.getProdTypeAmt1());
			statement.setDouble(8, companyProduct.getProdTypeAmt2());
			statement.setDouble(9, companyProduct.getProdTypeRsp());
			statement.setDouble(10, companyProduct.getProdTypeCost());
			statement.setString(11, companyProduct.getProdJoinName());
			statement.setString(12, companyProduct.getProdProRata());
			statement.setInt(13, companyProduct.getProdTypePay());
			statement.setInt(14, companyProduct.getProdTypeRebill());
			statement.setDouble(15, companyProduct.getProdTypeCycle());
			statement.setString(16, companyProduct.getProdStartDate());
			statement.setString(17, companyProduct.getProdEndDate());
			statement.setString(18, companyProduct.getProdActive());
			statement.setString(19, companyProduct.getProdSmsMessage1());
			statement.setInt(20, companyProduct.getProdSmsDays1());
			statement.setInt(21, companyProduct.getProdSmsDays2());
			statement.setString(22, companyProduct.getProdExtRef1());
			statement.setString(23, companyProduct.getProdExtRef2());
			statement.setDouble(24, companyProduct.getProdExtAmt1());
			statement.setString(25, companyProduct.getProdSmsMessage2());
			statement.setInt(26, companyProduct.getProdStruct());
			statement.setDouble(27, companyProduct.getProdStructAmt1());
			statement.setDouble(28, companyProduct.getProdStructAmt2());
			statement.setString(29, companyProduct.getProdSmsMessage3());
			statement.setInt(30, companyProduct.getCompInternalId());
			if(statement.executeUpdate()<=0)
				companyProduct = new CompanyProduct();
			
		}catch (Exception e) {
			System.out.println("Error updating tabl COMPANYPRODUCTS: " + e.toString());
			companyProduct = new CompanyProduct();
		}
		
		return companyProduct;
	}
	
	public static CompanyProduct updateCompanyProduct(Connection connection,CompanyProduct companyProduct) {
		try(
				PreparedStatement statement = connection.prepareStatement("UPDATE COMPANYPRODUCTS set prodcode=?,prodname=?,prodshortname=?,prodtype=?,"
						+ "prodtypeamt1=?,prodtypeamt2=?,prodtypersp=?,prodtypecost=?,prodjoinname=?,prodprorata=?,prodtypepay=?,prodtyperebill=?,prodtypecycle=?,"
						+ "prodstartdate=?,prodenddate=?,prodactive=?,prodsmsmessage1=?,prodsmsdays1=?,prodsmsdays2=?,prodextref1=?,prodextref2=?,prodextamt1=?,"
						+ "prodsmsmessage2=?,prodstruct=?,prodstructamt1=?,prodstructamt2=?,prodsmsmessage3=?,compinternalid=? WHERE compid = ? AND prodid = ?");
		){
			
			statement.setString(1, companyProduct.getProdCode());
			statement.setString(2, companyProduct.getProdName());
			statement.setString(3, companyProduct.getProdShortName());
			statement.setInt(4, companyProduct.getProdType());
			statement.setDouble(5, companyProduct.getProdTypeAmt1());
			statement.setDouble(6, companyProduct.getProdTypeAmt2());
			statement.setDouble(7, companyProduct.getProdTypeRsp());
			statement.setDouble(8, companyProduct.getProdTypeCost());
			statement.setString(9, companyProduct.getProdJoinName());
			statement.setString(10, companyProduct.getProdProRata());
			statement.setInt(11, companyProduct.getProdTypePay());
			statement.setInt(12, companyProduct.getProdTypeRebill());
			statement.setDouble(13, companyProduct.getProdTypeCycle());
			statement.setString(14, companyProduct.getProdStartDate());
			statement.setString(15, companyProduct.getProdEndDate());
			statement.setString(16, companyProduct.getProdActive());
			statement.setString(17, companyProduct.getProdSmsMessage1());
			statement.setInt(18, companyProduct.getProdSmsDays1());
			statement.setInt(19, companyProduct.getProdSmsDays2());
			statement.setString(20, companyProduct.getProdExtRef1());
			statement.setString(21, companyProduct.getProdExtRef2());
			statement.setDouble(22, companyProduct.getProdExtAmt1());
			statement.setString(23, companyProduct.getProdSmsMessage2());
			statement.setInt(24, companyProduct.getProdStruct());
			statement.setDouble(25, companyProduct.getProdStructAmt1());
			statement.setDouble(26, companyProduct.getProdStructAmt2());
			statement.setString(27, companyProduct.getProdSmsMessage3());
			statement.setInt(28, companyProduct.getCompInternalId());
			statement.setInt(29, companyProduct.getCompId());
			statement.setLong(30, companyProduct.getProdId());
			if(statement.executeUpdate()<=0)
				companyProduct = new CompanyProduct();
		}catch (Exception e) {
			System.out.println("Error updating table COMPANYPRODUCTS: " + e.toString());
			companyProduct = new CompanyProduct();
		}
		return companyProduct;
	}
	
	public static CompanyProduct getCompanyProduct(int compId, int prodId, Connection connection){
		CompanyProduct companyProduct = new CompanyProduct();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYPRODUCTS WHERE compid = ? AND prodid = ?");
		){
			statement.setInt(1, compId);
			statement.setInt(2, prodId);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				companyProduct.setProdId(rs.getInt("prodid"));
				companyProduct.setCompId(rs.getInt("compid"));
				companyProduct.setProdCode(rs.getString("prodcode"));
				companyProduct.setProdName(rs.getString("prodname"));
				companyProduct.setProdShortName(rs.getString("prodshortname"));
				companyProduct.setProdType(rs.getInt("prodType"));
				companyProduct.setProdTypeAmt1(rs.getDouble("prodtypeamt1"));
				companyProduct.setProdTypeAmt2(rs.getDouble("prodtypeamt2"));
				companyProduct.setProdTypeRsp(rs.getDouble("prodtypersp"));
				companyProduct.setProdTypeCost(rs.getDouble("prodtypecost"));
				companyProduct.setProdJoinName(rs.getString("prodjoinname"));
				companyProduct.setProdProRata(rs.getString("prodprorata"));
				companyProduct.setProdTypePay(rs.getInt("prodtypepay"));
				companyProduct.setProdTypeRebill(rs.getInt("prodtyperebill"));
				companyProduct.setProdTypeCycle(rs.getInt("prodtypecycle"));
				companyProduct.setProdStartDate(rs.getString("prodstartdate"));
				companyProduct.setProdEndDate(rs.getString("prodenddate"));
				companyProduct.setProdActive(rs.getString("prodactive"));
				companyProduct.setProdSmsMessage1(rs.getString("prodsmsmessage1"));
				companyProduct.setProdSmsDays1(rs.getInt("prodsmsdays1"));
				companyProduct.setProdSmsDays2(rs.getInt("prodsmsdays2"));
				companyProduct.setProdExtRef1(rs.getString("prodextref1"));
				companyProduct.setProdExtRef2(rs.getString("prodextref2"));
				companyProduct.setProdExtAmt1(rs.getDouble("prodextamt1"));
				companyProduct.setProdSmsMessage2(rs.getString("prodsmsmessage2"));
				companyProduct.setProdStruct(rs.getInt("prodstruct"));
				companyProduct.setProdStructAmt1(rs.getDouble("prodstructamt1"));
				companyProduct.setProdStructAmt2(rs.getDouble("prodstructamt2"));
				companyProduct.setProdSmsMessage3(rs.getString("prodsmsmessage3"));
				companyProduct.setCompInternalId(rs.getInt("compinternalid"));

			}rs.close();
		} catch(Exception e) {
			System.out.println("Error querying table : " + e.toString());
		}
		
		return companyProduct;
	} 
	
	public static ArrayList<CompanyProduct> getCompanyProducts(int compId, Connection connection){
		ArrayList<CompanyProduct> companyProducts = new ArrayList<CompanyProduct>();

		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYPRODUCTS WHERE compid = ? ORDER BY prodcode");
		){
			statement.setInt(1, compId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyProduct companyProduct = new CompanyProduct();
				companyProduct.setProdId(rs.getInt("prodid"));
				companyProduct.setCompId(rs.getInt("compid"));
				companyProduct.setProdCode(rs.getString("prodcode"));
				companyProduct.setProdName(rs.getString("prodname"));
				companyProduct.setProdShortName(rs.getString("prodshortname"));
				companyProduct.setProdType(rs.getInt("prodType"));
				companyProduct.setProdTypeAmt1(rs.getDouble("prodtypeamt1"));
				companyProduct.setProdTypeAmt2(rs.getDouble("prodtypeamt2"));
				companyProduct.setProdTypeRsp(rs.getDouble("prodtypersp"));
				companyProduct.setProdTypeCost(rs.getDouble("prodtypecost"));
				companyProduct.setProdJoinName(rs.getString("prodjoinname"));
				companyProduct.setProdProRata(rs.getString("prodprorata"));
				companyProduct.setProdTypePay(rs.getInt("prodtypepay"));
				companyProduct.setProdTypeRebill(rs.getInt("prodtyperebill"));
				companyProduct.setProdTypeCycle(rs.getInt("prodtypecycle"));
				companyProduct.setProdStartDate(rs.getString("prodstartdate"));
				companyProduct.setProdEndDate(rs.getString("prodenddate"));
				companyProduct.setProdActive(rs.getString("prodactive"));
				companyProduct.setProdSmsMessage1(rs.getString("prodsmsmessage1"));
				companyProduct.setProdSmsDays1(rs.getInt("prodsmsdays1"));
				companyProduct.setProdSmsDays2(rs.getInt("prodsmsdays2"));
				companyProduct.setProdExtRef1(rs.getString("prodextref1"));
				companyProduct.setProdExtRef2(rs.getString("prodextref2"));
				companyProduct.setProdExtAmt1(rs.getDouble("prodextamt1"));
				companyProduct.setProdSmsMessage2(rs.getString("prodsmsmessage2"));
				companyProduct.setProdStruct(rs.getInt("prodstruct"));
				companyProduct.setProdStructAmt1(rs.getDouble("prodstructamt1"));
				companyProduct.setProdStructAmt2(rs.getDouble("prodstructamt2"));
				companyProduct.setProdSmsMessage3(rs.getString("prodsmsmessage3"));
				companyProduct.setCompInternalId(rs.getInt("compinternalid"));
				companyProducts.add(companyProduct);
			}rs.close();
		} catch(Exception e) {
			System.out.println("Error querying table : " + e.toString());
		}
		return companyProducts;
	} 
	
	public static ArrayList<CompanyProduct> getCompanyProductsLikeProdCode(int compId, String prodcode, Connection connection){
		ArrayList<CompanyProduct> companyProducts = new ArrayList<CompanyProduct>();
		if(prodcode==null)
			prodcode = "";
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYPRODUCTS WHERE compid = ? " + (prodcode.length()>0 ? " AND prodcode ILIKE  ?" : "") + " ORDER BY prodcode");
		){
			statement.setInt(1, compId);
			if(prodcode.length()>0)
				statement.setString(2, "%"+prodcode+"%");
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyProduct companyProduct = new CompanyProduct();
				companyProduct.setProdId(rs.getInt("prodid"));
				companyProduct.setCompId(rs.getInt("compid"));
				companyProduct.setProdCode(rs.getString("prodcode"));
				companyProduct.setProdName(rs.getString("prodname"));
				companyProduct.setProdShortName(rs.getString("prodshortname"));
				companyProduct.setProdType(rs.getInt("prodType"));
				companyProduct.setProdTypeAmt1(rs.getDouble("prodtypeamt1"));
				companyProduct.setProdTypeAmt2(rs.getDouble("prodtypeamt2"));
				companyProduct.setProdTypeRsp(rs.getDouble("prodtypersp"));
				companyProduct.setProdTypeCost(rs.getDouble("prodtypecost"));
				companyProduct.setProdJoinName(rs.getString("prodjoinname"));
				companyProduct.setProdProRata(rs.getString("prodprorata"));
				companyProduct.setProdTypePay(rs.getInt("prodtypepay"));
				companyProduct.setProdTypeRebill(rs.getInt("prodtyperebill"));
				companyProduct.setProdTypeCycle(rs.getInt("prodtypecycle"));
				companyProduct.setProdStartDate(rs.getString("prodstartdate"));
				companyProduct.setProdEndDate(rs.getString("prodenddate"));
				companyProduct.setProdActive(rs.getString("prodactive"));
				companyProduct.setProdSmsMessage1(rs.getString("prodsmsmessage1"));
				companyProduct.setProdSmsDays1(rs.getInt("prodsmsdays1"));
				companyProduct.setProdSmsDays2(rs.getInt("prodsmsdays2"));
				companyProduct.setProdExtRef1(rs.getString("prodextref1"));
				companyProduct.setProdExtRef2(rs.getString("prodextref2"));
				companyProduct.setProdExtAmt1(rs.getDouble("prodextamt1"));
				companyProduct.setProdSmsMessage2(rs.getString("prodsmsmessage2"));
				companyProduct.setProdStruct(rs.getInt("prodstruct"));
				companyProduct.setProdStructAmt1(rs.getDouble("prodstructamt1"));
				companyProduct.setProdStructAmt2(rs.getDouble("prodstructamt2"));
				companyProduct.setProdSmsMessage3(rs.getString("prodsmsmessage3"));
				companyProduct.setCompInternalId(rs.getInt("compinternalid"));
				companyProducts.add(companyProduct);
			}rs.close();
		} catch(Exception e) {
			System.out.println("Error querying table : " + e.toString());
		}
		
		return companyProducts;
	} 
	
	
	public static ArrayList<CompanyProduct> getCompanyInternalProducts(int compId, int compInternalId, Connection connection){
		ArrayList<CompanyProduct> companyProducts = new ArrayList<CompanyProduct>();
		try(
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM COMPANYPRODUCTS WHERE compid = ?" + (compInternalId==0 ? "" : " AND compinternalid = " + compInternalId + " ORDER BY prodcode"));
		){
			statement.setInt(1, compId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				CompanyProduct companyProduct = new CompanyProduct();
				companyProduct.setProdId(rs.getInt("prodid"));
				companyProduct.setCompId(rs.getInt("compid"));
				companyProduct.setProdCode(rs.getString("prodcode"));
				companyProduct.setProdName(rs.getString("prodname"));
				companyProduct.setProdShortName(rs.getString("prodshortname"));
				companyProduct.setProdType(rs.getInt("prodType"));
				companyProduct.setProdTypeAmt1(rs.getDouble("prodtypeamt1"));
				companyProduct.setProdTypeAmt2(rs.getDouble("prodtypeamt2"));
				companyProduct.setProdTypeRsp(rs.getDouble("prodtypersp"));
				companyProduct.setProdTypeCost(rs.getDouble("prodtypecost"));
				companyProduct.setProdJoinName(rs.getString("prodjoinname"));
				companyProduct.setProdProRata(rs.getString("prodprorata"));
				companyProduct.setProdTypePay(rs.getInt("prodtypepay"));
				companyProduct.setProdTypeRebill(rs.getInt("prodtyperebill"));
				companyProduct.setProdTypeCycle(rs.getInt("prodtypecycle"));
				companyProduct.setProdStartDate(rs.getString("prodstartdate"));
				companyProduct.setProdEndDate(rs.getString("prodenddate"));
				companyProduct.setProdActive(rs.getString("prodactive"));
				companyProduct.setProdSmsMessage1(rs.getString("prodsmsmessage1"));
				companyProduct.setProdSmsDays1(rs.getInt("prodsmsdays1"));
				companyProduct.setProdSmsDays2(rs.getInt("prodsmsdays2"));
				companyProduct.setProdExtRef1(rs.getString("prodextref1"));
				companyProduct.setProdExtRef2(rs.getString("prodextref2"));
				companyProduct.setProdExtAmt1(rs.getDouble("prodextamt1"));
				companyProduct.setProdSmsMessage2(rs.getString("prodsmsmessage2"));
				companyProduct.setProdStruct(rs.getInt("prodstruct"));
				companyProduct.setProdStructAmt1(rs.getDouble("prodstructamt1"));
				companyProduct.setProdStructAmt2(rs.getDouble("prodstructamt2"));
				companyProduct.setProdSmsMessage3(rs.getString("prodsmsmessage3"));
				companyProduct.setCompInternalId(rs.getInt("compinternalid"));
				companyProducts.add(companyProduct);
			}rs.close();
		} catch(Exception e) {
			System.out.println("Error querying table : " + e.toString());
		}
		
		return companyProducts;
	} 
	
	
	
	
	
	
	
	
	
	
	//JSON HELPER METHODS
	
	public static JSONArray toJSONArray(ArrayList<CompanyProduct> compProds) {
		JSONArray json = new JSONArray();
		try {
			for(int i=0;i<compProds.size();i++) {
				CompanyProduct compProd = compProds.get(i);
				json.put(compProd.toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString());
		}
		return json;
	}
	
	
	
	public static StringBuilder getCompanyProductsJSON(ArrayList<CompanyProduct> compProds) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for(int i=0;i<compProds.size();i++) {
			CompanyProduct compProd = compProds.get(i);
			sb.append("{\n");
			sb.append("\"compid\" : "+compProd.getCompId()+",\n");
			sb.append("\"prodid\" : "+compProd.getProdId()+",\n");
			sb.append("\"prodcode\" : \""+compProd.getProdCode()+"\",\n");
			sb.append("\"prodname\" : \""+compProd.getProdName()+"\",\n");
			sb.append("\"prodshortname\" : \""+compProd.getProdShortName()+"\",\n");
			sb.append("\"prodtype\" : "+compProd.getProdType()+",\n");
			sb.append("\"prodtypeamt1\" : "+compProd.getProdTypeAmt1()+",\n");
			sb.append("\"prodtypeamt2\" : "+compProd.getProdTypeAmt2()+",\n");
			sb.append("\"prodtypersp\" : "+compProd.getProdTypeRsp()+",\n");
			sb.append("\"prodtypecost\" : "+compProd.getProdTypeCost()+",\n");
			sb.append("\"prodjoinname\" : \""+compProd.getProdJoinName()+"\",\n");
			sb.append("\"prodprorata\" : \""+(compProd.getProdProRata().length()>0 ? compProd.getProdProRata() : "0")+"\",\n");
			sb.append("\"prodtypepay\" : "+compProd.getProdTypePay()+",\n");
			sb.append("\"prodtyperebill\" : "+compProd.getProdTypeRebill()+",\n");
			sb.append("\"prodtypecycle\" : "+compProd.getProdTypeCycle()+",\n");
			sb.append("\"prodstartdate\" : \""+compProd.getProdStartDate()+"\",\n");
			sb.append("\"prodenddate\" : \""+compProd.getProdEndDate()+"\",\n");
			sb.append("\"prodactive\" : \""+compProd.getProdActive()+"\",\n");
			sb.append("\"prodsmsmessage1\" : \""+compProd.getProdSmsMessage1().replace("\"", "'")+"\",\n");
			sb.append("\"prodsmsdays1\" : "+compProd.getProdSmsDays1()+",\n");
			sb.append("\"prodsmsdays2\" : "+compProd.getProdSmsDays2()+",\n");
			sb.append("\"prodextref1\" : \""+compProd.getProdExtRef1()+"\",\n");
			sb.append("\"prodextref2\" : \""+compProd.getProdExtRef2()+"\",\n");
			sb.append("\"prodextamt1\" : "+compProd.getProdExtAmt1()+",\n");
			sb.append("\"prodsmsmessage2\" : \""+compProd.getProdSmsMessage2()+"\",\n");
			sb.append("\"prodstruct\" : "+compProd.getProdStruct()+",\n");
			sb.append("\"prodstructamt1\" : "+compProd.getProdStructAmt1()+",\n");
			sb.append("\"prodstructamt2\" : "+compProd.getProdStructAmt2()+",\n");
			sb.append("\"prodsmsmessage3\" : \""+compProd.getProdSmsMessage3()+"\",\n");
			sb.append("\"compinternalid\" : "+compProd.getCompInternalId()+"\n");
			sb.append("}"+(i==compProds.size()-1 ? "" : ",")+"\n");
		}
		sb.append("]");
		return sb;
	}
	public static StringBuilder getCompanyProductJSON(CompanyProduct compProd) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\"compid\" : "+compProd.getCompId()+",\n");
		sb.append("\"prodid\" : "+compProd.getProdId()+",\n");
		sb.append("\"prodcode\" : \""+compProd.getProdCode()+"\",\n");
		sb.append("\"prodname\" : \""+compProd.getProdName()+"\",\n");
		sb.append("\"prodshortname\" : \""+compProd.getProdShortName()+"\",\n");
		sb.append("\"prodtype\" : "+compProd.getProdType()+",\n");
		sb.append("\"prodtypeamt1\" : "+compProd.getProdTypeAmt1()+",\n");
		sb.append("\"prodtypeamt2\" : "+compProd.getProdTypeAmt2()+",\n");
		sb.append("\"prodtypersp\" : "+compProd.getProdTypeRsp()+",\n");
		sb.append("\"prodtypecost\" : "+compProd.getProdTypeCost()+",\n");
		sb.append("\"prodjoinname\" : \""+compProd.getProdJoinName()+"\",\n");
		sb.append("\"prodprorata\" : \""+(compProd.getProdProRata().length()>0 ? compProd.getProdProRata() : "0")+"\",\n");
		sb.append("\"prodtypepay\" : "+compProd.getProdTypePay()+",\n");
		sb.append("\"prodtyperebill\" : "+compProd.getProdTypeRebill()+",\n");
		sb.append("\"prodtypecycle\" : "+compProd.getProdTypeCycle()+",\n");
		sb.append("\"prodstartdate\" : \""+compProd.getProdStartDate()+"\",\n");
		sb.append("\"prodenddate\" : \""+compProd.getProdEndDate()+"\",\n");
		sb.append("\"prodactive\" : \""+compProd.getProdActive()+"\",\n");
		sb.append("\"prodsmsmessage1\" : \""+compProd.getProdSmsMessage1().replace("\"", "'")+"\",\n");
		sb.append("\"prodsmsdays1\" : "+compProd.getProdSmsDays1()+",\n");
		sb.append("\"prodsmsdays2\" : "+compProd.getProdSmsDays2()+",\n");
		sb.append("\"prodextref1\" : \""+compProd.getProdExtRef1()+"\",\n");
		sb.append("\"prodextref2\" : \""+compProd.getProdExtRef2()+"\",\n");
		sb.append("\"prodextamt1\" : "+compProd.getProdExtAmt1()+",\n");
		sb.append("\"prodsmsmessage2\" : \""+compProd.getProdSmsMessage2()+"\",\n");
		sb.append("\"prodstruct\" : "+compProd.getProdStruct()+",\n");
		sb.append("\"prodstructamt1\" : "+compProd.getProdStructAmt1()+",\n");
		sb.append("\"prodstructamt2\" : "+compProd.getProdStructAmt2()+",\n");
		sb.append("\"prodsmsmessage3\" : \""+compProd.getProdSmsMessage3()+"\",\n");
		sb.append("\"compinternalid\" : "+compProd.getCompInternalId()+"\n");
		sb.append("}");
		return sb;
	}
}
