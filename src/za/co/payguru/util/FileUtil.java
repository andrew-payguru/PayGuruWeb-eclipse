package za.co.payguru.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;



public class FileUtil {

	
	
	
  public static final String ENCODING_UTF8 = "UTF-8";
  
  public static boolean writeLog(String log, String className, String logDir) {
		return writeFile(logDir+"/"+className,  log+"\n", true);
	}
	public static String readFromFile(String fileName) {
  	return FileUtil.readFromFile(fileName, FileUtil.ENCODING_UTF8);
  }
  
  public static String readFromFile(String fileName, String charEncoding) {
  	StringBuffer details = new StringBuffer();
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), charEncoding));
      int i =0;
      while(i!=-1) {
        i = reader.read();
        if(i!=-1)
          details.append(String.valueOf((char)i));
      }
    }
    catch(Exception e) {
      System.out.println("Error : " + e.toString().trim());
    }
    finally {
      try {
        if(reader!=null)
          reader.close();
      }
      catch(Exception e) {
      }
    }
    return details.toString().trim();
  }

  public static boolean writeFile(String fileName, String details, boolean append) {
  	return FileUtil.writeFile(fileName, details, append, false);
  }
  public static boolean writeFile(String fileName, String details, boolean append, boolean ascii) {
    boolean done = false;
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(fileName,append);
      if(ascii==true)
        details = details.replaceAll("\n","\r\n");
      fos.write(details.getBytes());
      done = true;
    }
    catch(Exception e) {
      System.out.println("Error : " + e.toString().trim());
    }
    finally {
      try {
        if(fos!=null)
          fos.close();
      }
      catch(Exception e) {
      }
    }
    return done;
  }
  

	public static StringBuilder readInFileRequest(InputStream is) {
		StringBuilder sb = new StringBuilder();
		try(
				BufferedReader br = new BufferedReader(new InputStreamReader(is,Charset.forName(StandardCharsets.UTF_8.name())));
		){
			int c = 0;
			while((c= br.read()) != -1) {
				sb.append((char)c);
			}
		}catch (Exception e) {
			System.out.println("Error reading file: " + e.toString());
		}finally {
			if(is!=null)
				try {is.close();} catch (IOException e) {System.out.println("Error closing input stream!");}
		}
		return sb;
	}
	
	public static HashMap<String,String> readKeyValueFile(String filePath){
		HashMap<String,String> keyValues = new HashMap<String, String>();
		String errMsg = "";
		for(int z=0;z<1;z++) {
			File iniFile = new File(filePath);
			if((iniFile==null) || (iniFile.exists()==false) || (iniFile.isFile()==false)) {
				errMsg = "ERROR READING FILE: " + filePath;
				break;
			}
			try(
					BufferedReader br = new BufferedReader(new FileReader(iniFile))
			){
				String line = "";
				while((line = br.readLine()) != null) {
					line = line.trim();
					if(line.indexOf("//")==0)
						continue;
					String key = Util.getValueAt(line, "=", 0).trim();
					String value = Util.getValueAt(line, "=", 1).trim();
					keyValues.put(key, value);
				}
			}catch (Exception e) {
				System.out.println(e.toString());
				errMsg = "ERROR READING FILE: " + filePath;
				break;
			}
		}
		System.out.println(errMsg);
		return keyValues;
	}
	
	
}
