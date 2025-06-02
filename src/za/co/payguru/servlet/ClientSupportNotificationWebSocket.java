package za.co.payguru.servlet;

import java.io.IOException;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONObject;

import za.co.payguru.notification.SupportNotificationListener;
import za.co.payguru.util.Constants;
import za.co.payguru.util.Util;

@ServerEndpoint("/support/chat/{supportGroupId}/{supportType}")
public class ClientSupportNotificationWebSocket {

	@OnOpen
	public void onOpen(Session session, @PathParam("supportGroupId") int supportGroupId, @PathParam("supportType") int supportType) {
		session.getUserProperties().put("supportGroupId", supportGroupId);
		session.getUserProperties().put("supportType", supportType);
		SupportNotificationListener.getInstance().registerSession(session, supportGroupId);
	}

	@OnMessage
	public void onMessage(String json, Session session) {
		int supportGroupId = Util.parseInt(""+session.getUserProperties().get("supportGroupId"),0);
		int supportType = Util.parseInt(""+session.getUserProperties().get("supportType"), 0);
		try {
			JSONObject supportJSON = new JSONObject(json);
			String message = supportJSON.getString("supportmessage");
			if(supportGroupId==0) {
				System.out.println("Error On Message, invalid support group ID...");
				return;
			}
			if(supportType==0) {
				System.out.println("Error On Message, invalid support type...");
				return;
			}
			if(message==null||message.isEmpty()) {
				System.out.println("Error On Message, invalid message...");
				return;
			}
			message = message.replace("\r\n", Constants.RETURN_TAG).replace("\n", Constants.RETURN_TAG).replace("\r", Constants.RETURN_TAG);
			String[] msgAr = message.split(Constants.RETURN_TAG);
			while (message.endsWith(Constants.RETURN_TAG)) {
				message = message.substring(0, message.lastIndexOf(Constants.RETURN_TAG));
			}
			while (message.startsWith(Constants.RETURN_TAG)) {
				message = message.substring(Constants.RETURN_TAG.length());
			}
			boolean validated = false;
			for (String line : msgAr) {
				if (!line.trim().isEmpty()) {
					validated = true;
					break;
				}
			}
			if (message.length() <= 0 || !validated) {
				System.out.println("Error On Message, invalid message 2...");
				return;
			}
			SupportNotificationListener.getInstance().saveMessage(supportGroupId, message, supportType);      
		}catch (Exception e) {
			System.out.println("Error On Message: " + e.toString());
		}
	}

	@OnClose
	public void onClose(Session session) {
		SupportNotificationListener.getInstance().unregisterSession(session);
		System.out.println("Connection closed.");
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.err.println("Error in WebSocket connection: " + throwable.getMessage());
		try {
			session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, "Error in WebSocket connection"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
