package za.co.payguru.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONHelper {
	public final static String JSON_ERR_MSG_LABEL = "PG_ERROR_MSG";

	public static JSONArray getJSONArrayValue(JSONObject jsonBody, String key) {
		JSONArray array = new JSONArray();
		try {
			array = jsonBody.getJSONArray(key);
		}catch (Exception e) {
			System.out.println("Invalid key value: " + key);
		}
		return array;
	}
	public static long getLongValue(JSONObject jsonBody, String key) {
		long jsonLong = 0;
		try {
			jsonLong = jsonBody.getLong(key);
		}catch(Exception e) {
			System.out.println("Invalid key value: " + key);
		}
		return jsonLong;
	}
	public static boolean getBooleanValue(JSONObject jsonBody, String key) {
		boolean jsonBool = false;
		try {
			jsonBool = jsonBody.getBoolean(key);
		}catch(Exception e) {
			System.out.println("Invalid key value: " + key);
		}		
		return jsonBool;
	}
	public static int getIntValue(JSONObject jsonBody, String key) {
		int jsonInt = 0;
		try {
			jsonInt = jsonBody.getInt(key);
		}catch(Exception e) {
			System.out.println("Invalid key value: " + key);
		}
		return jsonInt;
	}
	public static double getDoubleValue(JSONObject jsonBody, String key) {
		double jsondouble = 0;
		try {
			jsondouble = jsonBody.getDouble(key);
		}catch(Exception e) {
			System.out.println("Invalid key value: " + key);
		}
		return jsondouble;
	}
	public static String getValue(JSONObject jsonBody, String key) {
		String jsonStr = "";
		try {
			jsonStr = jsonBody.getString(key);
		}catch(Exception e) {
			System.out.println("Invalid key value: " + key);
		}
		if(jsonStr==null)
			jsonStr = "";
		return jsonStr;
	}
	public static StringBuilder getErrorJson(String errMsg) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\""+JSON_ERR_MSG_LABEL+"\" : \""+errMsg+"\"\n");
		sb.append("}");
		return sb;
	}

	public static StringBuilder getSuccessJson(String successMsg) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\"SUCCESS\" : \""+successMsg+"\"\n");
		sb.append("}");
		return sb;
	}
	public static StringBuilder createJsonMessage(String name, String value) {
		StringBuilder sb = new StringBuilder();
		sb.append("{\n");
		sb.append("\""+name+"\" : \""+value+"\"\n");
		sb.append("}");
		return sb;
	}

	public static String createJsonAttributeString(String name, Object value) {
		String json = "";
		String quote = "";
		try {
			if(value.getClass()==String.class||value.getClass()==Date.class||value.getClass()==char.class)
				quote = "\"";
		}catch(Exception e) {
			System.out.println("Error -> createJsonAttributeString: " + e.toString());
		}
		json = "\""+name+"\" : "+quote+value+quote;
		return json;
	}

	public static boolean isErrorJson(StringBuilder sb) {
		boolean error = false;
		if(sb.toString().indexOf(JSON_ERR_MSG_LABEL)>0)
			error = true;
		return error;
	}

	public static String readJsonBody(HttpServletRequest request) {
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			return "";
		} finally {
			if (bufferedReader != null)
				try {bufferedReader.close();} catch (IOException ex) {}
		}
		return stringBuilder.toString();
	}

	public static HashMap<String, Object> getJsonBodyMap(HttpServletRequest request) {
		if(request.getContentType()==null)
			return new HashMap<String, Object>();
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String jsonBdy = readJsonBody(request);
			if(jsonBdy==null || jsonBdy.length()<=0)
				return new HashMap<String, Object>();
			JSONObject jObj = new JSONObject(jsonBdy);
			Iterator<String> it = jObj.keys();

			while(it.hasNext())
			{
				String key = it.next(); 
				Object o = jObj.get(key); 
				jsonMap.put(key, o);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonMap;
	}

	public static JSONObject getJsonBody(HttpServletRequest request) {
		if(request.getContentType()==null)
			return new JSONObject();
		if(request.getContentType().equals("application/json")==false) {
			return new JSONObject();
		}
		HashMap<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			String jsonBdy = readJsonBody(request);
			if(jsonBdy==null || jsonBdy.length()<=0)
				return new JSONObject();
			JSONObject jObj = new JSONObject(jsonBdy);
			return jObj;
		} catch (JSONException e) {
			e.printStackTrace();
			return new JSONObject();
		}
	}

	public static String fixJSONString(String jsonValue) {
		if(jsonValue==null)
			jsonValue = "";
		jsonValue = jsonValue.trim().replace('\n', ' ').replace("\r\n", " ").replace("\t", "").replace("\\", "").replace("\"", "");
		return jsonValue;
	}

	public static boolean containsJSONObject(JSONArray jsonArray, JSONObject jsonObject) {
		boolean valid = false;
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject currentObj = jsonArray.getJSONObject(i);
				if (currentObj.equals(jsonObject)) { 
					valid = true;
					break;
				}
			}
		}catch (Exception e) {
			System.out.println("Error JSON Method: containsJSONObject - " + e.toString());
		}
		return valid;
	}
	
	public static JSONObject createObjectFromPart(Part jsonPart) {
		JSONObject json = new JSONObject();
		StringBuilder jsonStringBuilder = new StringBuilder();
		try (
			InputStream jsonStream = jsonPart.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(jsonStream))
		) {
			String line;
			while ((line = reader.readLine()) != null) {
				jsonStringBuilder.append(line);
			}
			json = new JSONObject(jsonStringBuilder.toString());
		}
		catch (Exception e) {
			System.out.println("Error creating object from part: " + e.toString());
		}
		return json;
	}
}
