package pay.guru.recovery;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RecoveryRMIMethods extends Remote{
	public boolean remakeProdLink(int compId, String invNo) throws RemoteException;
}
