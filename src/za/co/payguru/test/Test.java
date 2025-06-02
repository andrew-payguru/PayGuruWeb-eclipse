package za.co.payguru.test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import za.co.payguru.dao.CompanyAgentParamsDao;
import za.co.payguru.http.HTTPUrlConnectionClient;
import za.co.payguru.security.SecurityUtil;
import za.co.payguru.util.DateUtil;
import za.co.payguru.util.Util;

public class Test {
	public static void main(String[] args) {
		getZoneSoftProducts();
		
	}


	public static void getZoneSoftProducts() {
		String docUrl = "https://api.zonesoft.org/v3/documents/getInstances";
		String docAppSecret =  "5820D41E21915BC427E7004FE28C201B";
//		String docClientId = "E3B421AC420215AD37A7215BBF50DD5E";
//		String docClientId = "C261686F7DF804D777C4B546ABBDC16E";
//		String docClientId = "8DD36D884E2AEB5667FBCB51CC35D263";
		String docClientId = "1EE03F1158B878E97460420763CDE3C5";
		
		String docAppKey = "B6801985B68019858B8BB38BB3F0F04DF04D8DF1F3094953E959F3094953E959";
		String docTemplate = "{\"document\":{\"condition\":\"referencia_pagamento = '<|payref|>'\"}}";

		String body = docTemplate.replace("<|payref|>", "1976016d31c7469d8a3ee38681201cd8");

		try {
			HTTPUrlConnectionClient urlConnection = new HTTPUrlConnectionClient(docUrl);
			String signature = SecurityUtil.generateHmacSHA256(body, docAppSecret);
			HashMap<String, String> headers = new HashMap<String, String>();
			headers.put("X-ZS-SIGNATURE", signature);
			headers.put("X-ZS-CLIENT-ID", docClientId);
			headers.put("X-ZS-APP-KEY", docAppKey);
			System.out.println(headers);
			System.out.println(body);
			urlConnection.setHeaders(headers);
			urlConnection.setData(new JSONObject(body));
			urlConnection.sendUrlRequest();

			String response = urlConnection.respMessage;

			System.out.println(response);
			JSONObject json = new JSONObject(response);
			
		}catch (Exception e) {
			System.out.println("ERROR: " + e.toString());
		}
	}
}
