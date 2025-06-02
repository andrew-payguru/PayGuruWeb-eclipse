package pay.guru.qrcode;
import java.rmi.*;
public interface QRRMIMethods extends Remote{
	public boolean createQRCode(String qrData,String qrDir) throws RemoteException;
}
