package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;

import za.co.payguru.model.AgentCompanyProductPromo;
import za.co.payguru.util.DateUtil;

public class AgentCompanyProductPromoDao {

    public static AgentCompanyProductPromo loadPromo(Connection connection, int compid, int prodid, String agent, String subagent) {
        AgentCompanyProductPromo promo = new AgentCompanyProductPromo();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM agentcompanyproductpromos WHERE compid = ? AND prodid = ? AND agent = ? AND subagent = ? AND promoactive = ?")) {
            statement.setInt(1, compid);
            statement.setInt(2, prodid);
            statement.setString(3, agent);
            statement.setString(4, subagent);
            statement.setString(5, AgentCompanyProductPromo.ACTIVE);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                promo.setCompid(rs.getInt("compid"));
                promo.setProdid(rs.getInt("prodid"));
                promo.setAgent(rs.getString("agent"));
                promo.setSubagent(rs.getString("subagent"));
                promo.setPromodiscamt(rs.getInt("promodiscamt"));
                promo.setPromoref1(rs.getString("promoref1"));
                promo.setPromoref2(rs.getString("promoref2"));
                promo.setPromodatefrom(rs.getDate("promodatefrom"));
                promo.setPromodateto(rs.getDate("promodateto"));
                promo.setPromoactive(rs.getString("promoactive"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error loading AgentCompanyProductPromo: " + e.toString());
        }
        return promo;
    }

    public static ArrayList<AgentCompanyProductPromo> getPromos(Connection connection, int compid, String agent, String subagent) {
        ArrayList<AgentCompanyProductPromo> promos = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM agentcompanyproductpromos WHERE compid = ? AND agent = ? AND subagent = ? AND promoactive = ?  AND ? >= promodatefrom AND ? <= promodateto ORDER BY prodid")) {
        	statement.setInt(1, compid);
        	statement.setString(2, agent);
        	statement.setString(3, subagent);
            statement.setString(4, AgentCompanyProductPromo.ACTIVE);
            statement.setDate(5, DateUtil.getCurrentCCYYMMDDDate());
            statement.setDate(6, DateUtil.getCurrentCCYYMMDDDate());

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                AgentCompanyProductPromo promo = new AgentCompanyProductPromo();
                promo.setCompid(rs.getInt("compid"));
                promo.setProdid(rs.getInt("prodid"));
                promo.setAgent(rs.getString("agent"));
                promo.setSubagent(rs.getString("subagent"));
                promo.setPromodiscamt(rs.getInt("promodiscamt"));
                promo.setPromoref1(rs.getString("promoref1"));
                promo.setPromoref2(rs.getString("promoref2"));
                promo.setPromodatefrom(rs.getDate("promodatefrom"));
                promo.setPromodateto(rs.getDate("promodateto"));
                promo.setPromoactive(rs.getString("promoactive"));
                promos.add(promo);
            }
            rs.close();
        } catch (Exception e) {
            System.out.println("Error querying AgentCompanyProductPromo: " + e.toString());
        }
        return promos;
    }

    public static boolean addPromo(Connection connection, AgentCompanyProductPromo promo) {
        boolean success = false;
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO agentcompanyproductpromos (compid, prodid, agent, subagent, promodiscamt, promoref1, promoref2, promodatefrom, promodateto, promoactive) VALUES (?,?,?,?,?,?,?,?,?,?)")) {
            statement.setInt(1, promo.getCompid());
            statement.setInt(2, promo.getProdid());
            statement.setString(3, promo.getAgent());
            statement.setString(4, promo.getSubagent());
            statement.setInt(5, promo.getPromodiscamt());
            statement.setString(6, promo.getPromoref1());
            statement.setString(7, promo.getPromoref2());
            statement.setDate(8, promo.getPromodatefrom());
            statement.setDate(9, promo.getPromodateto());
            statement.setString(10, promo.getPromoactive());
            success = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error adding AgentCompanyProductPromo: " + e.toString());
        }
        return success;
    }

    public static boolean updatePromo(Connection connection, AgentCompanyProductPromo promo) {
        boolean success = false;
        try (PreparedStatement statement = connection.prepareStatement("UPDATE agentcompanyproductpromos SET promodiscamt = ?, promoref1 = ?, promoref2 = ?, promodatefrom = ?, promodateto = ?, promoactive = ? WHERE compid = ? AND prodid = ? AND agent = ? AND subagent = ?")) {
            statement.setInt(1, promo.getPromodiscamt());
            statement.setString(2, promo.getPromoref1());
            statement.setString(3, promo.getPromoref2());
            statement.setDate(4, promo.getPromodatefrom());
            statement.setDate(5, promo.getPromodateto());
            statement.setString(6, promo.getPromoactive());
            statement.setInt(7, promo.getCompid());
            statement.setInt(8, promo.getProdid());
            statement.setString(9, promo.getAgent());
            statement.setString(10, promo.getSubagent());
            success = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error updating promo: " + e.toString());
        }
        return success;
    }

    public static boolean deletePromo(Connection connection, int compid, int prodid, String agent, String subagent) {
        boolean success = false;
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM agentcompanyproductpromos WHERE compid = ? AND prodid = ? AND agent = ? AND subagent = ?")) {
            statement.setInt(1, compid);
            statement.setInt(2, prodid);
            statement.setString(3, agent);
            statement.setString(4, subagent);
            success = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error deleting promo: " + e.toString());
        }
        return success;
    }

    public static JSONArray getPromosJSON(ArrayList<AgentCompanyProductPromo> promos) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (AgentCompanyProductPromo promo : promos) {
                jsonArray.put(promo.toJSON());
            }
        } catch (Exception e) {
            System.out.println("Error creating JSON: " + e.toString());
        }
        return jsonArray;
    }
}
