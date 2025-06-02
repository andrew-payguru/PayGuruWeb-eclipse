package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import za.co.payguru.model.DonorCompanyProduct;

public class DonorCompanyProductDao {

    public static DonorCompanyProduct loadDonorCompanyProduct(Connection connection, int donorid, int compid, int prodid) {
    	DonorCompanyProduct donorCompanyProducts = new DonorCompanyProduct();
        try (
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM donorcompanyproducts WHERE donorid = ? AND compid = ? AND prodid = ? AND prodactive = ?");
        ) {
            statement.setInt(1, donorid);
            statement.setInt(2, compid);
            statement.setInt(3, prodid);
            statement.setString(4, "1");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                donorCompanyProducts.setDonorid(rs.getInt("donorid"));
                donorCompanyProducts.setCompid(rs.getInt("compid"));
                donorCompanyProducts.setProdid(rs.getInt("prodid"));
                donorCompanyProducts.setDonorprodref1(rs.getString("donorprodref1"));
                donorCompanyProducts.setDonorprodref2(rs.getString("donorprodref2"));
                donorCompanyProducts.setProdactive(rs.getString("prodactive"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error loading DonorCompanyProducts: " + e.toString());
        }
        return donorCompanyProducts;
    }
    
    public static ArrayList<DonorCompanyProduct> getDonorCompanyProducts(Connection connection, int donorid) {
    	ArrayList<DonorCompanyProduct> donorCompProducts = new ArrayList<DonorCompanyProduct>();
        try (
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM donorcompanyproducts WHERE donorid = ? AND prodactive = ?");
        ) {
            statement.setInt(1, donorid);
            statement.setString(2, DonorCompanyProduct.ACTIVE);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            	DonorCompanyProduct donorCompanyProduct = new DonorCompanyProduct();
            	donorCompanyProduct.setDonorid(rs.getInt("donorid"));
            	donorCompanyProduct.setCompid(rs.getInt("compid"));
                donorCompanyProduct.setProdid(rs.getInt("prodid"));
                donorCompanyProduct.setDonorprodref1(rs.getString("donorprodref1"));
                donorCompanyProduct.setDonorprodref2(rs.getString("donorprodref2"));
                donorCompanyProduct.setProdactive(rs.getString("prodactive"));
                donorCompProducts.add(donorCompanyProduct);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error loading DonorCompanyProducts: " + e.toString());
        }
        return donorCompProducts;
    }

    public static DonorCompanyProduct addDonorCompanyProducts(Connection connection, DonorCompanyProduct donorCompanyProducts) {
        try (
                PreparedStatement statement = connection.prepareStatement("INSERT INTO donorcompanyproducts (donorid, compid, prodid, donorprodref1, donorprodref2, prodactive) VALUES (?,?,?,?,?,?)");
        ) {
            statement.setInt(1, donorCompanyProducts.getDonorid());
            statement.setInt(2, donorCompanyProducts.getCompid());
            statement.setInt(3, donorCompanyProducts.getProdid());
            statement.setString(4, donorCompanyProducts.getDonorprodref1());
            statement.setString(5, donorCompanyProducts.getDonorprodref2());
            statement.setString(6, donorCompanyProducts.getProdactive());
            if (statement.executeUpdate() <= 0) {
                donorCompanyProducts = new DonorCompanyProduct();
            }
        } catch (Exception e) {
            System.out.println("Error adding DonorCompanyProducts: " + e.toString());
        }
        return donorCompanyProducts;
    }

    public static DonorCompanyProduct updateDonorCompanyProducts(Connection connection, DonorCompanyProduct donorCompanyProducts) {
        try (
                PreparedStatement statement = connection.prepareStatement("UPDATE donorcompanyproducts SET donorprodref1 = ?, donorprodref2 = ?, prodactive = ? WHERE donorid = ? AND compid = ? AND prodid = ?");
        ) {
            statement.setString(1, donorCompanyProducts.getDonorprodref1());
            statement.setString(2, donorCompanyProducts.getDonorprodref2());
            statement.setString(3, donorCompanyProducts.getProdactive());
            statement.setInt(4, donorCompanyProducts.getDonorid());
            statement.setInt(5, donorCompanyProducts.getCompid());
            statement.setInt(6, donorCompanyProducts.getProdid());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating DonorCompanyProducts: " + e.toString());
        }
        return donorCompanyProducts;
    }
}
