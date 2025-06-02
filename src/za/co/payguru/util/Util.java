package za.co.payguru.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Util {
	
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


	
	public static ArrayList<String> fileSBToArrayList(StringBuilder sb){
		ArrayList<String> data = new ArrayList<String>();
		if(sb==null)
			return data;
		String [] sbAr = sb.toString().split("\n"); 
		for(String line : sbAr) {
			data.add(line.trim());
		}
		return data;
	}

	public static String prefixChar(String details, char val, int totallen) {
		int len1 = details.length();
		for(int c=0;c<totallen-len1;c++) {
			details = val + details;
		}
		return details;
	}

	public static String getValueAt(String details, String delim, int idx) {
		String value = "";
		int c = 0;
		StringTokenizer ts = new StringTokenizer(details,delim);
		while(ts.hasMoreTokens()) {
			String val = ts.nextToken();
			if(c==idx) {
				value = val;
				break;
			}
			c++;
		}
		return value;
	}
	public static String replaceValueAt(String details, String separator, int pos, String newValue) {
		String newString = "";
		try {
			if(details==null)
				return newString;
			if(separator==null||details.indexOf(separator)<0||newValue==null)
				return details;
			if(separator.equals("|"))
				separator = "\\|";
			String [] detailsAr = details.split(separator);
			if(separator.equals("\\|"))
				separator = "|";
			for(int i=0;i<detailsAr.length;i++) {
				newString+=(i==0?"":separator);
				if(i==pos) {
					newString+=newValue;
					continue;
				}
				newString+=detailsAr[i];
			}
		}catch (Exception e) {newString = details;}
		return newString;
	}

	public static int parseInt(String details, int defValue) {
		try {
			defValue = Integer.parseInt(details);
		}
		catch(Exception e) {
		}
		return defValue;
	}

	public static long parseLong(String details, long defValue) {
		try {
			defValue = Long.parseLong(details);
		}
		catch(Exception e) {
		}
		return defValue;
	}

	public static double parseDouble(String details, double defValue) {
		try {
			defValue = Double.parseDouble(details);
		}
		catch(Exception e) {
		}
		return defValue;
	}

	public static boolean parseBoolean(String details) {
		boolean valid = false;
		try {
			if( (details.equalsIgnoreCase("true")) || (details.equalsIgnoreCase("1")) || (details.equalsIgnoreCase("t")))
				valid = true;
		}catch (Exception e) {
		}
		return valid;
	}


	public static String replaceRepetitions(String details, char val1, char val2) {
		Vector data = new Vector();
		StringBuffer sb = new StringBuffer();
		if(details==null||details.length()<=0) {
			details = "";
			return details;
		}
		boolean first = true;
		for(char charEntry : details.toCharArray()) {
			if(charEntry!=val1) {
				sb.append(charEntry);
				first = true;
			}else {
				if(first == true) {
					sb.append(String.valueOf(val2));
					first = false;
				}
			}
		}
		return sb.toString();
	}

	public static int countCharOccurancesInString(String str, char c) {
		int count = 0;
		try {
			for(int i=0; i < str.length(); i++) {
				if(str.charAt(i) == c)
					count++;
			}
		}catch(Exception e) {}
		return count;
	} 
	public static String fixDbString(String data) {
		if(data==null)
			data = "";
		data = data.replace("\n", " ").replace("'", "");
		return data;
	}
	public static String fixDbFieldName(String data) {
		if(data==null)
			data = "";
		data = data.replace(" ", "").replace("-", "").replace("@", "").replace("$", "").replace("&", "").replace("!", "").replace("#", "").replace("%", "").replace("?", "").replace(")", "").replace("(", "");
		return data;
	}
	public static String fixInputName(String data) {
		if(data==null)
			data = "";
		data = data.replace("@", "").replace("$", "").replace("&", "").replace("#", "").replace("%", "");
		return data;
	}
	public static String removeSpecialCharactersSpace(String data) {
		if(data==null)
			data = "";
		data = data.replace(" ", "").replace("-", "").replace("_", "").replace("@", "").replace("$", "").replace("&", "").replace("!", "").replace("#", "").replace("%", "").replace("?", "").replace(")", "").replace("(", "").replace("+", "").replace("=", "").replace("|", "");
		return data;
	}

	public static boolean containsSpecialChar(String data) {
		boolean valid = false;
		try {
			if(data.contains("!")||data.contains("@")||data.contains("#")||data.contains("$")||data.contains("%")||data.contains("&")||data.contains("*")||data.contains("?")||data.contains("+")||data.contains("=")||data.contains("-")||data.contains("_"))
				valid = true;
		}catch(Exception e) {}
		return valid;
	}
	public static boolean containsNumeric(String data) {
		boolean valid = false;
		try {
			if(data.contains("1")||data.contains("2")||data.contains("3")||data.contains("4")||data.contains("5")||data.contains("6")||data.contains("7")||data.contains("8")||data.contains("9")||data.contains("0"))
				valid = true;
		}catch(Exception e) {}
		return valid;
	}
	public static boolean validatePasswordStrength(String password) {
		boolean valid = false;
		try {
			for(int z=0;z<1;z++) {
				if(password.length()<8)
					break;
				if(containsSpecialChar(password)==false)
					break;
				if(containsNumeric(password)==false)
					break;
				valid = true;
			}
		}catch (Exception e) {}
		return valid;
	}
	
	public static ArrayList<String> getUniqueData(ArrayList<String> dataAr){
		return getUniqueData(dataAr, 1);
	}
		
	public static ArrayList<String> getUniqueData(ArrayList<String> dataAr, int uniqueColumns){
		ArrayList<String> unique = new ArrayList<String>();
		for(int i=0;i<dataAr.size();i++) {
			String dataLine = Util.fixDelimitedString(dataAr.get(i),"|",'-');
			String data = "";
			for(int j=1;j<=uniqueColumns;j++) {
				if(j>1)
					data+=" ";
				data+=Util.getValueAt(dataLine,"|",j);
			}
			if(unique.indexOf(data)>=0||data.equals("-"))
				continue;
			unique.add(data);
		}
		return unique;
	}
	
	public static StringBuilder createMonthlyPolicyGraphData(ArrayList<String> uniqueChannels,  ArrayList<String> data, String jsonParentAttributeId, String uniqueJsonAttributeId) {
		return createDailyPolicyGraphData(uniqueChannels, 12, data, jsonParentAttributeId, uniqueJsonAttributeId);
	}
	public static StringBuilder createCurrentPolicyData(ArrayList<String> dayData, ArrayList<String> monthData, String jsonUniqueId) {
		ArrayList<String> uniqueData = getUniqueData(dayData);
		int monthLength = DateUtil.getTotDaysInMonth(DateUtil.getCurrentCCYYMMDDDate());
		StringBuilder sbData = new StringBuilder();
		sbData.append("{");
		sbData.append(createDailyPolicyGraphData(uniqueData, monthLength, dayData, "daydata", jsonUniqueId)); 
		sbData.append(",");
		uniqueData = getUniqueData(monthData);
		sbData.append(createMonthlyPolicyGraphData(uniqueData, monthData, "monthdata", jsonUniqueId));
		sbData.append("}");

		return sbData;
	}
	
	
	public static StringBuilder createDailyPolicyGraphData(ArrayList<String> uniqueData, int monthLength, ArrayList<String> data, String jsonParentAttributeId, String uniqueJsonAttributeId) {
		StringBuilder sbData = new StringBuilder();
		StringBuilder labels = new StringBuilder();
		boolean doneLabels = false;
		sbData.append("\""+jsonParentAttributeId+"\" : {");
		sbData.append("\"values\" : [");
		for(int i=0;i<uniqueData.size();i++) {
			sbData.append((i==0?"":",")+"{");
			String currVal = uniqueData.get(i);
			sbData.append("\""+uniqueJsonAttributeId+"\" : \"" + currVal + "\",");
			sbData.append("\"data\" : [");
			for(int d=1;d<=monthLength;d++) {
				if(!doneLabels)
					labels.append((d==1?"":",")+"\""+(Util.prefixChar(""+d,'0',2))+"\"");
				int count = 0;
				for(int j=0;j<data.size();j++) {
					String dataVal = data.get(j);
					int dayVal = Util.parseInt(Util.getValueAt(dataVal,"|",0),0);
					String uniqueVal = Util.getValueAt(dataVal,"|",1);
					if(dayVal!=d||uniqueVal.equals(currVal)==false)
						continue;
					count = Util.parseInt(Util.getValueAt(dataVal,"|",2),0);
					break;
				}
				sbData.append((d==1?"":",")+count);
			}
			doneLabels=true;
			sbData.append("]");
			sbData.append("}");
		}
		sbData.append("],");
		sbData.append("\"labels\" : ["+labels.toString()+"]");
		sbData.append("}");
		return sbData;
	}
	
	public static StringBuilder createDailyPolicyGraphDataPerMonth(ArrayList<String> uniqueData, ArrayList<String> data, String jsonParentAttributeId, String uniqueJsonAttributeId) {
		StringBuilder sbData = new StringBuilder();
		StringBuilder labels = new StringBuilder();
		boolean doneLabels = false;
		sbData.append("{");
		sbData.append("\"values\" : [");
		for(int i=0;i<uniqueData.size();i++) {
			sbData.append((i==0?"":",")+"{");
			String currVal = uniqueData.get(i);
			sbData.append("\""+uniqueJsonAttributeId+"\" : \"" + currVal + "\",");
			sbData.append("\"data\" : [");
			for(int d=1;d<=31;d++) {
				if(!doneLabels)
					labels.append((d==1?"":",")+"\""+(Util.prefixChar(""+d,'0',2))+"\"");
				int count = 0;
				for(int j=0;j<data.size();j++) {
					String dataVal = data.get(j);
					int dayVal = Util.parseInt(Util.getValueAt(dataVal,"|",0),0);
					String uniqueVal = Util.getValueAt(dataVal,"|",1) + " " + Util.getValueAt(dataVal, "|", 2);
					if(dayVal!=d||uniqueVal.equals(currVal)==false)
						continue;
					count = Util.parseInt(Util.getValueAt(dataVal,"|",3),0);
					break;
				}
				sbData.append((d==1?"":",")+count);
			}
			doneLabels=true;
			sbData.append("]");
			sbData.append("}");
		}
		sbData.append("],");
		sbData.append("\"labels\" : ["+labels.toString()+"]");
		sbData.append("}");
		return sbData;
	}
	public static String fixDelimitedString(String data, String delimiter, char fixVal) {
		String fixedString = "";
		try {
			String [] arr = data.split("");
			StringBuilder sb = new StringBuilder();
			int foundLastIdx = -1;
			for(int i=0;i<arr.length;i++) {
				if(arr[i].equals(delimiter)) {
					if(i==(foundLastIdx+1)||i==0)
							sb.append(fixVal);
					foundLastIdx = i;
				}
				sb.append(arr[i]);
				if(arr[i].equals(delimiter)&&i==arr.length-1)
					sb.append(fixVal);
			}
			fixedString = sb.toString();
		}catch (Exception e) {System.out.println(e.toString());}
		return fixedString;
	}


	public static String generateUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	public static char generateRandomLetter() {
		Random random = new Random();
		char randomLetter = 'A'; // Default letter
		int month = LocalDate.now().getMonthValue();
		if (month == 1) { // January: A-C
			randomLetter = (char) ('A' + random.nextInt(3));
		} else if (month == 2) { // February: D-F
			randomLetter = (char) ('D' + random.nextInt(3));
		} else if (month == 3) { // March: G-I
			randomLetter = (char) ('G' + random.nextInt(3));
		} else if (month == 4) { // April: J-L
			randomLetter = (char) ('J' + random.nextInt(3));
		} else if (month == 5) { // May: M-O
			randomLetter = (char) ('M' + random.nextInt(3));
		} else if (month == 6) { // June: P-R
			randomLetter = (char) ('P' + random.nextInt(3));
		} else if (month == 7) { // July: S-U
			randomLetter = (char) ('S' + random.nextInt(3));
		} else if (month == 8) { // August: V-X
			randomLetter = (char) ('V' + random.nextInt(3));
		} else if (month == 9) { // September: Y-Z
			randomLetter = (char) ('Y' + random.nextInt(2));
		} else if (month == 10) { // October: A-F
			randomLetter = (char) ('A' + random.nextInt(6));
		} else if (month == 11) { // November: G-L
			randomLetter = (char) ('G' + random.nextInt(6));
		} else if (month == 12) { // December: M-R
			randomLetter = (char) ('M' + random.nextInt(6));
		}
		return randomLetter;
	}
	
	public static boolean validEmail(String email) {
		if (email == null) 
			return false;
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}
	public static double calcDiscountAmt(double origAmt, int discount, boolean roundUp) {
	    double discountAmt = origAmt * (discount / 100.0); 
	    if (roundUp) 
	    	discountAmt = Math.ceil(discountAmt);
	    else
	    	discountAmt = Math.round(discountAmt * 100.0) / 100.0;
	    return discountAmt;
	}
	
	public static double calcDiscountedValue(double origAmt, int discount, boolean roundUp) {
	    double discountAmt = origAmt * (discount / 100.0); 
	    double calcAmt = origAmt - discountAmt; 

	    if (roundUp) {
	        calcAmt = Math.ceil(calcAmt);
	    } else {
	        calcAmt = Math.round(calcAmt * 100.0) / 100.0;
	    }

	    return calcAmt;
	}
	
	public static String formatDecimals(double number) {
        try {
            String numberString = String.valueOf(number);

            if (numberString.contains(".")) {
                String integerPart = numberString.substring(0, numberString.indexOf('.'));
                String decimalPart = numberString.substring(numberString.indexOf('.'));

                if (decimalPart.length() == 1) {
                    decimalPart += "00";
                } else if (decimalPart.length() == 2) {
                    decimalPart += "0";
                } else if (decimalPart.length() > 3) {
                    decimalPart = decimalPart.substring(0, 3);
                }

                numberString = integerPart + decimalPart;
            } else {
                numberString += ".00";
            }

            String integer = numberString.substring(0, numberString.indexOf('.'));
            String decimal = numberString.substring(numberString.indexOf('.'));
            StringBuilder formattedInteger = new StringBuilder();

            char[] integerArray = integer.toCharArray();
            int position = 1;

            for (int i = integerArray.length - 1; i >= 0; i--) {
                formattedInteger.insert(0, integerArray[i]);
                if (position % 3 == 0 && i != 0) {
                    formattedInteger.insert(0, ',');
                }
                position++;
            }

            return formattedInteger + decimal;
        } catch (Exception e) {
            System.err.println("Error formatting number: " + e.getMessage());
            return "";
        }
    }
}
