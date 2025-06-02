package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import za.co.payguru.model.SmsBulkSummary;

public class SmsBulkSummaryDao {

  
	public static int getNextID(Connection connection) {
		int id = 0;
		try(
			PreparedStatement statement  = connection.prepareStatement("SELECT NEXTVAL('smsbulksummary_summaryid_pk') AS id");
		){
			ResultSet rs = statement.executeQuery();
			if(rs.next())
				id = rs.getInt("id");
			rs.close();
		}catch (Exception e) {
			System.out.println("Error querying sequence: " + e.toString());
		}
		return id;
	}
	
    public static boolean insert(Connection connection, SmsBulkSummary summary) {
    	boolean inserted = false;
        String sql = "INSERT INTO public.smsbulksummary (summaryid, summarycompid, summarydesc, summarytype, summarytyperef1, " +
                "summarycreateddate, summarycreatedtime, summarycreateduserid, summarysmsmessage, summarysmssenddate, " +
                "summarysmssendtime, summarystatus, summarystatusdate, summarystatustime) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        summary.setSummaryID(getNextID(connection));
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, summary.getSummaryID());
            statement.setInt(2, summary.getSummaryCompId());
            statement.setString(3, summary.getSummaryDesc());
            statement.setString(4, summary.getSummaryType());
            statement.setString(5, summary.getSummaryTypeRef1());
            statement.setString(6, summary.getSummaryCreatedDate());
            statement.setString(7, summary.getSummaryCreatedTime());
            statement.setInt(8, summary.getSummaryCreatedUserID());
            statement.setString(9, summary.getSummarySmsMessage());
            statement.setString(10, summary.getSummarySmsSendDate());
            statement.setString(11, summary.getSummarySmsSendTime());
            statement.setString(12, summary.getSummaryStatus());
            statement.setString(13, summary.getSummaryStatusDate());
            statement.setString(14, summary.getSummaryStatusTime());

            if(statement.executeUpdate()>0)
            	inserted = true;
        }catch (Exception e) {
			System.out.println("Error updating table SMSBULKSUMMARY: " + e.toString());
		}
        
        return inserted;
    }

    // Retrieve a record by summaryid
    public SmsBulkSummary getById(Connection connection, int summaryId) {
        String sql = "SELECT * FROM public.smsbulksummary WHERE summaryid = ?";
        SmsBulkSummary summary = new SmsBulkSummary();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, summaryId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                summary = new SmsBulkSummary();
                summary.setSummaryID(rs.getInt("summaryid"));
                summary.setSummaryCompId(rs.getInt("summarycompid"));
                summary.setSummaryDesc(rs.getString("summarydesc"));
                summary.setSummaryType(rs.getString("summarytype"));
                summary.setSummaryTypeRef1(rs.getString("summarytyperef1"));
                summary.setSummaryCreatedDate(rs.getString("summarycreateddate"));
                summary.setSummaryCreatedTime(rs.getString("summarycreatedtime"));
                summary.setSummaryCreatedUserID(rs.getInt("summarycreateduserid"));
                summary.setSummarySmsMessage(rs.getString("summarysmsmessage"));
                summary.setSummarySmsSendDate(rs.getString("summarysmssenddate"));
                summary.setSummarySmsSendTime(rs.getString("summarysmssendtime"));
                summary.setSummaryStatus(rs.getString("summarystatus"));
                summary.setSummaryStatusDate(rs.getString("summarystatusdate"));
                summary.setSummaryStatusTime(rs.getString("summarystatustime"));
            }rs.close();
        }catch (Exception e) {
        	System.out.println("Error querying table SMSBULKSUMMARY: " + e.toString());
		}

        return summary;
    }

  
	
}
