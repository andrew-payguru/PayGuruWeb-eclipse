package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import za.co.payguru.model.Donor;

public class DonorDao {
    public static long getNextDonorId(Connection connection) {
        long donorid = 0;
        try (
            PreparedStatement statement = connection.prepareStatement("SELECT NEXTVAL('donors_donorid_seq') AS donorid");
        ) {
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                donorid = rs.getLong("donorid");
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error getting next donorid: " + e.toString());
        }
        return donorid;
    }

    public static Donor loadDonorById(Connection connection, int donorid) {
        Donor donor = new Donor();
        try (
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM donors WHERE donorid = ?");
        ) {
            statement.setInt(1, donorid);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                donor.setDonorid(rs.getInt("donorid"));
                donor.setDonorname(rs.getString("donorname"));
                donor.setDonoremail(rs.getString("donoremail"));
                donor.setDonorcell(rs.getString("donorcell"));
                donor.setDonorvatno(rs.getString("donorvatno"));
                donor.setDonorregno(rs.getString("donorregno"));
                donor.setDonortype(rs.getString("donortype"));
                donor.setDonorref1(rs.getString("donorref1"));
                donor.setDonorref2(rs.getString("donorref2"));
                donor.setDonorref(rs.getString("donorref"));
                donor.setDonoractive(rs.getString("donoractive"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error loading donor: " + e.toString());
        }
        return donor;
    }

    public static Donor addDonor(Connection connection, Donor donor) {
        donor.setDonorid((int) getNextDonorId(connection));
        try (
            PreparedStatement statement = connection.prepareStatement("INSERT INTO donors (donorid, donorname, donoremail, donorcell, donorvatno, donorregno, donortype, donorref1, donorref2, donorref, donoractive) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        ) {
            statement.setInt(1, donor.getDonorid());
            statement.setString(2, donor.getDonorname());
            statement.setString(3, donor.getDonoremail());
            statement.setString(4, donor.getDonorcell());
            statement.setString(5, donor.getDonorvatno());
            statement.setString(6, donor.getDonorregno());
            statement.setString(7, donor.getDonortype());
            statement.setString(8, donor.getDonorref1());
            statement.setString(9, donor.getDonorref2());
            statement.setString(10, donor.getDonorref());
            statement.setString(11, donor.getDonoractive());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error adding donor: " + e.toString());
        }
        return donor;
    }
    
    public static Donor updateDonor(Connection connection, Donor donor) {
        try (
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE donors SET donorname = ?, donoremail = ?, donorcell = ?, donorvatno = ?, donorregno = ?, donortype = ?, donorref1 = ?, donorref2 = ?, donorref = ?, donoractive = ? WHERE donorid = ?"
            );
        ) {
            statement.setString(1, donor.getDonorname());
            statement.setString(2, donor.getDonoremail());
            statement.setString(3, donor.getDonorcell());
            statement.setString(4, donor.getDonorvatno());
            statement.setString(5, donor.getDonorregno());
            statement.setString(6, donor.getDonortype());
            statement.setString(7, donor.getDonorref1());
            statement.setString(8, donor.getDonorref2());
            statement.setString(9, donor.getDonorref());
            statement.setString(10, donor.getDonoractive());
            statement.setInt(11, donor.getDonorid());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error updating donor: " + e.toString());
        }
        return donor;
    }

    public static boolean deleteDonor(Connection connection, int donorid) {
        boolean deleted = false;
        try (
            PreparedStatement statement = connection.prepareStatement("DELETE FROM donors WHERE donorid = ?");
        ) {
            statement.setInt(1, donorid);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                deleted = true;
            }
        } catch (Exception e) {
            System.out.println("Error deleting donor: " + e.toString());
        }
        return deleted;
    }
    
}
