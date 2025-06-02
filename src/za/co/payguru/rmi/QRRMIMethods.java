package za.co.payguru.rmi;
import java.rmi.*;
public interface QRRMIMethods extends Remote{
	public boolean createQRCode(String qrData,String qrDir) throws RemoteException;
}
