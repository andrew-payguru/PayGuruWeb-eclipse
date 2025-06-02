package za.co.payguru.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import za.co.payguru.notification.SupportNotificationListener;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Start the listener thread when the app is deployed
    	System.out.println("Starting Support Notification Thread...");
        SupportNotificationListener.getInstance().startListener();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Stop the listener thread when the app is undeployed or Tomcat is shutting down
    	System.out.println("Stopping Support Notification Thread...");
        SupportNotificationListener.getInstance().stopListener();
    }
}
