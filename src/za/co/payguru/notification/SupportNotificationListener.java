package za.co.payguru.notification;

import java.sql.Connection;
import java.sql.Statement;
import org.postgresql.PGConnection;
import org.postgresql.PGNotification;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import za.co.payguru.dao.CompanyClientSupportDao;
import za.co.payguru.dao.CompanyClientSupportGroupDao;
import za.co.payguru.model.CompanyClientSupport;
import za.co.payguru.model.CompanyClientSupportGroup;
import za.co.payguru.util.DBUtil;
import za.co.payguru.util.IniUtil;

public class SupportNotificationListener {

    private static SupportNotificationListener instance;
    private final Map<Session, Integer> sessionSupportGroups;
    private Thread listenerThread;
    private boolean running;
    private Connection connection;
    
    public static String dbName = ""; 
    public static String dbUser = "";
    public static String dbPass = "";
	public static String dbNameAdvert = "";
	public static String dbUserAdvert = "";
	public static String dbPassAdvert = "";
	public static String advertServerIp = "";
	public static String defaultLang = ""; 

    private SupportNotificationListener() {
        sessionSupportGroups = new ConcurrentHashMap<>();
        IniUtil ini = new IniUtil();
		dbName = ini.getValue("db");
		dbUser = ini.getValue("dbuser");
		dbPass = ini.getValue("dbpass");
		dbNameAdvert = ini.getValue("dbadvert");
		dbUserAdvert = ini.getValue("dbuseradvert");
		dbPassAdvert = ini.getValue("dbpassadvert");
		advertServerIp = ini.getValue("advertserverip");
		defaultLang = ini.getValue("defaultlang");
		if(defaultLang==null||defaultLang.length()<=0)
			defaultLang = "ENG";
    }

    public static SupportNotificationListener getInstance() {
        if (instance == null) {
            synchronized (SupportNotificationListener.class) {
                if (instance == null) {
                    instance = new SupportNotificationListener();
                }
            }
        }
        return instance;
    }

    public void registerSession(Session session, int supportGroupId) {
        sessionSupportGroups.put(session, supportGroupId);
    }

    public void unregisterSession(Session session) {
        sessionSupportGroups.remove(session);
    }

    public void startListener() {
        if (running) return; // Prevent multiple listeners

        listenerThread = new Thread(() -> {
            try {
            	connection = DBUtil.getConnection(dbName,dbUser,dbPass);
                PGConnection pgConnection = connection.unwrap(PGConnection.class);

                try (Statement stmt = connection.createStatement()) {
                    stmt.execute("LISTEN new_support_entry");
                }

                running = true;
                while (running) {
                    PGNotification[] notifications = pgConnection.getNotifications();
                    if (notifications != null) {
                        for (PGNotification notification : notifications) {
                            String[] payload = notification.getParameter().split(",");
                            int supportId = Integer.parseInt(payload[0]);
                            int supportGroupId = Integer.parseInt(payload[1]);
                            
                            CompanyClientSupport compCliSupport = CompanyClientSupportDao.loadCompClientSupport(connection, supportId);
                            sendNotificationToSupportGroup(supportGroupId, compCliSupport.toJSON().toString());
                        }
                    }
                    Thread.sleep(200); // Polling interval
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBUtil.closeConnection(connection);
            }
        });
        listenerThread.start();
    }

    
    public void stopListener() {
        running = false;
        if (listenerThread != null && listenerThread.isAlive()) {
            try {
                listenerThread.interrupt();
                listenerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendNotificationToSupportGroup(int supportGroupId, String message) {
        for (Map.Entry<Session, Integer> entry : sessionSupportGroups.entrySet()) {
            if (entry.getValue() == supportGroupId) {
                Session session = entry.getKey();
                session.getAsyncRemote().sendText(message);
            }
        }
    }
    
    public void saveMessage(int supportGroupId, String message, int supportType) {
    	CompanyClientSupportGroup supportGroup = CompanyClientSupportGroupDao.loadSupportGroup(connection, supportGroupId);
        CompanyClientSupport clientSupport = new CompanyClientSupport();
		clientSupport.setSupportgroupid(supportGroup.getSupportgroupid());
		clientSupport.setSupportmessage(message);
		clientSupport.setSupporttype(supportType);
		clientSupport = CompanyClientSupportDao.createCompanyClientSupport(connection, clientSupport);
    }
}
