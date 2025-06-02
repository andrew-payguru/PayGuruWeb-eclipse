package za.co.payguru.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/pdfproxy")
public class PdfProxyController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pdfurl = request.getParameter("pdfurl");
		if (pdfurl == null) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid");
			return;
		}
		HttpURLConnection urlConnection = null;
		try {
			URL url = new URL(pdfurl);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");

			response.setContentType("application/pdf");
			response.setHeader("Access-Control-Allow-Origin", "*");

			try (
					InputStream inputStream = urlConnection.getInputStream();
					OutputStream outputStream = response.getOutputStream()
					) {

				byte[] buffer = new byte[8192];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
			} catch (IOException e) {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching the PDF");
			}
		}catch (IOException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error connecting to the remote server");
		} finally {
			if (urlConnection != null) {
				urlConnection.disconnect(); // Close the connection
			}
		}
	}
}
