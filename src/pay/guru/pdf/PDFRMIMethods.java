package pay.guru.pdf;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PDFRMIMethods extends Remote{
	public boolean createPDF(String srcDir, String srcFileName, String destDir, String destFileName) throws RemoteException;
	public boolean pdfToImage(String pdfDir, String pdfFileName, String destDir) throws RemoteException;

}
