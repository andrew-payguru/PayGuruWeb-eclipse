package za.co.payguru.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class HTTPUrlConnectionClient {
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CONTENT_TYPE_XML= "application/xml";
	public static final String CONTENT_TYPE_MULTI = "multipart/form-data";
	public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
	public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";
	public static final String CONTENT_TYPE_UNKNOWN = "unknown";

	public static final String HTTP_METHOD_POST = "POST";
	public static final String HTTP_METHOD_GET = "GET";
	public static final String HTTP_METHOD_PUT = "PUT";
	public static final String HTTP_METHOD_DELETE = "DELETE";

	private static final int DEFAULT_TIMEOUT = 5000;

	private String url = new String();
	private String urlPathParams = new String();
	private String httpMethod = HTTP_METHOD_POST;
	private String contentType = CONTENT_TYPE_JSON;
    private String accept = "";
	private JSONObject data = new JSONObject();
	private HashMap<String, String> headers = new HashMap<String, String>();
	public int respStatusCode = 0;
	public String respMessage = new String();
	public String respDetails = new String();
	public String respContentType = new String();

	

	public HTTPUrlConnectionClient(String url, String urlPathParams) {
		this.url = url;
		this.urlPathParams = urlPathParams;
	}
	public HTTPUrlConnectionClient(String url) {
		this.url = url;
	}



	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlPathParams() {
		return urlPathParams;
	}
	public void setUrlPathParams(String urlPathParams) {
		this.urlPathParams = urlPathParams;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public JSONObject getData() {
		return data;
	}
	public void setData(JSONObject data) {
		this.data = data;	
	}
	public HashMap<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}
	public void setAccept(String accept) {
        this.accept = accept;
    }
	
	
	public boolean sendUrlRequest() {
		boolean sent = false;
		HttpURLConnection connection = null;
		for(int z=0;z<1;z++) {
			try {
				URL urlCon = new URL(url+urlPathParams);
				connection = (HttpURLConnection) urlCon.openConnection();
				connection.setRequestMethod(httpMethod.toUpperCase());
				connection.setRequestProperty("Content-Type", contentType);
				if(accept.isEmpty()==false)
					connection.setRequestProperty("Accept", accept);

				if(headers!=null) {
					for (String key : headers.keySet()) {
						connection.setRequestProperty(key,headers.get(key));
					}
				}
				connection.setConnectTimeout(DEFAULT_TIMEOUT);
				connection.setDoInput(true);

				if (!httpMethod.equals(HTTP_METHOD_GET)) {
					connection.setDoOutput(true);
					if(contentType.equals(CONTENT_TYPE_JSON)) {
						if(data==null) {
							respDetails = "Empty json data";
							return sent;
						}
					}
					try (OutputStream os = connection.getOutputStream()) {
						byte[] input = data.toString().getBytes(StandardCharsets.UTF_8);
						os.write(input, 0, input.length);
					}
				}else {
	                connection.setDoOutput(false);
	            }
				
				
				respStatusCode = connection.getResponseCode();
				System.out.println(respStatusCode);
				respContentType = connection.getHeaderField("Content-Type");
				if(respContentType==null)
					respContentType = CONTENT_TYPE_UNKNOWN;
				
				if(respContentType.indexOf(CONTENT_TYPE_MULTI)>=0)
					respContentType = CONTENT_TYPE_MULTI;
				if(respContentType.indexOf(CONTENT_TYPE_JSON)>=0)
					respContentType = CONTENT_TYPE_JSON;
				if(respContentType.indexOf(CONTENT_TYPE_XML)>=0)
					respContentType = CONTENT_TYPE_XML;
				if(respContentType.indexOf(CONTENT_TYPE_TEXT_PLAIN)>=0)
					respContentType = CONTENT_TYPE_TEXT_PLAIN;
				if(respContentType.indexOf(CONTENT_TYPE_TEXT_HTML)>=0)
					respContentType = CONTENT_TYPE_TEXT_HTML;
				if(respStatusCode>=200 && respStatusCode <300) {
					try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
						String respLine = null;
						StringBuilder respBuffer = new StringBuilder();
						while ((respLine = br.readLine()) != null) {
							respBuffer.append(respLine.trim());
						}
						respMessage = respBuffer.toString();
						sent = true;
					}catch (Exception e) {
						System.out.println("Error reading response: " + e.toString());
					}
				}else {
					System.out.println("Error Response Code: " + respStatusCode);
				}

			}catch (Exception e) {
				System.out.println(e.toString());
				System.out.println("Error making connection to: " + url);
			}finally {
				if(connection!=null)
					connection.disconnect();
			}
		}
		return sent;
	}
	
	public static void redirectRequest(HttpServletRequest request, HttpServletResponse response, String newBaseUrl) {
	    HttpURLConnection connection = null;
	    try {
	        // Extract the original request path after the IP/domain
	        String requestURI = request.getRequestURI(); // Gets everything after the domain

	        // Construct the new URL with the new base
	        String targetUrlString = newBaseUrl + requestURI + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
	        System.out.println(targetUrlString);
	        URL targetUrl = new URL(targetUrlString);

	        connection = (HttpURLConnection) targetUrl.openConnection();
	        connection.setRequestMethod(request.getMethod());

	        // Copy headers from original request
	        Enumeration<String> headerNames = request.getHeaderNames();
	        while (headerNames.hasMoreElements()) {
	            String headerName = headerNames.nextElement();
	            String headerValue = request.getHeader(headerName);
	            connection.setRequestProperty(headerName, headerValue);
	        }

	        connection.setDoOutput(true);

	        // Forward request body
	        try (
	            InputStream inputStream = request.getInputStream();
	            OutputStream outputStream = connection.getOutputStream()
	        ) {
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = inputStream.read(buffer)) != -1) {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	        }

	        int responseCode = connection.getResponseCode();
	        response.setStatus(responseCode);

	        // Copy headers from target response
	        for (String headerKey : connection.getHeaderFields().keySet()) {
	            if (headerKey != null) {
	                response.setHeader(headerKey, connection.getHeaderField(headerKey));
	            }
	        }

	        // Forward response body
	        try(
	        		InputStream responseStream = (responseCode >= 400) ? connection.getErrorStream() : connection.getInputStream();
	        ){
		        // Forward response body (including error messages)
		        if (responseStream != null) {
		            try (OutputStream outputStream = response.getOutputStream()) {
		                byte[] buffer = new byte[1024];
		                int bytesRead;
		                while ((bytesRead = responseStream.read(buffer)) != -1) {
		                    outputStream.write(buffer, 0, bytesRead);
		                }
		            }
		        }
	        }catch (Exception e) {
				System.out.println("Error creating input stream: " + e.toString());
			}

	    } catch (Exception e) {
	        System.out.println("Error redirecting request to: " + newBaseUrl);
	        e.printStackTrace();
	        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    } finally {
	        if (connection != null) {
	            connection.disconnect();
	        }
	    }
	}
	
	
}
