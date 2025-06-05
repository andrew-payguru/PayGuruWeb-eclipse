package za.co.payguru.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.model.ClientInvoice;
import za.co.payguru.model.ClientInvoiceData;
import za.co.payguru.model.ClientPaymentRef;
import za.co.payguru.model.ClientProductRef;
import za.co.payguru.model.CompanyProduct;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.Util;

public class ClientInvoiceDao {

	public static ClientInvoice loadClientInvoice(Connection connection, int compid, String invno) {
		ClientInvoice clientInvoice = new ClientInvoice();
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM CLIENTINVOICES WHERE compid = ? AND invno = ? AND active = ?");) {
			statement.setInt(1, compid);
			statement.setString(2, invno);
			statement.setString(3, ClientInvoice.INVOICE_ACTIVE);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICES: " + e.toString());
		}
		return clientInvoice;
	}

	public static ArrayList<ClientInvoice> loadClientsInvoices(Connection connection, int clientid) {
		ArrayList<ClientInvoice> clientInvoices = new ArrayList<ClientInvoice>();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM CLIENTINVOICES WHERE clientid = ? AND active = ?  AND status = ? ORDER BY paydate DESC, invno DESC");) {
			statement.setInt(1, clientid);
			statement.setString(2, ClientInvoice.INVOICE_ACTIVE);
			statement.setString(3, ClientInvoice.INVOICE_PAID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientInvoice clientInvoice = new ClientInvoice();
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
				clientInvoices.add(clientInvoice);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICES: " + e.toString());
		}
		return clientInvoices;
	}

	public static ArrayList<ClientInvoice> loadWalletTransactionsByRefNo(Connection connection, String walletRefNo) {
		ArrayList<ClientInvoice> clientInvoices = new ArrayList<ClientInvoice>();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM CLIENTINVOICES WHERE payref = ? AND active = ?  AND ( status = ? OR status = ? ) ORDER BY paydate DESC, invno DESC");) {
			statement.setString(1, walletRefNo);
			statement.setString(2, ClientInvoice.INVOICE_ACTIVE);
			statement.setString(3, ClientInvoice.INVOICE_PAID);
			statement.setString(4, ClientInvoice.INVOICE_REVERSED);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientInvoice clientInvoice = new ClientInvoice();
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
				clientInvoices.add(clientInvoice);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICES: " + e.toString());
		}
		return clientInvoices;
	}

	public static ArrayList<ClientInvoice> loadClientClientInvoices(Connection connection, int compid, int clientid,
			boolean period, String from, String to, boolean ignoreStatus) {
		ArrayList<ClientInvoice> clientInvoices = new ArrayList<ClientInvoice>();
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM CLIENTINVOICES WHERE compid = ? AND clientid = ? AND active = ?"
						+ (ignoreStatus ? "" : " AND status = '" + ClientInvoice.INVOICE_PAID + "' ")
						+ (period
								? " AND paydate >= '" + from.replace('-', '/') + "' AND paydate <= '"
										+ to.replace('-', '/') + "' "
								: " ")
						+ "ORDER BY paydate DESC, invno DESC");) {
			statement.setInt(1, compid);
			statement.setInt(2, clientid);
			statement.setString(3, ClientInvoice.INVOICE_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientInvoice clientInvoice = new ClientInvoice();
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
				clientInvoices.add(clientInvoice);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICES: " + e.toString());
		}
		return clientInvoices;
	}

	public static ArrayList<ClientInvoice> loadClientClientInvoices(Connection connection, int compid, int clientid) {
		return loadClientClientInvoices(connection, compid, clientid, false, DateUtil.getCurrentCCYYMMDDStr(),
				DateUtil.getCurrentCCYYMMDDStr(), false);
	}

	public static int getInvoiceDayCount(Connection connection, int compid, String date) {
		int count = 0;
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT count(*) AS tot FROM CLIENTINVOICES WHERE compid = ? AND paydate = ? AND status = ? AND canceldate = ''");) {
			statement.setInt(1, compid);
			statement.setString(2, date);
			statement.setString(3, ClientInvoice.INVOICE_PAID);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				count = rs.getInt("tot");
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICES: " + e.toString());
		}
		return count;
	}

	public static ArrayList<ClientInvoice> getInactiveSmsInvoices(Connection connection, int compid, int prodid,
			Date fromdate, Date todate) {
		return getInactiveSmsInvoices(connection, compid, prodid, fromdate, todate, null, null);
	}

	public static ArrayList<ClientInvoice> getInactiveSmsInvoices(Connection connection, int compid, int prodid,
			Date fromdate, Date todate, String invNo, String regNo) {
		String smsChannelStr = CompanyParamDao.getCompParamValue(connection, compid, "manualactivatesmschannels");
		if (smsChannelStr == null || smsChannelStr.isEmpty())
			smsChannelStr = "SMS";
		StringBuilder channelSql = new StringBuilder();
		String[] channels = smsChannelStr.split(",");
		for (int i = 0; i < channels.length; i++) {
			channelSql.append((i == 0 ? "" : " OR ") + ("salechannel = '" + channels[i] + "'"));
		}
		ArrayList<ClientInvoice> clientInvoices = new ArrayList<ClientInvoice>();
		boolean invNoFilter = (invNo != null && invNo.length() > 0);
		boolean regNoFilter = (regNo != null && regNo.length() > 0);
		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM CLIENTINVOICES WHERE compid = ?"
				+ (invNoFilter ? " AND invno LIKE '%" + invNo + "%' " : " ") + "AND paydate >= ? AND paydate <= ?"
				+ (regNoFilter ? " AND payref ILIKE '%" + regNo + "%' " : " ")
				+ "AND prodid IN (SELECT prodid FROM COMPANYPRODUCTS WHERE compid = ?"
				+ (prodid > 0 ? " AND prodid = " + prodid : "") + ") AND status = ? AND (" + channelSql
				+ ") AND invno in (SELECT invno FROM CLIENTPAYMENTREFS WHERE compid = ? AND createdate >= ? AND createdate <= ? AND status = ?) ORDER BY paydate, invno");) {
			statement.setInt(1, compid);
			statement.setString(2, String.valueOf(fromdate).replace('-', '/'));
			statement.setString(3, String.valueOf(todate).replace('-', '/'));
			statement.setInt(4, compid);
			statement.setString(5, ClientInvoice.INVOICE_INACTIVE);
			statement.setInt(6, compid);
			statement.setString(7, String.valueOf(fromdate).replace('-', '/'));
			statement.setString(8, String.valueOf(todate).replace('-', '/'));
			statement.setInt(9, Util.parseInt(ClientPaymentRef.ACTIVE, 1));

			System.out.println(statement.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientInvoice clientInvoice = new ClientInvoice();
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
				clientInvoices.add(clientInvoice);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println();
		}
		return clientInvoices;
	}

	// paydate not startdate
	public static ArrayList<ClientInvoice> getChannelClientInvoiceSales(Connection connection, int compId,
			int compinternalid, int prodid, String channel, String startDate, String endDate) {
		ArrayList<ClientInvoice> clientInvoices = new ArrayList<ClientInvoice>();

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM CLIENTINVOICES WHERE compid = ? AND paydate >= ? AND paydate <= ? "
						+ (channel.equalsIgnoreCase("ALL") ? "" : "AND salechannel = '" + channel.toUpperCase() + "'")
						+ " AND (status = ? OR status = ?) AND prodid IN (SELECT prodid FROM COMPANYPRODUCTS WHERE compid = ?"
						+ (prodid > 0 ? " AND prodid = " + prodid : "")
						+ (compinternalid > 0 ? (" AND compinternalid = " + compinternalid) : "")
						+ ") ORDER BY paydate, invno");) {
			statement.setInt(1, compId);
			statement.setString(2, startDate.replace('-', '/'));
			statement.setString(3, endDate.replace('-', '/'));
			statement.setString(4, ClientInvoice.INVOICE_PAID);
			statement.setString(5, ClientInvoice.INVOICE_ACTIVE);
			statement.setInt(6, compId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientInvoice clientInvoice = new ClientInvoice();
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
				clientInvoices.add(clientInvoice);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println();
		}
		return clientInvoices;
	}

	public static ClientInvoice searchLatestClientInvoiceByProdRef(Connection connection, int compid, String prodref) {
		ClientInvoice clientInvoice = new ClientInvoice();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM CLIENTINVOICES WHERE compid = ? AND payref ilike ? ORDER BY paydate DESC LIMIT 1");) {
			statement.setInt(1, compid);
			statement.setString(2, "%" + prodref + "%");
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICES: " + e.toString());
		}
		return clientInvoice;
	}

	public static ArrayList<ClientInvoice> getChannelClientInvoices(Connection connection, int compId,
			int compinternalid, int prodid, String channel, String startDate, String endDate) {
		ArrayList<ClientInvoice> clientInvoices = new ArrayList<ClientInvoice>();

		try (PreparedStatement statement = connection
				.prepareStatement("SELECT * FROM CLIENTINVOICES WHERE compid = ? AND paydate >= ? AND paydate <= ? "
						+ (channel.equalsIgnoreCase("ALL") ? "" : "AND salechannel = '" + channel.toUpperCase() + "'")
						+ " AND (status = ? OR status = ?) AND invno IN (SELECT invno FROM CLIENTINVOICEDATA WHERE compid = ? AND invdataref8 >= ? AND invdataref8 <= ?)  AND prodid IN (SELECT prodid FROM COMPANYPRODUCTS WHERE compid = ?"
						+ (prodid > 0 ? " AND prodid = " + prodid : "")
						+ (compinternalid > 0 ? (" AND compinternalid = " + compinternalid) : "")
						+ ") ORDER BY paydate, invno");) {
			statement.setInt(1, compId);
			statement.setString(2,
					String.valueOf(DateUtil.getPrevDate(DateUtil.getDateValue(startDate))).replace('-', '/'));
			statement.setString(3, endDate.replace('-', '/'));
			statement.setString(4, ClientInvoice.INVOICE_PAID);
			statement.setString(5, ClientInvoice.INVOICE_ACTIVE);
			statement.setInt(6, compId);
			statement.setString(7, startDate.replace('-', '/'));
			statement.setString(8, endDate.replace('-', '/'));
			statement.setInt(9, compId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientInvoice clientInvoice = new ClientInvoice();
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
				clientInvoices.add(clientInvoice);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println();
		}
		return clientInvoices;
	}

	public static ArrayList<String> getCompClientInvoicesGraph(int compId, int prevDays, Connection connection) {
		ArrayList<String> data = new ArrayList<String>();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT prodid,paydate,count(*) FROM CLIENTINVOICES WHERE compid = ? AND paydate >= ? AND paydate <= ? GROUP BY prodid,paydate ORDER BY prodid,paydate");) {
			Date today = DateUtil.getCurrentCCYYMMDDDate();
			statement.setInt(1, compId);
			statement.setString(2, String.valueOf(DateUtil.getPrevDate(today, prevDays, true)).replace("-", "/"));
			statement.setString(3, String.valueOf(today).replace("-", "/"));
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String paydate = rs.getString("paydate");
				int prodid = rs.getInt("prodid");
				CompanyProduct prod = CompanyProductDao.getCompanyProduct(compId, prodid, connection);
				if (prod.getProdId() <= 0)
					continue;
				String prodname = prod.getProdName();
				int count = rs.getInt("count");
				data.add(paydate + "|" + prodid + "|" + prodname + "|" + count);
			}
		} catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICES: " + e.toString());
		}
		return data;
	}

	public static ArrayList<ClientInvoice> getInvoicesByProduct(int compId, int prodId, String dateFrom, String dateTo,
			Connection connection) {
		ArrayList<ClientInvoice> clientInvoices = new ArrayList<ClientInvoice>();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM CLIENTINVOICES WHERE compid = ? AND prodid = ? AND paydate >= ? AND paydate <= ?  AND status != ? and active = ? ORDER BY paydate DESC, invno DESC");) {
			dateFrom = dateFrom.replace("-", "/");
			dateTo = dateTo.replace("-", "/");
			statement.setInt(1, compId);
			statement.setInt(2, prodId);
			statement.setString(3, dateFrom);
			statement.setString(4, dateTo);
			statement.setString(5, ClientInvoice.INVOICE_SAVINGS_BALANCE);
			statement.setString(6, ClientInvoice.INVOICE_ACTIVE);
			System.out.println(statement.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientInvoice clientInvoice = new ClientInvoice();
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
				clientInvoices.add(clientInvoice);
			}
			rs.close();
			return clientInvoices;

		} catch (Exception e) {
			System.out.println("Error querying table : " + e.toString());
		}
		return null;
	}

	public static ArrayList<ClientInvoice> searchUserProdInvoicesByCell(int compId, int userId, String cellNo,
			String dateFrom, String dateTo, Connection connection) {
		ArrayList<ClientInvoice> clientInvoices = new ArrayList<ClientInvoice>();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM CLIENTINVOICES WHERE compid = ? AND clientid IN (SELECT clientid FROM CLIENTS WHERE clientcellno LIKE ?) AND (prodid IN (SELECT prodid FROM COMPANYUSERPRODUCTS WHERE userid = ? AND compid = ?) OR -1 IN (SELECT prodid FROM COMPANYUSERPRODUCTS WHERE userid = ? AND compid = ?)) AND paydate >= ? AND paydate <= ? AND status != ? AND active = ?");) {
			dateFrom = dateFrom.replace("-", "/");
			dateTo = dateTo.replace("-", "/");
			statement.setInt(1, compId);
			statement.setString(2, "%" + cellNo + "%");
			statement.setInt(3, userId);
			statement.setInt(4, compId);
			statement.setInt(5, userId);
			statement.setInt(6, compId);
			statement.setString(7, dateFrom);
			statement.setString(8, dateTo);
			statement.setString(9, ClientInvoice.INVOICE_SAVINGS_BALANCE);
			statement.setString(10, ClientInvoice.INVOICE_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientInvoice clientInvoice = new ClientInvoice();
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
				clientInvoices.add(clientInvoice);
			}
			rs.close();
			return clientInvoices;

		} catch (Exception e) {
			System.out.println("Error querying table : " + e.toString());
		}
		return null;
	}

	public static ArrayList<ClientInvoice> searchInvoicesByCell(int compId, String cellNo, String dateFrom,
			String dateTo, Connection connection) {
		ArrayList<ClientInvoice> clientInvoices = new ArrayList<ClientInvoice>();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM CLIENTINVOICES WHERE compid = ? AND clientid IN (SELECT clientid FROM CLIENTS WHERE clientcellno LIKE ?) AND paydate >= ? AND paydate <= ? AND status != ? AND active = ?");) {
			dateFrom = dateFrom.replace("-", "/");
			dateTo = dateTo.replace("-", "/");
			statement.setInt(1, compId);
			statement.setString(2, "%" + cellNo + "%");
			statement.setString(3, dateFrom);
			statement.setString(4, dateTo);
			statement.setString(5, ClientInvoice.INVOICE_SAVINGS_BALANCE);
			statement.setString(6, ClientInvoice.INVOICE_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientInvoice clientInvoice = new ClientInvoice();
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
				clientInvoices.add(clientInvoice);
			}
			rs.close();
			return clientInvoices;

		} catch (Exception e) {
			System.out.println("Error querying table : " + e.toString());
		}
		return null;
	}

	public static int getClientInvoiceQuantity(Connection connection, int compid, String dateFrom, String dateTo,
			int compinternalid) {
		int quantity = 0;
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT count(*) AS quantity FROM CLIENTINVOICES JOIN COMPANYPRODUCTS ON (CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid) WHERE CLIENTINVOICES.compid = ? AND paydate >= ? AND paydate <= ? AND (status = ? OR status = ?)"
						+ (compinternalid > 0 ? " AND COMPANYPRODUCTS.compinternalid = " + compinternalid : ""));) {
			statement.setInt(1, compid);
			statement.setString(2, dateFrom);
			statement.setString(3, dateTo);
			statement.setString(4, ClientInvoice.INVOICE_PAID);
			statement.setString(5, ClientInvoice.INVOICE_ACTIVE);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				quantity = rs.getInt("quantity");
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICES: " + e.toString());
		}
		return quantity;
	}

	public static double getClientInvoiceTotal(Connection connection, int compid, String dateFrom, String dateTo,
			int compinternalid) {
		double amt = 0;
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT SUM(payamt) AS total FROM CLIENTINVOICES JOIN COMPANYPRODUCTS ON (CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid) WHERE CLIENTINVOICES.compid = ? AND paydate >= ? AND paydate <= ? AND (status = ? OR status = ?)"
						+ (compinternalid > 0 ? " AND COMPANYPRODUCTS.compinternalid = " + compinternalid : ""));) {
			statement.setInt(1, compid);
			statement.setString(2, dateFrom);
			statement.setString(3, dateTo);
			statement.setString(4, ClientInvoice.INVOICE_PAID);
			statement.setString(5, ClientInvoice.INVOICE_ACTIVE);
			ResultSet rs = statement.executeQuery();
			if (rs.next())
				amt = rs.getDouble("total");
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICES: " + e.toString());
		}
		return amt;
	}

	public static ArrayList<ClientInvoice> searchInvoices(Connection connection, int compid, String invoiceno) {
		ArrayList<ClientInvoice> invoices = new ArrayList<ClientInvoice>();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM CLIENTINVOICES WHERE compid = ? AND invno LIKE ? AND canceldate = '' AND active = ? AND (status = ? OR status = ?) ORDER BY paydate DESC, invno DESC")) {
			statement.setInt(1, compid);
			statement.setString(2, "%" + invoiceno + "%");
			statement.setString(3, ClientInvoice.INVOICE_ACTIVE);
			statement.setString(4, ClientInvoice.INVOICE_PAID);
			statement.setString(5, ClientInvoice.INVOICE_ACTIVE);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientInvoice clientInvoice = new ClientInvoice();
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
				invoices.add(clientInvoice);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICES: " + e.toString());
		}
		return invoices;
	}

	public static StringBuilder searchPolicyJSON(Connection connection, int compid, String policyno) {
		StringBuilder sb = new StringBuilder();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT * FROM CLIENTINVOICES JOIN CLIENTINVOICEDATA ON (CLIENTINVOICES.invno = CLIENTINVOICEDATA.invno AND CLIENTINVOICES.compid = CLIENTINVOICEDATA.compid) WHERE CLIENTINVOICES.invno LIKE ? AND CLIENTINVOICES.compid = ? ORDER BY CLIENTINVOICEDATA.invdataref8 DESC, CLIENTINVOICES.invno DESC");) {
			statement.setString(1, "%" + policyno + "%");
			statement.setInt(2, compid);
			ResultSet rs = statement.executeQuery();
			JSONArray invoices = new JSONArray();
			while (rs.next()) {
				JSONObject invoice = new JSONObject();
				invoice.put("clientid", rs.getInt("clientid"));
				invoice.put("prodid", rs.getInt("prodid"));
				invoice.put("compid", rs.getInt("compid"));
				invoice.put("invno", rs.getString("invno"));
				invoice.put("payamt", rs.getDouble("payamt"));
				invoice.put("payvat", rs.getDouble("payvat"));
				invoice.put("paydate", rs.getString("paydate"));
				invoice.put("payref", rs.getString("payref"));
				invoice.put("paynext", rs.getString("paynext"));
				invoice.put("status", rs.getString("status"));
				invoice.put("statusdate", rs.getString("statusdate"));
				invoice.put("statustime", rs.getString("statustime"));
				invoice.put("batchid", rs.getInt("batchid"));
				invoice.put("paymin", rs.getDouble("paymin"));
				invoice.put("payrebateamt", rs.getDouble("payrebateamt"));
				invoice.put("payrebatemin", rs.getDouble("payrebatemin"));
				invoice.put("paydesc", rs.getString("paydesc"));
				invoice.put("payid", rs.getLong("payid"));
				invoice.put("proddate", rs.getDate("proddate"));
				invoice.put("canceldate", rs.getString("canceldate"));
				invoice.put("canceltime", rs.getString("canceltime"));
				invoice.put("canceluserid", rs.getInt("canceluserid"));
				invoice.put("cancelreason", rs.getString("cancelreason"));
				invoice.put("cancelrefno", rs.getString("cancelrefno"));
				invoice.put("salechannel", rs.getString("salechannel"));
				invoice.put("salesubchannel", rs.getString("salesubchannel"));
				invoice.put("invdata", rs.getString("invdata").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", ""));
				invoice.put("invdatatype", rs.getString("invdatatype").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", ""));
				invoice.put("invdataref1", rs.getString("invdataref1").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", ""));
				invoice.put("invdataref2", rs.getString("invdataref2").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", ""));
				invoice.put("invdataref3", rs.getString("invdataref3").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", ""));
				invoice.put("invdataref4", rs.getString("invdataref4").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", ""));
				invoice.put("invdataref5", rs.getString("invdataref5").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", ""));
				invoice.put("invdataref6", rs.getString("invdataref6").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", ""));
				invoice.put("invdataref7", rs.getString("invdataref7").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", ""));
				invoice.put("invdataref8", rs.getString("invdataref8").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", ""));
				invoice.put("invdataamt1", rs.getDouble("invdataamt1"));
				invoice.put("invdataamt2", rs.getDouble("invdataamt2"));
				invoice.put("invdataamt3", rs.getDouble("invdataamt3"));
				invoice.put("invdataamt4", rs.getDouble("invdataamt4"));
				invoice.put("invdataamt5", rs.getDouble("invdataamt5"));
				invoice.put("payrebatemin", rs.getDouble("payrebatemin"));
				invoice.put("invlink", rs.getString("invlink"));
				invoices.put(invoice);

			}
			sb = new StringBuilder(invoices.toString());
		} catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICES,CLIENTINVOICEDATA: " + e.toString());
		}
		return sb;
	}

	public static StringBuilder searchPolicysJSON(Connection connection, int compid, int prodid, String fromDate,
			String toDate, int filterId, String filterWord, boolean usePayDate, int internalCompId, String saleChannel,
			boolean smsInactive) {
		StringBuilder sb = new StringBuilder();
		ArrayList<ClientInvoice> invoices = new ArrayList<ClientInvoice>();
		ArrayList<ClientInvoiceData> invoicedata = new ArrayList<ClientInvoiceData>();
		String sql = "SELECT * FROM CLIENTINVOICES JOIN CLIENTINVOICEDATA ON (CLIENTINVOICES.invno = CLIENTINVOICEDATA.invno AND CLIENTINVOICES.compid = CLIENTINVOICEDATA.compid)"
				+ (smsInactive ? " JOIN CLIENTPAYMENTREFS ON (CLIENTINVOICES.invno = CLIENTPAYMENTREFS.invno) " : " ")
				+ "WHERE CLIENTINVOICES.compid = ? ";
		String dateSql = (usePayDate ? "AND CLIENTINVOICES.paydate >= ? AND CLIENTINVOICES.paydate<= ?"
				: "AND CLIENTINVOICEDATA.invdataref8 >= ? AND CLIENTINVOICEDATA.invdataref8 <= ?");
		sql += dateSql;
		String internalCompSql = (internalCompId > 0
				? " AND CLIENTINVOICES.prodid IN (SELECT prodid FROM COMPANYPRODUCTS WHERE compid = " + compid
						+ " AND compinternalid = " + internalCompId + ")"
				: "");
		String payChannelSql = (saleChannel == null || saleChannel.length() <= 0 || saleChannel.equalsIgnoreCase("ALL")
				? ""
				: " AND CLIENTINVOICES.salechannel = '" + saleChannel + "'");
		String smsInactiveSql = " AND CLIENTINVOICES.status = '" + ClientInvoice.INVOICE_INACTIVE
				+ "' AND CLIENTINVOICES.salechannel = 'SMS' AND CLIENTPAYMENTREFS.status = '" + ClientPaymentRef.ACTIVE
				+ "'";
		sql += internalCompSql;
		sql += payChannelSql;
		sql += (smsInactive ? smsInactiveSql : "");
		if (prodid > 0)
			sql += " AND CLIENTINVOICES.prodid = " + prodid;
		if (filterId == 1)
			sql += " AND CLIENTINVOICES.invno LIKE '%" + filterWord + "%'";
		else if (filterId == 2) // can't filter less than 6 numbers
			sql += " AND CLIENTINVOICES.clientid IN (SELECT clientid FROM CLIENTS WHERE clientcellno LIKE '%"
					+ filterWord + "%')";
		else if (filterId == 3)
			sql += " AND CLIENTINVOICEDATA.invdataref1 ILIKE '%" + filterWord + "%'";
		else if (filterId == 4)
			sql += " AND CLIENTINVOICEDATA.invdataref2 ILIKE '%" + filterWord + "%'";
		else if (filterId == 5)
			sql += " AND CLIENTINVOICEDATA.invdataref3 ILIKE '%" + filterWord + "%'";
		String orderBySql = (usePayDate ? "CLIENTINVOICES.paydate DESC" : "CLIENTINVOICEDATA.invdataref8 DESC");
		sql += " AND CLIENTINVOICES.active = ? AND (CLIENTINVOICES.canceldate = ? OR CLIENTINVOICES.canceldate = ?) ORDER BY "
				+ orderBySql + ", CLIENTINVOICEDATA.invno DESC";
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, compid);
			statement.setString(2, fromDate.replace('-', '/'));
			statement.setString(3, toDate.replace('-', '/'));
			statement.setString(4, ClientInvoice.INVOICE_ACTIVE);
			statement.setString(5, "");
			statement.setString(6, DateUtil.DEFAULT_DATE.toString());
			System.out.println(statement);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				ClientInvoice clientInvoice = new ClientInvoice();
				clientInvoice.setClientId(rs.getInt("clientid"));
				clientInvoice.setProdId(rs.getInt("prodid"));
				clientInvoice.setCompId(rs.getInt("compid"));
				clientInvoice.setInvNo(rs.getString("invno"));
				clientInvoice.setPayAmt(rs.getDouble("payamt"));
				clientInvoice.setPayVat(rs.getDouble("payvat"));
				clientInvoice.setPayDate(rs.getString("paydate"));
				clientInvoice.setPayRef(rs.getString("payref"));
				clientInvoice.setPayNext(rs.getString("paynext"));
				clientInvoice.setStatus(rs.getString("status"));
				clientInvoice.setStatusDate(rs.getString("statusdate"));
				clientInvoice.setStatusTime(rs.getString("statustime"));
				clientInvoice.setBatchId(rs.getInt("batchid"));
				clientInvoice.setPayMin(rs.getDouble("paymin"));
				clientInvoice.setPayRebateAmt(rs.getDouble("payrebateamt"));
				clientInvoice.setPayRebateMin(rs.getDouble("payrebatemin"));
				clientInvoice.setPayDesc(rs.getString("paydesc"));
				clientInvoice.setPayId(rs.getLong("payid"));
				clientInvoice.setProdDate(rs.getDate("proddate"));
				clientInvoice.setCancelDate(rs.getString("canceldate"));
				clientInvoice.setCancelTime(rs.getString("canceltime"));
				clientInvoice.setCancelUserId(rs.getInt("canceluserid"));
				clientInvoice.setCancelReason(rs.getString("cancelreason"));
				clientInvoice.setCancelRefNo(rs.getString("cancelrefno"));
				clientInvoice.setSaleChannel(rs.getString("salechannel"));
				clientInvoice.setSalesSubChannel(rs.getString("salesubchannel"));
				invoices.add(clientInvoice);
				ClientInvoiceData clientInvoiceData = new ClientInvoiceData();
				clientInvoiceData.setInvNo(rs.getString("invno"));
				clientInvoiceData.setCompId(rs.getInt("compid"));
				clientInvoiceData.setInvData(rs.getString("invdata").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataType(rs.getString("invdatatype").trim().replace('\n', ' ')
						.replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef1(rs.getString("invdataref1").trim().replace('\n', ' ')
						.replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef2(rs.getString("invdataref2").trim().replace('\n', ' ')
						.replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef3(rs.getString("invdataref3").trim().replace('\n', ' ')
						.replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef4(rs.getString("invdataref4").trim().replace('\n', ' ')
						.replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef5(rs.getString("invdataref5").trim().replace('\n', ' ')
						.replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef6(rs.getString("invdataref6").trim().replace('\n', ' ')
						.replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef7(rs.getString("invdataref7").trim().replace('\n', ' ')
						.replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataRef8(rs.getString("invdataref8").trim().replace('\n', ' ')
						.replace("\r\n", " ").replace("\t", "").replace("\\", ""));
				clientInvoiceData.setInvDataAmt1(rs.getDouble("invdataamt1"));
				clientInvoiceData.setInvDataAmt2(rs.getDouble("invdataamt2"));
				clientInvoiceData.setInvDataAmt3(rs.getDouble("invdataamt3"));
				clientInvoiceData.setInvDataAmt4(rs.getDouble("invdataamt4"));
				clientInvoiceData.setInvDataAmt5(rs.getDouble("invdataamt5"));
				clientInvoiceData.setInvLink(rs.getString("invlink"));
				clientInvoiceData.setClientId(rs.getInt("clientid"));
				invoicedata.add(clientInvoiceData);
			}
			rs.close();

			sb = new StringBuilder("{\"timestamp\" : \"" + System.currentTimeMillis() + "\", \"clientinvoices\": "
					+ ClientInvoiceDao.getClientInvoicesJSON(invoices) + ", \"clientinvoicedata\": "
					+ ClientInvoiceDataDao.getClientInvoiceDatasJSON(invoicedata) + "}");
		} catch (Exception e) {
			System.out.println("Error querying CLIENTINVOICES,CLIENTINVOICEDATA: " + e.toString());
		}
		return sb;
	}

	public static ArrayList<String> getDailySaleChannelSaleData(Connection connection, int compid, String from,
			String to, int compinternalid) {
		ArrayList<String> data = new ArrayList<String>();
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT EXTRACT(DAY FROM TO_DATE(paydate, 'YYYY/MM/DD')) AS day,"
						+ " salechannel, COUNT(*) FROM CLIENTINVOICES JOIN COMPANYPRODUCTS ON (CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid) WHERE CLIENTINVOICES.compid = ? AND paydate >= ? AND"
						+ " paydate <= ?"
						+ (compinternalid > 0 ? " AND COMPANYPRODUCTS.compinternalid = " + compinternalid + " " : " ")
						+ "AND CLIENTINVOICES.status = ? GROUP BY day, salechannel ORDER BY salechannel,day")) {
			statement.setInt(1, compid);
			statement.setString(2, from.replace('-', '/'));
			statement.setString(3, to.replace('-', '/'));
			statement.setString(4, ClientInvoice.INVOICE_PAID);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int day = rs.getInt("day");
				String saleChannel = rs.getString("salechannel");
				int count = rs.getInt("count");
				data.add(day + "|" + saleChannel + "|" + count);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICES: " + e.toString());
		}
		return data;
	}

	public static ArrayList<String> getMonthlySaleChannelSaleData(Connection connection, int compid, String from,
			String to, int compinternalid) {
		ArrayList<String> data = new ArrayList<String>();
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT EXTRACT(MONTH FROM TO_DATE(paydate, 'YYYY/MM/DD')) AS month,"
						+ " salechannel, COUNT(*) FROM CLIENTINVOICES JOIN COMPANYPRODUCTS ON (CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid) WHERE CLIENTINVOICES.compid = ? AND paydate >= ? AND"
						+ " paydate<= ?"
						+ (compinternalid > 0 ? " AND COMPANYPRODUCTS.compinternalid = " + compinternalid + " " : " ")
						+ "AND CLIENTINVOICES.status = ? GROUP BY month, salechannel ORDER BY salechannel,month")) {
			statement.setInt(1, compid);
			statement.setString(2, from.replace('-', '/'));
			statement.setString(3, to.replace('-', '/'));
			statement.setString(4, ClientInvoice.INVOICE_PAID);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int day = rs.getInt("month");
				String saleChannel = rs.getString("salechannel");
				int count = rs.getInt("count");
				data.add(day + "|" + saleChannel + "|" + count);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICES: " + e.toString());
		}
		return data;
	}

	public static ArrayList<String> getDailyProductSaleData(Connection connection, int compid, String from, String to,
			int compinternalid) {
		ArrayList<String> data = new ArrayList<String>();
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT EXTRACT(DAY FROM TO_DATE(paydate, 'YYYY/MM/DD')) AS day, "
						+ "COMPANYPRODUCTS.prodname, COUNT(*) FROM CLIENTINVOICES JOIN COMPANYPRODUCTS ON (CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid) "
						+ "WHERE CLIENTINVOICES.compid = ? AND paydate >= ? AND paydate <= ?"
						+ (compinternalid > 0 ? " AND COMPANYPRODUCTS.compinternalid = " + compinternalid + " " : " ")
						+ "AND CLIENTINVOICES.status = ? "
						+ "GROUP BY day, COMPANYPRODUCTS.prodname ORDER BY COMPANYPRODUCTS.prodname,day")) {
			statement.setInt(1, compid);
			statement.setString(2, from.replace('-', '/'));
			statement.setString(3, to.replace('-', '/'));
			statement.setString(4, ClientInvoice.INVOICE_PAID);

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int day = rs.getInt("day");
				String saleChannel = rs.getString("prodname");
				int count = rs.getInt("count");
				data.add(day + "|" + saleChannel + "|" + count);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICES: " + e.toString());
		}
		return data;
	}

	public static ArrayList<String> getMonthlyProductSaleData(Connection connection, int compid, String from, String to,
			int compinternalid) {
		ArrayList<String> data = new ArrayList<String>();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT EXTRACT(MONTH FROM TO_DATE(paydate, 'YYYY/MM/DD')) AS month, COMPANYPRODUCTS.prodname,"
						+ " COUNT(*) FROM CLIENTINVOICES JOIN COMPANYPRODUCTS ON (CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid) WHERE CLIENTINVOICES.compid = ? "
						+ " AND paydate >= ? AND paydate <= ?"
						+ (compinternalid > 0 ? " AND COMPANYPRODUCTS.compinternalid = " + compinternalid + " " : " ")
						+ "AND CLIENTINVOICES.status = ?"
						+ " GROUP BY month, COMPANYPRODUCTS.prodname ORDER BY COMPANYPRODUCTS.prodname,month");) {
			statement.setInt(1, compid);
			statement.setString(2, from.replace('-', '/'));
			statement.setString(3, to.replace('-', '/'));
			statement.setString(4, ClientInvoice.INVOICE_PAID);

			System.out.println(statement.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int day = rs.getInt("month");
				String saleChannel = rs.getString("prodname");
				int count = rs.getInt("count");
				data.add(day + "|" + saleChannel + "|" + count);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICES: " + e.toString());
		}
		return data;
	}

	public static int getExpiringPolicyCount(Connection connection, int compid, Date from, Date to,
			int compinternalid) {
		int count = 0;
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT count(distinct(payref)) FROM CLIENTINVOICES JOIN COMPANYPRODUCTS ON (CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid) WHERE CLIENTINVOICES.compid = ?"
						+ " AND proddate >= ? AND proddate <= ? AND paydate < ?"
						+ (compinternalid > 0 ? " AND COMPANYPRODUCTS.compinternalid = " + compinternalid : "")
						+ " AND CLIENTINVOICES.status = ?");) {
			statement.setInt(1, compid);
			statement.setDate(2, from);
			statement.setDate(3, to);
			statement.setString(4, from.toString().replace('-', '/'));
			statement.setString(5, ClientInvoice.INVOICE_PAID);

			ResultSet rs = statement.executeQuery();
			if (rs.next())
				count = rs.getInt("count");
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICES: " + e.toString());
		}
		return count;
	}

	public static int getRenewedPolicyCount(Connection connection, int compid, Date from, Date to, int compinternalid) {
		int count = 0;
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT COUNT(*) FROM CLIENTINVOICES JOIN COMPANYPRODUCTS ON (CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid) "
						+ "WHERE CLIENTINVOICES.compid = ? AND paydate >= ? AND paydate <= ? AND payref IN (SELECT distinct(payref) FROM CLIENTINVOICES WHERE CLIENTINVOICES.compid = ? AND proddate >= ? AND proddate <= ? "
						+ "AND paydate <= ? "
						+ (compinternalid > 0 ? " AND COMPANYPRODUCTS.compinternalid = " + compinternalid : "") + ")"
						+ (compinternalid > 0 ? " AND COMPANYPRODUCTS.compinternalid = " + compinternalid : "")
						+ " AND CLIENTINVOICES.status = ?");) {
			statement.setInt(1, compid);
			statement.setString(2, from.toString().replace('-', '/'));
			statement.setString(3, to.toString().replace('-', '/'));
			statement.setInt(4, compid);
			statement.setDate(5, from);
			statement.setDate(6, to);
			statement.setString(7, from.toString().replace('-', '/'));
			statement.setString(8, ClientInvoice.INVOICE_PAID);

			ResultSet rs = statement.executeQuery();
			if (rs.next())
				count = rs.getInt("count");
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICES: " + e.toString());
		}
		return count;
	}

	public static ArrayList<String> getPolicyMonthlySalesComparison(Connection connection, int compid,
			int compinternalid, String from, String to) {
		ArrayList<String> data = new ArrayList<String>();
		try (PreparedStatement statement = connection
				.prepareStatement("SELECT EXTRACT(DAY FROM TO_DATE(paydate, 'YYYY/MM/DD')) AS day,"
						+ " EXTRACT(MONTH FROM TO_DATE(paydate, 'YYYY/MM/DD')) AS month, EXTRACT(YEAR FROM TO_DATE(paydate, 'YYYY/MM/DD')) AS year,"
						+ " COUNT(*) FROM CLIENTINVOICES JOIN COMPANYPRODUCTS ON"
						+ " (CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid) WHERE CLIENTINVOICES.compid = ? AND paydate >= ? AND paydate <= ?"
						+ (compinternalid > 0 ? " AND COMPANYPRODUCTS.compinternalid = " + compinternalid + " " : " ")
						+ "AND CLIENTINVOICES.status = ? GROUP BY year,month,day ORDER BY year DESC,month DESC,day");) {
			statement.setInt(1, compid);
			statement.setString(2, from.replace('-', '/'));
			statement.setString(3, to.replace('-', '/'));
			statement.setString(4, ClientInvoice.INVOICE_PAID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				data.add(rs.getInt("day") + "|" + rs.getInt("year") + "|"
						+ Util.prefixChar("" + rs.getInt("month"), '0', 2) + "|" + rs.getInt("count"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error querying table CLIENTINVOICES: " + e.toString());
		}
		return data;
	}

	public static boolean updateInvoice(Connection connection, ClientInvoice clientInvoice) {
		boolean updated = false;
		try (PreparedStatement statement = connection
				.prepareStatement("UPDATE CLIENTINVOICES SET clientid = ?, prodid = ?, payamt = ?, payvat = ?"
						+ ",paydate = ?,payref = ?,paynext = ?,status = ?,statusdate = ?,statustime = ?,batchid = ?,paymin = ?,payrebateamt = ?,payrebatemin = ?"
						+ ",paydesc = ?,payid = ?,proddate = ?,canceldate = ?,canceltime = ?,canceluserid = ?,cancelreason = ?,cancelrefno = ?,salechannel = ?"
						+ ",salesubchannel = ?,reversedate = ?,reversetime = ?,reversedesc = ?,reverseuserid = ?,active = ? WHERE compid = ? AND invno = ?");) {
			statement.setInt(1, clientInvoice.getClientId());
			statement.setInt(2, clientInvoice.getProdId());
			statement.setDouble(3, clientInvoice.getPayAmt());
			statement.setDouble(4, clientInvoice.getPayVat());
			statement.setString(5, clientInvoice.getPayDate());
			statement.setString(6, clientInvoice.getPayRef());
			statement.setString(7, clientInvoice.getPayNext());
			statement.setString(8, clientInvoice.getStatus());
			statement.setString(9, clientInvoice.getStatusDate());
			statement.setString(10, clientInvoice.getStatusTime());
			statement.setInt(11, clientInvoice.getBatchId());
			statement.setDouble(12, clientInvoice.getPayMin());
			statement.setDouble(13, clientInvoice.getPayRebateAmt());
			statement.setDouble(14, clientInvoice.getPayRebateMin());
			statement.setString(15, clientInvoice.getPayDesc());
			statement.setLong(16, clientInvoice.getPayId());
			statement.setDate(17, clientInvoice.getProdDate());
			statement.setString(18, clientInvoice.getCancelDate());
			statement.setString(19, clientInvoice.getCancelTime());
			statement.setInt(20, clientInvoice.getCancelUserId());
			statement.setString(21, clientInvoice.getCancelReason());
			statement.setString(22, clientInvoice.getCancelRefNo());
			statement.setString(23, clientInvoice.getSaleChannel());
			statement.setString(24, clientInvoice.getSalesSubChannel());
			statement.setString(25, clientInvoice.getReverseDate());
			statement.setString(26, clientInvoice.getReverseTime());
			statement.setString(27, clientInvoice.getReverseDesc());
			statement.setInt(28, clientInvoice.getReverseUserId());
			statement.setString(29, clientInvoice.getActive());
			statement.setInt(30, clientInvoice.getCompId());
			statement.setString(31, clientInvoice.getInvNo());
			if (statement.executeUpdate() > 0)
				updated = true;
		} catch (Exception e) {
			System.out.println("Error updating CLIENTINVOICES: " + e.toString());
		}
		return updated;
	}

	public static boolean cancelInvoice(Connection connection, int compid, String invno, int userid,
			String cancelreason) {
		boolean cancelled = false;
		try (PreparedStatement statement = connection.prepareStatement(
				"UPDATE CLIENTINVOICES SET canceldate = ?, canceltime = ?, canceluserid = ?, cancelreason = ?, active = ? WHERE compid = ? AND invno = ?");) {
			statement.setString(1, DateUtil.getCurrentCCYYMMDDStr(true, '-'));
			statement.setString(2, DateUtil.getCurrentHHMMSSStr());
			statement.setInt(3, userid);
			statement.setString(4, cancelreason);
			statement.setString(5, ClientInvoice.INVOICE_INACTIVE);
			statement.setInt(6, compid);
			statement.setString(7, invno);

			if (statement.executeUpdate() > 0)
				cancelled = true;
		} catch (Exception e) {
			System.out.println("Error updating CLIENTINVOICES: " + e.toString());
		}
		return cancelled;
	}

	public static StringBuffer getCompClientInvoiceGraphJSON(ArrayList<String> data) {
		StringBuffer sb = new StringBuffer();
		StringBuffer labels = new StringBuffer();
		String currDate = "";
		String currProdId = "";
		HashMap<String, String> prodDataMap = new HashMap<String, String>();
		HashMap<String, HashMap<String, String>> dataMap = new HashMap<String, HashMap<String, String>>();
		for (int i = 0; i < data.size(); i++) {
			String entry = data.get(i);
			String paydate = Util.getValueAt(entry, "|", 0);
			String prodid = Util.getValueAt(entry, "|", 1);
			String prodname = Util.getValueAt(entry, "|", 2);
			String count = Util.getValueAt(entry, "|", 3);
			if (currDate.equals(paydate) == false) {
				dataMap.put(currDate, prodDataMap);
				currDate = paydate;
				prodDataMap.clear();
			}
			prodDataMap.put(prodid, count);
		}
		return sb;
	}

	public static JSONObject searchCancelledPolicys(Connection connection, int compId, String fromDate, String toDate,
			int compinternalid) {
		JSONObject jsonObject = new JSONObject();
		ArrayList<String> data = new ArrayList<String>();
		try (PreparedStatement statement = connection.prepareStatement(
				"SELECT CLIENTINVOICES.invno, CLIENTINVOICES.compid, CLIENTINVOICES.clientid, CLIENTINVOICEDATA.invdataref1, CLIENTINVOICEDATA.invdataref2, CLIENTINVOICES.payref, CLIENTINVOICEDATA.invdataref4, CLIENTINVOICEDATA.invdataref5, CLIENTINVOICES.paydate, CLIENTINVOICEDATA.invdataref8, CLIENTINVOICES.proddate, COMPANYPRODUCTS.prodid, COMPANYPRODUCTS.prodname, CLIENTINVOICES.payamt, CLIENTINVOICES.canceldate, CLIENTINVOICES.cancelreason, CLIENTINVOICES.canceltime, CLIENTINVOICES.canceluserid, CLIENTINVOICES.cancelrefno FROM CLIENTINVOICES JOIN CLIENTINVOICEDATA ON (CLIENTINVOICES.invno = CLIENTINVOICEDATA.invno AND CLIENTINVOICES.compid = CLIENTINVOICEDATA.compid) JOIN COMPANYPRODUCTS ON (CLIENTINVOICES.prodid = COMPANYPRODUCTS.prodid AND CLIENTINVOICES.compid = COMPANYPRODUCTS.compid) WHERE CLIENTINVOICES.compid = ? AND CLIENTINVOICES.canceldate != '' AND CLIENTINVOICES.cancelreason != '' AND CLIENTINVOICES.paydate >= ? AND CLIENTINVOICES.paydate <= ?"
						+ ((compinternalid > 0) ? ("  AND COMPANYPRODUCTS.compinternalid = " + compinternalid + " ")
								: " ")
						+ "ORDER BY CLIENTINVOICES.paydate DESC, CLIENTINVOICES.invno DESC");) {
			statement.setInt(1, compId);
			statement.setString(2, fromDate.replace('/', '-'));
			statement.setString(3, toDate.replace('/', '-'));

			ResultSet rs = statement.executeQuery();
			JSONArray cancelledData = new JSONArray();
			while (rs.next()) {
				JSONObject cancelledInvoice = new JSONObject();
				int clientid = rs.getInt("clientid");
				int prodid = rs.getInt("prodid");
				int compid = rs.getInt("compid");
				String invno = rs.getString("invno");
				double payamt = rs.getDouble("payamt");
				String paydate = rs.getString("paydate");
				String vehiclereg = rs.getString("payref");
				String vehiclemake = rs.getString("invdataref4");
				String vehicleengineno = rs.getString("invdataref5");
				String expdate = rs.getDate("proddate").toString().replace('-', '/');
				String canceldate = rs.getString("canceldate");
				String canceltime = rs.getString("canceltime");
				int canceluserid = rs.getInt("canceluserid");
				String cancelreason = rs.getString("cancelreason");
				String cancelrefno = rs.getString("cancelrefno");
				String clientname = rs.getString("invdataref1").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", "");
				String clientsurname = rs.getString("invdataref2").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", "");
				String startdate = rs.getString("invdataref8").trim().replace('\n', ' ').replace("\r\n", " ")
						.replace("\t", "").replace("\\", "");
				String prodname = rs.getString("prodname");

				cancelledInvoice.put("clientid", clientid);
				cancelledInvoice.put("prodid", prodid);
				cancelledInvoice.put("compid", compid);
				cancelledInvoice.put("invno", invno);
				cancelledInvoice.put("payamt", payamt);
				cancelledInvoice.put("paydate", paydate);
				cancelledInvoice.put("vehiclereg", vehiclereg);
				cancelledInvoice.put("expdate", expdate);
				cancelledInvoice.put("canceldate", canceldate);
				cancelledInvoice.put("canceltime", canceltime);
				cancelledInvoice.put("canceluserid", canceluserid);
				cancelledInvoice.put("cancelreason", cancelreason);
				cancelledInvoice.put("cancelrefno", cancelrefno);
				cancelledInvoice.put("clientname", clientname);
				cancelledInvoice.put("clientsurname", clientsurname);
				cancelledInvoice.put("startdate", startdate);
				cancelledInvoice.put("prodname", prodname);
				cancelledInvoice.put("vehiclemake", vehiclemake);
				cancelledInvoice.put("vehicleengineno", vehicleengineno);
				cancelledData.put(cancelledInvoice);
			}
			jsonObject.put("timestamp", System.currentTimeMillis());
			jsonObject.put("cancelledinvoices", cancelledData);
		} catch (Exception e) {
			System.out.println("Error querying tables CLIENTINVOICES,CLIENTINVOICEDATA: " + e.toString());
		}
		return jsonObject;
	}

	public static ArrayList<ClientInvoice> getClientInvoiceSalesHistory(Connection connection, String toDate, String fromDate, int compId, int prodId) {
		ArrayList<ClientInvoice> clientInvoices = new ArrayList<>();

		try (PreparedStatement statement = connection.prepareStatement(
				"")) {
			statement.setString(1, toDate);
			statement.setString(2, fromDate);
			statement.setInt(3, compId);
			statement.setInt(4, prodId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				//put in relevant object
			}
			rs.close();
		} catch (Exception e) {
			System.out.println();
		}
		return clientInvoices;
	}

	// JSON HELPER METHODS
	public static StringBuilder getClientInvoicesJSON(ArrayList<ClientInvoice> clientInvoices) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for (int i = 0; i < clientInvoices.size(); i++) {
			ClientInvoice invoice = clientInvoices.get(i);
			sb.append(invoice.toJsonString() + (i == clientInvoices.size() - 1 ? "" : ","));
		}
		sb.append("]");
		return sb;
	}

	public static StringBuilder getClientInvoicesJSONCamelCase(ArrayList<ClientInvoice> clientInvoices) {
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for (int i = 0; i < clientInvoices.size(); i++) {
			ClientInvoice invoice = clientInvoices.get(i);
			sb.append(invoice.toJsonStringCamelCase() + (i == clientInvoices.size() - 1 ? "" : ","));
		}
		sb.append("]");
		return sb;
	}
	
	public static JSONArray toJSONArray(ArrayList<ClientInvoice> clientInvoices) {
		JSONArray jsonArray = new JSONArray();
		try {
			for(int i=0;i<clientInvoices.size();i++) {
				jsonArray.put(clientInvoices.get(i).toJSON());
			}
		}catch (Exception e) {
			System.out.println("Error creating json: " + e.toString()); 
		}
		return jsonArray;
	}

}
