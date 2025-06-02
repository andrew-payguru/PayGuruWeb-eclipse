package za.co.payguru.dao;

import java.sql.Connection;
import java.util.ArrayList;

public class UtilDao {

	// {
  //   label: 'Payments',
  //   data: [42, 13, 75,12,27,32],
  //   // you can set individual colors for each bar
  //   backgroundColor: [
  //     'rgba(0, 255, 0, 0.6)',
  //   ],
  //   borderWidth: 5,
  //   borderColor: 'rgba(0, 255, 0, 0.6)'
  // }
	public static StringBuffer buildClientInvoicesGraph(int compId, int prevDays, Connection connection) {
		StringBuffer sb = new StringBuffer();
		
		ArrayList<String> data = ClientInvoiceDao.getCompClientInvoicesGraph(compId, prevDays, connection);
		sb.append("[\n");
		for(int i=0;i<data.size();i++) {
			sb.append("{\n");
			
			sb.append("}"+(i==data.size()-1 ? "" : ",")+"\n");
		}
		sb.append("]");
		
		return sb;
	}
	
}
